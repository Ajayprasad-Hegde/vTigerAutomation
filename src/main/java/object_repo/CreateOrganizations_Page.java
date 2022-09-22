package object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Util;

public class CreateOrganizations_Page extends utility.Util
{
WebDriver driver;
	
	//----------------------------------Locators-------------------------------//
		@FindBy(xpath = "//input[@name='accountname']")
		WebElement organizationNameField;
		
		@FindBy(xpath = "//select[@name='industry']")
		WebElement industryDropDown;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		WebElement saveBtn;
		
	//------------------------------------Constructor----------------------------//
		public CreateOrganizations_Page(WebDriver d)
		{
			this.driver = d;
			PageFactory.initElements(d, this);
		}
		
	//-------------------------------------Operations-------------------------//
		
		public void setorganizationName(String name)
		{
			organizationNameField.sendKeys(name);
		}
		
		public void selectIndustry(String ind)
		{
			Util.selectDropDown(ind, industryDropDown);
		}
		public void clickSaveBtn()
		{
			saveBtn.click();
		}
		
	//--------------------------------------------------------------------------//
}
