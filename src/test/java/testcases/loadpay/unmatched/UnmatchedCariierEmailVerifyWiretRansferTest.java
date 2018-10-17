package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;
import pages.loadpay.unmatched.UnmatchedCariierEmailVerifyWiretRansfer;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class UnmatchedCariierEmailVerifyWiretRansferTest extends TestBase {
	UnmatchedCariierEmailVerifyWiretRansfer outlookk;
	outlooklogin outlook;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public UnmatchedCariierEmailVerifyWiretRansferTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedCariierEmailVerifyWiretRansfer();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
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
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		outlookk.outlookSearchInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(1), currentHour, currentMinutes);
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
