package testcases.LoadPay.Carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerOutlook;
import pages.LoadPay.Carrier.CarrierOutlook;
import pages.LoadPay.Outlook.outlooklogin;

public class CariierOutlookTest extends TestBase {
	CarrierOutlook outlookk;
	outlooklogin outlook;

	public CariierOutlookTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new CarrierOutlook();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=9)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=10)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
