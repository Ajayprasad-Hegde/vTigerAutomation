package object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Util;

public class Home_Page extends Util
{
	WebDriver driver;
	
	//----------------------------------Locators-------------------------------//
	
	@FindBy(xpath = "//tbody/tr[1]/td[6]/a[1]")
	WebElement organizationsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	WebElement userBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	WebElement logoutBtn;
	//------------------------------------Constructor----------------------------//
		public Home_Page(WebDriver d)
		{
			this.driver = d;
			PageFactory.initElements(d, this);
		}
		
	//-------------------------------------Operations-------------------------//
		
		public void clickOrganizationsLink()
		{
			organizationsLink.click();
		}
		
		public void clickContactsLink()
		{
			contactsLink.click();
		}
		
		public void performLogout()
		{
			Util.mouseOver(userBtn);
			logoutBtn.click();
		}
		//-------------------------------------------------------------------//			
}
