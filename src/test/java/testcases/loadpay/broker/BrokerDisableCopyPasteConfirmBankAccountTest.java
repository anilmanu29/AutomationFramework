package testcases.loadpay.broker;

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
import pages.loadpay.broker.BrokerBanking;
import pages.loadpay.broker.BrokerDisableCopyPasteConfirmBankAccount;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerRegister;
import util.TestUtil;

public class BrokerDisableCopyPasteConfirmBankAccountTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	BrokerRegister brokerRegistrationObj;
	BrokerDisableCopyPasteConfirmBankAccount brokerdisablecopypasteconfirmbankaccountobj;
	BrokerBanking brokerbankingobj;
	Select typeofentity;
	JavascriptExecutor js;
	public static String brokerUsername;
	public static String brokerPassword;
	public static String brokerCompanyName;

	/*-------Initializing driver---------*/
	public BrokerDisableCopyPasteConfirmBankAccountTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokerloginobj = new BrokerLoginPage();
		brokerRegistrationObj = new BrokerRegister();
		brokerdisablecopypasteconfirmbankaccountobj = new BrokerDisableCopyPasteConfirmBankAccount();
		brokerbankingobj = new BrokerBanking();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void verifyCopyPasteConfirmBankAccountFieldforRegister(String Dotnumber, String CompanyName,
			String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
			String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword,
			String NameonAccount, String RoutingNumber, String BankAccountNumber, String ConfirmbankAccountNumber)
			throws IOException, InterruptedException {

		if (Email.contains("[uniqueID]")) {
			String uniqueEmail = Email.replace("[uniqueID]", TestUtil.getCurrentDateTime());
			brokerUsername = uniqueEmail;
			brokerPassword = Password;
			brokerCompanyName = CompanyName;
		} else {
			brokerUsername = Email;
			brokerPassword = Password;
		}

		brokerRegistrationObj.signup();

		// clicking on carrier Register
		brokerRegistrationObj.shipperRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		brokerRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		brokerRegistrationObj.setMotorCarrierField(randomNum);

		brokerRegistrationObj.companyname(CompanyName);
		brokerRegistrationObj.doingbussiness(DoingBussinessAS);
		brokerRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brokerRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		brokerRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		brokerRegistrationObj.BrokerEmail(brokerUsername);
		brokerRegistrationObj.confirmEmail(brokerUsername);
		brokerRegistrationObj.iCertifyClick();
		brokerRegistrationObj.paymentTerm();

		brokerRegistrationObj.clickNextBtnOnCompanyForm();

		brokerRegistrationObj.ZipCode(ZipCode1);
		brokerRegistrationObj.country();

		Select countrys = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		countrys.selectByVisibleText("USA");

		brokerRegistrationObj.address(Address);
		brokerRegistrationObj.city(City);
		brokerRegistrationObj.State();

		Select states = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		states.selectByVisibleText("CA");

		brokerRegistrationObj.clickNextBtnOnAddressForm();

		brokerRegistrationObj.ContactFirstName(FirstNames);
		brokerRegistrationObj.LastName(LastName);
		brokerRegistrationObj.Phone(PhoneNumber);
		brokerRegistrationObj.Password(Password);
		brokerRegistrationObj.ConfirmPassword(ConfirmPassword);

		brokerRegistrationObj.clickNextBtnOnContactForm();

		brokerRegistrationObj.AccountName(NameonAccount);
		brokerRegistrationObj.BankingRouting(RoutingNumber);
		brokerRegistrationObj.BankingAccount(BankAccountNumber);
		// brokerRegistrationObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		brokerdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTypeofAccount();

		Assert.assertTrue(brokerdisablecopypasteconfirmbankaccountobj.isErrorDisplayed(),
				"Error message not found for confirmation account number");

	}

	@Test(dataProvider = "getBrokerBankAccountData", dependsOnMethods = "verifyCopyPasteConfirmBankAccountFieldforRegister")
	public void brokerLogin(String brokeremail, String password, String accname, String routingnum, String accnumber,
			String confirmaccnumber) throws InterruptedException {
		driver.get(prop.getProperty("loadPayURL"));
		brokerloginobj.Brokerlogin(brokeremail, password);
	}

	@Test(dataProvider = "getBrokerBankAccountData", dependsOnMethods = "brokerLogin")
	public void verifyCopyPasteConfirmBankAccountFieldforExistingBroker(String brokeremail, String password,
			String accname, String routingnum, String accnumber, String confirmaccnumber)
			throws InterruptedException, IOException {
		// verify Bank Details field
		Assert.assertTrue(brokerdisablecopypasteconfirmbankaccountobj.getAccountNameField().isEnabled(),
				"Account Name field is Disabled!");
		Assert.assertTrue(brokerdisablecopypasteconfirmbankaccountobj.getRoutingNumberField().isEnabled(),
				"Routing Number field is Disabled!");
		Assert.assertTrue(brokerdisablecopypasteconfirmbankaccountobj.getAccountNumberField().isEnabled(),
				"Account Number field is Disabled!");
		Assert.assertTrue(brokerdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumberField().isEnabled(),
				"Confirm Account Number field is Disabled!");
		js.executeScript("window.scrollBy(0,100)");
		brokerdisablecopypasteconfirmbankaccountobj.verifyCopyPastefornrokerTypeofAccount(accname, routingnum,
				accnumber);
		Assert.assertEquals(brokerdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumber(), "",
				"Copy/paste is happening");
		Assert.assertTrue(!brokerdisablecopypasteconfirmbankaccountobj.getNextButton().isEnabled(),
				"Next button is enabled");
		brokerloginobj.BrokerLogout();
	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyCopyPasteConfirmBankAccountFieldforExistingBroker")
	public void verifybrokerLogin(String username, String password) throws InterruptedException, IOException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = username;
			brokerPassword = password;
		}

		brokerloginobj.Brokerlogin(brokerUsername, brokerPassword);

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "verifybrokerLogin")
	public void verifyCopyPasteBankAccountNumberinaddinBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerbankingobj.clickAccountlink();

		brokerbankingobj.clickBankingLink();

		brokerbankingobj.clickAddNewBankAccountLink();

		brokerdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTyesofAccount(accname, routingnum, accnum);
		Assert.assertEquals(brokerdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumber(), "",
				"Copy/paste is happening");
		Assert.assertTrue(!brokerdisablecopypasteconfirmbankaccountobj.getSavebuton().isEnabled(),
				"Save button is enabled");

	}

}