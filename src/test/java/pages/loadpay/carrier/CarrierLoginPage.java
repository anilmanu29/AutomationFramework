package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierLoginPage extends TestBase {
	WebDriverWait wait;
	public String cemail;
	JavascriptExecutor js;

	// Page Factory - OR:
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = "//*[@id='AcceptedTerms']")
	WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//*[@id='termsForm']/input")
	WebElement finishButton;

	@FindBy(xpath = "//*[@id='angularScope']/div[3]/div/div/div[1]/div/p")
	WebElement confirmationPopup;

	@FindBy(xpath = "//*[@id='angularScope']/div[3]/div/div/div[2]/button")
	WebElement confirmationPopupCloseBtn;

	@FindBy(xpath = "//*[@id='EIN']")
	WebElement einField;

	@FindBy(xpath = "//*[@id='formCompany']/input")
	WebElement einNextButton;

	@FindBy(xpath = ("//a[contains(text(), 'Forgot Password?')]"))
	WebElement forgotPassword;

	@FindBy(xpath = "//input[contains(@ng-click, 'DoNotShowAutomaticPMNOptinChanged')]")
	WebElement donotshowagaincheckbox;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement savebutton;

	// Initializing the Page Objects:
	public CarrierLoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	// Actions:

	/**
	 * @return the einField
	 */
	public WebElement getEinField() {
		return einField;
	}

	/**
	 * @param einField
	 *            the einField to set
	 * @throws InterruptedException
	 */
	public void setEinField(String EIN) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(einField));
		einField.clear();
		einField.sendKeys(EIN);
	}

	public void clickEinNextButton() {
		wait.until(ExpectedConditions.elementToBeClickable(einNextButton));
		einNextButton.click();
	}

	/**
	 * @return the termsAndConditionsCheckBox
	 */
	public WebElement getTermsAndConditionsCheckBox() {
		return termsAndConditionsCheckBox;
	}

	public void clickTermsAndConditionsCheckBox() {
		wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckBox));
		termsAndConditionsCheckBox.click();
	}

	/**
	 * @return the finishButton
	 */
	public WebElement getFinishButton() {
		return finishButton;
	}

	public void clickFinishButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(finishButton));
		finishButton.click();
		Thread.sleep(1000);
	}

	/**
	 * @return the confirmationPopup
	 */
	public WebElement getConfirmationPopup() {
		return confirmationPopup;
	}

	public void clickConfirmationPopupCloseButton() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmationPopupCloseBtn));
		confirmationPopupCloseBtn.click();
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void Carrierlogin(String un, String pwd) {
		cemail = un;
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		// loginBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
	}

	public void carrierVerificationLogin(String UserName, String NewPassword) {
		cemail = UserName;
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}

	public void CarrierLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}

	public void verificationCarrierLogout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}

	public void forgotPasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassword));
		forgotPassword.click();
	}

	public void closePaymeNowPopUp() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(donotshowagaincheckbox));
			if (donotshowagaincheckbox.isDisplayed()) {
				donotshowagaincheckbox.click();
				wait.until(ExpectedConditions.elementToBeClickable(savebutton));
				savebutton.click();
			}
		} catch (Exception e) {
			log.info("Pop is NOT displayed");
		}
	}

	public WebElement getPayMeNowPopupSaveButton() {
		return savebutton;
	}

	/**
	 * @return the donotshowagaincheckbox
	 */
	public WebElement getDonotshowagaincheckbox() {
		return donotshowagaincheckbox;
	}

}
