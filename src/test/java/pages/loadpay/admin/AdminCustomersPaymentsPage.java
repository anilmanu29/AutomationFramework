package pages.loadpay.admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;

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
	String recordsUploaded;
	String checkField;

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

	@FindBy(css = "div[ng-click='ExpandingPayment(model);']")
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

	@FindBy(css = "div[class^=alert]")
	public WebElement sucessfulUploadMessage;

	@FindBy(css = "span[class='ng-scope'] b")
	public WebElement checkNumberField;

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
		expandedpayment.click();
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

		// JavascriptExecutor js = (JavascriptExecutor) driver;
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

	public void sucessfulCheckUpload() {
		recordsUploaded = wait.until(ExpectedConditions.visibilityOf(sucessfulUploadMessage))
				.getAttribute("textContent");
		Assert.assertTrue(recordsUploaded.contains("All records have been successfully uploaded."));
	}

	public void getCheckNumberField() {
		checkField = checkNumberField.getAttribute("textContent");
		Assert.assertTrue(checkField.contains("(# 543312)"));

	}
}
