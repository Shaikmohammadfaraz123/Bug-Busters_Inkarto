package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inkarto.hooks.Hook;
import com.inkarto.pages.GlossyItems;
import com.inkarto.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for verifying Glossy spray products on Inkarto site
public class GlossyItemsStepDefinition {

	private WebDriver driver; // WebDriver instance for browser automation
	private HomePage hmp; // HomePage object for common homepage interactions
	private GlossyItems gi; // GlossyItems object for spray product functionality

	// Logger to track step execution info
	private static final Logger logger = LogManager.getLogger(GlossyItemsStepDefinition.class);

	@Given("user landed on Inkarto main page")
	public void user_landed_on_inkarto_main_page() {
		// Retrieve WebDriver from Hooks and ensure it's initialized
		driver = Hook.driver;
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}

		// Initialize page objects
		hmp = new HomePage(driver);
		gi = new GlossyItems(driver);
	}

	@When("user hover mouse on the {string}")
	public void user_hover_mouse_on_the(String string) {
		// Hover mouse over Art Supplies menu (string is unused; consider wiring it
		// dynamically)
		hmp.hoverArtSuppplies();
	}

	@When("hover the mouse on {string}")
	public void hover_the_mouse_on(String string) {
		// Hover mouse over Paints submenu
		hmp.hoverPaintsSubMneu();
	}

	@When("user click on the {string} from dropdown list")
	public void user_click_on_the_from_dropdow_list(String string) {
		// Click the Spray Paints option from dropdown (string currently unused)
		hmp.clickOnSpray();
	}

	@Then("user navigates to page of spray products")
	public void user_navigates_to_page_of_spray_products() {
		// Assert that spray paints section is displayed successfully
		Assert.assertTrue(hmp.displaySparyPaints());
	}

	@Then("retrives the list of sprays containing \"Glossy\"")
	public void retives_the_list_of_spary_conatins_glossy() {
		// Print and save list of products containing "Glossy" keyword
		gi.printListGlossySpray();
		logger.info("GlossyItems have been retrieved");
	}
}
