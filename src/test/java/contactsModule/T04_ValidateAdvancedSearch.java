// This Test case is depends on T02
// Two Test scripts: One for Positive and one for Negative. Both are fed by a data provider each.
// This is to validate Advanced search feature with First Name and contains criteria 

package contactsModule;

import java.io.IOException;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base_Class.Base;
import object_repo.Contacts_Page;
import object_repo.Home_Page;
import object_repo.Login_Page;
import utility.ReadConfig;
import utility.XLUtils;

public class T04_ValidateAdvancedSearch extends Base
{
	ReadConfig rc;
	Login_Page login;
	Home_Page home;
	Contacts_Page contacts;
	
    @BeforeClass
	public void advancedSearchSetup() throws IOException
	{
    	rc = new ReadConfig();
		login = new Login_Page(getDriver());
		home= new Home_Page(getDriver());
		contacts = new Contacts_Page(getDriver());
		
		getDriver().get(rc.getUrl());
		login.setUserName(rc.getAdminUserName());
		login.setPassword(rc.getAdminPassword());
		login.clickLoginBtn();
		
		home.clickContactsLink();	
		contacts.clickGoToAdvancedSearchBtn();
		contacts.selectCriteria("First Name");
    	contacts.selectCondition("c");
	}
    
    @DataProvider
    public Iterator<String[]> dataFeederPositive() throws IOException
    {
    	 return XLUtils.getCellData("C:\\Ajay\\Java_Folder\\Java_Eclips\\vTiger"
    			+ "\\Test_Data\\validateAdvancedSearch.xlsx", 0).iterator();
    }
    
    @Test(dataProvider="dataFeederPositive",priority = 2, enabled= false)
    public void advancedSearchPositive(String firstName, String expextedValue) throws InterruptedException
    {
    	contacts.setSearchField(firstName);
    	contacts.clickSearchNowBtn();
    	System.out.println(firstName);
    	Thread.sleep(3000);
    	Assert.assertEquals(expextedValue, contacts.advancedSearchResults());
    	Thread.sleep(3000);
    	contacts.clickErasor();
    }
    
    @DataProvider
    public Iterator<String[]> dataFeederNegetive() throws IOException
    {
    	 return XLUtils.getCellData("C:\\Ajay\\Java_Folder\\Java_Eclips\\vTiger"
    			+ "\\Test_Data\\validateAdvancedSearch.xlsx", 1).iterator();
    }
    
    @Test(dataProvider="dataFeederNegetive",priority = 1)
    public void advancedSearchNegetive(String firstName) throws InterruptedException
    {
    	contacts.setSearchField(firstName);
    	contacts.clickSearchNowBtn();
    	System.out.println(firstName);
    	
    	Thread.sleep(3000);
    	try {
    	Assert.assertTrue(contacts.getNoContactsFound().isDisplayed());
    	}
    	catch (org.openqa.selenium.NoSuchElementException e)
    	{
    		Assert.fail();
    	}
    	
    	Thread.sleep(3000);
    	contacts.clickErasor();
    }
}
