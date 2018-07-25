package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.Carrierlockedaccountcanbeunlockedbyadmin;

public class CarrierlockedaccountcanbeunlockedbyadminTest extends TestBase {
	Carrierlockedaccountcanbeunlockedbyadmin Claua;
	AdminHomePage adminHomePage;
	AdminLogin adminLogin;
	CarrierLoginPage loginPage;
	public static String aemail;

	public CarrierlockedaccountcanbeunlockedbyadminTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		adminHomePage = new AdminHomePage();
		adminLogin = new AdminLogin();
		Claua = new Carrierlockedaccountcanbeunlockedbyadmin();
		loginPage = new CarrierLoginPage();
	}

	@Test(dataProvider = "getCarrierlockedaccountAdminUnlockData")
	public void carrierLoginTest(String user, String pass, String wrongpass) throws InterruptedException {
		aemail = user;
		Claua.Carrierloginlock(user, pass, wrongpass);

	}

	@Test(dependsOnMethods = { "carrierLoginTest" })
	public void switchToAdminURL() throws AWTException, InterruptedException {
		adminHomePage.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "switchToAdminURL" })
	public void adminCancelLockout(String Username, String pass) throws InterruptedException, AWTException {
		adminLogin.adminUserPass(Username, pass);

		adminLogin.adminLogin();

		adminLogin.ClickOnCustomersTab();

		adminLogin.ClickOnSearchBox(aemail);

		adminLogin.ClickOnSearchButton();

		adminLogin.clickCustomerId();

		adminLogin.clickeditloginuser();

		adminLogin.clickCancelLockout();

		adminLogin.AdminLogOut();

	}

	@Test(dataProvider = "getCarrierlockedaccountAdminUnlockData", dependsOnMethods = { "adminCancelLockout" })
	public void loginAsCarrierUnlocked(String user, String pass, String wrongpass) throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		loginPage.Carrierlogin(user, pass);

		loginPage.CarrierLogout();

	}
}
