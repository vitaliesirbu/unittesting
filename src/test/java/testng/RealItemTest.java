package testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

public class RealItemTest {

    @Test
    public void testRealItemProperties() {
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        // Initialize the SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        // Use soft assertions instead of hard assertions
        softAssert.assertEquals(car.getName(), "Audi", "The name of the item is not set correctly.");
        softAssert.assertEquals(car.getPrice(), 32026.9, "The price of the item is not set correctly.");
        softAssert.assertEquals(car.getWeight(), 1560.0, "The weight of the item is not set correctly.");

        // Soft assertion for the toString method
        String expectedToString = "Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0";
        softAssert.assertEquals(car.toString(), expectedToString, "The toString method does not return the expected output.");

        // This will check all soft assertions made during the test
        softAssert.assertAll();
    }
}