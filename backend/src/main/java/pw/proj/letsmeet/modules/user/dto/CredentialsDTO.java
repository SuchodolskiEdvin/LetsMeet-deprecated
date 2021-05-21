package pw.proj.letsmeet.modules.user.dto;

import lombok.Data;

@Data
public class CredentialsDTO {

	private String name;

	private String surname;

	private String email;

	private String password;

	private String confirmPassword;
}
