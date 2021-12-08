package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static helpers.WaitHelper.waitForElementToBeVisible;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class LogicalCalculatorPage extends AbstractBasePage {
    @FindBy(id = "Query")
    private WebElement queryWindow;

    @FindBy(id = "Calculate")
    private WebElement calculateBtn;

    @FindBy(id = "Difference")
    private WebElement differenceBtn;

    @FindBy(css = ".alert-success#Result")
    private WebElement resultsPrompt;

    @FindBy(css = ".list-group-item")
    private List<WebElement> userGroups;

    @FindBy(css = ".card-title")
    private WebElement siteHeader;

    private final By groupNameButton = By.className("btn");
    private static final int GROUP_NAME_INDEX_ORDER = 1;


    public String getHeaderText() {
        return siteHeader.getText();
    }

    public LogicalCalculatorPage clearQueryWindow() {
        queryWindow.clear();
        return this;
    }

    public LogicalCalculatorPage clickUseGroupByName(String name) {
        getGroupByName(name)
                .findElement(groupNameButton)
                .click();
        return this;
    }

    private WebElement getGroupByName(String name) {
        return userGroups.stream()
                .filter(group -> group
                        .findElement(groupNameButton)
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
        waitForElementToBeVisible(resultsPrompt);
        return asList(resultsPrompt.getText().split(","));
    }

    public List<String> getNumbersFromGroup(String name) {
        return stream(getGroupByName(name).getText()
                .replaceAll(",", "")
                .split(" "))
                .skip(GROUP_NAME_INDEX_ORDER)
                .collect(Collectors.toList());
    }
}
