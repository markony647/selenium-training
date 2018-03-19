package ua.marchenko.c_is_element_present.lesson_07_hw;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertTrue;

public class AdminPanelTest {
    WebDriver driver;
    WebDriverWait wait;

    By mainMenuItems = By.cssSelector("li#app- ");
    By innerItems = By.cssSelector("ul[class=docs] li a");
    By menuSidebar = By.id("box-apps-menu");
    By pageTitle = By.tagName("h1");

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/admin");
        login();
    }

    @Test
    public void testCheckAllMenuItemInAdminPanel() throws InterruptedException {
        int numOfMenuItems  = getNumOfElements(mainMenuItems);
        for (int i = 0; i < numOfMenuItems; i++) {
            getNthsElement(mainMenuItems, i).click();
            assertTrue(isElementPresent(pageTitle));
            if (isElementPresent(innerItems)) {
               int numOfInnerMenuItems = getNumOfElements(innerItems);
               for (int j = 0; j < numOfInnerMenuItems; j++) {
                    getNthsElement(innerItems, j).click();
                   assertTrue(isElementPresent(pageTitle));
               }
            }
        }
    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(isElementPresent(menuSidebar));
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> driver.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public WebElement getNthsElement(By selector, int index) {
        return driver.findElements(selector).get(index);
    }

    public int getNumOfElements(By selector) {
        return driver.findElements(selector).size();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
