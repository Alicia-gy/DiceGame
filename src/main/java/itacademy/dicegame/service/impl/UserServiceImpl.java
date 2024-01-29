package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        User user = toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return toDto(user.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream().map(UserServiceImpl::toDto).collect(Collectors.toList());
        return dtos;
    }

    static UserDTO toDto(User user){
        return new UserDTO(user);
    }

    static User toEntity(UserDTO userDTO){
        return new User(userDTO.getName());
    }

}