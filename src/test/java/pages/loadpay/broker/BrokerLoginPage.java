package pages.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerLoginPage extends TestBase {
	WebDriverWait wait;
	public static String bemail;

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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void brokerVerificationLogin(String UserName, String NewPassword) {
		bemail = UserName;
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void BrokerLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
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

}
