package testcases.loadpay.carrier;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierRegisterCanada;

public class CarrierRegisterCanadaTest extends TestBase {
	CarrierRegisterCanada cr;
	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;
	public static String cemail;
	public static String password;

	public CarrierRegisterCanadaTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		cr = new CarrierRegisterCanada();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City,
			String ocountry, String States, String FirstNames, String LastName, String PhoneNumber, String Password,
			String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
			String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		cr.signup();
		cr.CarrierRegister();

		if (Dotnumber == null) {
			cr.company(CompanyName);

		} else {

			cr.dotnumber(Dotnumber);

		}

		cr.doingbussiness(DoingBussinessAS);
		cr.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		cr.countrydropdown(country, state);

		cemail = cr.CarrierEmail(Email);
		cr.confirmEmail(ConfirmEmail);

		cr.iCertifyClick();

		cr.next();

		if (Dotnumber == null) {

			cr.originCountry(ocountry, States);

			cr.ZipCode(ZipCode1);
			cr.address(Address);

			cr.city(City);

		} else {
			cr.originCountry(ocountry, States);
			cr.ZipCode(ZipCode1);

		}

		cr.submit();

		cr.ContactFirstName(FirstNames);

		cr.LastName(LastName);
		cr.Phone(PhoneNumber);

		password = cr.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		cr.ConfirmPassword(ConfirmPassword);

		cr.Next();

		cr.AccountName(NameonAccount);
		cr.BankingAccount(BankAccountNumber);

		cr.BankingRouting(RoutingNumber);

		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);

		cr.submit();

	}

}
