package com.qafoc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//common object login locators
	
	@FindBy(xpath="//input[@id='input-email']") 
	private WebElement emailAdressField;
	
	@FindBy(xpath="//input[@id='input-password']") 
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']") 
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]") 
	private WebElement emailPasswordNotMatchingWarning;
	
	
	//Action Methods
	
	public void enterEmailAdress(String emailText)
	{
		emailAdressField.sendKeys(emailText);
	}
	
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton()
	{
		loginButton.click();
	}
	
	//we can combile upper 3 method in one for login (email+pass+click on button)
	
	public void loginAllSteps(String emailText, String password)
	{
		emailAdressField.sendKeys(emailText);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	
	
	
	
	public String retriveEmailPasswordNotMatchingWarningMassage()
	{
		String warningText=emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	
	
	
	
	
	
}

