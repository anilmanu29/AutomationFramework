package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import base.TestBase;

public class BrokerEditPaymentUnmatchedCarrier extends TestBase 
{
	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	String paymentStatus = "Unmatched";
	String carrierEmail = "";
	String invoiceNum = "";
	String loadId = "";
	String paymentAmount = "";
	String payto = "";
	String ein = "";
	
	/*-------Initializing driver---------*/
	public BrokerEditPaymentUnmatchedCarrier()
	{
		super();
	}
	
	public void setUp()
	{
		initialization();
		brokerLoginObj = new BrokerLoginPage();	
		brokerPaymentObj = new BrokerNewPayment();
	}
	
	public void loginAsBroker(String un, String pwd)
	{
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);		
	}
	
	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA, String pT, String Ein) throws InterruptedException {
		
		//Store data-provider elements into publicly-accessible strings
		carrierEmail = cE;
		invoiceNum = iN;
		loadId = lId;
		paymentAmount = pA;
		payto = pT;
		ein = Ein;
		
		//create new payment
		brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();
		Thread.sleep(1000);
		brokerPaymentObj.carrierEmail(carrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.setField_PayTo(payto);
		Thread.sleep(1000);
		brokerPaymentObj.amount(paymentAmount);
		Thread.sleep(1000);
		brokerPaymentObj.loadId(loadId);
		Thread.sleep(1000);
		brokerPaymentObj.invoiceNumber(invoiceNum);
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePayment();
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePaymenttab();
		Thread.sleep(1000);
		brokerPaymentObj.searchInvoice(invoiceNum);
		Thread.sleep(1000);
		brokerPaymentObj.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		brokerPaymentObj.verifyInvoiceNumber(invoiceNum,paymentAmount);
		Thread.sleep(1000);
		
		//verify payment status
		Assert.assertTrue(brokerPaymentObj.verifyPaymentStatus().equals(paymentStatus), "Payment Status not equal!");
	}
	
	public void verifyEditableFieldsEnabled() throws InterruptedException
	{
		brokerPaymentObj.clickEditIcon();
		Thread.sleep(1000);
		
		//Verify all editable fields are enabled
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(brokerPaymentObj.getField_CarrierEmail().isEnabled(), "Carrier Email Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PayTo().isEnabled(), "Pay-To Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_CarrierDOT().isEnabled(), "Carrier DOT Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_ScheduleDate().isEnabled(), "Schedule Date Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PaymentAmount().isEnabled(), "Payment Amount Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceNum().isEnabled(), "Invoice # Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_LoadID().isEnabled(), "Load ID Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceRecd().isEnabled(), "Invoice Received Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Memo().isEnabled(), "Memo Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getCheckbox_AdvancePayment().isEnabled(), "Advance Payment Checkbox Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getDropdown_OriginCountry().isEnabled(), "Origin Country Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_OriginCity().isEnabled(), "Origin City Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getDropdown_OriginState().isEnabled(), "Origin State Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_OriginZip().isEnabled(), "Origin ZIP Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getDropdown_DestinationCountry().isEnabled(), "Destination Country Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DestinationCity().isEnabled(), "Destination City Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getDropdown_DestinationState().isEnabled(), "Destination State Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DestinationZip().isEnabled(), "Destination ZIP Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getDropdown_TrailerType().isEnabled(), "Trailer-tyoe Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Miles().isEnabled(), "Miles Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PickupDate().isEnabled(), "Pickup Date Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DeliveryDate().isEnabled(), "Delivery Date Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getField_Commodity().isEnabled(), "Commodity Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getField_Length().isEnabled(), "Length Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Width().isEnabled(), "Width Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Height().isEnabled(), "Height Field Disabled!");
		
		softAssert.assertTrue(brokerPaymentObj.getField_Weight().isEnabled(), "Weight Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_NumberOfStops().isEnabled(), "Number Of Stops Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_FuelSurcharge().isEnabled(), "Fuel Surcharge Field Disabled!");		
		
		softAssert.assertAll();	
	}
	
	public void updatePaymentDetails(
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
		brokerPaymentObj.setField_CarrierEmail(updatedCarrierEmail);
		Thread.sleep(1000);
		brokerPaymentObj.setField_PayTo(updatedPayTo);
		Thread.sleep(1000);
		brokerPaymentObj.setField_CarrierDOT(updatedCarrierDOT);
		Thread.sleep(1000);
		brokerPaymentObj.setField_InvoiceRecd(updatedInvoiceRecd);
		Thread.sleep(1000);
		brokerPaymentObj.setField_ScheduleDate(updatedScheduleDate);
		Thread.sleep(1000);
		brokerPaymentObj.setField_PaymentAmount(updatedPaymentAmount);
		Thread.sleep(1000);
		brokerPaymentObj.setField_InvoiceNum(updatedInvoiceNumber);
		Thread.sleep(1000);
		brokerPaymentObj.setField_LoadID(updatedLoadID);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Memo(updatedMemo);
		Thread.sleep(1000);
		brokerPaymentObj.setDropdown_OriginCountry(updatedOriginCountry);
		Thread.sleep(1000);
		brokerPaymentObj.setDropdown_OriginState(updatedOriginState);
		Thread.sleep(1000);
		brokerPaymentObj.setField_OriginCity(updatedOriginCity);
		Thread.sleep(1000);
		brokerPaymentObj.setField_OriginZip(updatedOriginZIP);
		Thread.sleep(1000);
		brokerPaymentObj.setDropdown_DestinationCountry(updatedDestinationCountry);
		Thread.sleep(1000);
		brokerPaymentObj.setDropdown_DestinationState(updatedDestinationState);
		Thread.sleep(1000);
		brokerPaymentObj.setField_DestinationCity(updatedDestinationCity);
		Thread.sleep(1000);
		brokerPaymentObj.setField_DestinationZip(updatedDestinationZIP);
		Thread.sleep(1000);
		brokerPaymentObj.setDropdown_TrailerType(updatedTrailerType);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Miles(updatedMiles);
		Thread.sleep(1000);
		brokerPaymentObj.setField_PickupDate(updatedPickupDate);
		Thread.sleep(1000);
		brokerPaymentObj.setField_DeliveryDate(updatedDeliveryDate);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Commodity(updatedCommodity);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Length(updatedLength);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Width(updatedWidth);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Height(updatedHeight);
		Thread.sleep(1000);
		brokerPaymentObj.setField_Weight(updatedWeight);
		Thread.sleep(1000);
		brokerPaymentObj.setField_NumberOfStops(updatedNumOfStops);
		Thread.sleep(1000);
		brokerPaymentObj.setField_FuelSurcharge(updatedFuelSurcharge);
		Thread.sleep(1000);
		brokerPaymentObj.clickShedulePayment();
		Thread.sleep(2000);
		brokerPaymentObj.searchInvoice(updatedInvoiceNumber);
		Assert.assertTrue(brokerPaymentObj.isEditIconEnabled(), "Edit icon not found for updated payment");
	}
}
