package itacademy.dicegame.domain.entities;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class RollTest {

    //TODO fix errors



    @Test
    void TestD6Rolls_Returns1to6Values() {
        byte roll = Roll.d6Roll();
        assertTrue(roll >= 1 && roll <= 6);
    }

    @Test
    void isWinValue7() {
        User mockUser = Mockito.mock(User.class);
        Roll roll = new Roll(mockUser, (byte) 3, (byte) 4);
        assertTrue(roll.isWin());
    }
}