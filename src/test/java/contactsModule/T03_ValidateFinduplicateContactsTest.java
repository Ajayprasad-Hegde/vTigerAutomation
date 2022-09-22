// This Test case is depends on T02
// Using String... in a method argument
// Using @Before @Afrter annotations to run the TCs properly
// Keeping Browser launching actions in @BeforeTest and @AfterTest
// Writing dynamic xpath. And xapth to get exact text


package contactsModule;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base_Class.Base;
import object_repo.Contacts_Page;
import object_repo.Home_Page;
import object_repo.Login_Page;
import utility.ReadConfig;

public class T03_ValidateFinduplicateContactsTest extends Base
{
	
	ReadConfig rc;
	Login_Page login;
	Home_Page home;
	Contacts_Page contacts;

	@Test(groups = "RegressionTest")
	public void validateDuplicatesTest() throws IOException
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
		
		int expectedSize = getDriver().findElements(By.xpath("//div/table/tbody/tr/td[3]/a[text()='Kumar']")).size();
		
		contacts.clickFindDuplicateBtn();
		contacts.selector("First Name");
		contacts.clickSelectArrow();
		contacts.clickFindDuplicateCmd();
		
		int actSize =  getDriver().findElements(By.xpath("//tbody/tr[@class = 'sep1']")).size();
		
		Assert.assertEquals(actSize, expectedSize);
		
	}
	
	
}
