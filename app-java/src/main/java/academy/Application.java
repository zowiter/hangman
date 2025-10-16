package academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import javax.xml.transform.Source;
import static java.util.Objects.nonNull;

@Command(name = "Application Example", version = "Example 1.0", mixinStandardHelpOptions = true)
public class Application implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final ObjectReader YAML_READER =
        new ObjectMapper(new YAMLFactory()).findAndRegisterModules().reader();
    private static final Predicate<String[]> IS_TESTING_MODE = words -> nonNull(words) && words.length == 2;

    @Option(
        names = {"-s", "--font-size"},
        description = "Font size")
    int fontSize;

    @Parameters(
        paramLabel = "<word>",
        description = "Words pair for testing mode")
    private String[] words;

    @Option(
        names = {"-c", "--config"},
        description = "Path to YAML config file")
    private File configPath;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        AppConfig config = loadConfig();
        LOGGER.atInfo().addKeyValue("config", config).log("Config content");

        // ... logic
        if (IS_TESTING_MODE.test(config.words())) {
            LOGGER.atInfo().log("Non-interactive testing mode enabled");
            var word = config.words()[0];
            var userInput = config.words()[1];
            try {
                String result = new TestMode(word, userInput).getResult();
                System.out.println(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.atInfo().log("Interactive mode enabled");
            Console con = System.console();
            Scanner in = new Scanner(con.reader());

            System.out.println("Выберите категорию из списка:\n" +
                " 0 - Случайная категория\n 1 - Животные\n 2 - Города\n 3 - Слова из АйТи\n 4 - Еда\n");
            int categoryChoice = in.nextInt();

            System.out.println("\nВыберите сложность из списка:\n" +
                " 0 - Случайная сложность\n 1 - Лёгкая\n 2 - Средняя\n 3 - Сложная\n");
            int difficultyChoice = in.nextInt();

            GameSettings settings = new GameSettings(
                Difficulty.getDifficultyByNumber(difficultyChoice),
                WordDictionary.getCategoryByNumber(categoryChoice)
            );
            String[] hangmanStates = settings.getHangmanStates();
            GameSession game = new GameSession(settings.getSecretWord(), hangmanStates);

            System.out.println("\nКоличество попыток (количество допустимых ошибок): " + (hangmanStates.length - 1));
            System.out.println(game.getWord());

            while (!game.isGameOver()) {
                System.out.println("Введите букву:");
                char letter = in.next().charAt(0);
                game.guessLetter(letter);
            }
            System.exit(0);
        }
    }

    private AppConfig loadConfig() {
        // fill with cli options
        if (configPath == null) return new AppConfig(fontSize, words);

        // use config file if provided
        try {
            return YAML_READER.readValue(configPath, AppConfig.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
