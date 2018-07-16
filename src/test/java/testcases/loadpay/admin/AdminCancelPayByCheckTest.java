package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

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
		adminPayByCheckObj=new AdminPayByCheck();
		adminCancelPayByCheckObj = new AdminCancelPayByCheck();
		
		
	}

	

	/*@Test(dataProvider = "getAdminLoginData", priority =18 )
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
		apbc.ClickOnsearchKeyword(BrokerNewPaymentTest.al.get(0));
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
		@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=19)
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
			adminLogObj.adminUserPass(Username, pass);
			adminLogObj.adminLogin();
			Thread.sleep(1000);
			adminLogObj.ClickOnCustomersTab();
			Thread.sleep(1000);
			log.info(BrokerLoginPage.bemail);
			adminLogObj.ClickOnSearchBox(BrokerLoginPage.bemail);
			Thread.sleep(1000);
			adminLogObj.ClickOnSearchButton();
			Thread.sleep(1000);
			adminLogObj.DoubleClickID();
			Thread.sleep(1000);
			adminPayByCheckObj.clickPayments();
			Thread.sleep(3000);
			log.info(adminCancelPayByCheckObj);
			adminCancelPayByCheckObj.ClickOnsearchKeyword(adminCancelPayByCheckObj.getPaymentId1().getText());
			//acpbc.ClickOnsearchKeywordterm(BrokerNewPaymentTest.al.get(0));
			Thread.sleep(2000);
			adminCancelPayByCheckObj.getPaymentID();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.clickSearch();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.searchKeyword();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.clickSearch1();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.clickgridcollapse();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.clickPayByCheck();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.selectTerms();
			Thread.sleep(2000);
			adminCancelPayByCheckObj.selectTermsTermPayment();
			Thread.sleep(3000);
			
		}
			@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
			public void carrierTermPaymentPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
			adminCancelPayByCheckObj.EnterDOTNnumber(EnterDOTNnumber);
			Thread.sleep(3000);
			adminCancelPayByCheckObj.ContactName(ContactName);
			Thread.sleep(3000);
			adminCancelPayByCheckObj.clickPayByChecksubmit();
			Thread.sleep(3000);
			adminCancelPayByCheckObj.clickCancelPayByCheck();
			/*apbc.clickAddCheckNumber();
			Thread.sleep(2000);
			apbc.ClickOnEnterCheckNumber();
			Thread.sleep(2000);*/
			
			

		}

	
}
