package TestNG;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import shop.VirtualItem;

public class VirtualItemTest {

    @Test
    public void testToString() {
        // Arrange
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Windows");
        virtualItem.setPrice(11);
        virtualItem.setSizeOnDisk(20000);

        String expectedOutput = "Class: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0";

        // Act
        String actualOutput = virtualItem.toString();

        // Assert
        assertEquals(actualOutput, expectedOutput, "VirtualItem toString should return the correct string format.");
    }
}