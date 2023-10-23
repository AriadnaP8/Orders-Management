package Business_Logic;

import Data_Access.BillDAO;
import Data_Access.ComandaDAO;
import Model.Bill;
import Model.Comanda;
import Model.Produs;
import Validators.Validator;
import Validators.ValidatorCantitate;

import java.util.List;
import java.util.NoSuchElementException;

public class BillBLL {

    private BillDAO billDAO = new BillDAO();
    private Validator validator;

    public BillBLL(BillDAO billDAO, Validator validator) {
        this.billDAO = billDAO;
        this.validator = validator;
    }

    public BillBLL(){

    }

    public void billInsert(int id, int  id_comanda, String nume_client, String email, int pret) throws Exception {
        Bill factura = new Bill(id, id_comanda, nume_client, email, pret);
        billDAO.insert(factura);

    }

}
