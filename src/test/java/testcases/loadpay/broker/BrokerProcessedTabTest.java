package testcases.loadpay.broker;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerProcessedTab;
import util.TestUtil;

public class BrokerProcessedTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerProcessedTab brokerProcessedTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	JavascriptExecutor jse;
	String searchForInvoice, searchforcompanyname, searchforLoadID, searchforAmount, searchforDate  = "";

	public BrokerProcessedTabTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new BrokerLoginPage();
		brokerProcessedTab = new BrokerProcessedTab();
		jse = (JavascriptExecutor) driver;
	}

	@Test(dataProvider = "getBrokerProcessedTabSearchData")
	public void loadBrokerSearchData(String companyName, String pullDate, String searchAmount, String invoiceNumber, String loadID) throws InterruptedException {
	
		searchForInvoice = invoiceNumber;
		searchForInvoice = TestUtil.removeDecimalZeroFormat(searchForInvoice);
		searchforcompanyname = companyName;
		searchforLoadID = loadID;
		searchforLoadID = TestUtil.removeDecimalZeroFormat(searchforLoadID);
		searchforAmount = searchAmount;
		searchforAmount = TestUtil.removeDecimalZeroFormat(searchforAmount);
		searchforDate = pullDate;
		searchforDate = searchforDate.replace("'", "");
	}
	
	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = {"loadBrokerSearchData"})
	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		// login as broker
		loginPage.Brokerlogin(un, pwd);
	}

	@Test(dependsOnMethods = { "loginAsBroker" })
	public void verifyProcessedTabTest() throws InterruptedException {

		brokerProcessedTab.clickProcessedTab();
		verifyProcessTabElementsDisplayed();
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" })
	public void verifyPaySelectionSortTest() throws InterruptedException {
		// TEST - Pay Selection SORT
		// click pay selection Column to change sort from default to ascending
		brokerProcessedTab.clickPaySelectionColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click pay selection Column to change sort from ascending to descending
		brokerProcessedTab.clickPaySelectionColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
	}

	@Test(dependsOnMethods = { "verifyPaySelectionSortTest" })
	public void verifyDiscountSortTest() throws InterruptedException {
		// TEST - Discount SORT
		// click Discount Column to change sort from default to ascending
		brokerProcessedTab.clickDiscountColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Discount Column to change sort from ascending to descending
		brokerProcessedTab.clickDiscountColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Discount!");
	}

	@Test(dependsOnMethods = { "verifyDiscountSortTest" })
	public void verifyPulledFromBankDateSortTest() throws InterruptedException {
		// TEST - Pulled from Bank Date
		// click Pulled from Bank Date to change sort from default to ascending
		brokerProcessedTab.clickPulledfromBankdataColumnColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerProcessedTab.clickPulledfromBankdataColumnColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by Pulled from Bank Date!");
	}

	@Test(dependsOnMethods = { "verifyPulledFromBankDateSortTest" })
	public void verifyPaidToCarrierSortTest() throws InterruptedException {
		// TEST - Paid to Carrier
		// click Paid to Carrier to change sort from default to ascending
		brokerProcessedTab.clickPaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerProcessedTab.clickPaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Paid to Carrier!");
	}

	@Test(dependsOnMethods = { "verifyPaidToCarrierSortTest" })
	public void verifyAmountPulledSortTest() throws InterruptedException {
		// TEST - Amount Pulled
		// click Amount Pulled to change sort from default to ascending
		brokerProcessedTab.clickAmountPulledColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Amount Pulled to change sort from ascending to descending
		brokerProcessedTab.clickAmountPulledColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyAmountPulledSortTest" })
	public void verifyAmountPaidToCarrierSortTest() throws InterruptedException {
		// TEST - Amount Paid To Carrier
		// click Amount Paid ToCarrier sort from default to ascending
		brokerProcessedTab.clickAmountpaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Amount Paid To Carrier change sort from ascending to descending
		brokerProcessedTab.clickAmountpaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyAmountPaidToCarrierSortTest" })
	public void verifyCarrierSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		brokerProcessedTab.clickCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Carrier change sort from ascending to descending
		brokerProcessedTab.clickCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}

	@Test(dependsOnMethods = { "verifyCarrierSortTest" })
	public void verifyLoadIDSortTest() throws InterruptedException {
		// TEST - LoadID
		// click LoadID sort from default to ascending
		brokerProcessedTab.clickLoadIDColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		
		// get the data elements from the first row displayed, sorted in reverse
		// click LoadID sort from default to ascending
		brokerProcessedTab.clickLoadIDColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		
		// compare to the database when sorted by given column-Descending
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  LoadID!");
	}

	@Test(dependsOnMethods = { "verifyLoadIDSortTest" })
	public void verifyCompanySearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerProcessedTab.enterSearchText(searchforcompanyname);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforcompanyname),
				"Matching text [" + searchforcompanyname + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyCompanySearchTest" })
	public void verifyInvoiceSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchForInvoice);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForInvoice),
				"Matching text [" + searchForInvoice + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyInvoiceSearchTest" })
	public void verifyLoadIDSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchforLoadID);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforLoadID),
				"Matching text [" + searchforLoadID + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyLoadIDSearchTest" })
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerProcessedTab.enterSearchText(searchforAmount);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());

	}
	
	@Test(dependsOnMethods = { "verifyAmountSearchTest" })
	public void verifyDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchforDate);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());
	}
	


	public void verifyProcessTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerProcessedTab.paySelectionColumn.isDisplayed(), "PaySelection Column not found");
		Assert.assertTrue(brokerProcessedTab.discountcolumn.isDisplayed(), "Discount Column not found");
		Assert.assertTrue(brokerProcessedTab.pulledfrombankdataColumn.isDisplayed(),
				"Pulled from Bank data Column not found");
		Assert.assertTrue(brokerProcessedTab.paidtocarrierDColumn.isDisplayed(), "Paid to Carrier Column not found");
		Assert.assertTrue(brokerProcessedTab.amountpulledColumn.isDisplayed(), "Amount Pulled column not found");
		Assert.assertTrue(brokerProcessedTab.amountpulledColumn.isDisplayed(), "Amount Pulled column not found");
		Assert.assertTrue(brokerProcessedTab.amountpaidtocarrierColumn.isDisplayed(),
				"Amount Paid to carrier column not found");
		Assert.assertTrue(brokerProcessedTab.carriercolumn.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(brokerProcessedTab.loadidColumn.isDisplayed(), "LoadId column not found");
		Assert.assertNotNull(brokerProcessedTab.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(brokerProcessedTab.searchButton, "Search Button not found");

	}

}
