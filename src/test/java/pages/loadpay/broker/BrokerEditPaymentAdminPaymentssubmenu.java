package pages.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import pages.loadpay.admin.AdminPayByCheck;

public class BrokerEditPaymentAdminPaymentssubmenu extends TestBase {
	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	String paymentStatus = "Verified";
	public String carrierEmail = "";
	String invoiceNum = "";
	String loadId = "";
	String paymentAmount = "";
	String email = "";
	ArrayList<String> tabs;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = null;
	public static ArrayList<String> arraylist;
	AdminPayByCheck adminpaybycheckobj;

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

	/*-------Initializing driver---------*/
	public BrokerEditPaymentAdminPaymentssubmenu() {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		brokerPaymentObj = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
		arraylist = new ArrayList<String>();
		adminpaybycheckobj = new AdminPayByCheck();
	}

	public void loginAsBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);
	}

	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA) throws InterruptedException {

		// Store data-provider elements into publicly-accessible strings
		carrierEmail = cE;
		// invoiceNum = iN;
		loadId = lId;
		paymentAmount = pA;

		// create new payment
		brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();
		Thread.sleep(1000);
		brokerPaymentObj.carrierEmail(carrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.amount(paymentAmount);
		Thread.sleep(1000);
		invoiceNum = brokerPaymentObj.invoiceNumber(iN);
		arraylist.add(invoiceNum);
		Thread.sleep(1000);
		brokerPaymentObj.loadId(loadId);
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePayment();
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePaymenttab();
		Thread.sleep(1000);
		brokerPaymentObj.searchInvoice(invoiceNum);
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

	public void verifyEditableFieldsEnabled() throws InterruptedException {
		js.executeScript("arguments[0].click();", paymenteditbutton);
		Thread.sleep(1000);

		// Verify all editable fields are enabled
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(brokerPaymentObj.getField_CarrierEmail().isEnabled(), "Carrier Email Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PayTo().isEnabled(), "Pay-To Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_CarrierDOT().isEnabled(), "Carrier DOT Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_ScheduleDate().isEnabled(), "Schedule Date Field Disabled!");
		softAssert.assertTrue(!brokerPaymentObj.getField_PaymentAmount().isEnabled(), "Payment Amount Field Enabled!");

		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceNum().isEnabled(), "Invoice # Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_LoadID().isEnabled(), "Load ID Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceRecd().isEnabled(), "Invoice Received Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Memo().isEnabled(), "Memo Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Memo().isEnabled(), "Memo Field Disabled!");

		softAssert.assertAll();
	}

	public void updatePaymentDetails(String updatedCarrierEmail, String updatedInvoiceNumber, String updatedPayTo,
			String updatedLoadID, String updatedCarrierDOT, String updatedScheduleDate, String updatedInvoiceRecd,
			String updatedMemo) throws InterruptedException {
		brokerPaymentObj.setField_CarrierEmail(updatedCarrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.setField_PayTo(updatedPayTo);
		Thread.sleep(1000);
		brokerPaymentObj.setField_CarrierDOT(updatedCarrierDOT);
		Thread.sleep(1000);
		brokerPaymentObj.setField_InvoiceRecd(updatedInvoiceRecd);
		Thread.sleep(1000);
		brokerPaymentObj.setField_ScheduleDate(updatedScheduleDate);
		Thread.sleep(1000);
		brokerPaymentObj.setField_InvoiceNum(updatedInvoiceNumber);
		Thread.sleep(1000);
		brokerPaymentObj.setField_LoadID(updatedLoadID);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Memo(updatedMemo);
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(alertmessage));
		Assert.assertTrue(alertmessage.getText().contains("Updated Successfully!"), "Alert message NOT Displayed");
	}
}
