package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inkarto.hooks.Hook;
import com.inkarto.pages.BinderClips;
import com.inkarto.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definition class for Cucumber scenario: Verifying Binder Clips functionality
public class BinderClipsStepDefinition {

	private WebDriver driver; // WebDriver instance used across steps
	private HomePage hmp; // Home page object to access search methods
	private BinderClips bc; // Page object for Binder Clips functionality

	// Logger to log status and results during step execution
	private static final Logger logger = LogManager.getLogger(BinderClipsStepDefinition.class);

	@Given("user is on to the Inkarto Page")
	public void user_is_on_to_the_inkarto_Page() {
		// Get driver instance from Hook setup
		driver = Hook.driver;

		// Fail early if driver is not initialized correctly
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}

		// Initialize Page Object Models
		hmp = new HomePage(driver);
		bc = new BinderClips(driver);
	}

	@When("user click on search Icon")
	public void user_click_on_search_icon() {
		// Trigger search button click on home page
		hmp.clickOnsearch();
	}

	@When("user search for {string}")
	public void user_search_for(String string) {
		// Searches for product using Excel test data (not using passed string directly)
		hmp.searchForData(); // Consider updating this to use `string` if required
	}

	@Then("user redirect to page of products under {string}")
	public void user_redirect_to_page_of_product_under(String string) {
		// Validates that product display section is visible
		Assert.assertTrue(hmp.isDisplayfiles());
	}

	@When("user scroll to select checkbox {string}")
	public void user_scroll_to_select_checkbox(String string) {
		// Scrolls to the checkbox element for Binder Clips filter
		bc.scrollTocheckBoxBinderClips();
	}

	@Then("user retrieves the list of BinderClips")
	public void user_retrive_the_list_of_product_binder_clips() {
		// Prints the list of product names under Binder Clips category
		bc.printBinderClips();
		logger.info("BinderClips have been retrieved");
	}
}
