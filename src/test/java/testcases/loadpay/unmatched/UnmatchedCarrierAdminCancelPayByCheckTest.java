package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
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
		wait = new WebDriverWait(driver, 30);
		adminLoginPageObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
		UnCarrierAdminCancelPayByCheckObj = new UnmatchedCarrierAdminCancelPayByCheck();

	}

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {
		adminHomePageObj.AdminURL();
		adminLoginPageObj.adminUserPass(Username, pass);
		adminLoginPageObj.adminLogin();
		adminLoginPageObj.ClickOnCustomersTab();
		log.info(BrokerLoginPage.bemail);
		adminLoginPageObj.ClickOnSearchBox(BrokerLoginPage.bemail);
		adminLoginPageObj.ClickOnSearchButton();
		adminLoginPageObj.DoubleClickID();
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

		UnCarrierAdminCancelPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);
		UnCarrierAdminCancelPayByCheckObj.ContactName(ContactName);
		UnCarrierAdminCancelPayByCheckObj.clickPayByChecksubmit();
		UnCarrierAdminCancelPayByCheckObj.clickCancelPayByCheck();
	}

}
