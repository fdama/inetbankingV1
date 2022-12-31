package com.internetBanking.testCases;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.internetBanking.pageObjects.AddCustomerPage;
import com.internetBanking.pageObjects.LoginPage;

@Test
public class TC_AddCustomerTest_003 extends BaseClass {

	public void addNewCustomer() throws InterruptedException, IOException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		if (lp.iframeIsExists()) {
			if (lp.iframeIsVisible()) {
				logger.info("GDPR popup displayed");
				System.out.println("GDPR popup displayed");
				lp.switchToFrame();
				lp.clickAccept();
				lp.switchToDefault();
			}
		}

		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		Thread.sleep(3000);

		AddCustomerPage cp = new AddCustomerPage(driver);
		cp.clickAddNewCustomer();

		//Close annoying ad
		Thread.sleep(5000);
		WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		driver.switchTo().frame(frame1);
		WebElement frame2 = driver.findElement(By.id("ad_iframe"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		cp.custName("Faisal");
		cp.custGender("m");
		cp.custDob("13", "12", "1981");
		Thread.sleep(3000);
		cp.custAddress("UK");
		cp.custCity("Manchester");
		cp.custState("North West");
		cp.custPin("123456");
		cp.custTelno("1234567890");
		cp.custEmail(randomString() + "@gmail.com");
		cp.custPwd("abcdef");
		cp.submit();

		Thread.sleep(3000);

		boolean txt = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (txt == true) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}

	public String randomString() {
		String randStr = RandomStringUtils.randomAlphabetic(8);
		return randStr;
	}

	public String randomNumber() {
		String randNum = RandomStringUtils.randomNumeric(4);
		return randNum;
	}

}
