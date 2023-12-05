package TestNG;

import org.junit.jupiter.api.Disabled;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import com.google.gson.Gson;
import parser.JsonParser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Disabled
public class JsonParserReadFromFileTest {


    private JsonParser parser;
    private File tempFile;

    @BeforeClass
    public void setUp() throws IOException {
        parser = new JsonParser();
        tempFile = File.createTempFile("test-cart", ".json");

        // Create a Cart object and convert it to JSON
        Cart testCart = new Cart("test-cart");
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        testCart.addRealItem(car);
        testCart.addVirtualItem(disk);

        // Write JSON to the temporary file
        try (FileWriter writer = new FileWriter(tempFile)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(testCart));
        }
    }
    @Test
    public void testReadFromFileWithTempFile() {
        try {
            Cart cart = parser.readFromFile(tempFile);
            assertNotNull(cart, "Cart should not be null");
            assertEquals(cart.getCartName(), "test-cart", "Cart name should match");
            // Additional assertions to validate the content of the Cart
        } catch (Exception e) {
            fail("No exception should be thrown for a valid file");
        }
    }

    @AfterClass
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete(); // Delete the temporary file
        }
    }
}