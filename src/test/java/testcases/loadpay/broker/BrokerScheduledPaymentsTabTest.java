package testcases.loadpay.broker;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.broker.BrokerScheduledPaymentsTab;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerScheduledPaymentsTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	BrokerNewPayment newPaymentObj;
	BrokerLoginPage bl;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForInvoice, searchForCompanyName, searchForLoadID, searchForAmount, searchForDate, pullDate = "";
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
		newPaymentObj = new BrokerNewPayment();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerSchedPaymentTabSearchData")
	public void loadBrokerSearchData(String userName, String passWord, String companyName, String searchDate,
			String searchAmount, String invoiceNumber, String loadID) throws InterruptedException {

		searchForInvoice = invoiceNumber;
		searchForInvoice = TestUtil.removeDecimalZeroFormat(searchForInvoice);
		searchForCompanyName = companyName;
		searchForLoadID = loadID;
		searchForLoadID = TestUtil.removeDecimalZeroFormat(searchForLoadID);
		searchForAmount = searchAmount;
		searchForAmount = TestUtil.removeDecimalZeroFormat(searchForAmount);
		searchForDate = pullDate;
		searchForDate = searchForDate.replace("'", "");
		brokerUsername = userName;
		brokerPassword = passWord;
	}

	@Test(dependsOnMethods = { "loadBrokerSearchData" })
	public void loginAsBroker() throws InterruptedException {
		loginPage.Brokerlogin(brokerUsername, brokerPassword);
	}

	@Test(dependsOnMethods = { "loginAsBroker" })
	public void verifyBrokerScheduledPaymentsTabTest() throws InterruptedException {
		verifySchedulePaymentsTabElementsDisplayed();
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" })
	public void addScheduledPayment() throws InterruptedException {
		// click pay selection Column to change sort from default to ascending
		brokerschedulepaymentstab.clickpaymenTypeLink();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();

		String carrierUsername, invoiceNum, loadID, paymentAmt, paymentDate, companyName = "";

		// if not enough payment data, add 2 payments
		if (firstRowData.size() < 20) {
			newPaymentObj.newPayment();

			for (int x = 0; x < 2; x++) {

				if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
					carrierUsername = CarrierRegisterTest.carrierUsername;
				} else {
					carrierUsername = "testCarrier" + TestUtil.getCurrentDateTime() + "@loadpaytest.truckstop.com";
					companyName = "Test Company";
				}

				invoiceNum = "NP" + TestUtil.getCurrentDateTime();
				loadID = invoiceNum;
				Integer intPaymentAmt = TestUtil.getRandomNumber(100, 999);
				paymentAmt = intPaymentAmt.toString();
				paymentDate = BrokerNewPaymentTest.strDate;

				newPaymentObj.carrierEmail(carrierUsername);
				newPaymentObj.amount(paymentAmt);
				newPaymentObj.invoiceNumber(invoiceNum);
				newPaymentObj.loadId(loadID);
				newPaymentObj.clickShedulePayment();

				searchForInvoice = invoiceNum;
				searchForInvoice = TestUtil.removeDecimalZeroFormat(searchForInvoice);
				searchForCompanyName = companyName;
				searchForLoadID = loadID;
				searchForLoadID = TestUtil.removeDecimalZeroFormat(searchForLoadID);
				searchForAmount = paymentAmt;
				searchForAmount = TestUtil.removeDecimalZeroFormat(searchForAmount);
				searchForDate = paymentDate;
				searchForDate = searchForDate.replace("'", "");
				pullDate = searchForDate;// figure out how anticipate pull date is determined (always 1 day before?)
			}
		}
	}

	@Test(dependsOnMethods = { "verifyBrokerScheduledPaymentsTabTest" })
	public void verifyclickpaymenTypeLinksortTest() throws InterruptedException {
		// TEST - Pay Selection SORT
		// click pay selection Column to change sort from default to ascending
		brokerschedulepaymentstab.scheduledPaymentsTab.click();
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
		brokerschedulepaymentstab.clickLoadID();
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
		brokerschedulepaymentstab.enterSearchText(searchForCompanyName);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForCompanyName),
				"Matching text [" + searchForCompanyName + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyCompanyNameSearchTest" })
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchForAmount);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());

	}

	@Test(dependsOnMethods = { "verifyAmountSearchTest" })
	public void verifyPayToDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(searchForDate);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForDate),
				"Matching text [" + searchForDate + "] NOT found in [" + firstRowData + "]");
	}

	@Test(dependsOnMethods = { "verifyPayToDateSearchTest" })
	public void verifyAnticipatedPullDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerschedulepaymentstab.enterSearchText(pullDate);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(pullDate),
				"Matching text [" + pullDate + "] NOT found in [" + firstRowData + "]");

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
		brokerschedulepaymentstab.enterSearchText(searchForLoadID);
		brokerschedulepaymentstab.clickSearchButton();
		// click first row to expand
		brokerschedulepaymentstab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerschedulepaymentstab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForLoadID),
				"Matching text [" + searchForLoadID + "] NOT found in [" + firstRowData + "]");

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
