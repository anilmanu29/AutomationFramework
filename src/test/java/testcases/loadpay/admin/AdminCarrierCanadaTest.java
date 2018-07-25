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
import testcases.loadpay.carrier.CarrierRegisterCanadaTest;

public class AdminCarrierCanadaTest extends TestBase {
	AdminHomePage h;
	AdminLogin a;
	Select s;

	public AdminCarrierCanadaTest() {
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
		h.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
		a.adminUserPass(Username, pass);

		a.adminLogin();

		a.ClickOnCustomersTab();

		a.ClickOnSearchBox(CarrierRegisterCanadaTest.cemail);

		a.ClickOnSearchButton();

		a.DoubleClickID();

		a.StatusIDDropDown();

		a.UpdateButton();

		a.AdminLogOut();

	}

}
