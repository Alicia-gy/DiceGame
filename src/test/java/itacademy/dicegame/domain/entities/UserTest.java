package itacademy.dicegame.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void dateIsGenerated() {
        User user = new User("testUser");
        assertNotNull(user.getCreationDate());
    }

    @Test
    void calcAverageWith7and9is50() {
        User user = new User("testUser");
        new Roll(user, (byte) 3, (byte) 4);
        new Roll(user, (byte) 5, (byte) 4);
        assertEquals(user.calcAverage(), (float) (1 / 2));
    }
}