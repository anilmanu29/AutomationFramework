package testcases.LoadPay.Admin;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Admin.AdminHomePage;
import pages.LoadPay.Admin.AdminLogin;
import pages.LoadPay.Admin.AdminPayByCheck;
import pages.LoadPay.Admin.AdminPayByCheckServiceBatchFile;
import pages.LoadPay.Broker.BrokerLoginPage;
import testcases.LoadPay.Broker.BrokerNewPaymentTest;

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

	

	@Test(dataProvider = "getAdminLoginData", priority =56 )
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
