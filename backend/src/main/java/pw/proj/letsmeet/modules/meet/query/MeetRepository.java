package pw.proj.letsmeet.modules.meet.query;

import pw.proj.letsmeet.generic.ModelBaseRepository;
import pw.proj.letsmeet.modules.meet.domain.Meet;
import pw.proj.letsmeet.modules.user.domain.User;

import java.util.List;

public interface MeetRepository extends ModelBaseRepository<Meet, Long> {

    List<Meet> findMeetsByUser(User user);

    List<Meet> findMeetsByUserOrUsersContains(User user1, User user2);

}