package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ahp.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.adminUserPass(Username, pass);
		al.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayments();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ClickOnsearchKeyword(brokerInvoices.get(0));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.getPaymentID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickSearch();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.searchKeyword();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickSearch1();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickgridcollapse();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayByCheck();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.selectTerms();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheck")
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ContactName(ContactName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayByChecksubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickAddCheckNumber();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ClickOnEnterCheckNumber();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenowPayByCheck")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayments();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ClickOnsearchKeyword(brokerInvoices.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.getPaymentID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickSearch();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.searchKeyword();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickSearch1();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickgridcollapse();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayByCheck();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.selectTerms();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.selectTermsTermPayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ContactName(ContactName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickPayByChecksubmit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.clickAddCheckNumber();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		apbc.ClickOnEnterCheckNumber();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

}
