package com.cybersource.test.woocommerce;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
public class GooglePay extends CommonTest {
	String screenShotModulePath = null;
	final static Logger logger = Logger.getLogger(GooglePay.class);
	public static WebDriver driver = null;
	static JavascriptExecutor executor = null;
	String userIndex = "1";
	CommonUtil commonUtil = new CommonUtil();
	static int ChargeConfigure = 0;
	static int testCaseCount = 0;

	@BeforeTest
	public void init(ITestContext context) throws IOException {
		super.loadCommonConfig(context);
		driver = null;
		if (null != context.getCurrentXmlTest().getParameter(Constants.USER_INDEX)) {
			userIndex = context.getCurrentXmlTest().getParameter(Constants.USER_INDEX); 
		}
		commonUtil.executionSleepTimer(userIndex);
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		screenShotModulePath = CommonUtil.getScreenshotsModulePath(Constants.GOOGLE_PAY);
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

		logger.info("[GooglePay][getData] currentScenario:" + currentScenario);
		return data;
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void GooglePayConfiguration(String AdminName, String AdminPassword, String TransactionType,
			String Environment, String MerchantId,String MerchantKeyId,String MerchantSecretKey) {

		logger.info("[GooglePay][GooglePayConfiguration] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAYPAYMENT_MANAGE);
		CommonUtil.clickElementById(driver, Constants.BACK_OFFICE_GPAY_ENABLE);
		CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_ORDERS_GPAY_TRANSACTION_TYPE, TransactionType);
		CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_GPAY_ENVIRONMENT, Environment);
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_MERCHANT_ID, MerchantId);
		CommonUtil.findElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_MERCHANT_KEY_ID).clear();
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_MERCHANT_KEY_ID, MerchantKeyId);
		CommonUtil.findElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_MERCHANT_SECRET_KEY).clear();
		CommonUtil.setElementValueByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_MERCHANT_SECRET_KEY,
				MerchantSecretKey);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_SAVE_BUTTON);
		commonUtil.logoutBackOffice(driver);
		logger.info("[GooglePay][GooglePayConfiguration] ends");
	}

	public void fraudManagement(String AdminName, String AdminPassword,String Service) {
		logger.info("[GooglePay][FraudManagement] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_PAYMENT_MANAGE);
		if (Service.equalsIgnoreCase(Constants.SALE_DECISION_MANAGER)) {
			CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_ORDERS_GPAY_TRANSACTION_TYPE, Constants.CHARGE);
		} else {
			CommonUtil.setElementValueById(driver, prop, Constants. BACK_OFFICE_ORDERS_GPAY_TRANSACTION_TYPE, Constants.AUTHORIZATION);
		}
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_SAVE_BUTTON);
		commonUtil.logoutBackOffice(driver);
		logger.info("[GooglePay][FraudManagement] ends");

	}

	public void saleConfiguration(String AdminName, String AdminPassword) {

		logger.info("[GooglePay][saleConfiguration] Starts");
		driver = commonUtil.setupDriver(prop, Constants.BACK_OFFICE_LOGIN_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		commonUtil.adminLogin(driver,AdminName, AdminPassword);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WOOCOMMERCE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_WC_SETTINGS);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_SETTINGS_PAYMENT);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAYPAYMENT_MANAGE);
		CommonUtil.setElementValueById(driver, prop, Constants.BACK_OFFICE_ORDERS_GPAY_TRANSACTION_TYPE, Constants.CHARGE);
		CommonUtil.clickElementByXPath(driver, prop, Constants.BACK_OFFICE_GPAY_SAVE_BUTTON);
		logger.info("[GooglePay][saleConfiguration] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapture(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String Loggedin,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeAuthCapture] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);
		logger.info("[GooglePay][executeAuthCapture] ends");
	}


	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeDecisionManager(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String Service, String RequireEBC, String Dfp, String OrganizationId,
			String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeDecisionManager] Starts");
		fraudManagement(AdminName, AdminPassword, Service);
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.logoutBackOffice(driver);
		logger.info("[GooglePay][executeDecisionManager] ends");

	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthAuthReversal(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String RefundQuantity,String RefundAmount,String Service, String RequireEBC, String OrganizationId,
			String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeAuthAuthReversal] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeAuthReversal(driver,Service,RefundQuantity,RefundAmount);
		logger.info("[GooglePay][executeAuthAuthReversal] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,
			String ShippingCity,String ShippingZip,String Loggedin,String RefundQuantity, String RefundAmount, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeAuthCapRefund] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);
		commonUtil.executeRefund(driver,RefundQuantity, RefundAmount);

		logger.info("[GooglePay][executeAuthCapRefund] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuthCapParRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String RefundQuantity, String RefundAmount, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeAuthCapParRefund] Starts");
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.executeCapture(driver,Service);

		commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount);

		logger.info("[GooglePay][executeAuthCapParRefund] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSale(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String Service, String RequireEBC, String OrganizationId, String EBCUserName,String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeSale] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			ChargeConfigure++;
			commonUtil.logoutBackOffice(driver);
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		commonUtil.logoutBackOffice(driver);
		logger.info("[GooglePay][executeSale] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSaleRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String RefundQuantity, String RefundAmount,String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeSaleRefund] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			commonUtil.logoutBackOffice(driver);
			ChargeConfigure++;
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);

		commonUtil.executeRefund(driver,RefundQuantity, RefundAmount); 
		logger.info("[GooglePay][executeSaleRefund] ends");
	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeSaleParRefund(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String RefundQuantity, String RefundAmount,String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeSaleRefund] Starts");
		if (Constants.ZERO == ChargeConfigure) {
			saleConfiguration(AdminName, AdminPassword);
			commonUtil.logoutBackOffice(driver);
			ChargeConfigure++;
		}
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);

		commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount); 
		logger.info("[GooglePay][executeSaleRefund] ends");
	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeAuth(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String Loggedin,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[GooglePay][executeAuth] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_GOOGLE_SIGNIN_URL,userIndex);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
//		CommonUtil.setElementValueUsingExplicitWait(driver, prop,
//				Constants.FRONT_OFFICE_GOOGLE_LOGIN_USERNAME_TEXT_FIELD,
//				prop.getProperty(Constants.GOOGLE_PAY_LOGIN_USERNAME));
		driver.findElement(By.xpath(prop.getProperty(Constants.FRONT_OFFICE_GOOGLE_LOGIN_USERNAME_TEXT_FIELD))).sendKeys("santhoshwipro29@gmail.com");
		
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
		CommonUtil.clickElementUsingExplicitWait(driver, prop, Constants.GOOGLE_PAY_LOGIN_USERNAME_NEXT_BTN);
		CommonUtil.sleep(CommonUtil.SLEEP_TIMER_2);
	//	driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_GOOGLE_PASSWORD_URL,userIndex);
		//CommonUtil.sleep(CommonUtil.SLEEP_TIMER_1);
	//	CommonUtil.setElementValueUsingExplicitWait(driver, prop,
	//		Constants.FRONT_OFFICE_GOOGLE_LOGIN_PASSWORD_TEXT_FIELD,
		driver.findElement(By.xpath(prop.getProperty(Constants.FRONT_OFFICE_GOOGLE_LOGIN_PASSWORD_TEXT_FIELD))).sendKeys(prop.getProperty(Constants.GOOGLE_PAY_LOGIN_PASSWORD));;
//			CommonUtil.clickelementbyxpath(driver, prop,
//					Constants.FRONT_OFFICE_GOOGLE_LOGIN_PASSWORD_TEXT_FIELD,
//				prop.getProperty(Constants.GOOGLE_PAY_LOGIN_PASSWORD));
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
			commonUtil.unifiedCheckoutPlacingOrderUsingGooglePay(driver,Loggedin);
		}
		else {
		commonUtil.placingOrderUsingGooglePay(driver,Loggedin);
		}
//		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", element);
//		commonUtil.placingOrderUsingGooglePay(driver,Loggedin);
		commonUtil.orderConfirmationPageForGooglePay(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[GooglePay][executeAuth] ends");

	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeShippingMethod(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String shippingMethod,
			String Loggedin,String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[GooglePay][executeShippingMethod] Starts");
		driver = commonUtil.setupDriver(prop, Constants.FRONT_OFFICE_MY_ACCOUNT_URL,userIndex);
		executor = (JavascriptExecutor) driver;
		commonUtil.advancedSecurityPageHandle(driver);
		if (Constants.NO.equalsIgnoreCase(GuestUser)) {
			commonUtil.frontOfficeLogin(driver,AdminName, AdminPassword);
		}
		commonUtil.itemSelection(driver,GuestUser,ProductName, ProductQuantity);
		commonUtil.shippingMethod(driver,shippingMethod);
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
		commonUtil.placingOrderUsingGooglePay(driver,Loggedin);
		commonUtil.orderConfirmationPageForGooglePay(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[GooglePay][executeShippingMethod] ends");	    

	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeDiffCurrencies(String AdminName, String AdminPassword,String GuestUser,String Currency,String DecimalPoint, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String RefundQuantity,String RefundAmount,String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[GooglePay][DiifCurrencyandCountries] Starts");
		commonUtil.differentCurrency(driver,AdminName,AdminPassword,Currency,DecimalPoint);
		executeAuthCapRefund(AdminName,AdminPassword, GuestUser, ProductName,ProductQuantity,BillingFirstName,BillingLastName,BillingCountry,
				BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,
				ShippingCountry,ShippingStreet,ShippingCity,ShippingZip,Loggedin,RefundQuantity,RefundAmount,Service, 
				RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		logger.info("[GooglePay][DiifCurrencyandCountries] ends");

	}


	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeCoupons(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String Loggedin,
			String couponCode,String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {

		logger.info("[GooglePay][executeCoupons] Starts");
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
		WebElement element = CommonUtil.findElementByXPath(driver, prop,Constants.FRONT_OFFICE_PAYMENT_METHOD_CREDIT_CARD );
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		commonUtil.placingOrderUsingGooglePay(driver,Loggedin);
		commonUtil.orderConfirmationPageForGooglePay(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
				Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
		logger.info("[GooglePay][executeCoupons] ends");
	}
	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeTaxes(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry, String CountryCode, String Rate,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,String ShippingCountry,String ShippingStreet,String ShippingCity,
			String ShippingZip,String Loggedin,String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword) throws Exception {
		logger.info("[GooglePay][executeTaxes] Starts");
		commonUtil.TaxesConfiguration(driver,AdminName,  AdminPassword, CountryCode, BillingZip,  BillingCity,  Rate);
		executeAuth(AdminName, AdminPassword, GuestUser, ProductName, ProductQuantity, BillingFirstName,
				BillingLastName, BillingCountry, BillingStreet, BillingCity, BillingState, BillingZip, BillingPhoneNo,
				BillingEmail,NewShippingAddress,ShippingFirstName,ShippingLastName,ShippingCountry,ShippingStreet,ShippingCity,
				ShippingZip,Loggedin,Service, RequireEBC,OrganizationId, EBCUserName, EBCPassword);
		logger.info("[GooglePay][executeTaxes] ends");
	}

	@Test(dataProvider = Constants.GET_DATA,retryAnalyzer = RetryFailedTestCases.class)
	public void executeNullInvalidScenarioCase(String AdminName, String AdminPassword, String GuestUser, String ProductName,
			String ProductQuantity, String BillingFirstName, String BillingLastName, String BillingCountry,
			String BillingStreet, String BillingCity, String BillingState, String BillingZip, String BillingPhoneNo,
			String BillingEmail,String NewShippingAddress,String ShippingFirstName,String ShippingLastName,
			String ShippingCountry,String ShippingStreet,String ShippingCity,String ShippingZip,String Loggedin,
			String Service, String RequireEBC, String OrganizationId, String EBCUserName,String EBCPassword) 
					throws Exception {
		logger.info("[GooglePay][executenullInvalidScenarioCase] Starts");
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
		commonUtil.placingOrderUsingGooglePay(driver,Loggedin);
		if (driver.getPageSource().contains(Constants.FRONT_OFFICE_ORDER_CONFIRMATION_TEXT)) {
			commonUtil.orderConfirmationPageForGooglePay(driver,AdminName,AdminPassword,GuestUser,BillingCountry,BillingStreet,BillingCity,BillingState,BillingZip,BillingPhoneNo,
					Service, RequireEBC, OrganizationId,EBCUserName,EBCPassword);
			Reporter.log("Test Case status = Fail");
		}
		else {
			Reporter.log("Test Case status = Pass");
		}
		logger.info("[GooglePay][executenullInvalidScenarioCase] ends");
	}
}	

