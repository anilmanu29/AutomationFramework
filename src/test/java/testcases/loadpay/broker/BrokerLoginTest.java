package testcases.loadpay.broker;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import util.TestUtil;

public class BrokerLoginTest extends TestBase {
	BrokerLoginPage loginPage;
	String brokerUsername, brokerPassword = "";

	public BrokerLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		wait = new WebDriverWait(driver, 30);
		loginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}

		loginPage.Brokerlogin(brokerUsername, brokerPassword);

		loginPage.completeRegistration();

		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getBtn_logout()));
		Thread.sleep(2000);
		loginPage.BrokerLogout();
	}

}
