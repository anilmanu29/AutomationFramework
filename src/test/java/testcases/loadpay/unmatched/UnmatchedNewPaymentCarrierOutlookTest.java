package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;
import pages.loadpay.unmatched.UnmatchedNewPaymentCarrierOutlook;

public class UnmatchedNewPaymentCarrierOutlookTest extends TestBase {
	UnmatchedNewPaymentCarrierOutlook outlookk;
	outlooklogin outlook;

	public UnmatchedNewPaymentCarrierOutlookTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedNewPaymentCarrierOutlook();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
