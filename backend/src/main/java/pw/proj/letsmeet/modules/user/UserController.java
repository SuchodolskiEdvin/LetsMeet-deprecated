package pw.proj.letsmeet.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pw.proj.letsmeet.config.Cipher;
import pw.proj.letsmeet.config.EmailValidation;
import pw.proj.letsmeet.global.ErrorResponse;
import pw.proj.letsmeet.modules.user.domain.User;
import pw.proj.letsmeet.modules.user.dto.CredentialsDTO;
import pw.proj.letsmeet.modules.user.dto.UserProfileDTO;
import pw.proj.letsmeet.modules.user.query.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody CredentialsDTO credentialsDTO) throws NoSuchAlgorithmException {
		if (!credentialsDTO.getPassword().equals(credentialsDTO.getConfirmPassword())) {
			ErrorResponse response = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE);
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(response);
		}
		if (userRepository.existsByEmail(credentialsDTO.getEmail())) {
			ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT);
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(response);
		}
		EmailValidation emailCheck = new EmailValidation();
		if (emailCheck.check(credentialsDTO.getEmail()) == false) {
			ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN);
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body(response);
		}

		Cipher cipher = new Cipher();
		cipher.setPassword(credentialsDTO.getPassword());

		User user = new User(
				credentialsDTO,
				cipher.encrypt()
		);
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> editUser(@RequestBody UserProfileDTO userProfileDTO) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setEmail(userProfileDTO.getEmail());
		user.setName(userProfileDTO.getName());
		user.setSurname(userProfileDTO.getSurname());

		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/edit")
	public ResponseEntity<?> getUserData() {

		User userFromDB = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserProfileDTO userDTO = new UserProfileDTO();

		userDTO.setEmail(userFromDB.getEmail());
		userDTO.setName(userFromDB.getName());
		userDTO.setSurname(userFromDB.getSurname());

		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers() {

		List<User> usersDB = userRepository.findAll();
		List<UserProfileDTO> users = new ArrayList<>();

		for (User item : usersDB) {
			UserProfileDTO user = new UserProfileDTO();

			user.setEmail(item.getEmail());
			user.setName(item.getName());
			user.setSurname(item.getSurname());

			users.add(user);
		}

		return ResponseEntity.ok(users);
	}
}
