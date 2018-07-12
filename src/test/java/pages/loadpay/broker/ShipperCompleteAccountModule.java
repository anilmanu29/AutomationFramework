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
	int j=0;
	String pennyamt;
	String pennyamounts;
	String pennyamoun;

	public @FindBy(xpath = "//a[text()='Account']")
     WebElement lnk_account;

	public @FindBy(xpath = "//a[contains(text(),'Company')]")
	 WebElement lnk_Company;

	@FindBy(xpath = "//input[@name='DOT']")
	 WebElement dotnum;
	
	@FindBy(xpath = "//input[@name='EIN']")
	 WebElement einnum;
	
	public @FindBy(xpath = "//*[@id='formCompany']/div/div[14]/input")
	 WebElement companyupdate;
	
	
	public @FindBy(xpath = "//a[contains(text(),'Contact')]")
	WebElement lnk_Contact;
	
	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/a")
	WebElement clcik_AddNewContact;
	
	@FindBy(xpath = "//input[@id='ContactFirstName']")
	WebElement ContactFirstName;
	
	public @FindBy(xpath = ".//*[@id='PMNTerm']")
	WebElement click_PaymentTerm;
	
	@FindBy(xpath = "//*[@id='Contact_LastName']")
	WebElement Contact_LastName;
	
	public @FindBy(xpath = "//*[@id='Contact_Email']")
	WebElement Contact_Email;
	@FindBy(xpath = "//*[@id='Contact_Phone']")
	WebElement Contact_Phone;
	@FindBy(xpath = "//*[@id='Contact_Ext']")
	WebElement Contact_Ext;
	@FindBy(xpath = "//*[@id='Contact_Mobile']")
	WebElement Contact_Mobile;
	@FindBy(xpath = "//*[@id='Contact_Fax']")
	WebElement Contact_Fax;
	public @FindBy(xpath = ".//*[@id='formAddContacts']/input")
	WebElement click_Save;
	
	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[2]/ng-form/div[7]/input")
	WebElement click_ContactUpdate;
	
	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[3]/ng-form/div[7]/input[2]")
	WebElement click_DeleteContact;
	
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[2]/div/nav/div[2]/ul/li[5]/a")
	WebElement Click_Account1;
	
	public @FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement Click_BrokerpaymeNowUpdate;
	
	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[5]")
	WebElement lnk_Notifications;
	
	public @FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	WebElement check_NotifyByWithdrawal;
	
	public @FindBy(xpath = "//*[@id='formNotifications']/div[3]/input")
	WebElement click_updatebtn;
	
	public @FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	WebElement uncheck_NotifyByWithdrawal;
	
	
	public @FindBy(xpath = "//div[@role='alert']")
	WebElement text_saved;
	
	public @FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[4]")
	WebElement lnk_Credit;

	public @FindBy(xpath = "//*[@id='ExtendedCredit']")
	WebElement text_ExtendedCredit;
	
	public @FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	WebElement click_Update;
	
	public @FindBy(xpath = "//a[contains(text(),'My Credit')]")
	WebElement lnk_MyCredit;
	
	public @FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[7]")
	WebElement link_paymeNow;
	
	public @FindBy(xpath = "//*[@id='PMNEnrolled']")
	WebElement check_PMNEnrolled;
	
	public @FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement click_brokpaymeupdate;
	
	public @FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]")
	WebElement click_adminpaymenow;
	
	public @FindBy(xpath = "//*[@id='PMNEnrolled']")
	WebElement ucheck_adminPMNEnrolled;
	
	public @FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement click_adminupdate;
	
	
	/*@FindBy(xpath = "//div[@class='well myaccount ng-scope in collapse']//child::div[1]//child::a[1]")
	private WebElement lnk_addnewbankaccount;

	@FindBy(id = "NameOnAccount")
	private WebElement field_accountname;

	@FindBy(id = "BankingRouting")
	private WebElement field_routnumber;

	@FindBy(id = "BankingAccount")
	private WebElement field_accnum;

	@FindBy(id = "ConfirmBankingAccount")
	private WebElement field_confirmaccnum;

	@FindBy(xpath = "//form[@id='formBanking']//child::input[@value='Save']")
	private WebElement button_save;

	@FindBy(xpath = "//button[text()='Set default']")
	private WebElement button_satdefault;

	@FindBy(xpath = "//button[text()='Set default']//preceding::p[7]/b")
	private WebElement accname;

	@FindBy(xpath = "//button[text()='Set default']//preceding::p[6]/b")
	private WebElement routingnum;

	@FindBy(xpath = "//button[text()='Remove']")
	private  List<WebElement> button_remove;

	@FindBy(xpath = "//a[@class='btn btn-link menuOption'][contains(text(),'Banking')]")
	private WebElement lnk_adminbanking;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div[1]/div/div/p[9]/span/b")
	private WebElement pennyverificationamt;

	@FindBy(xpath = "//a[text()='Verify']")
	private WebElement lnk_verify;

	@FindBy(xpath = "//a[text()='Verify']//following::input[@type='text'][1]")
	private WebElement field_amount;

	@FindBy(xpath = "//a[text()='Verify']//following::input[@type='text'][1]//following::input[@value='Verify'][1]")
	private WebElement btn_verify;

	@FindBy(xpath = "//*[text()='Banking Information']//following::div[@class='well']/p[1]/b")
	private List<WebElement> banking;

	@FindBy(xpath = "//button[text()='Remove']//preceding::b[3]")
	private List<WebElement> lists;
	
	@FindBy(xpath = "//input[@value='checking']")
	private WebElement radiobtn_personalchecking;

	@FindBy(xpath = "//input[@value='savings']")
	private WebElement radiobtn_personalsaving;*/


	public ShipperCompleteAccountModule() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void clickAccountlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}
	
	public void clickCompanylink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Company));
		js.executeScript("arguments[0].click();", lnk_Company);
	}
	
	public void enterDotnumber( String Dot )
	  {
		dotnum.clear();
		dotnum.sendKeys(Dot);
	  }

	public void enterEinnumber( String EIN )
	  {
		einnum.sendKeys(EIN);
	  }
	
	
	public void clickCompanyUpdate() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(companyupdate));
		js.executeScript("arguments[0].click();", companyupdate);
	}
	
	public void clickContactlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Contact));
		js.executeScript("arguments[0].click();", lnk_Contact);
	}
	
	public void clickAddNewContact() throws InterruptedException {
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(clcik_AddNewContact));
		js.executeScript("arguments[0].click();", clcik_AddNewContact);
	}
	
	public void enterContactFirstName( String ContactFN )
	  {
		ContactFirstName.sendKeys(ContactFN);
	  }
	

	public void enterContactlastName( String ContactLN )
	  {
		Contact_LastName.sendKeys(ContactLN);
	  }
	
	public void enterContactemail( String contactemail )
	  {
		Contact_Email.sendKeys(contactemail);
	  }
	
	public void enterContactphonenum( String ContactPN )
	  {
		Contact_Phone.sendKeys(ContactPN);
	  }
	
	public void enterContactExtension( String Contactextension )
	  {
		Contact_Ext.sendKeys(Contactextension);
	  }
	
	public void enterContactMobileNumber( String ContactMobileNumber )
	  {
		Contact_Mobile.sendKeys(ContactMobileNumber);
	  }
	
	public void enterContactFax( String ContactFax )
	  {
		Contact_Fax.sendKeys(ContactFax);
	  }
	
	public void clicksavelink() throws InterruptedException {
		Thread.sleep(1000);
		/*wait.until(ExpectedConditions.elementToBeClickable(click_Save));*/
		js.executeScript("arguments[0].click();", click_Save);
	}
	
	public void clickContactUpdatelink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_ContactUpdate));
		js.executeScript("arguments[0].click();", click_ContactUpdate);
	}
	
	public void clickdeletecontactlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_DeleteContact));
		js.executeScript("arguments[0].click();", click_DeleteContact);
		
		driver.switchTo().alert().accept();
	
	}
	
	public void clickAccount1link() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(Click_Account1));
		js.executeScript("arguments[0].click();", Click_Account1);
	}
	
	
	public void clickNotificationlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Notifications));
		js.executeScript("arguments[0].click();", lnk_Notifications);
	}
	
	public void checkNotifyByWithdrwallink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(check_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", check_NotifyByWithdrawal);
	}
	
	public void clickUpdatebuttonlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtn));
		js.executeScript("arguments[0].click();", click_updatebtn);
	}
	
	public void uncheckNotifyByWithdrawallink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", uncheck_NotifyByWithdrawal);
		text_saved.getText();
		
	}
	
	public void clickcreditlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_Credit));
		js.executeScript("arguments[0].click();", lnk_Credit);
	}
	
	public void enterExtendedCredit(String ExtendedCredit)
	  {
		text_ExtendedCredit.clear();
		text_ExtendedCredit.sendKeys(ExtendedCredit);
	  }
	
	public void clickupdatelink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_Update));
		js.executeScript("arguments[0].click();", click_Update);
	}
	
	public void clickPaymentTermlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_PaymentTerm));
		js.executeScript("arguments[0].click();", click_PaymentTerm);
		
		 Select pay = new Select(click_PaymentTerm);
		    
			pay.selectByIndex( 11 );
	}
	
	public void clickBrokerpaymeNowUpdatelink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(Click_BrokerpaymeNowUpdate));
		js.executeScript("arguments[0].click();", Click_BrokerpaymeNowUpdate);
	}
	
	public void clickmycreditlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(lnk_MyCredit));
		js.executeScript("arguments[0].click();", lnk_MyCredit);
	}
	
	public void clickpaymenowlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(link_paymeNow));
		js.executeScript("arguments[0].click();", link_paymeNow);
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
	}
	
	public void checkpaymenowenroll() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(check_PMNEnrolled));
		js.executeScript("arguments[0].click();", check_PMNEnrolled);
	}
	
	public void clickbrokerpaymeupdatelink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_brokpaymeupdate));
		js.executeScript("arguments[0].click();", click_brokpaymeupdate);
	}
	
	public void clickadminpaymenowlink() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_adminpaymenow));
		js.executeScript("arguments[0].click();", click_adminpaymenow);
	}
	
	public void uncheckpaymenowenroll() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ucheck_adminPMNEnrolled));
		js.executeScript("arguments[0].click();", ucheck_adminPMNEnrolled);
	}
	
	public void clickadminupdate() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(click_adminupdate));
		js.executeScript("arguments[0].click();", click_adminupdate);
	}
	
	
}
