package ua.marchenko.g_alerts_modals;


import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
// либо alert.dismiss();




        driver.get("http://blueimp.github.io/jQuery-File-Upload/basic.html");
        attachFile(driver, By.id("fileupload"), "C:\\temp\\image.png");

        driver.get("http://imgup.net/");
        attachFile(driver, By.id("image_image"), "C:\\temp\\image.png");

        driver.get("http://www.2imgs.com/");
        attachFile(driver, By.id("f_file"), "C:\\temp\\image.png");
    }

    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
