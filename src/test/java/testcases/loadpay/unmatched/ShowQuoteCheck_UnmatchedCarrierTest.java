package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

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

public class ShowQuoteCheck_UnmatchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier brokerPaymentforUnmatchedCarrier;
	BrokerLoginPage brokerlogin;
	String payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static ArrayList<String> invoicenum;
	// public static String invoiceNum;
	BrokerLoginPage brokerloginobj;
	BrokerNewPayment brokerpaymentobj;
	BrokerAdvancePaymenttoUnmatchedCarrier brokeradvancepaymentobj;
	AdminHomePage homepage;
	AdminLogin adminlogin;
	AdminPayByCheck adminPayByCheck;
	UnmatchedCarrierAdminPayByCheck UnCarrierAdminPBC;
	public static ArrayList<String> arraylist;
	public static String cemail;
	public static String email;
	public static String brokerUsername;
	public static String brokerPassword;
	String dateTime;
	public static String unMatchedCarrierUsername;
	public static String unMatchedCarrierPassword;
	public static String loadID, invoiceNum = "";

	/*-------Initializing driver---------*/
	public ShowQuoteCheck_UnmatchedCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerlogin = new BrokerLoginPage();
		brokerPaymentforUnmatchedCarrier = new BrokerPaymentforUnmatchedCarrier();
		al = new ArrayList<String>();
		invoicenum = new ArrayList<String>();
		brokerloginobj = new BrokerLoginPage();
		brokerpaymentobj = new BrokerNewPayment();
		brokeradvancepaymentobj = new BrokerAdvancePaymenttoUnmatchedCarrier();
		homepage = new AdminHomePage();
		adminlogin = new AdminLogin();
		arraylist = new ArrayList<String>();
		adminPayByCheck = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();

	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerlogin = new BrokerLoginPage();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerlogin.Brokerlogin(brokerUsername, brokerPassword);
		dateTime = TestUtil.getCurrentDateTime();

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt, String payto, String ein)
			throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicUnmatchedData").contains("true")) {
			String[] emailArray = cemail.split("@");
			emailArray[0] = emailArray[0] + dateTime;

			unMatchedCarrierUsername = emailArray[0] + "@" + emailArray[1];
			unMatchedCarrierPassword = "Password@123";
			invoiceNum = "UM" + TestUtil.getCurrentDateTime();
			loadID = invoiceNum;

		} else {
			unMatchedCarrierUsername = cemail;
			unMatchedCarrierPassword = "Password@123";
		}

		brokerPaymentforUnmatchedCarrier.newPayment();

		brokerPaymentforUnmatchedCarrier.carrierEmail(unMatchedCarrierUsername);
		invoiceNum = brokerPaymentforUnmatchedCarrier.invoiceNumber(invoiceNum);
		invoicenum.add(invoiceNum);
		brokerPaymentforUnmatchedCarrier.amount(amt);

		brokerPaymentforUnmatchedCarrier.loadId(loadID);
		brokerPaymentforUnmatchedCarrier.companyName(payto);
		brokerPaymentforUnmatchedCarrier.clickShedulePayment();
		brokerPaymentforUnmatchedCarrier.clickShedulePaymenttab();
		umemail = brokerPaymentforUnmatchedCarrier.searchCarrier(unMatchedCarrierUsername);
		al.add(umemail);
		brokerPaymentforUnmatchedCarrier.clickSearchButton();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		brokerPaymentforUnmatchedCarrier.verifyInvoiceNumber(invoiceno, amt);
		einno = brokerPaymentforUnmatchedCarrier.getEin(ein);
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(brokerPaymentforUnmatchedCarrier.verifyPaymentStatus());

		// bp.logout();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		homepage.AdminURL();
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		Thread.sleep(1000);
		adminlogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		/*
		 * System.out.println(brokerUsername);
		 * adminlogin.ClickOnSearchBox(brokerUsername); Thread.sleep(1000);
		 */
		adminlogin.ClickOnSearchBox(brokerloginobj.bemail);
		adminlogin.ClickOnSearchButton();
		adminlogin.DoubleClickID();
		adminPayByCheck.clickPayments();
		Thread.sleep(1000);
		adminPayByCheck.ClickOnsearchKeyword(invoicenum.get(1));
		Thread.sleep(1000);
		// System.out.println(UnCarrierAdminPBC.getPaymentId1().getText());
		// adminPayByCheck.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		// Thread.sleep(1000);
		adminPayByCheck.getPaymentID(invoicenum.get(1));
		adminPayByCheck.clickSearch();
		adminPayByCheck.searchKeyword();
		adminPayByCheck.clickSearch1();
		Thread.sleep(1000);
		adminPayByCheck.clickgridcollapse();
		adminPayByCheck.clickPayByCheck();
		adminPayByCheck.selectTerms();
		adminPayByCheck.clickShowQuote();
		adminPayByCheck.selectTermsTermPayment();
		Thread.sleep(1000);
		adminPayByCheck.clickShowQuote();

	}

	public void verifyShowQuoteCheck_UnmatchedCarrierTestDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerPaymentforUnmatchedCarrier.field_invoicenum.isDisplayed(),
				" invoicenum field not found");
		Assert.assertTrue(brokerPaymentforUnmatchedCarrier.btn_search.isDisplayed(), "Search button not found");
		Assert.assertTrue(adminPayByCheck.link_Payments.isDisplayed(), "Payment ID not found");
		Assert.assertTrue(adminPayByCheck.select_Terms.isDisplayed(), "Payments Term & Term Payment not found");
		Assert.assertTrue(adminPayByCheck.ShowQuoteClose.isDisplayed(), "ShowQuoteClose button not found");

	}

}