package ua.marchenko.e_actions_with_elements.task_11;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Timestamp;

public class RegisterNewCustomerTests {

    WebDriver driver;
    WebDriverWait wait;
    String email = getUniqueEmail();
    String password = "access";
    By successNotification = By.cssSelector("div.notice.success");

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void testRegisterNewCustomer() {
        driver.findElement(By.cssSelector("[name=login_form] a")).click();
        driver.findElement(By.cssSelector("table input[name=firstname]")).sendKeys("Vitaly");
        driver.findElement(By.cssSelector("table input[name=lastname]")).sendKeys("Marchenko");
        driver.findElement(By.cssSelector("table input[name=address1]")).sendKeys("Test Address");
        driver.findElement(By.cssSelector("table input[name=postcode]")).sendKeys("00000");
        driver.findElement(By.cssSelector("table input[name=city]")).sendKeys("New York");
        WebElement selectElement = driver.findElement(By.cssSelector("select[name=country_code]"));
        Select countrySelect = new Select(selectElement);
        countrySelect.selectByVisibleText("United States");
        driver.findElement(By.cssSelector("table input[name=email]")).sendKeys(email);
        WebElement phoneFieldElement = driver.findElement(By.cssSelector("table input[name=phone]"));
        phoneFieldElement.click();
        phoneFieldElement.sendKeys(Keys.HOME + "+1111111111");
        driver.findElement(By.cssSelector("table input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("table input[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        Assert.assertTrue(isElementPresent(successNotification));
        logout();
        loginAsCustomer(email, password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        Assert.assertTrue(isElementPresent(successNotification));
    }

    private String getUniqueEmail() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String emailBase = "test@test";
        return emailBase + String.valueOf(timestamp.getTime()) + ".com";
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> driver.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    public void loginAsCustomer(String email, String password) {
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
