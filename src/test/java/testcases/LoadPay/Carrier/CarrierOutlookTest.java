package testcases.LoadPay.Carrier;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Carrier.CarrierOutlook;
import pages.LoadPay.Outlook.outlooklogin;

public class CarrierOutlookTest extends TestBase {
	CarrierOutlook outlookk;
	outlooklogin outlook;

	public CarrierOutlookTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new CarrierOutlook();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=11)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=12)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}
}
