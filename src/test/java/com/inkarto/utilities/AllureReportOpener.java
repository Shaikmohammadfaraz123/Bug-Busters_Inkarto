package com.inkarto.utilities;

import java.io.File;
import java.io.IOException;

// Utility class for handling Allure report generation and cleanup
public class AllureReportOpener {

	// Deletes all existing Allure result files from the target directory
	public static void cleanAllureResults() {
		File resultsDir = new File("target/allure-results");

		// Check if results directory exists and is a valid directory
		if (resultsDir.exists() && resultsDir.isDirectory()) {

			// Loop through all files in the directory and delete them
			for (File file : resultsDir.listFiles()) {
				file.delete(); // Removes individual report files
			}
		}
	}

	// Generates and opens the Allure report using the installed command-line tool
	public static void openAllureReport() {
		try {
			// Step 1: Generate Allure report from existing results
			ProcessBuilder generate = new ProcessBuilder(
					// Specify path to your local Allure CLI tool
					"C:\\Users\\2407295\\Downloads\\allure-commandline-2.29.0\\allure-2.29.0\\bin\\allure.bat",
					"generate", "target/allure-results", "-o", "target/allure-report", "--clean");

			generate.inheritIO(); // Display command output in console
			Process genProcess = generate.start(); // Run generate command
			genProcess.waitFor(); // Wait for generation to complete

			// Step 2: Open the generated Allure report in default browser
			ProcessBuilder open = new ProcessBuilder(
					"C:\\Users\\2407295\\Downloads\\allure-commandline-2.29.0\\allure-2.29.0\\bin\\allure.bat", "open",
					"target/allure-report");

			open.inheritIO(); // Show output when opening report
			Process openProcess = open.start(); // Launch report viewer
			openProcess.waitFor(); // Wait for command to finish

		} catch (IOException | InterruptedException e) {
			// Log any errors during generation or opening process
			e.printStackTrace();
		}
	}
}
