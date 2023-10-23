package Business_Logic;

import Data_Access.ClientDAO;
import Model.Client;
import Validators.ClientAgeValidator;
import Validators.EmailValidator;
import Validators.Validator;
import Validators.ValidatorNume;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private Validator validator;
    private ClientDAO clientDAO;
    // constructorul clasei
    public ClientBLL() {
        validator = new EmailValidator();
        clientDAO = new ClientDAO();
    }

    /**
     * metoda cauta cate un client dupa un id dat, iar in cazul in care nu se gaseste se afiseaza un mesaj de eroare
     * @param id
     * @return un Client
     */
    public Client cautaClientDupaId(int id)
    {
        Client client1 = clientDAO.findById(id);
        if(client1 == null){
            throw new NoSuchElementException("Clientul cu id-ul = " + id +" nu se gaseste!!");
        }
        return client1;
    }

    /**
     * metoda insereaza un client si valideaza datele introduse
     * @param cl
     * nu returneaza nimic
     */
    public void insertClient(Client cl){
        validator.validate(cl);
        clientDAO.insert(cl);
    }

    /**
     * metoda adauga toti clientii intr-o lista pe care o returneaza, iar daca nu se gaseste niciun client se va afisa un mesaj de eroare
     * nu are parametrii
     * @return o lista de clienti
     */
    public List<Client> findAllClients(){
        List<Client> list = clientDAO.viewAll();
        if(list == null){
            throw new NoSuchElementException("NU EXISTA CLIENTI!!!");
        }
        return list;
    }
    /**
     * metoda updateaza fiecare client in parte si il si valideaza
     * @param cl de tipul Client
     * nu returneaza nimic
     */
    public void updateClient(Client cl) {
        validator.validate(cl);
        clientDAO.update(cl);
        if(cl == null)
        {
            throw new NoSuchElementException("NU EXISTA CLIENTI!!!");
        }
    }

    /**
     * metoda sterge un client dar in functie de id
     * @param id
     * nu returneaza nimic
     */
    public void stergeClient(int id)
    {
        Client client1 = clientDAO.findById(id);
        clientDAO.delete(client1.getId());
    }
}
