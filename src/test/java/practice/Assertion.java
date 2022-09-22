package practice;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion 
{
	@Test(retryAnalyzer= utility.RetryAnalyzer.class)
	public void assertion_test()
	{
		SoftAssert softassert = new SoftAssert();
		String a = "abc"; 								// fail
		softassert.assertEquals(a, "abcd");
		
		String b = "pqr";								// pass
		softassert.assertEquals(b, "pqr");
		
		String c = "xyz";								// fail
		softassert.assertEquals(c, "xy");
		
		String d = "abcde";								// pass
		softassert.assertEquals(d, "abcde");
		
		softassert.assertAll();
	}
}
