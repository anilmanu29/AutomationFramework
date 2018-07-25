package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminCancelPayByCheck;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerLoginPage;

public class AdminCancelPayByCheckTest extends TestBase {

	AdminHomePage adminHomePageObj;
	AdminLogin adminLogObj;
	AdminPayByCheck adminPayByCheckObj;
	AdminCancelPayByCheck adminCancelPayByCheckObj;
	public static String acountname;

	/*-------Initializing driver---------*/

	public AdminCancelPayByCheckTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		wait = new WebDriverWait(driver, 30);
		adminLogObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		adminCancelPayByCheckObj = new AdminCancelPayByCheck();

	}

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {
		adminHomePageObj.AdminURL();

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getUserName()));
		adminLogObj.adminUserPass(Username, pass);

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getLoginBtn()));
		adminLogObj.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getCustomerTab()));
		adminLogObj.ClickOnCustomersTab();

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getSearch()));
		log.info(BrokerLoginPage.bemail);
		adminLogObj.ClickOnSearchBox(BrokerLoginPage.bemail);

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getClickonSearchButton()));
		adminLogObj.ClickOnSearchButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminLogObj.getDoubleClickID()));
		adminLogObj.DoubleClickID();

		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getLink_Payments()));
		adminPayByCheckObj.clickPayments();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getFieldSearch()));
		log.info(adminCancelPayByCheckObj);
		adminCancelPayByCheckObj.ClickOnsearchKeyword(adminCancelPayByCheckObj.getPaymentID1().getText());

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getPaymentIdElement()));
		adminCancelPayByCheckObj.getPaymentID();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getLink_Search()));
		adminCancelPayByCheckObj.clickSearch();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getSearchKeyword()));
		adminCancelPayByCheckObj.searchKeyword();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getBtn_Search()));
		adminCancelPayByCheckObj.clickSearch1();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getGrid_collapse()));
		adminCancelPayByCheckObj.clickgridcollapse();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getBtn_PayByCheck()));
		adminCancelPayByCheckObj.clickPayByCheck();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getSelect_Terms()));
		adminCancelPayByCheckObj.selectTerms();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getSelect_Terms()));
		adminCancelPayByCheckObj.selectTermsTermPayment();
	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getTxt_DOT()));
		adminCancelPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getTxt_ContactName()));
		adminCancelPayByCheckObj.ContactName(ContactName);

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getBtn_paybychksubmit()));
		adminCancelPayByCheckObj.clickPayByChecksubmit();

		wait.until(ExpectedConditions.elementToBeClickable(adminCancelPayByCheckObj.getBtn_CancelPayByCheck()));
		adminCancelPayByCheckObj.clickCancelPayByCheck();
	}

}
