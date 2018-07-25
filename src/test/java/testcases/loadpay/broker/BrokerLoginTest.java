package testcases.loadpay.broker;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerLoginTest extends TestBase {
	BrokerLoginPage loginPage;

	public BrokerLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		wait = new WebDriverWait(driver, 30);
		loginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		loginPage.Brokerlogin(user, pass);

		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getBtn_logout()));
		loginPage.BrokerLogout();
	}

}
