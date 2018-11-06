package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminCustomersSideMenuSearch;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminSearchPage;
import util.TestUtil;

public class AdminCustomersSideMenuSearchTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminSearchPage adminSearchPage;
	String adminUN = "";
	String adminPW = "";
	Integer maxRecordCount = 0;
	WebElement checkbox;
	AdminCustomersSideMenuSearch adminCustomersSideMenuSearch;

	public AdminCustomersSideMenuSearchTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		TestUtil.className = this.getClass().getName();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		adminSearchPage = new AdminSearchPage();
		wait = new WebDriverWait(driver, 30);
		adminCustomersSideMenuSearch = new AdminCustomersSideMenuSearch();
	}

	// login as admin
	@Test(description = "LP-5423 Admin_Search_adminLogin", dataProvider = "getAdminLoginData")
	public void adminLogin(String Username, String pass) throws AWTException, InterruptedException {
		adminHomePage.AdminURL();
		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);
		adminLoginPage.adminLogin();
		adminLoginPage.ClickOnCustomersTab();
	}

	@Test(description = "LP-5423 Admin_Search_adminLogin", dataProvider = "getAdminCustomersSideMenSearchData", dependsOnMethods = "adminLogin")
	public void adminvalidation(String CompanyName, String Contact, String RegisteredEmail)
			throws AWTException, InterruptedException {
		adminLoginPage.ClickOnSearchBox(CompanyName);
		Assert.assertTrue(adminLoginPage.ClickonSearchButton.isDisplayed(), "Search Button if not found");
		adminLoginPage.ClickOnSearchButton();
		adminLoginPage.ClickOnSearchBox(Contact);
		adminLoginPage.ClickOnSearchButton();
		adminLoginPage.ClickOnSearchBox(RegisteredEmail);
		adminLoginPage.ClickOnSearchButton();
		adminCustomersSideMenuSearch.ClickOnSearchBoxclear();

	}

	@Test(description = "LP-5423 Admin_Search_adminLogin", dependsOnMethods = "adminvalidation")
	public void uncheckfilters() throws AWTException, InterruptedException {
		Assert.assertTrue(adminCustomersSideMenuSearch.active.isDisplayed(), "Admin Active Check Box if not found");
		adminCustomersSideMenuSearch.ClickactiveButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.ToBeReviewed.isDisplayed(),
				"Admin ToBeReviewed Check Box if not found");
		adminCustomersSideMenuSearch.ClickToBeReviewedButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.Pending.isDisplayed(), "Admin Pending Check Box if not found");
		adminCustomersSideMenuSearch.ClickPendingButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.Inactive.isDisplayed(), "Admin Inactive Check Box if not found");
		adminCustomersSideMenuSearch.ClickInactiveButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.Broker.isDisplayed(), "Admin Broker   Check Box if not found");
		adminCustomersSideMenuSearch.ClickBrokerButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.Carrier.isDisplayed(), "Admin Carrier Check Box if not found");
		adminCustomersSideMenuSearch.ClickCarrierButton();
		Assert.assertTrue(adminLoginPage.ClickonSearchButton.isDisplayed(), "Search Button if not found");
		adminLoginPage.ClickOnSearchButton();

	}

	@Test(description = "LP-5423 Admin_Search_adminLogin", dependsOnMethods = "uncheckfilters")
	public void checkfilters() throws AWTException, InterruptedException {
		adminCustomersSideMenuSearch.ClickactiveButton();
		Assert.assertTrue(adminLoginPage.ClickonSearchButton.isDisplayed(), "Search Button if not found");
		adminLoginPage.ClickOnSearchButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.active.isDisplayed(), "Admin Active Check Box if not found");
		adminCustomersSideMenuSearch.ClickactiveButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.ToBeReviewed.isDisplayed(),
				"Admin ToBeReviewed Check Box if not found");
		adminCustomersSideMenuSearch.ClickToBeReviewedButton();
		adminLoginPage.ClickOnSearchButton();
		adminCustomersSideMenuSearch.ClickToBeReviewedButton();
		adminCustomersSideMenuSearch.ClickPendingButton();
		adminLoginPage.ClickOnSearchButton();
		adminCustomersSideMenuSearch.ClickPendingButton();
		adminCustomersSideMenuSearch.ClickInactiveButton();
		adminLoginPage.ClickOnSearchButton();
		adminCustomersSideMenuSearch.ClickInactiveButton();
		// Assert.assertTrue(adminCustomersSideMenuSearch.Denied.isDisplayed(), "Admin
		// Denied Check Box if not found");
		// adminCustomersSideMenuSearch.ClickDeniedButton();
		// adminLoginPage.ClickOnSearchButton();
		// adminCustomersSideMenuSearch.ClickDeniedButton();
		Assert.assertTrue(adminCustomersSideMenuSearch.Broker.isDisplayed(), "Admin Broker   Check Box if not found");
		adminCustomersSideMenuSearch.ClickBrokerButton();
		adminLoginPage.ClickOnSearchButton();
		adminCustomersSideMenuSearch.ClickBrokerButton();
		adminCustomersSideMenuSearch.ClickCarrierButton();
		adminLoginPage.ClickOnSearchButton();

	}

}
