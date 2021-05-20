package pw.proj.letsmeet.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ApplicationProperties {

    @Value("${frontend.address}")
    private String frontendAddress;
}
