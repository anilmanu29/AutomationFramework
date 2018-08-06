package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.outlook.outlooklogin;

public class AdminPaymentHistoryTest extends TestBase {

	AdminHomePage admHomePage;
	AdminLogin admLogin;
	BrokerLoginPage brokLoginPage;
	CarrierLoginPage loginPage;
	WebElement checkbox;
	String brokerUserName;
	String brokerPassword;
	BrokerOutlook outlookk;
	CarrierOutlook carrieroutlookk;
	outlooklogin outlook;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	CarrierLoginPage carrierloginPage;
	String carrierUserName;
	String carrierPassword;

	/*-------Initializing driver---------*/
	public AdminPaymentHistoryTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokLoginPage = new BrokerLoginPage();
		currentTime = new Date();
		outlook = new outlooklogin();
		outlookk = new BrokerOutlook();
		currentTime = new Date();
		carrierloginPage = new CarrierLoginPage();
		carrieroutlookk = new CarrierOutlook();

	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		brokerUserName = user;
		brokerPassword = pass;
	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminPaymentHistoryStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		Thread.sleep(1000);
		admLogin.adminUserPass(Username, pass);
		Thread.sleep(2000);
		admLogin.adminLogin();
		Thread.sleep(100);
		admLogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		admLogin.ClickOnSearchBox(brokerUserName);
		Thread.sleep(1000);
		admLogin.ClickOnSearchButton();
		Thread.sleep(1000);
		admLogin.DoubleClickID();
		admLogin.clickPaymentHistory();
		admLogin.clickpaymnt_Historydownload();
		admLogin.clicforwardfile();
		admLogin.EnterEmailTo(brokerUserName);
		admLogin.ClickcarriersendemailToVerify();
		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatus")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		// outlookk.clickOpen();
		getTimestamp();
		outlookk.outlookSearchInbox(brokerUserName, currentHour, currentMinutes);
		outlookk.handleNewInbox();

	}

	public void getTimestamp() {
		/////////////////////////////////////////////////////////////////
		TimeZone tz = Calendar.getInstance().getTimeZone();
		String currentTimeZone = tz.getDisplayName();
		System.out.println(currentTimeZone);

		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];

		System.out.println("\n\n\n===============================");
		System.out.println("Current date: " + longTime);
		System.out.println("Formatted date: " + formattedDate);
		System.out.println("Current Hour: " + currentHour);
		System.out.println("Current Minutes: " + currentMinutes);
		System.out.println("===============================");
	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "outlookloginTest")
	public void carrierloginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierloginTest")
	public void verifyAdminPaymentHistoryStatuscarrier(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		Thread.sleep(1000);
		admLogin.adminUserPass(Username, pass);
		Thread.sleep(2000);
		admLogin.adminLogin();
		Thread.sleep(100);
		admLogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		admLogin.ClickOnSearchBox(carrierUserName);
		Thread.sleep(1000);
		admLogin.ClickOnSearchButton();
		Thread.sleep(1000);
		admLogin.DoubleClickID();
		admLogin.clickcarrierPaymentHistory();
		admLogin.clickpaymntcarrierHistorydownload();
		admLogin.cliccarrierforwardfile();
		admLogin.EnterEmailTo(carrierUserName);
		admLogin.ClickcarriersendemailToVerify();
		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatuscarrier")
	public void outlookcarrierloginTest(String un, String pwd) throws InterruptedException, AWTException {
		driver.get(prop.getProperty("outlookurl"));
		carrieroutlookk.clickPopUp();
		carrieroutlookk.clickOpenMailBox();
		carrieroutlookk.enterEmail(super.prop.getProperty("email"));
		carrieroutlookk.outlookSearchInbox(carrierUserName, currentHour, currentMinutes);

		/* carrieroutlookk.handleNewInbox(); */
		/* outlookk.verifyConfirmationMessage(); */

	}

	public void verifyAdminPaymentHistoryTestDisplayed() {

		// Verify that the web elements for the AdminPaymentHistoryTest exist
		Assert.assertTrue(admLogin.search.isDisplayed(), " Broker Email ID should not be found");
		Assert.assertTrue(admLogin.paymnt_Historydownload.isDisplayed(), "Payment History Download not found");
		Assert.assertTrue(admLogin.emailTo.isDisplayed(), "Email To not found");
		Assert.assertTrue(outlookk.fieldTextbox.isDisplayed(), "Broker OutLook Email should not be found");
		Assert.assertTrue(carrieroutlookk.fieldTextbox.isDisplayed(), "Carrier OutLook Email should not be found");

	}

}
