package pw.proj.letsmeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@SpringBootApplication
public class LetsMeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsMeetApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    // Rozwiazuje problem z firewallem
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
