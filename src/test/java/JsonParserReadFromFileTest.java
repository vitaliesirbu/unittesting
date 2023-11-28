import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserReadFromFileTest {

    private JsonParser parser;
    private File tempFile;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        parser = new JsonParser();
        tempFile = tempDir.resolve("temp-cart.json").toFile();
        // Create a Cart instance and write it to the temp file
        Cart tempCart = new Cart("temp-cart");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("{\"cartName\":\"temp-cart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}");
        }
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Disabled
    @Test
    void readFromFile_NormalCase() {
        Cart cart = parser.readFromFile(tempFile);
        assertNotNull(cart);
        assertEquals("temp-cart", cart.getCartName());
        assertEquals(0.0, cart.getTotalPrice());
    }

    @Test
    void readFromFile_NonExistingFile() {
        File nonExistingFile = new File("nonExistingFile.json");
        assertThrows(NoSuchFileException.class, () -> parser.readFromFile(nonExistingFile));
    }

    @Test
    void readFromFile_CorruptedFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Invalid JSON");
        }
        assertThrows(RuntimeException.class, () -> parser.readFromFile(tempFile));
    }
}
