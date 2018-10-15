package testcases.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import testcases.loadpay.carrier.CarrierRegisterCanadaTest;
import util.TestUtil;

public class ShipperSchedulePaymentTest extends TestBase {

	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String payment_status = "Verified";
	static String invoice;
	String brokerUsername, brokerPassword = "";
	String carrierUsername = "";

	/*-------Initializing driver---------*/
	public ShipperSchedulePaymentTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
	}

	@AfterClass
	public void tearDown() {
		bl.isCanadaTest(false);
	}

	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterCanadaTest.brokerUsername;
			brokerPassword = BrokerRegisterCanadaTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		bl = new BrokerLoginPage();
		bl.Brokerlogin(brokerUsername, brokerPassword);
		bl.isCanadaTest(true);
		// bl.completeRegistration();

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		bp = new BrokerNewPayment();
		bp.newPayment();

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterCanadaTest.carrierUsername;
			invoiceno = "NP" + TestUtil.getCurrentDateTime();
			loadid = invoiceno;
		} else {
			carrierUsername = cemail;
		}

		bp.carrierEmail(carrierUsername);

		bp.amount(amt);

		invoice = bp.invoiceNumber(invoiceno);

		bp.loadId(loadid);

		// bp.advanceCheckbox();
		//
		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		bp.searchCarrier(carrierUsername);

		bp.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		bp.verifyInvoiceNumber(invoiceno, amt);

		Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		// bp.logout();
	}

}
