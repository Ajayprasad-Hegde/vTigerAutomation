// Creating an Organization. And creating a contact with org info
// Date picker, Switching to a window that popped up
// staleElementReferenceException (JS resolution)

package organizationModule;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import base_Class.Base;
import object_repo.Contacts_Page;
import object_repo.CreateContacts_Page;
import object_repo.CreateOrganizations_Page;
import object_repo.Home_Page;
import object_repo.Login_Page;
import object_repo.Organizations_page;
import utility.ReadConfig;

public class T01_CreateContactWithOrgTest extends Base
{
	@Test (groups = "RegressionTest")
	public void createOrgAndContacts() throws IOException, InterruptedException
	{
		ReadConfig rc = new ReadConfig();
		Login_Page login = new Login_Page(getDriver());
		Home_Page home= new Home_Page(getDriver());
		Organizations_page org = new Organizations_page(getDriver());
		CreateOrganizations_Page crtOrg = new CreateOrganizations_Page(getDriver());
		Contacts_Page contacts = new Contacts_Page(getDriver());
		CreateContacts_Page crtCont = new CreateContacts_Page(getDriver());
		
		getDriver().get(rc.getUrl());
		login.setUserName(rc.getAdminUserName());
		login.setPassword(rc.getAdminPassword());
		login.clickLoginBtn();
		
		home.clickOrganizationsLink();
		org.clickCreateOrgIcon();
		
		crtOrg.setorganizationName("TCS");
		crtOrg.selectIndustry("Technology");
		crtOrg.clickSaveBtn();
		 
		Thread.sleep(5000);
		
		JavascriptExecutor js =  (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()",getDriver().findElement(By.xpath("//tbody/tr[1]/td[8]/a[1]")));
		
		
		contacts.clickCreateContactsIcon();
		
		crtCont.setFirstName("Kumar");
		crtCont.setLastName("Manu");
		crtCont.selectLeadSource("Employee");
		crtCont.setBirthdate("2001", "November", "11");
		
		String parentWindow = getDriver().getWindowHandle();    // Window Handling
		crtCont.clickSelectOrgIcon();
		
		Set<String> winHandles = getDriver().getWindowHandles();
		for(String winHandle : winHandles)
		{
			if(!winHandle.equals(parentWindow))
			{
				getDriver().switchTo().window(winHandle);
			}
		}
			
		crtCont.setSearchField("TCS");
		crtCont.clickSearchNowBtn();
		crtCont.clickSearchedOrg("TCS");
		getDriver().switchTo().window(parentWindow);
		crtCont.clickSaveBtn();
	}
}