package com.utils;

import java.io.File;
import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.driverSession.DriverSession;


public class TestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extentReports = ExtentManager.createInstance();

	public static String getExecutingMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}

	public static String getExecutingMethodDescription(ITestResult result) {
		return result.getMethod().getDescription();
	}

	public static String getThrowableMessage(ITestResult result) {
		return result.getThrowable().getMessage();
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		DriverSession.getExtent().log(Status.PASS,
				MarkupHelper.createLabel("TC " + getExecutingMethodName(result) + "Passed!", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		DriverSession.getExtent().log(Status.FAIL,
				MarkupHelper.createLabel("TC " + getExecutingMethodName(result) + " Failed!", ExtentColor.RED));
		DriverSession.getExtent().log(Status.FAIL,
				MarkupHelper.createLabel(
						"TC " + getExecutingMethodName(result) + " Failed Reason " + getThrowableMessage(result),
						ExtentColor.RED));
		String screenshotPath = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "Screenshot" + GlobalParam.slash
				+ result.getName() + ".png";
		if (new File(screenshotPath).exists()) {
			try {
				screenshotPath = RestUtils.captureScreenshot(DriverSession.getLastExecutionDriver(),
						getExecutingMethodName(result));
				DriverSession.getExtent().fail(
						"Screenshot is below:" + DriverSession.getExtent().addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		DriverSession.getExtent().log(Status.SKIP,
				MarkupHelper.createLabel(getExecutingMethodName(result) + "Skipped!", ExtentColor.YELLOW));
		DriverSession.getExtent().log(Status.SKIP, "TEST CASE SKIPED IS " + result.getName());
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extentReports.createTest(getExecutingMethodName(result),
				getExecutingMethodDescription(result));
		DriverSession.setExtent(extentTest);
	}

	public void onStart(ISuite suite) {
		RestUtils.clearScreenshotFromScreenshotFolder();
		RestUtils.clearReportsFromReportsFolder();
		DriverSession.setExtentReport(extentReports);
	}

	public void onFinish(ISuite suite) {
		DriverSession.getExtentReport().flush();
	}
}
