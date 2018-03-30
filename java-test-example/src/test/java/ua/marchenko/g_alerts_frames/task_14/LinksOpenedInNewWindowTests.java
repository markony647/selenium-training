package ua.marchenko.g_alerts_frames.task_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class LinksOpenedInNewWindowTests {

    WebDriver driver;
    WebDriverWait wait;
    By externalLink = By.cssSelector("#content tbody [href^=http]");
    String originalWindow;
    Set<String> existingWindows;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
    }

    @Test
    public void testLinksOpenedInNewWindows() {
        login();
        originalWindow = driver.getWindowHandle();
        existingWindows = driver.getWindowHandles();
        navigateToCountries();
        goToEditCountryPage();
        int testLinkCount = getTestLinksCount();
        for (int i = 0; i < testLinkCount; i++) {
            getNthTestLink(i).click();
            switchToOpenedWindow();
            closeCurrentWindow();
        }
    }

    public int getTestLinksCount() {
        return driver.findElements(externalLink).size();
    }

    public void switchToOpenedWindow() {
        String newWindow = wait.until(anyWindowOtherThan(existingWindows));
        driver.switchTo().window(newWindow);
    }

    public void closeCurrentWindow() {
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    public WebElement getNthTestLink(int number) {
        return driver.findElements(externalLink).get(number);
    }

    public ExpectedCondition<String> anyWindowOtherThan(final Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    private void goToEditCountryPage() {
        driver.findElement(By.cssSelector("td [title=Edit]")).click();
    }

    public void navigateToCountries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }

    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
