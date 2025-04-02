package com.cybersource.test.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Helper {

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element ) {
		
	//	WebDriverWait wait = new WebDriverWait(driver, Constants.SIXTY_SECOND);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.SIXTY_SECOND));
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}
	
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {		
	//	WebDriverWait wait = new WebDriverWait(driver, Constants.SIXTY_SECOND);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.SIXTY_SECOND));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	
}
