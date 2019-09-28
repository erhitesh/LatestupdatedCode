package com.restAssuredSessions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class DriverSession {

	private static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<ExtentReports>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<WebDriver>();

	
	public static ExtentReports getExtentReport() {
		return extentReport.get();
	}
	
	public static void setExtentReport(ExtentReports report) {
		extentReport.set(report);
	}
	
	public static ExtentTest getExtent() {
		return extentTest.get();
	}

	public static void setExtent(ExtentTest test) {
		extentTest.set(test);
	}

	public static WebDriver getLastExecutionDriver() {
		return driverInstance.get();
	}

	public static void setDriverInstance(WebDriver driver) {
		driverInstance.set(driver);
	}
}
