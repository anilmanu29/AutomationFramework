package pages.jira;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class JIRALoginPage extends TestBase {
	private WebDriver driver;
	JavascriptExecutor js;

	@FindBy(id = "login-form-cancel")
	private WebElement cantAccessYourAccountButton;

	@FindBy(id = "login-form-submit")
	private WebElement logInButton;

	@FindBy(id = "login-form-password")
	private WebElement passwordField;

	@FindBy(id = "login-form-remember-me")
	private WebElement rememberMyLoginCheckbox;

	@FindBy(id = "login-form-username")
	private WebElement usernameField;

	public JIRALoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void clickCantAccessYourAccountButton() {
		wait.until(ExpectedConditions.elementToBeClickable(cantAccessYourAccountButton));
		cantAccessYourAccountButton.click();
	}

	public void clicklogInButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();
	}

	/**
	 * @param passwordField
	 *            the passwordField to set
	 */
	public void setPasswordField(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		this.passwordField.click();
		this.passwordField.clear();
		this.passwordField.sendKeys(password);
	}

	/**
	 * @return the rememberMyLoginCheckbox
	 */
	public Boolean getRememberMyLoginCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(rememberMyLoginCheckbox));
		return rememberMyLoginCheckbox.isSelected();
	}

	/**
	 * @param rememberMyLoginCheckbox
	 *            the rememberMyLoginCheckbox to set
	 */
	public void setRememberMyLoginCheckbox(Boolean rememberLogin) {

		wait.until(ExpectedConditions.elementToBeClickable(rememberMyLoginCheckbox));

		if (rememberLogin) {
			if (!getRememberMyLoginCheckbox())
				this.rememberMyLoginCheckbox.click();
		} else {
			if (getRememberMyLoginCheckbox())
				this.rememberMyLoginCheckbox.click();
		}
	}

	/**
	 * @param usernameField
	 *            the usernameField to set
	 */
	public void setUsernameField(String username) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		this.usernameField.click();
		this.usernameField.clear();
		this.usernameField.sendKeys(username);
	}

}
