package ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.concise_api.ConciseAPI;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.model.Product;

public class ProductsMainPage {

    private ConciseAPI conciseAPI;
    private WebDriver driver;

    private By firstProduct = By.cssSelector("#box-campaigns a.link");
    private By addCartButton = By.cssSelector("button[name=add_cart_product]");

    public ProductsMainPage(WebDriver driver) {
        this.driver = driver;
        conciseAPI = new ConciseAPI(driver);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public void returnToMain() {
        open();
    }

    public void addInCart(Product product) {
        driver.findElements(firstProduct).get(0).click();
        Select size = new Select(driver.findElement(By.tagName("select")));
        size.selectByValue(product.getSize());
        driver.findElement(addCartButton).click();
    }
}
