package Model;

public class Comanda {
    private int id = 0;
    private int id_client = 0;
    private int id_produs = 0;
    private int cantitate = 0;
    private int pret = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public Comanda(int id, int id_client, int id_produs, int cantitate, int pret) {
        this.id = id;
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Comanda() {
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", id_produs=" + id_produs +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }


}
