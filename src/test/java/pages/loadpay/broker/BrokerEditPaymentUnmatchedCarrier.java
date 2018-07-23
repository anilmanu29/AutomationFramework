package pages.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.carrierEmail(carrierEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_PayTo(payto);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.amount(paymentAmount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.loadId(loadId);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.invoiceNumber(invoiceNum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.clickShedulePaymenttab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.searchInvoice(invoiceNum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.clickSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.verifyInvoiceNumber(invoiceNum,paymentAmount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		
		//verify payment status
		Assert.assertTrue(brokerPaymentObj.verifyPaymentStatus().equals(paymentStatus), "Payment Status not equal!");
	}
	
	public void verifyEditableFieldsEnabled() throws InterruptedException
	{
		brokerPaymentObj.clickEditIcon();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_PayTo(updatedPayTo);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_CarrierDOT(updatedCarrierDOT);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_InvoiceRecd(updatedInvoiceRecd);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_ScheduleDate(updatedScheduleDate);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_PaymentAmount(updatedPaymentAmount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_InvoiceNum(updatedInvoiceNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_LoadID(updatedLoadID);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Memo(updatedMemo);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setDropdown_OriginCountry(updatedOriginCountry);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setDropdown_OriginState(updatedOriginState);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_OriginCity(updatedOriginCity);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_OriginZip(updatedOriginZIP);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setDropdown_DestinationCountry(updatedDestinationCountry);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setDropdown_DestinationState(updatedDestinationState);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_DestinationCity(updatedDestinationCity);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_DestinationZip(updatedDestinationZIP);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setDropdown_TrailerType(updatedTrailerType);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Miles(updatedMiles);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_PickupDate(updatedPickupDate);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_DeliveryDate(updatedDeliveryDate);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Commodity(updatedCommodity);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Length(updatedLength);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Width(updatedWidth);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Height(updatedHeight);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_Weight(updatedWeight);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_NumberOfStops(updatedNumOfStops);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.setField_FuelSurcharge(updatedFuelSurcharge);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		brokerPaymentObj.searchInvoice(updatedInvoiceNumber);
		Assert.assertTrue(brokerPaymentObj.isEditIconEnabled(), "Edit icon not found for updated payment");
	}
}
