package testcases.loadpay.broker;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.ShipperAdvancePayment;
import pages.loadpay.carrier.CarrierLoginPage;

public class ShipperAdvancePaymentTest extends TestBase {

	ShipperAdvancePayment bp;
	BrokerLoginPage bl;
	CarrierLoginPage loginPage;
	String payment_status = "Verified";
	String paymentdate;
	static String invoice;
	String loadidd;

	/*-------Initializing driver---------*/
	public ShipperAdvancePaymentTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new ShipperAdvancePayment();
		loginPage = new CarrierLoginPage();
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
			throws InterruptedException, InvalidFormatException, IOException {

		bp.newPayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.carrierEmail(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.amount(amt);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.invoiceNumber(invoiceno);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		loadidd = bp.loadId(loadid);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.advanceCheckbox();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		paymentdate = bp.getPaymentDate();
		log.info(paymentdate);
		bp.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickShedulePaymenttab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.searchCarrier(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("window.scrollBy(0,250)", "");
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 */
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		// log.info(bp.verifyPaymentStatus());
		bp.logout();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokernewPayment")
	public void loginTest(String user, String pass) throws InterruptedException {

		loginPage.Carrierlogin(user, pass);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.verifyScheduledDate(paymentdate, loadidd);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

}
