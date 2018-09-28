package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNotifications;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import testcases.loadpay.carrier.CarrierRegisterTest;

public class BrokerNotificationsTest extends TestBase {
	BrokerNotifications brokernotificationsobj;
	CarrierLoginPage carrierloginobj;
	CarrierNextDAYACH carriernextdayachobj;
	BrokerLoginPage brokerloginobj;
	String invoiceNumber = "";

	String carrierUsername, carrierPassword = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public BrokerNotificationsTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		brokernotificationsobj = new BrokerNotifications();
		carrierloginobj = new CarrierLoginPage();
		carriernextdayachobj = new CarrierNextDAYACH();
		brokerloginobj = new BrokerLoginPage();
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-6361 Broker Notifications", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokernotificationsobj.loginAsBroker(brokerUsername, brokerPassword);
		System.out.println("loginAsBrokerTest - Passed");
	}

	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "loginAsBrokerTest" })
	public void brokerCreateNewPaymentTest() throws InterruptedException {
		brokernotificationsobj.brokerCreateNewPayment();
		System.out.println("brokerCreateNewPaymentTest - Passed");
	}

	/*-------Login to Carrier ---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = {
			"brokerCreateNewPaymentTest" }, dataProvider = "getCarrierLoginData")
	public void loginAsCarrierTest(String username, String password) throws InterruptedException, AWTException {
		brokerloginobj.BrokerLogout();

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = username;
			carrierPassword = password;
		}

		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
	}

	/*-------Verify Carrier Next Day ACH---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "loginAsCarrierTest" })
	public void verifyCarrierPayMeNowNextDayACHTest() throws InterruptedException, AWTException {

		// brokernotificationsobj.clickPayMeNowPayment();
		carriernextdayachobj.clickPaymenow();
		carriernextdayachobj.clickSelectButton();
		carriernextdayachobj.clickConfirmButton();
		// carriernextdayachobj.clickPaidTab();
		Thread.sleep(2000);
		carrierloginobj.CarrierLogout();
	}

	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "verifyCarrierPayMeNowNextDayACHTest" })
	public void verifyBrokerNotificationsTest() throws InterruptedException {
		brokernotificationsobj.loginAsBroker(brokerUsername, brokerPassword);
		brokernotificationsobj.clickNotification();
		brokernotificationsobj.clickPaymeNowPaymentNotification();
	}

	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "verifyBrokerNotificationsTest" })
	public void verifyBrokerNotificationsDeleteTestt() throws InterruptedException {
		brokernotificationsobj.clickNotificationDeleteButton();
		Thread.sleep(2000);
	}
}
