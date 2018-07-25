package testcases.loadpay.broker;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerEditPaymentUnmatchedCarrier;

public class BrokerEditPaymentUnmatchedCarrierTest extends TestBase {

	BrokerEditPaymentUnmatchedCarrier brokerEditPaymentUnmatchedCarrierObj;

	/*-------Initializing driver---------*/
	public BrokerEditPaymentUnmatchedCarrierTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {
		brokerEditPaymentUnmatchedCarrierObj = new BrokerEditPaymentUnmatchedCarrier();
		brokerEditPaymentUnmatchedCarrierObj.setUp();
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-5392 BrokerEditPayment_UnmatchedCarrier_Login", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) {
		brokerEditPaymentUnmatchedCarrierObj.loginAsBroker(un, pwd);
		log.info("loginAsBrokerTest - Passed");
	}

	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description = "LP-5392 BrokerEditPayment_UnmatchedCarrier_CreateNewPayment", dependsOnMethods = {
			"loginAsBrokerTest" }, dataProvider = "getPaymentDataforUnmatchcarrier")
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA, String pT, String Ein)
			throws InterruptedException {

		brokerEditPaymentUnmatchedCarrierObj.brokerCreateNewPayment(cE, iN, lId, pA, pT, Ein);
		log.info("brokerCreateNewPaymentTest - Passed");
	}

	@Test(description = "LP-5392 BrokerEditPayment_UnmatchedCarrier_VerifyEditableFieldsEnabled", dependsOnMethods = {
			"brokerCreateNewPaymentTest" })
	public void verifyEditableFieldsEnabledTest() throws InterruptedException {
		brokerEditPaymentUnmatchedCarrierObj.verifyEditableFieldsEnabled();
		log.info("verifyEditableFieldsEnabledTest - Passed");
	}

	@Test(description = "LP-5392 BrokerEditPayment_UnmatchedCarrier_UpdatePaymentDetails", dependsOnMethods = {
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
		brokerEditPaymentUnmatchedCarrierObj.updatePaymentDetails(updatedCarrierEmail, updatedPayTo, updatedCarrierDOT,
				updatedScheduleDate, updatedPaymentAmount, updatedInvoiceNumber, updatedLoadID, updatedInvoiceRecd,
				updatedMemo, updatedAdvancedPayment, updatedOriginCountry, updatedOriginState, updatedOriginCity,
				updatedOriginZIP, updatedDestinationCountry, updatedDestinationState, updatedDestinationCity,
				updatedDestinationZIP, updatedTrailerType, updatedMiles, updatedPickupDate, updatedDeliveryDate,
				updatedCommodity, updatedLength, updatedWidth, updatedHeight, updatedWeight, updatedNumOfStops,
				updatedFuelSurcharge);

		log.info("updatePaymentDetailsTest - Passed");
	}
}
