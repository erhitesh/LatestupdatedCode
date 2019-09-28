package com.restAssuredData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.testng.log4testng.Logger;

import com.restAssuredUtilities.GlobalParam;

public class PropertyOperations {
	private static PropertyOperations singleton = null;
	private static Properties prop = new Properties();
	private static FileInputStream fis = null;
	private static FileOutputStream fos = null;
	private static String propertyFileLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash;
	static Logger logger = Logger.getLogger(PropertyOperations.class);

	private PropertyOperations() {
	}

	public static PropertyOperations getSingletonPropertyInstance() {
		if (singleton == null) {
			singleton = new PropertyOperations();
		}

		return singleton;
	}

	public static Properties loadProperties(String propFileName) {
		try {
			fis = new FileInputStream(propertyFileLocation + propFileName + ".properties");
			prop.load(fis);
			fis.close();
			logger.info("Property File Load Successfully......");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static void saveProperties(String fileName) {
		try {
			fos = new FileOutputStream(propertyFileLocation + fileName + ".properties");
			prop.store(fos, null);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getEnvironmentData(String propName) {
		return loadProperties("Configuration" + GlobalParam.slash + "Environment").getProperty(propName);
	}

	public static String getPropertyValue(String propFileName, String propName) {
		String propValue = null;
		try {
			if (StringUtils.isEmpty(propName)) {
				logger.warn("Property Value not found ", new NullPointerException());
			}
			propValue = loadProperties(propFileName).getProperty(propName);
		} catch (Exception e) {
		}

		return propValue;
	}

//	@SuppressWarnings("null")
//	public static String getPropertyValue(String propertyName) {
//		String propertyValue = null;
//		Properties prop = null;
//		try {
//			if (StringUtils.isEmpty(propertyName)) {
//				logger.warn("Please Enter the valid property name", new NullPointerException());
//			}
//			prop.load(new FileInputStream(new File(GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash
//					+ "Configuration" + GlobalParam.slash + ".properties")));
//			propertyValue = prop.getProperty(propertyName);
//		} catch (IOException e) {
//		}
//
//		return propertyValue;
//	}

	public static void setPropertyValue(String fileName, String propertyName, String propertyValue) {
		try {
			fos = new FileOutputStream(propertyFileLocation + fileName + ".properties");
			prop.setProperty(propertyName, propertyValue);
			prop.store(fos, "Employee Id Enteries..........");
			fos.close();
			logger.info("Property operation has been performed......");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		PropertyOperations.setPropertyValue("Employee", "emp", "we");
//	}
}
