package testcase;

import org.testng.annotations.Test;
import setup.BaseTest;


public class Test2 extends BaseTest {

    @Test
    public void Test_Scenario_2() throws InterruptedException {
        homePage.clickDragnDropSliders();
        homePage.verifyNavigation("drag-drop-range-slider");
        homePage.setSliderValue("95");
    }
}
