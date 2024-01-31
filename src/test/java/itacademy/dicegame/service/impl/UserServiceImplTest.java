package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(mockUserRepository);
    }

    @Test
    void TestSave_ChangesBlankNameToAnonymous() {
        UserDTO dto = new UserDTO(mock(User.class));
        dto.setName(" ");

        userService.save(dto);

        assertEquals("Anonymous", dto.getName());

    }


    @Test
    void TestFindById_ReturnsCorrectDto() {
        User user = new User("test");
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO dto = userService.findById(1L);

        assertEquals(dto.getName(), user.getName());
    }

}