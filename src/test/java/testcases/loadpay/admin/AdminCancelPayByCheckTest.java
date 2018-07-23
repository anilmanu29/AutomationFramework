package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
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

		adminLogObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		adminCancelPayByCheckObj = new AdminCancelPayByCheck();

	}

	/*
	 * @Test(dataProvider = "getAdminLoginData", priority =18 ) public void
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
	 * apbc.ClickOnsearchKeyword(BrokerNewPaymentTest.al.get(0));
	 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
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
	 * @Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=19)
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
		// acpbc.ClickOnsearchKeywordterm(BrokerNewPaymentTest.al.get(0));

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
		/*
		 * apbc.clickAddCheckNumber();
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 * apbc.ClickOnEnterCheckNumber();
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 */

	}

}
