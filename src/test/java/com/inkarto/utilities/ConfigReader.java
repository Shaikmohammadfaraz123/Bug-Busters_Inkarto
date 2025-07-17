package com.inkarto.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Utility class to read key-value pairs from config.properties file
public class ConfigReader {

	// Properties object used to store configuration data
	private static Properties properties = new Properties();

	// Static block ensures configuration is loaded only once when the class is
	// accessed
	static {
		try {
			// Load the config.properties file from the specified location
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");

			// Load file content into properties object
			properties.load(fis);

			// Close input stream after loading
			fis.close();

		} catch (IOException e) {
			// Handles file I/O errors such as missing file or read failure
			e.printStackTrace(); // Print detailed error message for debugging
		}
	}

	// Fetches value associated with the given key from loaded properties
	public static String getProperty(String key) {
		return properties.getProperty(key); // Returns value or null if key doesn't exist
	}
}
