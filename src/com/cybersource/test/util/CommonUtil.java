package com.cybersource.test.util;

//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.cybersource.test.CommonTest;

public class CommonUtil extends CommonTest {

	// time are in milli seconds.
	public static int SLEEP_TIMER_1 = 1000;
	public static int SLEEP_TIMER_2 = 2000;
	public static int SLEEP_TIMER_3 = 3000;
	public static int SLEEP_TIMER_4 = 4000;
	public static int SLEEP_TIMER_5 = 5000;
	public static int SLEEP_TIMER_6 = 6000;
	public static int SLEEP_TIMER_8 = 8000;
	public static int SLEEP_TIMER_10 = 10000;
	public static int SLEEP_TIMER_12 = 12000;
	public static int SLEEP_TIMER_15 = 15000;
	public static int SLEEP_TIMER_17 = 17000;
	public static int SLEEP_TIMER_25 = 25000;
	public static int SLEEP_TIMER_20 = 20000;
	public static int SLEEP_TIMER_30 = 30000;
	public static int SLEEP_TIMER_35 = 35000;
	public static int SLEEP_TIMER_40 = 40000;
	public static int SLEEP_TIMER_45 = 45000;
	public static int SLEEP_TIMER_50 = 50000;
	public static int SLEEP_TIMER_60 = 60000;
	public static int SLEEP_TIMER_70 = 70000;
	public static int SLEEP_TIMER_90 = 90000;
	

	static ArrayList<String> tabs = null;
	public static WebDriver driver = null;
	public static Writer fileWriter = null;
	//public static BufferedWriter bufferedWriter = null;
	WebDriver driverInstance = null;
	static int TaxConfigure = 0;
	String userIndex = "1";
	static ArrayList<WebDriver> driverIndex = new ArrayList<>();
	static ArrayList<WebDriver> debugDriverIndex = new ArrayList<>();
	public static PrintWriter printWriter = null;
	static JavascriptExecutor executor = null;
	static String screenShotModulePath = null;
	static String screenShotPath = null;
	final static Logger logger = Logger.getLogger(CommonUtil.class);

	public static WebDriver setupBrowserSpecificDriver(Properties prop) {
		logger.info("[CommonUtil][setupBrowserSpecificDriver] prop=" + prop);
		String browser = null;
		WebDriver driver = null;
		browser = prop.getProperty("browser.type");
		browser = (null != browser) ? browser.trim() : Constants.CHROME;
		logger.info("[CommonUtil][setupBrowserSpecificDriver] browser=" + browser);
		logger.info("[CommonUtil][setupBrowserSpecificDriver] firefox browser="
				+ browser.equalsIgnoreCase(Constants.FIREFOX));
		logger.info("[CommonUtil][setupBrowserSpecificDriver] chrome browser="
				+ browser.equalsIgnoreCase(Constants.CHROME));
		if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
			logger.info("[CommonUtil][setupBrowserSpecificDriver] FIREFOX browser=" + browser);
			System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, prop.getProperty(Constants.FIREFOXDRIVER_PATH));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase(Constants.CHROME)) {
			logger.info("[CommonUtil][setupBrowserSpecificDriver] CHROME browser=" + browser);
			System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, prop.getProperty(Constants.CHROMEDRIVER_PATH));
			driver = new ChromeDriver();
		}
		logger.info("[CommonUtil][setupBrowserSpecificDriver] driver=" + driver);
		return driver;
	}

	public static WebElement findElementByXPath(WebDriver driver, Properties prop, String xPath) {
		String xPathInProp = null;
		WebElement element = null;
		logger.info("[CommonUtil][findElementByXPath] xPath=" + xPath);
		if (null != xPath) {
			xPathInProp = prop.getProperty(xPath);
			logger.info("[CommonUtil][findElementByXPath] xPathInProp=" + xPathInProp);
			if (null != xPathInProp) {
				try {
					logger.info("[CommonUtil][findElementByXPath] By.xpath(xPathInProp)=" + By.xpath(xPathInProp));
					if (driver.findElements(By.xpath(xPathInProp)).size() != 0) {
						element = driver.findElement(By.xpath(xPathInProp));
						logger.info("[CommonUtil][findElementByXPath] element.isDisplayed()=" + element.isDisplayed());
					}
				} catch (Throwable e) {
					logger.info(
							"[CommonUtil][findElementByXPath] No Element found for given xPathInProp:" + xPathInProp);
					e.printStackTrace();
				} 
			} else {
				logger.info("[CommonUtil][findElementByXPath] xPathInProp is Null:" + xPathInProp);
			}
		} else {
			logger.info("[CommonUtil][findElementByXPath] xPath is Null");
		}
		return element;
	}

	public static WebElement findElementByXPath(WebDriver driver, Properties prop, String xPathPrefix,
			int positionNumber, String xPathSuffix) {
		String xPathPrefixInProp = null;
		String xPathSuffixInProp = null;
		WebElement element = null;
		logger.info("[CommonUtil][findElementByXPath] xPath=" + xPathPrefixInProp + positionNumber + xPathSuffixInProp);
		if (null != xPathPrefix && null != xPathSuffix) {
			xPathPrefixInProp = prop.getProperty(xPathPrefix);
			xPathSuffixInProp = prop.getProperty(xPathSuffix);
			logger.info("[CommonUtil][findElementByXPath] xPathPrefixInProp=" + xPathPrefixInProp
					+ ":: xPathSuffixInProp=" + xPathSuffixInProp);
			if (null != xPathPrefixInProp && null != xPathSuffixInProp) {
				try {
					logger.info("[CommonUtil][findElementByXPath] By.xpath(xPathInProp)="
							+ By.xpath(xPathPrefixInProp + positionNumber + xPathSuffixInProp));
					if (driver.findElements(By.xpath(xPathPrefixInProp + positionNumber + xPathSuffixInProp))
							.size() != 0) {
						element = driver.findElement(By.xpath(xPathPrefixInProp + positionNumber + xPathSuffixInProp));
						logger.info("[CommonUtil][findElementByXPath] element.isDisplayed()=" + element.isDisplayed());
					}
				} catch (Throwable e) {
					logger.info("[CommonUtil][findElementByXPath] No Element found for given xPathInProp:"
							+ xPathPrefixInProp + positionNumber + xPathSuffixInProp);
					e.printStackTrace();
				} finally {
					logger.info("[CommonUtil][findElementByXPath] finally called");
				}
			} else {
				logger.info("[CommonUtil][findElementByXPath] xPathInProp is Null:" + xPathPrefixInProp + positionNumber
						+ xPathSuffixInProp);
			}
		} else {
			logger.info("[CommonUtil][findElementByXPath] xPath is Null");
		}
		return element;
	}

	public static String getElementTextByXPath(WebDriver driver, Properties prop, String xPath) {
		String elementText = null;
		WebElement element = null;
		logger.info("[CommonUtil][getElementTextByXPath] xPath=" + xPath);
		if (null != xPath) {
			element = findElementByXPath(driver, prop, xPath);
			if (null != element) {
				elementText = element.getText();
				logger.info("[CommonUtil][getElementTextByXPath]elementText " + elementText);
			} else {
				logger.info("[CommonUtil][getElementTextByXPath] element is Null");
			}
		} else {
			logger.info("[CommonUtil][getElementTextByXPath] element is null for given xPath:" + xPath);
		}
		return elementText;
	}

	public static String setElementValueByXPath(WebDriver driver, Properties prop, String xPath, String value) {
		String elementText = null;
		WebElement element = null;
		logger.info("[CommonUtil][setElementValueByXPath] xPath=" + xPath);
		if (null != xPath) {
			element = findElementByXPath(driver, prop, xPath);
			if (null != element) {
				element.sendKeys(value);
				elementText = element.getAttribute("validationMessage");
			} else {
				logger.info("[CommonUtil][setElementValueByXPath] Not able to set the value element is Null for xPath="
						+ xPath);
			}
		} else {
			logger.info("[CommonUtil][setElementValueByXPath] xPath is Null for given xPath:" + xPath);
		}
		return elementText;
	}

	public static String setElementValueById(WebDriver driver, Properties prop, String Id, String value) {
		String elementText = null;
		WebElement element = null;
		logger.info("[CommonUtil][setElementValueByXPath] xPath=" + Id);
		if (null != Id) {
			element = findElementById(driver, prop, Id);
			if (null != element) {
				element.sendKeys(value);
				elementText = element.getAttribute("validationMessage");
			} else {
				logger.info("[CommonUtil][setElementValueByXPath] Not able to set the value element is Null for xPath="
						+ Id);
			}
		} else {
			logger.info("[CommonUtil][setElementValueByXPath] xPath is Null for given xPath:" + Id);
		}
		return elementText;
	}

	public static String setElementValueByName(WebDriver driver, Properties prop, String name, String value) {
		String elementText = null;
		WebElement element = null;
		logger.info("[CommonUtil][setElementValueByName] name=" + name);
		if (null != name) {
			element = findElementByName(driver, prop, name);
			if (null != element) {
				element.sendKeys(value);
			} else {
				logger.info("[CommonUtil][setElementValueByName] Not able to set the value element is Null for name="
						+ name);
			}
		} else {
			logger.info("[CommonUtil][setElementValueByName] name is Null for given name:" + name);
		}
		return elementText;
	}

	public static void clickElementByXPath(WebDriver driver, Properties prop, String xPath) {
		WebElement element = null;
		logger.info("[CommonUtil][clickElementByXPath] xPath=" + xPath);
		if (null != xPath) {
			element = findElementByXPath(driver, prop, xPath);
			if (null != element) {
				logger.info("[CommonUtil][clickElementByXPath] before click xPath=" + xPath);
				element.click();
				logger.info("[CommonUtil][clickElementByXPath] after click xPath=" + xPath);
			} else {
				logger.info("[CommonUtil][clickElementByXPath] Not able to set the value element is Null for xPath="
						+ xPath);
			}
		} else {
			logger.info("[CommonUtil][clickElementByXPath] xPath is Null for given xPath:" + xPath);
		}
	}

	public static void clickElementByXPath(WebDriver driver, Properties prop, String xPathPrefix, int positionNumber,
			String xPathSuffix) {
		WebElement element = null;
		logger.info("[CommonUtil][clickElementByXPath] xPathPrefix=" + xPathPrefix + ", positionNumber="
				+ positionNumber + ", xPathSuffix=" + xPathSuffix);
		if (null != (xPathPrefix + positionNumber + xPathSuffix)) {
			element = findElementByXPath(driver, prop, xPathPrefix, positionNumber, xPathSuffix);
			if (null != element) {
				logger.info("[CommonUtil][clickElementByXPath] before click xPath=" + xPathPrefix + positionNumber
						+ xPathSuffix);
				element.click();
				logger.info("[CommonUtil][clickElementByXPath] after click xPath=" + xPathPrefix + positionNumber
						+ xPathSuffix);
			} else {
				logger.info("[CommonUtil][clickElementByXPath] Not able to set the value element is Null for xPath="
						+ xPathPrefix + positionNumber + xPathSuffix);
			}
		}
	}
	public static void clickElementUsingExplicitWait(WebDriver driver, Properties prop, String Element) {

		try {
		//	WebDriverWait element = new WebDriverWait(driver,40);
			WebDriverWait element = new WebDriverWait(driver, Duration.ofSeconds(40));
			element.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(Element)))).click();

		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}
	public static void setElementValueUsingExplicitWait(WebDriver driver, Properties prop, String Element,
			String value) {

		try {
		//	WebDriverWait element = new WebDriverWait(driver,30);
			WebDriverWait element = new WebDriverWait(driver, Duration.ofSeconds(30));
			element.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(Element))))
					.sendKeys((value));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	public static void findElementValueUsingExplicitWait(WebDriver driver, Properties prop, String Element
			) {

		try {
			//WebDriverWait element = new WebDriverWait(driver,30);
			WebDriverWait element = new WebDriverWait(driver, Duration.ofSeconds(30));
			element.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(Element))));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	public static void checkTransactionInEBC(WebDriver driver, String cardDetails[], String customerDetails[],
			String devicefingerprint, String avscode, String cvncode, String transactionId, String OrganizationId,
			String EBCUserName, String EBCPassword, String Service, String screenShotPath) throws Exception {
		logger.info("[CommonUtil][checkTransactionInEBC] Starts");
		driver = setupDriver(prop, cardDetails, customerDetails, transactionId, Constants.EBC, OrganizationId,
				EBCUserName, EBCPassword, Service, screenShotPath);

		if (avscode.length() > 0) {
			String AVSCode = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_AVS_CODE))).getText();
			logger.info("[CommonUtil][checkTransactionInEBC] avs: " + AVSCode.substring(0, 1));
			if (AVSCode.substring(0, 1).equalsIgnoreCase(avscode)) {
				logger.info("[CommonUtil][checkTransactionInEBC] AVS Code Matched");
				Assert.assertTrue(true, "AVS Code Matched");
			} else {
				logger.info("[CommonUtil][checkTransactionInEBC] AVS Code Not Matched");
				Assert.assertTrue(false,
						"AVS Code Not Matched - Expected: " + avscode + ", Actual: " + AVSCode.substring(0, 1));
			}
		}
		if (cvncode.length() > 0) {
			String CVNCode = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CVN_CODE))).getText();
			if (CVNCode.equalsIgnoreCase(cvncode)) {
				logger.info("[CommonUtil][checkTransactionInEBC] CVN Code Matched");
				Assert.assertTrue(true, "CVN Code Matched");
			} else {
				logger.info("[CommonUtil][checkTransactionInEBC] CVN Code Not Matched");
				Assert.assertTrue(false, "CVN Code Not Matched - Expected: " + cvncode + ", Actual: " + CVNCode);
			}
		}
		if (Constants.YES.equalsIgnoreCase(devicefingerprint)) {
			logger.info("[CommonUtil][checkTransactionInEBC] Work in progress");
		}

		switchToWindow(driver, tabs.get(0));
		logger.info("[CommonUtil][checkTransactionInEBC] Ends");
	}

	public static void checkTransactionInEBC(WebDriver driver, String customerDetails[], String transactionId,
			String OrganizationId, String EBCUserName, String EBCPassword, String Service, String screenShotPath)
					throws Exception {
		logger.info("[CommonUtil][checkTransactionInEBC] Starts");
		driver = setupDriver(prop, customerDetails, transactionId, Constants.EBC, OrganizationId, EBCUserName,
				EBCPassword, Service, screenShotPath);

		switchToWindow(driver, tabs.get(0));
		logger.info("[CommonUtil][checkTransactionInEBC] Ends");
	}

	public static void checkTransactionInEBC(WebDriver driver, String cardDetails[], String customerDetails[],
			String transactionId, String OrganizationId, String EBCUserName, String EBCPassword, String Service,
			String screenShotPath) throws Exception {
		logger.info("[CommonUtil][checkTransactionInEBC] Starts");
		driver = setupDriver(prop, cardDetails, customerDetails, transactionId, Constants.EBC, OrganizationId,
				EBCUserName, EBCPassword, Service, screenShotPath);

		switchToWindow(driver, tabs.get(0));
		logger.info("[CommonUtil][checkTransactionInEBC] Ends");
	}
	public static void clickElementByName(WebDriver driver, String Name) {
		logger.info("[CommonUtil][clickElementByName] Name=" + Name);
		if (null != Name) {
			logger.info("[CommonUtil][clickElementByName] before click Name=" + Name);
			driver.findElement(By.name(Name)).click();
			logger.info("[CommonUtil][clickElementByXPath] after click Name=" + Name);
		} else {
			logger.info("[CommonUtil][clickElementByName] Name is Null: " + Name);
		}
	}

	public static WebElement findElementByCssSelector(WebDriver driver, Properties prop, String cssPath) {
		String cssPathInProp = null;
		WebElement element = null;
		logger.info("[CommonUtil][findElementByCssSelector] cssPath=" + cssPath);
		if (null != cssPath) {
			cssPathInProp = prop.getProperty(cssPath);
			logger.info("[CommonUtil][findElementByCssSelector] cssPathInProp=" + cssPathInProp);
			if (null != cssPathInProp) {
				try {
					logger.info("[CommonUtil][findElementByCssSelector] By.cssSelector(cssPathInProp)="
							+ By.cssSelector(cssPathInProp));
					if (Constants.ZERO != driver.findElements(By.cssSelector(cssPathInProp)).size()) {
						element = driver.findElement(By.cssSelector(cssPathInProp));
						logger.info("[CommonUtil][findElementByCssSelector] element.isDisplayed()="
								+ element.isDisplayed());
					}
				} catch (Throwable e) {
					System.out
					.println("[CommonUtil][findElementByCssSelector] No Element found for given cssPathInProp:"
							+ cssPathInProp);
					e.printStackTrace();
				} finally {
					logger.info("[CommonUtil][findElementByCssSelector] finally called");
				}
			} else {
				logger.info("[CommonUtil][findElementByCssSelector] cssPathInProp is Null:" + cssPathInProp);
			}
		} else {
			logger.info("[CommonUtil][findElementByCssSelector] cssPath is Null");
		}
		return element;
	}

	public static void clickElementByCssSelector(WebDriver driver, Properties prop, String cssPath) {
		WebElement element = null;
		logger.info("[CommonUtil][clickElementByCssSelector] cssPath=" + cssPath);
		if (null != cssPath) {
			element = findElementByCssSelector(driver, prop, cssPath);
			if (null != element) {
				logger.info("[CommonUtil][clickElementByCssSelector] before click cssPath=" + cssPath);
				element.click();
				logger.info("[CommonUtil][clickElementByCssSelector] after click cssPath=" + cssPath);
			} else {
				logger.info(
						"[CommonUtil][clickElementByCssSelector] Not able to set the value element is Null for cssPath="
								+ cssPath);
			}
		} else {
			logger.info("[CommonUtil][clickElementByCssSelector] cssPath is Null for given cssPath:" + cssPath);
		}
	}

	public static String getLowerCaseWithoutSpecialChar(String value) {
		if (null != value) {
			value = value.replaceAll(Constants.COLON, Constants.EMPTY_STRING);
			value = value.trim().toLowerCase();
		}
		return value;
	}

	public void executionSleepTimer(String userIndex) {
		logger.info("[executionSleepTimer] Starts");
		int sleepTimer = Integer.parseInt(userIndex);
		logger.info("[executionSleepTimer] sleepTimer: " + sleepTimer + "s");
		for (int i = 0; i < sleepTimer; i++) {
			sleep(SLEEP_TIMER_2);
		}
		logger.info("[executionSleepTimer] Ends");
	}

	public WebDriver setupDriver(Properties prop, String url,String userIndex) {
		logger.info("[setupDriver] Starts");
		WebDriver driverValue = configureDriver(driver, prop, userIndex);
		sleep(SLEEP_TIMER_2);
		driverValue.get(prop.getProperty(url));
		logger.info("[setupDriver] Ends");
		return driverValue;
	}
	public WebDriver getDriver() {

		logger.info("[WebDriver] Starts");

		return driverInstance;

	}		
	public WebDriver configureDriver(WebDriver driver, Properties prop, String userIndex) {
		logger.info("[configureDriver] Starts");

		// String prefix = CommonTest.prefix;
		int userIndexInt = Integer.parseInt(userIndex);
		//int browserMaxTimeoutSeconds = Integer.parseInt(prop.getProperty(Constants.BROWSER_MAX_TIMEOUT_SECONDS));
		// checks and sets if driver is already present for the user
		if (driverIndex != null) {
			if (driverIndex.size() >= userIndexInt) {
				logger.info("[configureDriver] driver is set: " + driverIndex.get(userIndexInt - 1));
				driver = driverIndex.get(userIndexInt - 1);
			}
		}
		logger.info("[configureDriver] driver: " + driver);
		// creates a new driver instance if driver is null
		if (driver == null) {
			// Handling different browser according to test case
			if (prop.getProperty(Constants.BROWSER_TYPE).equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty(Constants.CHROMEDRIVER_PATH));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
				logger.info("[configureDriver] driver is chrome");
			}

			else if (prop.getProperty(Constants.BROWSER_TYPE).equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", prop.getProperty(Constants.EDGEDRIVER_PATH));
				EdgeOptions options = new EdgeOptions();
				//options.addArguments("--remote-allow-origins=*");
				// options.addArguments("--headless");
				driver = new EdgeDriver(options);
				logger.info("[configureDriver] driver is edge");
			}

			else if (prop.getProperty(Constants.BROWSER_TYPE).equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
				logger.info("[configureDriver] driver is safari");
			}

			else {
				logger.info("[configureDriver] Browser type does not match");
			}

			maximizeWindow(driver);

			logger.info("[configureDriver] driver=" + driver);

			// checks driver index and adds new driver instance
			if (driverIndex != null) {
				if (driverIndex.size() < userIndexInt) {
					driverIndex.add(driver);
					logger.info("[configureDriver] driver is added");
				}
			}
		}
		driver.manage().deleteAllCookies();
		driverInstance = driver;
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		logger.info("[configureDriver] Ends");

		return driver;
	}


	public static WebDriver setupDriver(Properties prop) {
		logger.info("[CommonUtil][setupDriver] driver=" + driver);
		if (null == driver) {
			// System.setProperty("webdriver.edge.driver",
			// prop.getProperty(Constants.EDGEDRIVER_PATH));
			//EdgeOptions options = new EdgeOptions();


			ChromeOptions options = new ChromeOptions();
			//EdgeOptions options = new EdgeOptions();
			//options.setExperimentalOption("debuggerAddress","localhost:9222");

			//			options.addArguments("profile-directory=Profile 1");
			System.setProperty("webdriver.chrome.driver", prop.getProperty(Constants.CHROMEDRIVER_PATH));
			//System.setProperty("webdriver.edge.driver", prop.getProperty(Constants.EDGEDRIVER_PATH));
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			//driver = new EdgeDriver(options);
			//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			maximizeWindow(driver);
		}
		return driver;
	}

	public static WebDriver setupDriver(Properties prop, String customerDetails[], String TransactionID,
			String domainName, String OrganizationId, String EBCUserName, String EBCPassword, String Service,
			String screenShotPath) throws Exception {
		logger.info("[CommonUtil] [setupDriver] Starts");
		executor = (JavascriptExecutor) driver;
		if (null == tabs) {
			executor.executeScript("window.open();");
			tabs = new ArrayList<String>(driver.getWindowHandles());
		}
		switchToWindow(driver, tabs.get(1));

		if (null != domainName && domainName.contains(Constants.EBC)) {
			doEBCLogin(driver, prop, null, customerDetails, TransactionID, OrganizationId, EBCUserName, EBCPassword,
					Service, screenShotPath);
		}
		logger.info("[CommonUtil] [setupDriver] Ends");
		return driver;
	}

	public static WebDriver setupDriver(Properties prop, String cardDetails[], String customerDetails[],
			String TransactionID, String domainName, String OrganizationId, String EBCUserName, String EBCPassword,
			String Service, String screenShotPath) throws Exception {
		logger.info("[CommonUtil] [setupDriver] Starts");
		executor = (JavascriptExecutor) driver;
		if (null == tabs) {
			executor.executeScript("window.open();");
			tabs = new ArrayList<String>(driver.getWindowHandles());
		}
		switchToWindow(driver, tabs.get(1));

		if (null != domainName && domainName.contains(Constants.EBC)) {
			doEBCLogin(driver, prop, cardDetails, customerDetails, TransactionID, OrganizationId, EBCUserName,
					EBCPassword, Service, screenShotPath);
		}
		// driver.close();
		logger.info("[CommonUtil] [setupDriver] Ends");
		return driver;
	}

	public static int count = 0;

	public static void doEBCLogin(WebDriver driver, Properties prop, String cardDetails[], String customerDetails[],
			String TransactionID, String OrganizationId, String EBCUserName, String EBCPassword, String Service,
			String screenShotPath) throws Exception {
//		WebDriverWait wait = new WebDriverWait(driver, 300);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		logger.info("[CommonUtil][doEBCLogin] Starts");
		System.out.println(Service);

		if (count != 0) {
			String loggerInMerchant = findElementByXPath(driver, prop, Constants.EBC_LOGGED_IN_MERCHANT_ID).getText();
			System.out.println(loggerInMerchant);
			System.out.println(OrganizationId);

			if (OrganizationId.equalsIgnoreCase(loggerInMerchant)) {

				driver.get(prop.getProperty(Constants.PREIX_EBC_TRANSACTIONS_URL) + TransactionID
						+ prop.getProperty(Constants.SUFFIX_EBC_TRANSACTIONS_URL));
				EBCDetailsCheck(driver, prop, cardDetails, customerDetails, Service, screenShotPath);
			} else {

				// logout and make count to 0
				clickElementByXPath(driver, prop, Constants.EBC_LOGOUT);
				clickElementByXPath(driver, prop, Constants.EBC_LOGOUT_BUTTON);
				count = 0;
			}
		}

		if (count == 0) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get(prop.getProperty(Constants.EBC_LOGIN_URL));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(prop.getProperty(Constants.EBC_ORGANIZATION_ID_ATTRIBUTE))));
			WebElement toClear = driver
					.findElement(By.xpath(prop.getProperty(Constants.EBC_ORGANIZATION_ID_ATTRIBUTE)));
			toClear.sendKeys(Keys.CONTROL + "a");
			toClear.sendKeys(Keys.DELETE);
			setElementValueByXPath(driver, prop, Constants.EBC_ORGANIZATION_ID_ATTRIBUTE, OrganizationId);
			setElementValueByXPath(driver, prop, Constants.EBC_USERNAME_ATTRIBUTE, EBCUserName);
			setElementValueByXPath(driver, prop, Constants.EBC_PASSWORD_ATTRIBUTE, EBCPassword);
			clickElementByXPath(driver, prop, Constants.EBC_LOGIN_SUBMIT_BUTTON);

			if (Service.equalsIgnoreCase("DMACCEPT") || (Service.equalsIgnoreCase("DMREJECT"))) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(prop.getProperty(Constants.EBC_DECISION_MANAGER_ICON))));
				clickElementByXPath(driver, prop, Constants.EBC_DECISION_MANAGER_ICON);
				clickElementByXPath(driver, prop, Constants.EBC_DECISON_MANAGER_CASES);

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(prop.getProperty(Constants.EBC_DECISON_MANAGER_CASES_SEARCH))));
				setElementValueByXPath(driver, prop, Constants.EBC_DECISON_MANAGER_CASES_SEARCH, TransactionID);
				findElementByXPath(driver, prop, Constants.EBC_DECISON_MANAGER_CASES_SEARCH).sendKeys(Keys.ENTER);
				if (Service.equalsIgnoreCase("DMACCEPT")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath(prop.getProperty(Constants.EBC_DECISION_MANAGER_ACCEPT))));
					clickElementByXPath(driver, prop, Constants.EBC_DECISION_MANAGER_ACCEPT);
				} else {
					clickElementByXPath(driver, prop, Constants.EBC_DECISION_MANAGER_REJECT);
				}
				sleep(SLEEP_TIMER_5);
				setElementValueByXPath(driver, prop, Constants.EBC_DECISION_MANAGER_COMMENTS, TransactionID);
				clickElementByXPath(driver, prop, Constants.EBC_DECISION_MANAGER_SUBMIT_BUTTON);
				sleep(SLEEP_TIMER_10);
				clickElementByXPath(driver, prop, Constants.EBC_LOGOUT);
				clickElementByXPath(driver, prop, Constants.EBC_LOGOUT_BUTTON);
				switchToWindow(driver, tabs.get(2));
				switchToWindow(driver, tabs.get(1));
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(prop.getProperty(Constants.EBC_TRANSACTION_MANAGEMENT_ICON))));
				clickElementByXPath(driver, prop, Constants.EBC_TRANSACTION_MANAGEMENT_ICON);
				clickElementByXPath(driver, prop, Constants.EBC_TRANSACTION_MANAGEMENT_TRANSACTIONS);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(prop.getProperty(Constants.EBC_TRANSACTION_SIDEBAR))));
				clickElementByXPath(driver, prop, Constants.EBC_TRANSACTION_SIDEBAR);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(prop.getProperty(Constants.EBC_TRANSACTION_REQUESTID))));
				sleep(SLEEP_TIMER_10);
				setElementValueByXPath(driver, prop, Constants.EBC_TRANSACTION_REQUESTID, TransactionID);
				clickElementByXPath(driver, prop, Constants.EBC_TRANSACTION_QUICKSEARCH_BUTTON);
				clickElementByXPath(driver, prop, Constants.EBC_TRANSACTION_REQUESTID_AFTERSEARCH);
				sleep(SLEEP_TIMER_5);
				EBCDetailsCheck(driver, prop, cardDetails, customerDetails, Service, screenShotPath);
				count++;
			}
		}

		logger.info("[CommonUtil][doEBCLogin] Ends");

	}

	public static void EBCDetailsCheck(WebDriver driver, Properties prop, String cardDetails[],
			String customerDetails[], String Service, String screenShotPath) throws Exception {
		logger.info("[CommonUtil][EBCDetailsCheck] Starts");
		System.out.println(Service);
		String expirationMonth = null;
		String expirationYear = null;
		String expirationDate = null;
		String cardSuffix = null;
		int fieldCount = 0;
		if ((cardDetails != null)) {
			System.out.println("Inside");

			expirationDate = cardDetails[1];
			System.out.println(expirationDate);
			cardSuffix = cardDetails[0].substring(12, 16);
			System.out.println(cardSuffix);
		}
	//	WebDriverWait wait = new WebDriverWait(driver, 300);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(prop.getProperty(Constants.CYBS_EBCCHECK_TRANSACTION_HISTORY))));

		scrollToCustomerInfo(driver);
		scrollToPaymentInfo(driver);
		System.out.println("line622");
		String BillingAddress = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_BILLING_ADDRESS)))
				.getText();
		System.out.println("line626");
		String BillingCity = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_BILLING_CITY)))
				.getText();
		System.out.println("Line629");
		String BillingState = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_BILLING_STATE)))
				.getText();
		System.out.println("Line631");
		String BillingZipCode = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_BILLING_ZIPCODE)))
				.getText();
		System.out.println("Line635");
		String BillingCountry = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_BILLING_COUNTRY)))
				.getText();

		System.out.println("Line639");

		String ReasonCode = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_REASON_CODE))).getText();
		String PartnerSolutionID = driver.findElement(By.xpath(prop.getProperty(Constants.EBC_PARTNER_SOLUTION_ID)))
				.getText();
		String Applications = driver
				.findElement(By.xpath(prop.getProperty(Constants.EBC_REQUEST_INFORMATION_APPLICATIONS))).getText();
		String message[] = Applications.split("\\r?\\n");

		if ((Constants.HUNDRED).equals(ReasonCode) && (Constants.PARTNER_SOLUTION_ID).equals(PartnerSolutionID)) {
			System.out.println("Inside 100 code");
			if (Service.equalsIgnoreCase(Constants.AUTH) || Service.equalsIgnoreCase(Constants.AUTHORIZE)) {
				String AccountSuffix = driver
						.findElement(By.xpath(prop.getProperty(Constants.EBC_PAYMENT_INSTRUMENT_ACCOUNT_SUFFIX)))
						.getText();
				System.out.println("Account num"+AccountSuffix);
				String ExpirationDate = driver
						.findElement(By.xpath(prop.getProperty(Constants.EBC_PAYMENT_INSTRUMENT_EXPIRATION_DATE)))
						.getText();

				System.out.println("673" + "  " + ExpirationDate);
				if (cardDetails[2].equalsIgnoreCase(Constants.YES)) {

					fieldCount++;
					if (cardDetails[2].equalsIgnoreCase(Constants.THREE_DS1)
							|| (cardDetails[2].equalsIgnoreCase(Constants.THREE_DS2))) {
						if (expirationDate.equals(ExpirationDate) && (cardSuffix.equals(AccountSuffix))
								&& (Constants.SUBSCRIPTION_CREATION.equals(message[0]))
								&& (Constants.PAYER_AUTHENTICATION.equals(message[1]))
								&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[2]))
								&& (customerDetails[0].equals(BillingAddress))
								&& (customerDetails[1].equals(BillingCity)) && (customerDetails[2].equals(BillingState))
								&& (customerDetails[3].equals(BillingZipCode))
								&& (customerDetails[4].equals(BillingCountry))) {
							Assert.assertTrue(true, "Application matched");
						} else {
							Assert.assertTrue(false, "Required fields did not match");
						}
					} else {
						if (expirationDate.equals(ExpirationDate) && (cardSuffix.equals(AccountSuffix))
								&& (Constants.SUBSCRIPTION_CREATION.equals(message[0]))
								&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[1]))
								&& (customerDetails[0].equals(BillingAddress))
								&& (customerDetails[1].equals(BillingCity)) && (customerDetails[2].equals(BillingState))
								&& (customerDetails[3].equals(BillingZipCode))
								&& (customerDetails[4].equals(BillingCountry))) {
							Assert.assertTrue(true, "Application matched");
						} else {
							Assert.assertTrue(false, "Required fields did not match");
						}
					}
				}
				if (cardDetails[2].equalsIgnoreCase(Constants.NO)) {
					System.out.println("auth 706 " + customerDetails[0]);
					fieldCount++;
					if (cardDetails[2].equalsIgnoreCase(Constants.THREE_DS1)
							|| (cardDetails[2].equalsIgnoreCase(Constants.THREE_DS2))) {
						if (expirationDate.equals(ExpirationDate) && (cardSuffix.equals(AccountSuffix))
								&& (Constants.PAYER_AUTHENTICATION.equals(message[0]))
								&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[1]))
								&& (customerDetails[0].equals(BillingAddress))
								&& (customerDetails[1].equals(BillingCity)) && (customerDetails[2].equals(BillingState))
								&& (customerDetails[3].equals(BillingZipCode))
								&& (customerDetails[4].equals(BillingCountry))) {
							System.out.println("if660");
							Assert.assertTrue(true, "Application matched");
						} else {
							Assert.assertTrue(false, "Required fields did not match");
						}
					} else {
						System.out.println("Exp date EBC "+ExpirationDate);
						System.out.println("Exp date xl  "+expirationDate);
						System.out.println("Acc Suff EBC "+AccountSuffix);
						System.out.println("Card Suff "+cardSuffix);
						System.out.println("Billing addr EBC "+BillingAddress);
						System.out.println("Billing addr xl "+customerDetails[0]);
						System.out.println("Billing addr City EBC "+customerDetails[1]);
						System.out.println("Billing addr City xl "+BillingCity);
						if (expirationDate.equals(ExpirationDate) && (cardSuffix.equals(AccountSuffix))
								&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(Applications))
								&& (customerDetails[0].equals(BillingAddress))
								&& (customerDetails[1].equals(BillingCity))
								// && (customerDetails[2].equals(BillingState))
								&& (customerDetails[3].equals(BillingZipCode))
								// && (customerDetails[4].equals(BillingCountry))
								) {
							System.out.println("if673");
							Assert.assertTrue(true, "Application matched");
						} else {
							Assert.assertTrue(false, "Required fields did not match");
						}
					}
				}
			} else if (Constants.CAPTURE.equalsIgnoreCase(Service)) {
				System.out.println("capture");

				if (Constants.CREDIT_CARD_SETTLEMENT.equals(Applications)) {
					fieldCount++;
					Assert.assertTrue(true, "Application matched");
				} else {
					System.out.println("assert false");
					Assert.assertTrue(false, "Required fields did not match");
				}
			} else if (Constants.REFUND.equalsIgnoreCase(Service)) {
				if (Constants.CREDIT_CARD_CREDIT.equals(Applications)) {
					fieldCount++;
					Assert.assertTrue(true, "Application matched");
				} else {
					Assert.assertTrue(false, "Required fields did not match");
				}
			} else if (Constants.VOID.equalsIgnoreCase(Service)) {
				if (Constants.VOIDED_TRANSACTIONS.equals(Applications)) {
					fieldCount++;
					Assert.assertTrue(true, "Application matched");
				} else {
					Assert.assertTrue(false, "Required fields did not match");
				}
			} else if (Constants.SALE.equalsIgnoreCase(Service)) {
				if (Constants.THREE_DS1.equalsIgnoreCase(cardDetails[4])
						|| (Constants.THREE_DS2.equalsIgnoreCase(cardDetails[4]))) {

					if (Constants.PAYER_AUTHENTICATION.equals(message[0])
							&& Constants.CREDIT_CARD_AUTHORIZATION.equals(message[0])
							&& (Constants.CREDIT_CARD_SETTLEMENT.equals(message[1]))) {
						fieldCount++;
						Assert.assertTrue(true, "Application matched");
					} else {
						Assert.assertTrue(false, "Required fields did not match");
					}
				} else {
					if (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[0])
							&& (Constants.CREDIT_CARD_SETTLEMENT.equals(message[1]))) {
						fieldCount++;
						Assert.assertTrue(true, "Application matched");
					} else {
						Assert.assertTrue(false, "Required fields did not match");
					}
				}

			} else {
				logger.info("[CommonUtil][EBCDetailsCheck] reason code or parter solution ID mismatch");
			}
		} else if ("480".equals(ReasonCode) && (Constants.PARTNER_SOLUTION_ID).equals(PartnerSolutionID)) {
			if (Constants.DM_AUTH.equalsIgnoreCase(Service)) {
				if (Constants.DECISION_MANAGER.equals(message[0])
						&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[1]))) {
					fieldCount++;
					Assert.assertTrue(true, "Application matched");
				} else {
					Assert.assertTrue(false, "Required fields did not match");
				}
			}
			if (Constants.DM_SALE.equalsIgnoreCase(Service)) {
				if (Constants.DECISION_MANAGER.equals(message[0])
						&& (Constants.CREDIT_CARD_AUTHORIZATION.equals(message[1]))
						&& (Constants.CREDIT_CARD_SETTLEMENT.equals(message[2]))) {
					fieldCount++;
					Assert.assertTrue(true, "Application matched");
				} else {
					Assert.assertTrue(false, "Required fields did not match");
				}
			}
		} else {
			Assert.assertTrue(false, "Required fields did not match");
		}
		System.out.println("700");
		takeScreenShot(driver, screenShotPath, Constants.CYBS_EBCCHECK_PAYMENT_INFO);

		scrollToLineItem(driver);
		takeScreenShot(driver, screenShotPath, Constants.CYBS_EBCCHECK_LINEITEM);
		if (fieldCount > 0 && (Service.equalsIgnoreCase(Constants.AUTH) || Service.equals(Constants.AUTHORIZE))) {
			Reporter.log(Service + Constants.COLON
					+ " Validated EBC Field - Customer information, Reason code, Applications, Partner Solution Id, Account suffix, Expiration date");
		} else if (fieldCount > 0 && !(Service.equalsIgnoreCase(Constants.AUTH))) {
			Reporter.log(
					Service + Constants.COLON + "Validated EBC Field - Reason code, Applications, Partner Solution Id");
		}
		logger.info("[CommonUtil][EBCDetailsCheck] Ends");

	}

	public static WebElement findElementByLinkText(WebDriver driver, String string) {
		WebElement element = null;
		if (null != string) {
			element = driver.findElement(By.linkText(string));
		} else {
			logger.info("[CommonUtil][setElementValueByXPath] text is Null :" + string);
		}
		return element;
	}

	public static WebElement clickElementByLinkText(WebDriver driver, String LinkText) {
		WebElement element = null;
		Actions actions = new Actions(driver);
		if (null != LinkText) {
			logger.info("[CommonUtil][clickElementByLinkText] before click LinkText=" + LinkText);
			element = findElementByLinkText(driver, LinkText);
			// element.click();
			actions.doubleClick(element);
			logger.info("[CommonUtil][clickElementByXPath] after click LinkText=" + LinkText);
		} else {
			logger.info("[CommonUtil][setElementValueByXPath] LinkText is Null :" + LinkText);
		}
		return element;

	}

	@SuppressWarnings("static-access")
	public static void sleep(int time) {
		logger.info("[CommonUtil][sleep] for " + time + " ms");
		try {
			Thread.currentThread().sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("[CommonUtil][sleep] ends");
	}

	public static void clickLinkByXPath(WebDriver driver, Properties prop, String string) {
		logger.info("[CommonUtil][clickLinkByXpath] starts");
		WebElement element = null;
		executor = (JavascriptExecutor) driver;
		element = findElementByXPath(driver, prop, string);
		executor.executeScript("arguments[0].click();", element);
		logger.info("[CommonUtil][clickLinkByXpath] ends");
	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static WebElement findElementById(WebDriver driver, Properties prop, String Id) {
		logger.info("[CommonUtil][findElementById] Starts");
		String idInProp = null;
		WebElement element = null;
		logger.info("[CommonUtil][findElementById] element Id=" + Id);
		if (null != Id) {
			idInProp = prop.getProperty(Id);
			logger.info("[CommonUtil][findElementById] element id=" + idInProp);
			element = driver.findElement(By.id(idInProp));
		} else {
			logger.info("[CommonUtil][findElementById] Id is null.");
		}
		logger.info("[CommonUtil][findElementById] Ends" + element);
		return element;
	}

	public static WebElement findElementByName(WebDriver driver, Properties prop, String name) {
		String nameInProp = null;
		WebElement element = null;
		logger.info("[CommonUtil][findElementByName] element name=" + name);
		if (null != name) {
			nameInProp = prop.getProperty(name);
			element = driver.findElement(By.name(nameInProp));
		} else {
			logger.info("[CommonUtil][findElementByName] name is null.");
		}
		return element;
	}

	public static WebElement findElementById(WebDriver driver, String Id) {
		WebElement element = null;
		String idInProp = null;
		if (null != Id) {
			idInProp = prop.getProperty(Id);
			logger.info("[CommonUtil][findElementById] element Id=" + idInProp);
			if (null != idInProp) {
				element = driver.findElement(By.id(idInProp));
			} else {
				logger.info("[CommonUtil][findElementById] Id is null.");
			}
		}
		return element;
	}

	public static WebElement clickElementById(WebDriver driver, String Id) {
		WebElement element = null;
		String idInProp = null;
		if (null != Id) {
			idInProp = prop.getProperty(Id);
			logger.info("[CommonUtil][findElementById] element Id=" + idInProp);
			if (null != idInProp) {
				element = driver.findElement(By.id(idInProp));
				// element.click();
				element.sendKeys(Keys.ENTER);
			} else {
				logger.info("[CommonUtil][findElementById] Id is null.");
			}
		}
		return element;
	}
	public static WebElement findElementByclassName(WebDriver driver, Properties prop, String className) {
		String xPathInProp = null;
		WebElement element = null;
		logger.info("[findElementByXPath] xPath=" + className);
		if (null != className) {
			xPathInProp = prop.getProperty(className);
			logger.info("[findElementByXPath] xPathInProp=" + xPathInProp);
			if (null != xPathInProp) {
				try {
					logger.info("[findElementByXPath] By.xpath(xPathInProp)=" + By.className(xPathInProp));
					if (driver.findElements(By.className(xPathInProp)).size() != 0) {
						element = driver.findElement(By.className(xPathInProp));
						logger.info("[findElementByXPath] element.isDisplayed()=" + element.isDisplayed());
					}
				} catch (Throwable e) {
					logger.info("[findElementByXPath] No Element found for given xPathInProp:" + xPathInProp);
					e.printStackTrace();
				} finally {
					logger.info("[findElementByXPath] finally called");
				}
			} else {
				logger.info("[findElementByXPath] xPathInProp is Null:" + xPathInProp);
			}
		} else {
			logger.info("[findElementByXPath] xPath is Null");
		}
		return element;
	}

	public static void clickElementByclassName(WebDriver driver, Properties prop, String className) {
		WebElement element = null;
		logger.info("[clickElementByXPath] xPath=" + className);
		if (null != className) {
			element = findElementByclassName(driver, prop, className);
			if (null != element) {
				logger.info("[clickElementByXPath] before click xPath=" + className);
				element.click();
				logger.info("[clickElementByXPath] after click xPath=" + className);
			} else {
				logger.info("[clickElementByXPath] Not able to set the value element is Null for xPath=" + className);
			}
		} else {
			logger.info("[clickElementByXPath] xPath is Null for given xPath:" + className);
		}
	}


	public static List<WebElement> findElementsByClassName(WebDriver driver, String className) {
		List<WebElement> elements = null;
		logger.info("[CommonUtil][findElementByClassName] element className=" + className);
		if (null != className) {
			elements = driver.findElements(By.className(className));
		} else {
			logger.info("[CommonUtil][findElementByClassName] className is null.");
		}
		return elements;
	}
	public static void doRefreshIfNoElementByXPath(WebDriver driver, Properties prop, String xPath) {
		boolean isNeedRefresh = true;
		int refreshAttemptCount = 0;
		WebElement element = null;
		element = findElementByXPath(driver, prop, xPath);
		do {
			refreshAttemptCount++;
			if (null == element) {
				driver.navigate().refresh();
			} else {
				isNeedRefresh = (refreshAttemptCount > 3) ? false : true;
			}
		} while (isNeedRefresh);
	}

	public static boolean isNumeric(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void addLogToTestNGReport(String serviceName, String TransactionID) {
		Reporter.log(serviceName + Constants.TRANSACTION_ID_COLON + Constants.A_HREF_EQUAL
				+ prop.getProperty(Constants.PREIX_EBC_TRANSACTIONS_URL) + TransactionID
				+ prop.getProperty(Constants.SUFFIX_EBC_TRANSACTIONS_URL) + Constants.SINGLE_QUOTE_CLOSING_TAG
				+ TransactionID + Constants.CLOSING_A_TAG);
	}

	public static void addLogToTestNGReportOrderID(String serviceName, String OrderReferenceNumber) {
		Reporter.log(serviceName + OrderReferenceNumber);
	}
	public static String convertFileToDataURL(File file) {
		try {
			byte[] fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(file);
			byte[] base64 = Base64.encodeBase64(fileContent);
			String base64String = new String(base64);
			return "data:image/png;base64," + base64String;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addScreenshotToTestNGReport(WebDriver driver) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dataURL = convertFileToDataURL(srcFile);

		String dirPath = (System.getProperty(Constants.USER_DIR) + Constants.SCREENSHOTPATH);
		DateFormat df = new SimpleDateFormat("yyyyMMdd_HHMMSS");
		Date date = new Date();
		String formattedDate = df.format(date);
		File screenshotName = new File(dirPath + formattedDate + Constants._JPG);
		FileUtils.copyFile(srcFile, screenshotName);

		// Reporter.log("<br><img src ='" + screenshotName + "' height = '400'
		// width='800'/><br>");

		Reporter.log("<br><img src ='" + dataURL + "' height = '400' width='800'/><br>");
	}



	public static void clearElementByXPath(WebDriver driver, Properties prop, String xPath) {
		WebElement element = null;
		logger.info("[CommonUtil][clickElementByXPath] xPath=" + xPath);
		if (null != xPath) {
			element = findElementByXPath(driver, prop, xPath);
			if (null != element) {
				logger.info("[CommonUtil][clickElementByXPath] before click xPath=" + xPath);
				element.clear();
				logger.info("[CommonUtil][clickElementByXPath] after click xPath=" + xPath);
			} else {
				logger.info("[CommonUtil][clickElementByXPath] Not able to set the value element is Null for xPath="
						+ xPath);
			}
		} else {
			logger.info("[CommonUtil][clickElementByXPath] xPath is Null for given xPath:" + xPath);
		}
	}

	public static void checkEBCProfile(WebDriver driver, String ProfileName) {
		WebElement profileTable = findElementByXPath(driver, prop, Constants.PROFILE_TABLE);
		String profileTableContent = profileTable.getAttribute(Constants.INNERHTML);
		int start = 0;
		int end = 0;
		String CheckboxId = null;
		StringBuffer buf = null;
		if (profileTableContent.contains(ProfileName)) {
			logger.info(profileTableContent.lastIndexOf(ProfileName));
			buf = new StringBuffer(profileTableContent);
			end = profileTableContent.lastIndexOf(ProfileName);
			profileTableContent = buf.replace(start, end, Constants.EMPTY_STRING).toString();
			buf = new StringBuffer(profileTableContent);
			start = profileTableContent.indexOf(Constants.CHECKBOX_HIPHEN_HIPHEN);
			profileTableContent = buf.replace(0, start, Constants.EMPTY_STRING).toString();
			CheckboxId = StringUtils.substringBefore(profileTableContent, "\"");
			clickElementById(driver, CheckboxId);
		}
	}

	public static int i = 0;



	public static void deleteFilesInFolder(File srcDir) {
		String[] files = srcDir.list();
		for (String file : files) {
			File currentFile = new File(srcDir.getPath(), file);
			currentFile.delete();
		}
	}

	public void scrollToBottom(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript(Constants.SCROLL_TO_BOTTOM_OF_THE_PAGE);
	}

	public static void scrollToTop(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript(Constants.SCROLL_TO_TOP_OF_THE_PAGE);
	}

	public void scrollToDetails(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript(Constants.SCROLL_TO_THREE_FOURTH_OF_THE_PAGE);
	}

	public static void scrollToLineItem(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(prop.getProperty(Constants.EBC_LINEITEMS))));
	}

	public static void scrollToCustomerInfo(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(prop.getProperty(Constants.EBC_CUSTOMER_INFO))));
	}

	public static void scrollToPaymentInfo(WebDriver driver) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(prop.getProperty(Constants.EBC_PAYMENT_INFO))));
	}

	public static void takeScreenShot(WebDriver driver, String screenShotPath, String screenshotName) throws Exception {
		if (Constants.YES.equalsIgnoreCase(prop.getProperty(Constants.SCREENSHOT_ENABLED))) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// The below method will save the screen shot in d drive with name
			File destFile = new File(
					screenShotPath + Constants.DOUBLE_BACKWARD_SLASH + screenshotName + Constants._JPG);
			FileUtils.copyFile(scrFile, destFile);
		} else {
			logger.info("[CommonUtil][takeScreenShot] Screenshot is disabled");
		}
	}

	public static void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void closeCurrentWindow(WebDriver driver) {
		driver.close();
	}

	public static void switchToFrame(WebDriver driver, int frameId) {
		driver.switchTo().frame(frameId);
	}

	public static void switchToWindow(WebDriver driver, String window) {
		driver.switchTo().window(window);
	}

	public static String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public static String getScreenshotsPath(String screenShotModulePath, String ServiceName) throws IOException {
		String screenShotPath = null;
		File dir = new File(screenShotModulePath + Constants.DOUBLE_BACKWARD_SLASH + ServiceName);
		dir.mkdir();
		screenShotPath = screenShotModulePath + Constants.DOUBLE_BACKWARD_SLASH + ServiceName;
		return screenShotPath;
	}
	public static String getScreenshotsModulePath(String moduleName) throws IOException {
		String screenShotModulePath = null;
		String basePath = System.getProperty(Constants.USER_DIR)
				+ prop.getProperty(Constants.BASE_PATH_MULTISCREENSHOT_DIRECTORY);
		screenShotModulePath = getScreenshotsPath(basePath, moduleName);
		return screenShotModulePath;
	}
	public void advancedSecurityPageHandle(WebDriver driver) {
		if (driver.getPageSource().contains(Constants.BROWSER_ADVANCED_TEXT)) {
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_HIDEDETAILS_BUTTON_ID);
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_FOLINK_ID);
		}
	}
	public void coupon(WebDriver driver,String couponCode) {
		clickElementByclassName(driver, prop,Constants.FRONT_OFFICE_COUPON_LINK);
		sleep(SLEEP_TIMER_6);
		setElementValueById(driver, prop,Constants.FRONT_OFFICE_COUPON_BOX,couponCode);
		sleep(SLEEP_TIMER_6);
		clickElementByXPath(driver,prop,Constants.FRONT_OFFICE_COUPON_APPLY_BUTTON);
	}
	public void useNewCard(WebDriver driver) {
		if (driver.getPageSource().contains(Constants.FRONT_OFFICE_USE_NEW_CARD_PAYMENT_METHOD)) {
			clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_USE_NEW_CARD);
		}
	}	
	public void orderConfirmationPageForCreditCard(WebDriver driver,String AdminName, String AdminPassword, String GuestUser,String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		String orderNumber = null;
		String authTransactionId = null;
		String[] cardDetails = { CardNumber, ExpirationMonth, ExpirationYear};
		String[] customerDetails = { BillingStreet, BillingCity, BillingState, BillingZip, BillingCountry };
		sleep(SLEEP_TIMER_40);
		WebElement orderConfirmationNumber = findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ORDER_NUMBER);
		orderNumber = orderConfirmationNumber.getText();
		logger.info(orderNumber);
		try {
			addScreenshotToTestNGReport(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			logoutfrontoffice(driver);
		}
		authTransactionId = BackOfficeCheck(driver,AdminName, AdminPassword, orderNumber, GuestUser, Service);
		if (RequireEBC.equalsIgnoreCase(Constants.YES)) {
			checkTransactionInEBC(driver, cardDetails, customerDetails, authTransactionId, OrganizationId,
					EBCUserName, EBCPassword, Service, null);
		}
	}
	public void orderConfirmationPageForToken(WebDriver driver,String AdminName, String AdminPassword, String GuestUser,String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		String orderNumber = null;
		String authTransactionId = null;
		String[] cardDetails = { CardNumber, ExpirationMonth, ExpirationYear};
		String[] customerDetails = { BillingStreet, BillingCity, BillingState, BillingZip, BillingCountry };
		sleep(SLEEP_TIMER_40);
		WebElement orderConfirmationNumber = findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ORDER_NUMBER);
		orderNumber = orderConfirmationNumber.getText();
		logger.info(orderNumber);
		try {
			addScreenshotToTestNGReport(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if (Constants.YES.equalsIgnoreCase(GuestUser)) {
			logoutfrontoffice(driver);
		}
		else {
		logoutfrontoffice(driver);
		authTransactionId = BackOfficeCheck(driver,AdminName, AdminPassword, orderNumber, GuestUser, Service);
		if (RequireEBC.equalsIgnoreCase(Constants.YES)) {
			checkTransactionInEBC(driver, cardDetails, customerDetails, authTransactionId, OrganizationId,
					EBCUserName, EBCPassword, Service, null);
		}}
	}
	public void orderConfirmationPageForGooglePay(WebDriver driver,String AdminName, String AdminPassword, String GuestUser,String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		String orderNumber = null;
		String authTransactionId = null;
		String[] customerDetails = { BillingStreet, BillingCity, BillingState, BillingZip, BillingCountry };
		sleep(SLEEP_TIMER_40);
		WebElement orderConfirmationNumber = 
				findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ORDER_NUMBER);
		
		orderNumber = orderConfirmationNumber.getText();
		addScreenshotToTestNGReport(driver);
		logger.info(orderNumber);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			logoutfrontoffice(driver);
		}
		authTransactionId = BackOfficeCheck(driver,AdminName, AdminPassword, orderNumber, GuestUser, Service);
		if (RequireEBC.equalsIgnoreCase(Constants.YES)) {
			checkTransactionInEBC(driver,customerDetails, authTransactionId, OrganizationId,
					EBCUserName, EBCPassword, Service, null);
		}
	}
	public void placingOrderUsingGooglePay(WebDriver driver,String Loggedin) {
		WebElement element = findElementByXPath(driver, prop, Constants.FRONT_OFFICE_GPAY_BUTTON);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		sleep(SLEEP_TIMER_5);
		WebElement buttonelement = findElementByXPath(driver, prop, Constants.FRONT_OFFICE_PAYMENT_METHOD_GPAY_BUTTON);
		JavascriptExecutor executorbutton = (JavascriptExecutor) driver;
		executorbutton.executeScript("arguments[0].click();", buttonelement);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String p = it.next();
		String c = it.next();
		switchToWindow(driver, c);
		if (Constants.NO.equalsIgnoreCase(Loggedin)) {
			setElementValueUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_GPAY_EMAIL_BOX, Constants.FRONT_OFFICE_GPAY_EMAIL);
			clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_GPAY_EMAIL_NEXTBUTTON);
			setElementValueUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_GPAY_PASSWORD_BOX, Constants.FRONT_OFFICE_GPAY_PASSWORD);
			clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_GPAY_PASSWORD_NEXTBUTTON);
			sleep(SLEEP_TIMER_10);
			switchToFrame(driver, 0);
			clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_GPAY_IFRAMEPAY_BUTTON);
		}
		else {
			sleep(SLEEP_TIMER_10);
			switchToFrame(driver, 0);
			clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_GPAY_IFRAMEPAY_BUTTON);
		}
		switchToWindow(driver, p);
	}
	
	public void unifiedCheckoutPlacingOrderUsingGooglePay(WebDriver driver, String Loggedin) {
		sleep(SLEEP_TIMER_5);
		// int iframe = driver.findElements(By.xpath("//iframe")).size();
		WebElement iframe = driver.findElement(By.id("__buttonlist"));
		System.out.println(iframe);
		driver.switchTo().frame(iframe);
		// switchToFrame(driver, Constants.ZERO);
		int iframe1 = driver.findElements(By.xpath("//iframe")).size();
		System.out.println(iframe1);
	//	WebDriverWait wait = new WebDriverWait(driver, 60);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// By elementLocator = By.xpath(prop.getProperty(elementKey));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("__gPayContainer"));
		// driver.switchTo().frame("__gPayContainer");
		System.out.println("I m gpay");
		Set<String> handles = driver.getWindowHandles();

		String parenthandle = driver.getWindowHandle();
		// By elementLocator = By.xpath(prop.getProperty(elementKey));
		clickElementUsingExplicitWait(driver, prop, Constants.GPAY_CONTAINER_BUTTON);
		// isElementPresent(driver, prop.getProperty(Constants.GPAY_CONTAINER_BUTTON),
		// SLEEP_TIMER_15).click();
		System.out.println("click me");

		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_8);
		for (String handle : driver.getWindowHandles())
			if (!handles.contains(handle))
				driver.switchTo().window(handle);

		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_4);
		WebElement isPresent = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_GOOGLE_LOGIN_USERNAME_TEXT_FIELD);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		if (isPresent != null) {
			CommonUtil.sleep(CommonUtil.SLEEP_TIMER_3);
			CommonUtil.setElementValueUsingExplicitWait(driver, prop,
					Constants.FRONT_OFFICE_GOOGLE_LOGIN_USERNAME_TEXT_FIELD,
					prop.getProperty(Constants.GOOGLE_PAY_LOGIN_USERNAME));
			CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
			CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.GOOGLE_PAY_LOGIN_USERNAME_NEXT_BTN);
			CommonUtil.sleep(CommonUtil.SLEEP_TIMER_30);
			CommonUtil.setElementValueUsingExplicitWait(driver, prop,
				 Constants.FRONT_OFFICE_GOOGLE_LOGIN_PASSWORD_TEXT_FIELD,
					prop.getProperty(Constants.GOOGLE_PAY_LOGIN_PASSWORD));
//			CommonUtil.isElementPresent(driver, prop.getProperty(Constants.GOOGLE_PAY_LOGIN_PASSWORD_NEXT_BTN),
//					SLEEP_TIMER_15).click();
		} else {
			logger.info("[GooglePay][executeAuth] Already logged in");
		}
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_15);
	//	WebDriverWait waittwo = new WebDriverWait(driver, (60));
		WebDriverWait waittwo = new WebDriverWait(driver, Duration.ofSeconds(60));
		waittwo.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);
		
		By elementLocator = By.xpath(("/html/body/div[1]/c-wiz/div/div/c-wiz/div/div/c-wiz/div[1]/div/div/div/div/div/div[2]/div/div[1]/div/div/span/div/button/span"));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		element.click();
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);
		// *[@id="193"]/span/div/button
		// CommonUtil.sleep(CommonUtil.SLEEP_TIMER_8);
		int iframe2 = driver.findElements(By.xpath("//iframe")).size();
		System.out.println(iframe2);
		driver.switchTo().window(parenthandle);
	}

	
	public void adminLogin(WebDriver driver,String AdminName, String AdminPassword) {

		logger.info("[Service][adminLogin] Starts");
		setElementValueById(driver, prop, Constants.BACK_OFFICE_ADMIN_USERNAME, AdminName);
		setElementValueById(driver, prop, Constants.BACK_OFFICE_ADMIN_PASSWORD, AdminPassword);
		clickElementById(driver, Constants.BACK_OFFICE_LOGIN_SUBMIT);
		logger.info("[Service][adminLogin] Ends");

	}
	public void itemSelection(WebDriver driver,String GuestUser,String ProductName, String ProductQuantity) {
		logger.info("[Service][itemSelection] Starts");
		String[] Items = ProductName.split(Constants.COMMA);
		String[] Quantities = ProductQuantity.split(Constants.COMMA);

		for (int i = 0; i < Items.length; i++) {
			driver.navigate().to(Constants.FRONT_OFFICE_PRODUCT_NAVIGATE + Items[i]);
			findElementByXPath(driver, prop,Constants.FRONT_OFFICE_SEARCH_RESULT).click();
			findElementByName(driver, prop, Constants.FRONT_OFFICE_QUANTITY).clear();
			setElementValueByName(driver, prop, Constants.FRONT_OFFICE_QUANTITY, Quantities[i]);
			findElementByName(driver, prop, Constants.FRONT_OFFICE_ADD_TO_CART).click();
			//findElementByXPath(driver, prop, Constants.FRONT_OFFICE_VIEW_CART).click();
			clickElementByCssSelector(driver,prop,Constants.FRONT_OFFICE_VIEW_CART);
		}
		logger.info("[Service][itemSelection] Ends");
	}
	public void proceedToCheckout(WebDriver driver,String GuestUser) {
		if (Constants.YES.equalsIgnoreCase(GuestUser)) {
			clickElementByXPath(driver,prop,Constants.FRONT_OFFICE_PROCEED_TO_CHECKOUT_GUESTUSER);
		}
		else {
			clickElementByXPath(driver,prop,Constants.FRONT_OFFICE_PROCEED_TO_CHECKOUT_REGISTEREDUSER);
		}
	}
	public void PayerAuthDetails(WebDriver driver) {
		logger.info("Payer auth is validated");
		sleep(SLEEP_TIMER_10);
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer noOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("No. of iframes on the page are " + noOfFrames);
		sleep(SLEEP_TIMER_20);
		driver.switchTo().frame("step-up-iframe-id");
		Actions actions = new Actions(driver);
		actions.sendKeys(Constants.PAYER_AUTH_ONE).build().perform();
		actions.sendKeys(Constants.PAYER_AUTH_TWO).build().perform();
		actions.sendKeys(Constants.PAYER_AUTH_THREE).build().perform();
		actions.sendKeys(Constants.PAYER_AUTH_FOUR).build().perform();
		actions.sendKeys(Keys.ENTER).build().perform();
		sleep(SLEEP_TIMER_10);
		driver.switchTo().defaultContent();
		sleep(SLEEP_TIMER_20);
	}

	public void logoutfrontoffice(WebDriver driver) {
		logger.info("[Service][logoutfrontoffice] Starts");
		clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT);
		clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_LOGOUT);
		logger.info("[Service][logoutfrontoffice] ends");
	}
	public void frontOfficeLogin(WebDriver driver,String UserName, String Password) {
		logger.info("[Service][frontOfficeLogin] Starts");
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_USERNAME, UserName);
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_PASSWORD, Password);
		clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_LOGIN_BUTTON);
		logger.info("[Service][frontOfficeLogin] ends");
	}
	public void getSavedCard(WebDriver driver,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear) {
		logger.info("[Service][getSavedCard] Starts");
	//	Boolean CardText=findElementsByClassName(driver,Constants.FRONT_OFFICE_SAVED_CARD).size()>0;  
//		System.out.print(CardText);
//		if(CardText) {
//			List<WebElement> CardText2=findElementsByClassName(driver,Constants.FRONT_OFFICE_SAVED_CARD);
		Boolean CardText = driver.findElements(By.className("wc-payment-gateway-payment-form-saved-payment-method")).size()>0;
		System.out.print(CardText);
		if (CardText) {
			List<WebElement> CardText2 = driver.findElements(By.className("wc-payment-gateway-payment-form-saved-payment-method"));
			for(WebElement CardText3 : CardText2) {
				String labelText = CardText3.getText();
				logger.info(labelText);
				String cardSuffix = CardNumber.substring(CardNumber.length()-4);
				logger.info(cardSuffix);
				sleep(5000);
				if(labelText.contains(cardSuffix)) {
				    CardText3.click();
				    WebElement CVN = findElementByXPath(driver, prop, Constants.FRONT_OFFICE_SAVED_CARD_CVN);
					setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_SAVED_CARD_CVN, CardSecurityCode);
			//		WebElement cvnInput = driver.findElement(By.id(Constants.FRONT_OFFICE_SAVED_CARD_CVN));
					logger.info(CVN);
					CVN.sendKeys(CardSecurityCode);
					break;
				}
			}    
		}
		logger.info("[Service][getSavedCard] Ends");
	}

	public void addNewCard(WebDriver driver,String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear) {

		logger.info("[Service][addNewCard] Satrts");
		sleep(SLEEP_TIMER_12);
		driver.switchTo().frame(Constants.TWO);
		setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_CARD_NUMBER, CardNumber);
		driver.switchTo().defaultContent();
		try {
			addScreenshotToTestNGReport(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().frame(Constants.THREE);
		setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_SECURITY_NUMBER,
				CardSecurityCode);
		driver.switchTo().defaultContent();
		Select ExpiryMonth = new Select(findElementById(driver,Constants.FRONT_OFFICE_EXPIRY_MONTH));
		ExpiryMonth.selectByValue(ExpirationMonth);
		Select ExpiryYear = new Select(findElementById(driver,Constants.FRONT_OFFICE_EXPIRY_YEAR));
		ExpiryYear.selectByValue(ExpirationYear);
		logger.info("[Service][addNewCard] Ends");
	}
	
	public void shippingMethodLoadHandling(WebDriver driver) {
		try {
			for (int i = 0;; i++) {
				WebElement shippingtext = driver
						.findElement(By.xpath("//*[@id=\"shipping_method\"]"));
				if (shippingtext.getText().contains("Loading shipping rates…")) {
					sleep(SLEEP_TIMER_20);
				} else
					break;
			}
		} 

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	
	public void unifiedCheckoutAddNewCard(WebDriver driver, String BillingFirstName, String BillingLastName, String BillingEmail,
			 String CardNumber, String CardSecurityCode, String ExpirationMonth, 
			String ExpirationYear) {
		logger.info("[Service][unifiedCheckoutAddNewCard] Starts");
		driver.switchTo().frame(Constants.ZERO);
	//	WebDriverWait wait = new WebDriverWait(driver, 50);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	
		sleep(SLEEP_TIMER_20);
	    WebElement paymentmethod1 = driver.findElement(By.id(Constants.MCE_ID));
		 paymentmethod1.click();
		 
		 logger.info("im clicked");
		 driver.switchTo().defaultContent();
		 sleep(SLEEP_TIMER_10);
		 driver.switchTo().frame(Constants.ONE);
		 CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER,
				CardNumber);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		WebElement month_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_MONTH);
		Select month = new Select(month_dropdown);
		month.selectByVisibleText(ExpirationMonth);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_10);
		WebElement Year_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_YEAR);
		Select Year = new Select(Year_dropdown);
		Year.selectByVisibleText(ExpirationYear);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_SECURITY_NUMBER,
				CardSecurityCode);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.findElement(By.id("billing-first-name")).sendKeys(BillingFirstName);
		driver.findElement(By.id("billing-last-name")).sendKeys(BillingLastName);
		CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
	//	driver.findElement(By.id("contact-email")).sendKeys(BillingEmail);
	//	driver.findElement(By.id("contact-phone")).sendKeys(Constants.FRONT_OFFICE_PHONE_NUMBER);
	//	driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/form/div[2]/button")).click(); 
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/div/div/button")).click();
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);  
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.switchTo().defaultContent();
		logger.info("[Service][unifiedCheckoutAddNewCard] Ends");
	}
	
	public void unifiedCheckoutmyaccountAddNewCard(WebDriver driver, String BillingFirstName, String BillingLastName,
			 String CardNumber, String CardSecurityCode, String ExpirationMonth, 
			String ExpirationYear) {
		logger.info("[Service][unifiedCheckoutAddNewCard] Starts");
		driver.switchTo().frame(Constants.ZERO);
	//	WebDriverWait wait = new WebDriverWait(driver, 50);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		sleep(SLEEP_TIMER_15);
	    WebElement paymentmethod1 = driver.findElement(By.id(Constants.MCE_ID));
		 paymentmethod1.click();
		 
		 logger.info("im clicked");
		// driver.findElement(By.id("mce")).click();
		// clickElementUsingExplicitWait(driver,prop,Constants.MCE_ID);
		 driver.switchTo().defaultContent();
		sleep(SLEEP_TIMER_10);
		driver.switchTo().frame(Constants.ONE);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER,
				CardNumber);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		WebElement month_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_MONTH);
		Select month = new Select(month_dropdown);
		month.selectByVisibleText(ExpirationMonth);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_8);
		WebElement Year_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_YEAR);
		Select Year = new Select(Year_dropdown);
		Year.selectByVisibleText(ExpirationYear);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_SECURITY_NUMBER,
				CardSecurityCode);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.findElement(By.id("billing-first-name")).sendKeys(BillingFirstName);
		driver.findElement(By.id("billing-last-name")).sendKeys(BillingLastName);
		CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
	//	driver.findElement(By.id("contact-email")).sendKeys(BillingEmail);
	//	driver.findElement(By.id("contact-phone")).sendKeys(Constants.FRONT_OFFICE_PHONE_NUMBER);
	//	driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/form/div[2]/button")).click();    //*[@id="uc-stepper"]/div[2]/div[2]/div[2]/div/form/div[2]/button
	//	CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);  
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/div/div/button")).click();    //*[@id="uc-stepper"]/div[3]/div[2]/div[2]/div/div/div/button
		//*[@id="uc-stepper"]/div[2]/div[2]/div[2]/div/div/div/button   
		// CommonUtil.clickElementUsingExplicitWait(driver, prop,
		// Constants.FRONT_OFFICE_CC_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.switchTo().defaultContent();
		logger.info("[Service][unifiedCheckoutAddNewCard] Ends");
	}
	
	
	public void unifiedCheckoutTokenziationAddNewCard(WebDriver driver, String BillingFirstName, String BillingLastName, String BillingEmail,
			 String CardNumber, String CardSecurityCode, String ExpirationMonth, 
			String ExpirationYear) {
		logger.info("[Service][unifiedCheckoutTokenziationAddNewCard] Starts");
		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_CHECKBOX_SAVE_CARD);
		driver.switchTo().frame(Constants.ZERO);
//		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		sleep(SLEEP_TIMER_15);
	    WebElement paymentmethod1 = driver.findElement(By.id(Constants.MCE_ID));
		 paymentmethod1.click();
		 
		 logger.info("im clicked");
		// driver.findElement(By.id("mce")).click();
		// clickElementUsingExplicitWait(driver,prop,Constants.MCE_ID);
		 driver.switchTo().defaultContent();
		sleep(SLEEP_TIMER_10);
		driver.switchTo().frame(Constants.ONE);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER,
				CardNumber);

	//	driver.findElement(By.id("card-number")).sendKeys(CardNumber);
		// CommonUtil.setElementValueUsingExplicitWait(driver, prop,
		// Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER, CardNumber);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		WebElement month_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_MONTH);
		Select month = new Select(month_dropdown);
		month.selectByVisibleText(ExpirationMonth);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_8);
		WebElement Year_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_YEAR);
		Select Year = new Select(Year_dropdown);
		Year.selectByVisibleText(ExpirationYear);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_SECURITY_NUMBER,
				CardSecurityCode);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.findElement(By.id("billing-first-name")).sendKeys(BillingFirstName);
		driver.findElement(By.id("billing-last-name")).sendKeys(BillingLastName);
		CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		driver.findElement(By.id("contact-email")).sendKeys(BillingEmail);
		driver.findElement(By.id("contact-phone")).sendKeys(Constants.FRONT_OFFICE_PHONE_NUMBER);
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/form/div[2]/button")).click();    //*[@id="uc-stepper"]/div[2]/div[2]/div[2]/div/form/div[2]/button
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);  
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[3]/div[2]/div[2]/div/div/div/button")).click();    //*[@id="uc-stepper"]/div[3]/div[2]/div[2]/div/div/div/button
	
		// CommonUtil.clickElementUsingExplicitWait(driver, prop,
		// Constants.FRONT_OFFICE_CC_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.switchTo().defaultContent();
		logger.info("[Service][unifiedCheckoutTokenziationAddNewCard] Ends");
	}
	
	public void unifiedCheckoutNullcase(WebDriver driver, String ShippingFirstName, String ShippingLastName, String BillingEmail,
			 String CardNumber, String CardSecurityCode, String ExpirationMonth, 
			String ExpirationYear) {
		logger.info("[Service][unifiedCheckoutAddNewCard] Starts");
		driver.switchTo().frame(Constants.ZERO);
	//	WebDriverWait wait = new WebDriverWait(driver, 50);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	//	WebElement paymentmethod1 = wait
			//.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/button")));
		// for (int i = 0; i < 100; i++) {

	//	paymentmethod1.click();
      //driver.findElement(By.xpath("//button[@class='btn']")).click();
		//clickElementUsingExplicitWait(driver, prop, "//button[@class='btn']");
//	 WebElement paymentmethod1 = dr
		sleep(SLEEP_TIMER_15);
	 WebElement paymentmethod1 = driver.findElement(By.id(Constants.MCE_ID));
		 paymentmethod1.click();
		 
		 logger.info("im clicked");
		// driver.findElement(By.id("mce")).click();
		// clickElementUsingExplicitWait(driver,prop,Constants.MCE_ID);
		 driver.switchTo().defaultContent();
		sleep(SLEEP_TIMER_10);
		driver.switchTo().frame(Constants.ONE);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER,
				CardNumber);

	//	driver.findElement(By.id("card-number")).sendKeys(CardNumber);
		// CommonUtil.setElementValueUsingExplicitWait(driver, prop,
		// Constants.FRONT_OFFICE_ADD_NEW_UC_CARD_NUMBER, CardNumber);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		WebElement month_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_MONTH);
		Select month = new Select(month_dropdown);
		month.selectByVisibleText(ExpirationMonth);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_8);
		WebElement Year_dropdown = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_ADD_NEW_EXPIRATION_YEAR);
		Select Year = new Select(Year_dropdown);
		Year.selectByVisibleText(ExpirationYear);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		CommonUtil.setElementValueByXPath(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_UC_SECURITY_NUMBER,
				CardSecurityCode);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.findElement(By.id("billing-first-name")).sendKeys(ShippingFirstName);
		driver.findElement(By.id("billing-last-name")).sendKeys(ShippingLastName);
		CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_ADD_NEW_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		driver.findElement(By.id("contact-email")).sendKeys(BillingEmail);
		driver.findElement(By.id("contact-phone")).sendKeys(Constants.FRONT_OFFICE_PHONE_NUMBER);
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[2]/div[2]/div[2]/div/form/div[2]/button")).click();    //*[@id="uc-stepper"]/div[2]/div[2]/div[2]/div/form/div[2]/button
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);  
		driver.findElement(By.xpath("//*[@id=\"uc-stepper\"]/div[3]/div[2]/div[2]/div/div/div/button")).click();    //*[@id="uc-stepper"]/div[3]/div[2]/div[2]/div/div/div/button
	
		// CommonUtil.clickElementUsingExplicitWait(driver, prop,
		// Constants.FRONT_OFFICE_CC_CONFIRMORDER_BUTTON);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
		driver.switchTo().defaultContent();
		logger.info("[Service][unifiedCheckoutAddNewCard] Ends");
	}
	
	public void unifiedCheckoutCreditCardConfguration(String TransactionType, String Tokenization, String Environment,
			String MerchantId, String MerchantKeyId, String MerchantSecretKey) {
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/table/tbody/tr/td/table/tbody/tr[9]/td[5]/a");
		CommonUtil.clickElementById(driver, "woocommerce_isv_unified_checkout_enabled");
		CommonUtil.setElementValueById(driver, prop, "woocommerce_isv_unified_checkout_transaction_type",
				TransactionType);
		if (Tokenization.equalsIgnoreCase(Constants.YES)) {
			CommonUtil.clickElementById(driver, "woocommerce_isv_unified_checkout_tokenization");
		}
		CommonUtil.setElementValueById(driver, prop, "woocommerce_isv_unified_checkout_environment", Environment);
		CommonUtil.findElementByXPath(driver, prop, "//*[@id='woocommerce_isv_unified_checkout_test_merchant_id']")
				.clear();
		CommonUtil.setElementValueByXPath(driver, prop, "//*[@id='woocommerce_isv_unified_checkout_test_merchant_id']",
				MerchantId);
		CommonUtil.findElementByXPath(driver, prop, "//*[@id='woocommerce_isv_unified_checkout_test_api_key']").clear();
		CommonUtil.setElementValueByXPath(driver, prop, "//*[@id='woocommerce_isv_unified_checkout_test_api_key']",
				MerchantKeyId);
		CommonUtil
				.findElementByXPath(driver, prop, "//*[@id='woocommerce_isv_unified_checkout_test_api_shared_secret']")
				.clear();
		CommonUtil.setElementValueByXPath(driver, prop,
				"//*[@id='woocommerce_isv_unified_checkout_test_api_shared_secret']", MerchantSecretKey);
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/p[2]/button");
	}

	public void unifiedCheckoutFraudManagement(String Service) {
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/table/tbody/tr/td/table/tbody/tr[9]/td[5]/a");
		if (Service.equalsIgnoreCase(Constants.SALE_DECISION_MANAGER)) {
			CommonUtil.setElementValueById(driver, prop, "woocommerce_isv_unified_checkout_environment",
					Constants.CHARGE);
		} else {
			CommonUtil.setElementValueById(driver, prop, "woocommerce_isv_unified_checkout_environment",
					Constants.AUTHORIZATION);
		}
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/p[2]/button");
	}

	public void unifiedCheckoutSaleConfiguration() {
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/table/tbody/tr/td/table/tbody/tr[9]/td[5]/a");
		CommonUtil.setElementValueById(driver, prop, "woocommerce_isv_unified_checkout_environment", Constants.CHARGE);
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/p[2]/button");
	}

	public void unifiedCheckoutPayerAuthConfiguration() {
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/table/tbody/tr/td/table/tbody/tr[9]/td[5]/a");
		CommonUtil.clickElementById(driver, "woocommerce_isv_unified_checkout_enable_threed_secure");
		CommonUtil.clickElementByXPath(driver, prop, "//*[@id='mainform']/p[2]/button");
	}

	public void placeOrderButton(WebDriver driver, String GuestUser) {
		if (GuestUser.equalsIgnoreCase(Constants.NO)) {
			CommonUtil.clickElementByXPath(driver, prop,
					Constants.FRONT_OFFICE_CHECKOUT_REGISTEREDUSER_PLACE_ORDER_BUTTON);
		} else {
			CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_CHECKOUT_GUESTUSER_PLACE_ORDER_BUTTON);
		}
	}

    
	public void executeCapture(WebDriver driver,String Service) throws InterruptedException, IOException {

		logger.info("[Service][executeCapture] Starts");
		WebElement capturebutton = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_CAPTURE_CHARGE);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", capturebutton);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_CAPTURE_CHARGE);
		sleep(SLEEP_TIMER_5);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		sleep(SLEEP_TIMER_25);
		Alert alt = driver.switchTo().alert();
		String captureId = alt.getText();
		System.out.println(captureId);
		//captureId = captureId.substring(captureId.indexOf("(")  + 16);
		captureId = captureId.substring(captureId.indexOf("D") + 1, captureId.indexOf(")")).trim();
		logger.info("[Service][executeCapture] Capture ID " + captureId);
		alt.accept();
		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_PROCESSING)) {
			Assert.assertTrue(true, "Correct status");
		} else {
			Assert.assertTrue(false, "Incorrect status");
		}
		addLogToTestNGReport(Constants.CAPTURE, captureId);
		if (Service.equalsIgnoreCase(Constants.CAPTURE)) {
			logoutBackOffice(driver);
		}
		logger.info("[Service][executeCapture] Ends");

	}
	public void TaxesConfiguration(WebDriver driver,String AdminName, String AdminPassword, String CountryCode, String BillingZip, String BillingCity, String Rate) {

		logger.info("[Service][TaxesConfiguration] Starts");
		driver = setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		if (driver.getPageSource().contains(Constants.BROWSER_ADVANCED_TEXT)) {
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_HIDEDETAILS_BUTTON_ID);
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_FOLINK_ID);
		}
		adminLogin(driver,AdminName, AdminPassword);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		sleep(SLEEP_TIMER_5);
		if (Constants.ZERO == TaxConfigure) {
			clickElementByXPath(driver, prop, Constants.BACK_OFFICE_TAX_ENABLE_CHECKBOX);
			TaxConfigure++;
		}
		sleep(SLEEP_TIMER_5);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_TAX_TAB_NAVIGATE);
		sleep(SLEEP_TIMER_10);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_STANDARD_RATE_CLICK);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_INSERT_ROW_OPTION);
		sleep(SLEEP_TIMER_10);
		findElementByXPath(driver,prop,Constants.BACK_OFFICE_TAX_COUNTRYCODE).clear();
		setElementValueByXPath(driver,prop,Constants.BACK_OFFICE_TAX_COUNTRYCODE,CountryCode);
		findElementByXPath(driver,prop,Constants.BACK_OFFICE_TAX_BILLLINGZIP).clear();
		setElementValueByXPath(driver,prop,Constants.BACK_OFFICE_TAX_BILLLINGZIP,BillingZip);
		findElementByXPath(driver,prop,Constants.BACK_OFFICE_TAX_BILLLINGCITY).clear();
		setElementValueByXPath(driver,prop,Constants.BACK_OFFICE_TAX_BILLLINGCITY,BillingCity);
		findElementByXPath(driver,prop,Constants.BACK_OFFICE_TAX_RATE).clear();
		setElementValueByXPath(driver,prop,Constants.BACK_OFFICE_TAX_RATE,Rate);
		sleep(SLEEP_TIMER_5);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_TAX_SAVE_BUTTON);
		sleep(SLEEP_TIMER_15);
		logoutBackOffice(driver);
		logger.info("[Service][TaxesConfiguration] Ends");
	}
	
	public void executeAuthReversal(WebDriver driver,String Service,String RefundQuantity,String RefundAmount) {

		logger.info("[Service][executeAuthReversal] Starts");
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND);
		int i = 0;
		String[] RefundQuantityList = RefundQuantity.split(Constants.COMMA);
		String[] RefundAmountList = RefundAmount.split(Constants.COMMA);

		WebElement table = findElementByXPath(driver, prop, Constants.BACK_OFFICE_PRODUCT_TABLE_XPATH);
		List<WebElement> allRows = table.findElements(By.tagName(Constants.TR));
		List<WebElement> cells = null;
		for (WebElement row : allRows) {
			if (i < RefundQuantityList.length) {
				int j = i + 1;
				WebElement refundinputquantity = driver
						.findElement(By.xpath(prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_PREFIX) + j
								+ prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_SUFFIX)));
				refundinputquantity.sendKeys(RefundQuantityList[i]);
				i++;
			}
		}
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND_BOFA);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		sleep(SLEEP_TIMER_45);
		WebElement transactionId = findElementByXPath(driver, prop,
				Constants.BACK_OFFICE_ORDERS_TRANSACTION_ID);
		String authTransactionId = transactionId.getText();

		authTransactionId = authTransactionId.substring(authTransactionId.indexOf("(") + 16,
				authTransactionId.indexOf(")"));
		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		logger.info("[Service][executeAuthReversal] Order Status : " + orderStatus);
		if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_CANCELLED)) {
			Assert.assertTrue(true, "Correct status");
			addLogToTestNGReport(Constants.AUTH_REVERSAL, authTransactionId);
		} else {
			Assert.assertTrue(false, "Incorrect status");
		}
		if (Service.equalsIgnoreCase("AuthReversal")) {
			logoutBackOffice(driver);
		}
		logger.info("[Service][executeAuthReversal] Ends");

	}


	public void executeRefund(WebDriver driver,String RefundQuantity, String RefundAmount) {

		logger.info("[Service][executeRefund] Starts");
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND);
		int i = 0;
		String[] RefundQuantityList = RefundQuantity.split(Constants.COMMA);
		String[] RefundAmountList = RefundAmount.split(Constants.COMMA);

		WebElement table = findElementByXPath(driver, prop, Constants.BACK_OFFICE_PRODUCT_TABLE_XPATH);
		List<WebElement> allRows = table.findElements(By.tagName(Constants.TR));
		List<WebElement> cells = null;
		for (WebElement row : allRows) {
			if (i < RefundQuantityList.length) {
				int j = i + 1;
				WebElement refundinputquantity = driver
						.findElement(By.xpath(prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_PREFIX) + j
								+ prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_SUFFIX)));
				refundinputquantity.sendKeys(RefundQuantityList[i]);
				i++;
			}
		}
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND_BOFA);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		sleep(SLEEP_TIMER_60);
		WebElement transactionId = findElementByXPath(driver, prop,
				Constants.BACK_OFFICE_ORDERS_REFUND_TRANSACTION_ID);
		String authTransactionId = transactionId.getText();

		authTransactionId = authTransactionId.substring(authTransactionId.indexOf("(") + 16,
				authTransactionId.indexOf(")"));

		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		logger.info("[Service][executeRefund] Order Status : " + orderStatus);
		if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_REFUNDED)) {
			Assert.assertTrue(true, "Correct status");
			addLogToTestNGReport(Constants.REFUND, authTransactionId);
		} else {
			Assert.assertTrue(false, "Incorrect status");
		}
		logoutBackOffice(driver);
		logger.info("[Service][executeRefund] Ends");
	}
	public void shippingMethod(WebDriver driver,String shippingMethod) {
		logger.info("[Service][ShippingMethods] Starts");
		
	//	Boolean shippingmethod = findElementsByClassName(driver,Constants.FRONT_OFFICE_SHIPPING_METHOD).size()>0;
		Boolean shippingmethod = driver.findElements(By.className("wc-block-components-radio-control")).size()>0;
		
		if(shippingmethod) {
		//	List<WebElement> shippingmethod2=findElementsByClassName(driver,Constants.FRONT_OFFICE_SHIPPING_METHOD);
			List<WebElement> shippingmethod2=driver.findElements(By.className("wc-block-components-radio-control"));

			for(WebElement shippingmethod3 : shippingmethod2) {
				String labelText = shippingmethod3.getText();
				logger.info(labelText);
				String cardSuffix = shippingMethod;
				logger.info(cardSuffix);
				if(labelText.contains(cardSuffix)) {
					
					shippingmethod3.click();
					break;
				}
			}
		
		}
		 logger.info("[Service][ShippingMethods] Ends");
		
	}

	public void differentCurrency(WebDriver driver,String AdminName, String AdminPassword,String Currency,String DecimalPoint){

		logger.info("[Service][DifferentCurrenciesandcountries] Starts");
		driver = setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		if (driver.getPageSource().contains(Constants.BROWSER_ADVANCED_TEXT)) {
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_HIDEDETAILS_BUTTON_ID);
			clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_FOLINK_ID);
		}
		adminLogin(driver,AdminName, AdminPassword);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_CURRENCY_OPTIONS);
		setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_CURRENCY, Currency);
		findElementByXPath(driver,prop, Constants.BACK_OFFICE_CURRENCY).sendKeys(Keys.ENTER);
		findElementByXPath(driver,prop, Constants.BACK_OFFICE_DECIMAL_PLACE).clear();
		setElementValueByXPath(driver,prop,Constants.BACK_OFFICE_DECIMAL_PLACE,DecimalPoint);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SAVE);
		logoutBackOffice(driver);

		logger.info("[Service][DifferentCurrenciesandcountries] Ends");
	}

	public void executePartialRefund(WebDriver driver,String RefundQuantity, String RefundAmount) {

		logger.info("[Service][executePartialRefund] Starts");
		int i = 0;
		String[] RefundQuantityList = RefundQuantity.split(Constants.COMMA);
		String[] RefundAmountList = RefundAmount.split(Constants.COMMA);

		WebElement table = findElementByXPath(driver, prop, Constants.BACK_OFFICE_PRODUCT_TABLE_XPATH);
		List<WebElement> allRows = table.findElements(By.tagName(Constants.TR));
		List<WebElement> cells = null;
		for (WebElement row : allRows) {
			if (i < RefundQuantityList.length) {
				int j = i + 1;
				clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND);

				WebElement refundinputquantity = driver
						.findElement(By.xpath(prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_PREFIX) + j
								+ prop.getProperty(Constants.BACK_OFFICE_REFUND_QUANTITY_SUFFIX)));
				refundinputquantity.sendKeys(RefundQuantityList[i]);
				clickElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_REFUND_BOFA);
				Alert alert = driver.switchTo().alert();
				alert.accept();
				sleep(SLEEP_TIMER_45);
				i++;
			}
		}
		WebElement transactionId = findElementByXPath(driver, prop,
				Constants.BACK_OFFICE_ORDERS_REFUND_TRANSACTION_ID);
		String authTransactionId = transactionId.getText();

		authTransactionId = authTransactionId.substring(authTransactionId.indexOf("(") + 16,
				authTransactionId.indexOf(")"));
		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		logger.info("[Service][executePartialRefund] Order Status : " + orderStatus);
		if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_REFUNDED)) {
			Assert.assertTrue(true, "Correct status");
			addLogToTestNGReport(Constants.PARTIALREFUND, authTransactionId);
		}
		else {
			Assert.assertTrue(false, "Incorrect status");
		}
		logoutBackOffice(driver);
		logger.info("[Service][executePartialRefund] Ends");
	}


	public String BackOfficeCheck(WebDriver driver,String AdminName, String AdminPassword, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword, String OrderNumber) {

		logger.info("[Service][BackOfficeCheck] Ends");

		driver.navigate().to(prop.getProperty(Constants.BACK_OFFICE_LOGIN_URL));
		executor = (JavascriptExecutor) driver;
		adminLogin(driver,AdminName, AdminPassword);

		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_ORDERS);
		WebElement path = driver.findElement(By.xpath(prop.getProperty(Constants.BACK_OFFICE_ORDERS_ID_PREFIX)
				+ OrderNumber + prop.getProperty(Constants.BACK_OFFICE_ORDERS_ID_SUFFIX)));
		path.click();
		WebElement transactionId = findElementByXPath(driver, prop,
				Constants.BACK_OFFICE_ORDERS_TRANSACTION_ID);
		String authTransactionId = transactionId.getText();

		authTransactionId = authTransactionId.substring(authTransactionId.indexOf("(") + 1,
				authTransactionId.indexOf(")"));
		try {
			addScreenshotToTestNGReport(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("[Service][BackOfficeCheck] Auth Transaction Id : " + authTransactionId);

		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		if (Service.equalsIgnoreCase(Constants.SALE)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_PROCESSING)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.SALE, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} else if (Service.equalsIgnoreCase(Constants.SALE_DECISION_MANAGER)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_PROCESSING)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.SALE, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} else if (Service.equalsIgnoreCase(Constants.AUTH)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_ON_HOLD)) {

				addLogToTestNGReport(Constants.AUTH, authTransactionId);
				Assert.assertTrue(true, "Correct status");
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} else if (Service.equalsIgnoreCase(Constants.DECISION_MANAGER)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_ON_HOLD)) {

				addLogToTestNGReport(Constants.AUTH, authTransactionId);
				Assert.assertTrue(true, "Correct status");
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		}

		addLogToTestNGReportOrderID(Constants.ORDER_NUMBER, OrderNumber);

		if (Service.equalsIgnoreCase(Constants.AUTH)) {
			logoutBackOffice(driver);
		}

		logger.info("[Service][BackOfficeOrderCheck] Ends");
		return authTransactionId;
	}
	public String BackOfficeCheck(WebDriver driver,String AdminName, String AdminPassword, String orderNumber, String GuestUser,
			String Service) {

		logger.info("[Service][BackOfficeOrderCheck] Starts");
		//driver.navigate().to(prop.getProperty(Constants.BACK_OFFICE_LOGIN_URL));
		driver.navigate().to(prop.getProperty(Constants.BACK_OFFICE_LOGIN_URL));
		adminLogin(driver,AdminName, AdminPassword);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_ORDERS);
		WebElement path = driver.findElement(By.xpath(prop.getProperty(Constants.BACK_OFFICE_ORDERS_ID_PREFIX)
				+ orderNumber + prop.getProperty(Constants.BACK_OFFICE_ORDERS_ID_SUFFIX)));
		path.click();
		WebElement transactionId = findElementByXPath(driver, prop,
				Constants.BACK_OFFICE_ORDERS_TRANSACTION_ID);
		String authTransactionId = transactionId.getText();

		authTransactionId = authTransactionId.substring(authTransactionId.indexOf("(")  + 16,
				authTransactionId.indexOf(")"));
		logger.info("[Service][BackOfficeCheck] Auth Transaction Id : " + authTransactionId);

		WebElement status = findElementByXPath(driver, prop, Constants.BACK_OFFICE_ORDERS_STATUS);
		String orderStatus = status.getText();
		if (Service.equalsIgnoreCase(Constants.SALE)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_PROCESSING)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.SALE, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		}
		else if (Service.equalsIgnoreCase(Constants.PAYERAUTH)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_ON_HOLD)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.PAYERAUTH, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} 
		else if (Service.equalsIgnoreCase(Constants.SALEDECISIONMANAGER)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_PROCESSING)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.SALE, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} 		
		else if (Service.equalsIgnoreCase(Constants.REFUND)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_REFUNDED)) {
				Assert.assertTrue(true, "Correct status");
				addLogToTestNGReport(Constants.REFUND, authTransactionId);
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} 
		else if (Service.equalsIgnoreCase(Constants.AUTH)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_ON_HOLD)) {

				addLogToTestNGReport(Constants.AUTH, authTransactionId);
				Assert.assertTrue(true, "Correct status");
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		} else if (Service.equalsIgnoreCase(Constants.AUTH_DECISION_MANAGER)) {
			if (orderStatus.equals(Constants.BACK_OFFICE_STATUS_ON_HOLD)) {

				addLogToTestNGReport(Constants.AUTH, authTransactionId);
				Assert.assertTrue(true, "Correct status");
			} else {
				Assert.assertTrue(false, "Incorrect status");
			}
		}

		addLogToTestNGReportOrderID(Constants.ORDER_NUMBER, orderNumber);
		sleep(SLEEP_TIMER_10);
		try {
			addScreenshotToTestNGReport(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Service.equalsIgnoreCase(Constants.AUTH)) {
			logoutBackOffice(driver);
		}

		logger.info("[Service][BackOfficeOrderCheck] Ends");
		return authTransactionId;
	}
	
	
	public void logoutBackOffice(WebDriver driver) {

		logger.info("[Service][logoutBackOffice] Starts");
		WebElement mainMenu = findElementByXPath(driver, prop, Constants.BACK_OFFICE_LOGOUT_HOVER);
		Actions actions = new Actions(driver);
		actions.moveToElement(mainMenu);
		WebElement subMenu = findElementByXPath(driver, prop, Constants.BACK_OFFICE_LOGOUT);
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		logger.info("[Service][logoutBackOffice] Ends");
	}
	
	//Billing address
	public void frontOfficeBillingAddress(WebDriver driver,String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail) {
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_FIRST_NAME).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_FIRST_NAME, BillingFirstName);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_LAST_NAME).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_LAST_NAME, BillingLastName);
		clickElementByXPath(driver,prop,Constants.FRONT_OFFICE_BILLING_COUNTRY);
		WebElement Billingcountry=findElementByclassName(driver,prop,Constants.FRONT_OFFICE_BILLING_COUNTRY_TEXT);
		Billingcountry.sendKeys(BillingCountry);
		Billingcountry.sendKeys(Keys.ENTER);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_STREET).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_STREET, BillingStreet);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_STREET2).clear();
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_CITY).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_CITY, BillingCity);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_ZIP).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_ZIP, BillingZip);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_PHONE_NO).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_PHONE_NO, BillingPhoneNo);
		findElementById(driver, Constants.FRONT_OFFICE_BILLING_EMAIL).clear();
		setElementValueById(driver, prop, Constants.FRONT_OFFICE_BILLING_EMAIL, BillingEmail);

	}
	//Shipping Address
	public void frontOfficeShippingAddress(WebDriver driver,String ShippingFirstName,String ShippingLastName,String ShippingCountry,
			String ShippingStreet,String ShippingCity,String ShippingZip) {
		WebElement element = findElementByXPath(driver,prop,Constants.FRONT_OFFICE_DIFFERENT_SHIPPING_ADDRESS_CHECKBOX);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		findElementById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_FIRST_NAME).clear();
		setElementValueById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_FIRST_NAME,ShippingFirstName);
		findElementById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_LAST_NAME).clear();
		setElementValueById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_LAST_NAME,ShippingLastName);
		clickElementById(driver,Constants.FRONT_OFFICE_SHIPPING_COUNTRY);
		WebElement shippingcountry=findElementByXPath(driver,prop,Constants.FRONT_OFFICE_SHIPPING_COUNTRY_TEXT);
		shippingcountry.sendKeys(ShippingCountry);
		shippingcountry.sendKeys(Keys.ENTER);
		findElementById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_ADDRESS1).clear();
		setElementValueById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_ADDRESS1,ShippingStreet);
		findElementById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_CITY).clear();
		setElementValueById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_CITY,ShippingCity);
		findElementById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_POSTCODE).clear();
		setElementValueById(driver,prop,Constants.FRONT_OFFICE_SHIPPING_POSTCODE,ShippingZip);
	}
}
