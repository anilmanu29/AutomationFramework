package pages.loadpay.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminPayByCheckServiceBatchFile extends TestBase {

	WebDriverWait wait = null;
	String paymentidd;
	// Page Factory - OR:
	@FindBy(id = "EIN")
	WebElement field_ein;

	@FindBy(id = "ControlAmount")
	WebElement field_loadpaydepositeamt;

	@FindBy(xpath = ".//a[contains(text(),'Payments')]")
	WebElement link_Payments;

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

	@FindBy(xpath = "//a[@href='#/DailyCheckPaymentFiles']")
	private WebElement lnk_DailyCheckPaymentFiles;

	@FindBy(xpath = "//button[@ng-click='ShowPaymentsForBatch(file);']")
	private WebElement btn_ShowPaymentsForBatch;

	@FindBy(xpath = "//input[@id='SendTo']")
	private WebElement txt_email;

	@FindBy(xpath = "//input[contains(@value,'Send')]")
	private WebElement btn_Send;

	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btn_close;

	@FindBy(xpath = "//input[contains(@value,'Search')]")
	private WebElement btn_Search;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a")
	private WebElement grid_collapse;

	@FindBy(xpath = "//button[contains(@ng-click,'PayByCheck();')]")
	private WebElement btn_PayByCheck;

	@FindBy(xpath = ".//*[@id='Terms']")
	private WebElement select_Terms;

	@FindBy(xpath = ".//*[@id='DOT']")
	private WebElement txt_DOT;

	@FindBy(xpath = "//input[contains(@name,'ContactName')]")
	private WebElement txt_ContactName;

	@FindBy(xpath = "//input[contains(@value,'Submit')]")
	private WebElement btn_paybychksubmit;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='carrierPayment']//child::div[9]//child::span")
	WebElement paymentid;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[1]/div")
	WebElement fileSentMessage;

	// Initializing the Page Objects:
	public AdminPayByCheckServiceBatchFile() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void clickDailyCheckPaymentFiles() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_DailyCheckPaymentFiles));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnk_DailyCheckPaymentFiles);

	}

	public void ClickShowPaymentsForBatch() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_ShowPaymentsForBatch));
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_ShowPaymentsForBatch);

	}

	public void EnterEmailID(String email) {
		wait.until(ExpectedConditions.visibilityOf(txt_email));
		txt_email.sendKeys(email);
	}

	public void ClickSendButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_Send));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_Send);

	}

	public Boolean wasFileSent() {
		if (fileSentMessage.isDisplayed())
			return true;
		else
			return false;
	}
}
