package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

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
		apbc=new AdminPayByCheck();
		brokerInvoices = new ArrayList<String>();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String username, String pwd) throws InterruptedException {
		// login as broker
		brokerUsername = username;
		brokerPassword = pwd;
	}
	
	@Test(dataProvider="getPaymentData")
	public void getBrokerInvoiceNumbers(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		// login as broker
		brokerInvoices.add(invoiceno);
	}

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		ahp.AdminURL(); 
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		apbc.clickPayments();
		Thread.sleep(3000);
		apbc.ClickOnsearchKeyword(brokerInvoices.get(0));
		Thread.sleep(2000);
		apbc.getPaymentID();
		Thread.sleep(2000);
		apbc.clickSearch();
		Thread.sleep(2000);
		apbc.searchKeyword();
		Thread.sleep(2000);
		apbc.clickSearch1();
		Thread.sleep(2000);
		apbc.clickgridcollapse();
		Thread.sleep(2000);
		apbc.clickPayByCheck();
		Thread.sleep(2000);
		apbc.selectTerms();
		Thread.sleep(3000);
		
	}
		@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheck")
		public void carrierPaymenowPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);
		Thread.sleep(3000);
		apbc.ContactName(ContactName);
		Thread.sleep(3000);
		apbc.clickPayByChecksubmit();
		Thread.sleep(3000);
		apbc.clickAddCheckNumber();
		Thread.sleep(2000);
		apbc.ClickOnEnterCheckNumber();
		Thread.sleep(2000);
	}
		
		@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenowPayByCheck")
		public void verifyAdminPayByCheckTermPayment(String Username, String pass) throws InterruptedException, AWTException {
			Thread.sleep(1000);
			al.ClickOnCustomersTab();
			Thread.sleep(1000);
			log.info(brokerUsername);
			al.ClickOnSearchBox(brokerUsername);
			Thread.sleep(1000);
			al.ClickOnSearchButton();
			Thread.sleep(1000);
			al.DoubleClickID();
			Thread.sleep(1000);
			apbc.clickPayments();
			Thread.sleep(3000);
			apbc.ClickOnsearchKeywordterm(brokerInvoices.get(1));
			Thread.sleep(2000);
			apbc.getPaymentID();
			Thread.sleep(2000);
			apbc.clickSearch();
			Thread.sleep(2000);
			apbc.searchKeyword();
			Thread.sleep(2000);
			apbc.clickSearch1();
			Thread.sleep(2000);
			apbc.clickgridcollapse();
			Thread.sleep(2000);
			apbc.clickPayByCheck();
			Thread.sleep(2000);
			apbc.selectTerms();
			Thread.sleep(2000);
			apbc.selectTermsTermPayment();
			Thread.sleep(3000);
			
		}
			@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",dependsOnMethods = "verifyAdminPayByCheckTermPayment")
			public void carrierTermPaymentPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
			apbc.EnterDOTNnumber(EnterDOTNnumber);
			Thread.sleep(3000);
			apbc.ContactName(ContactName);
			Thread.sleep(3000);
			apbc.clickPayByChecksubmit();
			Thread.sleep(3000);
			apbc.clickAddCheckNumber();
			Thread.sleep(2000);
			apbc.ClickOnEnterCheckNumber();
			Thread.sleep(2000);
		}

	
}
