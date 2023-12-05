
package testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonParserWriteToFileTest {

    private Cart testCart;
    private File testFile;
    private JsonParser parser;

    @BeforeMethod
    public void setUp() {
        testCart = new Cart("test-cart");
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

        parser = new JsonParser();

        testFile = new File("src/main/resources/test-cart.json");
    }

    @Test
    public void testWriteToFileCreatesCorrectFile() {
        SoftAssert softAssert = new SoftAssert();

        parser.writeToFile(testCart);

        softAssert.assertTrue(testFile.exists(), "File should be created");

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            softAssert.fail("Exception occurred while reading the file", e);
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
