package ua.marchenko.d_cookies;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginAdminPanel() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().addCookie(new Cookie("test", "test"));
        Cookie testCookie = driver.manage().getCookieNamed("test");
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.manage().deleteCookieNamed("test");
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
