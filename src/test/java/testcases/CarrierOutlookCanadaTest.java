package testcases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CarrierOutlookCanada;
import pages.outlooklogin;

public class CarrierOutlookCanadaTest extends TestBase {
	CarrierOutlookCanada outlookk;
	outlooklogin outlook;

	public CarrierOutlookCanadaTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new CarrierOutlookCanada();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=7)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=8)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}
 

}
