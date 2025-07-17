package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inkarto.pages.HomePage;
import com.inkarto.utilities.ScreenShot;
import com.inkarto.hooks.Hook;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definition class for handling login and registration-related actions on Inkarto
public class LoginStepDefinition {

	private WebDriver driver; // WebDriver instance for the current scenario
	private HomePage hmp; // Page object to interact with homepage elements

	// Logger for recording events during execution
	private static final Logger logger = LogManager.getLogger(LoginStepDefinition.class);

	@Given("the user is on the Inkarto account logo")
	public void the_user_is_on_the_inkarto_account_logo() {
		// Get WebDriver instance from Hooks
		driver = Hook.driver;
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}

		// Initialize page objects
		hmp = new HomePage(driver);

		// Capture screenshot of the home page
		ScreenShot.screenShotTC(driver, "01_HomePage");

		// Click on account logo to begin registration flow
		hmp.clickAccountLogo();
	}

	@When("user scroll upto Register button")
	public void user_scroll_upto_register_button() {
		// Scroll down to the registration button
		hmp.scrollToRegBtn();
	}

	@When("click on register")
	public void click_on_register() {
		// Click the register initiation button
		hmp.clickOnRegBtn();
	}

	@When("user enters valid credentials from {string}")
	public void user_enters_valid_credentails(String source) {
		// Enter registration credentials from config file or data source
		hmp.enterCredentials();

		// Take screenshot of filled registration form
		ScreenShot.screenShotTC(driver, "02_RegistrationPage");
	}

	@When("click on register button")
	public void click_on_register_button() {
		// Click the final register submission button
		hmp.clickRegisterBtn();
	}

	@Then("home page should be display")
	public void home_page_should_be_display() {
		// Validate that user has landed on expected home page after registration
		String actualTitle = driver.getTitle();
		System.out.println("Page title after registration: " + actualTitle);
		logger.info("Logged Into Home Page");

		// Assert that title contains expected keywords
		Assert.assertTrue(actualTitle.contains("Inkarto") || actualTitle.contains("Welcome"),
				"Expected home page title but got: " + actualTitle);
	}

	@Then("user already Exist")
	public void user_already_exist() {
		// Assert that browser remains on registration page for an already registered
		// user
		String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.toLowerCase().contains("register"),
				"Expected to stay on registration page but got: " + actualTitle);
	}

	@Then("user get the error message")
	public void user_get_the_error_message() {
		// Retrieve and validate error message displayed during registration
		String errorMsg = hmp.getErrorMessage();
		System.out.println("Error message: " + errorMsg);

		// Check that the error message is not empty
		Assert.assertTrue(errorMsg.length() > 0, "Expected error message but found none.");
	}
}
