package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.dtos.request.UpdateRequest;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface UserService {

    String update(UpdateRequest request, String id);

    UserDTO findByIdReturnDTO(String id);

    User findByIdReturnEntity(String id);

    List<UserDTO> findAll();
    
}
