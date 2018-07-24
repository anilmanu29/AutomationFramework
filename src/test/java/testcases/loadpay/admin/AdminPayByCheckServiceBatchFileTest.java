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
		apbcf = new AdminPayByCheckServiceBatchFile();
	}

	@Test(dataProvider = "getAdminLoginData")
	public void verifyAdminPayByCheckServiceBatchFile(String Username, String pass)
			throws InterruptedException, AWTException {

		ahp.AdminURL();

		al.adminUserPass(Username, pass);
		al.adminLogin();

		apbcf.clickDailyCheckPaymentFiles();

		apbcf.ClickShowPaymentsForBatch();

		apbcf.EnterEmailID(BrokerNewPaymentTest.email);
		apbcf.ClickSendButton();
		al.AdminLogOut();

	}

}
