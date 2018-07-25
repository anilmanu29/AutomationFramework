package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class Carrierlockedaccountcanbeunlockedbyadmin extends TestBase {

	// Page Factory - OR:
	@FindBy(xpath = "//input[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	WebElement loginBtn;
	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;

	// Initializing the Page Objects:
	public Carrierlockedaccountcanbeunlockedbyadmin() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void Carrierloginlock(String un, String pwd, String wrgpwd) throws InterruptedException {
		for (int i = 0; i <= 10; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(UserName));
			UserName.clear();
			UserName.sendKeys(un);
			wait.until(ExpectedConditions.elementToBeClickable(Password));
			Password.clear();
			Password.sendKeys(wrgpwd);
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
			// loginBtn.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", loginBtn);
		}
	}

	public void CarrierLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}
}
