package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.dtos.request.UpdateRequest;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.enums.Role;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository mockUserRepository;

    private User user;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(mockUserRepository);
        user = new User("test1", "test2", "test3", Role.USER);
        user2 = new User("test4", "test5", "test6", Role.USER);
    }

    @Test
    void TestFindAll_ReturnsList() {
        when(mockUserRepository.findAll()).thenReturn(Arrays.asList(user, user2));
        List<UserDTO> list= userService.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void TestUpdate_ChangesBlankNameToAnonymous() {
        UpdateRequest request = new UpdateRequest(" ");
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.update(request, 1L);

        assertEquals("Anonymous", user.getPublicName());

    }



    @Test
    void TestFindById_ReturnsCorrectDto() {
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO dto = userService.findByIdReturnDTO(1L);

        assertEquals(dto.getPublicName(), user.getPublicName());
    }

    @Test
    void dateIsGenerated() {
        assertNotNull(user.getCreationDate());
    }

    @Test
    void calcAverageWith7and9is50() {
        new Roll(user, (byte) 3, (byte) 4);
        new Roll(user, (byte) 5, (byte) 4);
        assertEquals(user.calcAverage(), (float) 50);
    }

    @Test
    void userDTOIsCreatedCorrectly() {
        new Roll(user, (byte) 3, (byte) 4);
        new Roll(user, (byte) 5, (byte) 4);

        UserDTO dto = new UserDTO(user);

        assertEquals(user.getPublicName(), dto.getPublicName());
        assertEquals(user.calcAverage(), dto.getAverage());
    }

}