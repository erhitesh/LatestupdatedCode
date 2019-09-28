package com.restAssuredAPI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.restAssuredSessions.DriverSession;
import com.restAssuredUtilities.GlobalParam;
import com.restUtils.RestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static WebDriver driver;
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empId = "";
	public Logger logger;

//	@BeforeSuite
//	public void clearScreenshotAndReport() {
//		logger.info("Inside Before Suite Method....");
//		clearScreenshotData();
//		clearReportData();
//	}

	@BeforeTest
	@Parameters("hostUrl")
	public void setUp(String hostUrl) {
		logger = Logger.getLogger("RestAssuredApiTesting");
		PropertyConfigurator.configure("log4j.properties");
		// Log Level
		logger.setLevel(Level.DEBUG);
		RestAssured.baseURI = hostUrl; // PropertyOperations.getEnvironmentData("HostForEmp");
		httpRequest = RestAssured.given();
	
	}

	@AfterTest
	public void tearDown() {
		RestAssured.reset();
	}
}
