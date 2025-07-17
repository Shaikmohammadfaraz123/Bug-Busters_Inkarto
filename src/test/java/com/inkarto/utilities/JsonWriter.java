package com.inkarto.utilities;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// Utility class for writing JSON data to files
public class JsonWriter {

	/**
	 * Writes a JSONObject to the specified file path. This method serializes the
	 * JSON object and stores it as plain text.
	 *
	 * @param filePath The destination path of the JSON file.
	 * @param data     The JSONObject to be written.
	 */
	public static void writeJsonToFile(String filePath, JSONObject data) {
		// Use try-with-resources to ensure FileWriter closes automatically
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(data.toJSONString()); // Convert object to string and write
			writer.flush(); // Force write to disk
		} catch (IOException e) {
			// Wrap and rethrow exception for clearer error reporting
			throw new RuntimeException("❌ Failed to write JSON file: " + e.getMessage(), e);
		}
	}

	/**
	 * Converts a list of strings into a JSON array and writes it to a file. The
	 * array is stored under the key "results".
	 *
	 * @param filePath The destination file path.
	 * @param list     The list of strings to write.
	 */
	@SuppressWarnings("unchecked") // Suppresses warnings for raw use of JSON collections
	public static void writeJsonToFile(String filePath, java.util.List<String> list) {
		JSONObject json = new JSONObject(); // Create root JSON object
		JSONArray array = new JSONArray(); // Create array to hold list elements

		array.addAll(list); // Populate array with list data
		json.put("results", array); // Add array to object under "results"

		writeJsonToFile(filePath, json); // Reuse existing method to perform write
	}
}
