package pages.loadpay.broker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerBulkUploadPaymentsmatchedCarrier extends TestBase {
	String payment_status = "Verified";
	String payment_statuss = "matched:";
	String load;
	String scheduledamount;
	public static float totalamounnt;
	String schedule;
	String scheduleamt;
	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;

	@FindBy(xpath = "//a[text()='New Payment']")
	public WebElement lnk_newpayment;

	@FindBy(xpath = "//div[@class='total ng-scope notlast col-sm-6']//child::span[1]")
	public WebElement tab_shedulepayment;

	@FindBy(xpath = "//div[@class='total ng-scope']//child::span[1]")
	public WebElement creditvalue;

	@FindBy(xpath = ".//*[@id='dropzoneForm']")
	public WebElement link_Upload;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(xpath = "//input[contains(@value,'Import')]")
	public WebElement btn_import;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/ul/li[2]/a")
	public WebElement link_schpaymnt;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	public WebElement link_griddown;

	@FindBy(xpath = "//a[contains(text(),'Anticipated Pull Date')]")
	public WebElement click_pulldate;

	@FindBy(xpath = "//a[contains(text(),'Pay To Date')]")
	public WebElement click_PayToDate;

	@FindBy(xpath = "//a[contains(text(),'Amount')]")
	public WebElement click_Amount;

	@FindBy(xpath = "//a[@ng-click='SortBy(field)']")
	public WebElement click_Carrier;

	@FindBy(xpath = "//a[contains(text(),'Invoice #')]")
	public WebElement click_InvoiceID;

	@FindBy(xpath = "//a[contains(text(),'Load ID')]")
	public WebElement Click_LoadID;

	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement searchButton;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement expandCollapseFirstRow;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[6]/span")
	public WebElement invoice;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[9]/div/div[1]/div/div[4]/div/span")
	public WebElement CompanyName;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[3]")
	public WebElement Amount;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[2]/div/div[2]")
	public WebElement PayToDate;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[2]/div/div[1]/i")
	public WebElement AnticipatedPullDate;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]")
	public WebElement invoiceNum;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[6]")
	public WebElement LoadIDNum;

	/*-------PageFactory---------*/

	public BrokerBulkUploadPaymentsmatchedCarrier() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	/*-------New Payment---------*/
	public void newPayment() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_newpayment));
		lnk_newpayment.click();
	}

	public void ClickUpload() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(link_Upload));
		link_Upload.click();
	}

	public void UploadFile() throws IOException, InterruptedException, AWTException {

		// Specify the file location with extension
		StringSelection sel = new StringSelection(prop.getProperty("CarrierMatchedBulkUploadPath"));

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		log.info("selection" + sel);

		wait.until(ExpectedConditions.elementToBeClickable(link_Upload));
		link_Upload.click();

		// Create object of Robot class
		Robot robot = new Robot();

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Press CTRL+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		/*
		 * driver.findElement(By.xpath(".//*[@id='dropzoneForm']/div/div")).click();
		 * Runtime.getRuntime().exec(System.getProperty("user.dir")+
		 * "\\AutoItScripts\\FileUpload.exe"); //logger.debug("Bulk uploadfiles");
		 */

	}

	public void UploadFileforError() throws IOException, InterruptedException, AWTException {

		// Specify the file location with extension
		StringSelection sel = new StringSelection(prop.getProperty("BrokerBulkUploadPaymentErrorsPath"));

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		log.info("selection" + sel);

		wait.until(ExpectedConditions.elementToBeClickable(link_Upload));
		link_Upload.click();

		// Create object of Robot class
		Robot robot = new Robot();

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Press CTRL+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

	public void Clickimport() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_import));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_import);

	}

	public void Clickschpayment() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(link_schpaymnt));
		link_schpaymnt.click();
	}

	public void ClickGridDown() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(link_griddown));
		link_griddown.click();

	}

	public void clickAnticipatedPullDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_pulldate));
		click_pulldate.click();
	}

	public void clickPayToDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_PayToDate));
		click_PayToDate.click();
	}

	public void clickAmount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Amount));
		click_Amount.click();
	}

	public void clickCarrier() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Carrier));
		click_Carrier.click();
	}

	public void clickinvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_InvoiceID));
		click_InvoiceID.click();
	}

	public void clickLoadID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_LoadID));
		Click_LoadID.click();
	}

	public void enterSearchText(String searchText) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
		searchInputField.clear();
		searchInputField.sendKeys(searchText);
	}

	public void clickSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		js.executeScript("arguments[0].click();", searchButton);
	}

	public void clickFirstRow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(expandCollapseFirstRow));
		js.executeScript("arguments[0].click();", expandCollapseFirstRow);
	}

	public List<String> getFirstRowData() throws InterruptedException {
		List<String> dataElements = new ArrayList<String>();
		List<WebElement> webElements = driver.findElements(
				By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div"));

		for (WebElement element : webElements) {

			dataElements.add(element.getText());
		}

		return dataElements;
	}

}
