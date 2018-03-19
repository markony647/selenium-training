package ua.marchenko.c_is_element_present;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");

        WebElement form1 = driver.findElement(By.id("login-form"));
        WebElement form2 = driver.findElement(By.tagName("form"));
        WebElement form3 = driver.findElement(By.className("login"));
        WebElement form4 = driver.findElement(By.cssSelector("form.login"));
        WebElement form5 = driver.findElement(By.cssSelector("#login-form"));

        WebElement field1 = driver.findElement(By.name("username"));
        WebElement field2 = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement link = driver.findElement(By.linkText("Logout"));
        List<WebElement> links = driver.findElements(By.tagName("a"));
    }


    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
