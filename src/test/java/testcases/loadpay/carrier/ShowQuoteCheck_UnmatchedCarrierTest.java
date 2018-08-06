package testcases.loadpay.carrier;

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

public class ShowQuoteCheck_UnmatchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier brokerPaymentforUnmatchedCarrier;
	BrokerLoginPage brokerlogin;
	String payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static ArrayList<String> in;
	public static String invoiceNum;
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
		in = new ArrayList<String>();
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
		brokerlogin.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt, String payto, String ein)
			throws InterruptedException {

		brokerPaymentforUnmatchedCarrier.newPayment();
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.carrierEmail(cemail);
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.amount(amt);
		Thread.sleep(1000);
		invoiceNum = brokerPaymentforUnmatchedCarrier.invoiceNumber(invoiceno);
		in.add(invoiceNum);
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.loadId(loadid);
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.companyName(payto);
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.clickShedulePayment();
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.clickShedulePaymenttab();
		Thread.sleep(1000);
		umemail = brokerPaymentforUnmatchedCarrier.searchCarrier(cemail);
		al.add(umemail);
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		brokerPaymentforUnmatchedCarrier.verifyInvoiceNumber(invoiceno, amt);
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		adminlogin.DoubleClickID();
		Thread.sleep(1000);
		adminPayByCheck.clickPayments();
		Thread.sleep(1000);
		System.out.println(UnCarrierAdminPBC.getPaymentId1().getText());
		adminPayByCheck.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		Thread.sleep(1000);
		adminPayByCheck.getPaymentID();
		adminPayByCheck.clickSearch();
		adminPayByCheck.searchKeyword();
		adminPayByCheck.clickSearch1();
		Thread.sleep(1000);
		adminPayByCheck.clickgridcollapse();
		Thread.sleep(1000);
		adminPayByCheck.clickPayByCheck();
		Thread.sleep(1000);
		adminPayByCheck.selectTerms();
		Thread.sleep(2000);
		adminPayByCheck.clickShowQuote();
		Thread.sleep(2000);
		adminPayByCheck.selectTermsTermPayment();
		Thread.sleep(2000);
		adminPayByCheck.clickShowQuote();
		Thread.sleep(1000);

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
