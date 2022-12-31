package com.internetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.Homepage;
import com.internetBanking.pageObjects.LoginPage;
import com.internetBanking.utilities.XLUtils;

public class TC_LoginDDT_002  extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String uname, String pwd) throws InterruptedException {
		driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		Homepage hp = new Homepage(driver);
		
		if(lp.iframeIsExists()) {
			if(lp.iframeIsVisible()) {
				logger.info("GDPR popup displayed");
				System.out.println("GDPR popup displayed");
				lp.switchToFrame();
				lp.clickAccept();
				lp.switchToDefault();
			}
		}
		
		lp.setUserName(uname);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()) { //if login fail alert is displayed
			driver.switchTo().alert().accept(); //close alert
			driver.switchTo().defaultContent(); //focus on the main page
			Assert.assertTrue(false); //fail test
			logger.warn("Login failed");
		}
		else{
			Assert.assertTrue(true);
			logger.info("Login succeeded");
			hp.clickLogout();
			
			Thread.sleep(3000);
			
			driver.switchTo().alert().accept(); //accept 'logout completed' alert
			driver.switchTo().defaultContent();//focus back on main screen
			
			
		}
		
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")    //Retrieves data and stores in 2D array and made available to test case. 
	public String[][] getData() throws IOException {
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\internetBanking\\testData\\login.xls";
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rowNum][colCount];
		
		for(int row=1; row <= rowNum; row++) {
			for(int col=0; col < colCount; col++) {
				loginData[row-1][col] = XLUtils.getCellData(path, "Sheet1", row, col);
			}
		}
		
	return loginData; 	
	}
	

}
