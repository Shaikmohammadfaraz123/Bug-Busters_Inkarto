package com.inkarto.reRun;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// RetryListener class implements IAnnotationTransformer to apply retry logic globally
public class RetryListener implements IAnnotationTransformer {

	// The transform() method is invoked for every test method before execution
	@Override
	public void transform(ITestAnnotation annotation, // TestNG annotation on test method
			@SuppressWarnings("rawtypes") Class testClass, // Class containing the test
			@SuppressWarnings("rawtypes") Constructor constructor, // Constructor of the test class
			Method method // The actual test method
	) {
		// Assigns RetryAnalyzer class to each test method annotation dynamically
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
