package ua.marchenko.h_logging;


import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyListener extends AbstractWebDriverEventListener {

    Logger log = Logger.getLogger(MyListener.class.getName());

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.log(new LogRecord(Level.INFO, by.toString()));
        //System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.log(new LogRecord(Level.INFO, by.toString() + " found"));
        //System.out.println(by + " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        log.log(new LogRecord(Level.INFO, throwable.toString()));
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screen = new File("screen-" + System.currentTimeMillis() + ".png");
        try {
            com.google.common.io.Files.copy(tmp, screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(throwable);
        System.out.println(screen);
    }
}
