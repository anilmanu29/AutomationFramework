package testcases.loadpay.unmatched;

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
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedNewPaymentAdminCarrierTest extends TestBase {
	AdminHomePage adminHomePageObj;
	AdminLogin adminLoginObj;
	Select s;

	public UnmatchedNewPaymentAdminCarrierTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		adminHomePageObj = new AdminHomePage();
		adminLoginObj = new AdminLogin();
	}

	@Test()
	public void Home() throws IOException, AWTException, InterruptedException {
		log.info(BrokerPaymentforUnmatchedCarrierTest.umemail);
		adminHomePageObj.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getUserName()));
		adminLoginObj.adminUserPass(Username, pass);

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getLoginBtn()));
		adminLoginObj.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomerTab()));
		adminLoginObj.ClickOnCustomersTab();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getSearch()));
		adminLoginObj.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.al.get(1));

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getClickonSearchButton()));
		adminLoginObj.ClickOnSearchButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getDoubleClickID()));
		adminLoginObj.DoubleClickID();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomersatatusIdDropDown()));
		adminLoginObj.StatusIDDropDown();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getUpdateButton()));
		adminLoginObj.UpdateButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getLogOut()));
		adminLoginObj.AdminLogOut();

	}

}
