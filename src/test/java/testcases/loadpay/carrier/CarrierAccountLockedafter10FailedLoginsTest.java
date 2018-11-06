package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierAccountLockedafter10FailedLogins;
import pages.loadpay.carrier.CarrierLoginPage;
import util.TestUtil;

public class CarrierAccountLockedafter10FailedLoginsTest extends TestBase {
	CarrierAccountLockedafter10FailedLogins Calck;
	AdminHomePage h;
	AdminLogin a;
	Select s;
	public static String aemail;
	CarrierLoginPage loginPage;

	public CarrierAccountLockedafter10FailedLoginsTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		TestUtil.className = this.getClass().getName();
		h = new AdminHomePage();
		a = new AdminLogin();
		Calck = new CarrierAccountLockedafter10FailedLogins();
		loginPage = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierlockedaccountAdminUnlockData")
	public void loginTest(String user, String pass, String wrongpass) throws InterruptedException {
		aemail = user;
		Calck.Carrierloginlock(user, pass, wrongpass);

	}

	public void verifyCarrierAccLockTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(Calck.UserName.isDisplayed(), "username Column not found");
		Assert.assertTrue(Calck.Password.isDisplayed(), "Password Column not found");
		Assert.assertTrue(Calck.loginBtn.isDisplayed(), "loginBtn Column not found");
		Assert.assertTrue(Calck.btn_logout.isDisplayed(), "btn_logout Column not found");

	}

}
