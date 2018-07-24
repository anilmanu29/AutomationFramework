package pages.loadpay.broker;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerAvailableCreditTab extends TestBase {

	public static String bemail;
	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(xpath = "//*[text()='AVAILABLE CREDIT']")
	WebElement AvailableCreditTab;

	@FindBy(xpath = "//button[text()='REQUEST ADDITIONAL CREDIT']")
	WebElement RequestAdditionalCreditButton;

	@FindBy(xpath = "//button[text()='Close']")
	WebElement CloseButton;

	public BrokerAvailableCreditTab() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
	}

	public void clickAvailableCreditTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(AvailableCreditTab));
		AvailableCreditTab.click();
	}

	public void clickRequestAdditionalCreditButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(RequestAdditionalCreditButton));
		RequestAdditionalCreditButton.click();

	}

	public void clickCloseButton() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(CloseButton));
		CloseButton.click();
	}

}
