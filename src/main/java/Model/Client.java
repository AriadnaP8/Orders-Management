package Model;

public class Client
{
    private int id = 0;
    private int varsta = 0;
    private String nume;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client(int id, int varsta, String nume, String email) {
        this.id = id;
        this.varsta = varsta;
        this.nume = nume;
        this.email = email;
    }
    public Client(){

    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Client{" +
                "id=" + id +
                ", varsta=" + varsta +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
