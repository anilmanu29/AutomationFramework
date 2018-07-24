package pages.loadpay.broker;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerPaymentTermsChargeRecipient extends TestBase {
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
	Actions act = null;

	public @FindBy(xpath = "//a[text()='Account']") WebElement lnk_account;

	public @FindBy(xpath = "//a[contains(text(),'Company')]") WebElement lnk_Company;

	@FindBy(xpath = "//input[@name='DOT']")
	WebElement dotnum;

	@FindBy(xpath = "//input[@name='EIN']")
	WebElement einnum;

	public @FindBy(xpath = "//*[@id='formCompany']/div/div[14]/input") WebElement companyupdate;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[8]")
	public WebElement Click_PaymentTerms;

	@FindBy(xpath = ".//*[@id='formPaymentTerms']/div/div/div[3]/input")
	public WebElement click_paymentTermUpdate;

	@FindBy(xpath = ".//*[@id='formPaymentTerms']/div/div/div[1]/div/div[2]/div/div[1]/div[1]/label/input")
	public WebElement click_ChargeRecipient;

	@FindBy(xpath = ".//*[@id='PaymentTermsEnrolled']")
	public WebElement check_paymentenroll;

	public @FindBy(xpath = "//a[contains(text(),'Contact')]") WebElement lnk_Contact;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/a") WebElement clickAddNewContact;

	@FindBy(xpath = "//input[@id='ContactFirstName']")
	WebElement ContactFirstName;

	public @FindBy(xpath = ".//*[@id='PMNTerm']") WebElement click_PaymentTerm;

	@FindBy(xpath = "//*[@id='Contact_LastName']")
	WebElement Contact_LastName;

	public @FindBy(xpath = ".//*[@id='PaymentDate']") WebElement click_paymentdate;

	public @FindBy(xpath = "//*[text()='Prev']") WebElement click_predate;

	public @FindBy(xpath = "//*[contains(@class, 'ui-datepicker-today')]//following::td[1]") WebElement click_nextdate;

	public @FindBy(xpath = "//*[@id='Contact_Email']") WebElement Contact_Email;

	@FindBy(xpath = "//*[@id='Contact_Phone']")
	WebElement Contact_Phone;

	@FindBy(xpath = "//*[@id='Contact_Ext']")
	WebElement Contact_Ext;

	@FindBy(xpath = "//*[@id='Contact_Mobile']")
	WebElement Contact_Mobile;

	@FindBy(xpath = "//*[@id='Contact_Fax']")
	WebElement Contact_Fax;

	public @FindBy(xpath = ".//*[@id='formAddContacts']/input") WebElement click_Save;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[2]/ng-form/div[7]/input") WebElement click_ContactUpdate;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[3]/ng-form/div[7]/input[2]") WebElement click_DeleteContact;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[2]/div/nav/div[2]/ul/li[5]/a")
	WebElement Click_Account1;

	public @FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]") WebElement Click_BrokerpaymeNowUpdate;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[5]") WebElement lnk_Notifications;

	public @FindBy(xpath = ".//*[@id='NotifyByWithdrawal']") WebElement check_NotifyByWithdrawal;

	public @FindBy(xpath = "//*[@id='formNotifications']/div[3]/input") WebElement click_updatebtn;

	public @FindBy(xpath = ".//*[@id='NotifyByWithdrawal']") WebElement uncheck_NotifyByWithdrawal;

	// AdminPayment Terms
	public @FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]") WebElement Click_AdminPaymentTerms;

	public @FindBy(xpath = "//div[@role='alert']") WebElement text_saved;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[4]") WebElement lnk_Credit;

	public @FindBy(xpath = "//*[@id='ExtendedCredit']") WebElement text_ExtendedCredit;

	public @FindBy(xpath = "//*[@id='formCredit']/div[3]/input") WebElement click_Update;

	public @FindBy(xpath = "//a[contains(text(),'My Credit')]") WebElement lnk_MyCredit;

	public @FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[7]") WebElement link_paymeNow;

	public @FindBy(xpath = "//*[@id='PMNEnrolled']") WebElement check_PMNEnrolled;

	public @FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]") WebElement click_brokpaymeupdate;

	public @FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]") WebElement click_adminpaymenow;

	public @FindBy(xpath = "//*[@id='PMNEnrolled']") WebElement ucheck_adminPMNEnrolled;

	public @FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]") WebElement click_adminupdate;

	public BrokerPaymentTermsChargeRecipient() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		act = new Actions(driver);
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

	public void clickPaymentTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_PaymentTerms));
		js.executeScript("arguments[0].click();", Click_PaymentTerms);
	}

	public void clickpaymentTermUpdate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_paymentTermUpdate));
		js.executeScript("arguments[0].click();", click_paymentTermUpdate);
	}

	public void clickChargeRecipient() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_ChargeRecipient));
		js.executeScript("arguments[0].click();", click_ChargeRecipient);
	}

	public void clickpaymentenroll() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(check_paymentenroll));
		js.executeScript("arguments[0].click();", check_paymentenroll);
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

	public void clickpaymentdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_paymentdate));
		act.moveToElement(click_paymentdate).click().perform();
		// js.executeScript("arguments[0].click();", click_paymentdate);
	}

	public void clickpredatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_predate));
		js.executeScript("arguments[0].click();", click_predate);
	}

	public void clicknextdatelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_nextdate));
		js.executeScript("arguments[0].click();", click_nextdate);
	}

	public void clickAdminPaymentTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Click_AdminPaymentTerms));
		js.executeScript("arguments[0].click();", Click_AdminPaymentTerms);
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

}
