package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import testcases.loadpay.broker.BrokerRegisterCanadaTest;

public class AdminBrokerCanadaTest extends TestBase {
	AdminHomePage h;
	AdminLogin a;
	Select s;

	public AdminBrokerCanadaTest() {
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
		wait.until(ExpectedConditions.elementToBeClickable(a.getUserName()));
		a.adminUserPass(Username, pass);

		wait.until(ExpectedConditions.elementToBeClickable(a.getLoginBtn()));
		a.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(a.getCustomerTab()));
		a.ClickOnCustomersTab();

		wait.until(ExpectedConditions.elementToBeClickable(a.getSearch()));
		a.ClickOnSearchBox(BrokerRegisterCanadaTest.emailid);

		wait.until(ExpectedConditions.elementToBeClickable(a.getClickonSearchButton()));
		a.ClickOnSearchButton();

		wait.until(ExpectedConditions.elementToBeClickable(a.getDoubleClickID()));
		a.DoubleClickID();

		wait.until(ExpectedConditions.elementToBeClickable(a.getCustomersatatusIdDropDown()));
		a.StatusIDDropDown();

		wait.until(ExpectedConditions.elementToBeClickable(a.getUpdateButton()));
		a.UpdateButton();

		wait.until(ExpectedConditions.elementToBeClickable(a.getLogOut()));
		a.AdminLogOut();

	}

}
