package testcases.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			brc.dotnumber(Dotnumber);
		}
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.doingbussiness(DoingBussinessAS);
		brc.selectType();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.countrydropdown(country, state);

		// Select countryof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationCountry']" ) ) );
		//
		// countryof.selectByIndex( 0 );

		// r.stateofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Select stateof = new Select( driver.findElement( By.xpath(
		// ".//*[@id='IncorporationState']" ) ) );
		//
		// stateof.selectByVisibleText( "California" );
		emailid = brc.BrokerEmail(Email);
		brc.confirmEmail(ConfirmEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.paymentTerm();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.next();
		if (Dotnumber == null) {
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			brc.originCountry(ocountry, States);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			brc.ZipCode(ZipCode1);
			brc.address(Address);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			brc.city(City);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		} else {
			brc.originCountry(ocountry, States);
			brc.ZipCode(ZipCode1);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		}

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
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

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.ContactFirstName(FirstNames);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.LastName(LastName);
		brc.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		pwd = brc.Password(Password);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		brc.ConfirmPassword(ConfirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.Next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.AccountName(NameonAccount);
		brc.BankingAccount(BankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.BankingRouting(RoutingNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brc.submit();
		log.info("Broker Register Completed...");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info("BrokerRegisterSuccessfully");

	}

}
