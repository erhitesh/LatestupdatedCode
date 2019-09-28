package com.restUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.restAssuredSessions.DriverSession;


public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxCount = 1; // Run the failed test case 2 times

	@Override
	public boolean retry(ITestResult result) {
		boolean status = false;
		if (!result.isSuccess()) {
			if (count < maxCount) {
				count++;
				result.setStatus(ITestResult.FAILURE);
				extendReportsFailOperations(result);
				status = true;
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
			status = false;
		}

		return status;
	}
	
	public void extendReportsFailOperations(ITestResult iTestResult) {
		DriverSession.getExtent().log(Status.FAIL, MarkupHelper
				.createLabel("TC " + iTestResult.getMethod().getMethodName() + " Again failed", ExtentColor.RED));
	}
}
