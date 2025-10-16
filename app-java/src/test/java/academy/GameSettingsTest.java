package academy;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GameSettingsTest {

    @Test
    void getSecretWord() {
        WordDictionary category = WordDictionary.ANIMALS;
        Difficulty difficulty = Difficulty.EASY;
        GameSettings settings = new GameSettings(difficulty, category);
        String secretWord = settings.getSecretWord();
        assertNotNull(secretWord);
        assertFalse(secretWord.isEmpty());
        assertFalse(secretWord.isBlank());
        List<String> categoryWords = category.getWords(difficulty);
        assertTrue(categoryWords.contains(secretWord));
    }

    @Test
    void getHangmanStates() {
        Difficulty[] difficulties = Difficulty.values();
        for (Difficulty difficulty : difficulties) {
            GameSettings settings = new GameSettings(difficulty, WordDictionary.ANIMALS);
            String[] hangmanStates = settings.getHangmanStates();
            assertNotNull(hangmanStates);
            assertTrue(hangmanStates.length > 0);
            for (int i = 0; i < hangmanStates.length; i++) {
                assertNotNull(hangmanStates[i]);
                assertFalse(hangmanStates[i].isEmpty());
                assertFalse(hangmanStates[i].isBlank());
            }
        }
    }
}
