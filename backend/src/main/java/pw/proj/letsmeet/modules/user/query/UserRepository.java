package pw.proj.letsmeet.modules.user.query;

import pw.proj.letsmeet.generic.ModelBaseRepository;
import pw.proj.letsmeet.modules.user.domain.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends ModelBaseRepository<User, Long> {

    User findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);

    Set<User> findAllByEmailIn(List<String> participants);
}
