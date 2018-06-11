package testcases;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerLoginPage;
import pages.BrokerScheduledPaymentsTab;

public class BrokerScheduledPaymentsTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	BrokerLoginPage bl;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForText = "";
	
	/*-------Initializing driver---------*/
	
	public BrokerScheduledPaymentsTabTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage= new BrokerLoginPage();
		brokerschedulepaymentstab = new BrokerScheduledPaymentsTab();	
		
	}
	
	@Test(dataProvider = "getBrokerLoginData", priority = 1)
	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		// login as broker
		loginPage.Brokerlogin(un, pwd);
	}

	@Test(dependsOnMethods = { "loginAsBroker" }, priority = 2)
	public void verifyBrokerScheduledPaymentsTabTest() throws InterruptedException {
		verifySchedulePaymentsTabElementsDisplayed();
	}
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 3)
	public void verifyclickpaymenTypeLinksortTest() throws InterruptedException {
		// TEST - Pay Selection SORT
		// click pay selection Column to change sort from default to ascending
		brokerschedulepaymentstab.clickpaymenTypeLink();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click pay selection Column to change sort from ascending to descending
		brokerschedulepaymentstab.clickpaymenTypeLink();
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 4)
	public void verifyclickAnticipatedPullDatesortTest() throws InterruptedException {
		// TEST - Discount SORT
		// click Discount Column to change sort from default to ascending
		brokerschedulepaymentstab.clickAnticipatedPullDate();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Discount Column to change sort from ascending to descending
		brokerschedulepaymentstab.clickAnticipatedPullDate();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Discount!");
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 5)
	public void verifyclickPayToDatesortTest() throws InterruptedException {
		// TEST - Pulled from Bank Date
		// click Pulled from Bank Date to change sort from default to ascending
		brokerschedulepaymentstab.clickPayToDate();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerschedulepaymentstab.clickPayToDate();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by Pulled from Bank Date!");
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 6)
	public void verifyclickAmountSortTest() throws InterruptedException {
		// TEST - Paid to Carrier
		// click Paid to Carrier to change sort from default to ascending
		brokerschedulepaymentstab.clickAmount();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerschedulepaymentstab.clickAmount();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Paid to Carrier!");
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 7)
	public void verifyclickCarrierSortTest() throws InterruptedException {
		// TEST - Amount Pulled
		// click Amount Pulled to change sort from default to ascending
		brokerschedulepaymentstab.clickCarrier();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Amount Pulled to change sort from ascending to descending
		brokerschedulepaymentstab.clickCarrier();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 8)
	public void verifyclickinvoiceSortTest() throws InterruptedException {
		// TEST - Amount Paid To Carrier
		// click Amount Paid ToCarrier sort from default to ascending
		brokerschedulepaymentstab.clickinvoice();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Amount Paid To Carrier change sort from ascending to descending
		brokerschedulepaymentstab.clickinvoice();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 9)
	public void verifyclickLoadIDSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Carrier change sort from ascending to descending
		//brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 10)
	public void verifyCompanyNameSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.CompanyName.getText());
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 11)
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.Amount.getText());
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	/*@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 12)
	public void verifyPayToDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.PayToDate.getText());
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}*/
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 13)
	public void verifyAnticipatedPullDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.AnticipatedPullDate.getAttribute("innerText"));
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 14)
	public void verifyinvoiceNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.invoiceNum.getText());
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority = 15)
	public void verifyLoadIDNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.LoadIDNum.getText());
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	

	
	/*@Test(priority=21)
	public void BrokerScheduledPaymentsTab() throws InterruptedException
	{
		bsp.clickpaymenTypeLink();
		Thread.sleep(1000);
		bsp.clickAnticipatedPullDate();
		Thread.sleep(1000);
		bsp.clickPayToDate();
		Thread.sleep(1000);
		bsp.clickAmount();
		Thread.sleep(1000);
		bsp.clickCarrier();
		Thread.sleep(1000);
		bsp.clickinvoice();
		Thread.sleep(1000);
		bsp.clickLoadID();
		Thread.sleep(2000);
	}*/

	public void verifySchedulePaymentsTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerschedulepaymentstab.click_paymenType.isDisplayed() , "paymenType Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_pulldate.isDisplayed(), "pulldate Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_PayToDate.isDisplayed(),
				"PayToDate from Bank data Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_Amount.isDisplayed(), "Amount Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_Carrier.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_InvoiceID.isDisplayed(), "InvoiceID Pulled column not found");
		Assert.assertTrue(brokerschedulepaymentstab.Click_LoadID.isDisplayed(),
				"LoadID column not found");
		/*Assert.assertTrue(brokerschedulepaymentstab.expandCollapseFirstRow.isDisplayed(), "expandCollapseFirstRow column not found");*/
		Assert.assertNotNull(brokerschedulepaymentstab.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(brokerschedulepaymentstab.searchButton, "Search Button not found");

	}
}
