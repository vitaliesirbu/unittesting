package junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartTest {

    private Cart testCart;
    private RealItem testRealItem;
    private VirtualItem testVirtualItem;

    @BeforeEach
    void setUp() {
        testCart = new Cart("test-cart");

        testRealItem = new RealItem();
        testRealItem.setName("TestRealItem");
        testRealItem.setPrice(100.0);
        testRealItem.setWeight(20.0);

        testVirtualItem = new VirtualItem();
        testVirtualItem.setName("TestVirtualItem");
        testVirtualItem.setPrice(50.0);
        testVirtualItem.setSizeOnDisk(1000.0);
    }

    @AfterEach
    void tearDown() {
        testCart = null;
        testRealItem = null;
        testVirtualItem = null;
    }

    @Test
    void addRealItem() {
        testCart.addRealItem(testRealItem);
        assertTrue(testCart.getTotalPrice() > 0, "Total price should be greater than 0 after adding a real item");
    }

    @Test
    void addVirtualItem() {
        testCart.addVirtualItem(testVirtualItem);
        assertTrue(testCart.getTotalPrice() > 0, "Total price should be greater than 0 after adding a virtual item");
    }

    @Test
    void deleteRealItem() {
        testCart.addRealItem(testRealItem);
        testCart.deleteRealItem(testRealItem);
        assertEquals(0, testCart.getTotalPrice(), "Total price should be 0 after deleting the real item");
    }

    @Test
    void deleteVirtualItem() {
        testCart.addVirtualItem(testVirtualItem);
        testCart.deleteVirtualItem(testVirtualItem);
        assertEquals(0, testCart.getTotalPrice(), "Total price should be 0 after deleting the virtual item");
    }
}
