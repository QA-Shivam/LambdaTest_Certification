package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void initializeDriver(String browser, String version, String os, String gridUrl, boolean runLocally) {
        if (runLocally) {
            if (browser.equalsIgnoreCase("chrome")) {
                setDriver(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("firefox")) {
                setDriver(new FirefoxDriver());
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } else {
            try {
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                    options.setCapability("browserVersion", version);
                    options.setCapability("platformName", os);
                    options.setCapability("lt:options", new HashMap<String, Object>() {{
                        put("build", "LambdaTest-Certification");
                        put("name", "LambdaTest-Certification");
                        put("selenium_version", "4.0.0");
                        put("w3c", true);
                    }});
                    setDriver(new RemoteWebDriver(new URL(gridUrl), options));
                } else if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                    options.setCapability("browserVersion", version);
                    options.setCapability("platformName", os);
                    options.setCapability("lt:options", new HashMap<String, Object>() {{
                        put("build", "LambdaTest-Certification");
                        put("name", "LambdaTest-Certification");
                        put("w3c", true);
                    }});
                    setDriver(new RemoteWebDriver(new URL(gridUrl), options));
                }else if (browser.equalsIgnoreCase("safari")) {
                    SafariOptions options = new SafariOptions();
                    options.setCapability(CapabilityType.BROWSER_NAME, "safari");
                    options.setCapability("browserVersion", version);
                    options.setCapability("platformName", os);
                    options.setCapability("lt:options", new HashMap<String, Object>() {{
                        put("build", "LambdaTest-Certification");
                        put("name", "LambdaTest-Certification");
                        put("w3c", true);
                    }});
                    setDriver(new RemoteWebDriver(new URL(gridUrl), options));
                }
                else {
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } catch (MalformedURLException e) {
                System.out.println("Invalid grid URL: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error initializing WebDriver: " + e.getMessage());
            }
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
