package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierBanking;
import pages.loadpay.carrier.CarrierDisableCopyPasteConfirmBankAccount;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierRegisterPage;
import util.TestUtil;

public class CarrierDisableCopyPasteConfirmBankAccountTest extends TestBase {

	CarrierLoginPage carrierloginobj;
	CarrierRegisterPage carrierRegistrationObj;
	CarrierDisableCopyPasteConfirmBankAccount carrierdisablecopypasteconfirmbankaccountobj;
	CarrierBanking carrierbankingobj;
	JavascriptExecutor js;
	public static String carrierUsername;
	public static String carrierPassword;
	public static String companyname;

	/*-------Initializing driver---------*/
	public CarrierDisableCopyPasteConfirmBankAccountTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		carrierloginobj = new CarrierLoginPage();
		carrierRegistrationObj = new CarrierRegisterPage();
		carrierdisablecopypasteconfirmbankaccountobj = new CarrierDisableCopyPasteConfirmBankAccount();
		carrierbankingobj = new CarrierBanking();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Verify Copy/Paste functionality for confirm bank account field while registration---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierRegisterData")
	public void verifyCopyPasteConfirmBankAccountFieldforRegisterTest(String Dotnumber, String CompanyName,
			String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
			String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword,
			String NameonAccount, String RoutingNumber, String BankAccountNumber, String ConfirmbankAccountNumber)
			throws IOException, InterruptedException {

		if (Email.contains("[uniqueID]")) {
			String uniqueEmail = Email.replace("[uniqueID]", TestUtil.getCurrentDateTime());
			carrierUsername = uniqueEmail;
			carrierPassword = Password;
		} else {
			carrierUsername = Email;
			carrierPassword = Password;
		}

		companyname = "carrier";

		carrierRegistrationObj.signup();

		// clicking on carrier Register
		carrierRegistrationObj.CarrierRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		carrierRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		carrierRegistrationObj.setMotorCarrierField(randomNum);

		carrierRegistrationObj.companyname(companyname);

		carrierRegistrationObj.doingbussiness(DoingBussinessAS);

		carrierRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));

		type.selectByVisibleText("C Corporation");

		carrierRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));

		countryof.selectByIndex(0);

		carrierRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));

		stateof.selectByVisibleText("California");

		carrierRegistrationObj.CarrierEmail(carrierUsername);

		carrierRegistrationObj.confirmEmail(carrierUsername);

		carrierRegistrationObj.iCertifyClick();

		carrierRegistrationObj.clickNextBtnOnCompanyForm();

		carrierRegistrationObj.ZipCode(ZipCode1);

		carrierRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		carrierRegistrationObj.address(Address);

		carrierRegistrationObj.city(City);

		carrierRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));

		state.selectByVisibleText("CA");

		carrierRegistrationObj.clickNextBtnOnAddressForm();

		carrierRegistrationObj.ContactFirstName(FirstNames);

		carrierRegistrationObj.LastName(LastName);
		carrierRegistrationObj.Phone(PhoneNumber);

		carrierRegistrationObj.Password(carrierPassword);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		carrierRegistrationObj.ConfirmPassword(carrierPassword);

		carrierRegistrationObj.clickNextBtnOnContactForm();
		carrierRegistrationObj.AccountName(NameonAccount);
		carrierRegistrationObj.BankingRouting(RoutingNumber);

		carrierRegistrationObj.BankingAccount(BankAccountNumber);

		carrierdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTypeofAccount();
		Assert.assertTrue(
				carrierdisablecopypasteconfirmbankaccountobj.geterrorMessage().contains("Account Number do not match"),
				"ValidationMessage NOT found");
		log.info("verifyCopyPasteConfirmBankAccountFieldforRegisterTest - Passed");

	}

	/*-------carrier login test---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierBankAccountData", dependsOnMethods = "verifyCopyPasteConfirmBankAccountFieldforRegisterTest")
	public void carrierLoginTest(String carrieremail, String password, String accname, String routingnum,
			String accnumber, String confirmaccnumber) throws InterruptedException {
		driver.get(prop.getProperty("url"));

		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
		log.info("carrierLoginTest - Passed");
	}

	/*-------Verify Copy/Paste functionality for confirm bank account field for Carrier First Login---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierBankAccountData", dependsOnMethods = "carrierLoginTest")
	public void verifyCopyPasteConfirmBankAccountFieldforExistingCarrierTest(String carrieremail, String password,
			String accname, String routingnum, String accnumber, String confirmaccnumber)
			throws InterruptedException, IOException {
		// verify Bank Details field
		Assert.assertTrue(carrierdisablecopypasteconfirmbankaccountobj.getAccountNameField().isEnabled(),
				"Account Name field is Disabled!");
		Assert.assertTrue(carrierdisablecopypasteconfirmbankaccountobj.getRoutingNumberField().isEnabled(),
				"Routing Number field is Disabled!");
		Assert.assertTrue(carrierdisablecopypasteconfirmbankaccountobj.getAccountNumberField().isEnabled(),
				"Account Number field is Disabled!");
		Assert.assertTrue(carrierdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumberField().isEnabled(),
				"Confirm Account Number field is Disabled!");
		js.executeScript("window.scrollBy(0,100)");
		carrierdisablecopypasteconfirmbankaccountobj.verifyCopyPastefornrokerTypeofAccount(accname, routingnum,
				accnumber);
		Assert.assertEquals(carrierdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumber(), "",
				"Copy/paste is happening");
		Assert.assertTrue(!carrierdisablecopypasteconfirmbankaccountobj.getNextButton().isEnabled(),
				"Next button is enabled");
		carrierloginobj.CarrierLogout();
		log.info("verifyCopyPasteConfirmBankAccountFieldforExistingCarrierTest - Passed");
	}

	/*-------carrier login test---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierLoginData", dependsOnMethods = "verifyCopyPasteConfirmBankAccountFieldforExistingCarrierTest")
	public void carrierLogin(String username, String password) throws InterruptedException, IOException {
		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
		log.info("carrierLogin - Passed");
	}

	/*-------Verify Copy/Paste functionality for confirm bank account field for Adding Bank Account---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierBankingData", dependsOnMethods = "carrierLogin")
	public void verifyCopyPasteBankAccountNumberinaddingBankAccountTest(String accname, String routingnum,
			String accnum, String confirmaccnum) throws InterruptedException {
		carrierbankingobj.clickAccountlink();
		carrierbankingobj.clickBankingLink();
		carrierbankingobj.clickAddNewBankAccountLink();
		carrierdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTyesofAccount(accname, routingnum, accnum);
		Assert.assertEquals(carrierdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumber(), "",
				"Copy/paste is happening");
		Assert.assertTrue(!carrierdisablecopypasteconfirmbankaccountobj.getSavebuton().isEnabled(),
				"Save button is enabled");
		log.info("verifyCopyPasteBankAccountNumberinaddingBankAccountTest - Passed");
	}

}