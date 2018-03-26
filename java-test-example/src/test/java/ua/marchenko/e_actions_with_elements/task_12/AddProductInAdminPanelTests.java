package ua.marchenko.e_actions_with_elements.task_12;


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

import java.io.File;
import java.sql.Timestamp;

public class AddProductInAdminPanelTests {

    WebDriver driver;
    WebDriverWait wait;

    By successNotification = By.cssSelector("div.notice.success");
    String productName = "test_product" + getUniqueAppendix();

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
    }

    @Test
    public void testAddProduct() {
        login();
        driver.findElement(By.xpath("//span[contains(., 'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(., 'Add New Product')]")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name=code]")).sendKeys("00000");
        WebElement productGroupsTable = driver.findElement(By.xpath("//strong[contains(., 'Product Groups')]//..//table"));
        productGroupsTable.findElement(By.cssSelector("input[type=checkbox]")).click();
        WebElement quantityElement = driver.findElement(By.cssSelector("input[name=quantity]"));
        quantityElement.clear();
        quantityElement.sendKeys("10");
        uploadImage(By.cssSelector("input[type=file]"), "\\src\\test\\java\\ua\\marchenko\\resources\\test_product.png");
        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("10/11/2017");
        driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("10/11/2020");
        driver.findElement(By.cssSelector("button[name=save]")).click();
        Assert.assertTrue(isElementPresent(successNotification));
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        Assert.assertTrue(isElementPresent(By.linkText(productName)));
    }

     public void login() {
         driver.get("http://localhost/litecart/admin/");
         driver.findElement(By.name("username")).sendKeys("admin");
         driver.findElement(By.name("password")).sendKeys("admin");
         driver.findElement(By.name("login")).click();
     }

    private void uploadImage(By fileField, String pathToImage) {
        String filePath = new File("").getAbsolutePath();
        String absPath = filePath.concat(pathToImage);
        driver.findElement(fileField).sendKeys(absPath);
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> driver.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    private String getUniqueAppendix() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }


    @After
     public void tearDown() {
         driver.quit();
     }
}
