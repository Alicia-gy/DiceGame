package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
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

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByName(username);
                if (user == null) {
                    throw new EntityNotFoundException();
                }
                return user;
            }
        };
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        checkNameConditions(userDTO, userRepository);

        User user = DtoConverter.userToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO, Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new EntityNotFoundException();
        }

        checkNameConditions(userDTO, userRepository);

        user.get().setName(userDTO.getName());
        userRepository.save(user.get());
    }

    @Override
    public User findByName(String name) {
        User user = userRepository.findByName(name);
        if(user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByIdReturnDTO(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return DtoConverter.userToDto(user.get());
    }

    @Override
    @Transactional(readOnly = true)
    public User findByIdReturnEntity(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return user.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream().map(DtoConverter::userToDto).collect(Collectors.toList());
        return dtos;
    }

    private static void checkNameConditions(UserDTO userDTO, UserRepository userRepository) {
        if(userDTO.getName().isEmpty() || userDTO.getName().isBlank()){
            userDTO.setName("Anonymous");
        } else if(Optional.ofNullable(
                userRepository.findByName(userDTO.getName())).isPresent()) {
            throw new IllegalArgumentException("Name is already taken");
        }
    }

}