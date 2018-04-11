package ua.marchenko.j_page_object_and_other_patterns.a_task_19.tests;


import org.junit.Test;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.model.Product;

public class WorkWithCartTests extends TestBase {


    @Test
    public void testWorkWithCart() {
        app.productsMainPage.open();
        app.productsMainPage.addInCart(new Product("Small"));
        app.productPage.assertCounterIs(1);
        app.productsMainPage.returnToMain();

        app.productsMainPage.addInCart(new Product("Medium"));
        app.productPage.assertCounterIs(2);
        app.productsMainPage.returnToMain();

        app.productsMainPage.addInCart(new Product("Large"));
        app.productPage.assertCounterIs(3);
        app.productsMainPage.returnToMain();

        app.checkoutPage.open();
        app.checkoutPage.assertTableCountIs(3);
        app.checkoutPage.removeFromCart();
        app.checkoutPage.assertTableCountIs(2);
        app.checkoutPage.removeFromCart();
        app.checkoutPage.assertTableCountIs(1);
        app.checkoutPage.removeFromCart();
        app.checkoutPage.assertTableCountIs(0);
    }
}
