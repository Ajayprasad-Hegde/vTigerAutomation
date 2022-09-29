package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import base_Class.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.WebDriverDec;

public class HandleGeoLocationAndNotification
{
	@Test
	public void check() throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments(" --disable-geolocation ");
		options.addArguments("--ignore-certificate-errors");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@placeholder='Pick a date'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("24")).click();
	}
}
