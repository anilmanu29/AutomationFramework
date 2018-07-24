package testcases.loadpay.carrier;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierRegisterPage;

public class CarrierRegisterTest extends TestBase {
	CarrierRegisterPage cr;
	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;
	public static String email;

	public CarrierRegisterTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		cr = new CarrierRegisterPage();
	}

	@Test(dataProvider = "getCarrierRegisterData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		cr.signup();

		// clicking on carrier Register

		cr.CarrierRegister();

		cr.companyname(CompanyName);

		cr.doingbussiness(DoingBussinessAS);

		cr.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));

		type.selectByVisibleText("C Corporation");

		cr.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));

		countryof.selectByIndex(0);

		cr.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));

		stateof.selectByVisibleText("California");

		email = cr.CarrierEmail(Email);

		cr.confirmEmail(ConfirmEmail);

		cr.iCertifyClick();

		cr.next();

		cr.ZipCode(ZipCode1);

		cr.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		cr.address(Address);

		cr.city(City);

		cr.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));

		state.selectByVisibleText("CA");

		cr.submit();

		cr.ContactFirstName(FirstNames);

		cr.LastName(LastName);
		cr.Phone(PhoneNumber);

		cr.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		cr.ConfirmPassword(ConfirmPassword);

		cr.Next();

		cr.AccountName(NameonAccount);
		cr.BankingAccount(BankAccountNumber);

		cr.BankingRouting(RoutingNumber);

		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);

		cr.submit();
		log.info("Carrier Registration Completed...");

	}

}
