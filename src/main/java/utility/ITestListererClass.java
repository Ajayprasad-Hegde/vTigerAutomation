package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ITestListererClass implements ITestListener
{
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	
	public void onStart(ITestContext context) {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("C:\\Ajay\\Java_Folder\\Java_Eclips"
				+ "\\vTiger\\Reports\\Test_Report_"+Util.dateTime()+".html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host", "Local");
	
		spark.config().setDocumentTitle("Test Report");
		spark.config().setReportName("HMS_PROJECT");
		spark.config().setTheme(Theme.DARK);
	}
	
	public void onTestFailure(ITestResult result) 
	{
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  String path = Util.takeScrenshot(result.getMethod().getMethodName());
		  test = extent.createTest(result.getMethod().getMethodName());
		  test.log(Status.FAIL, "Test Failed: "+ result.getMethod().getMethodName()+"  "+result.getThrowable());
		  test.addScreenCaptureFromPath(path);
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.PASS, "Passed");
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.SKIP, "Skipped");
	}

	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
