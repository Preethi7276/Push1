package com.test;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class RetryFailedTestCases implements IRetryAnalyzer {
	int counter = 1;
	// total retry attempt if a test case fails.
	int retryLimit = 5;
	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}
}