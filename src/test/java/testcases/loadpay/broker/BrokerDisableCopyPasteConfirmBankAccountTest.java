package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.loadpay.broker.BrokerBanking;
import pages.loadpay.broker.BrokerDisableCopyPasteConfirmBankAccount;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerRegisterCanada;

public class BrokerDisableCopyPasteConfirmBankAccountTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	BrokerRegisterCanada brokerregisterobj;
	BrokerDisableCopyPasteConfirmBankAccount brokerdisablecopypasteconfirmbankaccountobj;
	BrokerBanking brokerbankingobj;
	Select typeofentity;
	JavascriptExecutor js;

	/*-------Initializing driver---------*/
	public BrokerDisableCopyPasteConfirmBankAccountTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		brokerloginobj = new BrokerLoginPage();
		brokerregisterobj = new BrokerRegisterCanada();
		brokerdisablecopypasteconfirmbankaccountobj = new BrokerDisableCopyPasteConfirmBankAccount();
		brokerbankingobj = new BrokerBanking();
		js = (JavascriptExecutor)driver;
	}

	@Test(dataProvider = "getBrokerData")
	public void verifyCopyPasteConfirmBankAccountFieldforRegister(String Dotnumber, String CompanyName,
			String DoingBussinessAS, String Email, String ConfirmEmail, String country, String state, String ZipCode1,
			String Address, String City, String ocountry, String States, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		brokerregisterobj.signup();
		brokerregisterobj.shipperRegister();
		if (Dotnumber == null) {
			brokerregisterobj.companyname(CompanyName);
			Thread.sleep(1000);
		} else {
			Thread.sleep(1000);
			brokerregisterobj.dotnumber(Dotnumber);
		}
		Thread.sleep(1000);
		brokerregisterobj.doingbussiness(DoingBussinessAS);
		brokerregisterobj.selectType();
		Thread.sleep(1000);
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		Thread.sleep(1000);
		brokerregisterobj.countrydropdown(country, state);
		brokerregisterobj.BrokerEmail(Email);
		brokerregisterobj.confirmEmail(ConfirmEmail);
		Thread.sleep(1000);
		brokerregisterobj.iCertifyClick();
		Thread.sleep(1000);
		brokerregisterobj.paymentTerm();
		Thread.sleep(1000);
		brokerregisterobj.next();
		if (Dotnumber == null) {
			Thread.sleep(1000);
			brokerregisterobj.originCountry(ocountry, States);
			Thread.sleep(1000);
			brokerregisterobj.ZipCode(ZipCode1);
			brokerregisterobj.address(Address);
			Thread.sleep(1000);
			brokerregisterobj.city(City);
			Thread.sleep(1000);
		} else {
			brokerregisterobj.originCountry(ocountry, States);
			brokerregisterobj.ZipCode(ZipCode1);
			Thread.sleep(1000);
		}

		brokerregisterobj.submit();
		Thread.sleep(1000);
		brokerregisterobj.ContactFirstName(FirstNames);
		brokerregisterobj.LastName(LastName);
		brokerregisterobj.Phone(PhoneNumber);
		brokerregisterobj.Password(Password);
		driver.findElement(By.xpath("//*[@id='Registration_User_Password']"));
		brokerregisterobj.ConfirmPassword(ConfirmPassword);
		Thread.sleep(1000);
		brokerregisterobj.Next();
		brokerregisterobj.AccountName(NameonAccount);
		brokerregisterobj.BankingRouting(RoutingNumber);
		brokerregisterobj.BankingAccount(BankAccountNumber);
		Thread.sleep(1000);
		brokerdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTypeofAccount();
		Thread.sleep(1000);
		Assert.assertTrue(
				brokerdisablecopypasteconfirmbankaccountobj.geterrorMessage().contains("Account Number do not match"),
				"Validation NOT found");

	}

	@Test(dataProvider = "getBrokerBankAccountData", dependsOnMethods = "verifyCopyPasteConfirmBankAccountFieldforRegister")
	public void brokerLogin(String brokeremail, String password, String accname, String routingnum, String accnumber,
			String confirmaccnumber) throws InterruptedException {
		driver.get(prop.getProperty("url"));
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
		brokerloginobj.Brokerlogin(username, password);

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "verifybrokerLogin")
	public void verifyCopyPasteBankAccountNumberinaddinBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerbankingobj.clickAccountlink();
		Thread.sleep(1000);
		brokerbankingobj.clickBankingLink();
		Thread.sleep(1000);
		brokerbankingobj.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		brokerdisablecopypasteconfirmbankaccountobj.verifyCopyPasteforTyesofAccount(accname, routingnum, accnum);
		Assert.assertEquals(brokerdisablecopypasteconfirmbankaccountobj.getConfirmAccountNumber(), "",
				"Copy/paste is happening");
		Assert.assertTrue(!brokerdisablecopypasteconfirmbankaccountobj.getSavebuton().isEnabled(),
				"Save button is enabled");

	}

}