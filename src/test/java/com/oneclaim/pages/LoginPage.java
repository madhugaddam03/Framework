package com.oneclaim.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(name="username") WebElement uname;
	
	@FindBy(name="password") WebElement pwd;
	
	@FindBy(xpath="//input[@value='Login']") WebElement loginbutton;
	
	public void loginToCRM(String usernameapplication, String passwordapplication)
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) 
		{
			
		}
		uname.sendKeys(usernameapplication);
		pwd.sendKeys(passwordapplication);
		
		loginbutton.click();
	}
	
}
