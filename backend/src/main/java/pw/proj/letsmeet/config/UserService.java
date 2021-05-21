package pw.proj.letsmeet.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pw.proj.letsmeet.modules.user.query.UserRepository;

@Service
@NoArgsConstructor
@AllArgsConstructor
class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByEmailIgnoreCase(s);
	}
}
