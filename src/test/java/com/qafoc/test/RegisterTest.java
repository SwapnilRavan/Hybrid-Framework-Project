package com.qafoc.test;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qafoc.base.Base;
import com.qafoc.utils.Utilities;

@Test
public class RegisterTest extends Base {  //inheritance using extend  login uses base metod 

	public WebDriver driver;

	@BeforeMethod
	public void setup() throws IOException {
		
		loadPropertiesFile(); //call mehod from base class to load here properties
		
		driver = initializeBrowserOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();

	}

	@AfterMethod // quit code which run after each method
	public void TearDown() {
		driver.quit();
	}

	public void verifyRegisterAccountWithMandatoryField() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("swapnil");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("ravan");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailwithTipeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9309509994");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);
		String Actualresult = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")).getText();

		Assert.assertEquals(Actualresult, prop.getProperty("accountSucssesfulyCreatedHeading"));//config prop
	}

	public void verifyRegisterAccountWithaDuplicateSameEmail() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("swapnil");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("ravan");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("swapnilravan2342@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9309509994");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);

		String Actualresult = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();

		Assert.assertEquals(Actualresult, "Warning: E-Mail Address is already registered!",
				"Text is not match with acual");
	}

	// for method 1 write in main common
	/*
	 * public String generateEmailwithTipeStamp() { Date date=new Date(); String
	 * timestamp =date.toString().replace(" ","_").replace(":", "_").replace("-",
	 * "_"); return "swapni"+timestamp+"@gmail.com"; }
	 */

}
