package pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminSearchPage extends TestBase
{

	WebDriverWait wait;
	
	@FindBy(xpath="//a[@href='#/Search']")
	WebElement searchPageLink;
	
	@FindBy(xpath="//*[@id='searchKeyword']")
	WebElement searchInputField;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement searchButton;
	
	@FindBy(xpath="//*[@id='AmountRangeFrom']")
	WebElement amountFromInputField;
	
	@FindBy(xpath="//*[@id='AmountRangeTo']")
	WebElement amountToInputField;
	
	@FindBy(xpath="//*[@id='StartDate']")
	WebElement startDateInputField;
	
	@FindBy(xpath="//*[@id='EndDate']")
	WebElement endDateInputField;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.All']")
	WebElement allCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.Orphan']")
	WebElement unmatchedCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.NotScheduled']")
	WebElement notScheduledCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.Scheduled']")
	WebElement scheduledCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.Issued']")
	WebElement paidCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.Error']")
	WebElement errorCheckBox;
	
	@FindBy(xpath="//input[@ng-model='SearchCriteria.Statuses.Canceled']")
	WebElement canceledCheckBox;
	
	public AdminSearchPage() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}
	

	/**
	 * click the searchPageLink
	 */
	public void clickSearchPageLink() {
		searchPageLink.click();
	}
	
	/**
	 * click the searchPageLink
	 */
	public void clickSearchButton() {
		searchButton.click();
	}


	/**
	 * @param searchInputField the searchInputField to set
	 */
	public void setSearchInputField(String searchText) {
		searchInputField.clear();
		searchInputField.sendKeys(searchText);
	}

	/**
	 * @param amountFromInputField the amountFromInputField to set
	 */
	public void setAmountFromInputField(String amount) {
		amountFromInputField.clear();
		amountFromInputField.sendKeys(amount);
	}

	/**
	 * @param amountToInputField the amountToInputField to set
	 */
	public void setAmountToInputField(String amount) {
		amountToInputField.clear();
		amountToInputField.sendKeys(amount);
	}

	/**
	 * @param startDateInputField the startDateInputField to set
	 */
	public void setStartDateInputField(String startDate) {
		startDateInputField.clear();
		startDateInputField.sendKeys(startDate);
	}

	/**
	 * @param endDateInputField the endDateInputField to set
	 */
	public void setEndDateInputField(String endDate) {
		endDateInputField.clear();
		endDateInputField.sendKeys(endDate);
	}


	/**
	 * @return the allCheckBox selection status
	 */
	public boolean isAllFilterChecked() {
		return allCheckBox.isSelected();
	}


	/**
	 * @param allCheckBox the allCheckBox to set
	 */
	public void clickAllCheckBox() {
		allCheckBox.click();
	}


	/**
	 * @return the unmatchedCheckBox
	 */
	public boolean isUnmatchedChecked() {
		return unmatchedCheckBox.isSelected();
	}


	/**
	 * @param unmatchedCheckBox the unmatchedCheckBox to set
	 */
	public void clickUnmatchedCheckBox() {
		unmatchedCheckBox.click();
	}


	/**
	 * @return the notScheduledCheckBox
	 */
	public boolean isNotScheduledChecked() {
		return notScheduledCheckBox.isSelected();
	}


	/**
	 * @param notScheduledCheckBox the notScheduledCheckBox to set
	 */
	public void clickNotScheduledCheckBox() {
		notScheduledCheckBox.click();
	}


	/**
	 * @return the scheduledCheckBox
	 */
	public boolean isScheduledChecked() {
		return scheduledCheckBox.isSelected();
	}


	/**
	 * @param scheduledCheckBox the scheduledCheckBox to set
	 */
	public void clickScheduledCheckBox() {
		scheduledCheckBox.click();
	}


	/**
	 * @return the paidCheckBox
	 */
	public boolean isPaidChecked() {
		return paidCheckBox.isSelected();
	}


	/**
	 * @param paidCheckBox the paidCheckBox to set
	 */
	public void clickPaidCheckBox() {
		paidCheckBox.click();
	}


	/**
	 * @return the errorCheckBox
	 */
	public boolean isErrorChecked() {
		return errorCheckBox.isSelected();
	}


	/**
	 * @param errorCheckBox the errorCheckBox to set
	 */
	public void clickErrorCheckBox() {
		errorCheckBox.click();
	}


	/**
	 * @return the canceledCheckBox
	 */
	public boolean isCanceledChecked() {
		return canceledCheckBox.isSelected();
	}


	/**
	 * @param canceledCheckBox the canceledCheckBox to set
	 */
	public void clickCanceledCheckBox() {
		canceledCheckBox.click();
	}
}