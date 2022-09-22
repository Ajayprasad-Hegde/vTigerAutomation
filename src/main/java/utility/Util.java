package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Util extends WebDriverDec
{
	
										
	public static void launchBrowser(String browser)    					// Launching browser
	{

		if (browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			threadLocalDriver.set(driver);
			
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();
			threadLocalDriver.set(driver);
		}
		else if (browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			WebDriver driver = new EdgeDriver();
			threadLocalDriver.set(driver);
		}
			
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
	}
	
	
	public static String dateTime() 											     // Date and time for folder name
	{
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("DDMMYYYYHHmmSS");
		String d = df.format(dateTime);
		return d;
	}
	
	static String takeScrenshot(String fileName) 						// Taking screen shot and returning the path
	{
		TakesScreenshot ts = (TakesScreenshot)getDriver();
		File sf = ts.getScreenshotAs(OutputType.FILE);
		File df = new File ("C:\\Ajay\\Java_Folder\\Java_Eclips"
				+ "\\vTiger\\FailedTest_ScreenShots\\"+fileName+dateTime()+".jpg");
	
		try {
			FileUtils.copyFile(sf, df);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return df.getAbsolutePath();
	}
	
	public static void scrollIntoView(WebDriver driver,WebElement element)     	// scroll to view
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public static void waitMethod(WebDriver driver,WebElement element) 		    //Explicit wait (until element is clickable)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void mouseOver(WebElement element)
	{
		Actions act = new Actions(getDriver());
		act.moveToElement(element).build().perform();
	}
	
	public static void uploadFile(String path) throws AWTException				  // upload a file
	{
		StringSelection ss = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public static boolean imageCompare(String expectedImagepath, WebElement ele) throws IOException    // Image compare
	{
		File file = new File(expectedImagepath);
		BufferedImage expectedImage = ImageIO.read(file);
		
		AShot ashot = new AShot();
		BufferedImage actualImage = ashot.takeScreenshot(getDriver(), ele).getImage();
		
		ImageDiffer imageDiffer = new ImageDiffer();
		ImageDiff imageDiff = imageDiffer.makeDiff(expectedImage, actualImage);
		
		if(imageDiff.hasDiff())
			return true;
		else
			return false;
	}
	
	
	public static void selectDropDown(WebElement element, String text)   // Drop down select by Visible text
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public static void selectDropDown(WebElement element, int index)     // Drop down select by index
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	public static void selectDropDown(String value,WebElement element)    // Drop down select by value
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	
	public static String generateRandomString(int count)                  // Random String
	{
		return RandomStringUtils.randomAlphabetic(count);
	}
	
	
}	

