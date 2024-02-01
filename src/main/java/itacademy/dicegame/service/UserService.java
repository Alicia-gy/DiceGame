package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void save(UserDTO userDTO);

    public void update(UserDTO userDTO, Long id);

    public void deleteById(Long id);

    public UserDTO findByIdReturnDTO(Long id);

    public User findByIdReturnEntity(Long id);

    public List<UserDTO> findAll();
    
}
