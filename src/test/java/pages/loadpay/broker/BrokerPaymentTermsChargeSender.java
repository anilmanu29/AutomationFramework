package pages.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import testcases.loadpay.broker.BrokerPaymentTermsChargeSenderTest;

public class BrokerPaymentTermsChargeSender extends TestBase {
	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	AdminHomePage ahp;
	AdminLogin adminloginobj;
	String defaultpercentagevalue = "";
	public String numberAsString = "";
	String paymentStatus = "Verified";
	String carrierEmail = "";
	String invoiceNum = "";
	String loadId = "";
	String paymentAmount = "";
	String email = "";
	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	ArrayList<String> tabs;

	@FindBy(xpath = "//a[@href='#/MyAccount']")
	private WebElement accountlink;

	@FindBy(xpath = "//a[contains(text(),'Payment Terms')]")
	private WebElement paymenttermslink;

	@FindBy(id = "PaymentTermsEnrolled")
	public WebElement paymenttermscheckbox;

	@FindBy(xpath = "//label[text()='Charge Sender:']//child::input[@name='PaymentTermChargeType']")
	public WebElement chargesenderradiobutton;
	
	@FindBy(xpath = "//label[text()='Charge Recipient:']//child::input[@name='PaymentTermChargeType']")
	public WebElement chargerecipientradiobutton;
	
	@FindBy(xpath = "//*[@id='formPaymentTerms']/div/div/div[3]/input")
	public WebElement updatebutton;

	@FindBy(xpath = "//input[@value='Enabled']")
	public WebElement adminstatusvalue;

	@FindBy(xpath = "//input[@value='P']")
	public WebElement adminpercentageradiobutton;

	@FindBy(xpath = "//input[@value='F']")
	public WebElement adminflatfeeradiobutton;

	@FindBy(id = "btnEditPaymentTerms")
	public WebElement adminpaymenttermeditbutton;

	@FindBy(xpath = "//input[@value='Sender']")
	public WebElement adminchargesenderfield;

	@FindBy(id = "txtFeesTypeP")
	public WebElement adminpercentagefield;

	@FindBy(id = "txtFeesTypeF")
	public WebElement adminflatfeefield;

	@FindBy(id = "btnUpdatePaymentTerms")
	public WebElement submittbutton;	
	
	@FindBy(id = "BrokerFees")
	public WebElement brokerfee;

	@FindBy(xpath = "//*[@id='PaymentDate']")
	public WebElement scheduledate;

	@FindBy(xpath = "//a[@title='Prev']")
	public WebElement prevdatepicker;

	@FindBy(xpath = "//*[contains(@class, 'ui-datepicker-today')]//following::td[1]")
	public WebElement tomorrowdate;

	/*-------Initializing driver---------*/
	public BrokerPaymentTermsChargeSender() throws IOException {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		brokerPaymentObj = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		ahp = new AdminHomePage();
		adminloginobj = new AdminLogin();
	}

	public void loginAsBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);
	}

	public void activatePaymentTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accountlink));
		js.executeScript("arguments[0].click();", accountlink);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermslink));
		js.executeScript("arguments[0].click();", paymenttermslink);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermscheckbox));
		log.info(paymenttermscheckbox.isSelected());
		if (paymenttermscheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", chargesenderradiobutton);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", updatebutton);

		}

		else {

			js.executeScript("arguments[0].click();", paymenttermscheckbox);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", chargesenderradiobutton);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", updatebutton);
		}

	}

	public void verifyPaymentTermsAdmin(String un, String pwd) throws AWTException, InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		ahp.AdminURL();
		adminloginobj.adminUserPass(un, pwd);
		Thread.sleep(1000);
		adminloginobj.adminLogin();
		Thread.sleep(1000);
		adminloginobj.ClickOnCustomersTab();
		Thread.sleep(1000);
		adminloginobj.ClickOnSearchBox(BrokerPaymentTermsChargeSenderTest.brokeremailid);
		Thread.sleep(1000);
		adminloginobj.ClickOnSearchButton();
		Thread.sleep(1000);
		adminloginobj.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermslink));
		js.executeScript("arguments[0].click();", paymenttermslink);
	}

	public void editPaymentTermsPercentage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(adminpaymenttermeditbutton));
		js.executeScript("arguments[0].click();", adminpaymenttermeditbutton);
		wait.until(ExpectedConditions.elementToBeClickable(adminpercentagefield));
		defaultpercentagevalue = adminpercentagefield.getAttribute("value");
		double d = Double.parseDouble(defaultpercentagevalue);
		double d1 = (d - 0.4);
		numberAsString = Double.toString(d1);
		adminpercentagefield.clear();
		adminpercentagefield.sendKeys(numberAsString);
		wait.until(ExpectedConditions.elementToBeClickable(submittbutton));
		js.executeScript("arguments[0].click();", submittbutton);
		Thread.sleep(1000);
		driver.switchTo().window(tabs.get(0));
		driver.navigate().refresh();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermslink));
		js.executeScript("arguments[0].click();", paymenttermslink);
		Thread.sleep(1000);

	}

	public void editPaymentTermsPercentageFlatFee() throws InterruptedException {
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(adminpaymenttermeditbutton));
		js.executeScript("arguments[0].click();", adminpaymenttermeditbutton);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(adminflatfeeradiobutton));
		js.executeScript("arguments[0].click();", adminflatfeeradiobutton);
		wait.until(ExpectedConditions.elementToBeClickable(adminflatfeefield));
		adminflatfeefield.clear();
		adminflatfeefield.sendKeys(numberAsString);
		wait.until(ExpectedConditions.elementToBeClickable(submittbutton));
		js.executeScript("arguments[0].click();", submittbutton);
		Thread.sleep(1000);
		driver.switchTo().window(tabs.get(0));
		driver.navigate().refresh();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermslink));
		js.executeScript("arguments[0].click();", paymenttermslink);
		Thread.sleep(1000);
	}

	public void resetStatusFlatFeeValuse() throws InterruptedException {
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(adminpaymenttermeditbutton));
		js.executeScript("arguments[0].click();", adminpaymenttermeditbutton);
		wait.until(ExpectedConditions.elementToBeClickable(adminpercentageradiobutton));
		js.executeScript("arguments[0].click();", adminpercentageradiobutton);
		wait.until(ExpectedConditions.elementToBeClickable(adminpercentagefield));
		adminpercentagefield.clear();
		Thread.sleep(1000);
		adminpercentagefield.sendKeys(defaultpercentagevalue);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(submittbutton));
		js.executeScript("arguments[0].click();", submittbutton);
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(tabs.get(0));

	}

	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA) throws InterruptedException {

		// Store data-provider elements into publicly-accessible strings
		carrierEmail = cE;
		invoiceNum = iN;
		loadId = lId;
		paymentAmount = pA;
		// create new payment
		// brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();
		Thread.sleep(1000);
		brokerPaymentObj.carrierEmail(carrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.amount(paymentAmount);
		Thread.sleep(1000);
		brokerPaymentObj.invoiceNumber(invoiceNum);
		Thread.sleep(1000);
		brokerPaymentObj.loadId(loadId);
		Thread.sleep(1000);
		// wait.until(ExpectedConditions.elementToBeClickable(scheduledate));
		act.doubleClick(scheduledate).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(prevdatepicker));
		js.executeScript("arguments[0].click();", prevdatepicker);
		wait.until(ExpectedConditions.elementToBeClickable(tomorrowdate));
		js.executeScript("arguments[0].click();", tomorrowdate);
		brokerPaymentObj.clickShedulePayment();
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePaymenttab();
		Thread.sleep(1000);
		brokerPaymentObj.searchCarrier(carrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		brokerPaymentObj.verifyInvoiceNumber(invoiceNum, paymentAmount);
		Thread.sleep(1000);
		// verify payment status
		Assert.assertTrue(brokerPaymentObj.verifyPaymentStatus().equals(paymentStatus), "Payment Status not equal!");
	}

	public void uncheckEnablePaymentTerms() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(accountlink));
		js.executeScript("arguments[0].click();", accountlink);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermslink));
		js.executeScript("arguments[0].click();", paymenttermslink);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(paymenttermscheckbox));
		if (paymenttermscheckbox.isSelected()) {
			wait.until(ExpectedConditions.elementToBeClickable(chargerecipientradiobutton));
			js.executeScript("arguments[0].click();", chargerecipientradiobutton);
			js.executeScript("arguments[0].click();", paymenttermscheckbox);
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", updatebutton);

		}

		else {
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", updatebutton);
		}
		
		
	}
	
	
	
}
