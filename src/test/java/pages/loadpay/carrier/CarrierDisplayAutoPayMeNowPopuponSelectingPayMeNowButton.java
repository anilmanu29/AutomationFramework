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

	@FindBy(xpath = "//*[contains(text(), 'Same Day ACH* PayMeNow')]")
	private WebElement paymenowsamedayachlabel;

	@FindBy(xpath = ".//*[text()='Next Day ACH* PayMeNow']")
	private WebElement paymenownextdayachlabel;

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement popupclosebutton;

	/*-------Initializing driver---------*/
	public CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton() {
		PageFactory.initElements(driver, this);
		carrierLoginObj = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	public void loginAsCarrier(String un, String pwd) {
		carrierLoginObj = new CarrierLoginPage();
		carrierLoginObj.Carrierlogin(un, pwd);
	}

	public WebElement getAutoPayMeNowPopup() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(autopaymenowpopup));
		return autopaymenowpopup;
	}

	public WebElement getPayMeNowSameDayACHLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(paymenowsamedayachlabel));
		return paymenowsamedayachlabel;
	}

	public WebElement getPayMeNowNextDayACHLabel() {
		wait.until(ExpectedConditions.elementToBeClickable(paymenownextdayachlabel));
		return paymenownextdayachlabel;
	}

	public void clickPopupCloseButton() {
		wait.until(ExpectedConditions.elementToBeClickable(popupclosebutton));
		popupclosebutton.click();
	}

}