package ua.marchenko.f_waits.a_presence_of_elem.task_13;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class WorkWithCartTests {

    WebDriver driver;
    WebDriverWait wait;
    int currentNumOfProductsInCart = 0;
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
        assertProductCounterIs(currentNumOfProductsInCart);
        selectByValueFromSelectOptions("Small");
        addCart();
        assertProductCounterIs(currentNumOfProductsInCart);

        returnToMainPage();

        selectFirstProduct();
        assertProductCounterIs(currentNumOfProductsInCart);
        selectByValueFromSelectOptions("Medium");
        addCart();
        assertProductCounterIs(currentNumOfProductsInCart);

        returnToMainPage();

        selectFirstProduct();
        assertProductCounterIs(currentNumOfProductsInCart);
        selectByValueFromSelectOptions("Large");
        addCart();
        assertProductCounterIs(currentNumOfProductsInCart);

        checkOut();
        assertOrderTableItemsNumber(currentNumOfProductsInCart);
        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(currentNumOfProductsInCart);

        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(currentNumOfProductsInCart);

        removeCurrentProductFromCart();
        assertOrderTableItemsNumber(currentNumOfProductsInCart);
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
        currentNumOfProductsInCart += 1;
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
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        currentNumOfProductsInCart -= 1;
    }

    public void assertOrderTableItemsNumber(int count) {
        wait.until(numberOfElementsToBe(productsInSummaryTable, count));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
