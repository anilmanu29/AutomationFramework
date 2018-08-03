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
	CarrierRegisterCanada canadaCarrierObj;
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
		canadaCarrierObj = new CarrierRegisterCanada();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String country, String state, String ZipCode1, String Address, String City,
			String ocountry, String States, String FirstNames, String LastName, String PhoneNumber, String Password,
			String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
			String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		canadaCarrierObj.signup();
		canadaCarrierObj.CarrierRegister();

		if (Dotnumber == null) {
			canadaCarrierObj.company(CompanyName);

		} else {

			canadaCarrierObj.dotnumber(Dotnumber);

		}

		canadaCarrierObj.doingbussiness(DoingBussinessAS);
		canadaCarrierObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		canadaCarrierObj.countrydropdown(country, state);

		cemail = canadaCarrierObj.CarrierEmail(Email);
		canadaCarrierObj.confirmEmail(ConfirmEmail);
		canadaCarrierObj.iCertifyClick();

		canadaCarrierObj.clickNextBtnOnCompanyForm();

		if (Dotnumber == null) {

			canadaCarrierObj.originCountry(ocountry, States);
			canadaCarrierObj.ZipCode(ZipCode1);
			canadaCarrierObj.address(Address);
			canadaCarrierObj.city(City);

		} else {
			canadaCarrierObj.originCountry(ocountry, States);
			canadaCarrierObj.ZipCode(ZipCode1);

		}

		canadaCarrierObj.clickNextBtnOnAddressForm();

		canadaCarrierObj.ContactFirstName(FirstNames);
		canadaCarrierObj.LastName(LastName);
		canadaCarrierObj.Phone(PhoneNumber);
		password = canadaCarrierObj.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		canadaCarrierObj.ConfirmPassword(ConfirmPassword);

		canadaCarrierObj.clickNextBtnOnContactForm();

		canadaCarrierObj.AccountName(NameonAccount);
		canadaCarrierObj.BankingAccount(BankAccountNumber);
		canadaCarrierObj.BankingRouting(RoutingNumber);
		canadaCarrierObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		canadaCarrierObj.clickNextBtnOnBankingForm();

	}

}
