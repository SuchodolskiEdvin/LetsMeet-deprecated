package pw.proj.letsmeet.modules.meet.dto;

import lombok.Data;
import pw.proj.letsmeet.modules.user.domain.User;

import java.util.List;

@Data
public class MeetDTO {

    private Long id;

    private String name;

    private String date;

    private String time;

    private List<String> participants;
}
