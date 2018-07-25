package pages.loadpay.admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminSearchPage extends TestBase {

	WebDriverWait wait;

	@FindBy(xpath = "//a[@href='#/Search']")
	WebElement searchPageLink;

	@FindBy(xpath = "//*[@id='searchKeyword']")
	WebElement searchInputField;

	@FindBy(xpath = "//input[@value='Search']")
	WebElement searchButton;

	@FindBy(xpath = "//*[@id='AmountRangeFrom']")
	WebElement amountFromInputField;

	@FindBy(xpath = "//*[@id='AmountRangeTo']")
	WebElement amountToInputField;

	@FindBy(xpath = "//*[@id='StartDate']")
	WebElement startDateInputField;

	@FindBy(xpath = "//*[@id='EndDate']")
	WebElement endDateInputField;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.All']")
	WebElement allCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.Orphan']")
	WebElement unmatchedCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.NotScheduled']")
	WebElement notScheduledCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.Scheduled']")
	WebElement scheduledCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.Issued']")
	WebElement paidCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.Error']")
	WebElement errorCheckBox;

	@FindBy(xpath = "//input[@ng-model='SearchCriteria.Statuses.Canceled']")
	WebElement canceledCheckBox;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div"))
	WebElement firstRowData;

	@FindBy(xpath = ("//span[contains(text(),'Total Count')]"))
	WebElement totalRecordCount;

	// constructor
	public AdminSearchPage() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	/**
	 * click the searchPageLink
	 */
	public void clickSearchPageLink() {
		wait.until(ExpectedConditions.elementToBeClickable(searchPageLink));
		searchPageLink.click();
	}

	/**
	 * click the searchPageLink
	 */
	public void clickSearchButton() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
	}

	/**
	 * @param searchInputField
	 *            the searchInputField to set
	 */
	public void setSearchInputField(String searchText) {
		wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
		searchInputField.clear();
		searchInputField.sendKeys(searchText);
		searchInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param amountFromInputField
	 *            the amountFromInputField to set
	 */
	public void setAmountFromInputField(String amount) {
		wait.until(ExpectedConditions.elementToBeClickable(amountFromInputField));
		amountFromInputField.clear();
		amountFromInputField.sendKeys(amount);
		amountFromInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param amountToInputField
	 *            the amountToInputField to set
	 */
	public void setAmountToInputField(String amount) {
		wait.until(ExpectedConditions.elementToBeClickable(amountToInputField));
		amountToInputField.clear();
		amountToInputField.sendKeys(amount);
		amountToInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param startDateInputField
	 *            the startDateInputField to set
	 * @throws InterruptedException
	 */
	public void setStartDateInputField(String startDate) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(startDateInputField));
		startDateInputField.clear();
		startDateInputField.sendKeys(startDate);
		wait.until(ExpectedConditions.elementToBeClickable(startDateInputField));
		startDateInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @param endDateInputField
	 *            the endDateInputField to set
	 * @throws InterruptedException
	 */
	public void setEndDateInputField(String endDate) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(endDateInputField));
		endDateInputField.clear();
		endDateInputField.sendKeys(endDate);
		wait.until(ExpectedConditions.elementToBeClickable(endDateInputField));
		endDateInputField.sendKeys(Keys.TAB);
	}

	/**
	 * @return the allCheckBox selection status
	 */
	public boolean isAllFilterChecked() {
		return allCheckBox.isSelected();
	}

	/**
	 * @param allCheckBox
	 *            the allCheckBox to set
	 */
	public void clickAllCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(allCheckBox));
		allCheckBox.click();
	}

	/**
	 * @return the unmatchedCheckBox
	 */
	public boolean isUnmatchedChecked() {
		return unmatchedCheckBox.isSelected();
	}

	/**
	 * @param unmatchedCheckBox
	 *            the unmatchedCheckBox to set
	 */
	public void clickUnmatchedCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(unmatchedCheckBox));
		unmatchedCheckBox.click();
	}

	/**
	 * @return the notScheduledCheckBox
	 */
	public boolean isNotScheduledChecked() {
		return notScheduledCheckBox.isSelected();
	}

	/**
	 * @param notScheduledCheckBox
	 *            the notScheduledCheckBox to set
	 */
	public void clickNotScheduledCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(notScheduledCheckBox));
		notScheduledCheckBox.click();
	}

	/**
	 * @return the scheduledCheckBox
	 */
	public boolean isScheduledChecked() {
		return scheduledCheckBox.isSelected();
	}

	/**
	 * @param scheduledCheckBox
	 *            the scheduledCheckBox to set
	 */
	public void clickScheduledCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(scheduledCheckBox));
		scheduledCheckBox.click();
	}

	/**
	 * @return the paidCheckBox
	 */
	public boolean isPaidChecked() {
		return paidCheckBox.isSelected();
	}

	/**
	 * @param paidCheckBox
	 *            the paidCheckBox to set
	 */
	public void clickPaidCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(paidCheckBox));
		paidCheckBox.click();
	}

	/**
	 * @return the errorCheckBox
	 */
	public boolean isErrorChecked() {
		return errorCheckBox.isSelected();
	}

	/**
	 * @param errorCheckBox
	 *            the errorCheckBox to set
	 */
	public void clickErrorCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(errorCheckBox));
		errorCheckBox.click();
	}

	/**
	 * @return the canceledCheckBox
	 */
	public boolean isCanceledChecked() {
		return canceledCheckBox.isSelected();
	}

	/**
	 * @param canceledCheckBox
	 *            the canceledCheckBox to set
	 */
	public void clickCanceledCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(canceledCheckBox));
		canceledCheckBox.click();
	}

	/**
	 * @return the totalRecordCount
	 */
	public Integer getTotalRecordCount() {

		String strCount = totalRecordCount.getText();
		log.info("Raw string of Record Count Element: " + strCount);

		strCount = strCount.substring(12, strCount.length());
		log.info("Substring of Record Count Element: " + strCount);

		Integer intRecordCount = Integer.parseInt(strCount);

		return intRecordCount;
	}

	public Boolean verifyFirstRowData(String searchText) {

		log.info("\n ===================== Search Text: " + searchText + "=====================");

		if (firstRowData.getText().contains(searchText)) {
			log.info("Search Text: [" + searchText + "] found within [" + firstRowData.getText() + "]");
			return true;
		} else {
			log.info("Search Text: [" + searchText + "] NOT found within [" + firstRowData.getText() + "]");
			return false;
		}
	}

	public Boolean verifyFirstRowDataWithinRange(String minRange, String maxRange, String elementType) {

		WebElement webElementType;
		log.info("\n\nFirst Row Data: [" + firstRowData.getText() + "]\n\n");

		if (elementType.equals("Invoice Amount")) {
			webElementType = driver.findElement(By.xpath(
					".//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a/div/div[3]/div/b"));

			log.info("Element Text: " + webElementType.getText());

			Double invoiceAmt = Double.parseDouble(webElementType.getText());
			Double minAmt = Double.parseDouble(minRange);
			Double maxAmt = Double.parseDouble(maxRange);

			log.info("Invoice Amt: " + invoiceAmt);
			log.info("Min Amt: " + minAmt);
			log.info("Max Amt: " + maxAmt);

			if (invoiceAmt >= minAmt && invoiceAmt <= maxAmt)
				return true;
			else
				return false;

		} else if (elementType.equals("Term Date")) {
			webElementType = driver.findElement(By.xpath(
					".//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a/div/div[2]/div/b"));

			String foundDateStr = webElementType.getText();
			log.info("Element Text: " + foundDateStr);

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

			log.info("Found Month: " + foundMonth);
			log.info("Found Day: " + foundDay);
			log.info("Found Year: " + foundYear);

			log.info("Min Month: " + minMonth);
			log.info("Min Day: " + minDay);
			log.info("Min Year: " + minYear);

			log.info("Max Month: " + maxMonth);
			log.info("Max Day: " + maxDay);
			log.info("Max Year: " + maxYear);

			// test month range
			if (foundMonth >= minMonth && foundMonth <= maxMonth) {
				log.info("Month filter within range");
				verificationCount++;
			} else {
				log.info("Month filter NOT within range");
			}

			// test day range
			if (foundDay >= minDay && foundDay <= maxDay) {
				log.info("Day filter within range");
				verificationCount++;
			} else {
				log.info("Day filter NOT within range");
			}

			// test year range
			if (foundYear >= minYear && foundYear <= maxYear) {
				log.info("Year filter within range");
				verificationCount++;
			} else {
				log.info("Year filter NOT within range");
			}

			if (verificationCount == 3)
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	public void resetFields() {
		driver.navigate().refresh();
	}
}