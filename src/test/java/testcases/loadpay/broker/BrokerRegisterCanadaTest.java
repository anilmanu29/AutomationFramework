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
			Thread.sleep(1000);
		} else {
			Thread.sleep(1000);
			brc.dotnumber(Dotnumber);
		}
		Thread.sleep(1000);
		brc.doingbussiness(DoingBussinessAS);
		brc.selectType();
		Thread.sleep(1000);
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		Thread.sleep(1000);
		brc.countrydropdown(country, state);

		// Select countryof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationCountry']" ) ) );
		//
		// countryof.selectByIndex( 0 );

		// r.stateofincorporation();
		Thread.sleep(1000);
		// Select stateof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationState']" ) ) );
		//
		// stateof.selectByVisibleText( "California" );
		emailid = brc.BrokerEmail(Email);
		brc.confirmEmail(ConfirmEmail);
		Thread.sleep(1000);
		brc.iCertifyClick();
		Thread.sleep(1000);
		brc.paymentTerm();
		Thread.sleep(1000);
		brc.next();
		if (Dotnumber == null) {
			Thread.sleep(1000);
			brc.originCountry(ocountry, States);
			Thread.sleep(1000);
			brc.ZipCode(ZipCode1);
			brc.address(Address);
			Thread.sleep(1000);
			brc.city(City);
			Thread.sleep(1000);
		} else {
			brc.originCountry(ocountry, States);
			brc.ZipCode(ZipCode1);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
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

		Thread.sleep(1000);
		brc.submit();
		Thread.sleep(1000);
		brc.ContactFirstName(FirstNames);
		Thread.sleep(1000);
		brc.LastName(LastName);
		brc.Phone(PhoneNumber);
		Thread.sleep(1000);
		pwd = brc.Password(Password);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		brc.ConfirmPassword(ConfirmPassword);
		Thread.sleep(1000);
		brc.Next();
		Thread.sleep(1000);
		brc.AccountName(NameonAccount);
		brc.BankingAccount(BankAccountNumber);
		Thread.sleep(1000);
		brc.BankingRouting(RoutingNumber);
		Thread.sleep(1000);
		brc.ConfirmBankingAccount(ConfirmbankAccountNumber);
		Thread.sleep(1000);
		brc.submit();
		System.out.println("Broker Register Completed...");
		Thread.sleep(1000);
		System.out.print("BrokerRegisterSuccessfully");

	}

}
