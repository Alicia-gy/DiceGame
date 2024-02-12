package itacademy.dicegame.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document("rolls")
public class Roll {

    @Id
    private String id;

    private byte dice1;

    private byte dice2;

    private boolean win;

    @DBRef
    private User user;

    @Transient
    private final byte winValue = 7;

    public Roll(User user, byte dice1, byte dice2) {
        this.user = user;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.win = (this.dice1 + this.dice2) == winValue;
        user.getRolls().add(this);
    }

}
