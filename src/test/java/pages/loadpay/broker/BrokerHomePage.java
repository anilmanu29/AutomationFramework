package pages.loadpay.broker;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class BrokerHomePage extends TestBase {

	@FindBy(xpath = "//a[text()='Notifications']")
	public WebElement notificationsLink;

	@FindBy(xpath = "//a[text()='Payment']")
	public WebElement newPaymentLink;

	@FindBy(xpath = "//a[text()='PaymentHistory']")
	public WebElement paymentHistoryLink;

	@FindBy(xpath = "//a[text()='CarrierManagement']")
	public WebElement carrierManagementLink;

	@FindBy(xpath = "//a[text()='MyAccount']")
	public WebElement accountLink;

	@FindBy(xpath = "//a[text()='LogOff']")
	public WebElement logOffLink;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[2]/div/nav/div[1]/div/span")
	public WebElement brokerAccountLabel;

	/**
	 * @return the brokerAccountLabel
	 */
	public WebElement getBrokerAccountLabel() {
		return brokerAccountLabel;
	}

	public void clickBrokerNotificationsLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(notificationsLink));
		notificationsLink.click();
	}

	public void clickBrokerNewPaymentLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(newPaymentLink));
		newPaymentLink.click();
	}

	public void clickBrokerPaymentHistoryLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymentHistoryLink));
		paymentHistoryLink.click();
	}

	public void clickBrokerCarrierManagementLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierManagementLink));
		carrierManagementLink.click();
	}

	public void clickBrokerAccountLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accountLink));
		accountLink.click();
	}

	public void clickBrokerLogOffLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(logOffLink));
		logOffLink.click();
	}

}
