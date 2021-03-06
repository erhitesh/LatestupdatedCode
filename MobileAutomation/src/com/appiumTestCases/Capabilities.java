package com.appiumTestCases;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.utils.GlobalParam;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Capabilities {
	
	public static String appPath = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "APPS" + GlobalParam.slash;

	public static DesiredCapabilities getDesiredCapability(String deviceInfo) {
		DesiredCapabilities cap = null;
		cap = new DesiredCapabilities();

		String str[] = deviceInfo.split(" ");
		String deviceName = str[0];
		String platformName = str[1];
		String platformVersion = str[2];
		String systemPort = str[3];

		if (platformName.equalsIgnoreCase("Android")) {
			//cap.setCapability("unlockType", "password"); // cap.setCapability("unlockType", "pin");
			//cap.setCapability("unlockKey", "123456");

			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator");//AutomationName.ANDROID_UIAUTOMATOR2);
			cap.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.parseInt(systemPort));
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
			cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
			//cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, Integer.parseInt("30"));
			//cap.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, Integer.parseInt("30"));
			//cap.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, Integer.parseInt("30"));
			//cap.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, Integer.parseInt("90"));
			// cap.setCapability(MobileCapabilityType.APP, appPath + ".apk");

		} else if (platformName.equalsIgnoreCase("ios")) {
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.UDID, "");
			cap.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, systemPort);
			cap.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, Integer.parseInt("60"));
			cap.setCapability(MobileCapabilityType.APP, ".ipa");
		}

	//	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Integer.parseInt("60"));
	//	cap.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
	//	cap.setCapability(MobileCapabilityType.NO_RESET, true);

		return cap;
	}
}
