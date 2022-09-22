package object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations_page 
{
	WebDriver driver;
	
	//----------------------------------Locators-------------------------------//
		@FindBy(xpath = "//img[@title='Create Organization...']")
		WebElement createOrgIcon;
		
	//------------------------------------Constructor----------------------------//
		public Organizations_page(WebDriver d)
		{
			this.driver = d;
			PageFactory.initElements(d, this);
		}
		
	//-------------------------------------Operations-------------------------//
		
		public void clickCreateOrgIcon()
		{
			createOrgIcon.click();
		}

		
		
	//--------------------------------------------------------------------------//
}
