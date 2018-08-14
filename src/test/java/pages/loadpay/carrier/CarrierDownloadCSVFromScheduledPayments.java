package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierDownloadCSVFromScheduledPayments extends TestBase {
	CarrierLoginPage carrierLoginObj;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//a[@href='#/Payments/ScheduledPayments']")
	private WebElement scheduledpaymentstab;

	@FindBy(xpath = "//div[contains(@ng-show, 'ScheduledPayments')]//child::button[@ng-click='Export();']")
	private WebElement exportbutton;

	/*-------Initializing driver---------*/
	public CarrierDownloadCSVFromScheduledPayments() {
		PageFactory.initElements(driver, this);
		carrierLoginObj = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	public void loginAsCarrier(String un, String pwd) {
		carrierLoginObj = new CarrierLoginPage();
		carrierLoginObj.Carrierlogin(un, pwd);
	}

	public WebElement getScheduledPaymenttab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(scheduledpaymentstab));
		return scheduledpaymentstab;
	}

	public void clickScheduledPaymetsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(scheduledpaymentstab));
		js.executeScript("arguments[0].click();", scheduledpaymentstab);
	}

	public WebElement getExportButton() {
		wait.until(ExpectedConditions.elementToBeClickable(exportbutton));
		return exportbutton;
	}

	public void clickExportButton() {
		wait.until(ExpectedConditions.elementToBeClickable(exportbutton));
		js.executeScript("arguments[0].click();", exportbutton);
	}

}
