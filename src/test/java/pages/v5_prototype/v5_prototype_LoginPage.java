package pages.v5_prototype;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class v5_prototype_LoginPage extends TestBase {
	public Map<String, String> data;
	WebDriverWait wait;
	public int timeout = 15;

	@FindBy(id = "mat-input-0")
	public WebElement emailAddress;

	@FindBy(css = "a.tc-gray-800")
	public WebElement forgotPassword;

	@FindBy(css = "button.mat-raised-button.mat-accent")
	public WebElement login;

	@FindBy(id = "mat-input-1")
	public WebElement password;

	@FindBy(id = "mat-checkbox-1-input")
	public WebElement rememberMe;

	@FindBy(xpath = "/html/body/app-root/ts-login/div/div/div")
	public WebElement loginLabel;

	@FindBy(xpath = "//*[@id='mat-dialog-0']/ts-safe-harbor-dialog/div/div/ts-button")
	public WebElement acknowledgeButton;

	public final String pageLoadedText = "Truckstop Login";

	public final String pageUrl = "/login";

	public v5_prototype_LoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	/**
	 * Click on Forgot Password Link.
	 *
	 * @return the loginPage class instance.
	 */
	public void clickForgotPasswordLink() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassword));
		forgotPassword.click();
	}

	/**
	 * Click on Login Button.
	 *
	 * @return the loginPage class instance.
	 */
	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(login));
		login.click();
	}

	/**
	 * Set value to Email Address Text field.
	 *
	 * @return the loginPage class instance.
	 */
	public void setEmailAddressTextField(String emailAddressValue) {
		wait.until(ExpectedConditions.elementToBeClickable(emailAddress));
		emailAddress.sendKeys(emailAddressValue);
	}

	/**
	 * Set value to Password Password field.
	 *
	 * @return the loginPage class instance.
	 */
	public void setPasswordPasswordField(String passwordValue) {
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.sendKeys(passwordValue);
	}

	/**
	 * Set Remember Me Checkbox field.
	 *
	 * @return the loginPage class instance.
	 */
	public void setRememberMeCheckboxField() {
		wait.until(ExpectedConditions.elementToBeClickable(rememberMe));

		if (!rememberMe.isSelected()) {
			rememberMe.click();
		}
	}

	/**
	 * Unset Remember Me Checkbox field.
	 *
	 * @return the loginPage class instance.
	 */
	public void unsetRememberMeCheckboxField() {
		wait.until(ExpectedConditions.elementToBeClickable(rememberMe));

		if (rememberMe.isSelected()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", rememberMe);
		}
	}

	/**
	 * Verify that the page loaded completely.
	 *
	 * @return the loginPage class instance.
	 */
	public Boolean verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(loginLabel));

		return driver.getPageSource().contains(pageLoadedText);
	}

	/**
	 * Verify that current page URL matches the expected URL.
	 *
	 * @return the loginPage class instance.
	 */
	public Boolean verifyPageUrl() {
		return driver.getCurrentUrl().contains(pageUrl);

	}

	public Boolean isAcknowledgeButtonDisplayed() {
		wait.until(ExpectedConditions.elementToBeClickable(acknowledgeButton));

		return acknowledgeButton.isDisplayed();
	}

	public void clickAcknowledgeButton() {
		wait.until(ExpectedConditions.elementToBeClickable(acknowledgeButton));

		acknowledgeButton.click();
	}
}
