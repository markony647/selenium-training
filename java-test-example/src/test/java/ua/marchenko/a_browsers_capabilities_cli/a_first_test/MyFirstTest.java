package ua.marchenko.a_browsers_capabilities_cli.a_first_test;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
