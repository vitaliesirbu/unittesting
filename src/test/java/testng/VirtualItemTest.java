package testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.VirtualItem;

public class VirtualItemTest {

    @Test
    public void testToString() {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Windows");
        virtualItem.setPrice(11);
        virtualItem.setSizeOnDisk(20000);

        String expectedOutput = "Class: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0";

        String actualOutput = virtualItem.toString();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualOutput, expectedOutput, "VirtualItem toString should return the correct string format.");

        softAssert.assertAll();
    }
}