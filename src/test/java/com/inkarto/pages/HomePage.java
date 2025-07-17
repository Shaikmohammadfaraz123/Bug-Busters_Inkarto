package com.inkarto.pages;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.inkarto.utilities.ConfigReader;
import com.inkarto.utilities.ExcelUtils;
import com.inkarto.utilities.JsonWriter;
import com.inkarto.utilities.ScreenShot;

public class HomePage extends BasePage{
	private String sheet = "Sheet1";
	private ExcelUtils exe = new ExcelUtils("Inkarto.xlsx");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[@aria-label='Search']")
	WebElement searchButton;
	@FindBy(xpath = "//input[@aria-label='Search products' and @type='search']")
	WebElement searchBar;
	@FindBy(xpath = "//div[@class='m:flex']//button[ @type='submit']")
	WebElement searchIcon;
	@FindBy(xpath = "//form[@action='/search']/button[@class='m-search--form-button']")
	WebElement searchBtnLipen;
	@FindBy(xpath = "//ul[@class='m-menu']/li//a[@class='m-menu__link m-menu__link--main']")
	List<WebElement> mainMenuCategories;
	@FindBy(xpath = "//ul[@class='m-menu']//a[contains(@href,'unique')]//..//div//li[@class='m-sub-menu__item m-sub-menu__item--level-1']/a")
	List<WebElement> subMenuCategories;
	@FindBy(xpath = "//li[contains(@class,'level-1')]//a[contains(text(),'Sticky Notes')]//..//div//a")
	List<WebElement> stickyNotesMenu;
	@FindBy(xpath = "//ul[@class='m-menu']/li//a[contains(text(),'Craft ') ]//..//div//ul//li[@class='m-sub-menu__item m-sub-menu__item--level-1']//a[contains(text(),'Papers')]")
	List<WebElement> craftMaterialMenu;

	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'Ganpati')]")
	WebElement scrollElementForCWS;
	@FindBy(xpath = "//div[contains(@class,'block-our_store ')]//h3")
	WebElement cwu;
	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'Trade')]")
	WebElement tradeName;
	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'Inkarto')]")
	WebElement brandName;
	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'27AACHA1185L1ZB')]")
	WebElement gst;
	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'Ganpati')]")
	WebElement headquaters;
	@FindBy(xpath = "//div[@class='block-text rte']/p[contains(text(),'919429692701')]")
	WebElement reachOut;

	@FindBy(xpath = "//div[@class='container-full']//a[contains(@href,'facebook')]//span")
	WebElement facebookFollowers;
	@FindBy(xpath = "//div[@class='container-full']//a[contains(@href,'instagram')]//span")
	WebElement instagramFollowers;
	@FindBy(xpath = "//div[@class='container-full']//div[contains(@class,'justify-center')]/span/b")
	WebElement customerRating;
	@FindBy(xpath = "//ul[@class='m-menu']//a[contains(text(),'Craft')]//..//div//li[@class='m-sub-menu__item m-sub-menu__item--level-1']/a[contains(text(),'Papers')]//..//div//a")
	List<WebElement> papersMenu;

	@FindBy(xpath = "//div[@class=\"m-header__menu\"]//a[contains(text(),'Art Supplies')]")
	WebElement productsMenu;
	@FindBy(xpath = "//div[@class='m-mega-menu m-gradient m-color-default']//a[@href='/collections/paints-buy-online']")
	WebElement paintsMenu;
	@FindBy(xpath = "//ul[@class='m-sub-menu m-sub-menu--level-2']//a[@href='/collections/spray-paint-buy-online']")
	WebElement waterColourOption;

	@FindBy(xpath = "//div[contains(@class,'open')]//h3[@class='m-accordion--item-button m-footer--block-title']")
	WebElement letsConnect;
	@FindBy(xpath = "//input[@name='contact[email]']")
	WebElement email;
	@FindBy(xpath = "//button[@name='commit']")
	WebElement subscribe;

	@FindBy(xpath = "//div[@class='m-header__right']//a[@href='/pages/wishlist']")
	WebElement wishlist;

	@FindBy(xpath = "//div[@class='m-product-card__action m-product-card__action--top m:hidden md:m:flex']")
	List<WebElement> items;

	@FindBy(xpath = "//div[@class='m-header__right']//a[@class='m-header__account']")
	WebElement accountLogo;
	@FindBy(xpath = "//a[@class='m-button m-button--primary']")
	WebElement regBtn;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement fname;
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement Lname;
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement emailId;
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement pass;
	@FindBy(xpath = "//button[contains(text(),'Register')]")
	WebElement regbtn2;
	@FindBy(xpath = "//div[@class='container-fluid']//h1")
	WebElement loginName;

	@FindBy(xpath = "//h1[contains(text(),\"files\")]")
	WebElement files;
	@FindBy(xpath = "//h1[contains(text(),\"lippan art\")]")
	WebElement lippenArt;
	@FindBy(xpath = "//li[contains(text(),\"email\")]")
	WebElement errorMsg;
	@FindBy(xpath = "//h2[contains(text(),\"Special\")]")
	WebElement specialDeals;
	@FindBy(xpath = "//h1[contains(text(),\"Spray\")]")
	WebElement sprayContains;
	@FindBy(xpath = "//div[@class='notification--error-message']")
	WebElement thanksMsg;

	@SuppressWarnings("unchecked")
	public void getFollowers() {
		// Adds a delay using JavaScript and captures follower counts and customer
		// rating
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

		JSONObject followerData = new JSONObject();
		followerData.put("InstagramFollowers", instagramFollowers.getText());
		followerData.put("FacebookFollowers", facebookFollowers.getText());
		followerData.put("CustomerRating", customerRating.getText());

		// Writes data to JSON file
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\Followers.json", followerData);
		ScreenShot.screenShotTC(driver, "07_Followers"); // Takes screenshot
	}

	public void glossyItems(WebDriver driver) {
		// Navigates through nested menus to click Water Colour option
		Actions act = new Actions(driver);
		act.moveToElement(productsMenu).build().perform();
		act.moveToElement(paintsMenu).build().perform();
		act.moveToElement(waterColourOption).click().perform();
	}

	public void getBinderClips() {
		// Searches for binder clips using test data from Excel
		searchButton.click();
		searchBar.sendKeys(exe.getCellData(sheet, 1, 1));
		searchIcon.click();
	}

	public void getLippanArts() {
		// Searches for Lippan Arts
		searchButton.click();
		searchBar.sendKeys(exe.getCellData(sheet, 1, 2));
		searchBtnLipen.click();
	}

	public void addToWishlist(WebDriver driver) {
		// Navigates to product page and adds up to 10 items to wishlist
		int i = 1;
		driver.navigate().to(exe.getCellData(sheet, 1, 0));
		for (WebElement ele : items) {
			if (i > 10)
				break;
			new Actions(driver).scrollToElement(ele).build().perform();
			ele.click();
			i++;
		}
	}

	public void clickOnsearch() {
		// Clicks search button
		searchButton.click();
	}

	public void searchForData() {
		// Enters search data and clicks search icon
		searchBar.sendKeys(exe.getCellData(sheet, 1, 1));
		searchIcon.click();
	}

	public boolean isDisplayfiles() {
		// Checks if file section is displayed
		return files.isDisplayed();
	}

	public boolean display() {
		// Checks if Instagram followers section is displayed
		return instagramFollowers.isDisplayed();
	}

	public void mousehover() {
		// Hovers over “Stationery” main menu
		Actions act = new Actions(driver);
		for (WebElement ele : mainMenuCategories) {
			if (ele.getText().equalsIgnoreCase("Stationery")) {
				act.moveToElement(ele).perform();
			}
		}
	}

	public void subMenuHover() {
		// Hovers over “Sticky Notes” submenu
		Actions act = new Actions(driver);
		for (WebElement e : subMenuCategories) {
			if (e.getText().equalsIgnoreCase("Sticky Notes")) {
				act.moveToElement(e).perform();
			}
		}
	}

	public void printList() {
		// Prints Sticky Notes items and takes a screenshot
		for (WebElement ee : stickyNotesMenu) {
			System.out.println(ee.getText());
		}
		ScreenShot.screenShotTC(driver, "09_StickyNotes");
	}

	public void craftMouseHover() {
		// Hovers over “Craft Material” main menu
		Actions act = new Actions(driver);
		for (WebElement ele : mainMenuCategories) {
			if (ele.getText().equalsIgnoreCase("Craft Material")) {
				act.moveToElement(ele).perform();
			}
		}
	}

	public void craftSubMenu() {
		// Hovers over “Papers” submenu under Craft Material
		Actions act = new Actions(driver);
		for (WebElement e : craftMaterialMenu) {
			if (e.getText().equalsIgnoreCase("Papers")) {
				act.moveToElement(e).perform();
			}
		}
	}

	public void quillingPaperPresent() {
		// Checks if “Quilling Paper” is present and writes result to Excel
		String str = "Quilling paper is present";
		for (WebElement ee : papersMenu) {
			if (ee.getText().equalsIgnoreCase("Qilling Paper")) {
				try {
					exe.setCellData(sheet, 1, 3, str);
					ScreenShot.screenShotTC(driver, "08_QuillingPaper");
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void scrollToConnect() {
		// Scrolls to Connect With Us section
		new Actions(driver).scrollToElement(scrollElementForCWS).perform();
	}

	@SuppressWarnings("unchecked")
	public void printDetails() {
		// Captures contact and brand details and saves to JSON file
		JSONObject details = new JSONObject();
		details.put("ConnectWithUs", cwu.getText());
		details.put("TradeName", tradeName.getText());
		details.put("BrandName", brandName.getText());
		details.put("GST", gst.getText());
		details.put("Headquarters", headquaters.getText());
		details.put("ReachOut", reachOut.getText());
		JsonWriter.writeJsonToFile(".\\src\\test\\resources\\output\\connectWithUs.json", details);
	}

	public void scrollToLetsGetInTouch() {
		// Scrolls to Subscribe section and clears email input
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 3000);");
		act.scrollToElement(subscribe).perform();
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 3000);");
		email.clear();
	}

	public void enterEmail() {
		// Enters email address from config
		email.sendKeys(ConfigReader.getProperty("emailId"));
	}

	public void clickSubscribe() {
		// Submits subscription form
		subscribe.click();
	}

	public void errorMessageSbuscribe() {
		// Handles success or validation message for subscription and logs result
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
		String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", email);
		try {
			if (ConfigReader.getProperty("emailId").contains("@")) {
				js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
				exe.setCellData(sheet, 1, 4, thanksMsg.getText());
				exe.fillGreenColor(sheet, 1, 4);
			} else {
				exe.setCellData(sheet, 1, 4, validationMessage);
				exe.fillRedColor(sheet, 1, 4);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToSearch() {
		// Searches for data from Excel
		searchBar.sendKeys(exe.getCellData(sheet, 1, 2));
		searchIcon.click();
	}

	public boolean isDisplayLippanArt() {
		// Checks if Lippan Art button is displayed
		return searchBtnLipen.isDisplayed();
	}

	public void clickAccountLogo() {
		// Clicks account logo
		accountLogo.click();
	}

	public void scrollToRegBtn() {
		// Scrolls to Register button
		new Actions(driver).scrollToElement(regBtn).perform();
	}

	public void clickOnRegBtn() {
		// Clicks Register button
		regBtn.click();
	}

	public void enterCredentials() {
		// Inputs registration form credentials from config
		fname.sendKeys(ConfigReader.getProperty("Fname"));
		Lname.sendKeys(ConfigReader.getProperty("Lname"));
		emailId.sendKeys(ConfigReader.getProperty("RegEmailId"));
		pass.sendKeys(ConfigReader.getProperty("RegPassword"));
	}

	public void clickRegisterBtn() {
		// Scrolls and submits registration form
		Actions act1 = new Actions(driver);
		act1.scrollToElement(regbtn2).build().perform();
		regbtn2.click();
	}

	public String getErrorMessage() {
		// Retrieves registration error message
		return errorMsg.getText();
	}

	public void hoverArtSuppplies() {
		// Hover over Product Menu
		new Actions(driver).moveToElement(productsMenu).build().perform();
	}

	public void hoverPaintsSubMneu() {
		// Hover over Paints submenu
		new Actions(driver).moveToElement(paintsMenu).build().perform();
	}

	public void clickOnSpray() {
		// Clicks Spray paint option
		new Actions(driver).moveToElement(waterColourOption).click().perform();
	}

	public void scrolltoSpecialDeals() {
		// Scrolls to Special Deals section
		new Actions(driver).moveToElement(specialDeals);
	}

	public void clickWishList() {
		// Clicks wishlist
		wishlist.click();
	}

	public boolean displaySparyPaints() {
		// Checks if Spray Paints section is displayed
		return sprayContains.isDisplayed();
	}
}
