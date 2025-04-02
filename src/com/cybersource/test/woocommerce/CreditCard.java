package com.cybersource.test.woocommerce;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cybersource.test.CommonTest;
import com.cybersource.test.util.CommonUtil;
import com.cybersource.test.util.Constants;
import com.cybersource.test.util.FileUtil;
import com.test.RetryFailedTestCases;


@Listeners(com.cybersource.test.util.Listeners.class)
public class CreditCard extends CommonTest {
	String screenShotModulePath = null;
	final static Logger logger = Logger.getLogger(CreditCard.class);
	public static WebDriver driver = null;
	static JavascriptExecutor executor = null;
	static int ChargeConfigure = 0;
	static int PayerAuthConfigure = 0;
	static int testCaseCount = 0;
	String userIndex = "1";
	CommonUtil commonUtil = new CommonUtil();
	@BeforeTest
	public void init(ITestContext context) throws IOException {
		super.loadCommonConfig(context);
		driver = null;
		if (null != context.getCurrentXmlTest().getParameter(Constants.USER_INDEX)) {
			userIndex = context.getCurrentXmlTest().getParameter(Constants.USER_INDEX); 
		}
		commonUtil.executionSleepTimer(userIndex);
		screenShotModulePath = CommonUtil.getScreenshotsModulePath(Constants.CREDIT_CARD);
	}


	@DataProvider
	public Object[][] getData(ITestContext context) {
		Object[][] data = null;
		String prefix = context.getCurrentXmlTest().getParameter(Constants.PREFIX);
		String currentScenario = context.getCurrentXmlTest().getParameter(Constants.TEST_SCENARIO);
		logger.info(prefix + currentScenario + Constants._SHEET_NAME);
		logger.info(XLSX_FILE_PATH);
		data = FileUtil.getSheetData(XLSX_FILE_PATH,
				prop.getProperty(prefix + currentScenario + Constants._SHEET_NAME));
		logger.info(data);

		logger.info("[CreditCard][getData] currentScenario:" + currentScenario);
		return data;
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void CreditCardConfiguration(String AdminName, String AdminPassword, String TransactionType,
			String Tokenization,String Environment, String MerchantId, String MerchantKeyId,
			String MerchantSecretKey) {

		logger.info("[CreditCard][CreditCardConfiguration] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_PAYMENT_MANAGE);
		CommonUtil.clickElementById(driver, Constants.BACK_OFFICE_CC_ENABLE);
		CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_CC_TRANSACTION_TYPE, TransactionType);
		if (Tokenization.equalsIgnoreCase(Constants.YES)) {
			CommonUtil.clickElementById(driver, Constants.BACK_OFFICE_CC_TOKENIZATION);
		}
        CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_CC_ENVIRONMENT, Environment);
		CommonUtil.findElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_ID).clear();
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_ID, MerchantId);
		CommonUtil.findElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_KEY_ID).clear();
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_KEY_ID, MerchantKeyId);
		CommonUtil.findElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_SECRET_KEY).clear();
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_CC_MERCHANT_SECRET_KEY,
				MerchantSecretKey);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_SAVE_BUTTON);
		commonUtil.logoutBackOffice(driver);
		logger.info("[CreditCard][CreditCardConfiguration] ends");
	}

	public void fraudManagement(String AdminName, String AdminPassword, String Service) {
		logger.info("[CreditCard][FraudManagement] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_PAYMENT_MANAGE);
		if (Service.equalsIgnoreCase(Constants.SALE_DECISION_MANAGER)) {
			CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_CC_TRANSACTION_TYPE, Constants.CHARGE);
		} else {
			CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_CC_TRANSACTION_TYPE, Constants.AUTHORIZATION);
		}
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_SAVE_BUTTON);
		commonUtil.logoutBackOffice(driver);
		logger.info("[CreditCard][FraudManagement] ends");

	}

	public void saleConfiguration(String AdminName, String AdminPassword) {

		logger.info("[CreditCard][saleConfiguration] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_PAYMENT_MANAGE);
		CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_CC_TRANSACTION_TYPE, Constants.CHARGE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_SAVE_BUTTON);
		logger.info("[CreditCard][saleConfiguration] Ends");
	}
	public void payerAuthConfiguration(String AdminName, String AdminPassword) {

		logger.info("[CreditCard][PayerAuthConfiguration] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_PAYMENT_MANAGE);
		CommonUtil.clickElementById(driver,Constants.BACK_OFFICE_CC_PAYER_AUTH_ENABLE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_CC_SAVE_BUTTON);
		logger.info("[CreditCard][PayerAuthConfiguration] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapture(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
			String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {

		logger.info("[CreditCard][executeAuthCapture] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear, Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);
		logger.info("[CreditCard][executeAuthCapture] ends");
	}


	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeDecisionManager(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String Dfp, String OrganizationId,
			String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[CreditCard][executeDecisionManager] Starts");
		fraudManagement(AdminName, AdminPassword,Service);
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.logoutBackOffice(driver);
		logger.info("[CreditCard][executeDecisionManager] ends");

	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthAuthReversal(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String RefundQuantity,String RefundAmount,String Service, String RequireEBC, String OrganizationId,
			String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[CreditCard][executeAuthAuthReversal] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip, CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeAuthReversal(driver,Service,RefundQuantity,RefundAmount);
		logger.info("[CreditCard][executeAuthAuthReversal] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,
			String ShippingCity,String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String RefundQuantity, String RefundAmount, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[CreditCard][executeAuthCapRefund] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);
		commonUtil.executeRefund(driver,RefundQuantity, RefundAmount);

		logger.info("[CreditCard][executeAuthCapRefund] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapParRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String RefundQuantity, String RefundAmount, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[CreditCard][executeAuthCapParRefund] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear, Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);

		commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount);

		logger.info("[CreditCard][executeAuthCapParRefund] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSale(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,String EBCPassword) throws Exception {

		logger.info("[creditCard][executeSale] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			ChargeConfigure++;
			commonUtil.logoutBackOffice(driver);
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		commonUtil.logoutBackOffice(driver);
		logger.info("[creditCard][executeSale] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSaleRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String RefundQuantity, String RefundAmount,String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[creditCard][executeSaleRefund] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			commonUtil.logoutBackOffice(driver);
			ChargeConfigure++;
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);

		commonUtil.executeRefund(driver,RefundQuantity, RefundAmount); 
		logger.info("[creditCard][executeSaleRefund] ends");
	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSaleParRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode, String ExpirationMonth, String ExpirationYear,
			String RefundQuantity, String RefundAmount,String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[creditCard][executeSaleRefund] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			commonUtil.logoutBackOffice(driver);
			ChargeConfigure++;
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);

		commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount); 
		logger.info("[creditCard][executeSaleRefund] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeMyAccountAddCard(String UserName, String Password, String BillingFirstName, String BillingLastName,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear) {

		logger.info("[creditCard][executeMyAccountAddCard] Starts");

		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.frontOfficeLogin(driver,UserName, Password);
		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_PAYMENT_METHOD);
		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_ADD_NEW_PAYMENT_METHOD);
	//	commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
	//	CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_ADD_CARD);
		commonUtil.unifiedCheckoutmyaccountAddNewCard(driver, BillingFirstName, BillingLastName,
				  CardNumber, CardSecurityCode,  ExpirationMonth, 
					 ExpirationYear);
		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_UC_ADD_CARD);
		WebElement message = CommonUtil.findElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_MESSAGE);
		if (null != message) {
			logger.info("[CreditCard][executeMyAccountAddCard] Card added");
			Assert.assertTrue(true, "Card added");
		} else {
			logger.info("[CreditCard][executeMyAccountAddCard] Card cannot be added");
			Assert.assertTrue(false, "Card cannot be added");
		}
		commonUtil.logoutfrontoffice(driver);
		logger.info("[creditCard][executeMyAccountAddCard] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeMyAccountDeleteCard(String UserName, String Password, String ExistingCardNumber,
			String Expiration) {
 
		logger.info("[creditCard][executeMyAccountDeleteCard] Starts");
		int i = 1;
		int count = 0;
 
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);
		if (driver.getPageSource().contains("Your connection is not private")) {
			CommonUtil.clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_HIDEDETAILS_BUTTON_ID);
			CommonUtil.clickElementById(driver, Constants.FRONT_OFFICE_SECURITYPAGE_FOLINK_ID);
		}
		commonUtil.frontOfficeLogin(driver,UserName, Password);
		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_PAYMENT_METHOD);
		WebElement table = CommonUtil.findElementByXPath(driver, prop,
				Constants.FRONT_OFFICE_MYACCOUNT_CARDS_TABLE_XPATH);
		System.out.println("hello");
		List<WebElement> allRows = table.findElements(By.tagName(Constants.TR));
		List<WebElement> cells = null;
		for (WebElement row : allRows) {
			cells = row.findElements(By.tagName(Constants.TD));
			if (Constants.ONE < cells.size()) {
				String CardNumber = cells.get(0).getText();
				String ExpirationDate = cells.get(3).getText();
 
				if ((CardNumber.substring(15,19).equalsIgnoreCase(ExistingCardNumber.substring(12,16)))
						&& (ExpirationDate.equalsIgnoreCase(Expiration))) {
					WebElement delete = cells.get(5)
							.findElement(By.xpath(prop.getProperty(Constants.FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_PREFIX)
									+ i + prop.getProperty(Constants.FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_SUFFIX)));
 
					delete.click();
					Alert alert = driver.switchTo().alert();
					alert.accept();
					CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
					count++;
					break;
				}
 
			}
			i++;
		}
 
		if (Constants.ONE == count) {
			WebElement message = CommonUtil.findElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_MESSAGE);
			if (message != null) {
				logger.info("[CreditCard][executeMyAccountDeleteCard] Card is deleted");
				Assert.assertTrue(true, "Card is deleted");
			} else {
				logger.info("[CreditCard][executeMyAccountDeleteCard] Card cannot be deleted - Default card");
				Assert.assertTrue(false, "Card cannot be deleted");
			}
		} else {
			logger.info("[CreditCard][executeMyAccountDeleteCard] Card cannot be deleted - Match not found");
			Assert.assertTrue(false, "Card cannot be deleted");
		}
		commonUtil.logoutfrontoffice(driver);
		logger.info("[creditCard][executeMyAccountDeleteCard] ends");
	}
//	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
//	public void executeMyAccountDeleteCard(String UserName, String Password, String ExistingCardNumber,
//			String Expiration) {
//
//		logger.info("[creditCard][executeMyAccountDeleteCard] Starts");
//		int i = 1;
//		int count = 0;
//
//		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
//		executor = (JavascriptExecutor) driver;
//		commonUtil.advancedSecurityPageHandle(driver);
//		commonUtil.frontOfficeLogin(driver,UserName, Password);
//		CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_PAYMENT_METHOD);
//		WebElement table = CommonUtil.findElementByXPath(driver, prop,
//				Constants.FRONT_OFFICE_MYACCOUNT_CARDS_TABLE_XPATH);
//		List<WebElement> allRows = table.findElements(By.tagName(Constants.TR));
//		List<WebElement> cells = null;
//		for (WebElement row : allRows) {
//			cells = row.findElements(By.tagName(Constants.TD));
//			if (Constants.ONE < cells.size()) {
//				String CardNumber = cells.get(0).getText();
//				String ExpirationDate = cells.get(3).getText();
//
//				if ((CardNumber.substring(15,19).equalsIgnoreCase(ExistingCardNumber.substring(12,16)))
//						&& (ExpirationDate.equalsIgnoreCase(Expiration))) {
//					WebElement delete = cells.get(5)
//							.findElement(By.xpath(prop.getProperty(Constants.FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_PREFIX)
//									+ i + prop.getProperty(Constants.FRONT_OFFICE_MYACCOUNT_DELETE_XPATH_SUFFIX)));
//
//					delete.click();
//					Alert alert = driver.switchTo().alert();
//					alert.accept();
//					count++;
//					break;
//				}
//
//			}
//			i++;
//		}
//
//		if (Constants.ONE == count) {
//			WebElement message = CommonUtil.findElementByXPath(driver, prop, Constants.FRONT_OFFICE_MYACCOUNT_MESSAGE);
//			if (message != null) {
//				logger.info("[CreditCard][executeMyAccountDeleteCard] Card is deleted");
//				Assert.assertTrue(true, "Card is deleted");
//			} else {
//				logger.info("[CreditCard][executeMyAccountDeleteCard] Card cannot be deleted - Default card");
//				Assert.assertTrue(false, "Card cannot be deleted");
//			}
//		} else {
//			logger.info("[CreditCard][executeMyAccountDeleteCard] Card cannot be deleted - Match not found");
//			Assert.assertTrue(false, "Card cannot be deleted");
//		}
//		commonUtil.logoutfrontoffice(driver);
//		logger.info("[creditCard][executeMyAccountDeleteCard] ends");
//	}
//
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuth(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[creditCard][executeAuth] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		commonUtil.shippingMethodLoadHandling(driver);
		if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
			commonUtil.unifiedCheckoutAddNewCard(driver, BillingFirstName, BillingLastName, BillingEmail,
					  CardNumber, CardSecurityCode,  ExpirationMonth, 
						 ExpirationYear);
			
		}
		else {
	//	WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
	//	JavascriptExecutor executor = (JavascriptExecutor) driver;
	//	executor.executeScript("arguments[0].click();", element);
	// 	commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		commonUtil.placeOrderButton(driver,GuestUser);
		}
	// 	CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[creditCard][executeAuth] ends");

	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeShippingMethod(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String shippingMethod,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[CreditCard][executeShippingMethod] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_10);
		commonUtil.shippingMethod(driver,shippingMethod);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_5);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		commonUtil.shippingMethodLoadHandling(driver);
		if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
			commonUtil.unifiedCheckoutAddNewCard(driver, BillingFirstName, BillingLastName, BillingEmail,
					  CardNumber, CardSecurityCode,  ExpirationMonth,
						 ExpirationYear);
		}
		else {
//		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
		commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		commonUtil.placeOrderButton(driver,GuestUser);
		}
	//	CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[CreditCard][executeShippingMethod] ends");	    

	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeTokenization(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String SaveNewCard, String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[CreditCard][executeTokenization] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		commonUtil.shippingMethodLoadHandling(driver);
		if(Constants.NO.equalsIgnoreCase(SaveNewCard)) {
			commonUtil.getSavedCard(driver,CardNumber, CardSecurityCode,  ExpirationMonth, ExpirationYear);
			commonUtil.placeOrderButton(driver,GuestUser);
		}
		else {
			if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
			//	CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.FRONT_OFFICE_CHECKBOX_SAVE_CARD);
				commonUtil.useNewCard(driver);
				commonUtil.unifiedCheckoutTokenziationAddNewCard(driver,BillingFirstName,BillingLastName,
	            BillingEmail,CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear);
			}

	//	WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
	//	JavascriptExecutor executor = (JavascriptExecutor) driver;
	//	executor.executeScript("arguments[0].click();", element);
	//	if (Constants.YES.equalsIgnoreCase(SaveNewCard)) {
	//	CommonUtil.clickElementByXPath(driver, prop, Constants.FRONT_OFFICE_CHECKBOX_SAVE_CARD);
	//		commonUtil.useNewCard(driver);
	//	commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		//	CommonUtil.clickElementByCssSelector(driver, prop, Constants.FRONT_OFFICE_CHECKBOX_SAVE_CARD);
	//		commonUtil.placeOrderButton(driver,GuestUser);
		}
	//	else {
	//		commonUtil.getSavedCard(driver,CardNumber, CardSecurityCode,  ExpirationMonth, ExpirationYear);
	//	}
	//CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		commonUtil.orderConfirmationPageForToken(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
					CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
			logger.info("[creditCard][executeAuth] ends");
	}
	
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeDiffCurrencies(String AdminName, String AdminPassword,String GuestUser,String Currency,String DecimalPoint, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip, String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String RefundQuantity,String RefundAmount,String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[creditCard][DiifCurrencyandCountries] Starts");
		commonUtil.differentCurrency(driver,AdminName,AdminPassword,Currency,DecimalPoint);
		executeAuthCapRefund(AdminName,AdminPassword, GuestUser, ProductName,ProductQuantity,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,
				ShippingCountry,ShippingStreet,ShippingCity,ShippingZip,CardNumber, 
				CardSecurityCode, ExpirationMonth,ExpirationYear,RefundQuantity,RefundAmount,Service, 
				RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		logger.info("[creditCard][DiifCurrencyandCountries] ends");

	}


	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeCoupons(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
			String couponCode,String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {

		logger.info("[creditCard][executeCoupons] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.coupon(driver, couponCode);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		commonUtil.shippingMethodLoadHandling(driver);
		if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
			commonUtil.unifiedCheckoutAddNewCard(driver, BillingFirstName, BillingLastName, BillingEmail,
					  CardNumber, CardSecurityCode,  ExpirationMonth, 
						 ExpirationYear);
			
		}
		else {
	//	WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
	//	JavascriptExecutor executor = (JavascriptExecutor) driver;
	//	executor.executeScript("arguments[0].click();", element);
	// 	commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		commonUtil.placeOrderButton(driver,GuestUser);
		}
	// 	CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[creditCard][executeCoupons] ends");

	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeTaxes(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry, String CountryCode, String Rate,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[creditCard][executeTaxes] Starts");
		commonUtil.TaxesConfiguration(driver,AdminName,  AdminPassword, CountryCode, BillingZip,  BillingCity,  Rate);
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear,Service, RequireEBC,
				OrganizationId, EBCUserName, EBCPassword);
		logger.info("[creditCard][executeTaxes] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeNullInvalidScenarioCase(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword
			) throws Exception {
		logger.info("[creditCard][executenullInvalidScenarioCase] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		commonUtil.shippingMethodLoadHandling(driver);
		if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
			commonUtil.unifiedCheckoutAddNewCard(driver,ShippingFirstName,ShippingLastName,
            BillingEmail,CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear);
		}
		else {
//		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
//		commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		commonUtil.placeOrderButton(driver,GuestUser);
		
	//	CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
	//	commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
	//			CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
	//	logger.info("[CreditCard][executeShippingMethod] ends");	   
 
	//}
		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
	    CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		}
		if (driver.getPageSource().contains(Constants.FRONT_OFFICE_NULL_INVALID_ERROR_MESSAGE_TEXT)) {
			String URL = CommonUtil.getCurrentUrl(driver);
			Assert.assertEquals(URL, URL );
			System.out.println("validated");
			Thread.sleep(10000);
			Reporter.log("Test Case status = Pass");
			
		}
			
//		if (driver.getPageSource().contains(Constants.FRONT_OFFICE_ORDER_CONFIRMATION_TEXT)) {
//			commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
//					CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
//			Reporter.log("Test Case status = Fail");
//		}
		else {
			Reporter.log("Test Case status = Fail");
		}
		logger.info("[creditCard][executenullInvalidScenarioCase] ends");
	}
//	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
//	public void executeNullInvalidScenarioCase(String AdminName, String AdminPassword, String GuestUser, String ProductName,
//			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
//			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
//			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
//			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,
//			String CardNumber, String CardSecurityCode,String ExpirationMonth, String ExpirationYear,
//			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
//			String EBCPassword
//			) throws Exception {
//		logger.info("[creditCard][executenullInvalidScenarioCase] Starts");
//		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
//		executor = (JavascriptExecutor) driver;
//		commonUtil.advancedSecurityPageHandle(driver);
//		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
//			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
//		}
//		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
//		commonUtil.proceedToCheckout(driver,GuestUser);
//		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
//				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
//				BillingEmail);
//		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
//			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
//					ShippingStreet, ShippingCity,ShippingZip);
//		}
//		commonUtil.shippingMethodLoadHandling(driver);
//		if(prop.getProperty(Constants.MODULE_TYPE).equalsIgnoreCase((Constants.UNIFIED_CHECKOUT))) {
//			commonUtil.unifiedCheckoutNullcase(driver, ShippingFirstName, ShippingLastName, BillingEmail,
//					  CardNumber, CardSecurityCode,  ExpirationMonth,
//						 ExpirationYear);
//		}
//		else {
////		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
////		JavascriptExecutor executor = (JavascriptExecutor) driver;
////		executor.executeScript("arguments[0].click();", element);
////		commonUtil.useNewCard(driver);
//		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
//		commonUtil.placeOrderButton(driver,GuestUser);
//		}
//	//	CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
//		if (driver.getPageSource().contains(Constants.FRONT_OFFICE_ORDER_CONFIRMATION_TEXT)) {
//			commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
//					CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
//			Reporter.log("Test Case status = Fail");
//		}
//		else {
//			Reporter.log("Test Case status = Pass");
//		}
//		logger.info("[creditCard][executenullInvalidScenarioCase] ends");
//	}


	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executePayerAuth(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,
			String ShippingStreet,String ShippingCity,String ShippingZip,String PayerAuthOTP, String CardNumber, String CardSecurityCode,
			String ExpirationMonth, String ExpirationYear,String Service, String RequireEBC, String OrganizationId, String EBCUserName,String EBCPassword) throws Exception {

		logger.info("[creditCard][executePayerAuth] Starts");
		if (Constants.ZERO == PayerAuthConfigure) {
			payerAuthConfiguration(AdminName, AdminPassword);
			PayerAuthConfigure++;
			commonUtil.logoutBackOffice(driver);
		}
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.proceedToCheckout(driver,GuestUser);
		commonUtil.frontOfficeBillingAddress(driver,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				BillingEmail);
		if (Constants.YES.equalsIgnoreCase(NewShippingAddress)) {
			commonUtil.frontOfficeShippingAddress(driver,ShippingFirstName,ShippingLastName,ShippingCountry,
					ShippingStreet, ShippingCity,ShippingZip);
		}
		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		commonUtil.useNewCard(driver);
		commonUtil.addNewCard(driver,CardNumber, CardSecurityCode, ExpirationMonth, ExpirationYear);
		CommonUtil.clickElementByXPath(driver,prop, Constants.FRONT_OFFICE_CHECKOUT_PLACE_ORDER_BUTTON);
		if(PayerAuthOTP.equalsIgnoreCase(Constants.YES)) {
			commonUtil.PayerAuthDetails(driver);
		}
		commonUtil.orderConfirmationPageForCreditCard(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				CardNumber,CardSecurityCode,ExpirationMonth,ExpirationYear,Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[creditCard][executePayerAuth] ends");

	}
}