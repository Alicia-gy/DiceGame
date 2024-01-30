package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void save(UserDTO userDTO);

    public void update(UserDTO userDTO, Long id);

    public void deleteById(Long id);

    public UserDTO findById(Long id);

    public List<UserDTO> findAll();
    
}
