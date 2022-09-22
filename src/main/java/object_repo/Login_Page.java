package object_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page 
{

	WebDriver driver;
	
//----------------------------------Locators-------------------------------//
	@FindBy(xpath = "//input[@name='user_name']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@name='user_password']")
	WebElement userPassword;
	
	@FindBy(xpath = "//input[@id=\"submitButton\"]")
	WebElement loginBtn;

//------------------------------------Constructor----------------------------//
	public Login_Page(WebDriver d)
	{
		this.driver = d;
		PageFactory.initElements(d, this);
	}
	
//-------------------------------------Operations-------------------------//
	
	public void setUserName(String un)
	{
		userName.sendKeys(un);
	}
	public void setPassword(CharSequence pw)
	{
		userPassword.sendKeys(pw);
	}
	public void clickLoginBtn()
	{
		loginBtn.click();
	}
//--------------------------------------------------------------------------//
}
