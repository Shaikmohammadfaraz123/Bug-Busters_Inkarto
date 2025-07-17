package com.inkarto.reRun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// RetryAnalyzer is used to automatically retry failed test cases
public class RetryAnalyzer implements IRetryAnalyzer {

	// Tracks how many times the test has been retried
	private int retryCount = 0;

	// Maximum number of retry attempts for a failed test
	private static final int maxRetryCount = 1; // You can increase this limit if needed

	// Called after a test fails to determine whether it should be retried
	@Override
	public boolean retry(ITestResult result) {
		// Check if test failed and we haven't exceeded retry limit
		if (!result.isSuccess() && retryCount < maxRetryCount) {
			retryCount++;

			// Log retry attempt in console
			System.out.println("Retrying failed scenario: " + result.getName() + " | Attempt #" + retryCount);

			// Return true to retry the test
			return true;
		}
		// If max attempts reached or test passed, do not retry
		return false;
	}
}
