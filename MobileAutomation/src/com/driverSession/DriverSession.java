package com.driverSession;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class DriverSession {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	// private static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<AppiumDriver<MobileElement>>();
	private static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<ExtentReports>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public static void setLastExecutionDriver(WebDriver wd) {
		driver.set(wd);
	}
	
	public static WebDriver getLastExecutionDriver() {
		return driver.get();
	}
	
	public static void setExtentReport(ExtentReports report) {
		extentReport.set(report);
	}
	
	public static ExtentReports getExtentReport() {
		return extentReport.get();
	}
	
	public static void setExtent(ExtentTest test) {
		extentTest.set(test);
	}
	
	public static ExtentTest getExtent() {
		return extentTest.get();
	}
}
