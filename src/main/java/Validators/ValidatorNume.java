package Validators;
import Model.Client;

import java.util.regex.Pattern;

public class ValidatorNume implements Validator<Client> {
    private static final String NUME_PATTERN = "^[A-Z](?=.{1,45}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";

    public void validate(Client t) {
        Pattern pattern = Pattern.compile(NUME_PATTERN);
        if (!pattern.matcher(t.getNume()).matches()) {
            throw new IllegalArgumentException("The last name is not valid!");
        }
    }
}
