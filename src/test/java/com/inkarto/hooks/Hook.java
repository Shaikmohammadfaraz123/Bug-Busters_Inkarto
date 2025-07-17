// Package declaration for hook-related logic
package com.inkarto.hooks;

// Required imports for browser setup, logging, screenshot, and reporting
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.inkarto.utilities.DriverSetup;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

// Hook class for managing WebDriver life cycle and logging during test execution
public class Hook {

	// Shared WebDriver instance
	public static WebDriver driver;

	// Instance of DriverSetup utility class for browser setup/teardown
	static DriverSetup setup;

	// Browser type to be launched
	public static String browser;

	// Logger for debugging and tracking
	public Logger logger;

	// Method to run before each scenario
	@Before
	public void setUp(Scenario scenario) throws MalformedURLException, URISyntaxException {
		// Initialize logger for this class
		logger = LogManager.getLogger(this.getClass());

		// Retrieve the browser parameter from TestNG configuration
		browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");

		// Initialize DriverSetup to launch browser
		setup = new DriverSetup();
		try {
			driver = setup.driverInstantiate(browser); // Launch the browser
		} catch (Exception e) {
			System.out.println("WebDriver initialization failed: " + e.getMessage());
			throw new RuntimeException("WebDriver setup failed", e);
		}
	}

	// Method to run after each scenario
	@After
	public void tearDown(Scenario scenario) {
		// If the test scenario failed, take a screenshot and attach to Allure report
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
		}

		// Tear down the WebDriver instance to close browser
		if (driver != null) {
			setup.driverTearDown();
		}
	}
}
