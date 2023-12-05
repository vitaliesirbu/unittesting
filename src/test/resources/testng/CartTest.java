package testng;


import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;

public class CartTest {

    private Cart testCart;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        testCart = new Cart("test-cart");
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Optionally, you can assert all in tearDown to ensure assertions are checked
        // even if there's an exception in the test method before reaching softAssert.assertAll()
        softAssert.assertAll();
    }

    @Test(groups = "functional")
    public void shouldAddRealItemToCart() {
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        testCart.addRealItem(car);

        // Grouped assertion using a SoftAssert instance
        softAssert.assertEquals(car.getName(), "Audi", "Car name should match");
        softAssert.assertEquals(car.getPrice(), 32026.9, "Car price should match");
        softAssert.assertEquals(car.getWeight(), 1560.0, "Car weight should match");
        softAssert.assertEquals(testCart.getTotalPrice(), 32026.9 * 1.2, "Total price should include tax");

        // Assert all to consolidate the results of the grouped assertions
        softAssert.assertAll();
    }

    // The rest of the class remains unchanged...
}