package com.cybersource.test;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.cybersource.test.util.Constants;
import com.cybersource.test.util.FileUtil;

public class CommonTest {

	static final String FILE_PATH_PREFIX="resources/testdata/";
	protected static String XLSX_FILE_PATH; 
	static String tech = null; 
	protected static Properties prop=null;
	private static Properties propWooCommerce=null;
	static int count = 0;
	final static Logger logger = Logger.getLogger("");
	static {
		count++;
		logger.info(count+" [CommonTest][static]  tech:" + tech);
	}
	

	public void loadCommonConfig(ITestContext context) throws IOException{
		count++;
		String techMasterStr = context.getCurrentXmlTest().getParameter("techMaster");
		String techStr = context.getCurrentXmlTest().getParameter("tech");
		tech =(null == techMasterStr)?techStr:techMasterStr;
		logger.info(count+" [CommonTest][loadCommonConfig]  XML:"+context.getCurrentXmlTest().getSuite().getFileName());
		logger.info(count+" [CommonTest][loadCommonConfig]  tech:" + tech+", Suite="+context.getCurrentXmlTest().getSuite()+",context.getName()="+context.getName()+", prop="+prop);
		//If technology woocommerce not there exit the system
		if(null == tech || !Constants.TECH_WOOCOMMERCE.equals(tech)) {
			logger.info("Since no technology configured, system is exiting, please configure in xml tech as woocommerce");
			System.exit(0);
		}
		if(Constants.TECH_WOOCOMMERCE.equals(tech)) {
			if(null == propWooCommerce) {
				logger.info("Loading WooCommerce specific properties for one time");
				logger.info(tech);
				propWooCommerce = FileUtil.loadConfig(tech);	
				logger.info(propWooCommerce);
			}
			prop = propWooCommerce;
			logger.info("Assigned WooCommerce specific properties");
		}

		
		if(null != prop) {
			XLSX_FILE_PATH = FILE_PATH_PREFIX+tech+Constants.UNDERSCORE+prop.getProperty("xls.filepath");
		}		
		logger.info(count+" [CommonTest][loadCommonConfig]  XLSX_FILE_PATH:" +XLSX_FILE_PATH);
	//	logger.info(count+" [CommonTest][loadCommonConfig]  tech:" + tech+", prop="+prop);
	}
	
	
}
