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
import pages.loadpay.outlook.outlooklogin;
import pages.loadpay.unmatched.UnmatchedCarrierFuelCard;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class UnmatchedCarrierOutlookFuelCardTest extends TestBase {

	UnmatchedCarrierFuelCard umCarrierfuelcardObj;
	outlooklogin outlook;
	public static String umEmailAddress;
	public static String pwd;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public UnmatchedCarrierOutlookFuelCardTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		outlook = new outlooklogin();
		umCarrierfuelcardObj = new UnmatchedCarrierFuelCard();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {

		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		umCarrierfuelcardObj.clickPopUp();
		umCarrierfuelcardObj.clickOpenMailBox();
		umCarrierfuelcardObj.enterEmail(super.getProperties().getProperty("email"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		umCarrierfuelcardObj.outlookSearchInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(1), currentHour,
				currentMinutes);
		umCarrierfuelcardObj.handleNewInbox();
		umCarrierfuelcardObj.switchtoCarrieregistration();
		umCarrierfuelcardObj.unmatchedCarrierRegistration();

	}

	@Test(dataProvider = "getCarrierRegisterData", dependsOnMethods = "outlookloginTest")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getCompanyName()));
		umCarrierfuelcardObj.companyname(CompanyName);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getDoingbussiness()));
		umCarrierfuelcardObj.doingbussiness(DoingBussinessAS);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getTypeofEntity()));
		umCarrierfuelcardObj.selectType();
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getCountryIncorporation()));
		umCarrierfuelcardObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getStateIncorporation()));
		umCarrierfuelcardObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getIcertify()));
		umCarrierfuelcardObj.iCertifyClick();

		umCarrierfuelcardObj.clickNextBtnOnCompanyForm();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getZipCode()));
		umCarrierfuelcardObj.ZipCode(ZipCode1);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getCountry()));
		umCarrierfuelcardObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getAddress()));
		umCarrierfuelcardObj.address(Address);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getCity()));
		umCarrierfuelcardObj.city(City);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getState()));
		umCarrierfuelcardObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		umCarrierfuelcardObj.clickNextBtnOnAddressForm();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getContactFirstName()));
		umCarrierfuelcardObj.ContactFirstName(FirstNames);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getLastName()));
		umCarrierfuelcardObj.LastName(LastName);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getPhone()));
		umCarrierfuelcardObj.Phone(PhoneNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getPasswordd()));
		pwd = umCarrierfuelcardObj.Password(Password);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getConfirmPassword()));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		umCarrierfuelcardObj.ConfirmPassword(ConfirmPassword);

		umCarrierfuelcardObj.clickNextBtnOnContactForm();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getAccountName()));
		umCarrierfuelcardObj.AccountName(NameonAccount);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getBankingAccount()));
		umCarrierfuelcardObj.BankingAccount(BankAccountNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getBankingRouting()));
		umCarrierfuelcardObj.BankingRouting(RoutingNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierfuelcardObj.getConfirmBankingAccount()));
		umCarrierfuelcardObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		umCarrierfuelcardObj.clickNextBtnOnBankingForm();

	}
}
