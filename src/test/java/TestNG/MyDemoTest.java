package TestNG;

import org.testng.annotations.Test;

public class MyDemoTest {
    @Test
    void setup(){
        System.out.println("I am inside setup");
    }
    @Test
    void testSteps(){
        System.out.println("I am inside testSteps");
    }
    @Test
    void tierDown(){
        System.out.println("I am inside teardown");
    }
}
