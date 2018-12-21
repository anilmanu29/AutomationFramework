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
import pages.loadpay.unmatched.UnmatchedCarrierOutlooksameDay;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class UnmatchedCarrierOutlooksameDayTest extends TestBase {

	UnmatchedCarrierOutlooksameDay umOutlookFunctionsObj;
	OutlookFunctions outlook;
	public static String umEmailAddress;
	public static String pwd;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public UnmatchedCarrierOutlooksameDayTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		TestUtil.className = this.getClass().getName();
		outlook = new OutlookFunctions();
		umOutlookFunctionsObj = new UnmatchedCarrierOutlooksameDay();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		umOutlookFunctionsObj.clickPopUp();
		umOutlookFunctionsObj.clickOpenMailBox();
		umOutlookFunctionsObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		// umOutlookFunctionsObj.clickOpen();
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		umOutlookFunctionsObj.outlookSearchInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(0), currentHour,
				currentMinutes);
		umOutlookFunctionsObj.handleNewInbox();
		umOutlookFunctionsObj.switchtoCarrieregistration();
		umOutlookFunctionsObj.unmatchedCarrierRegistration();

	}

	@Test(dataProvider = "getCarrierRegisterData", dependsOnMethods = "outlookloginTest")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getCompanyName()));
		umOutlookFunctionsObj.companyname(CompanyName);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getDoingbussiness()));
		umOutlookFunctionsObj.doingbussiness(DoingBussinessAS);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getTypeofEntity()));
		umOutlookFunctionsObj.selectType();
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getCountryIncorporation()));
		umOutlookFunctionsObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getStateIncorporation()));
		umOutlookFunctionsObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getIcertify()));
		umOutlookFunctionsObj.iCertifyClick();

		umOutlookFunctionsObj.clickNextBtnOnCompanyForm();

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getZipCode()));
		umOutlookFunctionsObj.ZipCode(ZipCode1);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getCountry()));
		umOutlookFunctionsObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getAddress()));
		umOutlookFunctionsObj.address(Address);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getCity()));
		umOutlookFunctionsObj.city(City);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getState()));
		umOutlookFunctionsObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		umOutlookFunctionsObj.clickNextBtnOnAddressForm();

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getContactFirstName()));
		umOutlookFunctionsObj.ContactFirstName(FirstNames);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getLastName()));
		umOutlookFunctionsObj.LastName(LastName);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getPhone()));
		umOutlookFunctionsObj.Phone(PhoneNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getPasswordd()));
		pwd = umOutlookFunctionsObj.Password(Password);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getConfirmPassword()));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		umOutlookFunctionsObj.ConfirmPassword(ConfirmPassword);

		umOutlookFunctionsObj.clickNextBtnOnContactForm();

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getAccountName()));
		umOutlookFunctionsObj.AccountName(NameonAccount);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getBankingAccount()));
		umOutlookFunctionsObj.BankingAccount(BankAccountNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getBankingRouting()));
		umOutlookFunctionsObj.BankingRouting(RoutingNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umOutlookFunctionsObj.getConfirmBankingAccount()));
		umOutlookFunctionsObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		umOutlookFunctionsObj.clickNextBtnOnBankingForm();

	}

}
