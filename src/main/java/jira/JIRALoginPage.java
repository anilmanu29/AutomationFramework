package jira;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class JIRALoginPage extends TestBase {

	@FindBy(id = "login-form-cancel")
	public static WebElement cantAccessYourAccountButton;

	@FindBy(id = "login-form-submit")
	public static WebElement logInButton;

	@FindBy(id = "login-form-password")
	public static WebElement passwordField;

	@FindBy(id = "login-form-remember-me")
	public static WebElement rememberMyLoginCheckbox;

	@FindBy(id = "login-form-username")
	public static WebElement usernameField;

	public JIRALoginPage() {
		PageFactory.initElements(driver, this);
	}

	public static void goToJiraLoginPage() {
		String url = prop.getProperty("JiraURL");
		driver.navigate().to(url);
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public static void clickCantAccessYourAccountButton() {
		wait.until(ExpectedConditions.elementToBeClickable(cantAccessYourAccountButton));
		cantAccessYourAccountButton.click();
	}

	public static void clicklogInButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();
	}

	/**
	 * @param passwordField
	 *            the passwordField to set
	 */
	public static void setPasswordField(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	/**
	 * @return the rememberMyLoginCheckbox
	 */
	public static Boolean getRememberMyLoginCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(rememberMyLoginCheckbox));
		return rememberMyLoginCheckbox.isSelected();
	}

	/**
	 * @param rememberMyLoginCheckbox
	 *            the rememberMyLoginCheckbox to set
	 */
	public static void setRememberMyLoginCheckbox(Boolean rememberLogin) {

		wait.until(ExpectedConditions.elementToBeClickable(rememberMyLoginCheckbox));

		if (rememberLogin) {
			if (!getRememberMyLoginCheckbox())
				rememberMyLoginCheckbox.click();
		} else {
			if (getRememberMyLoginCheckbox())
				rememberMyLoginCheckbox.click();
		}
	}

	/**
	 * @param usernameField
	 *            the usernameField to set
	 */
	public static void setUsernameField(String username) {
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		usernameField.click();
		usernameField.clear();
		usernameField.sendKeys(username);
	}

}
