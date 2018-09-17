package pages.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class BrokerViewCreditLessThan1000 extends TestBase {
	WebDriverWait wait;

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

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/ul/li[1]/a/div/div[1]/div")
	WebElement AvailableCreditTab;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div[3]/span")
	WebElement AvailableCreditText;

	// Initializing the Page Objects:
	public BrokerViewCreditLessThan1000() {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void Brokerlogin(String un, String pwd) {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		// loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void brokerVerificationLogin(String UserName, String NewPassword) {
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		// loginBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}

	public void BrokerLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}

	public void verificationBrokerLogout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_logout));
		btn_logout.click();
	}

	public void forgotPasswordButton() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassword));
		forgotPassword.click();
	}

	public void AvailableCreditTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(AvailableCreditTab));
		AvailableCreditTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(AvailableCreditText));
		String AvailableCredit = AvailableCreditText.getText();
		AvailableCredit = AvailableCredit.replace("$", "");
		AvailableCredit = AvailableCredit.replace(",", "");
		Double dblAvailableCredit = Double.parseDouble(AvailableCredit);
		Assert.assertTrue(dblAvailableCredit < 100.00);

	}

}
