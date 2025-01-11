package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import setup.PageActions;
import utils.PageElement;
import utils.WaitType;

public class HomePage extends PageActions {
    private PageElement simpleformulr;
    private PageElement entermessage;
    private PageElement getcheckedvalue;
    private PageElement yourmessage;

    private PageElement dragndropslider;
    private PageElement slider3;
    private PageElement slidervalue;
    private PageElement inputformsubmit;
    private PageElement submit;
    private PageElement name;
    private PageElement email;
    private PageElement password;
private PageElement company;
private PageElement website;
private PageElement city;
    private PageElement address1;
    private PageElement address2;
    private PageElement state;
    private PageElement zip;
private PageElement submitmessage;
private PageElement country;
    public HomePage() {

        simpleformulr = new PageElement(By.xpath("//a[contains(@href,'simple-form-demo')]"), "Simple Form Demo Url", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        entermessage = new PageElement(By.id("user-message"), "Enter Your Message Input", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        getcheckedvalue = new PageElement(By.id("showInput"), "Get Checked Value Button", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        yourmessage = new PageElement(By.id("message"), "Your Message Value", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        dragndropslider = new PageElement(By.xpath("//a[contains(@href,'drag-drop-range-slider')]"), "Drag and Drop Url", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        slider3 = new PageElement(By.cssSelector("div#slider3"), "Slider 3", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        slidervalue = new PageElement(By.cssSelector("div#slider3>div>output"), "Slider Value", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        inputformsubmit = new PageElement(By.xpath("//a[contains(@href,'input-form-demo')]"), "Input Form Submit", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        submit = new PageElement(By.cssSelector("#seleniumform > div.text-right.mt-20 > button"), "Submit Button", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        name= new PageElement(By.id("name"), "Name", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        email= new PageElement(By.cssSelector("#inputEmail4"), "Email", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        password= new PageElement(By.cssSelector("#inputPassword4"), "Password", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        company= new PageElement(By.xpath("//*[@id='company']"), "Company", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        website= new PageElement(By.cssSelector("#websitename"), "Website", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        city= new PageElement(By.id("inputCity"), "City", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        address1= new PageElement(By.xpath("//*[@id='inputAddress1']"), "Address1", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        address2= new PageElement(By.cssSelector("#inputAddress2"), "Address2", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        state= new PageElement(By.id("inputState"), "State", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        zip= new PageElement(By.xpath("//*[@id='inputZip']"), "Zip", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        submitmessage= new PageElement(By.xpath("//p[@class='success-msg hidden']"), "Submit Message", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
        country= new PageElement(By.xpath("//select[@name='country']"), "Country", WaitType.WAITFORELEMENTTOBEDISPLAED, 5);
    }

    public void clickSimpleFormDemo(){
    waitForElement(simpleformulr,3);
    click(simpleformulr);
    System.out.println("Clicked on: "+simpleformulr.getName());
    }
    public void verifyNavigation(String pageName){
        String url= getDriver().getCurrentUrl();
        if(url.contains(pageName.toLowerCase().replace(" ","-"))){
            System.out.println("Navigation is successful to "+pageName);
    }else {
            System.out.println("Navigation is not successful");
        }
    }
    public void enetrMessage(String message){
        waitForElement(entermessage,3);
        sendKeys(entermessage,message);
        System.out.println("Entered message: "+message);
    }
    public void clickgetCheckedValue(){
        waitForElement(getcheckedvalue,3);
        click(getcheckedvalue);
        System.out.println("Clicked on: "+getcheckedvalue.getName());
    }
    public void verifyMessage(String message){
        waitForElement(yourmessage,3);
        System.out.println("Your Message is: "+getText(yourmessage));
        Assert.assertEquals(getText(yourmessage),message,"Message is not matching");
    }
    public void clickDragnDropSliders(){
        waitForElement(dragndropslider,3);
        click(dragndropslider);
        System.out.println("Clicked on: "+dragndropslider.getName());
    }
    public void setSliderValue(String value) throws InterruptedException {
        WebElement DEF = getWebElement(slidervalue);
        String Def = DEF.getText();
        WebElement slider = getWebElement(slider3);

        while (!Def.matches(value)) {
            Actions builder = new Actions(getDriver());
            Action dragAndDrop = builder.clickAndHold(slider)
                    .moveToElement(slider)
                    .release(slider)
                    .build();
            dragAndDrop.perform();
            Def = DEF.getText();
            Thread.sleep(2000);
        }
    }

    public void clickInputFormSubmit(){
        waitForElement(inputformsubmit,3);
        click(inputformsubmit);
        System.out.println("Clicked on: "+inputformsubmit.getName());
    }
    public void clickSubmit(){
        waitForElement(submit,3);
        click(submit);
        System.out.println("Clicked on: "+submit.getName());
    }
    public void verifyErrorMessage(String message){
        waitForElement(name,5);
        WebElement Name = getWebElement(name);
        String pleasefilloutthisform = Name.getDomAttribute("required");
        Assert.assertTrue(true,pleasefilloutthisform);
        System.out.println("Validation message success: " + message);
    }
    public void fillformdetails(String name, String email, String password,String country, String company, String website, String city, String address1, String address2, String state, String zip){
        waitForElement(this.name,3);
        sendKeys(this.name,name);
        System.out.println("Entered name: "+name);
        sendKeys(this.email,email);
        System.out.println("Entered email: "+email);
        sendKeys(this.password,password);
        System.out.println("Entered password: "+password);
        sendKeys(this.company,company);
        System.out.println("Entered company: "+company);
        Select dropdown = new Select(getWebElement(this.country));
        dropdown.selectByVisibleText(country);
        System.out.println("Entered country: "+country);
        sendKeys(this.website,website);
        System.out.println("Entered website: "+website);
        sendKeys(this.city,city);
        System.out.println("Entered city: "+city);
        sendKeys(this.address1,address1);
        System.out.println("Entered address1: "+address1);
        sendKeys(this.address2,address2);
        System.out.println("Entered address2: "+address2);
        sendKeys(this.state,state);
        System.out.println("Entered state: "+state);
        sendKeys(this.zip,zip);
        System.out.println("Entered zip: "+zip);
    }
    public void verifySubmitMessage(String message){
        waitForElement(submitmessage,3);
        String submitmessagetxt = getText(submitmessage);
        System.out.println("Submit message: "+submitmessagetxt);
        Assert.assertEquals(submitmessagetxt,message,"Message is not matching");
    }
}
