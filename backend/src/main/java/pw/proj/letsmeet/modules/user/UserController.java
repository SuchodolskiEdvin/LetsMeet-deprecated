package pw.proj.letsmeet.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pw.proj.letsmeet.global.ErrorResponse;
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
			ErrorResponse response = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE);
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(response);
		}
		if (userRepository.existsByEmail(credentialsDTO.getEmail())) {
			ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT);
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(response);
		}
		User user = new User(
				credentialsDTO,
				passwordEncoder.encode(credentialsDTO.getPassword())
		);
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
