package ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.concise_api.ConciseAPI;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class ProductPage {

    private ConciseAPI conciseAPI;
    private WebDriver driver;

    private By counter = By.cssSelector("#cart .quantity");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        conciseAPI = new ConciseAPI(driver);
    }

    public void assertCounterIs(int numOfProducts) {
        conciseAPI.assertThat(attributeToBe(counter, "outerText", String.valueOf(numOfProducts)));
    }
}
