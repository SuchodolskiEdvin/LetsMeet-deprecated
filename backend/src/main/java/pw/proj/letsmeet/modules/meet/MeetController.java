package pw.proj.letsmeet.modules.meet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pw.proj.letsmeet.modules.meet.domain.Meet;
import pw.proj.letsmeet.modules.meet.dto.MeetDTO;
import pw.proj.letsmeet.modules.meet.query.MeetRepository;
import pw.proj.letsmeet.modules.user.domain.User;

import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetController {

    @Autowired
    MeetRepository repository;

    @PostMapping
    public ResponseEntity<?> createMeet (@RequestBody MeetDTO MeetDTO) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Meet meet = new Meet(MeetDTO, user);

        repository.save(meet);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMeets () {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Meet> meets = repository.findMeetsByUser(user);
        return ResponseEntity.ok(meets);
    }

}
