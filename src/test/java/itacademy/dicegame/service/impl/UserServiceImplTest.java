package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.enums.Role;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;


class UserServiceImplTest {

    private UserService userService;
    @Mock
    private UserRepository mockUserRepository;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(mockUserRepository);
        user = new User("test1", "test2", "test3", Role.USER);
    }

    @Test
    void TestUpdate_ChangesBlankNameToAnonymous() {
        UserDTO dto = new UserDTO(user);
        dto.setPublicName(" ");
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.update(dto.getPublicName(), 1L);

        assertEquals("Anonymous", user.getPublicName());

    }



    @Test
    void TestFindById_ReturnsCorrectDto() {
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO dto = userService.findByIdReturnDTO(1L);

        assertEquals(dto.getPublicName(), user.getPublicName());
    }

}