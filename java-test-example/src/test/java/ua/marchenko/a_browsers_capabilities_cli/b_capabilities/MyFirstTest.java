package ua.marchenko.a_browsers_capabilities_cli.b_capabilities;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();

    @Before
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
        WebDriver driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
