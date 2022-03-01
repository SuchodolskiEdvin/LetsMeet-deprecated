package pw.proj.letsmeet.modules.meet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pw.proj.letsmeet.modules.meet.domain.Meet;
import pw.proj.letsmeet.modules.meet.dto.MeetDTO;
import pw.proj.letsmeet.modules.meet.dto.MeetListItemDTO;
import pw.proj.letsmeet.modules.meet.dto.ParticipantsDTO;
import pw.proj.letsmeet.modules.meet.query.MeetRepository;
import pw.proj.letsmeet.modules.user.domain.User;
import pw.proj.letsmeet.modules.user.query.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/meet")
public class MeetController {

    @Autowired
    MeetRepository repository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createMeet (@RequestBody MeetDTO meetDTO) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<User> participants = userRepository.findAllByEmailIn(meetDTO.getParticipants());

        Meet meet = new Meet(meetDTO, user);
        Meet savedMeet = repository.save(meet);
        savedMeet.getUsers().addAll(participants);

        repository.save(savedMeet);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editMeet (@RequestBody MeetDTO meetDTO) {

        Optional<Meet> meetDB = repository.findById(meetDTO.getId());
        if (meetDB.isPresent()) {
            Meet meet = meetDB.get();
            meet.setName(meetDTO.getName());
            meet.setDate(meetDTO.getDate());
            meet.setTime(meetDTO.getTime());

            meet.getUsers().clear();
            Set<User> users = userRepository.findAllByEmailIn(meetDTO.getParticipants());
            meet.getUsers().addAll(users);

            repository.save(meet);
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<?> getMeets () {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Meet> meetsDB = repository.findMeetsByUserOrUsersContains(user, user);

        List<MeetListItemDTO> meets = new ArrayList<>();


        for (Meet item : meetsDB) {
            MeetListItemDTO meet = new MeetListItemDTO();

            meet.setId(item.getId());
            meet.setName(item.getName());
            meet.setCreatorName(item.getUser().getName());
            meet.setCreatorSurname(item.getUser().getSurname());
            meet.setCreationDate(item.getCreationDate().toString().substring(0, 19));
            meet.setModificationDate(item.getModificationDate().toString().substring(0, 19));
            meet.setDate(item.getDate());
            meet.setTime(item.getTime());

            meets.add(meet);
        }

        return ResponseEntity.ok(meets);
    }

    @GetMapping("/participants")
    public ResponseEntity<?>  getParticipants (@RequestParam Long id) {

        Set<ParticipantsDTO> participants = new HashSet<>();
        Optional<Meet> meetDB = repository.findById(id);
        if (meetDB.isPresent()) {
            Meet meet = meetDB.get();
            Set<User> users = meet.getUsers();


            for (User item : users) {
                ParticipantsDTO participant = new ParticipantsDTO();

                participant.setName(item.getName());
                participant.setSurname(item.getSurname());
                participant.setMail(item.getEmail());

                participants.add(participant);
            }
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(participants);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMeet(@RequestParam Long id) {

        Optional<Meet> meetDB = repository.findById(id);

        if (meetDB.isPresent()) {
            Meet meet = meetDB.get();
            meet.getUsers().clear();
            repository.delete(meet);
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
