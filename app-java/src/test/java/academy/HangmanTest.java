package academy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @Test
    void getHangman() {
        for (Difficulty difficulty : Difficulty.values()) {
            String[] states = Hangman.getHangman(difficulty);
            assertNotNull(states);
            for (int i = 0; i < states.length; i++) {
                assertNotNull(states[i]);
                assertFalse(states[i].isEmpty());
                assertFalse(states[i].isBlank());
            }
        }
    }
}
