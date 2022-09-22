package utility;

import org.openqa.selenium.WebDriver;

public class WebDriverDec 
{
	static 
    ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver()
	{
		return threadLocalDriver.get();
	}
}


