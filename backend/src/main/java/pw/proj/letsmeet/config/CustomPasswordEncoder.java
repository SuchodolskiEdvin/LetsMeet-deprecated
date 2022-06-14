package pw.proj.letsmeet.config;

import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    Cipher cipher = new Cipher();

    @SneakyThrows
    @Override
    public String encode(CharSequence plainTextPassword) {
        cipher.setPassword(plainTextPassword.toString());
        return cipher.encrypt();
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return cipher.decrypt(plainTextPassword.toString(), passwordInDatabase);
    }
}
