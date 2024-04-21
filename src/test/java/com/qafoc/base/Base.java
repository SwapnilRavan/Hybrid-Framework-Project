package com.qafoc.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	   public Properties prop;
	    WebDriver driver; 
	    public void loadPropertiesFile() throws IOException
	    {
	    	prop= new Properties();
	    	File propFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qafoc\\config\\config.properties");
	    	FileInputStream fis=new FileInputStream (propFile);
	    	prop.load(fis);
	    }
	    
	public WebDriver initializeBrowserOpenApplication(String browserName) {
		//String browserName = "Chrome"; // <<as pr requrement we can use browser can change string name

		if (browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}

		if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		// WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));// implicite wait apply once applicble for all
																			// element

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		return driver;
	}

}
