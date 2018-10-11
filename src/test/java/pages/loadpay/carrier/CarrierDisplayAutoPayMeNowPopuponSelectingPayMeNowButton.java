package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton extends TestBase {
	CarrierLoginPage carrierLoginObj;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[@id='angularScope']/div[4]/div/div/div[2]")
	private WebElement autopaymenowpopup;

	@FindBy(xpath = "//*[contains(text(), 'SAME DAY ACH')]")
	private WebElement payMeNowSameDayLabel;

	@FindBy(xpath = ".//*[text()='NEXT DAY ACH']")
	private WebElement payMeNowNextDayLabel;

	@FindBy(xpath = "//*[contains(text(), 'WIRE TRANSFER')]")
	private WebElement payMeNowWireLabel;

	@FindBy(xpath = "//*[contains(text(), 'FUEL CARD')]")
	private WebElement payMeNowFuelLabel;

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement popupclosebutton;

	/*-------Initializing driver---------*/
	public CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton() {
		PageFactory.initElements(driver, this);
		carrierLoginObj = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	public void loginAsCarrier(String un, String pwd) throws InterruptedException {
		carrierLoginObj = new CarrierLoginPage();
		carrierLoginObj.Carrierlogin(un, pwd);
	}

	public WebElement getAutoPayMeNowPopup() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(autopaymenowpopup));
		return autopaymenowpopup;
	}

	public WebElement getPayMeNowSameDayACHLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(payMeNowSameDayLabel));
		return payMeNowSameDayLabel;
	}

	public WebElement getPayMeNowNextDayACHLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(payMeNowNextDayLabel));
		return payMeNowNextDayLabel;
	}

	public WebElement getPayMeNowWireLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(payMeNowWireLabel));
		return payMeNowWireLabel;
	}

	public WebElement getPayMeNowFuelLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(payMeNowFuelLabel));
		return payMeNowFuelLabel;
	}

	public void clickPopupCloseButton() {
		wait.until(ExpectedConditions.elementToBeClickable(popupclosebutton));
		popupclosebutton.click();
	}

}