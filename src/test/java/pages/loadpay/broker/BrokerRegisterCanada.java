package pages.loadpay.broker;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class BrokerRegisterCanada extends TestBase {

	Select slc, sls, sloc, slos;

	@FindBy(xpath = "//a[text()='Sign Up']")
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

	@FindBy(xpath = "//form[@id='formCompany']//child::input[@type='submit']")
	WebElement buttonnext;

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

	@FindBy(id = "IncorporationCountry")
	private WebElement dropdown_country;

	@FindBy(id = "IncorporationState")
	private WebElement dropdown_states;

	@FindBy(xpath = ".//*[@id='State']")
	WebElement originstate;

	@FindBy(id = "OriginCountry")
	WebElement originCountrydropdown;

	public BrokerRegisterCanada() {
		PageFactory.initElements(driver, this);
	}

	public void signup() {
		buttonsignup.click();
	}

	public void shipperRegister() {
		buttonbrokersignup.click();
	}

	public void dotnumber(String Dot) {
		field_dotnumber.sendKeys(Dot);
	}

	public void companyname(String Company) {
		field_companyname.sendKeys(Company);
	}

	public void doingbussiness(String doingBussiness) {
		field_doingbussiness.sendKeys(doingBussiness);
	}

	public void selectType() {
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
				Thread.sleep(1000);
				break;
			}
		}
	}

	public String BrokerEmail(String email) {
		field_email.sendKeys(email);
		return email;
	}

	public void confirmEmail(String ConfirmEmail) {
		field_confirmemail.sendKeys(ConfirmEmail);

	}

	public void iCertifyClick() {
		checkboxicertify.click();
	}

	public void paymentTerm() {
		paymentTerms.click();
		Select pay = new Select(paymentTerms);
		pay.selectByIndex(2);
	}

	public void next() {
		buttonnext.click();
	}

	public void ZipCode(String ZipCode1) {
		field_zipCode.clear();
		field_zipCode.sendKeys(ZipCode1);
	}

	public void originCountry(String country, String stat) {
		sloc = new Select(originCountrydropdown);
		sloc.selectByVisibleText(country);
		slos = new Select(originstate);
		slos.selectByVisibleText(stat);

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
