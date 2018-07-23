package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPaymeNowTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPayMeNowTab;

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
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		admPayMeNowTab = new AdminPaymeNowTab();
		brokerPayMeNowTab = new BrokerPayMeNowTab();
		brokLoginPage = new BrokerLoginPage();

	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		brokerUserName = user;
		brokerPassword = pass;
	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyLockPayMeNowStatus(String Username, String pass)
			throws InterruptedException, AWTException, IOException, InvalidFormatException {
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
		admPayMeNowTab.openPayMeNowTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowTab.clickEnrollInPayMeNow();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowTab.clickLockPaymeNowStatusButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		admPayMeNowTab.clickUpdateButton();
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

	}
}
