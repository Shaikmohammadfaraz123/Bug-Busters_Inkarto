package com.inkarto.utilities;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

// Utility class for setting up and managing WebDriver based on environment configuration
public class DriverSetup {

    // WebDriver instance shared across test execution
    private static WebDriver driver;

    // Excel file and sheet used to fetch URL data
    private static String fileName = "Inkarto.xlsx";
    private static String sheet = "Sheet1";
    private static ExcelUtils exe = new ExcelUtils(fileName);

    // Logger declaration (commented for now; can be enabled if logging is needed)
    // private static final Logger logger = LogManager.getLogger(DriverSetup.class);

    // Default constructor
    public DriverSetup() {}

    /**
     * Initializes WebDriver based on browser and environment (local or remote)
     * @param browser - browser type ("chrome", "edge")
     * @return WebDriver instance
     * @throws MalformedURLException if remote URL is incorrectly formed
     * @throws URISyntaxException if URL contains invalid syntax
     */
    @SuppressWarnings("deprecation")
    public WebDriver driverInstantiate(String browser) throws MalformedURLException, URISyntaxException {

        // Read execution environment and OS from config.properties
        String executionEnv = ConfigReader.getProperty("environment").toLowerCase();
        String os = ConfigReader.getProperty("os").toLowerCase();

        if (executionEnv.equals("remote")) {
            // Setup desired capabilities for remote execution
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Set platform based on config OS
            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported OS: " + os);
            }

            // Set browser name for remote
            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Initialize RemoteWebDriver using Selenium Grid URL
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (executionEnv.equals("local")) {
            // Initialize local browser driver
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        // Common setup: maximize window, clear cookies, set wait, and open URL
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Launch application using URL read from Excel file
        driver.get(exe.getCellData(sheet, 1, 0));

        return driver;
    }

    /**
     * Terminates WebDriver session after test execution
     */
    public void driverTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Optional: Uncomment to enable logging retrieval
    // public static Logger getLogger() {
    //     return logger;
    // }
}
