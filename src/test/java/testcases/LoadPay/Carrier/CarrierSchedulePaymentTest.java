package testcases.LoadPay.Carrier;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerNewPayment;
import pages.LoadPay.Carrier.CarrierLoginPage;
import pages.LoadPay.Carrier.CarrierPayMeNowTab;
import pages.LoadPay.Carrier.CarrierSchedulePayment;

public class CarrierSchedulePaymentTest extends TestBase{

	CarrierPayMeNowTab cp;
	CarrierLoginPage loginPage;
	CarrierSchedulePayment carrierschedulepayment;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchStatusText = "";
	String searchAmountText = "";
	String searchPayerText = "";
	String searchLoadIdText = "";
	

	/*-------Initializing driver---------*/
	
	public CarrierSchedulePaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage= new CarrierLoginPage();
		carrierschedulepayment= new CarrierSchedulePayment();
	
		
	}
	
	
	@Test(description="LP-3475 CarrierSchedulePaymentTabTest_Login",dataProvider="getCarrierSchedulePaymentTabData", priority=1)
	public void loginCarrier(String carrierEmail, String carrierPassword, String amountText, String payerText, String loadIdText, String InvoiceNumberText) throws InterruptedException
	{
		loginPage=new CarrierLoginPage();
		loginPage.Carrierlogin(carrierEmail, carrierPassword);
		searchAmountText = amountText;
		searchPayerText = payerText;
		searchLoadIdText = loadIdText;
		
			
	}
	
	@Test( priority=2)
	public void verifyScheduledPaymentsTabTest() throws InterruptedException
	{
		//click Paid Tab
		carrierschedulepayment.clickScheduledPaymentsTab();
		
		//Assert search bar, search button, and table columns are displayed
		//verifySchedulePaymentTabElementsDisplayed();
	}
	
	@Test( priority=3)
	public void verifyDaysSortTest() throws InterruptedException
	{
		//TEST - STATUS SORT
		//click Status Column to change sort from default to ascending
		carrierschedulepayment.clickDaysColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click Status Column to change sort from ascending to descending
		carrierschedulepayment.clickDaysColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare sorted data sets
		//TODO
		//Uncomment Assertion when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by days!");
	}	
	
	@Test(priority=4)
	public void verifyAmountSortTest() throws InterruptedException
	{
		//TEST - AMOUNT SORT
		//click Amount Column to change sort from default to ascending
		carrierschedulepayment.clickAmountColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click Amount Column to change sort from ascending to descending
		carrierschedulepayment.clickAmountColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Amount!");
	}
	
	@Test( priority=5)
	public void verifyPayerSortTest() throws InterruptedException
	{
		//TEST - PAYER SORT
		//click Payer Column to change sort from default to ascending
		carrierschedulepayment.clickPayerColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click Payer Column to change sort from ascending to descending
		carrierschedulepayment.clickPayerColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Payer!");
	}
	
	@Test(priority=6)
	public void verifyLoadIdSortTest() throws InterruptedException
	{
		//TEST - LoadID SORT
		//click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickLoadIDColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickLoadIDColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by LoadID!");
	}
	
	@Test(priority=7)
	public void verifyInvoiceSortTest() throws InterruptedException
	{
		//TEST - LoadID SORT
		//click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickInvoiceColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickInvoiceColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by InvoiceNumber!");
	}
	
	@Test(priority=8)
	public void verifyschdateSortTest() throws InterruptedException
	{
		//TEST - LoadID SORT
		//click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickschdateColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		//click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickschdateColumn();
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by ScheduleDate!");
	}
	
	
	
	//TODO
	//Enable when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
	
	
	@Test(priority=9)
	public void verifySearchAmountTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchAmountText);
		carrierschedulepayment.clickSearchButton();		
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchAmountText), "Matching text [" + searchAmountText + "] NOT found in [" + firstRowData + "]");
	}
	
	@Test(priority=10)
	public void verifySearchPayerTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchPayerText);
		carrierschedulepayment.clickSearchButton();		
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchPayerText), "Matching text [" + searchPayerText + "] NOT found in [" + firstRowData + "]");
	}
	
	@Test(priority=11)
	public void verifySearchLoadIdTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchLoadIdText);
		carrierschedulepayment.clickSearchButton();		
		//click first row to expand
		carrierschedulepayment.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchLoadIdText), "Matching text [" + searchLoadIdText + "] NOT found in [" + firstRowData + "]");
	}
	
	
	
	public void verifySchedulePaymentTabElementsDisplayed(){

		//Verify that the web elements for the Paid tab exist

		Assert.assertTrue(carrierschedulepayment.amountColumn.getText().contains("Amount"), "Amount Column not found");
		Assert.assertTrue(carrierschedulepayment.payerColumn.getText().contains("Payer"), "Payer Column not found");
		Assert.assertTrue(carrierschedulepayment.loadIDColumn.getText().contains("Load ID"), "Load ID not found");
		Assert.assertTrue(carrierschedulepayment.InvoiceColumn.getText().contains("Invoice #"), "Invoice # not found");
		Assert.assertTrue(carrierschedulepayment.schdateColumn.getText().contains("Schedule Date"), "Schedule Date not found");
		Assert.assertTrue(carrierschedulepayment.searchButton.isDisplayed(), "Search Button not found");
		Assert.assertTrue(carrierschedulepayment.searchInputField.isDisplayed(), "Search Input Field not found");
	}
}
