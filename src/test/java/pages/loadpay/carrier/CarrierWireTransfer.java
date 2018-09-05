package pages.loadpay.carrier;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierWireTransfer extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
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

	@FindBy(xpath = "//a[contains(@href,'#/Payments/PayMeNow')]")
	WebElement PayMeNowTab;

	@FindBy(xpath = "//*[@class='getpaid']")
	private WebElement btn_paymenow;

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[8]/input[1]")
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

	@FindBy(xpath = "//*[@id='paymentOptionsDiv']/div[2]/div[1]/div[3]/p/span")
	WebElement wiretransframt;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(xpath = "//*[@class='getpaid']")
	private List<WebElement> paymenowpayments;

	public CarrierWireTransfer() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	public void getAmount() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		amtbeforepaidsameday = paidamt.getText();
		amtbeforepaidsmday = amtbeforepaidsameday.replaceAll("\\$", "");
		amtbefore = amtbeforepaidsmday.replaceAll(",", "");
		System.out.println(amtbeforepaidsmday);
		amtbeforesamedayach = Float.parseFloat(amtbefore);
		System.out.println(amtbeforesamedayach);
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
		System.out.println(amttwiretransfer);
	}

	public void clickSelectButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_selectwiretransfer));
		js.executeScript("arguments[0].click();", btn_selectwiretransfer);
		// btn_selectwiretransfer.click();
	}

	public void clickConfirmButton() throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(btn_confirm));
		Thread.sleep(1000);

		js.executeScript("arguments[0].click();", btn_confirm);
	}

	public void clickPaidTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tab_paid));
		act.moveToElement(tab_paid).click().perform();

	}

	public void gettotalpaiyAmount() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(paidamt));
		totalamt = paidamt.getText();
		totalamount = totalamt.replaceAll("\\$", "");
		totalamountt = totalamount.replaceAll(",", "");
		total = Float.parseFloat(totalamountt);
		System.out.println(total);

	}

	public void verifywiretransfer() throws InterruptedException {
		amttwiretransfer = (total - amtbeforesamedayach);
		System.out.println(amttwiretransfer);

	}

	public void goToPayMeNowTab() {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowTab));
		PayMeNowTab.click();
	}

	public void clickPayMeNowPayment(String iN) {
		for (int i = 0; i < List_payment.size(); i++) {
			String invoiceno = List_payment.get(i).getText();
			if (invoiceno.contains(iN)) {
				// paymenowpayments.get(i).click();
				act.moveToElement(paymenowpayments.get(i)).click().perform();
			}
		}
	}
}
