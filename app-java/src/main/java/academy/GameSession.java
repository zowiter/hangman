package academy;

public class GameSession {
    private final String secretWord;
    private static Integer mistakesNumber = 0;
    private boolean isGameOver = false;
    private static String word; // Строка из * и _, где * - какая буква угадана, а _ - какая нет
    private static Integer maxMistakes = 0;
    private static String[] HANGMAN_STAGES;
    private static Integer remaining;

    public GameSession(String secretWord, String[] HANGMAN_STAGES) {
        this.secretWord = secretWord;
        this.HANGMAN_STAGES = HANGMAN_STAGES;
        word = String.valueOf('_').repeat(secretWord.length());
        maxMistakes = HANGMAN_STAGES.length - 1;
        remaining = 0;
    }

    /**
     * @param letter Input of user's letter
     */
    public void guessLetter(char letter) {
        remaining = maxMistakes - mistakesNumber;
        if (!secretWord.contains(String.valueOf(letter))) {
            System.out.println("Буква не угадана!");
            if (mistakesNumber == HANGMAN_STAGES.length - 2) { //Если ошибка - последняя
                mistakesNumber++;
                remaining = maxMistakes - mistakesNumber;
                isGameOver = true;
                System.out.println(GameSession.getState());
                System.out.println("""
                 ===============
                    Проигрыш!
                 ===============
                    """);
                isGameOver = true;
            }
            if (!isGameOver) {
                mistakesNumber++;
                remaining = maxMistakes - mistakesNumber;
                System.out.println("Количество оставшихся попыток: " + remaining.toString());
                System.out.println(word + "\n");
                System.out.println(GameSession.getState());
            }
        } else if (secretWord.contains(String.valueOf(letter)) && !isGameOver) {
            System.out.println("Буква угадана!");
            changeWord(letter);
            System.out.println(word + "\n");
            if (!word.contains(String.valueOf('_'))) {
                System.out.println("""
                 ==============
                    Выигрыш!
                 ==============
                    """);
                isGameOver = true;
            }
        }
    }

    /**
     * @return (String) Hangman states (stages) for this game session
     */
    private static String getState() {
        return HANGMAN_STAGES[mistakesNumber];
    }

    /**
     * @param letter Input of user's letter for generating string with guessed and not guessed letters
     */
    public void changeWord(char letter) {
        StringBuilder newWord = new StringBuilder(word);

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                newWord.setCharAt(i, letter);
            }
        }
        word = newWord.toString();
    }

    /**
     * @return (boolean) Returns whether the game is finished
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    public String getWord() { //For tests
        return word;
    }

    public String[] getHangmanStages() { //For tests
        return HANGMAN_STAGES;
    }

    public Integer getMistakesNumber() { //For tests
        return mistakesNumber;
    }

    public static Integer getRemaining() {
        return remaining;
    }
}
