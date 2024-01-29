package itacademy.dicegame.domain.dtos;

import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollDTO {

    private User user;
    private byte dice1;
    private byte dice2;
    private String result;

    public RollDTO(Roll roll){
        this.user = roll.getUser();
        this.dice1 = roll.getDice1();
        this.dice2 = roll.getDice2();

        this.result = roll.isWin()? "Win" : "Lose";
    }
}
