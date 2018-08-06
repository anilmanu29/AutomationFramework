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

public class BrokerNotificationsTest extends TestBase {
	String brokeremailid = "";
	BrokerNotifications brokernotificationsobj;
	CarrierLoginPage carrierloginobj;
	CarrierNextDAYACH carriernextdayachobj;
	BrokerLoginPage brokerloginobj;

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
		brokeremailid = un;
		brokernotificationsobj.loginAsBroker(un, pwd);
		System.out.println("loginAsBrokerTest - Passed");
	}

	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = {
			"loginAsBrokerTest" }, dataProvider = "getBrokerNewPaymentData")
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA) throws InterruptedException {

		brokernotificationsobj.brokerCreateNewPayment(cE, iN, lId, pA);
		System.out.println("brokerCreateNewPaymentTest - Passed");
	}

	/*-------Login to Carrier ---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = {
			"brokerCreateNewPaymentTest" }, dataProvider = "getCarrierLoginData")
	public void loginAsCarrierTest(String username, String password) throws InterruptedException, AWTException {
		brokerloginobj.BrokerLogout();
		carrierloginobj.Carrierlogin(username, password);
	}

	/*-------Verify Carrier Next Day ACH---------*/
	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "loginAsCarrierTest" })
	public void verifyCarrierPayMeNowNextDayACHTest() throws InterruptedException, AWTException {
		brokernotificationsobj.clickPayMeNowPayment();
		carriernextdayachobj.clickSelectButton();
		carriernextdayachobj.clickConfirmButton();
		carriernextdayachobj.clickPaidTab();
		carrierloginobj.CarrierLogout();
	}

	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = {
			"verifyCarrierPayMeNowNextDayACHTest" }, dataProvider = "getBrokerLoginData")
	public void verifyBrokerNotificationsTest(String username, String password) throws InterruptedException {
		brokernotificationsobj.loginAsBroker(username, password);
		driver.navigate().refresh();
		brokernotificationsobj.clickNotification();
		brokernotificationsobj.clickPaymeNowPaymentNotification();
	}

	@Test(description = "LP-6361 Broker Notifications", dependsOnMethods = { "verifyBrokerNotificationsTest" })
	public void verifyBrokerNotificationsDeleteTestt() throws InterruptedException {
		brokernotificationsobj.clickNotificationDeleteButton();
		Thread.sleep(2000);
	}
}
