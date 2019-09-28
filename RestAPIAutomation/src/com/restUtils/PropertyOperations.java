package com.restUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyOperations {

	private static Properties prop = null;
	
	public static String getPropertyData(String configFileName, String propertyName) {
		String propertyValue = null;
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "Configuration"
					+ GlobalParam.slash + configFileName + ".properties")));
			propertyValue = prop.getProperty(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return propertyValue;
	}
}
