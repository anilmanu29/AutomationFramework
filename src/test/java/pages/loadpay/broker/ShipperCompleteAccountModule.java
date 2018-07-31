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
	JavascriptExecutor js;
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
		js = (JavascriptExecutor) driver;
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}

	public void clickCompanylink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Company));
		js.executeScript("arguments[0].click();", lnk_Company);
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
		js.executeScript("arguments[0].click();", companyupdate);
	}

	public void clickContactlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Contact));
		js.executeScript("arguments[0].click();", lnk_Contact);
	}

	public void clickAddNewContact() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickAddNewContact));
		js.executeScript("arguments[0].click();", clickAddNewContact);
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
		js.executeScript("arguments[0].click();", click_Save);
	}

	public void clickContactUpdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_ContactUpdate));
		js.executeScript("arguments[0].click();", click_ContactUpdate);
	}

	public void clickdeletecontactlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_DeleteContact));
		js.executeScript("arguments[0].click();", click_DeleteContact);

		driver.switchTo().alert().accept();

	}

	public void clickAccount1link() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_Account1));
		js.executeScript("arguments[0].click();", Click_Account1);
	}

	public void clickNotificationlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Notifications));
		js.executeScript("arguments[0].click();", lnk_Notifications);
	}

	public void checkNotifyByWithdrwallink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(check_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", check_NotifyByWithdrawal);
	}

	public void clickUpdatebuttonlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtn));
		js.executeScript("arguments[0].click();", click_updatebtn);
	}

	public void uncheckNotifyByWithdrawallink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", uncheck_NotifyByWithdrawal);
		text_saved.getText();

	}

	public void clickcreditlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Credit));
		js.executeScript("arguments[0].click();", lnk_Credit);
	}

	public void enterExtendedCredit(String ExtendedCredit) {
		wait.until(ExpectedConditions.elementToBeClickable(text_ExtendedCredit));
		text_ExtendedCredit.clear();
		text_ExtendedCredit.sendKeys(ExtendedCredit);
	}

	public void clickupdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Update));
		js.executeScript("arguments[0].click();", click_Update);
	}

	public void clickPaymentTermlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_PaymentTerm));
		js.executeScript("arguments[0].click();", click_PaymentTerm);

		Select pay = new Select(click_PaymentTerm);

		pay.selectByIndex(11);
	}

	public void clickBrokerpaymeNowUpdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_BrokerpaymeNowUpdate));
		js.executeScript("arguments[0].click();", Click_BrokerpaymeNowUpdate);
	}

	public void clickmycreditlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_MyCredit));
		js.executeScript("arguments[0].click();", lnk_MyCredit);
	}

	public void clickpaymenowlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(link_paymeNow));
		js.executeScript("arguments[0].click();", link_paymeNow);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
	}

	public void checkpaymenowenroll() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(check_PMNEnrolled));
		js.executeScript("arguments[0].click();", check_PMNEnrolled);
	}

	public void clickbrokerpaymeupdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_brokpaymeupdate));
		js.executeScript("arguments[0].click();", click_brokpaymeupdate);
	}

	public void clickadminpaymenowlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_adminpaymenow));
		js.executeScript("arguments[0].click();", click_adminpaymenow);
	}

	public void uncheckpaymenowenroll() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ucheck_adminPMNEnrolled));
		js.executeScript("arguments[0].click();", ucheck_adminPMNEnrolled);
	}

	public void clickadminupdate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_adminupdate));
		js.executeScript("arguments[0].click();", click_adminupdate);
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
