package itacademy.dicegame.domain.entities;

import itacademy.dicegame.utilities.DiceRoller;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RollTest {


    @Test
    void TestD6Rolls_Returns1to6Values() {
        byte roll = DiceRoller.d6Roll();
        assertTrue(roll >= 1 && roll <= 6);
    }

    @Test
    void isWinValue7() {
        User mockUser = Mockito.mock(User.class);
        Roll roll = new Roll(mockUser, (byte) 3, (byte) 4);
        assertTrue(roll.isWin());
    }

    @Test
    void TestRollIsCreatedOk() {
        User user = new User("TestUser");
        Roll roll = new Roll(user, DiceRoller.d6Roll(), DiceRoller.d6Roll());

        assertFalse(user.getRolls().isEmpty());
        assertEquals(roll.getUser(), user);
    }
}