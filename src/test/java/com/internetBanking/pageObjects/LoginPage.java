package com.internetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserame;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(id = "save")
	@CacheLookup
	WebElement btnAcceptCookies;
	
	@FindBy(id = "gdpr-consent-notice")
	@CacheLookup
	WebElement iframe; 
	
	public void setUserName(String uname) {
		txtUserame.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}
	
	public void clickAccept() {
		btnAcceptCookies.click();
	}
	
	public boolean cookiePopupVisible() {
		if (btnAcceptCookies.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public boolean iframeIsExists() {
		if(driver.findElements(By.id("gdpr-consent-notice")).isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean iframeIsVisible() {
		if (iframe.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public void switchToFrame() {
		driver.switchTo().frame(iframe);
	}
	
	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	
	

}


