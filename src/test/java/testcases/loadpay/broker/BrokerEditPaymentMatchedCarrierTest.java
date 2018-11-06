package testcases.loadpay.broker;

import java.time.LocalDate;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerEditPaymentMatchedCarrier;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerEditPaymentMatchedCarrierTest extends TestBase {
	BrokerEditPaymentMatchedCarrier brokerEditPaymentMatchCarrierObj;
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public BrokerEditPaymentMatchedCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		TestUtil.className = this.getClass().getName();
		wait = new WebDriverWait(driver, 30);
		brokerEditPaymentMatchCarrierObj = new BrokerEditPaymentMatchedCarrier();
		brokerEditPaymentMatchCarrierObj.setUp();
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-5393 BrokerEditPayment_MatchedCarrier_Login", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerEditPaymentMatchCarrierObj.loginAsBroker(brokerUsername, brokerPassword);
		log.info("loginAsBrokerTest - Passed");
	}

	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description = "LP-5393 BrokerEditPayment_MatchedCarrier_CreateNewPayment", dependsOnMethods = {
			"loginAsBrokerTest" }, dataProvider = "getPaymentData")
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA) throws InterruptedException {

		brokerEditPaymentMatchCarrierObj.brokerCreateNewPayment(cE, iN, lId, pA);
		log.info("brokerCreateNewPaymentTest - Passed");
	}

	@Test(description = "LP-5393 BrokerEditPayment_MatchedCarrier_VerifyEditableFieldsEnabled", dependsOnMethods = {
			"brokerCreateNewPaymentTest" })
	public void verifyEditableFieldsEnabledTest() throws InterruptedException {
		brokerEditPaymentMatchCarrierObj.verifyEditableFieldsEnabled();
		log.info("verifyEditableFieldsEnabledTest - Passed");
	}

	@Test(description = "LP-5393 BrokerEditPayment_MatchedCarrier_UpdatePaymentDetails", dependsOnMethods = {
			"verifyEditableFieldsEnabledTest" }, dataProvider = "getUpdatedPaymentData")
	public void updatePaymentDetailsTest(String updatedCarrierEmail, String updatedPayTo, String updatedCarrierDOT,
			String updatedScheduleDate, String updatedPaymentAmount, String updatedInvoiceNumber, String updatedLoadID,
			String updatedInvoiceRecd, String updatedMemo, String updatedAdvancedPayment, String updatedOriginCountry,
			String updatedOriginState, String updatedOriginCity, String updatedOriginZIP,
			String updatedDestinationCountry, String updatedDestinationState, String updatedDestinationCity,
			String updatedDestinationZIP, String updatedTrailerType, String updatedMiles, String updatedPickupDate,
			String updatedDeliveryDate, String updatedCommodity, String updatedLength, String updatedWidth,
			String updatedHeight, String updatedWeight, String updatedNumOfStops, String updatedFuelSurcharge)
			throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			updatedCarrierEmail = CarrierRegisterTest.carrierUsername;

			LocalDate today = LocalDate.now();
			Integer month = today.getMonthValue() < 12 ? (today.getMonthValue() + 1) : (today.getMonthValue() - 11);
			Integer pickupMonth = today.getMonthValue() < 11 ? (today.getMonthValue() + 2)
					: (today.getMonthValue() + 1);
			Integer deliveryMonth = today.getMonthValue() < 10 ? (today.getMonthValue() + 3)
					: (today.getMonthValue() + 2);

			Integer day = TestUtil.getRandomNumber(1, 28);
			String strDate = month.toString() + "/" + day.toString() + "/" + today.getYear();
			String pickupDate = pickupMonth.toString() + "/" + day.toString() + "/" + today.getYear();
			String deliveryDate = deliveryMonth.toString() + "/" + day.toString() + "/" + today.getYear();

			updatedScheduleDate = strDate;
			updatedInvoiceNumber = "UIN" + TestUtil.getCurrentDateTime();
			updatedLoadID = updatedInvoiceNumber;
			updatedInvoiceRecd = strDate;
			updatedPickupDate = pickupDate;
			updatedDeliveryDate = deliveryDate;

		}

		brokerEditPaymentMatchCarrierObj.updatePaymentDetails(updatedCarrierEmail, updatedPayTo, updatedCarrierDOT,
				updatedScheduleDate, updatedPaymentAmount, updatedInvoiceNumber, updatedLoadID, updatedInvoiceRecd,
				updatedMemo, updatedAdvancedPayment, updatedOriginCountry, updatedOriginState, updatedOriginCity,
				updatedOriginZIP, updatedDestinationCountry, updatedDestinationState, updatedDestinationCity,
				updatedDestinationZIP, updatedTrailerType, updatedMiles, updatedPickupDate, updatedDeliveryDate,
				updatedCommodity, updatedLength, updatedWidth, updatedHeight, updatedWeight, updatedNumOfStops,
				updatedFuelSurcharge);

	}
}
