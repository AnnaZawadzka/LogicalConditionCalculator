package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverManager {
    private static final String DRIVER_LINUX_PATH = "src/main/resources/chromedriver";
    private static final String DRIVER_WINDOWS_PATH = "src/main/resources/chromedriver.exe";
    private static final String DRIVER_PROPERTY_NAME = "webdriver.chrome.driver";

    private DriverManager(){};

    private static class LazyHolder {
        public static final WebDriver DRIVER = setUpDriver();
    }

    public static WebDriver getDriver() {
        return LazyHolder.DRIVER;
    }

    private static WebDriver setUpDriver() {
        setDriverPathForOS();
        return new ChromeDriver(getChromeOptions());
    }

    private static String getSystem() {
        return System.getProperty("os.name").toLowerCase();
    }

    private static void setDriverPathForOS() {
        String driverPath;
        if (getSystem().startsWith("win")) driverPath = DRIVER_WINDOWS_PATH;
        else if (getSystem().contains("nux")) driverPath = DRIVER_LINUX_PATH;
        else throw new IllegalStateException("Add support to your OS system");
        System.setProperty(DRIVER_PROPERTY_NAME, driverPath);
    }

    private static ChromeOptions getChromeOptions() {
        return new ChromeOptions().addArguments("start-maximized");
    }

    public static void tearDown() {
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
    }
}
