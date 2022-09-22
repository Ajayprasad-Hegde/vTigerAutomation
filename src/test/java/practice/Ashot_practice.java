package practice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Ashot_practice 
{
	WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name = \"user_name\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name = \"user_password\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	}
	@Test
	public void ahotprac() throws IOException
	{
		File file = new File("C:\\Users\\admin\\Desktop\\sel adv\\New folder\\vtiger-crm-logo.gif");
		BufferedImage expectedImage = ImageIO.read(file);
		
		AShot ashot = new AShot();
		WebElement imgelement = driver.findElement(By.xpath("//img[@alt=\"vtiger-crm-logo.gif\"]"));
		Screenshot screenshot = ashot.takeScreenshot(driver, imgelement);
		BufferedImage actualImage = screenshot.getImage();
		
		ImageDiffer imagediffer  = new ImageDiffer();
		ImageDiff imagediff = imagediffer.makeDiff(expectedImage, actualImage);
		
		if(imagediff.hasDiff())
		{
			System.out.println("Different Image");
		}
		else
		{
			System.out.println("Same Image");
		}
		
	}
	
}
