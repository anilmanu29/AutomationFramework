package pages.loadpay.carrier;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierNotification extends TestBase {

	@FindBy(xpath = "//a[text()='Notifications']")
	private WebElement lnk_notifications;

	@FindBy(xpath = "//div[@class='title ng-binding ng-scope']")
	private WebElement notificationgrid;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::*[contains(@class,'detailValue invoiceNumber ellipsis col-sm-2')]//child::span")
	private WebElement carrierinvoicedetail;

	@FindBy(xpath = "//div[@aria-expanded='true']//child::*[text()='INVOICE AMOUNT:']")
	private WebElement invoiceamt;

	@FindBy(xpath = ".//*[@id='collapseDetails31568']/div[3]/div[2]/div//child::span/text()")
	private WebElement camount;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement button_login;

	public CarrierNotification() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void clickNotifications() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_notifications));
		lnk_notifications.click();
		wait.until(ExpectedConditions.elementToBeClickable(notificationgrid));
		notificationgrid.click();
	}

	public String carrierInvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierinvoicedetail));
		String cinvoice = carrierinvoicedetail.getText();
		return cinvoice;
	}

}
