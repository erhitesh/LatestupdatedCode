package com.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.utilities.GlobalParam;
import com.utilities.ReadConfig;

public class BaseTest {

	private String reportFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "test-output"
			+ GlobalParam.slash + "Report";
	private String screenshotFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "Screenshot";
	ReadConfig config = new ReadConfig();

	public String baseUrl = config.getApplicationUrl();
	public String username = config.getuserName();
	public String password = config.getPassword();
	public WebDriver driver;
	public Logger logger;
	
	@BeforeSuite
	public void clearJunkData() {
//		clearReportData();
//		clearScreenshotData();
	}

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		logger = Logger.getLogger("Banking Application");
		PropertyConfigurator.configure(GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "log4j.properties");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", config.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		DriverSession.setLastExecutionDriver(driver);
		DriverSession.getLastExecutionDriver().get(baseUrl);
		DriverSession.getLastExecutionDriver().manage().window().maximize();
		DriverSession.getLastExecutionDriver().manage().deleteAllCookies();
		DriverSession.getLastExecutionDriver().manage().timeouts().implicitlyWait(Integer.parseInt(config.getPropertyValue("Implicitly_Wait_Timeouts")),
				TimeUnit.MINUTES);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		try {
			if (ITestResult.FAILURE == result.getStatus()) {
				takesScreenCapture(DriverSession.getLastExecutionDriver(), result.getName());

			} else if (result.getStatus() == ITestResult.SKIP) {
				takesScreenCapture(DriverSession.getLastExecutionDriver(), result.getName());

			} else if (result.getStatus() == ITestResult.SUCCESS) {
//				takesScreenCapture(driver, result.getName());
			}
			DriverSession.getLastExecutionDriver().quit();
		} catch (Exception e) {
			if (DriverSession.getLastExecutionDriver() != null) {
				DriverSession.getLastExecutionDriver().close();
				DriverSession.getLastExecutionDriver().quit();
			}
		} finally {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
		}
	}

	public String takesScreenCapture(WebDriver driver, String testCaseName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = screenshotFolderLocation + GlobalParam.slash + testCaseName + getCurrentDateAndTime() + ".png";
		File destFile = new File(dest);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest;
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
	
	public void clearReportData() {
		File reportFile = new File(reportFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	public void clearScreenshotData() {
		File reportFile = new File(screenshotFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}
}
