package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
