package pw.proj.letsmeet.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.proj.letsmeet.modules.user.domain.User;
import pw.proj.letsmeet.modules.user.dto.CredentialsDTO;
import pw.proj.letsmeet.modules.user.query.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody CredentialsDTO credentialsDTO) {
		if (!credentialsDTO.getPassword().equals(credentialsDTO.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		if (userRepository.existsByEmail(credentialsDTO.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		User user = new User(
				credentialsDTO,
				passwordEncoder.encode(credentialsDTO.getPassword())
		);
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
