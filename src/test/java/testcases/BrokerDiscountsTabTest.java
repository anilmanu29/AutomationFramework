package testcases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerDiscountsTab;
import pages.BrokerLoginPage;

public class BrokerDiscountsTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerDiscountsTab brokerdiscountsTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForInvoice, searchforcompanyname, searchforLoadID, searchforAmount, searchforDate = "";

	public BrokerDiscountsTabTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new BrokerLoginPage();
		brokerdiscountsTab = new BrokerDiscountsTab();
	}

	@Test(dataProvider = "getBrokerLoginData", priority = 32)
	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		// login as broker
		loginPage.Brokerlogin(un, pwd);
	}

	@Test(dependsOnMethods = { "loginAsBroker" }, priority = 33)
	public void verifyDiscountsTabTest() throws InterruptedException {

		brokerdiscountsTab.clickDiscountsTab();
		verifyDiscountTabElementsDisplayed();
	}

	@Test(dependsOnMethods = {"verifyDiscountsTabTest" }, priority = 34)
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

	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 35)
	public void verifyLoadIDSortTest() throws InterruptedException {
		// TEST - LoadID
		// click LoadID sort from default to ascending
		brokerdiscountsTab.clickLoadIDColumn();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		
		searchForInvoice = brokerdiscountsTab.invoice.getText();
		searchforcompanyname = brokerdiscountsTab.companyname.getText();
		searchforLoadID = brokerdiscountsTab.loadid.getText();
		searchforDate = 	brokerdiscountsTab.date.getAttribute("innerText").replace("PayMeNowTM", "");
		searchforAmount =	brokerdiscountsTab.amount.getAttribute("innerText").replace("INVOICE AMOUNT:$", "");
		System.out.println(searchforAmount);
		lastRowData = brokerdiscountsTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  LoadID!");
	}

	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 36)
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
	
	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 37)
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
	
	
	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 38)
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
	
	
	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 39)
	public void verifyDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforDate);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforDate),
				"Matching text [" + searchforDate + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = {"verifyDiscountsTabTest"}, priority = 40)
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerdiscountsTab.enterSearchText(searchforAmount);
		brokerdiscountsTab.clickSearchButton();
		// click first row to expand
		brokerdiscountsTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerdiscountsTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforAmount),
				"Matching text [" + searchforAmount + "] NOT found in [" + firstRowData + "]");

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
		Assert.assertNotNull(brokerdiscountsTab.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(brokerdiscountsTab.searchButton, "Search Button not found");

	}

}
