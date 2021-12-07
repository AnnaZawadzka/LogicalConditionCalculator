package main.java.helpers;

import main.java.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WaitHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);

    private WaitHelper() {
    }

    public static void waitForPageToLoad() {
        waitForPageToLoad(Duration.ofSeconds(10));
    }

    public static void waitForPageToLoad(Duration timeout) {
        LOGGER.info("Wait for page to load...");
        ExpectedCondition<Boolean> expectedCondition = wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
        condition(expectedCondition, timeout);
    }

    public static void waitForElementToBeVisible(WebElement element) {
        condition(visibilityOf(element), Duration.ofSeconds(1));
        LOGGER.info("Waiting for element to be visible " + element);
    }

    public static void condition(ExpectedCondition<?> condition, Duration timeoutDuration) {
        condition(condition, timeoutDuration, Duration.ofMillis(500));
    }

    public static void condition(ExpectedCondition<?> condition, Duration timeoutDuration, Duration pollingDuration) {
        FluentWait wait = new FluentWait(DriverManager.driver)
                .withTimeout(timeoutDuration)
                .pollingEvery(pollingDuration)
                .ignoring(NoSuchElementException.class);
        wait.until(condition);
    }
}
