package testcases.loadpay.broker;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.ShipperAdvancePayment;
import pages.loadpay.carrier.CarrierLoginPage;
import testcases.loadpay.carrier.CarrierRegisterCanadaTest;
import util.TestUtil;

public class ShipperAdvancePaymentTest extends TestBase {

	ShipperAdvancePayment bp;
	BrokerLoginPage bl;
	CarrierLoginPage loginPage;
	String payment_status = "Verified";
	String paymentdate;
	static String invoice;
	String loadidd;
	String brokerUsername, brokerPassword = "";
	String carrierUsername, carrierPassword = "";

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
		bl = new BrokerLoginPage();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterCanadaTest.brokerUsername;
			brokerPassword = BrokerRegisterCanadaTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		bl.Brokerlogin(brokerUsername, brokerPassword);
		bl.isCanadaTest(true);
	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException, InvalidFormatException, IOException {

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

		bp.invoiceNumber(invoiceno);

		loadidd = bp.loadId(loadid);

		bp.advanceCheckbox();

		paymentdate = bp.getPaymentDate();
		log.info(paymentdate);
		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		bp.searchCarrier(carrierUsername);

		bp.clickSearchButton();

	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokernewPayment")
	public void loginTest(String user, String pass) throws InterruptedException {

		bp.logout();

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterCanadaTest.carrierUsername;
			carrierPassword = CarrierRegisterCanadaTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

		bp.verifyScheduledDate(paymentdate, loadidd);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

	}

}
