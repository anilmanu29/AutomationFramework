package testcases.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;

public class ShipperSchedulePaymentTest extends TestBase {

	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String payment_status = "Verified";
	static String invoice;

	/*-------Initializing driver---------*/
	public ShipperSchedulePaymentTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new BrokerNewPayment();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		bl = new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		bp = new BrokerNewPayment();
		bp.newPayment();

		bp.carrierEmail(cemail);

		bp.amount(amt);

		invoice = bp.invoiceNumber(invoiceno);

		bp.loadId(loadid);

		// bp.advanceCheckbox();
		//
		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		bp.searchCarrier(cemail);

		bp.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		bp.verifyInvoiceNumber(invoiceno, amt);

		Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		// bp.logout();
	}

}
