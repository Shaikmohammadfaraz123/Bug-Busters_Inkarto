package com.inkarto.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


import com.inkarto.utilities.ConfigReader;
import com.inkarto.utilities.ExcelUtils;
import com.inkarto.utilities.ScreenShot;

// Page Object for handling all social media links on the Inkarto page
public class URLsPage extends BasePage{
	

	// Store original browser tab to return after switching windows
	private String parentWindow;

	// Excel sheet name and utility instance
	private String sheetName = "Sheet1";
	private ExcelUtils exe = new ExcelUtils("Inkarto.xlsx");

	// Store the URLs fetched from social media links
	private List<String> urlsList = new ArrayList<>();

	// Logger for debugging and status output
	private static final Logger logger = LogManager.getLogger(URLsPage.class);

	// Constructor initializes elements and records original window handle
	public URLsPage(WebDriver driver) {
		super(driver);
		parentWindow = driver.getWindowHandle();
	}

	// ======= Web Elements =======

	@FindBy(xpath = "//div/ul/li/a[text()='Office Stationery']")
	WebElement scrollElementForSM;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='Pinterest']")
	WebElement pinterestIcon;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='300k Followers']")
	WebElement facebookIcon;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='200k Followers']")
	WebElement instagramIcon;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='Youtube']")
	WebElement youtubeIcon;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='Tiktok']")
	WebElement tiktokIcon;

	@FindBy(xpath = "//div[@class='block-text']/div[@class='social-media-links ']/a[@aria-label='Whatsapp']")
	WebElement whatsappIcon;

	// Scrolls to the social media section
	public void scrollForSM(WebDriver driver) {
		Actions act = new Actions(driver);
		act.scrollToElement(scrollElementForSM).perform();
	}

	// Clicks Pinterest icon and saves its URL if it matches expected
	public void smPinterest(WebDriver driver) {
		pinterestIcon.click();
		String pinterestTitle = ConfigReader.getProperty("Purl");
		Set<String> ids1 = driver.getWindowHandles();

		for (String windowId : ids1) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (driver.getCurrentUrl().equals(pinterestTitle)) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Clicks Facebook icon and verifies against expected title
	public void smFacebook(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		facebookIcon.click();
		String facebookTitle = ConfigReader.getProperty("Fburl");
		Set<String> ids2 = driver.getWindowHandles();

		for (String windowId : ids2) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (facebookTitle.equals(driver.getTitle())) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Clicks Instagram icon and checks if current URL contains 'instagram'
	public void smInstagram(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		instagramIcon.click();
		Set<String> ids3 = driver.getWindowHandles();

		for (String windowId : ids3) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (driver.getCurrentUrl().contains("instagram")) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Clicks YouTube icon and verifies against expected title
	public void smYoutube(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		youtubeIcon.click();
		String youtubeTitle = ConfigReader.getProperty("Yurl");
		Set<String> ids4 = driver.getWindowHandles();

		for (String windowId : ids4) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (youtubeTitle.equals(driver.getTitle())) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Clicks TikTok icon and verifies against expected title
	public void smTiktok(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		tiktokIcon.click();
		String tiktokTitle = ConfigReader.getProperty("Turl");
		Set<String> ids5 = driver.getWindowHandles();

		for (String windowId : ids5) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (tiktokTitle.equals(driver.getTitle())) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Clicks WhatsApp icon and verifies against expected title
	public void smWhatsapp(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		whatsappIcon.click();
		String whatsappTitle = ConfigReader.getProperty("Wurl");
		Set<String> ids6 = driver.getWindowHandles();

		for (String windowId : ids6) {
			driver.switchTo().window(windowId);
			waitForPageLoad(driver);
			if (whatsappTitle.equals(driver.getTitle())) {
				urlsList.add(driver.getCurrentUrl());
			}
		}
	}

	// Writes all gathered social media URLs into Excel file
	public void noOfLinks() {
		int rowIndex = 1;
		for (String url : urlsList) {
			try {
				exe.setCellData(sheetName, rowIndex, 5, url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			rowIndex++;
		}
	}

	// Central method to collect all social media URLs and store them
	public void gettingAllSocialMediaURLs(WebDriver driver) {
		scrollForSM(driver);
		ScreenShot.screenShotTC(driver, "06_SocialMediaUrls");

		smPinterest(driver);
		smFacebook(driver);
		smInstagram(driver);
		smYoutube(driver);
		smTiktok(driver);
		smWhatsapp(driver);

		noOfLinks();
		logger.info("Urls are written into Excel");

		// Return to original tab
		driver.switchTo().window(parentWindow);
	}

	// Utility: Delays execution to wait for new window to load
	private void waitForPageLoad(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	}
}
