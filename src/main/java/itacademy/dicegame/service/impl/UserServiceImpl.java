package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.exceptions.UserNotFoundException;
import itacademy.dicegame.exceptions.UsernameAlreadyExistsException;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.UserService;
import itacademy.dicegame.utilities.DtoConverter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    /*@Override
    @Transactional
    public void save(UserDTO userDTO) {
        checkNameConditions(userDTO, userRepository);

        User user = DtoConverter.userToEntity(userDTO);
        userRepository.save(user);
    }*/

    @Override
    @Transactional
    public void update(UserDTO userDTO, Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new EntityNotFoundException();
        }

        checkNameConditions(userDTO, userRepository);

        user.get().setPublicName(userDTO.getPublicName());
        userRepository.save(user.get());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByIdReturnDTO(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: invalid id")));
        return DtoConverter.userToDto(user.get());
    }

    @Override
    @Transactional(readOnly = true)
    public User findByIdReturnEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: invalid id"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream().map(DtoConverter::userToDto).collect(Collectors.toList());
        return dtos;
    }

    private static void checkNameConditions(UserDTO userDTO, UserRepository userRepository) {
        if(userDTO.getPublicName().isEmpty() || userDTO.getPublicName().isBlank()){
            userDTO.setPublicName("Anonymous");
        } else if(Optional.ofNullable(
                userRepository.findByPublicName(userDTO.getPublicName())).isPresent()) {
            throw new UsernameAlreadyExistsException("Public Name is already taken");
        }
    }

}