package com.inkarto.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

// Utility class for capturing screenshots during test execution
public class ScreenShot {

	// Directory path where screenshots will be saved
	public static String filepath = "./screenshots/";

	/**
	 * Captures a screenshot of the current browser window.
	 *
	 * @param scdriver WebDriver instance used for capturing the screen
	 * @param fileName Base name for the screenshot file
	 * @return Full path of the saved screenshot file
	 */
	public static String screenShotTC(WebDriver scdriver, String fileName) {
		// Create the screenshots directory if it doesn't already exist
		File directory = new File(filepath);
		if (!directory.exists()) {
			directory.mkdirs(); // Create the directory structure
		}

		// Format timestamp to include in file name (e.g., 17-07-2025_09-53-12)
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date date = new Date();

		// Capture screenshot and store it as a temporary file
		File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);

		// Construct destination file path with name and timestamp
		String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";
		File dest = new File(destination);

		try {
			// Copy temporary screenshot file to the desired destination
			FileHandler.copy(src, dest);
			return destination; // Return file path of saved screenshot
		} catch (IOException e) {
			// Handle file copy error and wrap it in a runtime exception
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}
}
