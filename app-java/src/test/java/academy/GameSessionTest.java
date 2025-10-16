package academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameSessionTest {

    @BeforeEach
    void resetStaticFields() throws Exception {
        Field mistakesField = GameSession.class.getDeclaredField("mistakesNumber");
        mistakesField.setAccessible(true);
        mistakesField.set(null, 0);
        Field wordField = GameSession.class.getDeclaredField("word");
        wordField.setAccessible(true);
        wordField.set(null, null);
        Field maxMistakesField = GameSession.class.getDeclaredField("maxMistakes");
        maxMistakesField.setAccessible(true);
        maxMistakesField.set(null, 0);
    }

    @Test
    void initializeGame() {
        String[] stages = {"Stage1", "Stage2"};
        GameSession session = new GameSession("тест", stages);
        assertFalse(session.isGameOver());
        assertEquals("____", session.getWord());
        assertEquals(Integer.valueOf(0), session.getMistakesNumber());
        assertNotNull(session.getHangmanStages());
        assertEquals(Integer.valueOf(0), session.getRemaining());
    }

    @Test
    void testWin() {
        String[] stages = {"Stage0", "Stage1", "Stage3", "Stage4"};
        GameSession session = new GameSession("тест", stages);
        session.guessLetter('т');
        assertFalse(session.isGameOver());
        assertEquals("т__т", session.getWord());
        assertEquals(Integer.valueOf(0), session.getMistakesNumber());
        assertEquals(Integer.valueOf(3), session.getRemaining());

        session.guessLetter('п');
        assertEquals(Integer.valueOf(1), session.getMistakesNumber());
        assertFalse(session.isGameOver());
        assertEquals(Integer.valueOf(2), session.getRemaining());

        session.guessLetter('е');
        assertEquals("те_т", session.getWord());
        assertEquals(Integer.valueOf(1), session.getMistakesNumber());
        assertFalse(session.isGameOver());
        assertEquals(Integer.valueOf(2), session.getRemaining());

        session.guessLetter('с');
        assertEquals("тест", session.getWord());
        assertEquals(Integer.valueOf(1), session.getMistakesNumber());
        assertTrue(session.isGameOver());
        assertEquals(Integer.valueOf(2), session.getRemaining());
    }

    @Test
    void testLoose() {
        String[] stages = {"Stage0", "Stage1", "Stage3", "Stage4"};
        GameSession session = new GameSession("тест", stages);

        session.guessLetter('а');
        assertFalse(session.isGameOver());
        assertEquals("____", session.getWord());
        assertEquals(Integer.valueOf(1), session.getMistakesNumber());
        assertEquals(Integer.valueOf(2), session.getRemaining());

        session.guessLetter('п');
        assertEquals(Integer.valueOf(2), session.getMistakesNumber());
        assertEquals("____", session.getWord());
        assertFalse(session.isGameOver());
        assertEquals(Integer.valueOf(1), session.getRemaining());

        session.guessLetter('г');
        assertEquals("____", session.getWord());
        assertEquals(Integer.valueOf(3), session.getMistakesNumber());
        assertTrue(session.isGameOver());
        assertEquals(Integer.valueOf(0), session.getRemaining());

    }

    @Test
    void changeWord() {
        String[] stages = {"Stage0", "Stage1", "Stage3", "Stage4"};
        GameSession session = new GameSession("тест", stages);
        session.changeWord('т');
        assertEquals("т__т", session.getWord());
    }

}
