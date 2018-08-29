package pages.loadpay.carrier;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierRegisterCanada extends TestBase {

	Select slc, sls, sloc, slos;
	int k;
	String usastates;

	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(xpath = "//a[@href='/Account/Register']")
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

	@FindBy(xpath = ".//*[@id='formCompany']/div/div[12]/div/div/input")
	WebElement CompanyNextBtn;

	@FindBy(xpath = ".//*[@id='formAddress']/div/div[4]/div/div[2]/input")
	WebElement AddressNextBtn;

	@FindBy(xpath = ".//*[@id='formContact']/div[2]/div/div[2]/input")
	WebElement ContactNextBtn;

	@FindBy(xpath = ".//*[@id='formBanking']/div/div[8]/div/div/div[2]/input")
	WebElement BankingNextBtn;

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

	@FindBy(xpath = "//input[contains(@id,'AccountName')]")
	WebElement AccountName;

	@FindBy(xpath = "//input[@id='BankingRouting']")
	WebElement BankingRouting;

	@FindBy(xpath = "//input[@id='BankingAccount']")
	WebElement BankingAccount;

	@FindBy(xpath = "//input[@id='ConfirmBankingAccount']")
	WebElement ConfirmBankingAccount;

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
		wait = new WebDriverWait(driver, 30);
	}

	public void signup() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonsignup));
		buttonsignup.click();
	}

	public void CarrierRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(CarriersignupButton));
		CarriersignupButton.click();
	}

	public void dotnumber(String Dot) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(field_dotnumber));
		field_dotnumber.clear();
		field_dotnumber.sendKeys(Dot);
		// doingbussiness.click();
	}

	public void company(String cname) {
		wait.until(ExpectedConditions.elementToBeClickable(field_companyname));
		field_companyname.clear();
		field_companyname.sendKeys(cname);
	}

	public void doingbussiness(String doingBussiness) {
		wait.until(ExpectedConditions.elementToBeClickable(field_doingbussiness));
		field_doingbussiness.clear();
		field_doingbussiness.sendKeys(doingBussiness);
	}

	public void selectType() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(TypeofEntity));
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
				wait.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
		}
	}

	public String CarrierEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(Email));
		Email.clear();
		Email.sendKeys(email);
		return email;
	}

	public void confirmEmail(String ConfirmEmail) {
		wait.until(ExpectedConditions.elementToBeClickable(confirmEmail));
		confirmEmail.clear();
		confirmEmail.sendKeys(ConfirmEmail);

	}

	public void iCertifyClick() {
		wait.until(ExpectedConditions.elementToBeClickable(Icertify));
		Icertify.click();
	}

	public void paymentTerm() {
		wait.until(ExpectedConditions.elementToBeClickable(PaymentTerms));
		PaymentTerms.click();
		Select pay = new Select(PaymentTerms);
		pay.selectByIndex(2);

	}

	public void clickNextBtnOnCompanyForm() {
		wait.until(ExpectedConditions.elementToBeClickable(CompanyNextBtn));
		CompanyNextBtn.click();
	}

	public void clickNextBtnOnAddressForm() {
		wait.until(ExpectedConditions.elementToBeClickable(AddressNextBtn));
		AddressNextBtn.click();
	}

	public void clickNextBtnOnContactForm() {
		wait.until(ExpectedConditions.elementToBeClickable(ContactNextBtn));
		ContactNextBtn.click();
	}

	public void clickNextBtnOnBankingForm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(BankingNextBtn));
		Thread.sleep(1000);
		BankingNextBtn.click();
	}

	public void ZipCode(String ZipCode1) {
		wait.until(ExpectedConditions.elementToBeClickable(ZipCode));
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
		wait.until(ExpectedConditions.elementToBeClickable(address));
		address.clear();
		address.sendKeys(Address);
	}

	public void city(String City) {
		wait.until(ExpectedConditions.elementToBeClickable(city));
		city.clear();
		city.sendKeys(City);
	}

	public void ContactFirstName(String FirstName) {
		wait.until(ExpectedConditions.elementToBeClickable(ContactFirstName));
		ContactFirstName.clear();
		ContactFirstName.sendKeys(FirstName);
	}

	public void LastName(String lastName) {
		wait.until(ExpectedConditions.elementToBeClickable(LastName));
		LastName.clear();
		LastName.sendKeys(lastName);
	}

	public void Phone(String PhoneNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(Phone));
		Phone.clear();
		Phone.sendKeys(PhoneNumber);
	}

	public String Password(String pass) {
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		Password.clear();
		Password.sendKeys(pass);
		return pass;
	}

	public void ConfirmPassword(String confirmpass) {
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmPassword));
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys(confirmpass);
	}

	public void AccountName(String NameonAccount) {
		wait.until(ExpectedConditions.elementToBeClickable(AccountName));
		AccountName.clear();
		AccountName.sendKeys(NameonAccount);
	}

	public void BankingRouting(String routingNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(BankingRouting));
		BankingRouting.clear();
		BankingRouting.sendKeys(routingNumber);
	}

	public void BankingAccount(String BankAccountNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(BankingAccount));
		BankingAccount.clear();
		BankingAccount.sendKeys(BankAccountNumber);
	}

	public void ConfirmBankingAccount(String ConfirmBankAccountNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmBankingAccount));
		ConfirmBankingAccount.clear();
		ConfirmBankingAccount.sendKeys(ConfirmBankAccountNumber);
	}

}
