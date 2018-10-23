package testcases.loadpay.broker;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerRegister;

public class BrokerCanNotRegisterDuplicateEmail extends TestBase {

	BrokerRegister brokerRegistrationObj;
	BrokerLoginPage brokerLoginPage;
	String brokerUsername;
	public static String emailid;

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		wait = new WebDriverWait(driver, 30);
		brokerRegistrationObj = new BrokerRegister();
		brokerLoginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerCanNotRegisterDuplicateEmailData")
	public void BrokerRegister(String Email, String ConfirmEmail) throws IOException, InterruptedException {

		brokerUsername = Email;
		brokerRegistrationObj.signup();

		// clicking on carrier Register
		brokerRegistrationObj.shipperRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		brokerRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		brokerRegistrationObj.setMotorCarrierField(randomNum);

		brokerRegistrationObj.companyname("Test Company");
		brokerRegistrationObj.doingbussiness("Test DBA");
		brokerRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brokerRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		brokerRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		brokerRegistrationObj.BrokerEmail(brokerUsername);
		brokerRegistrationObj.confirmEmail(brokerUsername);
		brokerRegistrationObj.iCertifyClick();
		brokerRegistrationObj.paymentTerm();

		brokerRegistrationObj.verifyEmailInUseMessage();

		brokerRegistrationObj.clickNextBtnOnCompanyForm();

		brokerRegistrationObj.ZipCode("60101");
		brokerRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		brokerRegistrationObj.address("123 Main St");
		brokerRegistrationObj.city("Addison");
		brokerRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("IL");

		brokerRegistrationObj.clickNextBtnOnAddressForm();

		brokerRegistrationObj.ContactFirstName("John");
		brokerRegistrationObj.LastName("Doe");
		brokerRegistrationObj.Phone("6305551234");
		brokerRegistrationObj.Password("Password@1");
		brokerRegistrationObj.ConfirmPassword("Password@1");

		brokerRegistrationObj.clickNextBtnOnContactForm();

		brokerRegistrationObj.clickAddLaterButton();

		brokerRegistrationObj.verifyEmailAlreadyLinkedMessage();
		log.info("--------Unable to Login with duplicate email is verified-------");

		// *[@id="angularScope"]/div[5]/div/div

	}

}
