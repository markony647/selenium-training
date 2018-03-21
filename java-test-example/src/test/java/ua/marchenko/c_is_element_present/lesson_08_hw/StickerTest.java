package ua.marchenko.c_is_element_present.lesson_08_hw;

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

import java.util.ArrayList;
import java.util.List;

public class StickerTest {

    WebDriver driver;
    WebDriverWait wait;

    By sticker = By.className("sticker");
    By mostPopular = By.cssSelector("#box-most-popular .image-wrapper");
    By campaigns = By.cssSelector("#box-campaigns .image-wrapper");
    By latest = By.cssSelector("#box-latest-products .image-wrapper");

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void testStickersSaleAndNew() {
        List<WebElement> mostPopularProducts = driver.findElements(mostPopular);
        List<WebElement> campaignsProducts = driver.findElements(campaigns);
        List<WebElement> latestProducts = driver.findElements(latest);
        List<WebElement> allProducts = new ArrayList<WebElement>();
        allProducts.addAll(mostPopularProducts);
        allProducts.addAll(campaignsProducts);
        allProducts.addAll(latestProducts);
        for (WebElement product : allProducts) {
            Assert.assertTrue(product.findElements(By.className("sticker")).size() == 1);
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> driver.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
        Assert.assertTrue(isElementPresent(mostPopular));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
