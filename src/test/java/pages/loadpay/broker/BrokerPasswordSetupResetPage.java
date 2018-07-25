package pages.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerPasswordSetupResetPage extends TestBase {
	WebDriverWait wait;
	JavascriptExecutor js;

	@FindBy(id = "User_Password")
	WebElement newPasswordField;

	@FindBy(id = "User_ConfirmPassword")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@value='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;

	public BrokerPasswordSetupResetPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void enterNewPassword(String NewPassword) {
		wait.until(ExpectedConditions.visibilityOf(newPasswordField));
		js.executeScript("arguments[0].click();", newPasswordField);
		/* newPasswordField.click(); */
		newPasswordField.sendKeys(NewPassword);
	}

	public void confirmNewPassword(String ConfirmPassword) {

		wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));
		confirmPasswordField.click();
		confirmPasswordField.sendKeys(ConfirmPassword);

	}

	public void clickSubmitButton() {
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(loginBtn));

	}

}
