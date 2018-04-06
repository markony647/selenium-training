package ua.marchenko.h_logging.task_17;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.logging.Level;
import static org.junit.Assert.assertFalse;

public class ProductsBrowserLogTests {

    WebDriver driver;
    WebDriverWait wait;
    By products = By.xpath("//*/td/img/../a");

    @Before
    public void setUp() {
        DesiredCapabilities loggingCaps = setUpLoggingCapabilities();
        driver = new ChromeDriver(loggingCaps);
        wait = new WebDriverWait(driver, 4);
    }

    @Test
    public void testProductsNotContainErrorsInLog() {
        login();
        openCatalog();
        visitEachProductAndCheckLog();
    }

    private void visitEachProductAndCheckLog() {
        int numOfProducts = driver.findElements(products).size();
        for (int i = 0; i < numOfProducts; i++) {
            getProductByIndex(i).click();
            assertNoJsErrorsInLog(getBrowserLogs());
            back();
        }
    }

    private WebElement getProductByIndex(int index) {
        return driver.findElements(products).get(index);
    }


    private void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void openCatalog() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    }

    private void back() {
        driver.navigate().back();
    }

    private DesiredCapabilities setUpLoggingCapabilities() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return caps;
    }

    private List<LogEntry> getBrowserLogs() {
        return driver.manage().logs().get("browser").getAll();
    }

    private void assertNoJsErrorsInLog(List<LogEntry> log) {
        if (log.size() > 0) {
            for (LogEntry entry : log) {
                String currentLog = entry.toString();
                assertFalse(currentLog.contains("SEVERE"));
                assertFalse(currentLog.contains("FATAL"));
                assertFalse(currentLog.contains("ERROR"));
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
