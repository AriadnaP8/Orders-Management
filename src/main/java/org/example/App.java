package org.example;

import Business_Logic.ClientBLL;
import Business_Logic.ComandaBLL;
import Business_Logic.ProductBLL;
import Model.Client;
import Model.Comanda;
import Model.Produs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
       // Client client = new Client(3,21, "Iudt Sorina", "sori21iudt@gmail.com");
        //Produs produs = new Produs(1,"caiet", 14, 30);
        ClientBLL bll = new ClientBLL();
        //Comanda comanda = new Comanda(6, 4, 1, 5, 300);
        ProductBLL prod = new ProductBLL();
       // prod.deleteProduct(1);
        ComandaBLL bll2 = new ComandaBLL();
       // ProductBLL bll1 = new ProductBLL();
       // bll.insertClient(client);
        //bll2.placeOrder(4, 1, 3);
        Client client2 = new Client(2, 20, "Marian Iovescu", "iovemari34an@gmail.com");
        bll.updateClient(client2);
       // bll1.insertProduct(produs);
       // System.out.println( "Hello World!" );
    }
}
