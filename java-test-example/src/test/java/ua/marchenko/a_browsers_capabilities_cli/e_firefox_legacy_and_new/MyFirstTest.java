package ua.marchenko.a_browsers_capabilities_cli.e_firefox_legacy_and_new;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class MyFirstTest {

    WebDriver driver;

    @Before
    public void start() {

        // Old scheme - recommended for FF ESR!!!
        FirefoxOptions options = new FirefoxOptions();
        options.setLegacy(true);
        options.setBinary(new FirefoxBinary(new File("C:\\firefox_esr\\firefox.exe")));
        driver = new FirefoxDriver(options);

        // new scheme
//        options.setLegacy(false);
//        options.setBinary(new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe")));
//        WebDriver driver = new FirefoxDriver(options);
    }

    @Test
    public void testFirstTest() {
        driver.get("http://google.com");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
