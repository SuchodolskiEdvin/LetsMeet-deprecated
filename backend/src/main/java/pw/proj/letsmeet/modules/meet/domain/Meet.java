package pw.proj.letsmeet.modules.meet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.proj.letsmeet.generic.ModelBase;
import pw.proj.letsmeet.modules.meet.dto.MeetDTO;
import pw.proj.letsmeet.modules.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meets")
public class Meet extends ModelBase  {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Meet(MeetDTO meetDTO, User user) {
        this.name = meetDTO.getName();
        this.user = user;

    }
}

