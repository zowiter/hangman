package academy;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import static org.junit.jupiter.api.Assertions.*;

class DifficultyTest {

    @Test
    void getRandomDifficulty() {
        Difficulty difficulty = Difficulty.getRandomDifficulty();
        assertNotNull(difficulty);
        assertTrue(EnumSet.allOf(Difficulty.class).contains(difficulty));
    }

    @Test
    void getDifficultyByNumber() {
        Difficulty difficulty = Difficulty.getDifficultyByNumber(1);
        assertNotNull(difficulty);
        assertTrue(EnumSet.allOf(Difficulty.class).contains(difficulty));
    }
}
