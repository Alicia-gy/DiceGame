package itacademy.dicegame.domain.entities;

import itacademy.dicegame.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "date", updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "public_name", nullable = false)
    private String publicName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Roll> rolls;

    @Enumerated(EnumType.STRING)
    private Role role;


    public User(String username, String password, String publicName) {
        this.username = username;
        this.password = password;
        this.publicName = publicName;
        this.creationDate = LocalDateTime.now();
        this.rolls = new ArrayList<>();
    }

    public float calcAverage() {
        int wins = 0;

        if (rolls.isEmpty()) {
            return 0;
        }

        for (Roll roll : rolls) {
            if (roll.isWin()) wins++;
        }

        return (float) wins / rolls.size() * 100;
    }

    //Spring Security methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
