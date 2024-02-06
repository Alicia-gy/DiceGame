package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    //void save(UserDTO userDTO);

    void update(UserDTO userDTO, Long id);

    UserDetails findByUsername(String username);

    //User findByPublicName(String publicName);

    UserDTO findByIdReturnDTO(Long id);

    User findByIdReturnEntity(Long id);

    List<UserDTO> findAll();
    
}
