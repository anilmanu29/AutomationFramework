package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.outlook.outlooklogin;

public class BrokerOutlookTest extends TestBase {
	BrokerOutlook outlookk;
	outlooklogin outlook;

	public BrokerOutlookTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new BrokerOutlook();
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
