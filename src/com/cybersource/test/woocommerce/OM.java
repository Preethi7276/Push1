package com.cybersource.test.woocommerce;

import java.io.IOException;

import org.apache.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cybersource.test.CommonTest;
import com.cybersource.test.util.CommonUtil;
import com.cybersource.test.util.Constants;
import com.cybersource.test.util.FileUtil;

public class OM extends CommonTest {

	public static WebDriver driver = null;
	final static Logger logger = Logger.getLogger(OM.class);
	String screenShotModulePath = null;
	static JavascriptExecutor executor = null;
	CommonUtil commonUtil = new CommonUtil();
	CreditCard creditCard = new CreditCard();

	@BeforeTest
	public void init(ITestContext context) throws IOException {
		super.loadCommonConfig(context);
		screenShotModulePath = CommonUtil.getScreenshotsModulePath(Constants.CREDIT_CARD);
	}

	@DataProvider
	public Object[][] getData(ITestContext context) {

		logger.info("[OM][getData] Starts");
		Object[][] data = null;
		String prefix = context.getCurrentXmlTest().getParameter(Constants.PREFIX);
		String currentScenario = context.getCurrentXmlTest().getParameter(Constants.TEST_SCENARIO);
		logger.info(prefix + currentScenario + Constants._SHEET_NAME);
		logger.info(XLSX_FILE_PATH);
		data = FileUtil.getSheetData(XLSX_FILE_PATH,
				prop.getProperty(prefix + currentScenario + Constants._SHEET_NAME));

		logger.info("[OM][getData] currentScenario:" + currentScenario);
		logger.info("[OM][getData] Ends");
		return data;
	}

	@Test(dataProvider = Constants.GET_DATA)
	public void executeOMAuthAuthReversal(String AdminName, String AdminPassword, String RefundQuantity,String RefundAmount, String Service,
			String RequireEBC, String OrganizationId, String EBCUserName, String EBCPassword, String OrderNumber)
			throws Exception {

		logger.info("[OM][executeOMAuthAuthReversal] Starts");
		commonUtil.BackOfficeCheck(driver,AdminName, AdminPassword, Service, RequireEBC, OrganizationId, EBCUserName,
				EBCPassword, OrderNumber);
		commonUtil.executeAuthReversal(driver,Service,RefundQuantity,RefundAmount);

		commonUtil.logoutBackOffice(driver);
		logger.info("[OM][executeOMAuthAuthReversal] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA)
	public void executeOMAuthCapture(String AdminName, String AdminPassword, String Service, String RequireEBC,
			String OrganizationId, String EBCUserName, String EBCPassword, String OrderNumber) throws Exception {

		logger.info("[OM][executeOMAuthCapture] Starts");
		commonUtil.BackOfficeCheck(driver,AdminName, AdminPassword, Service, RequireEBC, OrganizationId, EBCUserName, EBCPassword, OrderNumber);
		commonUtil.executeCapture(driver,Service);
		logger.info("[OM][executeOMAuthCapture] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA)
	public void executeOMAuthCapRefund(String AdminName, String AdminPassword, String ProductName,
			String RefundQuantity, String RefundAmount, String Service, String RequireEBC, String OrganizationId,
			String EBCUserName, String EBCPassword, String OrderNumber) throws Exception {

		logger.info("[OM][executeOMAuthCapRefund] Ends");
		String[] OrderedQuantityList = ProductName.split(Constants.COMMA);

		commonUtil.BackOfficeCheck(driver,AdminName, AdminPassword, Service, RequireEBC, OrganizationId, EBCUserName,
				EBCPassword, OrderNumber);
		commonUtil.executeCapture(driver,Service);
		if (Constants.ONE == OrderedQuantityList.length) {
			commonUtil.executeRefund(driver,RefundQuantity, RefundAmount);
		} else {
			commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount);
		}

		commonUtil.logoutBackOffice(driver);
		logger.info("[OM][executeOMAuthCapRefund] Ends");
	}

	@Test(dataProvider = Constants.GET_DATA)
	public void executeOMSaleRefund(String AdminName, String AdminPassword, String ProductName, String RefundQuantity,
			String RefundAmount, String Service, String RequireEBC, String OrganizationId, String EBCUserName,
			String EBCPassword, String OrderNumber) throws Exception {

		logger.info("[OM][executeOMSaleRefund] Starts");
		String[] OrderedQuantityList = ProductName.split(Constants.COMMA);

		commonUtil.BackOfficeCheck(driver,AdminName, AdminPassword, Service, RequireEBC, OrganizationId, EBCUserName,
				EBCPassword, OrderNumber);

		if (Constants.ONE == OrderedQuantityList.length) {
			commonUtil.executeRefund(driver,RefundQuantity, RefundAmount);
		} else {
			commonUtil.executePartialRefund(driver,RefundQuantity, RefundAmount);
		}
		commonUtil.logoutBackOffice(driver);
		logger.info("[OM][executeOMSaleRefund] Ends");
	}
	
	
}
