package com.pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private static final int TIMEOUT = 5;
	private static final int POLLING = 100;

	protected WebDriver driver;
	private WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		PageFactory.initElements(driver, this);
	}

	protected void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected void waitForTextToDisappear(By locator, String text) {
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
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
