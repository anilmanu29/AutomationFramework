
package pages.loadpay.admin;

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
	String paymentidd;
	JavascriptExecutor js = (JavascriptExecutor) driver;
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

	// @FindBy(xpath =
	// ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a")
	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]")
	private WebElement grid_collapse;

	@FindBy(xpath = "//button[contains(@ng-click,'PayByCheck();')]")
	private WebElement btn_PayByCheck;

	@FindBy(xpath = ".//*[@id='Terms']")
	public WebElement select_Terms;

	@FindBy(xpath = "//input[contains(@value,'Show Quote')]")
	private WebElement buttonShowQuote;

	@FindBy(xpath = "//button[@ng-click='ShowQuoteClose()']")
	public WebElement ShowQuoteClose;

	@FindBy(xpath = ".//*[@id='DOT']")
	private WebElement txt_DOT;

	@FindBy(xpath = "//input[contains(@name,'ContactName')]")
	private WebElement txt_ContactName;

	@FindBy(xpath = "//*[@id='formPayByCheck']/div[1]/div[3]/input")
	private WebElement btn_paybychksubmit;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='carrierPayment']//child::div[9]//child::span")
	WebElement paymentid;

	@FindBy(xpath = "//button[contains(@ng-click,'UpdateCheckNumber();')]")
	private WebElement btn_UpdateCheckNumber; // Button to click Add Check Number

	@FindBy(xpath = "//*[@id='checkNo']")
	WebElement EnterCheckNumber; // EnterCheckNumber

	@FindBy(xpath = "//*[@id='reEnterCheckNo']")
	WebElement ReenterCheckNumber; // ReenterCheckNumber

	@FindBy(xpath = "//*[@id='formAddCheckNo']/div/div[3]/input")
	WebElement CheckNumberSubmit; // Click submit for Check Number

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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Payments);

	}

	public void ClickOnsearchKeyword(String invoice) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		js.executeScript("arguments[0].click();", FieldSearch);
		FieldSearch.sendKeys(invoice);
		FieldSearch.sendKeys(Keys.RETURN);
	}

	public void getPaymentID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymentid));
		paymentidd = paymentid.getText();
		log.info(paymentidd);
	}

	public void clickSearch() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_Search));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Search);

	}

	public void clickShowQuote() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(buttonShowQuote));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", buttonShowQuote);
		wait.until(ExpectedConditions.elementToBeClickable(ShowQuoteClose));
		ShowQuoteClose.click();

	}

	public void searchKeyword() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));
		searchKeyword.click();
		searchKeyword.sendKeys(paymentidd);
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));
		searchKeyword.sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.elementToBeClickable(searchKeyword));

	}

	public void clickSearch1() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_Search));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_Search);
	}

	public void clickgridcollapse() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(grid_collapse));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", grid_collapse);

	}

	public void clickPayByCheck() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_PayByCheck));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_PayByCheck);
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

	public void EnterDOTNnumber(String EnterDOTNnumber) {
		wait.until(ExpectedConditions.elementToBeClickable(txt_DOT));
		txt_DOT.clear();
		txt_DOT.sendKeys(EnterDOTNnumber);
	}

	public void ContactName(String ContactName) {
		wait.until(ExpectedConditions.elementToBeClickable(txt_ContactName));
		txt_ContactName.clear();
		txt_ContactName.sendKeys(ContactName);
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
		wait.until(ExpectedConditions.elementToBeClickable(CheckNumberSubmit));
		WebElement CheckNum = driver.findElement(By.xpath("//*[contains(text(),'1234567890')]"));
		log.info(CheckNum.getText());

	}

	/**
	 * @return the paymentidd
	 */
	public String getPaymentidd() {
		return paymentidd;
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
	 * @return the grid_collapse
	 */
	public WebElement getGrid_collapse() {
		return grid_collapse;
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
	 * @return the txt_DOT
	 */
	public WebElement getTxt_DOT() {
		return txt_DOT;
	}

	/**
	 * @return the txt_ContactName
	 */
	public WebElement getTxt_ContactName() {
		return txt_ContactName;
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
}
