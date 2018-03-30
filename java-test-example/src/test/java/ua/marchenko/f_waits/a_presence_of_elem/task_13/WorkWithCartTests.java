package ua.marchenko.f_waits.a_presence_of_elem.task_13;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class WorkWithCartTests {

    WebDriver driver;
    WebDriverWait wait;
    By productsInSummaryTable = By.cssSelector("table.dataTable td.item");

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void testWorkWithCart() {
        selectFirstProduct();
        assertProductCounterIs(0);
        selectByValueFromSelectOptions("Small");
        addCart();
        assertProductCounterIs(1);

        returnToMainPage();

        selectFirstProduct();
        assertProductCounterIs(1);
        selectByValueFromSelectOptions("Medium");
        addCart();
        assertProductCounterIs(2);

        returnToMainPage();

        selectFirstProduct();
        assertProductCounterIs(2);
        selectByValueFromSelectOptions("Large");
        addCart();
        assertProductCounterIs(3);

        checkOut();
        assertOrderTableItemsNumber(3);
        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(2);

        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(1);

        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(0);

    }

    private void selectFirstProduct() {
        driver.findElements(By.cssSelector("#box-campaigns a.link")).get(0).click();
    }

    private void selectByValueFromSelectOptions(String option) {
        Select size = new Select(driver.findElement(By.tagName("select")));
        size.selectByValue(option);
    }

    private void addCart() {
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
    }

    private void returnToMainPage() {
        driver.get("http://localhost/litecart/en/");
    }

    public void assertProductCounterIs(int amount) {
        wait.until(attributeToBe(By.cssSelector("#cart .quantity"), "outerText", String.valueOf(amount)));
    }

    public void checkOut() {
        driver.findElement(By.cssSelector("#cart .link")).click();
    }

    public void removeCurrentProductFromCart() {
        WebElement orderSummaryTable = driver.findElement(By.cssSelector("table .dataTable"));
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        Assert.assertTrue(isUpdated(orderSummaryTable));
    }

    public void assertOrderTableItemsNumber(int count) {
        wait.until(numberOfElementsToBe(productsInSummaryTable, count));
    }

    public boolean isUpdated(WebElement element) {
        return wait.until(stalenessOf(element));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
