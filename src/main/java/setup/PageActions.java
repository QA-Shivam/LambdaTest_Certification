package setup;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import utils.PageElement;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class PageActions extends BaseTest {
    private static final Logger LOGGER = Logger.getLogger(PageActions.class);
    public String pageName;

    public PageActions(WebDriver driver) {
        super();
    }

    public PageActions() {
    }

    public WebElement getWebElement(PageElement pageElement) {
        try {
            LOGGER.info("Getting WebDriver for thread: " + Thread.currentThread().getId());
            return getDriver().findElement(pageElement.getBy());
        } catch (Exception e) {
            LOGGER.error("Failed to locate element: " + pageElement.getName() + ". Exception: " + e.getMessage());
            return null;
        }
    }

    // Wait for the visibility of an element
    public boolean waitForElement(PageElement pageElement, int timeOutPeriod) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutPeriod));
        try {
            WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageElement.getBy()));
            return true;
        } catch (NoSuchElementException nex) {
            LOGGER.error("Element not found: " + pageElement.getName() + ". Exception: " + nex.getMessage());
        } catch (StaleElementReferenceException see) {
            LOGGER.error("Stale element: " + pageElement.getName() + ". Exception: " + see.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error waiting for element: " + pageElement.getName() + ". Exception: " + e.getMessage());
        }
        return false;
    }

    // Check if WebElement is available in PageElement
    public boolean isWebElementAvailableInPageElement(PageElement pageElement) {
        return pageElement.getWebElement() != null;
    }

    // Send keys to an element
    public void sendKeys(PageElement pageElement, String value) {
        try {
            value = (value == null) ? "" : value;

            WebElement element = isWebElementAvailableInPageElement(pageElement)
                    ? pageElement.getWebElement()
                    : getWebElement(pageElement);

            if (element != null) {
                element.sendKeys(value);
                LOGGER.info("Typed value: \"" + value + "\" into element: " + pageElement.getName());
            } else {
                throw new NoSuchElementException("Element not found: " + pageElement.getName());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to type value into element: " + pageElement.getName() + ". Exception: " + e.getMessage());
        }
    }

    // Click on an element
    protected void click(PageElement pageElement) {
        try {
            WebElement element = isWebElementAvailableInPageElement(pageElement)
                    ? pageElement.getWebElement()
                    : getWebElement(pageElement);

            if (element != null) {
                element.click();
                String timestamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                LOGGER.info("Clicked on element: " + pageElement.getName() + " at " + timestamp);
            } else {
                throw new NoSuchElementException("Element not found: " + pageElement.getName());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to click on element: " + pageElement.getName() + " on page: " + pageName + ". Exception: " + e.getMessage());
        }
    }
    public String getText(PageElement pageElement) {
        try {
            WebElement element = isWebElementAvailableInPageElement(pageElement) ? pageElement.getWebElement() : getWebElement(pageElement);
            if (element != null) {
                String text = element.getText();
                LOGGER.info("Retrieved text: \"" + text + "\" from element: " + pageElement.getName());
                return text;
            } else {
                throw new NoSuchElementException("Element not found: " + pageElement.getName());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve text from element: " + pageElement.getName() + ". Exception: " + e.getMessage());
            return null;
        }
    }
}
