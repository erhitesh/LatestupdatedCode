package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPage extends BasePage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.NAME, using = "uid")
	WebElement txtUserName;
	
	@FindBy(how = How.NAME, using = "password")
	WebElement password;
	
	@FindBy(how = How.NAME, using = "btnLogin")
	WebElement loginBtn;
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String upassword) {
		password.sendKeys(upassword);
	}
	
	public void submitLoginBtn() {
		loginBtn.click();
	}
}
