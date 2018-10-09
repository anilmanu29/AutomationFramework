package pages.loadpay.broker;

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

	@FindBy(xpath = "//a[text()='Account']")
	WebElement AccountLink;

	@FindBy(xpath = "//a[contains(text(),'PayMeNow')]")
	WebElement PayMeNowLink;

	@FindBy(xpath = "//*[@id='PMNEnrolled']")
	WebElement EnrollInPayMeNow;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement UpdatePayMeNowButton;

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
		loginBtn.click();

	}

	public void brokerVerificationLogin(String UserName, String NewPassword) {
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		this.UserName.sendKeys(UserName);
		Password.sendKeys(NewPassword);
		loginBtn.click();
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

	public void clickAccountLink() {
		wait.until(ExpectedConditions.elementToBeClickable(AccountLink));
		AccountLink.click();
	}

	public void clickPayMeNowLink() {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowLink));
		PayMeNowLink.click();
	}

	public void enrollInPayMeNow() {
		wait.until(ExpectedConditions.elementToBeClickable(EnrollInPayMeNow));

		if (!EnrollInPayMeNow.isSelected()) {
			EnrollInPayMeNow.click();
			clickUpdatePayMeNow();
		}
	}

	public void clickUpdatePayMeNow() {
		wait.until(ExpectedConditions.elementToBeClickable(UpdatePayMeNowButton));
		UpdatePayMeNowButton.click();
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
