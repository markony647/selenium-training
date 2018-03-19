package ua.marchenko.a_browsers_capabilities_cli.f_cookies;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

public class MyFirstTest {

    WebDriver driver;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        driver.manage().addCookie(new Cookie("test", "test"));
        Cookie testCookie = driver.manage().getCookieNamed("test");
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.manage().deleteCookieNamed("test");
        driver.manage().deleteAllCookies();
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
