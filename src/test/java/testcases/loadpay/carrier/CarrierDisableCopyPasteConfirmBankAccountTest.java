package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

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
import pages.loadpay.carrier.CarrierRegisterCanada;

public class CarrierDisableCopyPasteConfirmBankAccountTest extends TestBase {

	CarrierLoginPage carrierloginobj;
	CarrierRegisterCanada carrierregisterobj;
	CarrierDisableCopyPasteConfirmBankAccount carrierdisablecopypasteconfirmbankaccountobj;
	CarrierBanking carrierbankingobj;
	JavascriptExecutor js;

	/*-------Initializing driver---------*/
	public CarrierDisableCopyPasteConfirmBankAccountTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		carrierloginobj = new CarrierLoginPage();
		carrierregisterobj = new CarrierRegisterCanada();
		carrierdisablecopypasteconfirmbankaccountobj = new CarrierDisableCopyPasteConfirmBankAccount();
		carrierbankingobj = new CarrierBanking();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Verify Copy/Paste functionality for confirm bank account field while registration---------*/
	@Test(description = "LP-6366 LoadPay Carrier_Disable_copy/paste_functionality_for_add_and_confirmbankaccount", dataProvider = "getCarrierData")
	public void verifyCopyPasteConfirmBankAccountFieldforRegisterTest(String Dotnumber, String CompanyName,
			String DoingBussinessAS, String Email, String ConfirmEmail, String country, String state, String ZipCode1,
			String Address, String City, String ocountry, String States, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		carrierregisterobj.signup();
		carrierregisterobj.CarrierRegister();
		if (Dotnumber == null) {
			carrierregisterobj.company(CompanyName);
		} else {

			carrierregisterobj.dotnumber(Dotnumber);
		}
		carrierregisterobj.doingbussiness(DoingBussinessAS);
		carrierregisterobj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		carrierregisterobj.countrydropdown(country, state);
		carrierregisterobj.CarrierEmail(Email);
		carrierregisterobj.confirmEmail(ConfirmEmail);
		carrierregisterobj.iCertifyClick();
		carrierregisterobj.clickNextBtnOnCompanyForm();
		if (Dotnumber == null) {
			carrierregisterobj.originCountry(ocountry, States);
			carrierregisterobj.ZipCode(ZipCode1);
			carrierregisterobj.address(Address);
			carrierregisterobj.city(City);

		} else {
			carrierregisterobj.originCountry(ocountry, States);
			carrierregisterobj.ZipCode(ZipCode1);
		}
		carrierregisterobj.clickNextBtnOnAddressForm();
		carrierregisterobj.ContactFirstName(FirstNames);
		carrierregisterobj.LastName(LastName);
		carrierregisterobj.Phone(PhoneNumber);
		carrierregisterobj.Password(Password);
		driver.findElement(By.xpath("//*[@id='Registration_User_Password']"));
		carrierregisterobj.ConfirmPassword(ConfirmPassword);
		carrierregisterobj.clickNextBtnOnContactForm();
		carrierregisterobj.AccountName(NameonAccount);
		carrierregisterobj.BankingRouting(RoutingNumber);
		carrierregisterobj.BankingAccount(BankAccountNumber);
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
		carrierloginobj.Carrierlogin(carrieremail, password);
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
		carrierloginobj.Carrierlogin(username, password);
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