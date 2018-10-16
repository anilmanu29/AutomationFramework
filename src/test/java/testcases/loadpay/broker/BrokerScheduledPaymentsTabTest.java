package testcases.loadpay.broker;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerScheduledPaymentsTab;
import util.TestUtil;

public class BrokerScheduledPaymentsTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	BrokerLoginPage bl;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForInvoice, searchforcompanyname, searchforLoadID, searchforAmount, searchforDate = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/

	public BrokerScheduledPaymentsTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new BrokerLoginPage();
		brokerschedulepaymentstab = new BrokerScheduledPaymentsTab();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerSchedPaymentTabSearchData")
	public void loadBrokerSearchData(String companyName, String pullDate, String searchAmount, String invoiceNumber,
			String loadID) throws InterruptedException {

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

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = { "loadBrokerSearchData" })
	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		// login as broker
		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		loginPage.Brokerlogin(brokerUsername, brokerPassword);
	}

	@Test(dependsOnMethods = { "loginAsBroker" })
	public void verifyBrokerScheduledPaymentsTabTest() throws InterruptedException {
		verifySchedulePaymentsTabElementsDisplayed();
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" })
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

	@Test(dependsOnMethods = { "verifyclickpaymenTypeLinksortTest" })
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

	@Test(dependsOnMethods = { "verifyclickAnticipatedPullDatesortTest" })
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

	@Test(dependsOnMethods = { "verifyclickPayToDatesortTest" })
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

	@Test(dependsOnMethods = { "verifyclickAmountSortTest" })
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

	@Test(dependsOnMethods = { "verifyclickCarrierSortTest" })
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

	@Test(dependsOnMethods = { "verifyclickinvoiceSortTest" })
	public void verifyclickLoadIDSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		// click Carrier change sort from ascending to descending
		// brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerschedulepaymentstab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}

	@Test(dependsOnMethods = { "verifyclickLoadIDSortTest" })
	public void verifyCompanyNameSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchforcompanyname);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforcompanyname),
				"Matching text [" + searchforcompanyname + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyCompanyNameSearchTest" })
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchforAmount);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());

	}

	/*
	 * @Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" }, priority
	 * = 12) public void verifyPayToDateSearchTest() throws InterruptedException {
	 * // TEST - SEARCH VERIFICATION
	 * brokerschedulepaymentstab.enterSearchText(brokerschedulepaymentstab.PayToDate
	 * .getText()); brokerschedulepaymentstab.clickSearchButton(); // click first
	 * row to expand brokerschedulepaymentstab.clickFirstRow(); // get the data
	 * elements from the first row displayed firstRowData =
	 * brokerschedulepaymentstab.getFirstRowData();
	 * Assert.assertTrue(firstRowData.get(0).contains(searchForText),
	 * "Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");
	 * 
	 * }
	 */

	@Test(dependsOnMethods = { "verifyAmountSearchTest" })
	public void verifyAnticipatedPullDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchforDate);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforDate),
				"Matching text [" + searchforDate + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyAnticipatedPullDateSearchTest" })
	public void verifyinvoiceNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchForInvoice);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForInvoice),
				"Matching text [" + searchForInvoice + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyinvoiceNumSearchTest" })
	public void verifyLoadIDNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchforLoadID);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforLoadID),
				"Matching text [" + searchforLoadID + "] NOT found in [" + firstRowData + "]");

	}

	public void verifySchedulePaymentsTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerschedulepaymentstab.click_paymenType.isDisplayed(), "paymenType Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_pulldate.isDisplayed(), "pulldate Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_PayToDate.isDisplayed(),
				"PayToDate from Bank data Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_Amount.isDisplayed(), "Amount Column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_Carrier.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(brokerschedulepaymentstab.click_InvoiceID.isDisplayed(), "InvoiceID Pulled column not found");
		Assert.assertTrue(brokerschedulepaymentstab.Click_LoadID.isDisplayed(), "LoadID column not found");
		/*
		 * Assert.assertTrue(brokerschedulepaymentstab.expandCollapseFirstRow.
		 * isDisplayed(), "expandCollapseFirstRow column not found");
		 */
		Assert.assertNotNull(brokerschedulepaymentstab.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(brokerschedulepaymentstab.searchButton, "Search Button not found");

	}
}
