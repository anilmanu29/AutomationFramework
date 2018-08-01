package pages.loadpay.broker;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class BrokerRegister extends TestBase {

	@FindBy(xpath = ".//*[@id='page-main']/div/div/div[2]/div/div/div[2]/div[2]/div/a")
	WebElement signupButton;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div[3]/div/button[2]")
	WebElement BrokersignupButton;

	@FindBy(xpath = "//*[@id='MCType']")
	WebElement motorCarrierSelector;

	@FindBy(xpath = "//*[@id='MC']")
	WebElement motorCarrierField;

	@FindBy(xpath = ".//*[@id='DOT'] ")
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

	@FindBy(xpath = ".//*[@id='formCompany']/div/div[13]/div/div/input")
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

	@FindBy(xpath = "//*[@id='angularScope']/div[4]/div[3]/div[5]/div/p[1]")
	WebElement BrokerRegistrationConfirmation;

	@FindBy(className = "close")
	WebElement emailAddressAlreadyInUse;

	public BrokerRegister() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void signup() {
		wait.until(ExpectedConditions.elementToBeClickable(signupButton));
		signupButton.click();
	}

	public void shipperRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(BrokersignupButton));
		BrokersignupButton.click();
	}

	public void dotnumber(String Dot) {
		wait.until(ExpectedConditions.visibilityOf(Dotnumber));
		Dotnumber.sendKeys(Dot);
	}

	public void companyname(String Company) {
		wait.until(ExpectedConditions.visibilityOf(CompanyName));
		CompanyName.sendKeys(Company);
	}

	public void doingbussiness(String doingBussiness) {
		wait.until(ExpectedConditions.visibilityOf(doingbussiness));
		doingbussiness.sendKeys(doingBussiness);
	}

	public void selectType() {
		wait.until(ExpectedConditions.elementToBeClickable(TypeofEntity));
		TypeofEntity.click();

	}

	public void countryofincorporation() {
		wait.until(ExpectedConditions.elementToBeClickable(countryIncorporation));
		countryIncorporation.click();
	}

	public void stateofincorporation() {
		wait.until(ExpectedConditions.elementToBeClickable(stateIncorporation));
		stateIncorporation.click();
	}

	public String BrokerEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(Email));
		Email.sendKeys(email);
		return email;
	}

	public void confirmEmail(String ConfirmEmail) {
		wait.until(ExpectedConditions.visibilityOf(confirmEmail));
		confirmEmail.sendKeys(ConfirmEmail);

	}

	public void iCertifyClick() {
		wait.until(ExpectedConditions.elementToBeClickable(Icertify));
		Icertify.click();
	}

	public void paymentTerm() throws InterruptedException {
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
		wait.until(ExpectedConditions.visibilityOf(ZipCode));
		ZipCode.sendKeys(ZipCode1);
	}

	public void country() {
		wait.until(ExpectedConditions.elementToBeClickable(Country));
		Country.click();
	}

	public void address(String Address) {
		wait.until(ExpectedConditions.visibilityOf(address));
		address.sendKeys(Address);
	}

	public void city(String City) {
		wait.until(ExpectedConditions.visibilityOf(city));
		city.sendKeys(City);
	}

	public void State() {
		wait.until(ExpectedConditions.visibilityOf(State));
		State.sendKeys();
	}

	public void ContactFirstName(String FirstName) {
		wait.until(ExpectedConditions.visibilityOf(ContactFirstName));
		ContactFirstName.sendKeys(FirstName);
	}

	public void LastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(LastName));
		LastName.sendKeys(lastName);
	}

	public void Phone(String PhoneNumber) {
		wait.until(ExpectedConditions.visibilityOf(Phone));
		Phone.sendKeys(PhoneNumber);
	}

	public void Password(String pass) {
		wait.until(ExpectedConditions.visibilityOf(Password));
		Password.sendKeys(pass);
	}

	public void ConfirmPassword(String confirmpass) {
		wait.until(ExpectedConditions.visibilityOf(ConfirmPassword));
		ConfirmPassword.sendKeys(confirmpass);
	}

	public void AccountName(String NameonAccount) {
		wait.until(ExpectedConditions.visibilityOf(AccountName));
		AccountName.sendKeys(NameonAccount);
	}

	public void BankingRouting(String routingNumber) {
		wait.until(ExpectedConditions.visibilityOf(BankingRouting));
		BankingRouting.sendKeys(routingNumber);
	}

	public void BankingAccount(String BankAccountNumber) {
		wait.until(ExpectedConditions.visibilityOf(BankingAccount));
		BankingAccount.sendKeys(BankAccountNumber);
	}

	public void ConfirmBankingAccount(String ConfirmBankAccountNumber) {
		wait.until(ExpectedConditions.visibilityOf(ConfirmBankingAccount));
		ConfirmBankingAccount.sendKeys(ConfirmBankAccountNumber);
	}

	public void verifyErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(emailAddressAlreadyInUse));
		Assert.assertTrue(emailAddressAlreadyInUse.isDisplayed(), "No error message is present");
	}

	public void verifyRegistrationConfirmationMessage() {
		wait.until(ExpectedConditions.visibilityOf(BrokerRegistrationConfirmation));
		Assert.assertTrue(BrokerRegistrationConfirmation.isDisplayed(), "No confirmation message is present");
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