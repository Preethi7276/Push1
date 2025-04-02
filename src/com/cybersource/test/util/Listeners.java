package com.cybersource.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.cybersource.test.CommonTest;

public class Listeners extends CommonTest implements ITestListener {

	WebDriver driver = null;
	CommonUtil commonUtil = new CommonUtil();

	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log(arg0.getName() + Constants.FINISHED);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		driver=commonUtil.getDriver();
		driver = CommonUtil.setupDriver(prop);
		Reporter.log(arg0.getName() + Constants.FAILED);
		String methodName = arg0.getName();
		if (!arg0.isSuccess()) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				File destFile = new File(System.getProperty(Constants.USER_DIR) + Constants.FAILEDSCREENSHOTPATH
						+ methodName + Constants._JPG);
				FileUtils.copyFile(scrFile, destFile);
				
				
				// Reporter.log(Constants.FAILED_SCREENSHOT + Constants.A_HREF_EQUAL +
				// destFile.getAbsolutePath()
				// + Constants.SINGLE_QUOTE_CLOSING_TAG + Constants.IMG_SRC_EQUAL +
				// destFile.getAbsolutePath()
				// + prop.getProperty(Constants.SCREENSHOT_HEIGHT_WIDTH) +
				// Constants.FORWARD_SLASH_CLOSING_TAG
				// + Constants.CLOSING_A_TAG);
			} catch (IOException e) {
				Reporter.log(Constants.SCREENSHOT_FEATURE_DISABLED);
			}
		}
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Reporter.log(arg0.getName() + Constants.STARTS);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		String methodName = arg0.getName();
		driver=commonUtil.getDriver();
		driver = CommonUtil.setupDriver(prop);
		Reporter.log(arg0.getName() + Constants.PASS);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			File destFile = new File(System.getProperty(Constants.USER_DIR) + Constants.SUCCESSSCREENSHOTPATH
					+ methodName + Constants._JPG);
			
			
			try {
				FileUtils.copyFile(scrFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
			
			
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
}