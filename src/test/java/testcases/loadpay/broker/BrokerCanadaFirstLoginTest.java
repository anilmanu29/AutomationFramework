package testcases.loadpay.broker;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerCanadaFirstLogin;

public class BrokerCanadaFirstLoginTest extends TestBase {
	BrokerCanadaFirstLogin loginPage;

	public BrokerCanadaFirstLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new BrokerCanadaFirstLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "Login as broker for first time")
	public void loginTest() throws InterruptedException {
		loginPage.brokerfirstLogin();
	}

}
