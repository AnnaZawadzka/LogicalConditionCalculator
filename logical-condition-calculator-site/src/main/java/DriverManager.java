package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverManager {
    private static final String DRIVER_LINUX_PATH = "logical-condition-calculator-site/src/main/resources/chromedriver";
    private static final String DRIVER_WINDOWS_PATH = "logical-condition-calculator-site/src/main/resources/chromedriver.exe";
    private static final String DRIVER_PROPERTY_NAME = "webdriver.chrome.driver";
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    public static WebDriver driver;

    private static String getSystem() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static WebDriver setUp() {
        if (getSystem().startsWith("win"))
            System.setProperty(DRIVER_PROPERTY_NAME, DRIVER_WINDOWS_PATH);
        else if (getSystem().contains("nux")) {
            System.setProperty(DRIVER_PROPERTY_NAME, DRIVER_LINUX_PATH);
        } else
            LOGGER.error("Add support to your OS system");
        ChromeOptions options = getChromeOptions();
        driver = new ChromeDriver(options);
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

    public static void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
