package pages.loadpay.broker;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerRegisterCanada extends TestBase {

	Select slc, sls, sloc, slos;

	@FindBy(xpath = "//a[@href='/Account/Register']")
	WebElement buttonsignup;

	@FindBy(xpath = "//button[text()='Shipper/Broker']")
	WebElement buttonbrokersignup;

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

	@FindBy(xpath = ".//*[@id='IncorporationState']")
	WebElement stateIncorporation;

	@FindBy(id = "Registration_User_UserName")
	static WebElement field_email;

	@FindBy(id = "Registration_User_UserNameConfirm")
	WebElement field_confirmemail;

	@FindBy(id = "ICertify")
	WebElement checkboxicertify;

	@FindBy(xpath = "//*[@id='PMNTerm'] ")
	WebElement paymentTerms;

	@FindBy(id = "ZipCode")
	WebElement field_zipCode;

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

	@FindBy(id = "IncorporationCountry")
	private WebElement dropdown_country;

	@FindBy(id = "IncorporationState")
	private WebElement dropdown_states;

	@FindBy(xpath = ".//*[@id='State']")
	WebElement originstate;

	@FindBy(id = "OriginCountry")
	WebElement originCountrydropdown;

	@FindBy(xpath = ".//*[@id='formCompany']/div/div[13]/div/div/input")
	WebElement CompanyNextBtn;

	@FindBy(xpath = ".//*[@id='formAddress']/div/div[4]/div/div[2]/input")
	WebElement AddressNextBtn;

	@FindBy(xpath = ".//*[@id='formContact']/div[2]/div/div[2]/input")
	WebElement ContactNextBtn;

	@FindBy(xpath = ".//*[@id='formBanking']/div/div[8]/div/div/div[2]/input")
	WebElement BankingNextBtn;

	public BrokerRegisterCanada() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void signup() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonsignup));
		buttonsignup.click();
	}

	public void shipperRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonbrokersignup));
		buttonbrokersignup.click();
	}

	public void dotnumber(String Dot) {
		wait.until(ExpectedConditions.elementToBeClickable(field_dotnumber));
		field_dotnumber.clear();
		field_dotnumber.sendKeys(Dot);
	}

	public void companyname(String Company) {
		wait.until(ExpectedConditions.elementToBeClickable(field_companyname));
		field_companyname.clear();
		field_companyname.sendKeys(Company);
	}

	public void doingbussiness(String doingBussiness) {
		wait.until(ExpectedConditions.elementToBeClickable(field_doingbussiness));
		field_doingbussiness.clear();
		field_doingbussiness.sendKeys(doingBussiness);
	}

	public void selectType() {
		wait.until(ExpectedConditions.elementToBeClickable(dropdowntypeofentity));
		dropdowntypeofentity.click();

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
				break;
			}
		}
	}

	public String BrokerEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(field_email));
		field_email.clear();
		field_email.sendKeys(email);
		return email;
	}

	public void confirmEmail(String ConfirmEmail) {
		wait.until(ExpectedConditions.elementToBeClickable(field_confirmemail));
		field_confirmemail.clear();
		field_confirmemail.sendKeys(ConfirmEmail);

	}

	public void iCertifyClick() {
		wait.until(ExpectedConditions.elementToBeClickable(checkboxicertify));
		checkboxicertify.click();
	}

	public void paymentTerm() {
		wait.until(ExpectedConditions.elementToBeClickable(paymentTerms));
		paymentTerms.click();
		Select pay = new Select(paymentTerms);
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
		wait.until(ExpectedConditions.elementToBeClickable(field_zipCode));
		field_zipCode.clear();
		field_zipCode.sendKeys(ZipCode1);
	}

	public void originCountry(String country, String stat) {
		wait.until(ExpectedConditions.elementToBeClickable(originCountrydropdown));
		sloc = new Select(originCountrydropdown);
		sloc.selectByVisibleText(country);
		slos = new Select(originstate);
		slos.selectByVisibleText(stat);

	}

	public void country() {
		wait.until(ExpectedConditions.elementToBeClickable(Country));
		Country.click();
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

	public void State() {
		wait.until(ExpectedConditions.elementToBeClickable(State));
		State.clear();
		State.sendKeys();
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
