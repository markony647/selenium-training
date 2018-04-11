package ua.marchenko.j_page_object_and_other_patterns.a_task_19.app;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.concise_api.ConciseAPI;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages.CheckoutPage;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages.ProductPage;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.pages.ProductsMainPage;

public class Application {

    private WebDriver driver;
    public ProductsMainPage productsMainPage;
    public ProductPage productPage;
    public CheckoutPage checkoutPage;

    public Application() {
        driver = new ChromeDriver();
        new ConciseAPI(driver);
        productsMainPage = new ProductsMainPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void quit() {
        driver.quit();
    }
}
