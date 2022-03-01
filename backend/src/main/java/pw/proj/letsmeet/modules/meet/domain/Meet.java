package pw.proj.letsmeet.modules.meet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.proj.letsmeet.generic.ModelBase;
import pw.proj.letsmeet.modules.meet.dto.MeetDTO;
import pw.proj.letsmeet.modules.user.domain.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_meets",
            joinColumns = { @JoinColumn(name = "meet_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users = new HashSet<>();


    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    public Meet(MeetDTO meetDTO, User user) {
        this.name = meetDTO.getName();
        this.user = user;
        this.date = meetDTO.getDate();
        this.time = meetDTO.getTime();
    }
}

