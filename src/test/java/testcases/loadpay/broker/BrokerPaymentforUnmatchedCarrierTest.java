package testcases.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;

public class BrokerPaymentforUnmatchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier bp;
	BrokerLoginPage bl;
	String payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static ArrayList<String> in;
	public static String invoiceNum;

	/*-------Initializing driver---------*/
	public BrokerPaymentforUnmatchedCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new BrokerPaymentforUnmatchedCarrier();
		al = new ArrayList<String>();
		in = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		bl = new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt, String payto, String ein)
			throws InterruptedException {

		bp.newPayment();

		bp.carrierEmail(cemail);

		bp.amount(amt);

		invoiceNum = bp.invoiceNumber(invoiceno);
		in.add(invoiceNum);

		bp.loadId(loadid);

		bp.companyName(payto);

		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		umemail = bp.searchCarrier(cemail);
		al.add(umemail);

		bp.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		bp.verifyInvoiceNumber(invoiceno, amt);

		einno = bp.getEin(ein);
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());

		// bp.logout();
	}

}
