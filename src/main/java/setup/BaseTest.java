package setup;

import config.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

public class BaseTest extends ObjectsFactory {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    @BeforeTest
    @Parameters({"browser", "version", "os"})
    public void setUp(String browser, String version, String os) {
        LOGGER.info("Initializing WebDriver for browser: " + browser + " on thread: " + Thread.currentThread().getId());

        // Load configuration
        ConfigLoader config = new ConfigLoader();
        String gridUrl = config.getProperty("LAMBDAGRID_URL");
        String appUrl = config.getProperty("url");
        String runLocally = config.getProperty("runLocally");

        // Validate configuration
        validateConfiguration(gridUrl, appUrl, runLocally);

        // Initialize WebDriver
        WebDriverManager.initializeDriver(browser, version, os, gridUrl, "true".equalsIgnoreCase(runLocally));

        // Validate WebDriver and initialize page objects
        if (WebDriverManager.getDriver() != null) {
            LOGGER.info("WebDriver initialized successfully for thread: " + Thread.currentThread().getId());
            initPageObjects(WebDriverManager.getDriver());
            getDriver().manage().window().maximize();
            getDriver().get(appUrl);
            LOGGER.info("Navigated to: " + appUrl);
        } else {
            throw new IllegalStateException("WebDriver initialization failed for thread: " + Thread.currentThread().getId());
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.quitDriver();
        LOGGER.info("WebDriver quit successfully for thread: " + Thread.currentThread().getId());
    }

    // Get the driver for the current thread
    public WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    // Validate the configuration properties
    private void validateConfiguration(String gridUrl, String appUrl, String runLocally) {
        if (gridUrl == null || gridUrl.isEmpty()) {
            throw new IllegalArgumentException("LAMBDAGRID_URL is missing in configuration");
        }
        if (appUrl == null || appUrl.isEmpty()) {
            throw new IllegalArgumentException("url is missing in configuration");
        }
        if (runLocally == null || runLocally.isEmpty()) {
            throw new IllegalArgumentException("runLocally is missing in configuration");
        }
    }
}
