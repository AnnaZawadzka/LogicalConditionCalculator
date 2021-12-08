package pageobjects;

import driver.DriverManager;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class AbstractBasePage {
    public AbstractBasePage() {
        initElements(DriverManager.getDriver(), this);
    }
}
