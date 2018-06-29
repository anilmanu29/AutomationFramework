package testcases.LoadPay.Unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Outlook.outlooklogin;
import pages.LoadPay.Unmatched.UnmatchedCariierEmailVerifyNextDay;

public class UnmatchedCariierEmailVerifyNextDayTest extends TestBase {
	
	UnmatchedCariierEmailVerifyNextDay outlookk;
	outlooklogin outlook;

	public UnmatchedCariierEmailVerifyNextDayTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedCariierEmailVerifyNextDay();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=27)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=28)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	} 

}
