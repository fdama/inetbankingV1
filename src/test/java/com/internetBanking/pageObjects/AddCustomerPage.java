package com.internetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='New Customer']")
	@CacheLookup
	WebElement lnkAddNewCustomer;
	
	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}
	
	
	@FindBy(how = How.NAME, using = "name")
	@CacheLookup
	WebElement txtCustomerName;
	
	public void custName(String custName) {
		txtCustomerName.sendKeys(custName);
	}
	
	
	@FindBy(how = How.NAME, using = "rad1")
	@CacheLookup
	WebElement rdGender;
	
	public void custGender(String gender) {
		rdGender.click();
	}
	
	
	@FindBy(how = How.ID_OR_NAME, using = "dob")
	@CacheLookup
	WebElement dateDob;
	
	public void custDob(String mm, String dd, String yyyy) {
		dateDob.sendKeys(dd);
		dateDob.sendKeys(mm);
		dateDob.sendKeys(yyyy);
	}
	
	
	@FindBy(how = How.NAME, using = "addr")
	@CacheLookup
	WebElement txtAddress;
	
	public void custAddress(String address) {
		txtAddress.sendKeys(address);
	}
	
	
	@FindBy(how = How.NAME, using = "city")
	@CacheLookup
	WebElement txtCity;
	
	public void custCity(String city) {
		txtCity.sendKeys(city);
	}
	
	
	@FindBy(how = How.NAME, using = "state")
	@CacheLookup
	WebElement txtState;
	
	public void custState(String state) {
		txtState.sendKeys(state);
	}
		
	
	@FindBy(how = How.NAME, using = "pinno")
	@CacheLookup
	WebElement txtPin;
	
	public void custPin(String pin) {
		txtPin.sendKeys(pin);
	}
	
	@FindBy(how = How.NAME, using = "telephoneno")
	@CacheLookup
	WebElement txtTelephone;
	
	public void custTelno(String telno) {
		txtTelephone.sendKeys(telno);
	}
	
	
	@FindBy(how = How.NAME, using = "emailid")
	@CacheLookup
	WebElement txtemail;
	
	public void custEmail(String email) {
		txtemail.sendKeys(email);
	}
	
	
	@FindBy(how = How.NAME, using = "password")
	@CacheLookup
	WebElement txtPassword;
	
	public void custPwd(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	
	@FindBy(how = How.NAME, using = "sub")
	@CacheLookup
	WebElement btnSubmit;
	
	public void submit() {
		btnSubmit.click();
	}
	
	
	

}
