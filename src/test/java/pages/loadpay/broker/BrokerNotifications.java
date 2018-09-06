package pages.loadpay.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import pages.loadpay.admin.AdminPayByCheck;

public class BrokerNotifications extends TestBase {
	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	String paymentStatus = "Verified";
	public String carrierEmail = "";
	int invoiceNum = 0;
	String loadId = "";
	String paymentAmount = "";
	ArrayList<String> tabs;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = null;
	public static ArrayList<String> arraylist;
	AdminPayByCheck adminpaybycheckobj;
	String invoicenumber = "";
	int min = 0;
	int max = 30;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div/div[2]/div/div/div[1]/div/div[9]/span")
	private WebElement payment;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[3]/div/div[2]/div/div[2]/div/div/div[1]/div/div[6]/span")
	private WebElement carrierpayment;

	@FindBy(xpath = ".//*[@class='carrierPayment'][@aria-expanded='true']")
	private WebElement expandedpayment;

	@FindBy(xpath = "//i[text()='mode_edit']")
	private WebElement paymenteditbutton;

	@FindBy(xpath = "//div[@role='alert']")
	private WebElement alertmessage;

	@FindBy(xpath = "//*[@class='getpaid']")
	private List<WebElement> paymenowpayments;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(xpath = "//i[@class='fa fa-circle fa-stack-2x background']")
	private WebElement notificationcount;

	@FindBy(xpath = "//a[@href='#/Notifications']")
	private WebElement notificationlink;

	@FindBy(xpath = "//*[contains(@class,'detailValue invoiceNumber ellipsis')]")
	private List<WebElement> listofnotificationinvoicenumbers;

	@FindBy(xpath = "//*[@class='icon fa fa-clock-o']")
	private WebElement notificationinvoice;

	@FindBy(xpath = "//a[@aria-expanded='true']//child::i[@class='fa fa-lg fa-minus-circle']")
	private WebElement notificationcancelbutton;

	/*-------Initializing driver---------*/
	public BrokerNotifications() {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		brokerPaymentObj = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
		arraylist = new ArrayList<String>();
		adminpaybycheckobj = new AdminPayByCheck();
	}

	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);
		Thread.sleep(1000);
	}

	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA) throws InterruptedException {

		int randomNumber = getRandomNumber(1, 999999);
		invoiceNum = randomNumber;
		invoicenumber = Integer.toString(invoiceNum);
		iN = invoicenumber;
		lId = invoicenumber;

		// Store data-provider elements into publicly-accessible strings
		carrierEmail = cE;
		loadId = lId;
		paymentAmount = pA;

		// create new payment
		brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();
		// Thread.sleep(1000);
		brokerPaymentObj.carrierEmail(carrierEmail);
		// Thread.sleep(1000);
		brokerPaymentObj.amount(paymentAmount);
		// Thread.sleep(1000);
		brokerPaymentObj.invoiceNumber(invoicenumber);
		arraylist.add(invoicenumber);
		// Thread.sleep(1000);
		brokerPaymentObj.loadId(loadId);
		// Thread.sleep(1000);
		brokerPaymentObj.clickShedulePayment();
		// Thread.sleep(1000);
		brokerPaymentObj.clickShedulePaymenttab();
		// Thread.sleep(1000);
		brokerPaymentObj.searchInvoice(invoicenumber);
		// Thread.sleep(1000);
		brokerPaymentObj.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		brokerPaymentObj.verifyInvoiceNumber(invoicenumber, paymentAmount);
		Thread.sleep(1000);

		// verify payment status
		// Assert.assertTrue(brokerPaymentObj.verifyPaymentStatus().equals(paymentStatus),
		// "Payment Status not equal!");
	}

	public void openandSwitchtoNewTab() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public void SwitchtoTab(int index) throws InterruptedException {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

	public void clickPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(payment));
		js.executeScript("arguments[0].click();", payment);
	}

	public void clickCarrierkPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierpayment));
		js.executeScript("arguments[0].click();", carrierpayment);
	}

	public WebElement getExpandedPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(expandedpayment));
		return expandedpayment;
	}

	public int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public void clickPayMeNowPayment() {
		for (int i = 0; i < List_payment.size(); i++) {
			String invoiceno = List_payment.get(i).getText();
			if (invoiceno.contains(invoicenumber)) {
				paymenowpayments.get(i).click();
			}
		}
	}

	public void clickPaymeNowPaymentNotification() throws InterruptedException {
		while ((!driver.findElement(By.xpath(
				".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/div/a/div/div[3]/div[2]/span"))
				.getText().contains("a few seconds ago")) && (min < max)) {
			driver.navigate().refresh();
			Thread.sleep(3000);
			min++;

		}
		Thread.sleep(1000);
		// Assert.assertTrue(getNotification().isDisplayed(), "Notification NOT found");
		// driver.findElement(By.xpath(
		// ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/div/a/div/div[3]/div[2]/span"))
		// .click();
		js.executeScript("arguments[0].click();", notificationinvoice);
		// notificationinvoice.click();

	}

	public void clickNotificationDeleteButton() {
		wait.until(ExpectedConditions.elementToBeClickable(notificationcancelbutton));
		js.executeScript("arguments[0].click();", notificationcancelbutton);
	}

	public WebElement getNotification() {
		wait.until(ExpectedConditions.elementToBeClickable(notificationcount));
		return notificationcount;
	}

	public void clickNotification() {
		wait.until(ExpectedConditions.elementToBeClickable(notificationlink));
		js.executeScript("arguments[0].click();", notificationlink);
	}
}