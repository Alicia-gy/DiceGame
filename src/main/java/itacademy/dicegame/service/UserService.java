package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsService userDetailsService();

    void save(UserDTO userDTO);

    void update(UserDTO userDTO, Long id);

    UserDetails findByName(String name);

    UserDTO findByIdReturnDTO(Long id);

    User findByIdReturnEntity(Long id);

    List<UserDTO> findAll();
    
}
