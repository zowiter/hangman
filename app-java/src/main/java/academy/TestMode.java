package academy;

public class TestMode {
    private String secretWord;
    private String guessedWord;

    public TestMode(String secretWord, String guessedWord) {
        this.secretWord = secretWord.toLowerCase();
        this.guessedWord = guessedWord.toLowerCase();
    }

    /**
     * @return (boolean) Result of checking whether the word is guessed
     */
    public boolean checkGuessedWord() {
        return guessedWord.equals(secretWord);
    }

    /**
     * @return (String) Returns the result of guessing in the format "word's_guessed_letters;result"
     * Letters: * - if the letter is not guessed otherwise the guessed letter
     * Result: NEG - the word is not guessed, POS - the word is guessed
     * @throws Exception Throws an exception if lengths of secret and guessed word are not the same
     */
    public String getResult() throws Exception {
        if (secretWord.length() != guessedWord.length()) {
            System.out.println("Длины слов не совпадают!");
            throw new WrongLengthException("Lengths do not match");
        }

        if (checkGuessedWord()) {
            return guessedWord + ";POS";
        } else {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < secretWord.length(); i++) {
                char secretChar = secretWord.charAt(i);
                // Если буква есть в угаданном слове - показываем ее, иначе *
                if (guessedWord.indexOf(secretChar) != -1) {
                    result.append(secretChar);
                } else {
                    result.append('*');
                }
            }

            return result.toString() + ";NEG";
        }
    }
}
