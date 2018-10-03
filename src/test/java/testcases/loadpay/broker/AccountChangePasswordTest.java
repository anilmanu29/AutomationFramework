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
	String brokerUsername, brokerPassword = "";

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

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}

		brokerLoginPage.Brokerlogin(brokerUsername, brokerPassword);

	}

	@Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "loginTest")
	public void changePasswordVerification(String Username, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = Username;
			brokerPassword = CurrentPassword;
		}

		log.info(brokerUsername);
		log.info(brokerPassword);
		log.info(NewPassword);
		brokEmailLogUsePage.openAccountTab();
		brokEmailLogUsePage.goToEmailLoginUsers();
		brokEmailLogUsePage.openPasswordAccountSecurityLink();
		brokEmailLogUsePage.clickChangePasswordButton();

		brokEmailLogUsePage.clicCurrentPasswordField();
		brokEmailLogUsePage.enterCurrentPassword(brokerPassword);

		brokerPassword = NewPassword;
		BrokerRegisterTest.brokerPassword = brokerPassword;

		brokEmailLogUsePage.clickNewPasswordField();
		brokEmailLogUsePage.enterNewPassword(brokerPassword);

		brokEmailLogUsePage.clickConfirmNewPasswordField();
		brokEmailLogUsePage.enterConfirmNewPasswordField(brokerPassword);

		brokEmailLogUsePage.clickUpdateButton();
		assertEquals(brokEmailLogUsePage.verificationMessage(), "Saved", "Password isn't saved");
		brokerLoginPage.BrokerLogout();

	}

	@Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "changePasswordVerification")
	public void loginVerificationTest(String UserName, String CurrentPassword, String NewPassword,
			String ConfirmNewPassword) throws InterruptedException {

		brokerLoginPage.brokerVerificationLogin(brokerUsername, brokerPassword);
		assertEquals(brokEmailLogUsePage.logOffButton(), true, "User is unable to login with New Password");
	}
}
