package itacademy.dicegame.service;

import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.service.impl.RollServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RollServiceTest {

    private RollService rollService;
    @Mock
    private RollRepository mockRollRepository;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        rollService = new RollServiceImpl(mockRollRepository);
        user = Mockito.mock(User.class);
    }



    @RepeatedTest(40)
    void TestD6Rolls_Returns1to6Values() {
        byte roll = rollService.d6Roll();
        assertTrue(roll >= 1 && roll <= 6);
    }

    @Test
    void isWinValue7() {
        Roll roll = new Roll(user, (byte) 3, (byte) 4);
        assertTrue(roll.isWin());
    }

    @Test
    void TestRollIsCreatedOk() {
        Roll roll = new Roll(user, rollService.d6Roll(), rollService.d6Roll());

        assertEquals(roll.getUser(), user);
    }
}
