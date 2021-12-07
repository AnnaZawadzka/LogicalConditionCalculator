package main.java.pageobjects;

import main.java.DriverManager;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class AbstractBasePage {
    public AbstractBasePage() {
        initElements(DriverManager.driver, this);
    }
}
