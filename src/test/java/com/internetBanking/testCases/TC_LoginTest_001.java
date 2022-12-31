package com.internetBanking.testCases;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		if(lp.iframeIsVisible()) {
			logger.info("GDPR popup displayed");
			lp.switchToFrame();
			lp.clickAccept();
			lp.switchToDefault();
		}
		
		lp.setUserName(username);
		logger.info("Username entered");
		
		lp.setPassword(password);
		logger.info("Password entered");
		lp.clickSubmit();
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Test completed");
		}
		else {
			Assert.assertTrue(false);
			logger.info("Login test failed");
			captureScreen(driver, "loginTest");
		}


		
	}

}
