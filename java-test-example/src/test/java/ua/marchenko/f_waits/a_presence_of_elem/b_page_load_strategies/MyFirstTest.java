package ua.marchenko.f_waits.a_presence_of_elem.b_page_load_strategies;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);

    @Test
    public void testFirstTest() {
        WebElement element = wait.until(presenceOfElementLocated(By.name("q")));
        WebElement element2 = wait.until((WebDriver d) -> d.findElement(By.name("q")));
        driver.get("http://google.com");

        driver.navigate().refresh();
        wait.until(stalenessOf(element));

        wait.until(visibilityOf(element));

        List<WebElement> elements = wait.until(
                (WebDriver d) -> {
                    List<WebElement> l = d.findElements(By.cssSelector("div.rc"));
                    return l.size() == 10 ? l : null;
                }
        );

        // инициализируем драйвер
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        WebDriver driver = new FirefoxDriver(capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // открываем сайт
        driver.get("http://www.sazehgostar.com/SitePages/HomePage.aspx");
        // ждём появления кнопки на "недозагруженной" странице
        WebElement button = wait.until(visibilityOfElementLocated(By.id("en")));
        // кликаем
        button.click();
        // ждём исчезновения кнопки, то есть "выгрузки" страницы
        wait.until(stalenessOf(button));
        // ждём загрузки следующей страницы
        wait.until(visibilityOfElementLocated(By.id("menu")));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
