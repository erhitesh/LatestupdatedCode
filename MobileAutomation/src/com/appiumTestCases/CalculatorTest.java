package com.appiumTestCases;

import org.testng.annotations.Test;

import com.enums.OperandType;
import com.enums.OperatorType;

import junit.framework.Assert;

public class CalculatorTest extends BaseTest {

	@Test(priority=1)
	public void performAddition() {
		calculatorPage.selectOperandValue(OperandType.NINE).click();
		calculatorPage.performCalculatorOperation(OperatorType.PLUS);
		calculatorPage.selectOperandValue(OperandType.ONE).click();
		calculatorPage.submitOperation();
		Assert.assertTrue(10 == Integer.parseInt(calculatorPage.resultStatus().getText()));
	}
	
	@Test(priority=2)
	public void performSubstration() {
		calculatorPage.selectOperandValue(OperandType.NINE).click();
		calculatorPage.performCalculatorOperation(OperatorType.MINUS);
		calculatorPage.selectOperandValue(OperandType.ONE).click();
		calculatorPage.submitOperation();
		Assert.assertTrue(8 == Integer.parseInt(calculatorPage.resultStatus().getText()));
	}
	
	@Test(priority=3)
	public void performMultiplication() {
		calculatorPage.selectOperandValue(OperandType.NINE).click();
		calculatorPage.performCalculatorOperation(OperatorType.MULTI);
		calculatorPage.selectOperandValue(OperandType.ONE).click();
		calculatorPage.submitOperation();
		Assert.assertTrue(9 == Integer.parseInt(calculatorPage.resultStatus().getText()));
	}
	
	@Test(priority=4)
	public void performDivision() {
		calculatorPage.selectOperandValue(OperandType.NINE).click();
		calculatorPage.performCalculatorOperation(OperatorType.MULTI);
		calculatorPage.selectOperandValue(OperandType.THREE).click();
		calculatorPage.submitOperation();
		Assert.assertTrue(3 == Integer.parseInt(calculatorPage.resultStatus().getText()));
	}
}
