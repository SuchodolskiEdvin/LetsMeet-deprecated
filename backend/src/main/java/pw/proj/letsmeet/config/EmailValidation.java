package pw.proj.letsmeet.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    public boolean check(String email) {
        boolean check = false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9.]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if (mat.matches()) {
            check = true;
        }

        return check;
    }
}
