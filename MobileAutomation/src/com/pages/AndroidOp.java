package com.pages;

import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidOp extends BasePage {

	public AndroidDriver<MobileElement> androidDriver = null;
	public AndroidOp(AppiumDriver<MobileElement> driver) {
		super(driver);
		androidDriver = (AndroidDriver<MobileElement>) driver;
	}
	
	public void lockUnlockDevice() {
		if (androidDriver.isDeviceLocked()) {
			logger.info("Going to Lock Device for 10 seconds...");
			androidDriver.lockDevice(Duration.ofSeconds(10));
			
			// Unlock Device if it is locked
			if (androidDriver.isDeviceLocked()) {
				logger.info("Going to Unock Device after 10 seconds...");
				androidDriver.unlockDevice();
			} else {
				logger.info("Device Already Unlock by capability....");
			}
		}
	}
	
}

