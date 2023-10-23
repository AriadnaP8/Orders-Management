package Model;

public class Produs
{
    private int id;
    private String nume;
    private int pret = 0;
    private int stoc = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public Produs(int id, String nume, int pret, int stoc) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
    }

    public Produs() {

    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "id =" + id +
                ", nume_produs=" + nume +
                ", pret=" + pret +
                ", stoc=" + stoc +
                '}';
    }
}
