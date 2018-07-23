package pages.loadpay.admin;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class AdminLogin extends TestBase {
	Select s;

	@FindBy(xpath = ".//*[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = ".//*[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "html/body/div[1]/div/div/div[2]/form/div[4]/input")
	WebElement loginBtn;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[8]/a")
	WebElement logOut;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[3]/a")
	public WebElement CustomerTab;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr/td[1]/a")
	WebElement CustomerId;

	@FindBy(xpath = ".//*[@id='searchKeyword']")
	WebElement search;

	@FindBy(xpath = ".//*[@id='angularScope']/div[3]/div/div/div[2]/button[2]")
	WebElement click_AdminResetpwdConfirm;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement ClickonSearchButton;

	@FindBy(xpath = "//a[contains(@class,'ng-binding')]")
	public WebElement DoubleClickID;

	@FindBy(xpath = ".//*[@id='CustomerStatusId']")
	WebElement CustomersatatusIdDropDown;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]")
	WebElement editloginuser;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[6]/input")
	WebElement CancelLockout;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[7]/div/i")
	WebElement clickAdmin_ResetPassword;

	@FindBy(xpath = ".//*[@id='formCompany']/div[2]/input")
	WebElement updateButton;

	@FindBy(xpath = "//*[@id='ExtendedCredit']")
	WebElement ExtendedCredit;

	@FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	WebElement CreditSubmit;

	@FindBy(xpath = ("//a[contains(text(),'Credit')]"))
	WebElement CreditTab;

	public AdminLogin() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void adminUserPass(String Username, String pass) throws InterruptedException {
		UserName.sendKeys(Username);
		Password.sendKeys(pass);

	}

	public void adminLogin() throws InterruptedException {
		loginBtn.click();
	}

	public void AdminLogOut() throws InterruptedException {
		logOut.click();
	}

	public void ClickOnCreditTab() throws InterruptedException {
		CreditTab.click();
	}

	public void ClickOnCreditSubmitButton() throws InterruptedException {
		CreditSubmit.click();
	}

	public void EnterExtendedCredit(String CreditAmount) throws InterruptedException {
		ExtendedCredit.click();
		ExtendedCredit.clear();
		ExtendedCredit.sendKeys(CreditAmount);
	}

	public void ClickOnCustomersTab() throws InterruptedException {
		CustomerTab.click();
	}

	public void ClickOnSearchBox(String keyword) throws InterruptedException {
		search.click();
		search.sendKeys(keyword);
	}

	public void ClickOnSearchButton() throws InterruptedException {
		ClickonSearchButton.click();
	}

	public void DoubleClickID() throws InterruptedException {
		DoubleClickID.click();
	}

	public void clickCustomerId() throws InterruptedException {
		CustomerId.click();
	}

	public void clickeditloginuser() throws InterruptedException {
		editloginuser.click();
	}

	public void click_AdminResetPassword() throws InterruptedException {
		clickAdmin_ResetPassword.click();
	}

	public void clickAdmin_ResetpwdConfirm() throws InterruptedException {
		click_AdminResetpwdConfirm.click();
	}

	public void clickCancelLockout() throws InterruptedException {
		CancelLockout.click();
	}

	public void StatusIDDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(getCustomersatatusIdDropDown()));
		CustomersatatusIdDropDown.click();
		s = new Select((CustomersatatusIdDropDown));
		/* s.deselectByVisibleText("Active"); */
		s.selectByVisibleText("Active");
	}

	public void UpdateButton() throws InterruptedException {
		updateButton.click();
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return UserName;
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return Password;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * @return the logOut
	 */
	public WebElement getLogOut() {
		return logOut;
	}

	/**
	 * @return the customerTab
	 */
	public WebElement getCustomerTab() {
		return CustomerTab;
	}

	/**
	 * @return the customerId
	 */
	public WebElement getCustomerId() {
		return CustomerId;
	}

	/**
	 * @return the search
	 */
	public WebElement getSearch() {
		return search;
	}

	/**
	 * @return the click_AdminResetpwdConfirm
	 */
	public WebElement getClick_AdminResetpwdConfirm() {
		return click_AdminResetpwdConfirm;
	}

	/**
	 * @return the clickonSearchButton
	 */
	public WebElement getClickonSearchButton() {
		return ClickonSearchButton;
	}

	/**
	 * @return the doubleClickID
	 */
	public WebElement getDoubleClickID() {
		return DoubleClickID;
	}

	/**
	 * @return the customersatatusIdDropDown
	 */
	public WebElement getCustomersatatusIdDropDown() {
		return CustomersatatusIdDropDown;
	}

	/**
	 * @return the editloginuser
	 */
	public WebElement getEditloginuser() {
		return editloginuser;
	}

	/**
	 * @return the cancelLockout
	 */
	public WebElement getCancelLockout() {
		return CancelLockout;
	}

	/**
	 * @return the clickAdmin_ResetPassword
	 */
	public WebElement getClickAdmin_ResetPassword() {
		return clickAdmin_ResetPassword;
	}

	/**
	 * @return the updateButton
	 */
	public WebElement getUpdateButton() {
		return updateButton;
	}
}