package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;
import pages.loadpay.unmatched.UnmatchedCariierEmailVerifyWiretRansfer;

public class UnmatchedCariierEmailVerifyWiretRansferTest extends TestBase {
	UnmatchedCariierEmailVerifyWiretRansfer outlookk;
	outlooklogin outlook;

	public UnmatchedCariierEmailVerifyWiretRansferTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedCariierEmailVerifyWiretRansfer();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.getProperties().getProperty("email"));
		// outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
