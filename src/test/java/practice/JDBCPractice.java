package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JDBCPractice 
{
	
	
	@Test
	public void database() throws SQLException, InterruptedException
	{
		Driver d = new Driver();
		Connection con = null;
			
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.screener.in/screens/178/growth-stocks/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		 
		DriverManager.registerDriver(d); 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientdb", "root","root"); 
		
		for(int i =2;i<16;i++)
		{
			String st = (driver.findElement(By.xpath("//tbody/tr["+i+"]/td[1]")).getText());
			int s = Integer.parseInt((st.substring(0, st.length()-1)));
			String n = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText();
			String c = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).getText();
			
			Statement statement = con.createStatement();
			statement.executeUpdate("insert into screener(slno,name,cmp) values('"+s+"','"+n+"','"+c+"')");
			
		}
		con.close();
	}
}
