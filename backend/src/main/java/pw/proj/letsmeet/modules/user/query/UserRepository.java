package pw.proj.letsmeet.modules.user.query;

import pw.proj.letsmeet.generic.ModelBaseRepository;
import pw.proj.letsmeet.modules.user.domain.User;

public interface UserRepository extends ModelBaseRepository<User, Long> {

	User findByEmailIgnoreCase(String email);
}
