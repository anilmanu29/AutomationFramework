package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.loadpay.carrier.CarrierRegisterTest;

public class AdminCarrierTest extends TestBase {
	AdminHomePage h;
	AdminLogin a;
	Select s;

	public AdminCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		h = new AdminHomePage();
		a = new AdminLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "Switch to admin URL")
	public void Home() throws IOException, AWTException, InterruptedException {
		log.info(BrokerPaymentforUnmatchedCarrierTest.umemail);
		h.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
		a.adminUserPass(Username, pass);

		a.adminLogin();

		a.ClickOnCustomersTab();

		a.Uncheck_Factor();

		if (BrokerPaymentforUnmatchedCarrierTest.umemail != null) {
			a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.umemail);
		} else {
			a.ClickOnSearchBox(CarrierRegisterTest.carrierUsername);

		}
		a.ClickOnSearchButton();

		a.DoubleClickID();

		a.StatusIDDropDown();

		a.UpdateButton();

		a.AdminLogOut();

	}

}
