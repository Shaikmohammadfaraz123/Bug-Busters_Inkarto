// Package declaration for page objects
package com.inkarto.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.inkarto.utilities.JsonWriter;
import com.inkarto.utilities.ScreenShot;

// Page Object class to handle interaction with "Glossy" products on the website
public class GlossyItems extends BasePage{

	// JavaScript executor to perform advanced browser operations
	private JavascriptExecutor js;

	// Constructor initializes page elements with PageFactory
	public GlossyItems(WebDriver driver) {
		super(driver);
	}

	// Locator for the "Load More" button to reveal additional products
	@FindBy(xpath = "//button//span[text()='Load More']")
	WebElement loadMoreBtn;

	// List of product links displayed in the product grid
	@FindBy(xpath = "//div[@id='CollectionProductGrid']//div[contains(@class,'collection-products')]/div//div[contains(@class,'content')]//a")
	List<WebElement> productList;

	// Scrolls down the page to bring the "Load More" button into view
	public void scrollToLoadMore() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", loadMoreBtn);
	}

	// Extracts names of products containing "Glossy", takes a screenshot, and
	// writes them to a JSON file
	public void printListGlossySpray() {
		// Waits 5 seconds to ensure products are loaded (simulates async wait)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

		// Create a list to store product names containing "Glossy"
		List<String> glossyList = new ArrayList<>();
		for (WebElement ele : productList) {
			if (ele.getText().contains("Glossy")) {
				glossyList.add(ele.getText()); // Add matching product name to the list
			}
		}

		// Capture a screenshot of the current page state
		ScreenShot.screenShotTC(driver, "04_GlossySprays");

		// Write the list of glossy product names to a JSON file
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\glossy.json", glossyList);
	}
}
