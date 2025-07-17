package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inkarto.hooks.Hook;
import com.inkarto.pages.HomePage;
import com.inkarto.pages.WishListItems;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for handling Wishlist-related features on Inkarto site
public class WishListStepDefinition {

	private WebDriver driver; // WebDriver instance for this scenario
	private HomePage hmp; // Page Object Model for home page actions
	private WishListItems wli; // Page Object for wish list interactions

	// Logger for tracking step execution and debugging
	private static final Logger logger = LogManager.getLogger(WishListStepDefinition.class);

	@Given("user on to the Inkarto website")
	public void user_is_on_to_the_inkarto() {
		// Initialize WebDriver and page objects
		driver = Hook.driver;
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}
		hmp = new HomePage(driver);
		wli = new WishListItems(driver);
	}

	@When("user scroll to specialDeals")
	public void user_scroll_to_special_deals() {
		// Scrolls down to 'Special Deals' section on homepage
		hmp.scrolltoSpecialDeals();
	}

	@When("user click on the logo of wishlist")
	public void user_clcik_on_the_logo_of_wishlist() {
		// Clicks product logos to add items to wishlist (max 10 items)
		hmp.addToWishlist(driver);
	}

	@When("user click on wishlist icon on top most right")
	public void user_click_on_wishlist_icon_on_top_most_right() {
		// Opens wish list page by clicking the wish list icon
		hmp.clickWishList();
	}

	@Then("page is redirected to wishlist")
	public void page_is_redirected_to_wishlist() {
		// Verifies navigation to the wish list page by checking page title
		String pageTitle = Hook.driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Wishlist"),
				"Page title doesn't indicate Wishlist. Title was: " + pageTitle);
	}

	@Then("user retrives the list added to Wishlist")
	public void user_retivce_the_list_added_to_wish_lits() {
		// Retrieves list of items in wish list and saves output to file
		wli.retrieveWishList();
		logger.info("Wishlist items retrieved");
	}
}
