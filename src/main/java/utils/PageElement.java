package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitType;

public class PageElement {
    private By by;
    private String fieldname;
    private WebElement webElement;
    private int timeOut;
    WaitType waitType;


    public PageElement(By by, String fieldname, WaitType waitType, int timeOut){
        super();
        this.by=by;
        this.fieldname=fieldname;
        this.waitType=waitType;
        this.timeOut=timeOut;
    }
    public String getName(){
        return fieldname;
    }
    public WebElement getWebElement(){
        return  webElement;
    }
    public  By getBy(){
        return by;
    }
    @Override
    public  String toString(){
        return  "PageElement [fieldName="+ fieldname + " ]";
    }
}
