package itacademy.dicegame.domain.dtos;

import itacademy.dicegame.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String publicName;
    private float average;

    public UserDTO(User user) {
        this.publicName = user.getPublicName();
        this.average = user.calcAverage();
    }

    @Override
    public String toString() {
        return publicName + " Average: " + average + " %";
    }



}
