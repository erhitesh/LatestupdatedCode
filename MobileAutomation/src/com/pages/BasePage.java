package com.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class BasePage {

	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	public static Logger logger;

	public BasePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
		logger = Logger.getLogger("MobileAutomation");
		PropertyConfigurator.configure("Log4j.properties");
	}

	protected void waitVisibility(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected void sleep(int timeoutsInSeconds) {
		try {
			Thread.sleep(1000 * timeoutsInSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void waitAndClick(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
	}

	protected void click(By by) {
		waitVisibility(by);
		driver.findElement(by).click();
	}
	
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected WebElement waitAndFindElement(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	protected List<WebElement> waitAndFindElements(By by) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}
	
	protected String getText(By by) {
		return waitAndFindElement(by).getText();
	}
	
	protected void sendText(By by, String message) {
		waitAndFindElement(by).sendKeys(message);
	}
	
	protected void assertEquals(String actual, String expected) {
		Assert.assertEquals(actual, expected, actual + " and " + expected + " text are not matched");
	}
}
