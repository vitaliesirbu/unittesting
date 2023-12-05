package junit;

import org.junit.jupiter.api.*;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

class VirtualItemTest {

    private static VirtualItem virtualItem;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up the testing environment...");
    }

    @BeforeEach
    void init() {
        virtualItem = new VirtualItem();
    }

    @Test
    @DisplayName("Test setting and getting size on disk")
    void testSizeOnDisk() {
        double expectedSize = 5000.0;
        virtualItem.setSizeOnDisk(expectedSize);
        double actualSize = virtualItem.getSizeOnDisk();

        assertEquals(expectedSize, actualSize, "The size on disk should match the set value");
    }

    @AfterEach
    void tearDownEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Cleaning up the testing environment...");
    }
}
