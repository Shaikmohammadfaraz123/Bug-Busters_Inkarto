package com.inkarto.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inkarto.hooks.Hook;
import com.inkarto.pages.HomePage;
import com.inkarto.pages.URLsPage;
import com.inkarto.utilities.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Cucumber step definitions for home page interactions
public class HomePageStepDefinition {

	private WebDriver driver;
	private HomePage hmp;
	private URLsPage up;

	// Logger to track test execution in logs
	private static final Logger logger = LogManager.getLogger(HomePageStepDefinition.class);

	@Given("user is on to the inkarto")
	public void user_is_on_to_the_inkarto() {
		// Initialize WebDriver and page objects
		driver = Hook.driver;
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		}
		hmp = new HomePage(driver);
		up = new URLsPage(driver);
	}

	@When("user see Followers of website on top")
	public void user_see_followers_of_website_on_top() {
		// Check if follower section is visible
		Assert.assertTrue(hmp.display());
	}

	@Then("Retrieve the number of followers")
	public void retrive_the_number_of_followers() {
		// Capture and save follower data (Instagram, Facebook, Rating)
		hmp.getFollowers();
		logger.info("Followers retrieved");
	}

	@When("user hover mouse on to stationary")
	public void user_hover_mouse_on_to_stationary() {
		// Hover on 'Stationery' main menu category
		hmp.mousehover();
	}

	@When("user hover the mouse to subMenu stickyNotes")
	public void user_hover_the_mouse_to_sub_menu_sticky_notes() {
		// Hover on submenu item 'Sticky Notes'
		hmp.subMenuHover();
	}

	@Then("user retrive the list of notes")
	public void user_retrive_the_list_of_notes() {
		// Print all sticky note items and take screenshot
		hmp.printList();
	}

	@When("user hover mouse on to Craft Material")
	public void user_hover_mouse_on_to_craft_materail() {
		// Hover over 'Craft Material' category
		hmp.craftMouseHover();
	}

	@When("user goes to submenu hover mouse to paper")
	public void user_goes_to_submenu_hover_mouse_to_paper() {
		// Hover over 'Papers' submenu under craft materials
		hmp.craftSubMenu();
	}

	@Then("user check whether QuillingPaper display or not")
	public void user_check_weather_quilling_paper_dispaly_or_not() {
		// Verifies presence of 'Quilling Paper' item and logs status
		hmp.quillingPaperPresent();
	}

	@When("user scroll down to footer connect with us")
	public void user_scroll_down_to_footer_connectt_with_us() {
		// Scroll to 'Connect With Us' section
		hmp.scrollToConnect();
	}

	@Then("retrive the data of connect with us")
	public void retrive_the_data_of_connect_with_us() {
		// Capture brand and contact details into JSON file
		hmp.printDetails();
		logger.info("Connect With Us Details Retrieved");
	}

	@When("user scroll down to SupportInkarto")
	public void user_scroll_down_to_support_inkarto() {
		// Scroll to social media section (Support Inkarto)
		up.scrollForSM(driver);
	}

	@Then("retrive the Hyperlinks of the website")
	public void retrive_the_hyperlinks_of_the_website() {
		// Capture all social media links and write to Excel
		up.gettingAllSocialMediaURLs(driver);
	}

	@When("user scroll down to letsGet in touch")
	public void user_scroll_down_to_lets_get_in_touch() {
		// Scroll to newsletter subscription section and take screenshot
		hmp.scrollToLetsGetInTouch();
		ScreenShot.screenShotTC(driver, "11_GetInTouch");
	}

	@When("user give wrong credential")
	public void user_give_wroong_credential() {
		// Input invalid email from config (used for testing error message)
		hmp.enterEmail();
	}

	@When("click on the subscribe")
	public void click_on_the_subscribe() {
		// Click subscribe button
		hmp.clickSubscribe();
	}

	@Then("user will get the error message")
	public void user_will_get_the_erros_message() {
		// Validates error or success message and logs outcome
		hmp.errorMessageSbuscribe();
	}
}
