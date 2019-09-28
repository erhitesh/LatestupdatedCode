package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pageObjects.LoginPage;
import com.utilities.Reusable;
import com.utils.ExtentManager;


public class TC_LoginTest extends BaseTest {
	// Reusable reusable = new Reusable(driver);
	LoginPage loginPage = null;

	@Test(enabled = false)
	public void loginTest() {
		loginPage = new LoginPage(driver);
		loginPage.setUserName(username);
		ExtentManager.logs(Status.INFO, "User Name entered Successfully...");
		logger.info("User Name entered Successfully...");
		ExtentManager.logs(Status.INFO, "User Name entered Successfully...");
		loginPage.setPassword(password);
		logger.info("Password entered Successfully...");
		ExtentManager.logs(Status.INFO, "Password entered Successfully...");
		loginPage.submitLoginBtn();
		logger.info("Login Button click successfully");
		ExtentManager.logs(Status.INFO, "Login Button Click Successfully...");

		if (driver.getTitle().contains(config.getPropertyValue("title"))) {
			Assert.assertTrue(true, "Title Matched successfully");
			logger.info("Title Matched successfully");
			ExtentManager.logs(Status.PASS, "LoginTest Passes Successfully...");
		} else {
			Assert.assertTrue(false, "Title not Matched");
			logger.info("Title not Matched.");
			ExtentManager.logs(Status.FAIL, "Login Test Failed...");
		}
	}

	@Test
	public void validateLoginCredentialTest() throws InterruptedException {
		// Reusable reusable = new Reusable(driver);
		loginPage = new LoginPage(driver);
		loginPage.setUserName(username);
		logger.info("User Name entered Successfully...");
		ExtentManager.logs(Status.INFO, "User Name entered Successfully...");
		loginPage.setPassword("dnakdn");
		logger.info("Password entered Successfully...");
		ExtentManager.logs(Status.INFO, "User Password entered Successfully...");
		loginPage.submitLoginBtn();
		logger.info("Login Button click successfully");
		ExtentManager.logs(Status.INFO, "Login button Click Successfully...");

		Thread.sleep(5000);
		if (loginPage.getAlert() != null) {
			loginPage.getAlert().accept();
		}

		if (driver.getTitle().contains(config.getPropertyValue("title"))) {
			Assert.assertTrue(true, "Title Matched successfully");
			logger.info("Title Matched successfully");
			ExtentManager.logs(Status.INFO, "Title Matched Successfully...");
		} else {
			Assert.assertTrue(false, "Title not Matched");
			logger.info("Title not Matched.");
			ExtentManager.logs(Status.INFO, "Title Not Matched Successfully...");
		}
	}
}