package testcases.loadpay.carrier;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierRegisterPage;

public class CarrierCanNotRegisterDuplicateEmail extends TestBase {
	CarrierRegisterPage carrierRegisterPage;
	String carrierUsername;
	public static String emailid;

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		wait = new WebDriverWait(driver, 30);
		carrierRegisterPage = new CarrierRegisterPage();

	}

	@Test(dataProvider = "getCarrierCanNotRegisterDuplicateEmailData")
	public void CarrierRegister(String Email, String ConfirmEmail) throws InterruptedException {

		carrierUsername = Email;
		carrierRegisterPage.signup();
		// clicking on carrier Register
		carrierRegisterPage.CarrierRegister();
		carrierRegisterPage.CarrierEmail(Email);
		carrierRegisterPage.confirmEmail(ConfirmEmail);
		carrierRegisterPage.verifyErrorMessage();
		log.info("--------Unable to Login with duplicate email is verified-------");
	}

}
