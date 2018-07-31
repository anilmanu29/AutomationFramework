package testcases.loadpay.broker;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerRegister;

public class BrokerRegisterTest extends TestBase {
	BrokerRegister brokerRegistrationObj;

	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;

	public static String emailid;

	String brokerUsername;
	String brokerPassword;
	String outlookUsername;
	String outlookPassword;

	public BrokerRegisterTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerRegistrationObj = new BrokerRegister();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		brokerUsername = Email;
		brokerPassword = Password;

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

		emailid = brokerRegistrationObj.BrokerEmail(Email);
		brokerRegistrationObj.confirmEmail(ConfirmEmail);
		brokerRegistrationObj.iCertifyClick();
		brokerRegistrationObj.paymentTerm();

		brokerRegistrationObj.clickNextBtnOnCompanyForm();

		brokerRegistrationObj.ZipCode(ZipCode1);
		brokerRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		brokerRegistrationObj.address(Address);
		brokerRegistrationObj.city(City);
		brokerRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		brokerRegistrationObj.clickNextBtnOnAddressForm();

		brokerRegistrationObj.ContactFirstName(FirstNames);
		brokerRegistrationObj.LastName(LastName);
		brokerRegistrationObj.Phone(PhoneNumber);
		brokerRegistrationObj.Password(Password);
		brokerRegistrationObj.ConfirmPassword(ConfirmPassword);

		brokerRegistrationObj.clickNextBtnOnContactForm();

		brokerRegistrationObj.AccountName(NameonAccount);
		brokerRegistrationObj.BankingRouting(RoutingNumber);
		brokerRegistrationObj.BankingRouting(RoutingNumber);
		brokerRegistrationObj.BankingAccount(BankAccountNumber);
		brokerRegistrationObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		brokerRegistrationObj.clickNextBtnOnBankingForm();

		brokerRegistrationObj.verifyRegistrationConfirmationMessage();

		log.info(" Broker Register Completed...");
	}
}
