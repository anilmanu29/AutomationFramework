package pages.LoadPay.Carrier;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierRegisterCanada  extends TestBase {

	Select slc, sls, sloc, slos;
	int k;
	String usastates;
	
	WebDriverWait wait = new  WebDriverWait(driver, 30);
	
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement buttonsignup;
	
	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div[3]/div/button[1]")
	WebElement CarriersignupButton;
	
	@FindBy(id = "DOT")
	WebElement field_dotnumber;

	@FindBy(id = "Name")
	WebElement field_companyname;

	@FindBy(id = "DoingBusinessAs")
	WebElement field_doingbussiness;

	@FindBy(id = "EntityType")
	WebElement dropdowntypeofentity;

	@FindBy(id = "IncorporationCountry")
	WebElement countryincorporation;

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
	@FindBy(xpath = ".//*[@id='formAddress']//child::input[@type='submit']")
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
	WebElement Password;
	
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

	@FindBy(xpath = ".//*[@id='OriginCountry']")
	WebElement originCountry;

	@FindBy(xpath = ".//*[@id='State']")
	WebElement originstate;
	
	@FindBy(id = "IncorporationState")
	WebElement originstatee;
	
	@FindBy(id = "OriginCountry")
	WebElement originCountrydropdown;
	
	@FindBy(id = "IncorporationCountry")
	private WebElement dropdown_country;

	@FindBy(id = "IncorporationState")
	private WebElement dropdown_states;

	
	
	public CarrierRegisterCanada() {
		PageFactory.initElements(driver, this);
	}

	public void signup() {
		buttonsignup.click();
	}

	public void CarrierRegister() {
		CarriersignupButton.click();
	}

	public void dotnumber(String Dot) throws InterruptedException {
		field_dotnumber.sendKeys(Dot);
		//doingbussiness.click();
	}

	public void company(String cname) {
		field_companyname.sendKeys(cname);
	}

	  public void doingbussiness( String doingBussiness )
	  {
		  field_doingbussiness.sendKeys(doingBussiness);
	  }

	public void selectType() throws InterruptedException {
		Thread.sleep(1000);
		TypeofEntity.click();

	}

	public void countrydropdown(String cname, String state) throws InterruptedException {
		slc = new Select(dropdown_country);
		sls = new Select(dropdown_states);

		List<WebElement> countryvalues = slc.getOptions();
		for (WebElement e : countryvalues) {
			String countryname = e.getText();
			if (countryname.equalsIgnoreCase(cname)) {
				slc.selectByVisibleText(countryname);
				sls.selectByVisibleText(state);
				Thread.sleep(1000);
				break;
			}
			}
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
		ZipCode.clear();
		ZipCode.sendKeys(ZipCode1);
	}

	public void originCountry(String country, String stat) {
		
		sloc = new Select(originCountrydropdown);
		sloc.selectByVisibleText(country);
		slos = new Select(originstate);
		slos.selectByVisibleText(stat);

	}

	public void address(String Address) {
		address.sendKeys(Address);
	}

	public void city(String City) {
		city.sendKeys(City);
	}


	public void submit() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);

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
		Password.sendKeys(pass);
		return pass;
	}

	public void ConfirmPassword(String confirmpass) {
		ConfirmPassword.sendKeys(confirmpass);
	}

	public void Next() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit1);

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
