package practice;

import org.testng.annotations.Test;

public class RunTime_Practice 
{
@Test
public void runTimePrac()
{
	String a = System.getProperty("a");
	String b = System.getProperty("b");
	
	int sum = Integer.parseInt(a) + Integer.parseInt(b);
	System.out.println(sum);
}
}
