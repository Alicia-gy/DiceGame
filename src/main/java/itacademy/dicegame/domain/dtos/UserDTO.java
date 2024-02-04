package itacademy.dicegame.domain.dtos;

import itacademy.dicegame.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private float average;

    public UserDTO(User user) {
        this.name = user.getName();
        this.average = user.calcAverage();
    }

    @Override
    public String toString() {
        return name + " Average: " + average + " %";
    }



}
