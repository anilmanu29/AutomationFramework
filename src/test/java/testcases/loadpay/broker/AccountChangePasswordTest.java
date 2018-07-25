package testcases.loadpay.broker;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerEmailLoginUsersPage;
import pages.loadpay.broker.BrokerLoginPage;

public class AccountChangePasswordTest extends TestBase {
	WebDriverWait wait;
	BrokerLoginPage brokerLoginPage;
	BrokerEmailLoginUsersPage brokEmailLogUsePage;

	public AccountChangePasswordTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerLoginPage = new BrokerLoginPage();
		brokEmailLogUsePage = new BrokerEmailLoginUsersPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		brokerLoginPage.Brokerlogin(user, pass);

	}

	@Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "loginTest")
	public void changePasswordVerification(String Username, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {
		log.info(Username);
		log.info(CurrentPassword);
		log.info(NewPassword);
		brokEmailLogUsePage.openAccountTab();
		brokEmailLogUsePage.goToEmailLoginUsers();
		brokEmailLogUsePage.openPasswordAccountSecurityLink();
		brokEmailLogUsePage.clickChangePasswordButton();
		brokEmailLogUsePage.clicCurrentPasswordField();
		brokEmailLogUsePage.enterCurrentPassword(CurrentPassword);
		brokEmailLogUsePage.clickNewPasswordField();
		brokEmailLogUsePage.enterNewPassword(NewPassword);
		brokEmailLogUsePage.clickConfirmNewPasswordField();
		brokEmailLogUsePage.enterConfirmNewPasswordField(ConfirmNewPassword);
		brokEmailLogUsePage.clickUpdateButton();
		assertEquals(brokEmailLogUsePage.verificationMessage(), "Saved", "Password isn't saved");
		brokerLoginPage.BrokerLogout();

	}

	@Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "changePasswordVerification")
	public void loginVerificationTest(String UserName, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		brokerLoginPage.brokerVerificationLogin(UserName, NewPassword);
		assertEquals(brokEmailLogUsePage.logOffButton(), true, "User is unable to login with New Password");
	}
}
