package itacademy.dicegame.utilities;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;

public class DtoConverter {

    public static UserDTO userToDto(User user){
        return new UserDTO(user);
    }

    public static User userToEntity(UserDTO userDTO){
        return new User(userDTO.getName());
    }

    public static RollDTO rollToDto(Roll roll){
        return new RollDTO(roll);
    }

}
