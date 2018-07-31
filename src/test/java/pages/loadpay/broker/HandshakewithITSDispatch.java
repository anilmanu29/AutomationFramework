package pages.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class HandshakewithITSDispatch extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	JavascriptExecutor js;
	ArrayList<String> tab;

	@FindBy(id = "account_numberlgn")
	private WebElement accountidfield;

	@FindBy(id = "usernamelgn")
	private WebElement usernamefield;

	@FindBy(id = "passwordlgn")
	private WebElement passwordfield;

	@FindBy(xpath = "//button[text()='GO']")
	private WebElement gobutton;

	@FindBy(xpath = "//div[@id='foot']//child::img[@alt='LoadPay']")
	private WebElement loadpayimage;

	@FindBy(xpath = ".//*[text()='Close']")
	private WebElement popupclosebutton;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loadpayaccountloginbutton;

	@FindBy(xpath = "//input[@value='Close']")
	private WebElement loadpayaccountcancelbutton;

	@FindBy(xpath = "//*[@class='lp-logo']")
	private WebElement loadpaylogo;

	@FindBy(id = "UserName")
	private WebElement loadpayusernamefield;

	@FindBy(id = "Password")
	private WebElement loadpaypasswordfield;

	@FindBy(id = "loginBtn")
	private WebElement login;

	@FindBy(xpath = "//input[@value='Disconnect']")
	private WebElement disconnectbutton;

	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement logoutbutton;

	/*-------PageFactory---------*/
	public HandshakewithITSDispatch() throws AWTException, IOException {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 20);
		act = new Actions(driver);
	}

	public void accesITSDispatchAplication() throws InterruptedException {
		driver.get(prop.getProperty("ITSDispatchurl"));
		// Thread.sleep(1000);
	}

	public void setAccountNameField(String accountid) {
		wait.until(ExpectedConditions.elementToBeClickable(accountidfield));
		accountidfield.clear();
		accountidfield.sendKeys(accountid);
	}

	public void setUsername(String username) {
		wait.until(ExpectedConditions.elementToBeClickable(usernamefield));
		usernamefield.clear();
		usernamefield.sendKeys(username);
	}

	public void setPassword(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(passwordfield));
		passwordfield.clear();
		passwordfield.sendKeys(password);
	}

	public void clickGoButton() {
		js.executeScript("arguments[0].click();", gobutton);
	}

	public void clickCloseButton() {
		wait.until(ExpectedConditions.elementToBeClickable(popupclosebutton));
		js.executeScript("arguments[0].click();", popupclosebutton);
	}

	public WebElement getLoadPayImage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadpayimage));
		return loadpayimage;
	}

	public void clickLoadPayImage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loadpayimage));
		act.moveToElement(loadpayimage).click().perform();
	}

	public WebElement getLoadPayLoginButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().frame(1);
		wait.until(ExpectedConditions.elementToBeClickable(loadpayaccountloginbutton));
		return loadpayaccountloginbutton;
	}

	public WebElement getLoadPayCancelButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loadpayaccountcancelbutton));
		return loadpayaccountcancelbutton;
	}

	public WebElement getLoadPayLogo() {
		wait.until(ExpectedConditions.elementToBeClickable(loadpaylogo));
		return loadpaylogo;
	}

	public void clickLoadPayLoginButton() {
		js.executeScript("arguments[0].click();", loadpayaccountloginbutton);
		driver.switchTo().defaultContent();
	}

	public void switchtoNewTab(int index) {
		tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(index));
	}

	public void setLoadPayUsername(String username) {
		wait.until(ExpectedConditions.elementToBeClickable(loadpayusernamefield));
		loadpayusernamefield.clear();
		loadpayusernamefield.sendKeys(username);
	}

	public void setLoadPayPassword(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(loadpaypasswordfield));
		loadpaypasswordfield.clear();
		loadpaypasswordfield.sendKeys(password);
	}

	public void clickLogin() {
		js.executeScript("arguments[0].click();", login);
	}

	public WebElement getDisconnectButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().frame(1);
		wait.until(ExpectedConditions.elementToBeClickable(disconnectbutton));
		return disconnectbutton;
	}

	public void clickDisconnectbutton() {
		js.executeScript("arguments[0].click();", disconnectbutton);
		driver.switchTo().defaultContent();
	}

	public void clickLogOutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutbutton));
		js.executeScript("arguments[0].click();", logoutbutton);
		driver.switchTo().alert().accept();
	}

}
