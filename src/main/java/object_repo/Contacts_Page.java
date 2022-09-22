package object_repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Util;

public class Contacts_Page extends Util {
	WebDriver driver;

	// ----------------------------------Locators-------------------------------//
	@FindBy(xpath = "//img[@title='Create Contact...']")
	WebElement createContactsIcon;

	// Find Duplicates:
	@FindBy(xpath = "//img[@src='themes/images/findduplicates.gif']")
	WebElement findDuplicateBtn;
	@FindBy(xpath = "//select[@id = 'availList']")
	WebElement selectAvailableFields;
	@FindBy(xpath = "//input[@name = 'Button']")
	WebElement selectArrow;
	@FindBy(xpath = "//input[@name = 'Button1']")
	WebElement deselectArrow;
	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement findDuplicateCmd;

	// Advanced Search:
	@FindBy(xpath = "//a[contains(text(),'Go to Advanced Search')]")
	WebElement goToAdvancedSearchBtn;
	@FindBy(xpath = "//select[@class='detailedViewTextBox']")
	WebElement searchCriteriaDropDown;
	@FindBy(xpath = "//select[@class='repBox']")
	WebElement conditionDropDown;
	@FindBy(xpath = "//input[@class='repBox']")
	WebElement searchInputField;
	@FindBy(xpath = "//body[1]/table[4]/tbody[1]/tr[1]/td[2]"
			+ "/div[2]/form[1]/table[3]/tbody[1]/tr[1]/td[1]/input[1]")
	WebElement searchNowBtn;
	@FindBy(xpath = "//input[@title='Clear']")
	WebElement erasor;
	@FindBy(xpath = "//span[contains(text(),'No Contact Found !')]")
	WebElement noContactsFound;

	// ------------------------------------Constructor----------------------------//
	public Contacts_Page(WebDriver d) {
		this.driver = d;
		PageFactory.initElements(d, this);
	}

	// -------------------------------------Operations-------------------------//

	public void clickCreateContactsIcon() {
		createContactsIcon.click();
	}

	// Find Duplicates:
	public void clickFindDuplicateBtn() {
		findDuplicateBtn.click();
	}

	public void clickSelectArrow() {
		selectArrow.click();
	}

	public void clickDeSelectArrow() {
		deselectArrow.click();
	}

	public void clickFindDuplicateCmd() {
		findDuplicateCmd.click();
	}

	public void selector(String... fieldsToSelect) {
		Select s = new Select(driver.findElement(By.xpath("//select[@id='availList']")));
		for (int i = 0; i < fieldsToSelect.length; i++) {
			s.selectByVisibleText(fieldsToSelect[i]);
		}
	}

	// Advanced Search:
	public void clickGoToAdvancedSearchBtn() {
		goToAdvancedSearchBtn.click();
	}

	public void selectCriteria(String text) {
		Util.selectDropDown(searchCriteriaDropDown, text);
	}

	public void selectCondition(String value) {
		Util.selectDropDown(value, conditionDropDown);
	}

	public void setSearchField(CharSequence text) {
		searchInputField.sendKeys(text);
	}

	public void clickSearchNowBtn() {
		searchNowBtn.click();
	}

	public String advancedSearchResults() {

		/*
		 * System.out.println(driver.findElement
		 * (By.xpath("//body[1]/table[4]/tbody[1]/tr[1]/td[2]/div[4]" +
		 * "/form[1]/table[1]/tbody[1]/tr[1]/td[1]" +
		 * "/table[1]/tbody[1]/tr[1]/td[1]")).getText());
		 */

		return driver
				.findElement(By.xpath("//body[1]/table[4]/tbody[1]/tr[1]/td[2]/div[4]"
						+ "/form[1]/table[1]/tbody[1]/tr[1]/td[1]" + "/table[1]/tbody[1]/tr[1]/td[1]"))
				.getText().substring(25);

	}

	public void clickErasor() {
		erasor.click();
	}

	public WebElement getNoContactsFound() {
		return noContactsFound;
	}

	// --------------------------------------------------------------------------//
}
