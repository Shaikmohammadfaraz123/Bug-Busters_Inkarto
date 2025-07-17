package com.inkarto.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.inkarto.utilities.JsonWriter;
import com.inkarto.utilities.ScreenShot;

// Page Object Model class to handle wishlist operations
public class WishListItems extends BasePage{



	// Constructor initializes the WebDriver and PageFactory
	public WishListItems(WebDriver driver) {
		super(driver);
	}

	// Locator for all product names in the wishlist
	@FindBy(xpath = "//div[@class='m-product-card__info']//a[@class='m-product-card__name']")
	List<WebElement> itemsOfWishList;

	// Retrieves wishlist item names and saves to JSON file
	public void retrieveWishList() {
		// Scroll to the subscribe button to ensure wishlist items are in view
		WebElement sub = driver.findElement(By.xpath("//button[@name='commit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", sub);

		// Flag to exit loop after collecting items
		boolean click = false;

		// List to store item names
		List<String> wishlist = new ArrayList<>();

		// Collects wishlist items by text and breaks once at least one item is found
		while (true) {
			for (WebElement ele : itemsOfWishList) {
				wishlist.add(ele.getText()); // Add item name to list
				click = true;
			}
			if (click) {
				break; // Exit once collection is successful
			}
		}

		// Write wishlist items to a JSON file
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\wishList.json", wishlist);

		// Capture screenshot of the wishlist page
		ScreenShot.screenShotTC(driver, "10_WishListPage");
	}
}
