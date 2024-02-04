package itacademy.dicegame.domain.dtos;

import itacademy.dicegame.domain.entities.Roll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollDTO {

    private String userName;
    private byte dice1;
    private byte dice2;
    private String result;

    public RollDTO(Roll roll){
        this.userName = roll.getUser().getName();
        this.dice1 = roll.getDice1();
        this.dice2 = roll.getDice2();

        this.result = roll.isWin()? "Win" : "Lose";
    }

    @Override
    public String toString() {
        return "Roll result: " + dice1 + " + " + dice2 + " - " + result;
    }

}
