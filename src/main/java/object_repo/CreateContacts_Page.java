package object_repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Util;

public class CreateContacts_Page 
{

WebDriver driver;
	
	//----------------------------------Locators-------------------------------//
		@FindBy(xpath = "//input[@name='firstname']")
		WebElement firstNameField;
		
		@FindBy(xpath = "//input[@name='lastname']")
		WebElement lastNameField;
		
		@FindBy(xpath = "//select[@name='leadsource']")
		WebElement leadSourceDropDown;
		

		@FindBy(xpath = "//input[@id='email']")
		WebElement emailField;
		
		@FindBy(xpath = "//input[@title='Save [Alt+S]']")
		WebElement saveBtn;
		
		@FindBy(xpath = "//tbody/tr[5]/td[2]/img[1]")
		WebElement selectOrgIcon;
		
		@FindBy(xpath = "//input[@id='search_txt']")
		WebElement searchOrgField;
		
		@FindBy(xpath = "//input[@name = 'search']")
		WebElement searchNowBtn;
		

		@FindBy(xpath = "//img[@id='jscal_trigger_birthday']")
		WebElement birthDayCalander;
		
		
		
		
	//------------------------------------Constructor----------------------------//
		public CreateContacts_Page(WebDriver d)
		{
			this.driver = d;
			PageFactory.initElements(d, this);
		}
		
		
		
	//-------------------------------------Operations-------------------------//
		
		public void setFirstName(String fn)
		{
			firstNameField.sendKeys(fn);
		}
		
		public void setLastName(String ln)
		{
			lastNameField.sendKeys(ln);
		}
		
		public void setEmail(String email)
		{
			emailField.sendKeys(email);
		}
		
		public void selectLeadSource(String ls)
		{
			Util.selectDropDown(ls, leadSourceDropDown);
		}
		public void clickSaveBtn()
		{
			saveBtn.click();
		}
		
		public void clickSelectOrgIcon()
		{
			selectOrgIcon.click();
		}
		
		public void clickSearchNowBtn()
		{
			searchNowBtn.click();
		}
		
		public void clickSearchedOrg(String org)
		{
			driver.findElement(By.xpath("//a[contains(text(),'"+org+"')]")).click();
		}
		
		public void setSearchField(String orgname)
		{
			searchOrgField.sendKeys(orgname);
		}
		
		
		public void setBirthdate(String year, String month , String date) throws InterruptedException // Date Picker
		{
			birthDayCalander.click();
			
			
			while(true)
			{
				String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
				if((monthAndYear[1]).equals(" "+year))
				{
					break;
				}
				driver.findElement(By.xpath("//thead/tr[2]/td[1]")).click();
			}
			while(true)
			{
				String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
				if(monthAndYear[0].equals(month))
				{
					break;
				}
				driver.findElement(By.xpath("//thead/tr[2]/td[2]")).click();
			}
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]")).click();
			
			
		}
		
		/*
		public void setBirthdate(String year, String month , String date) throws InterruptedException // Date Picker
		{
			birthDayCalander.click();
			
			if(LocalDate.now().isAfter(LocalDate.of(Integer.parseInt(year), Month.valueOf(month), Integer.parseInt(date))))
			{
				while(true)
				{
					String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
					if((monthAndYear[1]).equals(" "+year))
					{
						break;
					}
					driver.findElement(By.xpath("//td[contains(text(),'«')]")).click();
				}
				while(true)
				{
					String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
					if(monthAndYear[0].equalsIgnoreCase(month))
					{
						break;
					}
					driver.findElement(By.xpath("//td[contains(text(),'‹')]")).click();
				}
				
				Thread.sleep(3000);
				driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]")).click();	
			}
			else
			{
				while(true)
				{
					String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
					if((monthAndYear[1]).equals(" "+year))
					{
						break;
					}
					driver.findElement(By.xpath("//td[contains(text(),'»')]")).click();
				}
				while(true)
				{
					String[] monthAndYear = driver.findElement(By.xpath("//thead/tr[1]/td[2]")).getText().split(",");
					if(monthAndYear[0].equalsIgnoreCase(month))
					{
						break;
					}
					driver.findElement(By.xpath("//td[contains(text(),'›')]")).click();
				}
				
				Thread.sleep(3000);
				driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]")).click();	
			}
			
			
			
		}
		*/
		
	//--------------------------------------------------------------------------//
}
