package contactsModule;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import base_Class.Base;
import object_repo.Home_Page;
import object_repo.Login_Page;
import utility.ReadConfig;

public class T01_LoginTest extends Base
{
	ReadConfig rc;
	Login_Page login;
	Home_Page home;
	
	@Test(groups = {"SmokeTest","RegressionTest"})
	public void loginTest() throws IOException 
	{
		rc = new ReadConfig();
		login = new Login_Page(getDriver());
		home= new Home_Page(getDriver());
		
		getDriver().get(rc.getUrl());
		login.setUserName(rc.getAdminUserName());
		login.setPassword(rc.getAdminPassword());
		login.clickLoginBtn();
		
		Assert.assertEquals(getDriver().getTitle(),rc.getHomePageTitle());
		home.performLogout();
	}
}
