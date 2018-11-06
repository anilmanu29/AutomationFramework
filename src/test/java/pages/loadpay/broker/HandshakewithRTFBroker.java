package pages.loadpay.broker;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class HandshakewithRTFBroker extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	Robot robot;
	BrokerBanking brokerbankingobj;

	@FindBy(id = "txtUName")
	private WebElement usernamefield;

	@FindBy(id = "txtPw")
	private WebElement passwordfield;

	@FindBy(id = "btnSubmit")
	private WebElement submitbutton;

	@FindBy(xpath = "//*[contains(@id,'wm-shoutout')]/div[1]")
	private WebElement popupclosebutton;

	@FindBy(xpath = "//a[text()='Apps']")
	private WebElement appsmenulink;

	@FindBy(xpath = "//*[contains(@id,'sa')][contains(text(),'Account')]")
	private WebElement accountlink;

	@FindBy(xpath = ".//a[text()='LoadPay']")
	private WebElement laodpaylink;

	@FindBy(xpath = "//input[@value='Unlink my LoadPay account']")
	private WebElement unlinkloadpaybutton;

	@FindBy(xpath = ".//input[@value='YES']")
	private WebElement yesbutton;

	@FindBy(id = "btnSignIn")
	private WebElement loginbutton;

	@FindBy(id = "btnSignup")
	private WebElement signupbutton;

	@FindBy(xpath = "//*[@id='formBanking']//child::input[@type='submit']")
	private WebElement registrationbuttonsave;

	@FindBy(id = "UserName")
	private WebElement brokerusernamefield;

	@FindBy(id = "Password")
	private WebElement brokerpasswordfield;

	@FindBy(id = "loginBtn")
	private WebElement brokerloginbutton;

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutlink;

	/*-------PageFactory---------*/
	public HandshakewithRTFBroker() throws AWTException, IOException {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		robot = new Robot();
		brokerbankingobj = new BrokerBanking();
	}

	public void accesRTFAplication() throws InterruptedException {
		driver.get(prop.getProperty("rtfBrokerURL"));
		// Thread.sleep(1000);
	}

	public void setUsername(String un) {
		wait.until(ExpectedConditions.elementToBeClickable(usernamefield));
		usernamefield.clear();
		usernamefield.sendKeys(un);
	}

	public void setPassword(String pwd) {
		wait.until(ExpectedConditions.elementToBeClickable(passwordfield));
		passwordfield.clear();
		passwordfield.sendKeys(pwd);
	}

	public void clickSubmitButton() {
		js.executeScript("arguments[0].click();", submitbutton);
		wait.until(ExpectedConditions.elementToBeClickable(popupclosebutton));
		js.executeScript("arguments[0].click();", popupclosebutton);
	}

	public WebElement getLoadPaylink() {
		wait.until(ExpectedConditions.elementToBeClickable(accountlink));
		act.moveToElement(accountlink).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(appsmenulink));
		act.moveToElement(appsmenulink).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(appsmenulink));
		return laodpaylink;

	}

	public void clickLoadPaylink() {
		wait.until(ExpectedConditions.elementToBeClickable(accountlink));
		act.moveToElement(accountlink).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(appsmenulink));
		act.moveToElement(appsmenulink).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(appsmenulink));
		js.executeScript("arguments[0].click();", laodpaylink);
	}

	public WebElement getUnlinkMyLoadPayAccountbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(unlinkloadpaybutton));
		return unlinkloadpaybutton;
	}

	public void clickUnlinkMyLoadPayAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(unlinkloadpaybutton));
		js.executeScript("arguments[0].click();", unlinkloadpaybutton);
		wait.until(ExpectedConditions.elementToBeClickable(yesbutton));
		js.executeScript("arguments[0].click();", yesbutton);

	}

	public WebElement getLoginbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
		return loginbutton;
	}

	public WebElement getSignUpTodaybutton() {
		wait.until(ExpectedConditions.elementToBeClickable(signupbutton));
		return signupbutton;
	}

	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
		js.executeScript("arguments[0].click();", loginbutton);
	}

	public void setBrokerUserName(String un) {
		wait.until(ExpectedConditions.elementToBeClickable(brokerusernamefield));
		brokerusernamefield.sendKeys(un);
	}

	public void setBrokerPassword(String pwd) {
		wait.until(ExpectedConditions.elementToBeClickable(brokerpasswordfield));
		brokerpasswordfield.sendKeys(pwd);
	}

	public void clickBrokerLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(brokerloginbutton));
		js.executeScript("arguments[0].click();", brokerloginbutton);
	}

	public void clickLogOut() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutlink));
		js.executeScript("arguments[0].click();", logoutlink);

	}

}
