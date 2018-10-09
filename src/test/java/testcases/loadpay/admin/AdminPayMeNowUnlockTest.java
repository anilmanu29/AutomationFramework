package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayMeNowUnlockTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPayMeNowTab;
import testcases.loadpay.broker.BrokerRegisterTest;

public class AdminPayMeNowUnlockTest extends TestBase {
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	AdminPayMeNowUnlockTab admPayMeNowUnlockTab;
	BrokerLoginPage brokLoginPage;
	BrokerPayMeNowTab brokerPayMeNowTab;
	WebElement checkbox;
	String brokerUserName;
	String brokerPassword;

	public AdminPayMeNowUnlockTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokerPayMeNowTab = new BrokerPayMeNowTab();
		brokLoginPage = new BrokerLoginPage();
		admPayMeNowUnlockTab = new AdminPayMeNowUnlockTab();
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
	public void verifyLockPayMeNowStatus(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();

		admLogin.adminUserPass(Username, pass);

		admLogin.adminLogin();

		admLogin.ClickOnCustomersTab();

		admLogin.ClickOnSearchBox(brokerUserName);

		admLogin.ClickOnSearchButton();

		admLogin.DoubleClickID();

		admPayMeNowUnlockTab.openPayMeNowTab();

		admPayMeNowUnlockTab.clickEnrollInPayMeNow();

		admPayMeNowUnlockTab.clickLockPaymeNowStatusButton();

		admPayMeNowUnlockTab.clickUpdateButton();

		admLogin.AdminLogOut();

	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyLockPayMeNowStatus")
	public void verifyBrokerCannotOptOutPayMeNow(String un, String pwd) throws InterruptedException, AWTException {

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

		brokLoginPage.BrokerLogout();
	}

	@Test(description = "LP-6167 AdminUnlockPayMeNowTest", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyBrokerCannotOptOutPayMeNow")
	public void verifyAdminUnlockPayMeNowStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();

		admLogin.adminUserPass(Username, pass);

		admLogin.adminLogin();

		admLogin.ClickOnCustomersTab();

		admLogin.ClickOnSearchBox(brokerUserName);

		admLogin.ClickOnSearchButton();

		admLogin.DoubleClickID();

		admPayMeNowUnlockTab.openPayMeNowTab();

		admPayMeNowUnlockTab.clickUnLockPaymeNowStatusButton();

		admPayMeNowUnlockTab.clickUpdateButton();

		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminUnlockPayMeNowStatus")
	public void verifyBrokerCanEnrollInPayMeNow(String un, String pwd) throws InterruptedException, AWTException {

		driver.get(super.getProperties().getProperty("url"));
		brokLoginPage.Brokerlogin(brokerUserName, brokerPassword);

		brokerPayMeNowTab.openAccountTab();

		brokerPayMeNowTab.openPayMeNowTab();

		checkbox = driver.findElement(By.id("PMNEnrolled"));

		brokerPayMeNowTab.enrollPayMeNow();

		log.info("User enrolled in PayMeNow");
		brokerPayMeNowTab.updateButton();
	}

}
