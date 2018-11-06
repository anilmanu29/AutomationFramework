package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerAdvancePaymenttoUnmatchedCarrier;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;
import pages.loadpay.unmatched.UnmatchedCarrierAdminPayByCheck;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class ShowQuoteCheck_matchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier brokerPaymentforUnmatchedCarrier;
	BrokerLoginPage brokerLoginObj;
	BrokerNewPayment brokerNewPaymentObj;
	BrokerAdvancePaymenttoUnmatchedCarrier brokeradvancepaymentobj;
	AdminHomePage homepage;
	AdminLogin adminlogin;
	AdminPayByCheck adminPayByCheck;
	UnmatchedCarrierAdminPayByCheck UnCarrierAdminPBC;

	String payment_status = "Unmatched";
	int invoiceNum = 0;

	String brokerUsername;
	String brokerPassword;
	public static String newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum, carrierEmail = "";

	/*-------Initializing driver---------*/
	public ShowQuoteCheck_matchedCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		brokerNewPaymentObj = new BrokerNewPayment();
		brokerPaymentforUnmatchedCarrier = new BrokerPaymentforUnmatchedCarrier();
		brokerLoginObj = new BrokerLoginPage();
		brokeradvancepaymentobj = new BrokerAdvancePaymenttoUnmatchedCarrier();
		homepage = new AdminHomePage();
		adminlogin = new AdminLogin();
		adminPayByCheck = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerLoginObj.Brokerlogin(brokerUsername, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dependsOnMethods = "loginBroker")
	public void brokernewPayment() throws InterruptedException {

		carrierEmail = CarrierRegisterTest.carrierUsername;
		newPaymentInvoiceNum = TestUtil.getCurrentDateTime();
		newPaymentLoadId = newPaymentInvoiceNum;
		Integer paymentAmount = TestUtil.getRandomNumber(100, 1000);
		newPaymentAmount = paymentAmount.toString();

		brokerNewPaymentObj = new BrokerNewPayment();
		brokerNewPaymentObj.newPayment();
		brokerNewPaymentObj.carrierEmail(carrierEmail);
		brokerNewPaymentObj.amount(newPaymentAmount);
		brokerNewPaymentObj.invoiceNumber(newPaymentInvoiceNum);
		brokerNewPaymentObj.loadId(newPaymentLoadId);
		brokerNewPaymentObj.clickShedulePayment();
		brokerNewPaymentObj.clickShedulePaymenttab();
		brokerNewPaymentObj.searchCarrier(carrierEmail);
		brokerNewPaymentObj.clickSearchButton();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		brokerNewPaymentObj.verifyInvoiceNumber(newPaymentInvoiceNum, newPaymentAmount);
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		homepage.AdminURL();
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		adminlogin.ClickOnCustomersTab();

		adminlogin.ClickOnSearchBox(brokerUsername);
		// Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		// Thread.sleep(1000);
		adminlogin.DoubleClickID();
		// Thread.sleep(1000);
		adminPayByCheck.clickPayments();
		Thread.sleep(1000);
		adminPayByCheck.ClickOnsearchKeyword(newPaymentInvoiceNum);
		// System.out.println(UnCarrierAdminPBC.getPaymentId1().getText());
		// adminPayByCheck.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		Thread.sleep(1000);
		adminPayByCheck.getPaymentID(newPaymentInvoiceNum);
		adminPayByCheck.clickSearch();
		adminPayByCheck.searchKeyword();
		adminPayByCheck.clickSearch1();
		adminPayByCheck.clickgridcollapse();
		adminPayByCheck.clickPayByCheck();
		adminPayByCheck.selectTerms();
		adminPayByCheck.clickShowQuote();
		adminPayByCheck.selectTermsTermPayment();
		adminPayByCheck.clickShowQuote();
	}

	public void verifyShowQuoteCheck_UnmatchedCarrierTestDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerNewPaymentObj.field_InvoiceNum.isDisplayed(), " invoicenum field not found");
		Assert.assertTrue(brokerNewPaymentObj.btn_search.isDisplayed(), "Search button not found");
		Assert.assertTrue(adminPayByCheck.link_Payments.isDisplayed(), "Payment ID not found");
		Assert.assertTrue(adminPayByCheck.select_Terms.isDisplayed(), "Payments Term & Term Payment not found");
		Assert.assertTrue(adminPayByCheck.ShowQuoteClose.isDisplayed(), "ShowQuoteClose button not found");

	}

}