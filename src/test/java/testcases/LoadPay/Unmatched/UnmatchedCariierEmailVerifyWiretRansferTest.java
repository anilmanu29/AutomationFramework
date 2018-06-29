package testcases.LoadPay.Unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Outlook.outlooklogin;
import pages.LoadPay.Unmatched.UnmatchedCariierEmailVerify;
import pages.LoadPay.Unmatched.UnmatchedCariierEmailVerifyWiretRansfer;

public class UnmatchedCariierEmailVerifyWiretRansferTest extends TestBase {
	UnmatchedCariierEmailVerifyWiretRansfer outlookk;
	outlooklogin outlook;

	public UnmatchedCariierEmailVerifyWiretRansferTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedCariierEmailVerifyWiretRansfer();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=36)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=37)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	} 

}
