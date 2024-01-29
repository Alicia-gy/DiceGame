package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserDTO save(UserDTO userDTO);

    public void deleteById(int id);

    public Optional<UserDTO> findById(int id);

    public List<UserDTO> findAll();
    
}
