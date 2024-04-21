package com.qafoc.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qafoc.base.Base;
import com.qafoc.pages.HomePage;
import com.qafoc.pages.LoginPage;
import com.qafoc.utils.Utilities;

public class LoginTest extends Base { // inheritance using extend login uses base metod

	public WebDriver driver; // globally declared instead localy

	@BeforeMethod
	public void setup() throws IOException // repeated common code i.e login-before each method for every test case
	{
		loadPropertiesFile(); //call mehod from base class to load here properties
		
		//ceate home page constructor to override method bcoz below are home page fn
		driver = initializeBrowserOpenApplication(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver); //obj=homepage we can call method from homepage directly                        
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		
		/*driver = initializeBrowserOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();*/

	}

	@AfterMethod // quit code which run after each method
	public void TearDown() {
		driver.quit();
	}

	@Test(priority= 1)
	
	public void VerifyLoginWithValidCredential() {
		System.out.println("Test Case 1");
        //login page page factory.  create obj for loginpage class
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAdress("sachintestselenium1591@gmail.com");
		loginpage.enterPassword("12345");
		loginpage.clickOnLoginButton();
		
		//OR INSTEAD 3 STEPS USE COMBINED METHOD ALL STEPS INCLUUDE
		//loginpage.loginAllSteps("sachintestselenium1591@gmail.com", "12345");
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed());
		
		/*driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sachintestselenium1591@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed());*/

	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredential() throws InterruptedException {
		System.out.println("Test Case 2");
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAdress(Utilities.generateEmailwithTipeStamp());
		loginpage.enterPassword(prop.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualText=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("invalidPasswordNoMatchWarning"));
		
		/*driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailwithTipeStamp());
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(3000);
		String actualText=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("invalidPasswordNoMatchWarning"));//config prop*/

	}

	@Test(priority = 3)
	public void VerifyLoginWithInvalidUsername() {
		System.out.println("Test Case 3");

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("invaliduserid@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("invalidpw12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualText=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("invalidPasswordNoMatchWarning")); //config
	}

	@Test(priority = 4)
	public void VerifyLoginWithInavlidPassword() {
		System.out.println("Test Case 4");

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("invaliduserid@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click;

		//String actualText=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//Assert.assertEquals(actualText, prop.getProperty("invalidPasswordNoMatchWarning"));

	}

	// for method 2 write in mail utilities common
	/*
	 * public String generateEmailwithTipeStamp() { Date date=new Date(); String
	 * timestamp =date.toString().replace(" ","_").replace(":", "_").replace("-",
	 * "_"); return "swapni"+timestamp+"@gmail.com"; }
	 */

}
