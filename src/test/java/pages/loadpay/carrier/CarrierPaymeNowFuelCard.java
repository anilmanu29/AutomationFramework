package pages.loadpay.carrier;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierPaymeNowFuelCard extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	String totalamt;
	String amtwiretransfer;
	String amtbeforepaidsameday;
	float amtbeforesamedayach;
	String amtwiretrsfr;
	String amtsmdayach;
	float amttwiretransfer;
	float total;
	String totalamount;
	String totalamountt;
	String amtbeforepaidsmday;
	String amtbefore;
	String paymenowfe;
	String paymenow;
	String paymenowfeee;
	float paymenowf;

	@FindBy(xpath = "//*[@class='getpaid']")
	private WebElement btn_paymenow;

	@FindBy(xpath = "//span[text()='FUEL CARD']//following::input[@type='submit'][2]")
	private WebElement btn_selectfuelcard;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btn_confirm;

	@FindBy(xpath = ".//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tab_paid;

	@FindBy(xpath = ".//button[text()='Add New Card']")
	private WebElement btn_addnewcard;

	@FindBy(xpath = "//input[@value='fleetone']")
	private WebElement rbtn_fleetone;

	@FindBy(id = "accountnbr")
	private WebElement inp_accountnbr;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btn_submit;

	@FindBy(xpath = "//*[@type='button'][text()='SUBMIT']")
	private WebElement btn_fuelcardsubmit;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement button_login;

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
	WebElement rbtn_fts;

	public CarrierPaymeNowFuelCard() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_paymenow));
		btn_paymenow.click();
	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectfuelcard));
		btn_selectfuelcard.click();
	}

	public void clickaddnewcard() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_addnewcard));
		btn_addnewcard.click();
	}

	public void clickfleetone() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(rbtn_fleetone));
		rbtn_fleetone.click();
	}

	public void input_accountnbr(String accountnbr) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(inp_accountnbr));
		inp_accountnbr.sendKeys(accountnbr);
	}

	public void clicksubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_submit));
		btn_submit.click();
	}

	public void clickfuelcardsubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_fuelcardsubmit));
		btn_fuelcardsubmit.click();
	}

	public void clickConfirmButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_confirm));
		btn_confirm.click();
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_paid));
		tab_paid.click();

	}

	public void clickpaymenowtab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymenowtab));
		paymenowtab.click();

	}

	public void clickFTS() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(rbtn_fts));
		rbtn_fts.click();

	}
}
