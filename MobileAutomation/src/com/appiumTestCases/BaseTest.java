package com.appiumTestCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.driverSession.DriverSession;
import com.enums.OperatorType;
import com.enums.PlatformTypes;
import com.pages.AndroidOp;
import com.pages.CalculatorPage;
import com.utils.RestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public class BaseTest {
	
	public AppiumDriver<MobileElement> aDriver;
	public WebDriver driver;
	public WebDriverWait wait;
	protected CalculatorPage calculatorPage = null;
	protected AndroidOp androidOp = null;
	
	@SuppressWarnings("unchecked")
	@BeforeMethod
	@Parameters({ "deviceInfo" })
	public void getDriverInstance(String deviceInfo) {
		PlatformTypes platformType = PlatformTypes.ANDROID;
		try {
			if (platformType.equals(PlatformTypes.ANDROID)) {
				driver = new AndroidDriver<MobileElement>(new URL("https://127.0.0.1:4723/wd/hub"),
						Capabilities.getDesiredCapability(deviceInfo));

			} else if (platformType.equals(PlatformTypes.IOS)) {
				driver = new IOSDriver<MobileElement>(new URL("https://localhost:4723/wd/hub"),
						Capabilities.getDesiredCapability(deviceInfo));
			}

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (MalformedURLException e) {
			e.getMessage();
		}
		DriverSession.setLastExecutionDriver(driver);
		wait = new WebDriverWait(DriverSession.getLastExecutionDriver(), 10);

		// Class Initialization
		aDriver = (AppiumDriver<MobileElement>)DriverSession.getLastExecutionDriver();
		calculatorPage = new CalculatorPage(aDriver);
		androidOp = new AndroidOp(aDriver);
	}
	
	@SuppressWarnings("unchecked")
	@AfterMethod
	public void tearDown(ITestResult result) {
		calculatorPage.performCalculatorOperation(OperatorType.CLEAR);
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				RestUtils.captureScreenshot(DriverSession.getLastExecutionDriver(), result.getMethod().getMethodName());
				
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				System.out.println("TC PASSED");
				
			} else if (result.getStatus() == ITestResult.SKIP) {
				System.out.println("TC SKIPPED");
			}
			
			if (DriverSession.getLastExecutionDriver() != null) {
				driver = null;
				((AppiumDriver<MobileElement>) DriverSession.getLastExecutionDriver()).closeApp();
			}
		} catch (Exception e) {
			if (DriverSession.getLastExecutionDriver() != null) {
				DriverSession.setLastExecutionDriver(null);
			}
		}
	}
}
