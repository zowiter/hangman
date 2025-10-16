package academy;
import java.util.*;

public enum WordDictionary {
    ANIMALS(
        List.of("кот", "собака", "тигр", "слон", "жираф", "обезьяна", "крокодил"),
        List.of("бегемот", "пантера", "кенгуру", "дельфин", "хамелеон"),
        List.of("акула", "страус", "фламинго", "скорпион", "тарантул")
    ),

    CITIES(
        List.of("москва", "самара", "сочи", "казань", "омск"),
        List.of("владивосток", "екатеринбург", "калининград", "новосибирск"),
        List.of("благовещенск", "уссурийск", "абакан")
    ),

    IT(
        List.of("код", "баг", "фича", "бит", "байт"),
        List.of("компиляция","алгоритм", "фреймворк", "интерфейс"),
        List.of("рефакторинг", "инкапсуляция", "полиморфизм")
    ),

    FOOD(
        List.of("суп", "хлеб", "сыр", "мясо", "чай"),
        List.of("борщ", "пельмени", "шашлык", "мороженое"),
        List.of("оливье", "винегрет", "салат")
    );

    private final List<String> easyWords;
    private final List<String> mediumWords;
    private final List<String> hardWords;
    private final Random random = new Random();

    WordDictionary(List<String> easyWords, List<String> mediumWords, List<String> hardWords) {
        this.easyWords = easyWords;
        this.mediumWords = mediumWords;
        this.hardWords = hardWords;
    }

    /**
     * @param difficulty Game difficulty
     * @return (String) Words for this difficulty
     */
    public String getRandomWord(Difficulty difficulty) {
        List<String> words = switch (difficulty) {
            case EASY -> easyWords;
            case MEDIUM -> mediumWords;
            case HARD -> hardWords;
        };
        return words.get(random.nextInt(words.size()));
    }

    /**
     * @return (WordDictionary) Get random category for guessing
     */
    public static WordDictionary getRandomCategory() {
        WordDictionary[] categories = values();
        return categories[new Random().nextInt(categories.length)];
    }

    /**
     * @param number Number for each category for easier setting.
     *               0 - RANDOM CATEGORY;
     *               1 - ANIMALS;
     *               2 - CITIES;
     *               3 - IT;
     *               4 - FOOD;
     * @return (WordDictionary) Returns object with all words for chosen category
     */
    public static WordDictionary getCategoryByNumber(int number) {
        switch (number) {
            case 1: return WordDictionary.ANIMALS;
            case 2: return WordDictionary.CITIES;
            case 3: return WordDictionary.IT;
            case 4: return WordDictionary.FOOD;
            default: return WordDictionary.getRandomCategory();
        }
    }

    /**
     * @param difficulty Game difficulty
     * @return (List<String>) Returns words for chosen difficulty
     */
    public List<String> getWords(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> easyWords;
            case MEDIUM -> mediumWords;
            case HARD -> hardWords;
        };
    }
}
