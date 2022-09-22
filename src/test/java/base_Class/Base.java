package base_Class;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utility.WebDriverDec;

public class Base extends WebDriverDec
{	
	
	
	@BeforeTest(groups = {"RegressionTest","SmokeTest"})
	//@BeforeClass(groups = {"RegressionTest","SmokeTest"})
	@Parameters({"browser"})
	public void launchBrowser(String browser) 
	{ 
	 utility.Util.launchBrowser(browser);
	}
	
	@AfterTest(groups = {"RegressionTest","SmokeTest"})
	//@AfterClass(groups = {"RegressionTest","SmokeTest"})
	public void quitBrowser() 
	{
		getDriver().quit();
	}
	
}
