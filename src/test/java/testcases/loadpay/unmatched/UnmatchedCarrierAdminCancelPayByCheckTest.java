package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.unmatched.UnmatchedCarrierAdminCancelPayByCheck;
import pages.loadpay.unmatched.UnmatchedCarrierAdminPayByCheck;

public class UnmatchedCarrierAdminCancelPayByCheckTest extends TestBase {

	AdminHomePage adminHomePageObj;
	AdminLogin adminLoginPageObj;
	AdminPayByCheck adminPayByCheckObj;
	UnmatchedCarrierAdminPayByCheck UnCarrierAdminPBC;
	UnmatchedCarrierAdminCancelPayByCheck UnCarrierAdminCancelPayByCheckObj;
	public static String acountname;

	/*-------Initializing driver---------*/

	public UnmatchedCarrierAdminCancelPayByCheckTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();

		adminLoginPageObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
		UnCarrierAdminCancelPayByCheckObj = new UnmatchedCarrierAdminCancelPayByCheck();

	}

	/*
	 * @Test(dataProvider = "getAdminLoginData", priority =113 ) public void
	 * verifyAdminPayByCheck(String Username, String pass) throws
	 * InterruptedException, AWTException {
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement)); ahp.AdminURL();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * al.adminUserPass(Username, pass); al.adminLogin();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * al.ClickOnCustomersTab();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * log.info(BrokerLoginPage.bemail);
	 * al.ClickOnSearchBox(BrokerLoginPage.bemail);
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * al.ClickOnSearchButton();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * al.DoubleClickID();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickPayments();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * log.info(UnCarrierAdminPBC.getPaymentId1().getText());
	 * UnCarrierAdminPBC.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().
	 * getText()); wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.getPaymentID();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickSearch();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.searchKeyword();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickSearch1();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickgridcollapse();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickPayByCheck();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.selectTerms();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * 
	 * }
	 * 
	 * @Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=114)
	 * public void carrierPaymenowPayByCheck(String EnterDOTNnumber,String
	 * ContactName) throws InterruptedException {
	 * apbc.EnterDOTNnumber(EnterDOTNnumber);
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.ContactName(ContactName);
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickPayByChecksubmit();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.clickAddCheckNumber();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * apbc.ClickOnEnterCheckNumber();
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * //al.AdminLogOut();
	 * //wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	 * 
	 * 
	 * }
	 */

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {
		adminHomePageObj.AdminURL();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPageObj.getUserName()));
		adminLoginPageObj.adminUserPass(Username, pass);

		adminLoginPageObj.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPageObj.getCustomerTab()));
		adminLoginPageObj.ClickOnCustomersTab();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPageObj.getSearch()));
		log.info(BrokerLoginPage.bemail);
		adminLoginPageObj.ClickOnSearchBox(BrokerLoginPage.bemail);

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPageObj.getClickonSearchButton()));
		adminLoginPageObj.ClickOnSearchButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPageObj.getDoubleClickID()));
		adminLoginPageObj.DoubleClickID();

		wait.until(ExpectedConditions.elementToBeClickable(adminPayByCheckObj.getLink_Payments()));
		adminPayByCheckObj.clickPayments();

		log.info(UnCarrierAdminPBC.getPaymentId1().getText());

		UnCarrierAdminPBC.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		UnCarrierAdminCancelPayByCheckObj.getPaymentID();
		UnCarrierAdminCancelPayByCheckObj.clickSearch();
		UnCarrierAdminCancelPayByCheckObj.searchKeyword();
		UnCarrierAdminCancelPayByCheckObj.clickSearch1();
		UnCarrierAdminCancelPayByCheckObj.clickgridcollapse();
		UnCarrierAdminCancelPayByCheckObj.clickPayByCheck();
		UnCarrierAdminCancelPayByCheckObj.selectTerms();
		adminPayByCheckObj.selectTermsTermPayment();
	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(UnCarrierAdminCancelPayByCheckObj.getTxt_DOT()));
		UnCarrierAdminCancelPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);

		wait.until(ExpectedConditions.elementToBeClickable(UnCarrierAdminCancelPayByCheckObj.getTxt_ContactName()));
		UnCarrierAdminCancelPayByCheckObj.ContactName(ContactName);
		UnCarrierAdminCancelPayByCheckObj.clickPayByChecksubmit();
		UnCarrierAdminCancelPayByCheckObj.clickCancelPayByCheck();

		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// apbc.clickAddCheckNumber();
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// apbc.ClickOnEnterCheckNumber();
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

}
