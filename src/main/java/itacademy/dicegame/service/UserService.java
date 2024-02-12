package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.dtos.request.UpdateRequest;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface UserService {

    String update(UpdateRequest request, Long id);

    UserDTO findByIdReturnDTO(Long id);

    User findByIdReturnEntity(Long id);

    List<UserDTO> findAll();
    
}
