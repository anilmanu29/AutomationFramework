package testcases.loadpay.outlook;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;
import util.TestUtil;

public class outlooklogintest extends TestBase {

	outlooklogin loginPage;

	public outlooklogintest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new outlooklogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void loginTest(String user, String pass) throws InterruptedException, AWTException {
		loginPage.outlookLogin(user, pass);

		Thread.sleep(5000);
		/* loginPage.BrokerLogout(); */
	}

}
