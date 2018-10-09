package pages.loadpay.broker;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class ShipperCompleteAccountModule extends TestBase {
	WebDriverWait wait = null;
	ArrayList<String> tabs;
	String amount;
	String pennyamount;
	String pennyamountt;
	int j = 0;
	String pennyamt;
	String pennyamounts;
	String pennyamoun;

	@FindBy(xpath = "//a[text()='Account']")
	public WebElement lnk_account;

	@FindBy(xpath = "//a[contains(text(),'Company')]")
	public WebElement lnk_Company;

	@FindBy(xpath = "//input[@name='DOT']")
	public WebElement dotnum;

	@FindBy(xpath = "//input[@name='EIN']")
	public WebElement einnum;

	@FindBy(xpath = "//*[@id='MCType']")
	WebElement motorCarrierSelector;

	@FindBy(xpath = "//*[@id='MC']")
	WebElement motorCarrierField;

	@FindBy(xpath = "//*[@id='formCompany']/div/div[15]/input")
	public WebElement companyupdate;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	public WebElement lnk_Contact;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/a")
	public WebElement clickAddNewContact;

	@FindBy(xpath = "//input[@id='ContactFirstName']")
	public WebElement ContactFirstName;

	@FindBy(xpath = ".//*[@id='PMNTerm']")
	public WebElement click_PaymentTerm;

	@FindBy(xpath = "//*[@id='Contact_LastName']")
	public WebElement Contact_LastName;

	@FindBy(xpath = "//*[@id='Contact_Email']")
	public WebElement Contact_Email;

	@FindBy(xpath = "//*[@id='Contact_Phone']")
	public WebElement Contact_Phone;

	@FindBy(xpath = "//*[@id='Contact_Ext']")
	public WebElement Contact_Ext;

	@FindBy(xpath = "//*[@id='Contact_Mobile']")
	public WebElement Contact_Mobile;

	@FindBy(xpath = "//*[@id='Contact_Fax']")
	public WebElement Contact_Fax;

	@FindBy(xpath = ".//*[@id='formAddContacts']/input")
	public WebElement click_Save;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[2]/ng-form/div[7]/input")
	public WebElement click_ContactUpdate;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[3]/ng-form/div[7]/input[2]")
	public WebElement click_DeleteContact;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[2]/div/nav/div[2]/ul/li[5]/a")
	public WebElement Click_Account1;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement Click_BrokerpaymeNowUpdate;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[5]")
	public WebElement lnk_Notifications;

	@FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	public WebElement check_NotifyByWithdrawal;

	@FindBy(xpath = "//*[@id='formNotifications']/div[3]/input")
	public WebElement click_updatebtn;

	@FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	public WebElement uncheck_NotifyByWithdrawal;

	@FindBy(xpath = "//div[@role='alert']")
	public WebElement text_saved;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[4]")
	public WebElement lnk_Credit;

	@FindBy(xpath = "//*[@id='ExtendedCredit']")
	public WebElement text_ExtendedCredit;

	@FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	public WebElement click_Update;

	@FindBy(xpath = "//a[contains(text(),'My Credit')]")
	public WebElement lnk_MyCredit;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[7]")
	public WebElement link_paymeNow;

	@FindBy(xpath = "//*[@id='PMNEnrolled']")
	public WebElement check_PMNEnrolled;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement click_brokpaymeupdate;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]")
	public WebElement click_adminpaymenow;

	@FindBy(xpath = "//*[@id='PMNEnrolled']")
	public WebElement ucheck_adminPMNEnrolled;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement click_adminupdate;

	public ShipperCompleteAccountModule() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		lnk_account.click();
	}

	public void clickCompanylink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Company));
		lnk_Company.click();
	}

	public void enterDotnumber(String Dot) {
		wait.until(ExpectedConditions.elementToBeClickable(dotnum));
		dotnum.clear();
		dotnum.sendKeys(Dot);
	}

	public void enterEinnumber(String EIN) {
		wait.until(ExpectedConditions.elementToBeClickable(einnum));
		einnum.clear();
		einnum.sendKeys(EIN);
	}

	public void clickCompanyUpdate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(companyupdate));
		companyupdate.click();
	}

	public void clickContactlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Contact));
		lnk_Contact.click();
	}

	public void clickAddNewContact() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickAddNewContact));
		clickAddNewContact.click();
	}

	public void enterContactFirstName(String ContactFN) {
		wait.until(ExpectedConditions.elementToBeClickable(ContactFirstName));
		ContactFirstName.clear();
		ContactFirstName.sendKeys(ContactFN);
	}

	public void enterContactlastName(String ContactLN) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_LastName));
		Contact_LastName.clear();
		Contact_LastName.sendKeys(ContactLN);
	}

	public void enterContactemail(String contactemail) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Email));
		Contact_Email.clear();
		Contact_Email.sendKeys(contactemail);
	}

	public void enterContactphonenum(String ContactPN) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Phone));
		Contact_Phone.clear();
		Contact_Phone.sendKeys(ContactPN);
	}

	public void enterContactExtension(String Contactextension) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Ext));
		Contact_Ext.clear();
		Contact_Ext.sendKeys(Contactextension);
	}

	public void enterContactMobileNumber(String ContactMobileNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Mobile));
		Contact_Mobile.clear();
		Contact_Mobile.sendKeys(ContactMobileNumber);
	}

	public void enterContactFax(String ContactFax) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Fax));
		Contact_Fax.clear();
		Contact_Fax.sendKeys(ContactFax);
	}

	public void clicksavelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Save));
		click_Save.click();
	}

	public void clickContactUpdatelink() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(click_ContactUpdate));
		click_ContactUpdate.click();
	}

	public void clickdeletecontactlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_DeleteContact));
		click_DeleteContact.click();

		driver.switchTo().alert().accept();

	}

	public void clickAccount1link() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_Account1));
		Click_Account1.click();
	}

	public void clickNotificationlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Notifications));
		lnk_Notifications.click();
	}

	public void checkNotifyByWithdrwallink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(check_NotifyByWithdrawal));
		check_NotifyByWithdrawal.click();
	}

	public void clickUpdatebuttonlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtn));
		click_updatebtn.click();
	}

	public void uncheckNotifyByWithdrawallink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByWithdrawal));
		uncheck_NotifyByWithdrawal.click();
		text_saved.getText();

	}

	public void clickcreditlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Credit));
		lnk_Credit.click();
	}

	public void enterExtendedCredit(String ExtendedCredit) {
		wait.until(ExpectedConditions.elementToBeClickable(text_ExtendedCredit));
		text_ExtendedCredit.clear();
		text_ExtendedCredit.sendKeys(ExtendedCredit);
	}

	public void clickupdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Update));
		click_Update.click();
	}

	public void clickPaymentTermlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_PaymentTerm));
		click_PaymentTerm.click();

		Select pay = new Select(click_PaymentTerm);

		pay.selectByIndex(11);
	}

	public void clickBrokerpaymeNowUpdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_BrokerpaymeNowUpdate));
		Click_BrokerpaymeNowUpdate.click();
	}

	public void clickmycreditlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_MyCredit));
		lnk_MyCredit.click();
	}

	public void clickpaymenowlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_paymeNow));
		link_paymeNow.click();

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
	}

	public void checkpaymenowenroll() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(check_PMNEnrolled));
		check_PMNEnrolled.click();
	}

	public void clickbrokerpaymeupdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_brokpaymeupdate));
		click_brokpaymeupdate.click();
	}

	public void clickadminpaymenowlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_adminpaymenow));
		click_adminpaymenow.click();
	}

	public void uncheckpaymenowenroll() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ucheck_adminPMNEnrolled));
		ucheck_adminPMNEnrolled.click();
	}

	public void clickadminupdate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_adminupdate));
		click_adminupdate.click();
	}

	/**
	 * @return the motorCarrierSelector
	 */
	public WebElement getMotorCarrierSelector() {
		return motorCarrierSelector;
	}

	/**
	 * @param motorCarrierSelector
	 *            the motorCarrierSelector to set
	 */
	public void setMotorCarrierSelector(Integer motorCarrierIndex) {
		wait.until(ExpectedConditions.elementToBeClickable(this.motorCarrierSelector));
		Select motorCarrier = new Select(this.motorCarrierSelector);
		motorCarrier.selectByIndex(motorCarrierIndex);
	}

	/**
	 * @return the motorCarrierField
	 */
	public WebElement getMotorCarrierField() {
		return motorCarrierField;
	}

	/**
	 * @param motorCarrierField
	 *            the motorCarrierField to set
	 */
	public void setMotorCarrierField(Integer motorCarrierNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(this.motorCarrierField));
		this.motorCarrierField.clear();
		this.motorCarrierField.sendKeys(motorCarrierNumber.toString());
	}

}
