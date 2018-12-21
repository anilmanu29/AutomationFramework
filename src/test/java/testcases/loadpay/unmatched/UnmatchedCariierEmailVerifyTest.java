package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.unmatched.UnmatchedCariierEmailVerify;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class UnmatchedCariierEmailVerifyTest extends TestBase {
	UnmatchedCariierEmailVerify outlookk;
	OutlookFunctions outlook;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public UnmatchedCariierEmailVerifyTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		outlook = new OutlookFunctions();
		outlookk = new UnmatchedCariierEmailVerify();
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
		outlookk.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		Thread.sleep(10000);
		outlookk.outlookSearchInbox(BrokerPaymentforUnmatchedCarrierTest.al.get(0) + " AND NOT payments@loadpay.com",
				currentHour, currentMinutes);
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}
}
