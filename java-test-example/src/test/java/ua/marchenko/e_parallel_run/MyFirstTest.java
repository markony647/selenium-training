package ua.marchenko.e_parallel_run;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("q")).sendKeys("webdriver", Keys.ENTER);
        wait.until(titleIs("webdriver - Пошук Google"));
    }

    @Test
    public void mySecondTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver", Keys.ENTER);
        wait.until(titleIs("webdriver - Пошук Google"));
    }

    @Test
    public void myThirdTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver", Keys.ENTER);
        wait.until(titleIs("webdriver - Пошук Google"));
    }
}
