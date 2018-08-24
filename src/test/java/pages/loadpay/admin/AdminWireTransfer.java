package pages.loadpay.admin;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class AdminWireTransfer extends TestBase {
	WebDriverWait wait = null;
	String invoicenumber = "";
	String paymentidd;
	Actions act = new Actions(driver);
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

	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btn_close;

	@FindBy(xpath = "//input[contains(@value,'Search')]")
	private WebElement btn_Search;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/a")
	private WebElement grid_collapse;

	@FindBy(xpath = ".//*[@id='Terms']")
	private WebElement select_Terms;

	@FindBy(xpath = ".//*[@id='DOT']")
	private WebElement txt_DOT;

	@FindBy(xpath = "//input[contains(@name,'ContactName')]")
	private WebElement txt_ContactName;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='carrierPayment']//child::div[9]//child::span")
	WebElement paymentid;

	@FindBy(xpath = "//button[text()='WIRE TRANSFER']")
	private WebElement buttonWireTransfer;

	@FindBy(id = "OFACCheck")
	WebElement ofacCheckbox;

	@FindBy(id = "ConfirmationNumber")
	WebElement wireTransferConfirmationNumberField;

	@FindBy(xpath = "//input[@value='Confirm!']")
	WebElement confirmWireTransferButton;
	/*
	 * @FindBy(xpath = "//span[contains(@class,'paymentDate')]") WebElement
	 * paymentDate;
	 */

	@FindBy(xpath = ".//*[text()='PayMeNow']")
	WebElement paymentDate;

	@FindBy(xpath = "//button[text() ='Failed']")
	WebElement failedWireTransferButton;

	@FindBy(xpath = "//span[text() ='PAYMENT FAILED']")
	WebElement paymentFailed;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(xpath = "//*[@class='getpaid']")
	private List<WebElement> paymenowpayments;

	@FindBy(xpath = "//*[@class='pull-left loadNumber ellipsis']")
	private List<WebElement> loadnumbers;

	// Initializing the Page Objects:
	public AdminWireTransfer() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void clickPayments() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(link_Payments));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Payments);
		Thread.sleep(1000);

	}

	public void ClickOnsearchKeyword(String invoice) throws InterruptedException {
		FieldSearch.click();
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.sendKeys(invoice);
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));

	}

	public void ClickOnsearchKeywordterm(String invoice) throws InterruptedException {
		FieldSearch.click();
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.sendKeys(invoice);
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.sendKeys(Keys.RETURN);
		// wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));

	}

	public void getPaymentID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymentid));
		paymentidd = paymentid.getText();
		System.out.println(paymentidd);
	}

	public void clickSearch() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(link_Search));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_Search);

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

	public void clickWireTransferButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(buttonWireTransfer));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", buttonWireTransfer);
	}

	public void markOFacCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(ofacCheckbox));
		ofacCheckbox.click();
	}

	public void enterWireTransferConfirmationNumber() {
		wait.until(ExpectedConditions.visibilityOf(wireTransferConfirmationNumberField));
		wireTransferConfirmationNumberField.sendKeys("12345600");
	}

	public void confirmWireTransfer() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmWireTransferButton));
		confirmWireTransferButton.click();
	}

	public void verifyPaymentIssued() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(paymentDate));
		System.out.println(paymentDate.getText());
		Assert.assertTrue(paymentDate.isDisplayed());
	}

	public void clickFailedWireTransferButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(failedWireTransferButton));
		failedWireTransferButton.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void verifyPaymentFailed() {
		wait.until(ExpectedConditions.visibilityOf(paymentFailed));
		System.out.println(paymentFailed.getText());
		Assert.assertTrue(paymentFailed.isDisplayed());
	}

	public void clickPayMeNowPayment(String iN) {
		for (int i = 0; i < List_payment.size(); i++) {
			String invoiceno = List_payment.get(i).getText();
			if (invoiceno.contains(iN)) {
				paymenowpayments.get(i).click();
			}
		}
	}

	public void expandPayment(String load) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElements(loadnumbers));
		for (int i = 0; i < loadnumbers.size(); i++) {
			String loadid = loadnumbers.get(i).getAttribute("title");
			System.out.println(loadid);
			if (loadid.contains(load)) {
				Thread.sleep(1000);
				act.moveToElement(loadnumbers.get(i)).click().perform();
				// loadnumbers.get(i).click();
			}
		}
	}

}