package testcases.loadpay.unmatched;

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

public class UnmatchedCarrierAdminSameDayTest extends TestBase {
	AdminHomePage adminHomePageObj;
	AdminLogin adminLoginObj;
	Select s;

	public UnmatchedCarrierAdminSameDayTest() {
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
		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();
		adminLoginObj.ClickOnCustomersTab();
		adminLoginObj.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.al.get(0));
		adminLoginObj.ClickOnSearchButton();
		adminLoginObj.DoubleClickID();
		adminLoginObj.StatusIDDropDown();
		adminLoginObj.UpdateButton();
		adminLoginObj.AdminLogOut();

	}

}
