package testcases.loadpay.broker;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerDiscountsTab;
import pages.loadpay.broker.BrokerLoginPage;
import util.TestUtil;

public class BrokerDiscountsTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerDiscountsTab brokerdiscountsTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForInvoice, searchforcompanyname, searchforLoadID, searchforAmount, searchforDate = "";
	String brokerUsername, brokerPassword = "";

	public BrokerDiscountsTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new BrokerLoginPage();
		brokerdiscountsTab = new BrokerDiscountsTab();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerDiscountsTabSearchData")
	public void loadBrokerSearchData(String userName, String passWord, String companyName, String pullDate,
			String searchAmount, String invoiceNumber, String loadID) throws InterruptedException {

		searchForInvoice = invoiceNumber;
		searchForInvoice = TestUtil.removeDecimalZeroFormat(searchForInvoice);
		searchforcompanyname = companyName;
		searchforLoadID = loadID;
		searchforLoadID = TestUtil.removeDecimalZeroFormat(searchforLoadID);
		searchforAmount = searchAmount;
		searchforAmount = TestUtil.removeDecimalZeroFormat(searchforAmount);
		searchforDate = pullDate;
		searchforDate = searchforDate.replace("'", "");
		brokerUsername = userName;
		brokerPassword = passWord;
	}

	@Test(dependsOnMethods = { "loadBrokerSearchData" })
	public void loginAsBroker() throws InterruptedException {
		// login as broker
		loginPage.Brokerlogin(brokerUsername, brokerPassword);
	}

	@Test(dependsOnMethods = { "loginAsBroker" })
	public void verifyDiscountsTabTest() throws InterruptedException {

		brokerdiscountsTab.clickDiscountsTab();
		verifyDiscountTabElementsDisplayed();
	}

	@Test(dependsOnMethods = { "verifyDiscountsTabTest" })
	public void verifyCarrierSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		brokerdiscountsTab.clickCarrierColumn();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		// click Carrier change sort from ascending to descending
		brokerdiscountsTab.clickCarrierColumn();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerdiscountsTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}

	@Test(dependsOnMethods = { "verifyCarrierSortTest" })
	public void verifyLoadIDSortTest() throws InterruptedException {
		// TEST - LoadID
		// click LoadID sort from default to ascending
		brokerdiscountsTab.clickLoadIDColumn();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();

		// click LoadID sort from default to ascending
		brokerdiscountsTab.clickLoadIDColumn();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerdiscountsTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		// Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal
		// when sorted by LoadID!");
	}

	@Test(dependsOnMethods = { "verifyLoadIDSortTest" })
	public void verifyInvoiceSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforcompanyname);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforcompanyname),
				"Matching text [" + searchforcompanyname + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyInvoiceSearchTest" })
	public void verifyCompanyNameSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchForInvoice);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForInvoice),
				"Matching text [" + searchForInvoice + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyCompanyNameSearchTest" })
	public void verifyLoadIDSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforLoadID);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforLoadID),
				"Matching text [" + searchforLoadID + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dependsOnMethods = { "verifyLoadIDSearchTest" })
	public void verifyDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforDate);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());

	}

	@Test(dependsOnMethods = { "verifyDateSearchTest" })
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforAmount);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(!firstRowData.isEmpty(),
				"First row of data appears to be empty - size = " + firstRowData.size());

	}

	public void verifyDiscountTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerdiscountsTab.discountstabelements.get(0).getText().contains("Invoice Totals"),
				"Invoice Totals not found");
		Assert.assertTrue(brokerdiscountsTab.discountstabelements.get(0).getText().contains("Paid Totals"),
				"Paid Totals not found");
		Assert.assertTrue(brokerdiscountsTab.discountstabelements.get(0).getText().contains("Profits Realized"),
				"Profits Realized not found");
		Assert.assertTrue(brokerdiscountsTab.carriercolumn.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(brokerdiscountsTab.loadIDColumn.isDisplayed(), "Load ID column not found");
		Assert.assertTrue(brokerdiscountsTab.searchInputField.isDisplayed(), "Search Input Field not found");
		Assert.assertTrue(brokerdiscountsTab.searchButton.isDisplayed(), "Search Button not found");

	}

}
