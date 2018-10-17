package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerOutlookCanada;
import pages.loadpay.outlook.outlooklogin;
import util.TestUtil;

public class BrokerOutlookCanadaTest extends TestBase {
	BrokerOutlookCanada outlookk;
	outlooklogin outlook;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public BrokerOutlookCanadaTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new BrokerOutlookCanada();
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
		outlookk.outlookSearchInbox(BrokerRegisterCanadaTest.brokerUsername, currentHour, currentMinutes);
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}
}
