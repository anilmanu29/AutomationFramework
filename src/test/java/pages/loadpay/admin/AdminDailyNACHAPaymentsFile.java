package pages.loadpay.admin;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;

public class AdminDailyNACHAPaymentsFile extends TestBase {
	BrokerLoginPage brokerLoginObj;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String invoicenumber = "";
	int mincount = 0;
	int maxcount = 30;

	@FindBy(xpath = "//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List<WebElement> List_payment;

	@FindBy(xpath = "//*[@class='getpaid']")
	private List<WebElement> paymenowpayments;

	@FindBy(xpath = "//a[@href='#/DailyPaymentFiles']")
	private WebElement dailynachapaymentfilelink;

	@FindBy(xpath = "//*[text()='Files']")
	private WebElement fileslabel;

	@FindBy(xpath = "//*[contains(text(), 'Select a file from the left to see its content')]")
	private WebElement dailynachalabel;

	@FindBy(xpath = "//img[@src='/content/images/spinner.gif']")
	private WebElement spinnerimage;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div/button[1]")
	private WebElement filebutton;

	@FindBy(xpath = "//a[contains(@ng-click,'GenerateNACHABatchPaymentsFile')]")
	private WebElement downloadascsvlink;

	/*-------Initializing driver---------*/
	public AdminDailyNACHAPaymentsFile() {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	public WebElement getDailyNACHAPaymentFiles() {
		wait.until(ExpectedConditions.elementToBeClickable(dailynachapaymentfilelink));
		return dailynachapaymentfilelink;
	}

	public void clickDailyNACHAPaymentFile() {
		wait.until(ExpectedConditions.elementToBeClickable(dailynachapaymentfilelink));
		dailynachapaymentfilelink.click();
	}

	public WebElement getFilesLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(fileslabel));
		return fileslabel;
	}

	public WebElement getNACHAFilesLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(dailynachalabel));
		return dailynachalabel;
	}

	public void clickNACHAFileButon() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(filebutton));
		filebutton.click();
		wait.until(ExpectedConditions.visibilityOf(spinnerimage));
		while (spinnerimage.isDisplayed() && (mincount < maxcount)) {
			// Thread.sleep(1000);
			log.info("Spinner image is displayed");
		}
	}

	public WebElement getDownloadCSVLink() {
		return downloadascsvlink;
	}

	public void clickDownloadCSVLink() {
		wait.until(ExpectedConditions.elementToBeClickable(filebutton));
		downloadascsvlink.click();
	}
}
