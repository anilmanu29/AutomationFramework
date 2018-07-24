package testcases.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerRegisterCanada;

public class BrokerRegisterCanadaTest extends TestBase {
	BrokerRegisterCanada brc;
	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;
	public static String emailid;
	public static String pwd;

	public BrokerRegisterCanadaTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		brc = new BrokerRegisterCanada();
	}

	@Test(dataProvider = "getBrokerData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City,
			String ocountry, String States, String FirstNames, String LastName, String PhoneNumber, String Password,
			String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
			String ConfirmbankAccountNumber) throws IOException, InterruptedException {
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
		emailid = brc.BrokerEmail(Email);
		brc.confirmEmail(ConfirmEmail);

		brc.iCertifyClick();

		brc.paymentTerm();

		brc.next();
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

		brc.submit();

		brc.ContactFirstName(FirstNames);

		brc.LastName(LastName);
		brc.Phone(PhoneNumber);

		pwd = brc.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		brc.ConfirmPassword(ConfirmPassword);

		brc.Next();

		brc.AccountName(NameonAccount);
		brc.BankingAccount(BankAccountNumber);

		brc.BankingRouting(RoutingNumber);

		brc.ConfirmBankingAccount(ConfirmbankAccountNumber);

		brc.submit();
		log.info("Broker Register Completed...");

		log.info("BrokerRegisterSuccessfully");

	}

}
