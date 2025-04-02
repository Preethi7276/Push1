package com.cybersource.test.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtil {
	static Properties prop = null;
	static Xls_Reader xls = null;
	private static Xls_Reader xlsWooCommerce = null;
	static final Logger logger = Logger.getLogger(FileUtil.class);

	
	public static Properties loadConfig(String tech) throws IOException {
		prop = new Properties();
		String techStr = (null != tech) ? tech + Constants.UNDERSCORE : Constants.EMPTY_STRING;
		logger.info(Constants.CONFIG_FILE_EQUAL + Constants.RESOURCES_FORWARD_SLASH + techStr + Constants.CONFIG_PROPERTIES);
		FileInputStream fs = new FileInputStream(Constants.RESOURCES_FORWARD_SLASH + techStr + Constants.CONFIG_PROPERTIES);
		prop.load(fs);
		return prop;

	}

	public static Object[][] getSheetData(String fileName, String sheetName) {
		logger.info("[FileUtil][getSheetData] WOOCOMMERCE?:" + fileName.contains(Constants.TECH_WOOCOMMERCE));
		if (null != fileName) {
			if (fileName.contains(Constants.TECH_WOOCOMMERCE)) {
				if (null == xlsWooCommerce) {
					logger.info("Loading woocommerce specific XLSX for one time");
					logger.info("[FileUtil][getSheetData] fileName = " + fileName);
					xlsWooCommerce = new Xls_Reader(fileName);
				}
				logger.info(Constants.ASSIGNED_WOOCOMMERCE_SPECIFIC_XLSX);
				xls = xlsWooCommerce;
			}
		}
		logger.info(Constants.FILENAME_EQUAL + fileName);
		logger.info(Constants.SHEETNAME_EQUAL + sheetName);

		int cols = xls.getColumnCount(sheetName);
		int rows = xls.getRowCount(sheetName);
		logger.info(Constants.ROW_COUNT_EQUAL + rows);
		logger.info(Constants.COL_COUNT_EQUAL + rows);
		Object data[][] = new Object[rows - 1][cols];
		String myData = null;
		for (int rNum = 2; rNum <= rows; rNum++) {
			myData = "";
			for (int cNum = 0; cNum < cols; cNum++) {
				data[rNum - 2][cNum] = xls.getCellData(sheetName, cNum, rNum);
				myData += xls.getCellData(sheetName, cNum, rNum) + Constants.COMMA_SPACE;
			}
			logger.info(Constants.ROW_EQUAL+rNum+ Constants.COLON+ myData);
		}
		return data;
	}

	public static void writeDataToSheet(String path, String sheetame, Object profileData[][]) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			FileOutputStream fos = new FileOutputStream(path);
			Sheet sheet = workbook.getSheet(sheetame);
			XSSFRow row = null;
			XSSFCell cell = null;
			int cellNumber = 6;
			row = (XSSFRow) sheet.getRow(1);
			for (Object[] aProfile : profileData) {
				cellNumber = 6;
				for (Object field : aProfile) {
					cell = row.getCell(cellNumber);
					if (cell == null) {
						cell = row.createCell(cellNumber);
					}
					cell.setCellValue(field.toString());
					cellNumber++;
				}
			}
			workbook.write(fos);
		}
		catch (IOException e) {
			logger.info(Constants.IOEXCEPTION_OCCURRED);
			e.printStackTrace();
		}
	}

}
