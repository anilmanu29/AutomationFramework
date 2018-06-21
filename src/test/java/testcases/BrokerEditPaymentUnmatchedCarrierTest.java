package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BrokerEditPaymentUnmatchedCarrier;
import base.TestBase;

public class BrokerEditPaymentUnmatchedCarrierTest extends TestBase 
{

	BrokerEditPaymentUnmatchedCarrier brokerEditPaymentUnmatchedCarrierObj;
	
	/*-------Initializing driver---------*/
	public BrokerEditPaymentUnmatchedCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		brokerEditPaymentUnmatchedCarrierObj = new BrokerEditPaymentUnmatchedCarrier();
		brokerEditPaymentUnmatchedCarrierObj.setUp();
	}
	
	
	/*-------Login to Load Pay as Broker---------*/
	@Test(description="LP-5392 BrokerEditPayment_UnmatchedCarrier_Login", dataProvider="getBrokerLoginData", priority=109)
	public void loginAsBrokerTest(String un, String pwd)
	{
		brokerEditPaymentUnmatchedCarrierObj.loginAsBroker(un, pwd);
		System.out.println("loginAsBrokerTest - Passed");
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description="LP-5392 BrokerEditPayment_UnmatchedCarrier_CreateNewPayment", dependsOnMethods =  {"loginAsBrokerTest"}, dataProvider="getPaymentDataforUnmatchcarrier", priority=110)
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA, String pT, String Ein) throws InterruptedException {
		
		brokerEditPaymentUnmatchedCarrierObj.brokerCreateNewPayment(cE, iN, lId, pA, pT, Ein);
		System.out.println("brokerCreateNewPaymentTest - Passed");
	}
	
	@Test(description="LP-5392 BrokerEditPayment_UnmatchedCarrier_VerifyEditableFieldsEnabled", dependsOnMethods =  {"brokerCreateNewPaymentTest"}, priority=111)
	public void verifyEditableFieldsEnabledTest() throws InterruptedException
	{
		brokerEditPaymentUnmatchedCarrierObj.verifyEditableFieldsEnabled();
		System.out.println("verifyEditableFieldsEnabledTest - Passed");
	}
	
	@Test(description="LP-5392 BrokerEditPayment_UnmatchedCarrier_UpdatePaymentDetails", dependsOnMethods =  {"verifyEditableFieldsEnabledTest"}, dataProvider="getUpdatedPaymentData", priority=112)
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
		brokerEditPaymentUnmatchedCarrierObj.updatePaymentDetails(updatedCarrierEmail, updatedPayTo, updatedCarrierDOT, updatedScheduleDate, updatedPaymentAmount, 
				updatedInvoiceNumber, updatedLoadID, updatedInvoiceRecd, updatedMemo, updatedAdvancedPayment, updatedOriginCountry, updatedOriginState, 
				updatedOriginCity, updatedOriginZIP, updatedDestinationCountry, updatedDestinationState, updatedDestinationCity, updatedDestinationZIP, 
				updatedTrailerType, updatedMiles, updatedPickupDate, updatedDeliveryDate, updatedCommodity, updatedLength, updatedWidth, updatedHeight, 
				updatedWeight, updatedNumOfStops, updatedFuelSurcharge);
		
		System.out.println("updatePaymentDetailsTest - Passed");
	}
}
