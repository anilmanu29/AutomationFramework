package testcases.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerRegisterCanada;
import util.TestUtil;

public class BrokerRegisterCanadaTest extends TestBase {
	BrokerRegisterCanada brc;
	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;
	public static String brokerUsername;
	public static String brokerPassword;

	public BrokerRegisterCanadaTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		brc = new BrokerRegisterCanada();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City,
			String ocountry, String States, String FirstNames, String LastName, String PhoneNumber, String Password,
			String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
			String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			String[] emailArray = Email.split("@");
			emailArray[0] = emailArray[0] + TestUtil.getCurrentDateTime();

			brokerUsername = emailArray[0] + "@" + emailArray[1];
			brokerPassword = Password;
		} else {
			brokerUsername = Email;
			brokerPassword = Password;
		}

		brc.signup();
		brc.shipperRegister();

		if (Dotnumber == null) {
			brc.companyname(CompanyName);

		} else {

			brc.dotnumber(Dotnumber);
		}

		brc.doingbussiness(DoingBussinessAS);
		brc.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brc.countrydropdown(country, state);

		// Select countryof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationCountry']" ) ) );
		//
		// countryof.selectByIndex( 0 );

		// r.stateofincorporation();

		// Select stateof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationState']" ) ) );
		//
		// stateof.selectByVisibleText( "California" );
		brc.BrokerEmail(brokerUsername);
		brc.confirmEmail(brokerUsername);

		brc.iCertifyClick();

		brc.paymentTerm();

		brc.clickNextBtnOnCompanyForm();

		if (Dotnumber == null) {

			brc.originCountry(ocountry, States);

			brc.ZipCode(ZipCode1);
			brc.address(Address);

			brc.city(City);

		} else {
			brc.originCountry(ocountry, States);
			brc.ZipCode(ZipCode1);

		}

		// r.country();

		// Select country = new Select( driver.findElement( By.xpath(
		// ".//*[@id='OriginCountry']" ) ) );
		// country.selectByVisibleText( "USA" );

		// r.address( Address );
		// r.city( City );

		// r.State();

		// Select state = new Select( driver.findElement( By.xpath( ".//*[@id='State']"
		// ) ) );

		// state.selectByVisibleText( "CA" );

		brc.clickNextBtnOnAddressForm();

		brc.ContactFirstName(FirstNames);

		brc.LastName(LastName);
		brc.Phone(PhoneNumber);

		brc.Password(brokerPassword);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		brc.ConfirmPassword(brokerPassword);

		brc.clickNextBtnOnContactForm();

		brc.AccountName(NameonAccount);
		brc.BankingAccount(BankAccountNumber);

		brc.BankingRouting(RoutingNumber);

		brc.ConfirmBankingAccount(ConfirmbankAccountNumber);

		brc.clickNextBtnOnBankingForm();
		log.info("Broker Register Completed...");
	}

}
