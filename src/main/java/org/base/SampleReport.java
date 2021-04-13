package org.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleReport  {
	WebDriver driver;
	ExtentReports reports;
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() {
		reports = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("fbpagereport.html");
		reports.attachReporter(htmlReporter);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		reports.flush();
	}

	@Test
	public void openWebPage() {
		 test = reports.createTest("launching Fb Application");
		test.log(Status.INFO, "verifying Url");
		if(driver.getCurrentUrl().contains("facebook")) {
			test.log(Status.INFO, "valid url");
		}else {
			test.log(Status.INFO, "invalid url");
		}
	}
	@Test
	public void loginPage() throws IOException {
		test = reports.createTest("Entering invalid values in LoginPage");
		test.log(Status.INFO, "verify UserName text box is enabled");
		WebElement txtUserName = driver.findElement(By.id("email"));
		if(txtUserName.isDisplayed()&&txtUserName.isEnabled()) {
			test.log(Status.PASS, "UserName field is enabled");
			txtUserName.sendKeys("hijavaa");
			File file = txtUserName.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("fbpage.png"));
			test.addScreenCaptureFromPath("fbpage.png");
		}
	}
}
