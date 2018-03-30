package ua.marchenko.f_waits.a_presence_of_elem;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");
        listNthElementHasText(By.cssSelector("asd"), 5, "dfsd");
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
    }

    public List<WebElement> waitCollectionSizeIs(By locator, int size) {
        wait.until(
                (WebDriver d) -> {
                    List<WebElement> l = d.findElements(By.cssSelector("div.rc"));
                    return l.size() == 10 ? l : null;
                }
        );
        return null;
    }

    public static ExpectedCondition<WebElement> listNthElementHasText(By locator, int index, String text) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            List<WebElement> elements;
            WebElement element;
            String elementText;
            @Override
            public WebElement apply(WebDriver driver) {
                elements = driver.findElements(locator);
                element = elements.get(index);
                elementText = element.getText();
                return elementText.contains(text) ? element : null;
            }

            @Override
            public String toString() {
                return String.format("\n%s element of list located %s contains text %s\nwhile expected text is: %s",
                        index, locator, elementText, text);
            }
        });
    }

    public static <V> ExpectedCondition<V> elementExceptionsCatcher(final Function<? super WebDriver, V> condition){
        return new ExpectedCondition<V>() {
            public V apply(WebDriver driver) {
                try {
                    return condition.apply(driver);
                } catch (StaleElementReferenceException | ElementNotVisibleException | IndexOutOfBoundsException e) {
                    return null;
                }
            }

            public String toString() {
                return condition.toString();
            }
        };
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
