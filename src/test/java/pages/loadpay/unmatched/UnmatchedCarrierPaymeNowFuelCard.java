package pages.loadpay.unmatched;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedCarrierPaymeNowFuelCard extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//*[@class='getpaid']")
	private WebElement btnPayMeNow;

	@FindBy(xpath = "//span[text()='FUEL CARD']//following::input[@type='submit'][2]")
	private WebElement btnSelectFuelCard;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btnConfirm;

	@FindBy(xpath = ".//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tabPaid;

	@FindBy(xpath = ".//button[text()='Add New Card']")
	private WebElement btnAddNewCard;

	@FindBy(xpath = "//input[@value='fleetone']")
	private WebElement rbtnFleetone;

	@FindBy(id = "accountnbr")
	private WebElement inpAccountnbr;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSubmit;

	@FindBy(xpath = "//*[@type='button'][text()='SUBMIT']")
	private WebElement btnFuelCardSubmit;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btnLogout;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement buttonLogin;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[2]/span[1]")
	private WebElement paidamt;

	@FindBy(xpath = "//*[@class='PMN']")
	WebElement paymenowtab;

	@FindBy(xpath = ".//input[@value='efs']")
	WebElement rbtnFts;

	public UnmatchedCarrierPaymeNowFuelCard() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void carrierLogin() {
		UserName.sendKeys(BrokerPaymentforUnmatchedCarrierTest.unMatchedCarrierUsername);
		Password.sendKeys(BrokerPaymentforUnmatchedCarrierTest.unMatchedCarrierPassword);
		loginBtn.click();
	}

	public void clickPaymenow() throws InterruptedException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(btnPayMeNow));
		btnPayMeNow.click();
	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnSelectFuelCard));
		btnSelectFuelCard.click();
	}

	public void clickaddnewcard() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewCard));
		btnAddNewCard.click();
	}

	public void clickfleetone() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(rbtnFleetone));
		rbtnFleetone.click();
	}

	public void input_accountnbr(String accountnbr) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(inpAccountnbr));
		inpAccountnbr.sendKeys(accountnbr);
	}

	public void clicksubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
	}

	public void clickfuelcardsubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnFuelCardSubmit));
		btnFuelCardSubmit.click();
	}

	public void clickConfirmButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tabPaid));
		tabPaid.click();

	}

	public void clickpaymenowtab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymenowtab));
		paymenowtab.click();
	}

	public void clickFTS() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(rbtnFts));
		rbtnFts.click();

	}

}
