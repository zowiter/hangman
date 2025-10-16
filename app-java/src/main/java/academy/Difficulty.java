package academy;

import java.util.List;
import java.util.Random;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    private static final Random random = new Random();

    /**
     * @return (Difficulty) Get random difficulty for guessing
     */
    public static Difficulty getRandomDifficulty() {
        List<Difficulty> difficulty = List.of(Difficulty.values());
        return difficulty.get(random.nextInt(difficulty.size()));
    }

    /**
     * @param number Number for each difficulty for easier setting.
     *               0 - RANDOM CATEGORY;
     *               1 - EASY;
     *               2 - MEDIUM;
     *               3 - HARD;
     * @return (Difficulty) Returns chosen difficulty
     */
    public static Difficulty getDifficultyByNumber(int number) {
        switch (number) {
            case 1: return Difficulty.EASY;
            case 2: return Difficulty.MEDIUM;
            case 3: return Difficulty.HARD;
            default: return Difficulty.getRandomDifficulty();
        }
    }
}
