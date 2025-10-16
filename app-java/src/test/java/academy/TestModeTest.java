package academy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestModeTest {

    @Test
    void checkGuessedWord() {
        TestMode test1 = new TestMode("кот", "рот");
        assertFalse(test1.checkGuessedWord());
        TestMode test2 = new TestMode("кот", "кот");
        assertTrue(test2.checkGuessedWord());
    }

    @Test
    void checkDifferentLengths() {
        TestMode testException = new TestMode("кот", "коты");
        Exception exception = assertThrows(WrongLengthException.class, () -> {
            testException.getResult();
        });
        assertEquals("Lengths do not match", exception.getMessage());
    }

    @Test
    void checkIfWordGuessed() throws Exception {
        TestMode testMode = new TestMode("слово", "слово");
        assertDoesNotThrow(() -> testMode.getResult());
        assertEquals("слово;POS", testMode.getResult());
    }

    @Test
    void checkIfWordNotGuessed() throws Exception {
        TestMode testMode = new TestMode("волокно", "толокно");
        assertDoesNotThrow(() -> testMode.getResult());
        assertEquals("*олокно;NEG", testMode.getResult());
    }
}
