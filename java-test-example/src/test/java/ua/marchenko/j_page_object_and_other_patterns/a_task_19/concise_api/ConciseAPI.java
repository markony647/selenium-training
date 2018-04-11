package ua.marchenko.j_page_object_and_other_patterns.a_task_19.concise_api;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.marchenko.j_page_object_and_other_patterns.a_task_19.tests.TestBase;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class ConciseAPI {

    private WebDriver driver;

    public ConciseAPI(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementUpdated(WebElement element) {
        return assertThat(stalenessOf(element));
    }

    public <T> T assertThat(ExpectedCondition<T> condition) {
        return assertThat(condition, TestBase.timeout);
    }

    public <T> T assertThat(ExpectedCondition<T> condition, int timeout) {
        return new WebDriverWait(driver, timeout).until(condition);
    }
}
