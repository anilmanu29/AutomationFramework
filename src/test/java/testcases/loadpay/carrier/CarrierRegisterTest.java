package testcases.loadpay.carrier;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		cr.doingbussiness(DoingBussinessAS);

		cr.selectType();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));

		type.selectByVisibleText("C Corporation");

		cr.countryofincorporation();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));

		countryof.selectByIndex(0);

		cr.stateofincorporation();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));

		stateof.selectByVisibleText("California");

		email = cr.CarrierEmail(Email);

		cr.confirmEmail(ConfirmEmail);

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.next();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		cr.ZipCode(ZipCode1);

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		cr.country();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		cr.address(Address);

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		cr.city(City);

		cr.State();

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));

		state.selectByVisibleText("CA");

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.ContactFirstName(FirstNames);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.LastName(LastName);
		cr.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.Password(Password);
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		cr.submit();
		log.info("Carrier Registration Completed...");

	}

}
