package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

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
		adminPayByCheckObj=new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
		UnCarrierAdminCancelPayByCheckObj = new UnmatchedCarrierAdminCancelPayByCheck ();
		
	}

	

	/*@Test(dataProvider = "getAdminLoginData", priority =113 )
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		ahp.AdminURL(); 
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		log.info(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		apbc.clickPayments();
		Thread.sleep(3000);
		log.info(UnCarrierAdminPBC.getPaymentId1().getText());
		UnCarrierAdminPBC.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
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
		@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=114)
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
		//al.AdminLogOut();
		//Thread.sleep(2000);
	

	} */
		
		@Test(dataProvider = "getAdminLoginData")
		public void verifyAdminPayByCheckTermPayment(String Username, String pass) throws InterruptedException, AWTException {
			Thread.sleep(1000);
			adminHomePageObj.AdminURL(); 
			Thread.sleep(2000);
			adminLoginPageObj.adminUserPass(Username, pass);
			adminLoginPageObj.adminLogin();
			Thread.sleep(1000);
			adminLoginPageObj.ClickOnCustomersTab();
			Thread.sleep(1000);
			log.info(BrokerLoginPage.bemail);
			adminLoginPageObj.ClickOnSearchBox(BrokerLoginPage.bemail);
			Thread.sleep(1000);
			adminLoginPageObj.ClickOnSearchButton();
			Thread.sleep(1000);
			adminLoginPageObj.DoubleClickID();
			Thread.sleep(1000);
			adminPayByCheckObj.clickPayments();
			Thread.sleep(3000);
			log.info(UnCarrierAdminPBC.getPaymentId1().getText());
			UnCarrierAdminPBC.ClickOnsearchKeywordterm(UnCarrierAdminPBC.getPaymentId1().getText());
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.getPaymentID();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.clickSearch();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.searchKeyword();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.clickSearch1();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.clickgridcollapse();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.clickPayByCheck();
			Thread.sleep(2000);
			UnCarrierAdminCancelPayByCheckObj.selectTerms();
			Thread.sleep(2000);
			adminPayByCheckObj.selectTermsTermPayment();
			Thread.sleep(3000);
			
		}
			@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
			public void carrierTermPaymentPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
			UnCarrierAdminCancelPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);
			Thread.sleep(3000);
			UnCarrierAdminCancelPayByCheckObj.ContactName(ContactName);
			Thread.sleep(3000);
			UnCarrierAdminCancelPayByCheckObj.clickPayByChecksubmit();
			Thread.sleep(3000);
			UnCarrierAdminCancelPayByCheckObj.clickCancelPayByCheck();
			Thread.sleep(2000);
			//apbc.clickAddCheckNumber();
			//Thread.sleep(2000);
			//apbc.ClickOnEnterCheckNumber();
			//Thread.sleep(2000);
			
			

		}

	
}
