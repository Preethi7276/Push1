package com.cybersource.test.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

	int counter = 1;
	// total retry attempt if a test case fails.
	int retryLimit = 3; 
	
	@Override
	public boolean retry(ITestResult result) {
		if(counter <= retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	} 
}