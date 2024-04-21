package com.qafoc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//page object for crate all pages of application with seperate class and page factory for write locator and method
	
	WebDriver driver;
	
	//Common objects locator for home
	@FindBy(xpath="//span[text()='My Account']") 
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[text()='Login']") 
	private WebElement loginOption;
	
	@FindBy(xpath="//a[text()='Register']") 
	private WebElement registerOption;

	//constructor-for connect this to upper driver
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public void selectLoginOption()
	{
		loginOption.click();
	}
	
	public void selectRegisterOption()
	{
		registerOption.click();
	}
	
}
