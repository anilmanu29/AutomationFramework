package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheckServiceBatchFile;
import testcases.loadpay.broker.BrokerNewPaymentTest;

public class AdminPayByCheckServiceBatchFileTest extends TestBase {

	AdminHomePage ahp;
	AdminLogin al;
	AdminPayByCheckServiceBatchFile apbcf;
	public static String acountname;
	

	/*-------Initializing driver---------*/

	public AdminPayByCheckServiceBatchFileTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		
		al = new AdminLogin();
		ahp = new AdminHomePage(); 
		apbcf=new AdminPayByCheckServiceBatchFile();
	}

	

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheckServiceBatchFile(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		ahp.AdminURL(); 
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		apbcf.clickDailyCheckPaymentFiles();
		Thread.sleep(1000);
		apbcf.ClickShowPaymentsForBatch();
		Thread.sleep(1000);
		apbcf.EnterEmailID(BrokerNewPaymentTest.email);
		apbcf.ClickSendButton();
		al.AdminLogOut();
		
	}

		
	
}
