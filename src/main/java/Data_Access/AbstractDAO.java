package Data_Access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AbstractDAO<T> {


    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> object;       // declar un obiect de tipul clasa

    /**
     * in constructor salvam in object clasa generica specificata in implementarea subclaselor AbstractDao
     * adica ClientDao, ComandaDAO si ProdudtDAO
     */
    public AbstractDAO() {
        this.object = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * selecteaza o inregistrare din baza de date asociata cu id-ul specificat, creeaza o instanța a obiectului T si seteaza valorile
     * campurilor obiectului folosind valorile din baza de date.
     *
     * @param field
     * @return un obiect de tip T asociat cu acel id din baza de date
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(object.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * metoda cauta obiectul cu id ul dat ca parametru
     *
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda creeaza obiectele si le pune intr o lista
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = object.getDeclaredConstructors();  // vector cu parametrii fiecarui constructor al fiecarei clase
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);   // ca sa poata accesa variabilele de tip private
                T instance = (T) ctor.newInstance();   // creeaza un constructor specific pentru fiecare clasa in parte
                for (Field field : object.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, object);
                    Method method = propertyDescriptor.getWriteMethod();  // metoda care ia setter-ul fiecarei clase
                    method.invoke(instance, value); // apeleaza metoda de set
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @return o lista care va contine toate inregistrarile dintr-o tabela asociate clasei de tip T din baza de date
     */
    public List<T> viewAll() {
        // se creeaza query-ul intr un string
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(object.getSimpleName());

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:findById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * functia insert va insera un obiect de tipul T
     *
     * @param obj_insert
     * @return obiectul inserat
     */
    public T insert(T obj_insert) {
        // construieste query-ul pentru insert, pentru fiecare tabel in parte
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(object.getSimpleName());
        int currentParameter = 1;
        for (Field f : obj_insert.getClass().getDeclaredFields()) {
            if (f.getName().equals("id") == false)  // se sare peste id pt ca el are autoincrement in tabela
            {
                if (currentParameter == 1) { // daca i primu element, trebuie sa deschida parantezele
                    sb.append("(" + f.getName()); // pune numele field-ului/coloanei
                } else {
                    sb.append(", " + f.getName());
                }
                currentParameter++;
            }
        }
        sb.append(") VALUES ");
        for (int i = 1; i <= obj_insert.getClass().getDeclaredFields().length - 1; i++) {
            if (i == 1) {   // daca i primu element, va trebui sa deschida parantezele de la values
                sb.append("(?");   // salvam un loc pentru a introduce valoarea ulterioara a parametrului din tabel
            } else {
                sb.append(", ?");  // salvam un loc pentru a introduce valoarea ulterioara a parametrului din tabel
            }
        }
        sb.append(")");

        currentParameter = 1;

        Connection connection = null;
        PreparedStatement statement = null;
        String query = sb.toString();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field f : obj_insert.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id"))   // sare peste id pentru ca nu s-a precizar mai sus, inserandu-se doar celelalte valori
                    continue;   // id are autoincrement si nu trebuie inserat manual
                if (f.getType().getSimpleName().equals("String")) {
                    statement.setString(currentParameter, (String) f.get(obj_insert));
                } else if (f.getType().getSimpleName().equals("int")) {
                    statement.setInt(currentParameter, (int) f.get(obj_insert));
                }
                currentParameter += 1;
            }
            statement.execute();
            return obj_insert;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda actualizeaza campurile fiecarui tabel
     *
     * @param obiect
     * @return
     */
    public T update(T obiect) {
        // construieste query-ul pentru update, pentru fiecare tabel in parte
        StringBuilder str = new StringBuilder();
        str.append("UPDATE ");
        str.append(object.getSimpleName());
        str.append(" SET ");
        int currentParameter = 1;
        for (Field f : obiect.getClass().getDeclaredFields()) {
            if (!f.getName().equals("id"))  // daca coloana nu e id
            {
                if (currentParameter != 1) {
                    str.append(", ");
                }
                str.append(f.getName());    // pune numele field-ului/coloanei
                str.append(" = ?"); // salvam un loc pentru a introduce valoarea ulterioara a parametrului din tabel
                currentParameter++;
            }
        }
        str.append(" WHERE id = ?"); //update tabela set(nume=?,varsta=?) where id=?

        Connection connection = null;
        PreparedStatement statement = null;
        String query = str.toString();

        int idForUpdate = -1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            currentParameter = 1;
            for (Field f : obiect.getClass().getDeclaredFields()) {
                f.setAccessible(true);  // pentru a accesa elementele private dintr-o clasa
                if (f.getName().equals("id")) {  // se verifica daca se ajunge la randul cu id-ul care se updateaza
                    idForUpdate = (int) f.get(obiect); // si se salveaza valoarea id-ului in query ul cu where
                    continue;
                }
                if (f.getType().getSimpleName().equals("String")) {
                    statement.setString(currentParameter, (String) f.get(obiect));
                } else if (f.getType().getSimpleName().equals("int")) {
                    statement.setInt(currentParameter, (int) f.get(obiect));
                }
                currentParameter += 1;
            }
            statement.setInt(currentParameter, idForUpdate);    //  adaug id-ul la final pentru coloana respectiva
            statement.execute();
            return obiect;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda sterge o inregistrare din tabela corespunzatoare obiectului T
     *
     * @param id ul inregistrarii care se doreste sa se stearga
     */
    public void delete(int id) {

        StringBuilder str = new StringBuilder();
        str.append("DELETE FROM ");
        str.append(object.getSimpleName());
        str.append(" WHERE id =?");

        Connection connection = null;
        PreparedStatement statement = null;
        String query = str.toString();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:deleteById " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, object.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @param list
     * @param tabel
     * @throws IllegalAccessException
     */
    public void tabelInterfata(List<T> list, JTable tabel) throws IllegalAccessException {
        if (!list.isEmpty()) {
            // calculam numarul coloanelor
            int nrCol = 0;
            for (Field field : list.get(0).getClass().getDeclaredFields()) {
                nrCol++;
            }
            // salvam numele coloanelor
            String[] coloana = new String[nrCol];
            int i = 0;
            for (Field field : list.get(0).getClass().getDeclaredFields()) {
                coloana[i] = field.getName();
                i++;
            }
            // salvam datele din campuri
            String[][] data = new String[list.size()][nrCol];
            int j = 0;
            for (i = 0; i < list.size(); i++)  // parcurg lista de obiecte(pentru linii)
            {
                j = 0;
                for (Field field : list.get(i).getClass().getDeclaredFields()) { // parcurg coloanele, deci practic vom lua asa fiecare field
                    field.setAccessible(true);
                    try {
                        data[i][j] = field.get(list.get(i)) + ""; // si se salveaza field-ul in matricea de string uri
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
            }
            DefaultTableModel model = new DefaultTableModel(data, coloana);
            tabel.setModel(model);
        }
    }
}
