package scenarios;

import main.java.helpers.WaitHelper;
import main.java.pageobjects.LogicalCalculatorPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;


public class HomepageViewTest extends AbstractBaseTest {

    @Test()
    public void hasOpenedPage() {
        driver.get("https://dwi80qdh684no.cloudfront.net/index.v5.html");
        var calculator = new LogicalCalculatorPage();
        WaitHelper.waitForPageToLoad();
        assertThat(calculator.getHeaderText()).isEqualTo("Logial Condition Calculator");
    }

    @Test()
    public void squareDeltaEmptyGroup() {
        //Given I have an empty set
        //And I have not empty set
        //When I calculate delta from that groups
        //Then Results should contain all numbers from not empty set
        driver.get("https://dwi80qdh684no.cloudfront.net/index.v5.html");
        WaitHelper.waitForPageToLoad();
        var calculator = new LogicalCalculatorPage();
        var expectedNumbers = calculator.getNumbersFromGroup("square");
        System.out.println(expectedNumbers);
        var resultsNumbers = calculator
                .clearQueryWindow()
                .clickUseGroupByName("square")
                .clickDifference()
                .clickUseGroupByName("empty")
                .clickCalculate()
                .getResults();
        System.out.println(resultsNumbers);
        assertThat(resultsNumbers).containsExactlyInAnyOrderElementsOf(expectedNumbers);
    }

}
