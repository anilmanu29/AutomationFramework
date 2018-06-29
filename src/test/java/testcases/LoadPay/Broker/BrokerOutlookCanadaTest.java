package testcases.LoadPay.Broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Broker.BrokerOutlook;
import pages.LoadPay.Broker.BrokerOutlookCanada;
import pages.LoadPay.Outlook.outlooklogin;

public class BrokerOutlookCanadaTest extends TestBase {
	BrokerOutlookCanada outlookk;
	outlooklogin outlook;

	public BrokerOutlookCanadaTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new BrokerOutlookCanada();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=2)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=3)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}


}
