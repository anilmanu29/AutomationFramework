package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.outlook.outlooklogin;
import util.RetryTest;
import util.TestUtil;

public class BrokerOutlookTest extends TestBase {
	BrokerOutlook brokerOutlookObj;
	outlooklogin outlook;
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
		outlook = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login", retryAnalyzer = RetryTest.class)
	public void outlookloginTest() throws InterruptedException, AWTException {
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		// outlookk.clickOpen();
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokerOutlookObj.outlookSearchInbox(BrokerRegisterTest.brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox();
		brokerOutlookObj.verifyConfirmationMessage();

	}
}
