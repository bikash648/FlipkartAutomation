package com.flipkart.testcases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.flipkart.Pages.LoginPage;
import com.flipkart.data.ExcelFileUtility;
import com.flipkart.utility.BrowserFactory;

public class VerifyFlipkartLogin {

	private WebDriver driver = null;
	static ExtentTest test;
	static ExtentReports report;
	static String flipkartLoginURL;
	
	@BeforeClass
	public static void startTest() throws IOException
	{
	report = new ExtentReports(System.getProperty("user.dir")+"\\reports\\ExtentReportResults.html", true);
	test = report.startTest("VerifyFlipkartLogin");
	FileReader reader=new FileReader("TestData/ProjectDetails.properties");  
    Properties p=new Properties();  
    p.load(reader);  
    flipkartLoginURL=p.getProperty("flipkartLoginURL");
	}

	@Test(dataProvider = "loginCredentials")
	public void checkValidUser(String userName, String pwd) throws InterruptedException {
		driver = BrowserFactory.setup("chrome",flipkartLoginURL);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.logInToFlipkart(userName, pwd);
		Thread.sleep(5000);
		String title = driver.getTitle();
		if(title.equals("My Profile"))
		{
			test.log(LogStatus.PASS, "User successfully logged-in");
		}
		else
		{
			test.log(LogStatus.FAIL, "Invalid credentials");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "loginCredentials")
	public Object[][] getTestInputs() {
		ExcelFileUtility excel = new ExcelFileUtility("TestData/InputData.xlsx");
		int rowCount = excel.getRowCount(0);
		Object[][] testCases = new Object[rowCount][2];
		for (int i = 0; i < rowCount; i++) {
			testCases[i][0] = excel.getData(0, i, 0);
			testCases[i][1] = excel.getData(0, i, 1);
		}
		return testCases;
	}
	
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}
}
