package pages.LoadPay.Admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div"))
	WebElement firstRowData;
	
	//@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[22]/div[1]/h3/span"))
	@FindBy(xpath = ("//span[contains(text(),'Total Count')]"))
	WebElement totalRecordCount;

	//constructor
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
		searchInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param amountFromInputField the amountFromInputField to set
	 */
	public void setAmountFromInputField(String amount) {
		amountFromInputField.clear();
		amountFromInputField.sendKeys(amount);
		amountFromInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param amountToInputField the amountToInputField to set
	 */
	public void setAmountToInputField(String amount) {
		amountToInputField.clear();
		amountToInputField.sendKeys(amount);
		amountToInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param startDateInputField the startDateInputField to set
	 * @throws InterruptedException 
	 */
	public void setStartDateInputField(String startDate) throws InterruptedException {
		startDateInputField.clear();
		startDateInputField.sendKeys(startDate);
		Thread.sleep(1000);
		startDateInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param endDateInputField the endDateInputField to set
	 * @throws InterruptedException 
	 */
	public void setEndDateInputField(String endDate) throws InterruptedException {
		endDateInputField.clear();
		endDateInputField.sendKeys(endDate);
		Thread.sleep(1000);
		endDateInputField.sendKeys(Keys.TAB);
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
	
	/**
	 * @return the totalRecordCount
	 */
	public Integer getTotalRecordCount() {
		
		String strCount = totalRecordCount.getText();
		System.out.println("Raw string of Record Count Element: " + strCount);
		
		strCount = strCount.substring(12, strCount.length());
		System.out.println("Substring of Record Count Element: " + strCount);
		
		Integer intRecordCount = Integer.parseInt(strCount);
		
		return intRecordCount;
	}
	
	public Boolean verifyFirstRowData(String searchText) {
		
		System.out.println("\n ===================== Search Text: " + searchText + "=====================");
		
		if(firstRowData.getText().contains(searchText))
		{
			System.out.println("Search Text: [" + searchText + "] found within [" + firstRowData.getText() + "]");
			return true;
		}
		else
		{
			System.out.println("Search Text: [" + searchText + "] NOT found within [" + firstRowData.getText() + "]");
			return false;
		}
	}
	
	public Boolean verifyFirstRowDataWithinRange(String minRange, String maxRange, String elementType) {
		
		WebElement webElementType;
		System.out.println("\n\nFirst Row Data: [" + firstRowData.getText() + "]\n\n");
		
		if(elementType.equals("Invoice Amount"))
		{
			webElementType = driver.findElement(By.xpath(".//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a/div/div[3]/div/b"));
			
			System.out.println("Element Text: " + webElementType.getText());
			
			Double invoiceAmt = Double.parseDouble(webElementType.getText());
			Double minAmt = Double.parseDouble(minRange);
			Double maxAmt = Double.parseDouble(maxRange);
			
			System.out.println("Invoice Amt: " + invoiceAmt);
			System.out.println("Min Amt: " + minAmt);
			System.out.println("Max Amt: " + maxAmt);
			
			if(invoiceAmt >= minAmt && invoiceAmt <= maxAmt)
				return true;
			else
				return false;
			
		}
		else if(elementType.equals("Term Date"))
		{
			webElementType = driver.findElement(By.xpath(".//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a/div/div[2]/div/b"));
			
			String foundDateStr = webElementType.getText();
			System.out.println("Element Text: " + foundDateStr);
			
			String[] minDateElements = minRange.split("/");
			String[] maxDateElements = maxRange.split("/");
			String[] foundDateElements = foundDateStr.split("/");
			
			Integer foundMonth = Integer.parseInt(foundDateElements[0]);
			Integer foundDay = Integer.parseInt(foundDateElements[1]);
			Integer foundYear = Integer.parseInt(foundDateElements[2]);
			
			Integer minMonth = Integer.parseInt(minDateElements[0]);
			Integer minDay = Integer.parseInt(minDateElements[1]);
			Integer minYear = Integer.parseInt(minDateElements[2]);
			
			Integer maxMonth = Integer.parseInt(maxDateElements[0]);
			Integer maxDay = Integer.parseInt(maxDateElements[1]);
			Integer maxYear = Integer.parseInt(maxDateElements[2]);
			
			Integer verificationCount = 0;
			
			System.out.println("Found Month: " + foundMonth);
			System.out.println("Found Day: " + foundDay);
			System.out.println("Found Year: " + foundYear);
			
			System.out.println("Min Month: " + minMonth);
			System.out.println("Min Day: " + minDay);
			System.out.println("Min Year: " + minYear);
			
			System.out.println("Max Month: " + maxMonth);
			System.out.println("Max Day: " + maxDay);
			System.out.println("Max Year: " + maxYear);
			
			//test month range
			if(foundMonth >= minMonth && foundMonth <= maxMonth)
			{
				System.out.println("Month filter within range");
				verificationCount++;
			}
			else
			{
				System.out.println("Month filter NOT within range");
			}
			
			//test day range
			if(foundDay >= minDay && foundDay <= maxDay)
			{
				System.out.println("Day filter within range");
				verificationCount++;
			}
			else
			{
				System.out.println("Day filter NOT within range");
			}
			
			//test year range
			if(foundYear >= minYear && foundYear <= maxYear)
			{
				System.out.println("Year filter within range");
				verificationCount++;
			}
			else
			{
				System.out.println("Year filter NOT within range");
			}
			
			if(verificationCount == 3)
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
	}
	
	public void resetFields() {
		driver.navigate().refresh();
	}
}