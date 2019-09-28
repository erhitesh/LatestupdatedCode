package com.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusable {

	public WebDriver driver;
	
	public Reusable(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public Alert getAlert() {
		Alert alert = null;
		alert = new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());

		if (alert != null) {
			alert = driver.switchTo().alert();
		}

		return alert;
	}

	public void acceptAlert() {
		getAlert().accept();
	}

	public void dismissAlert() {
		getAlert().dismiss();
	}

	public String getAlertMessage() {
		return getAlert().getText();
	}

	public void setAlertMessage(String message) {
		getAlert().sendKeys(message);
	}
}
