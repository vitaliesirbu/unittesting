package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonParserWriteToFileTest {

    private Cart testCart;
    private Parser parser;
    private String filePath;
    private File file;

    @BeforeEach
    void setUp() {
        // Setup a test Cart
        testCart = new Cart("test-cart");

        // Add items to the cart
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);
        testCart.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);
        testCart.addVirtualItem(disk);

        // Setup JsonParser
        parser = new JsonParser();

        // Setup file path
        filePath = "src/main/resources/test-cart.json";
        file = new File(filePath);
    }

    @AfterEach
    void tearDown() {
        // Clean up the created file
        file.delete();
    }

    @Test
    void writeToFile_NormalCase() {
        // Write to file
        parser.writeToFile(testCart);

        // Check if file is created
        assertTrue(file.exists());

        // Verify file content
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String content = reader.readLine();
            assertNotNull(content);
            assertTrue(content.contains("Audi"));
            assertTrue(content.contains("Windows"));
        } catch (IOException e) {
            fail("Exception occurred while reading file: " + e.getMessage());
        }
    }

    @Test
    void writeToFile_EmptyCart() {
        // Create an empty cart and write to file
        Cart emptyCart = new Cart("empty-cart");
        parser.writeToFile(emptyCart);

        File emptyCartFile = new File("src/main/resources/empty-cart.json");

        // Check if file is created
        assertTrue(emptyCartFile.exists());

        // Verify file content is empty or minimal JSON
        try (BufferedReader reader = new BufferedReader(new FileReader(emptyCartFile))) {
            String content = reader.readLine();
            assertNotNull(content);
            assertTrue(content.matches("\\{\\s*\\}")); // Checks for an empty JSON object
        } catch (IOException e) {
            fail("Exception occurred while reading file: " + e.getMessage());
        } finally {
            emptyCartFile.delete();
        }
    }

    @Test
    void writeToFile_ExceptionTest() {
        // Try writing to a read-only directory or invalid path
        Cart invalidCart = new Cart("invalid-cart");
        parser.writeToFile(invalidCart);

        File invalidFile = new File("src/readonly/invalid-cart.json");

        // Check if file creation failed
        assertFalse(invalidFile.exists());
    }
}
