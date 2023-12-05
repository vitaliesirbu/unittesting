package testng;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import com.google.gson.Gson;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonParserReadFromFileTest {


    private JsonParser parser;
    private File tempFile;

    @BeforeClass
    public void setUp() throws IOException {
        parser = new JsonParser();
        tempFile = File.createTempFile("test-cart", ".json");

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

        try (FileWriter writer = new FileWriter(tempFile)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(testCart));
        }
    }
    @Test(enabled = false)
    public void testReadFromFileWithTempFile() {
        SoftAssert softAssert = new SoftAssert();
        try {
            Cart cart = parser.readFromFile(tempFile);
            softAssert.assertNotNull(cart, "Cart should not be null");
            softAssert.assertEquals(cart.getCartName(), "test-cart", "Cart name should match");

        } catch (Exception e) {
            fail("No exception should be thrown for a valid file");
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterClass
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
}