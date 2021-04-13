package org.base;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testing {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://blazedemo.com/reserve.php");
		WebElement listFlights = driver.findElement(By.tagName("tbody"));
		List<WebElement> listRows = listFlights.findElements(By.tagName("tr"));
		for (int i = 0; i < listRows.size(); i++) {
			WebElement flightData = listRows.get(i);
			List<WebElement> listData = flightData.findElements(By.tagName("td"));
			WebElement element = listData.get(5);
			String attribute = element.getText();
			System.out.println(attribute);
			float in = Float.valueOf(attribute.trim());
			System.out.println(in);
		}
	}
}
