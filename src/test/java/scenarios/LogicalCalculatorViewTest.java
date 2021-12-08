package scenarios;

import org.testng.annotations.Test;
import pageobjects.LogicalCalculatorPage;

import static org.assertj.core.api.Assertions.assertThat;


public class LogicalCalculatorViewTest extends AbstractBaseTest {

    @Test()
    public void hasOpenedPage() {
        var calculator = new LogicalCalculatorPage();
        assertThat(calculator.getHeaderText())
                .as("There is typo in title:)")
                .isEqualTo("Logical Condition Calculator");
    }

    @Test()
    public void squareDeltaEmptyGroup() {
        //Given I have an empty set
        //And I have not empty set
        //When I calculate delta from that groups
        //Then Results should contain all numbers from not empty set
        var calculator = new LogicalCalculatorPage();
        var expectedNumbers = calculator.getNumbersFromGroup("square");
        var resultsNumbers = calculator
                .clearQueryWindow()
                .clickUseGroupByName("square")
                .clickDifference()
                .clickUseGroupByName("empty")
                .clickCalculate()
                .getResults();
        assertThat(resultsNumbers).containsExactlyInAnyOrderElementsOf(expectedNumbers);
    }

}
