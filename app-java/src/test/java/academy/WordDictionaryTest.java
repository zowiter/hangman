package academy;

import org.junit.jupiter.api.Test;
import java.util.EnumSet;
import static org.junit.jupiter.api.Assertions.*;

class WordDictionaryTest {

    @Test
    void getRandomWord() {
        WordDictionary dictionary = WordDictionary.ANIMALS;
        String word = dictionary.getRandomWord(Difficulty.EASY);
        assertNotNull(word);
        assertFalse(word.isEmpty());
        assertTrue(dictionary.getWords(Difficulty.EASY).contains(word));
    }

    @Test
    void getRandomCategory() {
        WordDictionary category = WordDictionary.getRandomCategory();
        assertNotNull(category);
        assertTrue(EnumSet.allOf(WordDictionary.class).contains(category));
    }

    @Test
    void getCategoryByNumber() {
        WordDictionary category = WordDictionary.getCategoryByNumber(1);
        WordDictionary category_second = WordDictionary.getCategoryByNumber(1);
        assertNotNull(category);
        assertTrue(EnumSet.allOf(WordDictionary.class).contains(category));
        assertEquals(category, category_second);
    }

    @Test
    void values() {
        WordDictionary dictionary = WordDictionary.ANIMALS;
        assertTrue(EnumSet.allOf(WordDictionary.class).contains(dictionary));
    }

}
