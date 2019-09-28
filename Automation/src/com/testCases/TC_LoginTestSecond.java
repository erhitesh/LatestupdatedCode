package com.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pageObjects.LoginPage;
import com.utilities.GlobalParam;
import com.utilities.Reusable;
import com.utilities.XLUtilis;
import com.utils.ExtentManager;

public class TC_LoginTestSecond extends BaseTest {
	LoginPage loginPage = new LoginPage(driver);
	Reusable reusable = new Reusable(driver);

	@Test(dataProvider = "LoginData")
	public void loginDDT(String uname, String password) {
		loginPage.setUserName(uname);
		logger.info("User Name entered Successfully...");
		ExtentManager.logs(Status.INFO, "User Name entered Successfully...");
		loginPage.setPassword(password);
		logger.info("Password entered Successfully...");
		ExtentManager.logs(Status.INFO, "Password entered Successfully...");
		loginPage.submitLoginBtn();
		ExtentManager.logs(Status.INFO, "Submit Button click Successfully...");
		logger.info("Login Button click successfully");

		if (reusable.getAlert() != null) {
			reusable.getAlert().accept();
		}
		else if (driver.getTitle().contains(config.getPropertyValue("title"))) {
			Assert.assertTrue(true, "Title Matched successfully");
			logger.info("Title Matched successfully");
			ExtentManager.logs(Status.INFO, "Login Successfully");
		}
	}

	@DataProvider(name = "LoginData")
	public String[][] getLoginData() {
		String loginData[][] = null;
		String sheetPath = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "src//com//testData//LoginData.xlsx";
		int rownum = XLUtilis.getRowCount(sheetPath, "Credential");
		int colCount = XLUtilis.getCellCount(sheetPath, "Credential", 1);
		loginData = new String[rownum][colCount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = XLUtilis.getCellData(sheetPath, "Credential", i, j);
			}
		}

		return loginData;
	}
}
