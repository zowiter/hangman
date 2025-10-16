package academy;

public class GameSettings {
    private final Difficulty difficulty;
    private final String secretWord;

    public GameSettings(Difficulty difficulty, WordDictionary category) {
        this.difficulty = difficulty;
        this.secretWord = category.getRandomWord(difficulty);
    }

    /**
     * @return (String) Secret word for this game session
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * @return (String[]) Get Hangman states for chosen difficulty
     */
    public String[] getHangmanStates() {
        return Hangman.getHangman(difficulty);
    }
}
