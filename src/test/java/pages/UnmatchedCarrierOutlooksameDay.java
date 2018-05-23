package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import testcases.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedCarrierOutlooksameDay extends TestBase {

	// Page Factory - OR:
	WebDriverWait wait = null;
	Actions act = null;

	@FindBy(id = "username")
	WebElement UserName;

	@FindBy(xpath = ".//*[@id='lgnDiv']/div[9]/div/span")
	WebElement loginButton;

	@FindBy(id = "password")
	WebElement Password;

	@FindBy(xpath = "//button[contains(@class,'_hl_2 _hl_e ms-font-weight-regular')]")
	WebElement haspopup;

	@FindBy(xpath = "//span[contains(text(),'Open another mailbox')]")
	WebElement lnkopenanothermail;

	@FindBy(xpath = "//span[contains(text(),'Search mail and people')]")
	WebElement fieldSearchMail;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement fieldTextbox;

	@FindBy(xpath = "//*[@title='Loadpay Test All']")
	WebElement searchSuggestion;

	@FindBy(xpath = "//span[text()='Open']")
	WebElement buttonOpen;

	@FindBy(xpath = "//table/tbody/tr/td//child::strong[text()='Setup your FREE account']")
	WebElement linkVerify;

	@FindBy(xpath = "//div[@id='page-main']//child::*[text()='Email Verified']")
	WebElement emailverifymessage;

	// @FindBy(xpath =
	// "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/button")
	// WebElement fieldsearch;

	@FindBy(xpath = "//button[@type='button'][@aria-label='Activate Search Textbox']")
	WebElement fieldsearch;
	//
	// @FindBy(xpath =
	// "html/body/div[2]/div/div[3]/div[3]/div/div[1]/div[2]/div[4]/div/div/div[1]/div/div/div/div[1]/form/div/input")
	// WebElement fieldsearchinput;

	@FindBy(xpath = "//input[@aria-label='Search. Press Enter to Start Searching.']")
	WebElement fieldsearchinput;

	@FindBy(xpath = ".//*[contains(@class,'lvHighlightAllClass')]//following::*[text()='Verify your email address']")
	WebElement verifyImailMail;

	// @FindBy(xpath = "//div[@role='option']//child::span[2]")
	// WebElement keyword;

	@FindBy(xpath = "//button[@aria-label='Start search']")
	WebElement btnsearch;

	@FindBy(xpath = "//span[contains(@class,'lvHighlightSubjectClass')][contains(text(),'Calpion has scheduled your payment - ACTION REQUIRED')]")
	WebElement verifyEmail;

	@FindBy(xpath = "//span[text()='Search contacts and directory']")
	WebElement buttonsearchcontacts;

	@FindBy(xpath = "//button[text()='Carrier']")
	WebElement btn_carrier;

	WebElement Dotnumber;
	@FindBy(xpath = ".//*[@id='Name'] ")
	WebElement CompanyName;
	@FindBy(xpath = ".//*[@id='DoingBusinessAs'] ")
	WebElement doingbussiness;
	@FindBy(xpath = ".//*[@id='EntityType']")
	WebElement TypeofEntity;
	@FindBy(xpath = ".//*[@id='IncorporationCountry'] ")
	WebElement countryIncorporation;
	@FindBy(xpath = ".//*[@id='IncorporationState']")
	WebElement stateIncorporation;
	@FindBy(xpath = ".//*[@id='Registration_User_UserName']")
	WebElement Email;
	@FindBy(xpath = " .//*[@id='Registration_User_UserNameConfirm']")
	WebElement confirmEmail;
	@FindBy(xpath = " .//*[@id='ICertify']")
	WebElement Icertify;

	@FindBy(xpath = ".//*[@id='PMNTerm'] ")
	WebElement PaymentTerms;

	@FindBy(xpath = ".//*[@id='formCompany']/div/div[12]/div/div/input ")
	WebElement Next;
	@FindBy(xpath = "//input[@id='ZipCode']")
	WebElement ZipCode;
	@FindBy(xpath = "//select[@id='OriginCountry']")
	WebElement Country;
	@FindBy(xpath = "//input[@id='StreetAddress']")
	WebElement address;
	@FindBy(xpath = "//input[@id='City']")
	WebElement city;
	@FindBy(xpath = "//select[@id='State']")
	WebElement State;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//input[@id='ContactFirstName']")
	WebElement ContactFirstName;
	@FindBy(xpath = "//input[contains(@id,'LastName')]")
	WebElement LastName;
	@FindBy(xpath = "//input[contains(@id,'Phone')]")
	WebElement Phone;
	@FindBy(xpath = "//input[contains(@id,'Ext')]")
	WebElement Ext;
	@FindBy(xpath = "//input[contains(@id,'Mobile')]")
	WebElement Mobile;
	@FindBy(xpath = "//input[contains(@id,'Password')]")
	WebElement Passwordd;
	@FindBy(xpath = "//input[contains(@id,'ConfirmPassword')]")
	WebElement ConfirmPassword;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit1;

	@FindBy(xpath = "//input[contains(@id,'AccountName')]")
	WebElement AccountName;

	@FindBy(xpath = "//input[@id='BankingRouting']")
	WebElement BankingRouting;

	@FindBy(xpath = "//input[@id='BankingAccount']")
	WebElement BankingAccount;
	@FindBy(xpath = "//input[@id='ConfirmBankingAccount']")
	WebElement ConfirmBankingAccount;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit2;
	
	@FindBy(xpath = "//*[@role='menuitemradio'][text()='Unread']")
	WebElement lnkunread;
	
	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	WebElement emailid;
	
	
	

	// Initializing the Page Objects:
	public UnmatchedCarrierOutlooksameDay() throws IOException {

		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);

	}

	public void clickPopUp() throws InterruptedException {
		// Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(fieldSearchMail));
		fieldSearchMail.click();
		Thread.sleep(2000);
		haspopup.click();
		Thread.sleep(1000);
	}

	public void clickOpenMailBox() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}

	public void enterEmail(String email) throws InterruptedException {
		fieldTextbox.sendKeys(email);
		Thread.sleep(1000);
		try {
			buttonsearchcontacts.click();
			Thread.sleep(1000);
			buttonOpen.click();
		} catch (Exception e) {
			searchSuggestion.click();
			Thread.sleep(1000);
			buttonOpen.click();
		}
	}

	/*
	 * public void enterEmail(String email) throws InterruptedException {
	 * fieldTextbox.sendKeys(email); Thread.sleep(1000); searchSuggestion.click(); }
	 */

	/*
	 * public void clickOpen() { buttonOpen.click(); }
	 */
	
	public void handleNewInbox() throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		driver.navigate().refresh();
		Thread.sleep(4000);
		
		int count = BrokerPaymentforUnmatchedCarrierTest.al.size();



		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		
		for(WebElement e: list)
		{
			Thread.sleep(1000);
			e.click();
			Thread.sleep(1000);
			System.out.println(list.size());
			Thread.sleep(1000);
			System.out.println(BrokerPaymentforUnmatchedCarrierTest.al.get(0)+";");
			System.out.println(emailid.getText());
			if(emailid.getText().equalsIgnoreCase(BrokerPaymentforUnmatchedCarrierTest.al.get(0)+";"))
			{
				Thread.sleep(1000);
				linkVerify.click();
				break;
			}
			
		}
		
		
		
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", fieldsearch);
//		Thread.sleep(1000);
//		fieldsearchinput.sendKeys(BrokerPaymentforUnmatchedCarrierTest.umemail);
//		Thread.sleep(1000);
//		//fieldsearchinput.sendKeys(Keys.ENTER);
//		 JavascriptExecutor jc = (JavascriptExecutor) driver;
//		 jc.executeScript("arguments[0].click();", btnsearch);
//		Thread.sleep(5000);
		//fieldsearchinput.sendKeys(Keys.ENTER);
		//Thread.sleep(3000);
	/*	verifyEmail.click();
		Thread.sleep(2000);
		linkVerify.click();*/
	
	
		

	}

	public void switchtoCarrieregistration() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		Thread.sleep(1000);
	}

	public void unmatchedCarrierRegistration() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btn_carrier));
		btn_carrier.click();
	}

	public void dotnumber(String Dot) {
		wait.until(ExpectedConditions.elementToBeClickable(Dotnumber));
		Dotnumber.sendKeys(Dot);
	}

	public void companyname(String Company) {
		CompanyName.sendKeys(Company);
	}

	public void doingbussiness(String doingBussiness) {
		doingbussiness.sendKeys(doingBussiness);
	}

	public void selectType() {
		TypeofEntity.click();

	}

	public void countryofincorporation() {
		countryIncorporation.click();
	}

	public void stateofincorporation() {
		stateIncorporation.click();
	}

	public String CarrierEmail(String email) {
		Email.sendKeys(email);

		return email;
	}

	public void confirmEmail(String ConfirmEmail) {
		confirmEmail.sendKeys(ConfirmEmail);

	}

	public void iCertifyClick() {
		Icertify.click();
	}

	public void paymentTerm() {
		PaymentTerms.click();
		Select pay = new Select(PaymentTerms);

		pay.selectByIndex(2);

	}

	public void next() {
		Next.click();
	}

	public void ZipCode(String ZipCode1) {
		ZipCode.sendKeys(ZipCode1);
	}

	public void country() {
		Country.click();
	}

	public void address(String Address) {
		address.sendKeys(Address);
	}

	public void city(String City) {
		city.sendKeys(City);
	}

	public void State() {
		State.sendKeys();
	}

	public void submit() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);

		// submit.click();
	}

	public void ContactFirstName(String FirstName) {
		ContactFirstName.sendKeys(FirstName);
	}

	public void LastName(String lastName) {
		LastName.sendKeys(lastName);
	}

	public void Phone(String PhoneNumber) {
		Phone.sendKeys(PhoneNumber);
	}

	public String Password(String pass) {
		Passwordd.sendKeys(pass);
		return pass;
	}

	public void ConfirmPassword(String confirmpass) {
		ConfirmPassword.sendKeys(confirmpass);
	}

	public void Next() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit1);

		// submit1.click();
	}

	public void AccountName(String NameonAccount) {
		AccountName.sendKeys(NameonAccount);
	}

	public void BankingRouting(String routingNumber) {
		BankingRouting.sendKeys(routingNumber);
	}

	public void BankingAccount(String BankAccountNumber) {
		BankingAccount.sendKeys(BankAccountNumber);
	}

	public void ConfirmBankingAccount(String ConfirmBankAccountNumber) {
		ConfirmBankingAccount.sendKeys(ConfirmBankAccountNumber);
	}

	public void Finish() {
		submit2.click();
	} 

}
