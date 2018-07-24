package testcases.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;

public class BrokerNewPaymentTest extends TestBase {

	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String payment_status = "Verified";
	static String invoice;
	public static ArrayList<String> al;
	public static String email;

	/*-------Initializing driver---------*/
	public BrokerNewPaymentTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new BrokerNewPayment();
		al = new ArrayList<String>();
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

		email = bp.carrierEmail(cemail);

		bp.amount(amt);

		invoice = bp.invoiceNumber(invoiceno);
		al.add(invoice);

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

		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		// bp.logout();
	}

}
