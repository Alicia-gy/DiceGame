package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface UserService {

    void update(String publicName, Long id);

    UserDTO findByIdReturnDTO(Long id);

    User findByIdReturnEntity(Long id);

    List<UserDTO> findAll();
    
}
