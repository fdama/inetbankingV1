package com.internetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.internetBanking.pageObjects.LoginPage;
import com.internetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	ReadConfig rc = new ReadConfig();
	public String baseURL = rc.getApplicationURL();
	public String username = rc.getUsername();
	public String password = rc.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	public LoginPage lp;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		//driver.manage().window().maximize();
		logger = LogManager.getLogger("ebanking");
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
		
		
	}
	
	public void cookieAccept() {
		if(lp.iframeIsExists()) {
			if(lp.iframeIsVisible()) {
				logger.info("GDPR popup displayed");
				System.out.println("GDPR popup displayed");
				lp.switchToFrame();
				lp.clickAccept();
				lp.switchToDefault();
			}
		}
	}
	
	
	

}
