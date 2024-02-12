package itacademy.dicegame.domain.entities;

import itacademy.dicegame.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotEmpty(message = "Username cannot be empty or null")
    private String username;

    @NotNull
    private String password;

    private LocalDateTime creationDate;

    private String publicName;

    @DBRef
    private List<Roll> rolls;

    private Role role;


    public User(String username, String password, String publicName, Role role) {
        this.username = username;
        this.password = password;
        this.publicName = publicName;
        this.creationDate = LocalDateTime.now();
        this.rolls = new ArrayList<>();
        this.role = role;
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
