package pages.loadpay.broker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

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

public class BrokerDisableCopyPasteConfirmBankAccount extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	Robot robot;
	BrokerBanking brokerbankingobj;

	@FindBy(id = "ConfirmBankingAccount")
	private WebElement confirmbankaccountfield;

	@FindBy(id = "BankingAccount")
	private WebElement bankaccountnumber;

	@FindBy(xpath = "//*[@name='formBanking']//child::input[@type='radio']")
	private List<WebElement> businesscheckboxes;

	@FindBy(id = "NameOnAccount")
	private WebElement nameonaccountfield;

	@FindBy(id = "BankingRouting")
	private WebElement routingnumberfield;

	@FindBy(id = "BankingAccount")
	private WebElement accnumberfield;

	@FindBy(id = "ConfirmBankingAccount")
	private WebElement confirmaccnumberfield;

	@FindBy(xpath = "//*[@class='error ng-scope']")
	private WebElement errormessage;

	@FindBy(xpath = "//input[@type='submit'][@value='Next']")
	private WebElement nextbutton;

	@FindBy(xpath = "//form[@id='formBanking']//child::input[@value='Save']")
	private WebElement button_save;

	@FindBy(xpath = "//form[@id='formBanking']//child::input[@value='Submit']")
	private WebElement button_submit;

	@FindBy(xpath = "//*[@id='formBanking']//child::input[@type='submit']")
	private WebElement registrationbuttonsave;

	/*-------PageFactory---------*/
	public BrokerDisableCopyPasteConfirmBankAccount() throws AWTException, IOException {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 20);
		act = new Actions(driver);
		robot = new Robot();
		brokerbankingobj = new BrokerBanking();
	}

	public void copyBankAccountNumber(WebElement element) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
	}

	public void pasteBankAccountNumber(WebElement element) {
		element.clear();
		element.click();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public String getConfirmAccountNumber() {
		return confirmbankaccountfield.getAttribute("value");
	}

	public WebElement getAccountNameField() {
		wait.until(ExpectedConditions.elementToBeClickable(nameonaccountfield));
		return nameonaccountfield;
	}

	public WebElement getRoutingNumberField() {
		return routingnumberfield;
	}

	public WebElement getAccountNumberField() {
		return accnumberfield;
	}

	public WebElement getConfirmAccountNumberField() {
		return confirmaccnumberfield;
	}

	public void setAccountNameField(String accname) {
		wait.until(ExpectedConditions.elementToBeClickable(nameonaccountfield));
		nameonaccountfield.clear();
		nameonaccountfield.click();
		nameonaccountfield.sendKeys(accname);
	}

	public void setRoutingNumberField(String routnum) {
		wait.until(ExpectedConditions.elementToBeClickable(routingnumberfield));
		routingnumberfield.clear();
		routingnumberfield.click();
		routingnumberfield.sendKeys(routnum);
	}

	public void setAccountNumberField(String accnum) {
		wait.until(ExpectedConditions.elementToBeClickable(accnumberfield));
		accnumberfield.clear();
		accnumberfield.click();
		accnumberfield.sendKeys(accnum);
	}

	public void setConfirmAccountNumberField() {
		wait.until(ExpectedConditions.elementToBeClickable(accnumberfield));
		accnumberfield.clear();
		accnumberfield.click();
	}

	public void clearTextField(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
	}

	public String geterrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(errormessage));
		return errormessage.getText();
	}

	public void clickSubmitButton() {
		wait.until(ExpectedConditions.visibilityOf(registrationbuttonsave));
		js.executeScript("arguments[0].click();", registrationbuttonsave);
	}

	public WebElement getNextButton() {
		return nextbutton;
	}

	public WebElement getSavebuton() {
		return button_save;
	}

	public void verifyCopyPasteforTyesofAccount(String accname, String routingnum, String accnum)
			throws InterruptedException {

		for (WebElement checkbox : businesscheckboxes) {
			js.executeScript("arguments[0].click();", checkbox);
			clearTextField(getAccountNameField());
			brokerbankingobj.enterAccountName(accname);
			Thread.sleep(1000);
			clearTextField(getRoutingNumberField());
			brokerbankingobj.enterRoutingNumber(routingnum);
			clearTextField(getAccountNumberField());
			brokerbankingobj.enterAccountNumber(accnum);
			Thread.sleep(1000);
			copyBankAccountNumber(getAccountNumberField());
			Thread.sleep(1000);
			pasteBankAccountNumber(getConfirmAccountNumberField());
			Thread.sleep(1000);
			Assert.assertEquals(getConfirmAccountNumber(), "", "Copy/paste is happening");
			if (button_save.isEnabled()) {
				js.executeScript("arguments[0].click();", button_save);
			} else {
				Assert.assertTrue(!button_save.isEnabled(), "Save button is Disabled");
			}

		}

	}

	public void verifyCopyPasteforTypeofAccount() throws InterruptedException {

		for (WebElement checkbox : businesscheckboxes) {
			js.executeScript("arguments[0].click();", checkbox);
			Thread.sleep(1000);
			copyBankAccountNumber(getAccountNumberField());
			Thread.sleep(1000);
			pasteBankAccountNumber(getConfirmAccountNumberField());
			Thread.sleep(1000);
			Assert.assertEquals(getConfirmAccountNumber(), "", "Copy/paste is happening");
			if (button_submit.isEnabled()) {
				js.executeScript("arguments[0].click();", button_submit);
				Assert.assertTrue(geterrorMessage().contains("Account Number do not match"), "Validation NOT found");

			} else {
				Assert.assertTrue(!button_submit.isEnabled(), "Submit button is Disabled");
			}

		}

	}

	public void verifyCopyPastefornrokerTypeofAccount(String accname, String routingnum, String accnum)
			throws InterruptedException {

		for (WebElement checkbox : businesscheckboxes) {
			js.executeScript("arguments[0].click();", checkbox);
			clearTextField(getAccountNameField());
			brokerbankingobj.enterAccountName(accname);
			Thread.sleep(1000);
			clearTextField(getRoutingNumberField());
			brokerbankingobj.enterRoutingNumber(routingnum);
			clearTextField(getAccountNumberField());
			brokerbankingobj.enterAccountNumber(accnum);
			Thread.sleep(1000);
			copyBankAccountNumber(getAccountNumberField());
			Thread.sleep(1000);
			pasteBankAccountNumber(getConfirmAccountNumberField());
			Thread.sleep(1000);
			Assert.assertEquals(getConfirmAccountNumber(), "", "Copy/paste is happening");
			Assert.assertTrue(!getNextButton().isEnabled(), "NextButton is Enabled");

		}

	}

}
