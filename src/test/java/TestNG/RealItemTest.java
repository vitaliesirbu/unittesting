package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.RealItem;

public class RealItemTest {

    @Test
    public void testRealItemProperties() {
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        // Assert that the name has been set correctly
        Assert.assertEquals(car.getName(), "Audi", "The name of the item is not set correctly.");

        // Assert that the price has been set correctly
        Assert.assertEquals(car.getPrice(), 32026.9, "The price of the item is not set correctly.");

        // Assert that the weight has been set correctly
        Assert.assertEquals(car.getWeight(), 1560.0, "The weight of the item is not set correctly.");

        // Assert that the toString method returns the correct string representation
        String expectedToString = "Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0";
        Assert.assertEquals(car.toString(), expectedToString, "The toString method does not return the expected output.");
    }
}