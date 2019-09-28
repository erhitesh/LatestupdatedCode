package com.testCases;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class DriverSession {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setLastExecutionDriver(WebDriver wdriver) {
		driver.set(wdriver);
	}

	public static WebDriver getLastExecutionDriver() {
		return driver.get();
	}

	public static void setExtent(ExtentTest test) {
		extentTest.set(test);
	}

	public static ExtentTest getExtent() {
		return extentTest.get();
	}
}
