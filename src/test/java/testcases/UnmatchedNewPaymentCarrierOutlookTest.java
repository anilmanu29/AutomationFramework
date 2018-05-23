package testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerOutlook;
import pages.CarrierOutlook;
import pages.UnmatchedNewPaymentCarrierOutlook;
import pages.outlooklogin;

public class UnmatchedNewPaymentCarrierOutlookTest extends TestBase {
	UnmatchedNewPaymentCarrierOutlook outlookk;
	outlooklogin outlook;

	public UnmatchedNewPaymentCarrierOutlookTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedNewPaymentCarrierOutlook();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=37)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=38)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
