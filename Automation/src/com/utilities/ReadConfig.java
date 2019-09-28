package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		File srcFile = new File(GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "Configuration"
				+ GlobalParam.slash + "config.properties");
		try {
			prop = new Properties();
			prop.load(new FileInputStream(srcFile));
		} catch (IOException io) {
			System.out.println("Exception is " + io.getMessage());
		}
	}

	public String getApplicationUrl() {
		return prop.getProperty("baseUrl");
	}

	public String getChromePath() {
		return prop.getProperty("chromepath");
	}

	public String getFirefoxPath() {
		return prop.getProperty("firefoxpath");
	}

	public String getIEPath() {
		return prop.getProperty("iepath");
	}

	public String getuserName() {
		return prop.getProperty("username");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

	public String getPropertyValue(String propName) {
		return prop.getProperty(propName);
	}
}
