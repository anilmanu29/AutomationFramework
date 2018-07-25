package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;

public class AdminPayByCheckTest extends TestBase {

	AdminHomePage ahp;
	AdminLogin al;
	AdminPayByCheck apbc;
	public static String acountname;
	String brokerUsername = "";
	String brokerPassword = "";
	public static ArrayList<String> brokerInvoices;

	/*-------Initializing driver---------*/

	public AdminPayByCheckTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();

		al = new AdminLogin();
		ahp = new AdminHomePage();
		apbc = new AdminPayByCheck();
		brokerInvoices = new ArrayList<String>();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String username, String pwd) throws InterruptedException {
		// login as broker
		brokerUsername = username;
		brokerPassword = pwd;
	}

	@Test(dataProvider = "getPaymentData")
	public void getBrokerInvoiceNumbers(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		// login as broker
		brokerInvoices.add(invoiceno);
	}

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		ahp.AdminURL();

		al.adminUserPass(Username, pass);
		al.adminLogin();

		al.ClickOnCustomersTab();

		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);

		al.ClickOnSearchButton();

		al.DoubleClickID();

		apbc.clickPayments();

		apbc.ClickOnsearchKeyword(brokerInvoices.get(0));

		apbc.getPaymentID();

		apbc.clickSearch();

		apbc.searchKeyword();

		apbc.clickSearch1();

		apbc.clickgridcollapse();

		apbc.clickPayByCheck();

		apbc.selectTerms();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheck")
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);

		apbc.ContactName(ContactName);

		apbc.clickPayByChecksubmit();

		apbc.clickAddCheckNumber();

		apbc.ClickOnEnterCheckNumber();

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenowPayByCheck")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {

		al.ClickOnCustomersTab();

		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);

		al.ClickOnSearchButton();

		al.DoubleClickID();

		apbc.clickPayments();

		apbc.ClickOnsearchKeyword(brokerInvoices.get(1));

		apbc.getPaymentID();

		apbc.clickSearch();

		apbc.searchKeyword();

		apbc.clickSearch1();

		apbc.clickgridcollapse();

		apbc.clickPayByCheck();

		apbc.selectTerms();

		apbc.selectTermsTermPayment();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);

		apbc.ContactName(ContactName);

		apbc.clickPayByChecksubmit();

		apbc.clickAddCheckNumber();

		apbc.ClickOnEnterCheckNumber();

	}

}
