package testcases.loadpay.broker;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerEditPaymentMatchedCarrier;

public class BrokerEditPaymentMatchedCarrierTest extends TestBase 
{
	BrokerEditPaymentMatchedCarrier brokerEditPaymentMatchCarrierObj;
	
	/*-------Initializing driver---------*/
	public BrokerEditPaymentMatchedCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		brokerEditPaymentMatchCarrierObj = new BrokerEditPaymentMatchedCarrier();
		brokerEditPaymentMatchCarrierObj.setUp();
	}
	
	
	/*-------Login to Load Pay as Broker---------*/
	@Test(description="LP-5393 BrokerEditPayment_MatchedCarrier_Login", dataProvider="getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd)
	{
		brokerEditPaymentMatchCarrierObj.loginAsBroker(un, pwd);
		System.out.println("loginAsBrokerTest - Passed");
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description="LP-5393 BrokerEditPayment_MatchedCarrier_CreateNewPayment", dependsOnMethods =  {"loginAsBrokerTest"}, dataProvider="getPaymentData")
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA) throws InterruptedException {
		
		brokerEditPaymentMatchCarrierObj.brokerCreateNewPayment(cE, iN, lId, pA);
		System.out.println("brokerCreateNewPaymentTest - Passed");
	}
	
	@Test(description="LP-5393 BrokerEditPayment_MatchedCarrier_VerifyEditableFieldsEnabled", dependsOnMethods =  {"brokerCreateNewPaymentTest"})
	public void verifyEditableFieldsEnabledTest() throws InterruptedException
	{
		brokerEditPaymentMatchCarrierObj.verifyEditableFieldsEnabled();
		System.out.println("verifyEditableFieldsEnabledTest - Passed");
	}
	
	@Test(description="LP-5393 BrokerEditPayment_MatchedCarrier_UpdatePaymentDetails", dependsOnMethods =  {"verifyEditableFieldsEnabledTest"}, dataProvider="getUpdatedPaymentData")
	public void updatePaymentDetailsTest(
		String updatedCarrierEmail,
		String updatedPayTo,
		String updatedCarrierDOT,
		String updatedScheduleDate,
		String updatedPaymentAmount,
		String updatedInvoiceNumber,
		String updatedLoadID,
		String updatedInvoiceRecd,
		String updatedMemo,
		String updatedAdvancedPayment,
		String updatedOriginCountry,
		String updatedOriginState,
		String updatedOriginCity,
		String updatedOriginZIP,
		String updatedDestinationCountry,
		String updatedDestinationState,
		String updatedDestinationCity,
		String updatedDestinationZIP,
		String updatedTrailerType,
		String updatedMiles,
		String updatedPickupDate,
		String updatedDeliveryDate,
		String updatedCommodity,
		String updatedLength,
		String updatedWidth,
		String updatedHeight,
		String updatedWeight,
		String updatedNumOfStops,
		String updatedFuelSurcharge) throws InterruptedException
	{
		brokerEditPaymentMatchCarrierObj.updatePaymentDetails(
				updatedCarrierEmail, updatedPayTo, updatedCarrierDOT, updatedScheduleDate, updatedPaymentAmount, updatedInvoiceNumber, updatedLoadID, 
				updatedInvoiceRecd, updatedMemo, updatedAdvancedPayment, updatedOriginCountry, updatedOriginState, updatedOriginCity, updatedOriginZIP, 
				updatedDestinationCountry, updatedDestinationState, updatedDestinationCity, updatedDestinationZIP, updatedTrailerType, updatedMiles, updatedPickupDate, 
				updatedDeliveryDate, updatedCommodity, updatedLength, updatedWidth, updatedHeight, updatedWeight, updatedNumOfStops, updatedFuelSurcharge);
		
		System.out.println("updatePaymentDetailsTest - Passed");
	}
}
