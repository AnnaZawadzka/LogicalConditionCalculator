package scenarios;

import driver.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static helpers.WaitHelper.waitForPageToLoad;


public abstract class AbstractBaseTest {
    private final String PAGE_URL = "https://dwi80qdh684no.cloudfront.net/index.v5.html";

    @BeforeTest
    public void openPage() {
        DriverManager.getDriver().get(PAGE_URL);
        waitForPageToLoad();
    }

    @AfterTest
    public void cleanAfterTest() {
        DriverManager.tearDown();
    }
}
