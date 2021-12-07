package scenarios;

import main.java.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public abstract class AbstractBaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseTest.class);
    WebDriver driver;

    @BeforeTest
    public void prepareDriver() {
        LOGGER.info("Preparing driver");
        driver = DriverManager.setUp();
    }

    @AfterTest
    public void cleanAfterTest() {
        DriverManager.tearDown();
    }
}
