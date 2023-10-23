package Business_Logic;

import Data_Access.ProductDAO;
import Model.Produs;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * cauta produsul dupa id, iar in caz ca nu gaseste se afiseaza mesaj de eroare
     * @param id
     * @return produsul
     */
    public Produs findProductById(int id) {
        Produs pr = productDAO.findById(id);
        if (pr == null) {
            throw new NoSuchElementException("Produsul cu id =" + id + " nu se gaseste!");
        }
        return pr;
    }

    /**
     * insereaza fiecare produs
     * @param pd
     * nu returneaza nimic
     */
    public void insertProduct(Produs pd) {
        productDAO.insert(pd);
    }

    /**
     * adauga toate produsele intr-o lista
     * nu are parametrii
     * @return lista de produse
     */
    public List<Produs> findAllProducts() {
        List<Produs> list = productDAO.viewAll();
        if (list == null) {
            throw new NoSuchElementException("NU exista produse!!");
        }
        return list;
    }

    /**
     * verifica daca produsul a putut fi actualizat sau nu
     * @param pd
     * @return
     * @throws Exception
     */
    public boolean updateProduct(Produs pd) throws Exception {
        if (productDAO.update(pd) == null) {
            throw new Exception("Produsul nu a putut fi actualizat!");
        }
        return true;
    }

    /**
     * sterge produsul in functie de id
     * @param id
     * nu returneaza nimic
     */
    public void deleteProduct(int id) {
        Produs p = productDAO.findById(id);
        productDAO.delete(p.getId());
    }

}
