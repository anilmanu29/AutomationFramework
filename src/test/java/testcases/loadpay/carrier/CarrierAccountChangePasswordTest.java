package testcases.loadpay.carrier;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierEmailLoginUsersPage;
import pages.loadpay.carrier.CarrierLoginPage;
import util.TestUtil;

public class CarrierAccountChangePasswordTest extends TestBase {
	WebDriverWait wait;
	CarrierLoginPage CarrierLoginPage;
	CarrierEmailLoginUsersPage CarrierEmailLoginUsersPage;
	String carrierUsername, carrierPassword, carrierDOT, carrierEIN = "";

	public CarrierAccountChangePasswordTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		CarrierLoginPage = new CarrierLoginPage();
		CarrierEmailLoginUsersPage = new CarrierEmailLoginUsersPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		CarrierLoginPage.Carrierlogin(carrierUsername, carrierPassword);

	}

	@Test(dataProvider = "getCarrierChangePasswordData", dependsOnMethods = "loginTest")
	public void changePasswordVerification(String Username, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {
		log.info(carrierUsername);
		log.info(carrierPassword);
		log.info(NewPassword);
		CarrierEmailLoginUsersPage.openAccountTab();
		CarrierEmailLoginUsersPage.goToEmailLoginUsers();
		CarrierEmailLoginUsersPage.openPasswordAccountSecurityLink();
		CarrierEmailLoginUsersPage.clickChangePasswordButton();
		CarrierEmailLoginUsersPage.clicCurrentPasswordField();
		CarrierEmailLoginUsersPage.enterCurrentPassword(CurrentPassword);
		CarrierEmailLoginUsersPage.clickNewPasswordField();

		carrierPassword = NewPassword;
		CarrierRegisterTest.carrierPassword = carrierPassword;

		CarrierEmailLoginUsersPage.enterNewPassword(carrierPassword);
		CarrierEmailLoginUsersPage.clickConfirmNewPasswordField();
		CarrierEmailLoginUsersPage.enterConfirmNewPasswordField(carrierPassword);
		CarrierEmailLoginUsersPage.clickUpdateButton();
		assertEquals(CarrierEmailLoginUsersPage.verificationMessage(), "Saved", "Password isn't saved");
		CarrierLoginPage.CarrierLogout();
	}

	@Test(dataProvider = "getCarrierChangePasswordData", dependsOnMethods = "changePasswordVerification")
	public void loginVerificationTest(String UserName, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {

		CarrierLoginPage.carrierVerificationLogin(carrierUsername, carrierPassword);
		assertEquals(CarrierEmailLoginUsersPage.logOffButton(), true, "User is unable to login with New Password");
	}
}
