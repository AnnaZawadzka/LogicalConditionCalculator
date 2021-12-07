package main.java.pageobjects;

import main.java.helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LogicalCalculatorPage extends AbstractBasePage {
    @FindBy(id = "Query")
    private WebElement queryWindow;

    @FindBy(id = "Calculate")
    private WebElement calculateBtn;

    @FindBy(id = "Union")
    private WebElement unionBtn;

    @FindBy(id = "Intersection")
    private WebElement intersectionBtn;

    @FindBy(id = "Difference")
    private WebElement differenceBtn;

    @FindBy(css = ".alert-success#Result")
    private WebElement resultsPrompt;

    @FindBy(css = ".list-group-item")
    private List<WebElement> userGroups;

    @FindBy(css = ".card-title")
    private WebElement siteHeader;

    public String getHeaderText() {
        return siteHeader.getText();
    }

    public LogicalCalculatorPage clearQueryWindow() {
        queryWindow.clear();
        return this;
    }

    public LogicalCalculatorPage clickUseGroupByName(String name) {
        getGroupByName(name).findElement(By.className("btn"))
                .click();
        return this;
    }

    private WebElement getGroupByName(String name) {
        return userGroups.stream()
                .filter(group -> group
                        .findElement(By.className("btn"))
                        .getText()
                        .contains(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public LogicalCalculatorPage clickDifference() {
        differenceBtn.click();
        return this;
    }

    public LogicalCalculatorPage clickCalculate() {
        calculateBtn.click();
        return this;
    }

    public List<String> getResults() {
        WaitHelper.waitForElementToBeVisible(resultsPrompt);
        return Arrays.asList(resultsPrompt.getText().split(","));
    }

    public List<String> getNumbersFromGroup(String name) {
        return Arrays.stream(getGroupByName(name).getText().replaceAll(",", "").split(" ")).skip(GROUP_NAME_INDEX_ORDER).collect(Collectors.toList());
    }

    private static final int GROUP_NAME_INDEX_ORDER = 1;
}
