package testcases.loadpay.carrier;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	}

	@Test(dataProvider = "getCarrierData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City,
			String ocountry, String States, String FirstNames, String LastName, String PhoneNumber, String Password,
			String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
			String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		cr.signup();
		cr.CarrierRegister();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		if (Dotnumber == null) {
			cr.company(CompanyName);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			cr.dotnumber(Dotnumber);

		}
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.doingbussiness(DoingBussinessAS);
		cr.selectType();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.countrydropdown(country, state);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cemail = cr.CarrierEmail(Email);
		cr.confirmEmail(ConfirmEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		if (Dotnumber == null) {

			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			cr.originCountry(ocountry, States);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			cr.ZipCode(ZipCode1);
			cr.address(Address);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
			cr.city(City);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		} else {
			cr.originCountry(ocountry, States);
			cr.ZipCode(ZipCode1);
			wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		}
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.ContactFirstName(FirstNames);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.LastName(LastName);
		cr.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		password = cr.Password(Password);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		cr.ConfirmPassword(ConfirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.Next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.AccountName(NameonAccount);
		cr.BankingAccount(BankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.BankingRouting(RoutingNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

}
