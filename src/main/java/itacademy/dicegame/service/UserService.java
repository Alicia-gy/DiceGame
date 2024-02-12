package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface UserService {

    void update(String publicName, String id);

    UserDTO findByIdReturnDTO(String id);

    User findByIdReturnEntity(String id);

    List<UserDTO> findAll();
    
}
