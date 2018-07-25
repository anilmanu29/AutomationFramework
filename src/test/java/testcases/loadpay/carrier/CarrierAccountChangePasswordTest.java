package testcases.loadpay.carrier;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierEmailLoginUsersPage;
import pages.loadpay.carrier.CarrierLoginPage;

public class CarrierAccountChangePasswordTest extends TestBase {
	WebDriverWait wait;
	CarrierLoginPage CarrierLoginPage;
	CarrierEmailLoginUsersPage CarrierEmailLoginUsersPage;

	public CarrierAccountChangePasswordTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		CarrierLoginPage = new CarrierLoginPage();
		CarrierEmailLoginUsersPage = new CarrierEmailLoginUsersPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		CarrierLoginPage.Carrierlogin(user, pass);

	}

	@Test(dataProvider = "getCarrierChangePasswordData", dependsOnMethods = "loginTest")
	public void changePasswordVerification(String Username, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {
		log.info(Username);
		log.info(CurrentPassword);
		log.info(NewPassword);
		CarrierEmailLoginUsersPage.openAccountTab();
		CarrierEmailLoginUsersPage.goToEmailLoginUsers();
		CarrierEmailLoginUsersPage.openPasswordAccountSecurityLink();
		CarrierEmailLoginUsersPage.clickChangePasswordButton();
		CarrierEmailLoginUsersPage.clicCurrentPasswordField();
		CarrierEmailLoginUsersPage.enterCurrentPassword(CurrentPassword);
		CarrierEmailLoginUsersPage.clickNewPasswordField();
		CarrierEmailLoginUsersPage.enterNewPassword(NewPassword);
		CarrierEmailLoginUsersPage.clickConfirmNewPasswordField();
		CarrierEmailLoginUsersPage.enterConfirmNewPasswordField(ConfirmNewPassword);
		CarrierEmailLoginUsersPage.clickUpdateButton();
		assertEquals(CarrierEmailLoginUsersPage.verificationMessage(), "Saved", "Password isn't saved");
		CarrierLoginPage.CarrierLogout();
	}

	@Test(dataProvider = "getCarrierChangePasswordData", dependsOnMethods = "changePasswordVerification")
	public void loginVerificationTest(String UserName, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		CarrierLoginPage.carrierVerificationLogin(UserName, NewPassword);
		assertEquals(CarrierEmailLoginUsersPage.logOffButton(), true, "User is unable to login with New Password");
	}
}
