package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayMeNowUnlockTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPayMeNowTab;

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

	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		brokerUserName = user;
		brokerPassword = pass;
	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyLockPayMeNowStatus(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.adminUserPass(Username, pass);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnSearchBox(brokerUserName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.openPayMeNowTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.clickEnrollInPayMeNow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.clickLockPaymeNowStatusButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.clickUpdateButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.AdminLogOut();

	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyLockPayMeNowStatus")
	public void verifyBrokerCannotOptOutPayMeNow(String un, String pwd) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.get(super.getProperties().getProperty("url"));
		brokLoginPage.Brokerlogin(un, pwd);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPayMeNowTab.openAccountTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPayMeNowTab.openPayMeNowTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		checkbox = driver.findElement(By.id("PMNEnrolled"));
		if (!checkbox.isEnabled()) {
			log.info("Enroll in PayMeNow is Disabled");
		} else {
			log.info("Enroll in PayMeNow is Enabled");
		}
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokLoginPage.BrokerLogout();
	}

	@Test(description = "LP-6167 AdminUnlockPayMeNowTest", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyBrokerCannotOptOutPayMeNow")
	public void verifyAdminUnlockPayMeNowStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.adminUserPass(Username, pass);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnSearchBox(brokerUserName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.openPayMeNowTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.clickUnLockPaymeNowStatusButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowUnlockTab.clickUpdateButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminUnlockPayMeNowStatus")
	public void verifyBrokerCanEnrollInPayMeNow(String un, String pwd) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.get(super.getProperties().getProperty("url"));
		brokLoginPage.Brokerlogin(un, pwd);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPayMeNowTab.openAccountTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPayMeNowTab.openPayMeNowTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		checkbox = driver.findElement(By.id("PMNEnrolled"));

		brokerPayMeNowTab.enrollPayMeNow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info("User enrolled in PayMeNow");
		brokerPayMeNowTab.updateButton();

		/*
		 * if (!checkbox.isEnabled()) { brokerPayMeNowTab.enrollPayMeNow();
		 * log.info("Enroll in PayMeNow is Disabled"); } else {
		 * log.info("Enroll in PayMeNow is Enabled"); }
		 */
	}

}
