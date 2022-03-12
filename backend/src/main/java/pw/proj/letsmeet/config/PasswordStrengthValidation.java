package pw.proj.letsmeet.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthValidation {
    public boolean checkPasswordStrength(String password) {
        int count = 0;

        if( 12 <= password.length())
        {
            if( password.matches(".*\\d.*") )
                count ++;
            if( password.matches(".*[a-z].*") )
                count ++;
            if( password.matches(".*[A-Z].*") )
                count ++;
            if( password.matches(".*[!@#&()–[{}]:;',?/*~$^+=<>].*") )
            count ++;
        }

        return count == 4;
    }
}
