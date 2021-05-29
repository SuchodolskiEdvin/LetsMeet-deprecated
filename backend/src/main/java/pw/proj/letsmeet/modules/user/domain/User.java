package pw.proj.letsmeet.modules.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pw.proj.letsmeet.generic.ModelBase;
import pw.proj.letsmeet.modules.meet.domain.Meet;
import pw.proj.letsmeet.modules.user.dto.CredentialsDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_users")
public class User extends ModelBase implements UserDetails {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(CredentialsDTO credentialsDTO, String encodedPassword) {
        this.name = credentialsDTO.getName();
        this.surname = credentialsDTO.getSurname();
        this.email = credentialsDTO.getEmail();
        this.password = encodedPassword;
    }
}
