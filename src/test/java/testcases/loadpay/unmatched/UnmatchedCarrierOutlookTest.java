package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class UnmatchedCarrierOutlookTest extends TestBase {

	OutlookFunctions outlook;

	String unmatchedCarrierEmail, invoiceNum, loadId, Amount, PayTo, EIN = "";
	public static String pwd;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public UnmatchedCarrierOutlookTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		TestUtil.className = this.getClass().getName();
		outlook = new OutlookFunctions();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier")
	public void getUnmatchedCarrierData(String cemail, String invoiceno, String loadid, String amt, String payto,
			String ein) throws InterruptedException {
		unmatchedCarrierEmail = cemail;
		invoiceNum = invoiceno;
		loadId = loadid;
		Amount = amt;
		PayTo = payto;
		EIN = ein;

	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlook.clickPopUp();
		outlook.clickOpenMailBox();
		outlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		outlook.outlookSearchInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(1), currentHour, currentMinutes);
		outlook.handleNewInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(1));
		outlook.switchtoCarrieregistration();
		outlook.unmatchedCarrierRegistration();

	}

	@Test(dataProvider = "getCarrierRegisterData", dependsOnMethods = "getUnmatchedCarrierData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		outlook.clickRegisterHereBtn();
		outlook.clickCarrierSignupBtn();

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getCompanyName()));
		outlook.companyname(CompanyName);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getDoingbussiness()));
		outlook.doingbussiness(DoingBussinessAS);

		outlook.selectType();
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getCountryIncorporation()));
		outlook.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getStateIncorporation()));
		outlook.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		outlook.CarrierEmail(unmatchedCarrierEmail);
		outlook.confirmEmail(unmatchedCarrierEmail);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getIcertify()));
		outlook.iCertifyClick();

		outlook.clickNextBtnOnCompanyForm();

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getZipCode()));
		outlook.ZipCode(ZipCode1);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getCountry()));
		outlook.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getAddress()));
		outlook.address(Address);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getCity()));
		outlook.city(City);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getState()));

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		outlook.clickNextBtnOnAddressForm();

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getContactFirstName()));
		outlook.ContactFirstName(FirstNames);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getLastName()));
		outlook.LastName(LastName);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getPhone()));
		outlook.Phone(PhoneNumber);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getPasswordd()));
		pwd = outlook.Password(Password);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getConfirmPassword()));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		outlook.ConfirmPassword(ConfirmPassword);

		outlook.clickNextBtnOnContactForm();

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getAccountName()));
		outlook.AccountName(NameonAccount);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getBankingAccount()));
		outlook.BankingAccount(BankAccountNumber);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getBankingRouting()));
		outlook.BankingRouting(RoutingNumber);

		wait.until(ExpectedConditions.elementToBeClickable(outlook.getConfirmBankingAccount()));
		outlook.ConfirmBankingAccount(ConfirmbankAccountNumber);

		outlook.clickNextBtnOnBankingForm();

	}

}
