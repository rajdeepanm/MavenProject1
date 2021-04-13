package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import io.github.bonigarcia.wdm.WebDriverManager;

public class LibGlobal {
	static WebDriver driver;
	public static WebDriver launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = (WebDriver) new ChromeDriver();
		return driver;
	}
	public static void passUrl(String url) {
		driver.get(url);
	}
	public static void maximize() {
		driver.manage().window().maximize();
	}
	public static void fullScreen() {
		driver.manage().window().fullscreen();
	}
	public static void screenShot(String name) throws IOException {
		TakesScreenshot sS = (TakesScreenshot) driver;
		File source = sS.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\target\\"+name+".png");
		FileUtils.copyFile(source, dest);
	}
	public static void passText(WebElement element,String text) {
		element.sendKeys(text);
	}
	public static WebElement locbyID(String value) {
		WebElement element = driver.findElement(By.id(value));
		return element;
	}
	public static WebElement locbyName(String text) {
		WebElement element = driver.findElement(By.name(text));
		return element;
	}
	public static WebElement locbyXpath(String text) {
		WebElement element = driver.findElement(By.xpath(text));
		return element;
	}
	public static String currentUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}
	public static String pickTitle() {
		String title = driver.getTitle();
		return title;
	}
	public static void mouseOver(WebElement target) {
		new Actions(driver).moveToElement(target).perform();
	}
	public static void rightClick(WebElement target) {
		new Actions(driver).contextClick(target).perform();
	}
	public static void doubleTap() {
		new Actions(driver).doubleClick().perform();
	}
	public static void passTextByJs(String text , WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+text+"')", element);
	}
	public static String returnJsValue(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script =(String) js.executeScript("return arguments[0].getAttribute('value')", element);
		return script;
	}
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	public static void scrollDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	public static void scrollUp(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].ScrollIntoView()", element);
	}
	public static void stay() {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	public static void selectbyIndex(WebElement element, int index) {
		new Select(element).selectByIndex(index);
	}
	public static void selectbyValue(WebElement element , String value) {
		new Select(element).selectByValue(value);
	}
	public static void selectbyText(WebElement element , String text) {
		new Select(element).selectByVisibleText(text);
	}
	public static String getValuefromExcel(String pathname,String sn,int index,int ci) throws IOException {
		File f = new File(pathname);
		FileInputStream stream = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet(sn);
		Row r = s.getRow(index);
		Cell c = r.getCell(ci);
		int type = c.getCellType();
		String value = "";
		if(type==1) {
			value=c.getStringCellValue();
		}else if (DateUtil.isCellDateFormatted(c)) {
			value=new SimpleDateFormat("dd-MM-yyyy").format(c.getDateCellValue());
		}else {
			value = String.valueOf((long)c.getNumericCellValue());
		}
		return value;
		
		
		
	}
		

	
}