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

public class UnmatchedCarrierNextDayACH extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	String totalamt;
	String amtnextdayach;
	String amtbeforepaidsameday;
	float amtbeforesamedayach;
	String amtnextdayachh;
	String amtnxtdayach;
	float amttnextdayach;
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

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[8]/input[1]")
	private WebElement btn_selectnextdayach;

	@FindBy(xpath = "//div[@id='payMeNowQuoteDiv']//child::button[text()='CONFIRM']")
	private WebElement btn_confirm;

	@FindBy(xpath = "//*[@class='title ng-binding'][text()='PAID']")
	private WebElement tab_paid;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[3]/a/div/div[1]/div[2]/span[1]")
	private WebElement paidamt;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[3]/p/span")
	private WebElement nextdayamt;

	@FindBy(xpath = ".//*[@id='paymentOptionsDiv']/div[3]/div[1]/div[5]/span[3]//following::text()[1]")
	private WebElement paymenowfee;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/ul/li[1]/a/div/div/div[2]/span[2]")
	private WebElement paymenowtab;

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	public UnmatchedCarrierNextDayACH() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void carrierLogin() {
		UserName.sendKeys(BrokerPaymentforUnmatchedCarrierTest.unMatchedCarrierUsername);
		Password.sendKeys(BrokerPaymentforUnmatchedCarrierTest.unMatchedCarrierPassword);
		loginBtn.click();
	}

	public void getAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		amtbeforepaidsameday = paidamt.getText();
		amtbeforepaidsmday = amtbeforepaidsameday.replaceAll("\\$", "");
		amtbefore = amtbeforepaidsmday.replaceAll(",", "");
		amtbeforesamedayach = Float.parseFloat(amtbefore);
		log.info(amtbeforesamedayach);
	}

	public void clickPaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_paymenow));
		btn_paymenow.click();
	}

	public void getnextdayAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(nextdayamt));
		amtnextdayach = nextdayamt.getText();
		amtnextdayachh = amtnextdayach.replaceAll("\\$", "");
		amtnxtdayach = amtnextdayachh.replaceAll(",", "");
		amttnextdayach = Float.parseFloat(amtnxtdayach);
		log.info(amttnextdayach);
	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectnextdayach));
		btn_selectnextdayach.click();
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
		// wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		totalamt = paidamt.getText();
		totalamount = totalamt.replaceAll("\\$", "");
		totalamountt = totalamount.replaceAll(",", "");
		total = Float.parseFloat(totalamountt);
		log.info(total);

	}

	public void verifyNextDayach() throws InterruptedException {
		amttnextdayach = (total - amtbeforesamedayach);
		log.info(amttnextdayach);

	}

	/**
	 * @return the btn_paymenow
	 */
	public WebElement getBtn_paymenow() {
		return btn_paymenow;
	}

	/**
	 * @return the btn_selectnextdayach
	 */
	public WebElement getBtn_selectnextdayach() {
		return btn_selectnextdayach;
	}

	/**
	 * @return the btn_confirm
	 */
	public WebElement getBtn_confirm() {
		return btn_confirm;
	}

	/**
	 * @return the tab_paid
	 */
	public WebElement getTab_paid() {
		return tab_paid;
	}

	/**
	 * @return the paidamt
	 */
	public WebElement getPaidamt() {
		return paidamt;
	}

	/**
	 * @return the nextdayamt
	 */
	public WebElement getNextdayamt() {
		return nextdayamt;
	}

	/**
	 * @return the paymenowfee
	 */
	public WebElement getPaymenowfee() {
		return paymenowfee;
	}

	/**
	 * @return the paymenowtab
	 */
	public WebElement getPaymenowtab() {
		return paymenowtab;
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

}
