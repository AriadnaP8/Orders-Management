package Validators;

import Model.Client;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 60;

    public void validate(Client t) {

        if (t.getVarsta() < MIN_AGE || t.getVarsta() > MAX_AGE) {
            throw new IllegalArgumentException("Limita de varsta pentru client nu este respectata!");
        }

    }

}
