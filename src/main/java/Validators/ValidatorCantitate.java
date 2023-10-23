package Validators;
import Model.Comanda;


public class ValidatorCantitate implements Validator<Comanda> {

    public void validate(Comanda t) {
        if (t.getCantitate() < 1)   // cantitatea introdusa nu poate fi mai mica decat 1
        {
            throw new IllegalArgumentException("Cantitatea introdusa nu este valida!");
        }
    }
}
