package com.inkarto.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


import com.inkarto.utilities.ConfigReader;
import com.inkarto.utilities.JsonWriter;
import com.inkarto.utilities.ScreenShot;

// Page Object Model class for interacting with Lippan Art page
public class LippanArt extends BasePage{
	
	private JavascriptExecutor js;
	private Actions act;

	// Constructor to initialize WebDriver and PageFactory
	public LippanArt(WebDriver driver) {
		super(driver);
	}

	// ===== Web Elements =====
	@FindBy(xpath = "//a[@class='m-product-card__name']")
	List<WebElement> results; // List of product names

	@FindBy(xpath = "//label[@for='Filter-Availability-1']//span[@class='m-facet--product-count']")
	WebElement stockAvaliable; // Stock availability indicator

	@FindBy(xpath = "//price-range//input[@name='filter.v.price.gte']")
	WebElement minPrice; // Minimum price field

	@FindBy(xpath = "//price-range//input[@name='filter.v.price.lte']")
	WebElement maxPrice; // Maximum price field

	@FindBy(xpath = "//span[text()='Filter']//parent::button")
	WebElement edgeFilter; // Filter button (Edge)

	@FindBy(xpath = "//div[contains(@class,'m-sidebar--close')]")
	WebElement filterClose; // Filter sidebar close button

	@FindBy(xpath = "//button[@name='commit']")
	WebElement subscribe; // Subscribe button (unused in this context)

	// ===== Methods =====

	// Scrolls to the stock availability filter on the page
	public void scrollToFilter() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
		act = new Actions(driver);
		act.scrollToElement(stockAvaliable).perform();
	}

	// Prints availability status to console
	public void checkAvailability() {
		if (stockAvaliable.isEnabled()) {
			System.out.println("Stock is Available");
		}
	}

	// Applies price filters via Chrome-style interaction
	public void filterClickChrome() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");

		act = new Actions(driver);
		act.scrollToElement(stockAvaliable).perform();

		if (stockAvaliable.isEnabled()) {
			System.out.println("Stock is Available");
		}

		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
		minPrice.sendKeys(ConfigReader.getProperty("minprice"));
		maxPrice.sendKeys(ConfigReader.getProperty("maxprice"));
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	}

	// Applies filters and closes sidebar for Edge browser scenario
	public void filterClickEdge() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		js.executeScript("arguments[0].click()", edgeFilter);

		checkAvailability();

		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
		minPrice.sendKeys(ConfigReader.getProperty("minprice"));
		maxPrice.sendKeys(ConfigReader.getProperty("maxprice"));
		filterClose.click();
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	}

	// Sets price range based on config values
	public void setPrice() {
		js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");

		minPrice.sendKeys(ConfigReader.getProperty("minprice"));
		maxPrice.sendKeys(ConfigReader.getProperty("maxprice"));

		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	}

	// Prints all visible Lippan Art product names and stores them in a JSON file
	public void printLippenArtProduct() {
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		List<String> lippenArtList = new ArrayList<>();
		for (WebElement ele : results) {
			lippenArtList.add(ele.getText());
		}

		// Capture screenshot and write list to JSON file
		ScreenShot.screenShotTC(driver, "05_LippanArts");
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\LippenArt.json", lippenArtList);
	}
}
