package testcase;

import org.testng.annotations.Test;
import setup.BaseTest;

public class Test3 extends BaseTest {
    @Test
    public void Test_Scenario_3() {
        homePage.clickInputFormSubmit();
        homePage.verifyNavigation("Input Form Demo");
        homePage.clickSubmit();
        homePage.verifyErrorMessage("Please fill out this field.");
        homePage.fillformdetails("John Doe","lambdatest@gmail.com","Test@123","United States","LambdaTest","www.lambdatest.com","San Francisco","123 Main St","Apt 123","California","12345");
        homePage.clickSubmit();
        homePage.verifySubmitMessage("Thanks for contacting us, we will get back to you shortly.");

    }
}
