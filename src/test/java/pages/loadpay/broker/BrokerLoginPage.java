package pages.loadpay.broker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import testcases.loadpay.admin.AdminBrokerCanadaTest;
import testcases.loadpay.broker.BrokerRegisterTest;

public class BrokerLoginPage extends TestBase {
	WebDriverWait wait;
	public static String bemail;
	public static Boolean isCanadaTest = false;

	// Page Factory - OR:
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	@FindBy(xpath = ("//a[contains(text(), 'Forgot Password?')]"))
	WebElement forgotPassword;

	// Initializing the Page Objects:
	public BrokerLoginPage() {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void Brokerlogin(String un, String pwd) {
		bemail = un;
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
	}

	public void brokerVerificationLogin(String UserName, String NewPassword) {
		bemail = UserName;
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}

	public void BrokerLogout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		Thread.sleep(2000);
		btn_logout.click();
	}

	public void forgotPasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassword));
		forgotPassword.click();
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return UserName;
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return Password;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * @return the btn_logout
	 */
	public WebElement getBtn_logout() {
		return btn_logout;
	}

	/**
	 * @return the forgotPassword
	 */
	public WebElement getForgotPassword() {
		return forgotPassword;
	}

	public void completeRegistration() {
		// enter EIN
		WebElement einInputField = driver.findElement(By.xpath("//*[@id='EIN']"));
		wait.until(ExpectedConditions.elementToBeClickable(einInputField));
		einInputField.clear();
		einInputField.sendKeys("99-9999999");

		// enter deposit amount
		WebElement depositAmtField = driver.findElement(By.xpath("//*[@id='ControlAmount']"));
		wait.until(ExpectedConditions.elementToBeClickable(depositAmtField));
		depositAmtField.clear();

		if (isCanadaTest) {
			depositAmtField.sendKeys(AdminBrokerCanadaTest.depositAmount);
		} else {
			depositAmtField.sendKeys(BrokerRegisterTest.depositAmount);
		}

		// click Next
		WebElement nextButton = driver.findElement(By.xpath("//*[@id='formCompany']/input"));
		wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		nextButton.click();

		// accept terms and conditions
		WebElement acceptTermsCheckBox = driver.findElement(By.xpath("//*[@id='AcceptedTerms']"));
		wait.until(ExpectedConditions.elementToBeClickable(acceptTermsCheckBox));
		acceptTermsCheckBox.click();

		WebElement finishButton = driver.findElement(By.xpath("//*[@id='termsForm']/input"));
		wait.until(ExpectedConditions.elementToBeClickable(finishButton));
		finishButton.click();

		WebElement confirmationPopup = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[1]/div/p"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmationPopup));
		log.info("Confirmation message: " + confirmationPopup.getText());

		Assert.assertTrue(confirmationPopup.getText().contains("registration has been completed successfully."),
				"Registration success message not found");

		WebElement confirmationPopupClose = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[2]/button"));
		confirmationPopupClose.click();
	}

	public void isCanadaTest(Boolean setCanadaFlag) {
		if (setCanadaFlag)
			isCanadaTest = true;
		else
			isCanadaTest = false;
	}

}
