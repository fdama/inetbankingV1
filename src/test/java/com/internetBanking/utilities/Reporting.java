//Listener C;ass used to generate Extent Reports
package com.internetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@Override
	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report"+timestamp+".html";
		
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); //Title of Project
		htmlReporter.config().setReportName("Functional Test Report");
	
	
				
				
		
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenSshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f = new File(screenSshotPath);
		logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenSshotPath));
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
	
	
	

	

}
