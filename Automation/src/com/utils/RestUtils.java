package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.driverSession.DriverSession;

public class RestUtils {
	private static String reportFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "test-output"
			+ GlobalParam.slash + "Report";

	private static String screenshotFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash
			+ "Screenshot";

	public static void clearReportsFromReportsFolder() {
		File reportFile = new File(reportFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	public static void clearScreenshotFromScreenshotFolder() {
		File reportFile = new File(screenshotFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	public static String captureScreenshot(WebDriver driver, String testCaseName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotLocation = screenshotFolderLocation + GlobalParam.slash + testCaseName + getCurrentDateAndTime() +  ".png";		
		File destFile = new File(screenshotLocation);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return screenshotLocation;
	}
	
	public static String captureScreenshot(WebDriver driver) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotLocation = screenshotFolderLocation + GlobalParam.slash + getCurrentDateAndTime() +  ".png";		
		File destFile = new File(screenshotLocation);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return screenshotLocation;
	}
	
	public static String getCurrentDateAndTime() {
		String str = null;
		try {
			str = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS").format(new Date()).replace(" ", "")
					.replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}

		return str;
	}

}
