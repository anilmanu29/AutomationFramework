package pages.loadpay.carrier;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class HandshakewithRTFCarrier extends TestBase {
	WebDriverWait wait;
	public static String cemail;
	Actions act;

	// Page Factory - OR:
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='txtUName']")
	WebElement Enter_RTFUsername;

	@FindBy(xpath = "//input[@id='txtPw']")
	WebElement Enter_RTFPassword;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[@id='btnSubmit']")
	public WebElement Click_RTFSubmit;

	@FindBy(linkText = "Account")
	WebElement click_Account;

	/*
	 * @FindBy(xpath=".//*[@id='ca7']") WebElement click_Account;
	 */

	@FindBy(xpath = ".//*[@id='btnSignIn']")
	public WebElement Click_Login;

	@FindBy(xpath = ".//*[@id='ctl00_ContentPlaceHolder_nl_cmdSubmit']")
	public WebElement Click_accept;

	@FindBy(xpath = ".//*[@id='ctl00_ContentPlaceHolder_nl_btnSaveSetup']")
	public WebElement Click_UnlinkMyUploadAccount;

	@FindBy(xpath = ".//*[@id='nl_toptablerightfourbuttons_new']/tbody/tr[1]/td[4]/a")
	public WebElement Click_rtflogout;

	@FindBy(linkText = "LoadPay")
	WebElement click_LoadPay;

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

	// Initializing the Page Objects:
	public HandshakewithRTFCarrier() {
		PageFactory.initElements(driver, this);
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
		einField.clear();
		Thread.sleep(1000);
		einField.sendKeys(EIN);
	}

	public void clickEinNextButton() {
		einNextButton.click();
	}

	/**
	 * @return the termsAndConditionsCheckBox
	 */
	public WebElement getTermsAndConditionsCheckBox() {
		return termsAndConditionsCheckBox;
	}

	public void clickTermsAndConditionsCheckBox() {
		termsAndConditionsCheckBox.click();
	}

	/**
	 * @return the finishButton
	 */
	public WebElement getFinishButton() {
		return finishButton;
	}

	public void clickFinishButton() {
		finishButton.click();
	}

	/**
	 * @return the confirmationPopup
	 */
	public WebElement getConfirmationPopup() {
		return confirmationPopup;
	}

	public void clickConfirmationPopupCloseButton() {
		confirmationPopupCloseBtn.click();
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void Carrierlogin(String un, String pwd) {
		cemail = un;

		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		// loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void carrierVerificationLogin(String UserName, String NewPassword) {
		cemail = UserName;
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		// loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void clickAccount() throws InterruptedException {

		WebElement element = driver.findElement(By.linkText("Account"));

		Actions action = new Actions(driver);

		action.moveToElement(element).build().perform();

		driver.findElement(By.linkText("Load Pay")).click();

		/*
		 * Actions action = new Actions(driver); WebElement mainMenu =
		 * driver.findElement(By.linkText("Account"));
		 * action.moveToElement(mainMenu).moveToElement(driver.findElement(By.
		 * linkText("Load Pay"))).click().build().perform();
		 */

	}

	public void RTFCarrierlogin(String un, String pwd) {

		Enter_RTFUsername.sendKeys(un);
		Enter_RTFPassword.sendKeys(pwd);
		// loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Click_RTFSubmit);
	}

	public void CarrierLogout() {
		btn_logout.click();
	}

	public void verificationCarrierLogout() throws InterruptedException {
		// wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		Thread.sleep(3000);
		btn_logout.click();
	}

	public void click_LoginButton() throws InterruptedException {
		/* wait.until(ExpectedConditions.elementToBeClickable(Click_Login)); */
		Click_Login.click();
	}

	public void click_alert() throws InterruptedException {
		/* wait.until(ExpectedConditions.elementToBeClickable(Click_accept)); */
		Click_accept.click();
	}

	public void clickRtflogout() throws InterruptedException {
		/* wait.until(ExpectedConditions.elementToBeClickable(Click_accept)); */
		Click_rtflogout.click();
	}

	public void clickUnlinkMyUploadAccount() throws InterruptedException {
		/*
		 * wait.until(ExpectedConditions.elementToBeClickable(
		 * Click_UnlinkMyUploadAccount));
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Click_UnlinkMyUploadAccount);

	}

	public void forgotPasswordButton() {
		forgotPassword.click();
	}

}
