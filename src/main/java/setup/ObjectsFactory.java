package setup;

import org.databene.feed4testng.FeedTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import java.util.HashMap;
import java.util.Map;

public class ObjectsFactory extends FeedTest {
    protected  static HomePage homePage;

    private static final Map<Class<?>,Object> instances=new HashMap<>();

    protected ObjectsFactory(){
    }

    public  static synchronized <T> T getInstance(WebDriver driver,Class<T> pageClass){
        if (!instances.containsKey(pageClass)){
            T instance = PageFactory.initElements(driver,pageClass);
            instances.put(pageClass,instance);
        }
        return (T) instances.get(pageClass);
    }

    public void initPageObjects(WebDriver driver){

        homePage = ObjectsFactory.getInstance(driver, HomePage.class);
    }
}
