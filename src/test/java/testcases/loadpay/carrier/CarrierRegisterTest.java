package testcases.loadpay.carrier;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierRegisterPage;
import util.TestUtil;

public class CarrierRegisterTest extends TestBase {
	CarrierRegisterPage carrierRegistrationObj;
	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;

	public static String carrierUsername;
	public static String carrierPassword;

	public CarrierRegisterTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		carrierRegistrationObj = new CarrierRegisterPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierRegisterData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			String[] emailArray = Email.split("@");
			emailArray[0] = emailArray[0] + TestUtil.getCurrentDateTime();

			carrierUsername = emailArray[0] + "@" + emailArray[1];
			carrierPassword = Password;
		} else {
			carrierUsername = Email;
			carrierPassword = Password;
		}

		carrierRegistrationObj.signup();

		// clicking on carrier Register

		carrierRegistrationObj.CarrierRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		carrierRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		carrierRegistrationObj.setMotorCarrierField(randomNum);

		carrierRegistrationObj.companyname(CompanyName);

		carrierRegistrationObj.doingbussiness(DoingBussinessAS);

		carrierRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));

		type.selectByVisibleText("C Corporation");

		carrierRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));

		countryof.selectByIndex(0);

		carrierRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));

		stateof.selectByVisibleText("California");

		carrierRegistrationObj.CarrierEmail(carrierUsername);

		carrierRegistrationObj.confirmEmail(carrierUsername);

		carrierRegistrationObj.iCertifyClick();

		carrierRegistrationObj.clickNextBtnOnCompanyForm();

		carrierRegistrationObj.ZipCode(ZipCode1);

		carrierRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		carrierRegistrationObj.address(Address);

		carrierRegistrationObj.city(City);

		carrierRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));

		state.selectByVisibleText("CA");

		carrierRegistrationObj.clickNextBtnOnAddressForm();

		carrierRegistrationObj.ContactFirstName(FirstNames);

		carrierRegistrationObj.LastName(LastName);
		carrierRegistrationObj.Phone(PhoneNumber);

		carrierRegistrationObj.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		carrierRegistrationObj.ConfirmPassword(ConfirmPassword);

		carrierRegistrationObj.clickNextBtnOnContactForm();

		carrierRegistrationObj.AccountName(NameonAccount);
		carrierRegistrationObj.BankingAccount(BankAccountNumber);

		carrierRegistrationObj.BankingRouting(RoutingNumber);

		carrierRegistrationObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		carrierRegistrationObj.clickNextBtnOnBankingForm();
		log.info("Carrier Registration Completed...");

	}

}
