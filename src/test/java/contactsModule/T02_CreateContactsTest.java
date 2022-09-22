//DataFormatter

package contactsModule;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base_Class.Base;
import object_repo.Contacts_Page;
import object_repo.CreateContacts_Page;
import object_repo.Home_Page;
import object_repo.Login_Page;
import utility.ReadConfig;
import utility.Util;

public class T02_CreateContactsTest extends Base
{
	ReadConfig rc;
	Login_Page login;
	Home_Page home;
	Contacts_Page contacts;
	CreateContacts_Page crtCont;
	 
	@DataProvider()
	public Iterator<String[]> dataFeader() throws IOException
	{
		return utility.XLUtils.getCellData("C:\\Ajay\\Java_Folder\\Java_Eclips"
				+ "\\vTiger\\Test_Data\\Validate_Dulicates.xlsx", 0).iterator();
	}
	
    @BeforeClass(groups = "RegressionTest")
	public void setUpForCreateContacts() throws IOException
					
	{
		rc = new ReadConfig();
		login = new Login_Page(getDriver());
		home= new Home_Page(getDriver());
		contacts = new Contacts_Page(getDriver());
		crtCont = new CreateContacts_Page(getDriver());
		
		getDriver().get(rc.getUrl());
		login.setUserName(rc.getAdminUserName());
		login.setPassword(rc.getAdminPassword());
		login.clickLoginBtn();
		
		home.clickContactsLink();
		contacts.clickCreateContactsIcon();
		
	}
    
    @Test(dataProvider ="dataFeader",groups = "RegressionTest")
    public void CreateContacts(String firstName, String lastName,
			String leadSource, String bYear, String bMonth, String bDate) throws InterruptedException
    {
    	crtCont.setFirstName(firstName);
		crtCont.setLastName(lastName);
		crtCont.selectLeadSource(leadSource);
		crtCont.setEmail(firstName+lastName+ Util.generateRandomString(2)+"@gmail.com");
		crtCont.setBirthdate(bYear, bMonth, bDate);
		
		crtCont.clickSaveBtn();
		home.clickContactsLink();
		contacts.clickCreateContactsIcon();
    }
}
