package com.utils;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.driverSession.DriverSession;

public class ExtentManager {

	// Global Variable Declaration
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	private static InetAddress ip;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}

		return extent;
	}

	public static ExtentReports createInstance() {
		String timeStamp = "";
		String repoName = "";
		try {
			timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss a").format(new Date());
			repoName = "Report" + GlobalParam.slash + "Test-Report-" + timeStamp + ".html";

			htmlReporter = new ExtentHtmlReporter(GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "test-output"
					+ GlobalParam.slash + repoName);
			htmlReporter.config().setDocumentTitle(PropertyOperations.getPropertyData("Report", "ReportName"));
			htmlReporter.config().setReportName(PropertyOperations.getPropertyData("Report", "ReportDocumentTitle"));
			htmlReporter.config().setEncoding("UTF-8");
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");

			ip = InetAddress.getLocalHost();
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("OS", System.getProperty("os.name").toUpperCase());
			extent.setSystemInfo("Host Name", ip.getHostName());
			extent.setSystemInfo("IP", ip.toString());
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", PropertyOperations.getPropertyData("Report", "Environment"));
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);
			extent.setReportUsesManualConfiguration(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return extent;
	}

	public static void logs(Status status, String message) {
		String screenshot = null;
		switch (status) {
		case INFO:
			try {
				WebDriver driver = DriverSession.getLastExecutionDriver();
				if (driver == null ) {
					System.out.println("Now Webdriver Instance ...... ");
				}
				screenshot = RestUtils.captureScreenshot(DriverSession.getLastExecutionDriver());
				DriverSession.getExtent().log(Status.INFO, MarkupHelper.createLabel(
						message + " " + " <a href = '" + screenshot + "'>Screenshot_Link</a>", ExtentColor.GREEN));
			} catch (Exception e) {
			}
			break;

		case PASS:
			DriverSession.getExtent().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
			break;

		case FAIL:
			DriverSession.getExtent().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
			break;

		case SKIP:
			DriverSession.getExtent().log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.YELLOW));
			break;

		default:
			Reporter.log("Not Found Appropriate operation types...");
		}
	}
}
