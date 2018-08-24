package pages.loadpay.unmatched;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.loadpay.unmatched.UnmatchedCarrierOutlooksameDayTest;

public class UnmatchedCarrierWireTransfer extends TestBase {

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

	@FindBy(xpath = "//span[text()='WIRE TRANSFER']//following::input[@type='submit'][1]")
	private WebElement btn_selectwiretransfer;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btn_confirm;

	@FindBy(xpath = ".//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tab_paid;

	@FindBy(xpath = ".//*[@id='collapseDetails31568']/div[3]/div[2]/div//child::span/text()")
	private WebElement camount;

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

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[4]/div[1]/div[3]/p/span")
	WebElement wiretransframt;

	public UnmatchedCarrierWireTransfer() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void carrierLogin() {
		UserName.sendKeys(BrokerPaymentforUnmatchedCarrierTest.al.get(1));
		Password.sendKeys(UnmatchedCarrierOutlooksameDayTest.pwd);
		loginBtn.click();
	}

	public void getAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		amtbeforepaidsameday = paidamt.getText();
		amtbeforepaidsmday = amtbeforepaidsameday.replaceAll("\\$", "");
		amtbefore = amtbeforepaidsmday.replaceAll(",", "");
		log.info(amtbeforepaidsmday);
		amtbeforesamedayach = Float.parseFloat(amtbefore);
		log.info(amtbeforesamedayach);
	}

	public void clickPaymenow() throws InterruptedException {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(btn_paymenow));
		btn_paymenow.click();
	}

	public void getwiretransferAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(wiretransframt));
		amtwiretransfer = wiretransframt.getText();
		amtwiretrsfr = amtwiretransfer.replaceAll("\\$", "");
		amtsmdayach = amtwiretrsfr.replaceAll(",", "");
		amttwiretransfer = Float.parseFloat(amtsmdayach);
		log.info(amttwiretransfer);
	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectwiretransfer));
		btn_selectwiretransfer.click();
	}

	public void clickConfirmButton() throws InterruptedException {
		act.pause(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_confirm);
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_paid));
		act.moveToElement(tab_paid).click().perform();

	}

	public void gettotalpaiyAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		totalamt = paidamt.getText();
		totalamount = totalamt.replaceAll("\\$", "");
		totalamountt = totalamount.replaceAll(",", "");
		total = Float.parseFloat(totalamountt);
		log.info(total);

	}

	public void verifywiretransfer() throws InterruptedException {
		amttwiretransfer = (total - amtbeforesamedayach);
		log.info(amttwiretransfer);

	}

}
