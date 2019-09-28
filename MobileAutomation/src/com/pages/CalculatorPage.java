package com.pages;

import com.enums.OperandType;
import com.enums.OperatorType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class CalculatorPage extends BasePage {

	public CalculatorPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public void performCalculatorOperation(OperatorType opType) {
		String opTypeValue = "";

		switch (opType) {
		case CLEAR:
			opTypeValue = "clear";
			break;

		case DELETE:
			opTypeValue = "delete";
			break;

		case DIVIDE:
			opTypeValue = "divide";
			break;

		case PLUS:
			opTypeValue = "plus";
			break;

		case MINUS:
			opTypeValue = "minus";
			break;

		default:
			opTypeValue = "multiply";
			break;
		}

		waitAndFindElement(MobileBy.AccessibilityId(opTypeValue)).click();
	}

	public MobileElement resultStatus() {
		return driver.findElement(MobileBy.id("com.android.calculator2:id/result"));
	}

	public MobileElement selectOperandValue(OperandType operandType) {
		String firstPart = "com.android.calculator2:id/digit_";
		int lastPart = 0;

		switch (operandType) {
		case ZERO:
			lastPart = operandType.ZERO.getValue();
			break;

		case ONE:
			lastPart = operandType.ONE.getValue();
			break;

		case TWO:
			lastPart = operandType.TWO.getValue();
			break;

		case THREE:
			lastPart = operandType.THREE.getValue();
			break;

		case FOUR:
			lastPart = operandType.FOUR.getValue();
			break;

		case FIVE:
			lastPart = operandType.FIVE.getValue();
			break;

		case SIX:
			lastPart = operandType.SIX.getValue();
			break;

		case SEVEN:
			lastPart = operandType.SEVEN.getValue();
			break;

		case EIGHT:
			lastPart = operandType.EIGHT.getValue();
			break;

		case NINE:
			lastPart = operandType.NINE.getValue();
			break;

		default:
			break;
		}

		return driver.findElement(MobileBy.id(firstPart + lastPart));
	}
	
	public void submitOperation() {
		if (driver.findElement(MobileBy.AccessibilityId("equals")).isDisplayed()) {
			driver.findElement(MobileBy.AccessibilityId("equals")).click();
		} else {
			System.out.println("Nothing to perform");
		}
	}
}
