package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderPractice 
{
	
	public static ArrayList<String[]> data () throws IOException
	{
		FileInputStream fip = new FileInputStream("C:\\Users\\admin\\Desktop\\sel adv\\New folder\\1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fip);
		XSSFSheet sheet = workbook.getSheetAt(0); 
		ArrayList<String []> arrlst = new ArrayList<String []>();
		for(int i = 1; i<=sheet.getLastRowNum();i++)
		{
			XSSFRow row = sheet.getRow(i);
			String firstName = row.getCell(0).toString();
			String lastName = row.getCell(1).toString();
			String department = row.getCell(2).toString();
			String[] arr = {firstName,lastName,department};
			arrlst.add(arr);
		}
		return arrlst;
		
	}
	
	@DataProvider
	public Iterator<String[]> provider() throws IOException
	{
		Iterator<String[]> iterator = data().iterator();
		return iterator;
		
	}
	
	/*
	 * public static void main(String[] args) throws IOException {
	 * ArrayList<String[]> a = data(); String[] b = a.get(1);
	 * System.out.println(b[0] + "   " +b[1]+ "  "+b[2]); }
	 */
	
	
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
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
	}
	/*@AfterTest
	public void tearDown()
	{
		driver.quit();
	}*/
	
	
	@Test (dataProvider ="provider")
	public void dataProvider(String firstName, String lastName, String department)
	{
	
		driver.findElement(By.xpath("//img[@title =\"Create Contact...\"]")).click();
		driver.findElement(By.xpath("//input[@name = \"firstname\"]")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name = \"lastname\"]")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name = \"department\"]")).sendKeys(department);
		driver.findElement(By.xpath("//tbody/tr[29]/td[1]/div[1]/input[1]")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[8]/a[1]")).click();
	}
	
}

