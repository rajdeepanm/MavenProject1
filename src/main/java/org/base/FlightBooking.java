package org.base;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightBooking {
	WebDriver driver;
	ExtentReports reports;
	ExtentHtmlReporter htmlReports;
	ExtentTest test;
	TakesScreenshot sS;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		reports = new ExtentReports();
		htmlReports = new ExtentHtmlReporter("bookingreports.html");
		reports.attachReporter(htmlReports);
		driver = new ChromeDriver();
		driver.get("https://blazedemo.com/");
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
		reports.flush();
	}

	@Test(priority = 1)
	public void launchTravelPage() {
		test = reports.createTest("Verifying TravelPage by Booking Tickets");
		test.log(Status.INFO, "verifying Url");
		if (driver.getCurrentUrl().contains("blazedemo")) {
			test.log(Status.PASS, "Valid Url");

		} else {
			test.log(Status.FAIL, "InvalidUrl");
		}
	}

	@Test(priority = 2)
	public void selectSrcndDest() throws IOException {
		test = reports.createTest("ChecKing FromPort DropDown");
		test.log(Status.INFO, "Verifying dropdown FromPort is enabled and Displayed");
		WebElement dDnFromPort = driver.findElement(By.name("fromPort"));
		if (dDnFromPort.isDisplayed() && dDnFromPort.isEnabled()) {
			test.log(Status.PASS, "DropDown is Enabled");
			Select s = new Select(dDnFromPort);
			s.selectByIndex(3);
			File file = dDnFromPort.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("fromport.png"));
			test.addScreenCaptureFromPath("fromport.png");
		}
	}

	@Test(priority = 3)
	public void selectDest() throws IOException {
		test = reports.createTest("Checking ToPort DropDown");
		test.log(Status.INFO, "Verifying dropdown ToPort is enabled and Displayed");
		WebElement dDnToPort = driver.findElement(By.name("toPort"));
		if (dDnToPort.isDisplayed() && dDnToPort.isEnabled()) {
			test.log(Status.PASS, "DropDown is Enabled");
			Select s = new Select(dDnToPort);
			s.selectByIndex(2);
			File file = dDnToPort.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("toport.png"));
			test.addScreenCaptureFromPath("toport.png");
		}
	}

	@Test(priority = 4)
	public void buttonFind() throws IOException {
		test = reports.createTest("Checking FindFlights Button");
		test.log(Status.INFO, "Verifying FindFlights button is enabled and Displayed");
		WebElement btnFindFlights = driver.findElement(By.xpath("//input[@type='submit']"));
		if (btnFindFlights.isDisplayed() && btnFindFlights.isEnabled()) {
			test.log(Status.PASS, "btnFindFlights is Enabled");
			btnFindFlights.click();

		}
	}

	@Test(priority = 5)
	public void secondPage() {
		test = reports.createTest("Verifying it goes to second page");
		test.log(Status.INFO, "Verifying second page url");
		if (driver.getCurrentUrl().contains("reserve")) {
			test.log(Status.PASS, "Entered Successfully");
		} else {
			test.log(Status.FAIL, " UnSuccessful Attempt");
		}
	}

	@Test(priority = 6)
	public void chooseFlight() {
		test = reports.createTest("Choosing least Cost Flights");
		test.log(Status.INFO, "Verifying Table is enabled and Displayed");
		WebElement chsTable = driver.findElement(By.xpath("//table[@class='table']"));
		if (chsTable.isDisplayed() && chsTable.isEnabled()) {
			test.log(Status.PASS, "Enabled");
			WebElement listFlights = driver.findElement(By.tagName("tbody"));
			List<WebElement> listRows = listFlights.findElements(By.tagName("tr"));
			for (int i = 0; i < listRows.size(); i++) {
				WebElement flightData = listRows.get(i);
				List<WebElement> listData = flightData.findElements(By.tagName("td"));
				String attribute = listData.get(5).getAttribute("value");
				Set<String> s1 = new TreeSet<String>();
				s1.add(attribute);
				System.out.println(s1);
			}
		}
	}

	@Test(priority = 7)
	public void thirdPage() {
		test = reports.createTest("Verifying it goes to third page");
		test.log(Status.INFO, "Verifying third page url");
		if (driver.getCurrentUrl().contains("purchase")) {
			test.log(Status.PASS, "Entered Successfully");
		} else {
			test.log(Status.FAIL, " UnSuccessful Attempt");
		}
	}

	@Test(priority = 8)
	public void valuePassing() throws IOException {
		test = reports.createTest("Declaring Customer details");
		test.log(Status.INFO, "Passing Invalid Datas");
		test.log(Status.INFO, "verifying Name Textbox is enabled and displayed");
		WebElement txtName = driver.findElement(By.id("inputName"));
		if (txtName.isDisplayed() && txtName.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtName.sendKeys("Gowtham Murugan");
		}
		test.log(Status.INFO, "verifying address Textbox is enabled and displayed");
		WebElement txtAddress = driver.findElement(By.id("address"));
		if (txtAddress.isDisplayed() && txtAddress.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtAddress.sendKeys("Omalur");
		}
		test.log(Status.INFO, "verifying city Textbox is enabled and displayed");
		WebElement txtCity = driver.findElement(By.id("city"));
		if (txtCity.isDisplayed() && txtCity.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtCity.sendKeys("Salem");
		}
		test.log(Status.INFO, "verifying state Textbox is enabled and displayed");
		WebElement txtState = driver.findElement(By.id("state"));
		if (txtState.isDisplayed() && txtState.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtState.sendKeys("Tamilnadu");
		}
		test.log(Status.INFO, "verifying pincode Textbox is enabled and displayed");
		WebElement txtPincode = driver.findElement(By.id("zipCode"));
		if (txtPincode.isDisplayed() && txtPincode.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtPincode.sendKeys("636002");
		}
		test.log(Status.INFO, "verifying Dropdown is enabled and displayed");
		WebElement dDnCardType = driver.findElement(By.id("cardType"));
		if (dDnCardType.isDisplayed() && dDnCardType.isEnabled()) {
			test.log(Status.PASS, "dropdown is enabled and Displayed");
			Select s = new Select(dDnCardType);
			s.selectByIndex(0);
			File file = dDnCardType.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("ddncradtype.png"));
			test.addScreenCaptureFromPath("ddncradtype.png");
		}
		test.log(Status.INFO, "verifying creditcard Textbox is enabled and displayed");
		WebElement txtCC = driver.findElement(By.id("creditCardNumber"));
		if (txtCC.isDisplayed() && txtCC.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtCC.sendKeys("9876543212347890");
		}
		test.log(Status.INFO, "verifying Creditcard Month Textbox is enabled and displayed");
		WebElement txtCcMon = driver.findElement(By.id("creditCardMonth"));
		if (txtCcMon.isDisplayed() && txtCcMon.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtCcMon.sendKeys("12");
		}
		test.log(Status.INFO, "verifying CreditCard year Textbox is enabled and displayed");
		WebElement txtCcYear = driver.findElement(By.id("creditCardYear"));
		if (txtCcYear.isDisplayed() && txtCcYear.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtCcYear.sendKeys("2023");
		}
		test.log(Status.INFO, "verifying state Textbox is enabled and displayed");
		WebElement txtCcName = driver.findElement(By.id("nameOnCard"));
		if (txtCcName.isDisplayed() && txtCcName.isEnabled()) {
			test.log(Status.PASS, "textbox enabled and Displayed");
			txtCcName.sendKeys("GOWTHAM");
		}
		test.log(Status.INFO, "verifying Purchase button is enabled and displayed");
		WebElement btnPurchase = driver.findElement(By.xpath("//input[@value='Purchase Flight']"));
		if (btnPurchase.isDisplayed() && btnPurchase.isEnabled()) {
			test.log(Status.PASS, "button enabled and Displayed");
			sS = (TakesScreenshot) driver;
			File source = sS.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("thirdPage.png"));
			test.addScreenCaptureFromPath("thirdPage.png");
			btnPurchase.click();
		}
	}

	@Test(priority = 9)
	public void fourtPage() {
		test = reports.createTest("Verifying it goes to Fourth page");
		test.log(Status.INFO, "Verifying Fourth page url");
		if (driver.getCurrentUrl().contains("confirmation")) {
			test.log(Status.PASS, "Entered Successfully");
		} else {
			test.log(Status.FAIL, " UnSuccessful Attempt");
		}
	}

	@Test(priority = 10)
	public void bookingDetails() throws IOException {
		test = reports.createTest("Receiveing Booking details");
		test.log(Status.INFO, "Order id");
		sS = (TakesScreenshot) driver;
		File src = sS.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("fourthPage.png"));
		test.addScreenCaptureFromPath("fourthPage.png");

	}

}
