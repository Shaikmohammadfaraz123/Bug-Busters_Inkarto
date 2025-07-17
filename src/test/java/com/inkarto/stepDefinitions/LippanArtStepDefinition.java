package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.inkarto.hooks.Hook;
import com.inkarto.pages.HomePage;
import com.inkarto.pages.LippanArt;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step Definitions for validating Lippan Art search and filter functionality on Inkarto website
public class LippanArtStepDefinition {

	private WebDriver driver; // WebDriver for browser control
	private HomePage hmp; // HomePage object to access shared methods
	private LippanArt la; // Page Object for Lippan Art-specific functions

	// Fetch browser name dynamically via TestNG Reporter (used for conditional
	// logic)
	private String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("browser");

	// Logger instance for logging output
	private static final Logger logger = LogManager.getLogger(LippanArtStepDefinition.class);

	@Given("the user open the Inkarto Website")
	public void user_is_on_to_the_inkarto_Page() {
		// Initialize WebDriver from Hook and ensure it's valid
		driver = Hook.driver;
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}

		// Instantiate HomePage and LippanArt page objects
		hmp = new HomePage(driver);
		la = new LippanArt(driver);
	}

	@When("user click on search icon")
	public void user_click_on_search_icon() {
		// Trigger search button click on home page
		hmp.clickOnsearch();
	}

	@When("user  enter the {string} in search text box")
	public void user_enter_the_in_search_text_box(String string) {
		// Enter search term into textbox
		// Note: Currently using test data via HomePage method, not `string` directly
		hmp.sendDataToSearch(); // Consider modifying to accept the passed string for dynamic input
	}

	@Then("the user see the products of {string}")
	public void the_user_see_the_products_of(String productKeyword) {
		// Validate that Lippan Art product results are visible
		Assert.assertTrue(hmp.isDisplayLippanArt());

		// Apply filter based on browser type
		if (browser.equalsIgnoreCase("chrome")) {
			la.filterClickChrome();
		} else {
			la.filterClickEdge();
		}
	}

	@Then("user retrieves the list of products based on conditions above")
	public void user_retives_the_list_of_products_based_on_conditions_above() {
		// Extract and save product names to JSON
		la.printLippenArtProduct();
		logger.info("LippanArts have been retrieved");
	}
}
