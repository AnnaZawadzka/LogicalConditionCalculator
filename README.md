# Logical Condition Calculator
Logical Condition Calculator project is a framework based on Selenium and  Test NG

To open the project you need to have installed: 
1. Git 
2. Maven
3. JDK 1.11
4. Chrome driver need to be added to locally to path: 

   **logical-condition-calculator-site/src/main/resources**


5. Chrome Browser relevant to downloaded drivers

**To run the tests:** 
1. Clone repository
2. install project using command mvn install
3. To run specific test from cdm use command:

a) mvn clean test -Dsurefire.suiteXmlFiles=[test path]

example:
mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/resources/LogicalCalculatorViewTestsRunner.xml 

b) mvn test

c) running test using xml file runners 

d) using class