package junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {

    private RealItem item;

    @BeforeEach
    void setUp() {
        // This method runs before each test.
        // It sets up the necessary preconditions for the tests.
        item = new RealItem();
    }

    @Test
    void testWeight() {
        // Testing both setWeight and getWeight methods
        double weight = 1500.0; // example weight
        item.setWeight(weight);

        assertEquals(weight, item.getWeight(), "Weight should be equal to the value set");
    }

    @AfterEach
    void tearDown() {
        // This method runs after each test.
        // It can be used to perform cleanup activities.
        item = null;
    }
}
