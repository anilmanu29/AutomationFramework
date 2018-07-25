package pages.loadpay.carrier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class CarrierAccountLockedafter10FailedLogins extends TestBase {

	// Page Factory - OR:
	@FindBy(xpath = "//input[@id='UserName']")
	public WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement Password;

	@FindBy(xpath = "//input[contains(@type,'submit')]")
	public WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Logoff']")
	public WebElement btn_logout;

	@FindBy(xpath = "//*[@id='page-main']/div/div/div[2]/div/div/div[1]/form/div[1]/ul/li")
	public WebElement failuremessage;

	@FindBy(xpath = "//*[@id='page-main']/div/div/div[2]/div/div/div[1]/form/div[1]/ul/li")
	public WebElement accountlockedmessage;

	// Initializing the Page Objects:
	public CarrierAccountLockedafter10FailedLogins() {
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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", loginBtn);

			Assert.assertTrue(failuremessage.isDisplayed(), "Failure message is NOT displayed");
		}

		Assert.assertTrue(accountlockedmessage.isDisplayed(), "Account locked message is NOT Displayed");
	}

	public void CarrierLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}
}
