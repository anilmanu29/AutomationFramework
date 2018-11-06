package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPaymeNowTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPayMeNowTab;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class AdminPayMeNowLockTest extends TestBase {
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	AdminPaymeNowTab admPayMeNowTab;
	BrokerLoginPage brokLoginPage;
	BrokerPayMeNowTab brokerPayMeNowTab;
	WebElement checkbox;
	String brokerUserName;
	String brokerPassword;

	public AdminPayMeNowLockTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		admPayMeNowTab = new AdminPaymeNowTab();
		brokerPayMeNowTab = new BrokerPayMeNowTab();
		brokLoginPage = new BrokerLoginPage();
		wait = new WebDriverWait(driver, 30);

	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUserName = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUserName = user;
			brokerPassword = pass;
		}
	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyLockPayMeNowStatus(String Username, String pass)
			throws InterruptedException, AWTException, IOException, InvalidFormatException {
		admHomePage.AdminURL();

		admLogin.adminUserPass(Username, pass);

		admLogin.adminLogin();

		admLogin.ClickOnCustomersTab();

		admLogin.ClickOnSearchBox(brokerUserName);

		admLogin.ClickOnSearchButton();

		admLogin.DoubleClickID();

		admPayMeNowTab.openPayMeNowTab();

		admPayMeNowTab.clickEnrollInPayMeNow();

		admPayMeNowTab.clickLockPaymeNowStatusButton();

		admPayMeNowTab.clickUpdateButton();

		admLogin.AdminLogOut();

	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dependsOnMethods = "verifyLockPayMeNowStatus")
	public void verifyBrokerCannotOptOutPayMeNow() throws InterruptedException, AWTException {

		driver.get(super.getProperties().getProperty("url"));
		brokLoginPage.Brokerlogin(brokerUserName, brokerPassword);

		brokerPayMeNowTab.openAccountTab();

		brokerPayMeNowTab.openPayMeNowTab();

		checkbox = driver.findElement(By.id("PMNEnrolled"));
		if (!checkbox.isEnabled()) {
			log.info("Enroll in PayMeNow is Disabled");
		} else {
			log.info("Enroll in PayMeNow is Enabled");
		}

	}
}
