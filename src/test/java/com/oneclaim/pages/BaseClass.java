package com.oneclaim.pages;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.oneclaim.utility.BrowserFactory;
import com.oneclaim.utility.ConfigDataProvider;
import com.oneclaim.utility.ExcelDataProvider;
import com.oneclaim.utility.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite()
	{
		Reporter.log("Setting up Report and Test is getting ready", true);

		excel=new ExcelDataProvider();
        config=new ConfigDataProvider();
        ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
        
        report=new ExtentReports();
        report.attachReporter(extent);

		Reporter.log("Setting done and Test can be Started", true);

	}
	
	@BeforeClass
	public void setUp() 
	{
		Reporter.log("Trying to Start Browser and application is getting ready", true);

		driver=BrowserFactory.startApplication(driver,config.getBrowser(),config.getStagingUrl());

		Reporter.log("Browser and application up and running", true);

	}
	
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Reporter.log("Test about to end", true);

			System.out.println("Not Capturing screenshot");
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			System.out.println("Capturing screenshot");
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
		Reporter.log("Test completed and Reports Generated", true);

	}


}
