package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import util.RetryTest;
import util.TestUtil;

public class BrokerOutlookTest extends TestBase {
	OutlookFunctions brokerOutlookObj;
	OutlookFunctions outlook;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public BrokerOutlookTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		outlook = new OutlookFunctions();
		brokerOutlookObj = new OutlookFunctions();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		// OutlookFunctions.checkEmail(un, pwd, 10, "Verify your email address",
		// BrokerRegisterTest.brokerUsername);
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login", retryAnalyzer = RetryTest.class)
	public void outlookloginTest() throws InterruptedException, AWTException {
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		// outlookk.clickOpen();
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokerOutlookObj.outlookSearchInbox(BrokerRegisterTest.brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox(BrokerRegisterTest.brokerUsername);
		brokerOutlookObj.verifyConfirmationMessage();

	}
}
