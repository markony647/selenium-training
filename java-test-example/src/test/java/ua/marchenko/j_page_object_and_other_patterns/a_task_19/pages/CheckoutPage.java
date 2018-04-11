package ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.concise_api.ConciseAPI;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class CheckoutPage {

    private ConciseAPI conciseAPI;
    private WebDriver driver;

    private By orderSummaryTable = By.cssSelector("table .dataTable");
    private By removeButton = By.cssSelector("button[name=remove_cart_item]");
    private By productsInSummaryTable = By.cssSelector("table.dataTable td.item");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        conciseAPI = new ConciseAPI(driver);
    }

    public void open() {
        driver.findElement(By.cssSelector("#cart .link")).click();
    }

    public void removeFromCart() {
        driver.findElement(removeButton).click();
        conciseAPI.isElementUpdated(driver.findElement(orderSummaryTable));
    }

    public void assertTableCountIs(int count) {
        conciseAPI.assertThat(numberOfElementsToBe(productsInSummaryTable, count));
    }
}
