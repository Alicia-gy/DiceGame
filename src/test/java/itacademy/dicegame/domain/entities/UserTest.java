package itacademy.dicegame.domain.entities;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setup() {
        user = new User("test1", "test2", "test3",Role.USER);
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