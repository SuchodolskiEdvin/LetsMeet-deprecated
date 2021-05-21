package pw.proj.letsmeet.modules.user.domain;

import pw.proj.letsmeet.generic.ModelBase;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends ModelBase implements UserDetails {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean active;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean accepted;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean confirmed;

    @Transient
    public String getFullName() {
        return this.name + " " + this.surname;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
