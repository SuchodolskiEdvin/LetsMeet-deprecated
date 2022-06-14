package pw.proj.letsmeet.modules.meet.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MeetListItemDTO {

    private Long id;

    private String name;

    private String creatorName;

    private String creatorSurname;

    private String creationDate;

    private String modificationDate;

    private String date;

    private String time;

}
