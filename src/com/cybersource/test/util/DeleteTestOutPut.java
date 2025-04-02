package com.cybersource.test.util;

import java.io.File;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;

public class DeleteTestOutPut {
	final static Logger logger = Logger.getLogger(DeleteTestOutPut.class);
	
	 @BeforeSuite
	 public static void deleteTestOutPutDirectory() {
		 logger.info(Constants.TEST_OUTPUT_DELETE_IS_CALLED);
		 boolean isDeleted = deleteTestOutput(new File(System.getProperty(Constants.USER_DIR)+Constants.TEST_OUTPUT_DIRECTORY));
		 if(isDeleted) {
			 logger.info(Constants.TEST_OUTPUT_DELETED);
		 }
		 else {
			 logger.info(Constants.ERROR_DELETING_TEST_OUTPUT);
		 }		 
	 }
	
	 public static boolean deleteTestOutput(File dir) {
		 boolean success = false;
		 String[] children = null;
	      if (dir.isDirectory()) {
	         children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            success = deleteTestOutput (new File(dir, children[i]));	            
	            if (!success) {
	               return false;
	            }
	         }
	      }
	      return dir.delete();
	   }
}
