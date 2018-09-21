package pages.loadpay.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminPayByCheck extends TestBase {

	WebDriverWait wait = null;
	String paymentIdText;

	// Page Factory - OR:
	@FindBy(id = "EIN")
	WebElement field_ein;

	@FindBy(id = "ControlAmount")
	WebElement field_loadpaydepositeamt;

	@FindBy(xpath = ".//a[contains(text(),'Payments')]")
	public WebElement link_Payments;

	@FindBy(xpath = "//a[@href='#/Search']")
	WebElement link_Search;

	@FindBy(id = "AcceptedTerms")
	WebElement checkboxaccept;

	@FindBy(id = "EmailTerms")
	private WebElement checkboxemail;

	@FindBy(xpath = "//input[@id='searchKeyword']")
	private WebElement FieldSearch;

	@FindBy(xpath = "//input[contains(@id,'searchKeyword')]")
	private WebElement searchKeyword;

	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btn_close;

	@FindBy(xpath = "//input[contains(@value,'Search')]")
	private WebElement btn_Search;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a")
	private WebElement stage_grid_collapse;

	@FindBy(xpath = ".//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div/b")
	private WebElement qa_grid_collapse;

	@FindBy(xpath = "//button[contains(@ng-click,'PayByCheck();')]")
	private WebElement btn_PayByCheck;

	@FindBy(xpath = ".//*[@id='Terms']")
	public WebElement select_Terms;

	@FindBy(xpath = "//input[contains(@value,'Show Quote')]")
	private WebElement buttonShowQuote;

	@FindBy(xpath = "//button[@ng-click='ShowQuoteClose()']")
	public WebElement ShowQuoteClose;

	@FindBy(xpath = ".//*[@id='DOT']")
	private WebElement carrierDOT;

	@FindBy(xpath = ".//*[@id='CompanyName']")
	private WebElement carrierCompanyName;

	@FindBy(xpath = ".//*[@id='Street']")
	private WebElement carrierStreet;

	@FindBy(xpath = ".//*[@id='City']")
	private WebElement carrierCity;

	@FindBy(xpath = ".//*[@id='State']")
	private WebElement carrierState;

	@FindBy(xpath = ".//*[@id='ZipCode']")
	private WebElement carrierZIP;

	@FindBy(xpath = ".//*[@id='Country']")
	private WebElement carrierCountry;

	@FindBy(xpath = ".//*[@id='PhoneNumber']")
	private WebElement carrierPhone;

	@FindBy(xpath = ".//*[@id='ContactName']")
	private WebElement carrierContactName;

	@FindBy(xpath = "//*[@id='formPayByCheck']/div[1]/div[3]/input")
	private WebElement btn_paybychksubmit;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@class='carrierPayment']")
	WebElement paymentid;

	@FindBy(xpath = "//button[contains(@ng-click,'UpdateCheckNumber();')]")
	private WebElement btn_UpdateCheckNumber; // Button to click Add Check Number

	@FindBy(xpath = "//*[@id='checkNo']")
	WebElement EnterCheckNumber; // EnterCheckNumber

	@FindBy(xpath = "//*[@id='reEnterCheckNo']")
	WebElement ReenterCheckNumber; // ReenterCheckNumber

	@FindBy(xpath = "//*[@id='formAddCheckNo']/div/div[3]/input")
	WebElement CheckNumberSubmit; // Click submit for Check Number

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/img")
	WebElement loadingSpinner;

	// Initializing the Page Objects:
	public AdminPayByCheck() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void clickPayments() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_Payments));
		Thread.sleep(1000);
		link_Payments.click();

	}

	public void ClickOnsearchKeyword(String invoice) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.click();
		FieldSearch.sendKeys(invoice);
		Thread.sleep(2000);
		FieldSearch.sendKeys(Keys.RETURN);
	}

	public void getPaymentID(String invoiceNum) throws InterruptedException {

		// this takes the invoice number and looks for it's occurence in the DIV tag
		// if the invoice number is found, get the very next element, which should be
		// the PaymentID link

		Integer counter = 0;

		wait.until(ExpectedConditions.elementToBeClickable(paymentid));
		List<WebElement> childrenElements = paymentid.findElements(By.xpath(".//*"));

		for (WebElement child : childrenElements) {

			if (child.getText().equals(invoiceNum)) {
				paymentIdText = childrenElements.get(counter + 2).getText();
			}

			counter++;
		}

		log.info(paymentIdText);
	}

	public void clickSearch() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_Search));
		link_Search.click();
		waitForLoadingToComplete();
	}

	public void clickShowQuote() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(buttonShowQuote));
		buttonShowQuote.click();
		wait.until(ExpectedConditions.elementToBeClickable(ShowQuoteClose));
		ShowQuoteClose.click();

	}

	public void searchKeyword() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));
		searchKeyword.click();
		searchKeyword.sendKeys(paymentIdText);
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));
		searchKeyword.sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));

	}

	public void clickSearch1() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_Search));
		btn_Search.click();
		waitForLoadingToComplete();
	}

	public void clickgridcollapse() throws InterruptedException {

		if (driver.getCurrentUrl().contains("lpstageadmin")) {
			wait.until(ExpectedConditions.elementToBeClickable(stage_grid_collapse));
			stage_grid_collapse.click();
		} else if (driver.getCurrentUrl().contains("lpqaadmin")) {
			wait.until(ExpectedConditions.elementToBeClickable(qa_grid_collapse));
			qa_grid_collapse.click();
		}

		Thread.sleep(2000);

	}

	public void clickPayByCheck() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_PayByCheck));
		btn_PayByCheck.click();
	}

	public void selectTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_Terms));
		select_Terms.click();
		Select pay = new Select(select_Terms);
		pay.selectByIndex(1);
	}

	public void selectTermsTermPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_Terms));
		select_Terms.click();
		Select pay = new Select(select_Terms);
		pay.selectByIndex(2);
	}

	/**
	 * @return the carrierDOT
	 */
	public WebElement getCarrierDOT() {
		return carrierDOT;
	}

	/**
	 * @param carrierDOT
	 *            the carrierDOT to set
	 * @throws InterruptedException
	 */
	public void setCarrierDOT(String dotValue) throws InterruptedException {
		carrierDOT.clear();
		carrierDOT.sendKeys(dotValue);
	}

	/**
	 * @return the carrierCompanyName
	 */
	public WebElement getCarrierCompanyName() {
		return carrierCompanyName;
	}

	/**
	 * @param carrierCompanyName
	 *            the carrierCompanyName to set
	 * @throws InterruptedException
	 */
	public void setCarrierCompanyName(String carrierNameValue) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierCompanyName));
		carrierCompanyName.clear();
		Thread.sleep(1000);
		carrierCompanyName.sendKeys(carrierNameValue);
	}

	/**
	 * @return the carrierStreet
	 */
	public WebElement getCarrierStreet() {
		return carrierStreet;
	}

	/**
	 * @param carrierStreet
	 *            the carrierStreet to set
	 * @throws InterruptedException
	 */
	public void setCarrierStreet(String carrierStreetValue) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierStreet));
		carrierStreet.click();
		carrierStreet.clear();
		Thread.sleep(1000);
		carrierStreet.sendKeys(carrierStreetValue);
	}

	/**
	 * @return the carrierCity
	 */
	public WebElement getCarrierCity() {
		return carrierCity;
	}

	/**
	 * @param carrierCity
	 *            the carrierCity to set
	 */
	public void setCarrierCity(String carrierCityValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierCity));
		carrierCity.click();
		carrierCity.clear();
		carrierCity.sendKeys(carrierCityValue);
	}

	/**
	 * @return the carrierState
	 */
	public WebElement getCarrierState() {
		return carrierState;
	}

	/**
	 * @param carrierState
	 *            the carrierState to set
	 */
	public void setCarrierState(String carrierStateValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierState));
		carrierState.click();
		Select state = new Select(carrierState);
		state.selectByVisibleText(carrierStateValue);
	}

	/**
	 * @return the carrierZIP
	 */
	public WebElement getCarrierZIP() {
		return carrierZIP;
	}

	/**
	 * @param carrierZIP
	 *            the carrierZIP to set
	 */
	public void setCarrierZIP(String carrierZIPValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierZIP));
		carrierZIP.click();
		carrierZIP.clear();
		carrierZIP.sendKeys(carrierZIPValue);
	}

	/**
	 * @return the carrierCountry
	 */
	public WebElement getCarrierCountry() {
		return carrierCountry;
	}

	/**
	 * @param carrierCountry
	 *            the carrierCountry to set
	 */
	public void setCarrierCountry(String carrierCountryValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierCountry));
		carrierCountry.click();
		Select country = new Select(carrierCountry);
		country.selectByVisibleText(carrierCountryValue);
	}

	/**
	 * @return the carrierPhone
	 */
	public WebElement getCarrierPhone() {
		return carrierPhone;
	}

	/**
	 * @param carrierPhone
	 *            the carrierPhone to set
	 */
	public void setCarrierPhone(String carrierPhoneValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierPhone));
		carrierPhone.click();
		carrierPhone.clear();
		carrierPhone.sendKeys(carrierPhoneValue);
	}

	/**
	 * @return the carrierContactName
	 */
	public WebElement getCarrierContactName() {
		return carrierContactName;
	}

	/**
	 * @param carrierContactName
	 *            the carrierContactName to set
	 */
	public void setCarrierContactName(String carrierContactNameValue) {
		wait.until(ExpectedConditions.elementToBeClickable(carrierContactName));
		carrierContactName.click();
		carrierContactName.clear();
		carrierContactName.sendKeys(carrierContactNameValue);
	}

	public void clickPayByChecksubmit() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(btn_paybychksubmit));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_paybychksubmit);

	}

	public void clickAddCheckNumber() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_UpdateCheckNumber));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_UpdateCheckNumber);
	}

	public void ClickOnEnterCheckNumber() throws InterruptedException {
		String CheckNumber = "1234567890";
		EnterCheckNumber.click();
		wait.until(ExpectedConditions.elementToBeClickable(EnterCheckNumber));
		EnterCheckNumber.sendKeys(CheckNumber);
		wait.until(ExpectedConditions.elementToBeClickable(ReenterCheckNumber));
		ReenterCheckNumber.sendKeys(CheckNumber);
		wait.until(ExpectedConditions.elementToBeClickable(CheckNumberSubmit));
		CheckNumberSubmit.click();
		Thread.sleep(3000);
		WebElement CheckNum = driver.findElement(By.xpath("//*[contains(text(),'1234567890')]"));
		log.info(CheckNum.getText());

	}

	/**
	 * @return the paymentidd
	 */
	public String getPaymentidd() {
		return paymentIdText;
	}

	/**
	 * @return the field_ein
	 */
	public WebElement getField_ein() {
		return field_ein;
	}

	/**
	 * @return the field_loadpaydepositeamt
	 */
	public WebElement getField_loadpaydepositeamt() {
		return field_loadpaydepositeamt;
	}

	/**
	 * @return the link_Payments
	 */
	public WebElement getLink_Payments() {
		return link_Payments;
	}

	/**
	 * @return the link_Search
	 */
	public WebElement getLink_Search() {
		return link_Search;
	}

	/**
	 * @return the checkboxaccept
	 */
	public WebElement getCheckboxaccept() {
		return checkboxaccept;
	}

	/**
	 * @return the checkboxemail
	 */
	public WebElement getCheckboxemail() {
		return checkboxemail;
	}

	/**
	 * @return the fieldSearch
	 */
	public WebElement getFieldSearch() {
		return FieldSearch;
	}

	/**
	 * @return the searchKeyword
	 */
	public WebElement getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * @return the btn_close
	 */
	public WebElement getBtn_close() {
		return btn_close;
	}

	/**
	 * @return the btn_Search
	 */
	public WebElement getBtn_Search() {
		return btn_Search;
	}

	/**
	 * @return the btn_PayByCheck
	 */
	public WebElement getBtn_PayByCheck() {
		return btn_PayByCheck;
	}

	/**
	 * @return the select_Terms
	 */
	public WebElement getSelect_Terms() {
		return select_Terms;
	}

	/**
	 * @return the btn_paybychksubmit
	 */
	public WebElement getBtn_paybychksubmit() {
		return btn_paybychksubmit;
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return UserName;
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return Password;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * @return the paymentid
	 */
	public WebElement getPaymentid() {
		return paymentid;
	}

	/**
	 * @return the btn_UpdateCheckNumber
	 */
	public WebElement getBtn_UpdateCheckNumber() {
		return btn_UpdateCheckNumber;
	}

	/**
	 * @return the enterCheckNumber
	 */
	public WebElement getEnterCheckNumber() {
		return EnterCheckNumber;
	}

	/**
	 * @return the reenterCheckNumber
	 */
	public WebElement getReenterCheckNumber() {
		return ReenterCheckNumber;
	}

	/**
	 * @return the checkNumberSubmit
	 */
	public WebElement getCheckNumberSubmit() {
		return CheckNumberSubmit;
	}

	public void waitForLoadingToComplete() throws InterruptedException {
		if (loadingSpinner.isDisplayed()) {
			Thread.sleep(2000);
			waitForLoadingToComplete();
		}
	}
}
