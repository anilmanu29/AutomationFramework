package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;
import pages.loadpay.unmatched.UnmatchedCarrierOutlooksameDay;

public class UnmatchedCarrierOutlooksameDayTest extends TestBase {

	UnmatchedCarrierOutlooksameDay umCarrierOutlookObj;
	outlooklogin outlook;
	public static String pwd;

	public UnmatchedCarrierOutlooksameDayTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		outlook = new outlooklogin();
		umCarrierOutlookObj = new UnmatchedCarrierOutlooksameDay();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		umCarrierOutlookObj.clickPopUp();
		umCarrierOutlookObj.clickOpenMailBox();
		umCarrierOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		// umCarrierOutlookObj.clickOpen();
		umCarrierOutlookObj.handleNewInbox();
		umCarrierOutlookObj.switchtoCarrieregistration();
		umCarrierOutlookObj.unmatchedCarrierRegistration();

	}

	@Test(dataProvider = "getCarrierRegisterData", dependsOnMethods = "outlookloginTest")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getCompanyName()));
		umCarrierOutlookObj.companyname(CompanyName);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getDoingbussiness()));
		umCarrierOutlookObj.doingbussiness(DoingBussinessAS);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getTypeofEntity()));
		umCarrierOutlookObj.selectType();
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getCountryIncorporation()));
		umCarrierOutlookObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getStateIncorporation()));
		umCarrierOutlookObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getIcertify()));
		umCarrierOutlookObj.iCertifyClick();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getNext()));
		umCarrierOutlookObj.next();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getZipCode()));
		umCarrierOutlookObj.ZipCode(ZipCode1);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getCountry()));
		umCarrierOutlookObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getAddress()));
		umCarrierOutlookObj.address(Address);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getCity()));
		umCarrierOutlookObj.city(City);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getState()));
		umCarrierOutlookObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getSubmit()));
		umCarrierOutlookObj.submit();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getContactFirstName()));
		umCarrierOutlookObj.ContactFirstName(FirstNames);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getLastName()));
		umCarrierOutlookObj.LastName(LastName);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getPhone()));
		umCarrierOutlookObj.Phone(PhoneNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getPasswordd()));
		pwd = umCarrierOutlookObj.Password(Password);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getConfirmPassword()));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		umCarrierOutlookObj.ConfirmPassword(ConfirmPassword);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getNext()));
		umCarrierOutlookObj.Next();

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getAccountName()));
		umCarrierOutlookObj.AccountName(NameonAccount);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getBankingAccount()));
		umCarrierOutlookObj.BankingAccount(BankAccountNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getBankingRouting()));
		umCarrierOutlookObj.BankingRouting(RoutingNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getConfirmBankingAccount()));
		umCarrierOutlookObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		wait.until(ExpectedConditions.elementToBeClickable(umCarrierOutlookObj.getSubmit()));
		umCarrierOutlookObj.submit();

	}

}
