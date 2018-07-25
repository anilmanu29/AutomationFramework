package testcases.loadpay.outlook;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.outlook.outlooklogin;

public class outlooklogintest extends TestBase {

	outlooklogin loginPage;

	public outlooklogintest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		loginPage = new outlooklogin();

	}

	@Test(dataProvider = "getoutlookLoginData")
	public void loginTest(String user, String pass) throws InterruptedException, AWTException {
		loginPage.outlookLogin(user, pass);

		Thread.sleep(5000);
		/* loginPage.BrokerLogout(); */
	}

}
