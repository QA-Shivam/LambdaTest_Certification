package testcase;

import org.testng.annotations.Test;
import setup.BaseTest;


public class Test1 extends BaseTest {
@Test
    public void Test_Scenario_1(){
    homePage.clickSimpleFormDemo();
    homePage.verifyNavigation("simple-form-demo");
    homePage.enetrMessage("Welcome to LambdaTest");
    homePage.clickgetCheckedValue();
    homePage.verifyMessage("Welcome to LambdaTest");
}
}
