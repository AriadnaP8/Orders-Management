package Model;

public record Bill(int id, int  id_comanda, String nume_client, String email, int pret) {
    // este o clasa imutabila(odata ce am initializat un obiect nu mai putem modifica valorile variabilelor)
}
