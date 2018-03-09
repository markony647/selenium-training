package ua.marchenko;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void firstTest() {
        driver.get("http://google.com");
        driver.quit();
    }
}
