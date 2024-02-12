package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.dtos.request.UpdateRequest;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.exceptions.UserNotFoundException;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.UserService;
import itacademy.dicegame.utilities.DtoConverter;
import itacademy.dicegame.utilities.NameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public String update(UpdateRequest request, String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPublicName(
                NameValidator.checkPublicName(request.getPublicName(), userRepository));

        userRepository.save(user);
        return user.getPublicName();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByIdReturnDTO(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: invalid id"));
        return DtoConverter.userToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByIdReturnEntity(String id) {
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


}