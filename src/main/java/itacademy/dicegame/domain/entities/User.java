package itacademy.dicegame.domain.entities;

import itacademy.dicegame.domain.entities.Roll;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Roll> rolls;


    public User(String name){
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.rolls = new ArrayList<Roll>();
    }

    public float calcAverage() {
        int wins = 0;

        if(rolls.isEmpty()) {
            return 0;
        }

        for(Roll roll : rolls) {
            if(roll.isWin()) wins++;
        }

        return (float) (wins / rolls.size());
    }
}
