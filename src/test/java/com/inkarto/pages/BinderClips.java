// Package for page-related classes
package com.inkarto.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.inkarto.utilities.JsonWriter;
import com.inkarto.utilities.ScreenShot;

// Page Object Model class for handling binder clips filtering and listing
public class BinderClips  extends BasePage{

	
	// JavaScript executor for performing JS-based actions
	private JavascriptExecutor js;

	// Constructor initializes elements using PageFactory
	public BinderClips(WebDriver driver) {
		super(driver);
	}

	// Check box to filter products by "Binder Clips & Pins"
	@FindBy(xpath = "//input[@value='Binder Clips & Pins']")
	WebElement binderClipsCheckBox;

	// List of product name elements on the page
	@FindBy(xpath = "//a[@class='m-product-card__name']")
	List<WebElement> results;

	// Scrolls down the page and clicks the binder clips checkbox using JavaScript
	public void scrollTocheckBoxBinderClips() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"); // Scroll down by 500 pixels
		js.executeScript("arguments[0].click();", binderClipsCheckBox); // Click checkbox via JS
	}

	// Extracts and saves binder clip product names as JSON
	public void printBinderClips() {
		// Wait for results to load (2-second JS a sync delay)
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");

		// Initialize list to store product names
		List<String> binderClips = new ArrayList<>();

		// Iterate over each result element and add its text to the list
		for (WebElement ele : results) {
			binderClips.add(ele.getText());
		}

		// Capture screenshot for visual confirmation
		ScreenShot.screenShotTC(driver, "03_BinderClips");

		// Write the list of product names to a JSON file
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\BinderClips.json", binderClips);
	}
}
