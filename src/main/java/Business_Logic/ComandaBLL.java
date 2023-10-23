package Business_Logic;

import Data_Access.ComandaDAO;
import Model.Comanda;
import Model.Produs;
import Validators.Validator;
import Validators.ValidatorCantitate;

import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDAO orderDAO;
    private Validator validator;
    // constructorul clasei
    public ComandaBLL(){
        orderDAO = new ComandaDAO();
        validator = new ValidatorCantitate();
    }

    /**
     * aceasta metoda plaseaza o comanda in sistem, actualizand stocul produsului, stergand in cazul in care stocul e 0
     * @param idClient
     * @param idProdus
     * @param cantitate
     * @throws Exception
     */
    public void placeOrder(int idClient, int idProdus, int cantitate) throws Exception {
        ProductBLL productBLL = new ProductBLL();
        Produs produs = productBLL.findProductById(idProdus);  // selectam produsul pe care sa l punem in comanda
        // creez un obiect de tip Comanda si se calculeaza pretul in functie de cantitate
        Comanda comand = new Comanda(0, idClient, idProdus, cantitate, produs.getPret() * cantitate);
        validator.validate(comand);
        orderDAO.insert(comand);
        produs.setStoc(produs.getStoc() - cantitate); // ca sa i scadem cantitatea produsului
        productBLL.updateProduct(produs); // updatam produsul
        produs = productBLL.findProductById(idProdus);
        if(produs.getStoc() == 0){
            productBLL.deleteProduct(produs.getId());
        }
    }

    /**
     * adauga toate comenziile intr-o lista, iar daca nu se gaseste nicio comanda se afiseaza un mesaj de eroare
     * nu are parametrii
     * @return o lista de comenzi
     */
    public List<Comanda> findAllOrders(){
        List<Comanda> list = orderDAO.viewAll();
        if(list == null){
            throw new NoSuchElementException("NU SUNT COMENZI!!!");
        }
        return list;
    }

}
