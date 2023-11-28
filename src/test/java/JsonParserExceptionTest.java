import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;
import java.util.stream.Stream;

class JsonParserExceptionTest {

    private JsonParser jsonParser;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
    }

    static Stream<String> invalidFilePaths() {
        return Stream.of(
                "nonExistingFile.json",
                "invalidFolder/nonExistingFile.json",
                "/unaccessiblePath/nonExistingFile.json",
                "corruptedFile.json", // assuming this is a corrupted JSON file in the resources
                ""
        );
    }

    @Test
    void readFromInvalidFileTest() {
        invalidFilePaths().forEach(filePath -> {
            File file = new File("src/main/resources/" + filePath);
            assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(file),
                    "Expected readFromFile to throw, but it didn't");
        });
    }
}
