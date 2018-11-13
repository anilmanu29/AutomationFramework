package pages.loadpay.admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import pages.loadpay.broker.BrokerEditPaymentAdminPaymentssubmenu;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminCustomersPaymentsPage extends TestBase {

	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	String paymentStatus = "Verified";
	public String carrierEmail = "";
	String invoiceNum = "";
	String loadId = "";
	String paymentAmount = "";
	String email = "";
	String StorePaymentiD;
	ArrayList<String> tabs;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = null;
	public ArrayList<String> arraylist;
	Actions act = null;

	//// PayByCheckFileUpload
	@FindBy(xpath = "//a[contains(@href, '#/PayByCheckFileUpload' )]")
	WebElement payByCheckFileUploadMenu;

	@FindBy(xpath = "//input[@class='uploadFileButton']")
	WebElement uploadSelectedCheckFile;

	@FindBy(xpath = "//div[@class='list-group']/button")
	List<WebElement> listPayByCheckUploadRecords;

	@FindBy(xpath = "//div[@class='downloadTemplateLink']")
	WebElement downloadPayByCheckFileUploadTemplate;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div/div[2]/div/div/div[1]/div/div[9]/span")
	private WebElement payment;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div/div[2]/div/div/div[1]/div/div[9]/span")
	private WebElement carrierpayment;

	@FindBy(xpath = ".//*[@class='carrierPayment'][@aria-expanded='true']")
	private WebElement expandedpayment;

	@FindBy(xpath = "//i[text()='mode_edit']")
	private WebElement paymenteditbutton;

	@FindBy(xpath = "//div[@role='alert']")
	private WebElement alertmessage;

	@FindBy(xpath = ".//a[contains(text(),'Payments')]")
	public WebElement link_Payments;

	@FindBy(xpath = "//input[@id='searchKeyword']")
	private WebElement FieldSearch;

	@FindBy(xpath = "//a[@target='_blank']")
	private WebElement paymentId;

	/*-------Initializing driver---------*/
	public AdminCustomersPaymentsPage() {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		brokerPaymentObj = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
		arraylist = new ArrayList<String>();
		act = new Actions(driver);

	}

	public void loginAsBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);
	}

	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierEmail = CarrierRegisterTest.carrierUsername;
		} else {
			carrierEmail = cE;
		}

		iN = "NP" + TestUtil.getCurrentDateTime();
		lId = iN;

		// create new payment
		brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();
		brokerPaymentObj.carrierEmail(carrierEmail);
		brokerPaymentObj.amount(pA);
		invoiceNum = brokerPaymentObj.invoiceNumber(iN);
		arraylist.add(invoiceNum);
		brokerPaymentObj.loadId(lId);
		brokerPaymentObj.clickShedulePayment();
		brokerPaymentObj.clickShedulePaymenttab();
		brokerPaymentObj.searchInvoice(invoiceNum);
		brokerPaymentObj.clickSearchButton();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(3000);
		brokerPaymentObj.verifyInvoiceNumber(invoiceNum, paymentAmount);

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
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(payment));
		js.executeScript("arguments[0].click();", payment);
		Thread.sleep(1000);
	}

	public void clickCarrierkPayment() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(carrierpayment));
		carrierpayment.click();
		Thread.sleep(1000);
	}

	public void clickPayments() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_Payments));
		Thread.sleep(1000);
		link_Payments.click();

	}

	public void ClickOnsearchKeyword(String invoice) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(FieldSearch));
		FieldSearch.click();
		FieldSearch.clear();
		FieldSearch.sendKeys(invoice);
		Thread.sleep(2000);
		FieldSearch.sendKeys(Keys.RETURN);
	}

	public WebElement getExpandedPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(expandedpayment));
		return expandedpayment;
	}

	public String getPaymentIdAndStoreItInVariable() {
		StorePaymentiD = paymentId.getText();
		System.out.println(paymentId.getText());
		return paymentId.getText();
	}

	//////
	public void payByCheckFileUploadMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(payByCheckFileUploadMenu));
		payByCheckFileUploadMenu.click();
	}

//	public void uploadSelectedCheckFile() {
//		wait.until(ExpectedConditions.elementToBeClickable(uploadSelectedCheckFile));
//		uploadSelectedCheckFile.click();
//	}

	public int listPayByCheckUpload() {
		wait.until(ExpectedConditions.visibilityOfAllElements(listPayByCheckUploadRecords));
		System.out.println(listPayByCheckUploadRecords.size());
		return listPayByCheckUploadRecords.size();

	}
	

	public void clickUploadFile() {
		wait.until(ExpectedConditions.visibilityOf(downloadPayByCheckFileUploadTemplate));
		downloadPayByCheckFileUploadTemplate.click();
	}
	

	public void downloadPayByCheckFileUploadTemplate() {
		wait.until(ExpectedConditions.visibilityOf(downloadPayByCheckFileUploadTemplate));
		downloadPayByCheckFileUploadTemplate.click();
	}
	
	public void uploadFile() throws IOException, InterruptedException, AWTException {

		// Specify the file location with extension
		StringSelection sel = new StringSelection(prop.getProperty("CheckProcessImportTemplateupdatedPath"));

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);

		//JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", uploadSelectedCheckFile); 

		wait.until(ExpectedConditions.elementToBeClickable(uploadSelectedCheckFile));
		uploadSelectedCheckFile.click();

		Thread.sleep(1000);
		driver.switchTo().activeElement();

		// Create object of Robot class
		Robot robot = new Robot();

		// Press CTRL+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// press tab twice to focus on the Open button
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	public void verifyEditableFieldsEnabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymenteditbutton));
		Thread.sleep(1000);
		paymenteditbutton.click();

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

		softAssert.assertAll();
	}

	public void updatePaymentDetails(String updatedCarrierEmail, String updatedInvoiceNumber, String updatedPayTo,
			String updatedLoadID, String updatedCarrierDOT, String updatedScheduleDate, String updatedInvoiceRecd,
			String updatedMemo) throws InterruptedException {
		brokerPaymentObj.setField_CarrierEmail(updatedCarrierEmail);
		brokerPaymentObj.setField_PayTo(updatedPayTo);
		brokerPaymentObj.setField_CarrierDOT(updatedCarrierDOT);
		brokerPaymentObj.setField_InvoiceRecd(updatedInvoiceRecd);
		brokerPaymentObj.setField_ScheduleDate(updatedScheduleDate);
		brokerPaymentObj.setField_InvoiceNum(updatedInvoiceNumber);
		brokerPaymentObj.setField_LoadID(updatedLoadID);
		brokerPaymentObj.setField_Memo(updatedMemo);
		brokerPaymentObj.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(alertmessage));
		Assert.assertTrue(alertmessage.getText().contains("Updated Successfully!"), "Alert message NOT Displayed");
	}

}
