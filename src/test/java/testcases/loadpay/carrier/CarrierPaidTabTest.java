package testcases.loadpay.carrier;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPaidTab;

public class CarrierPaidTabTest extends TestBase {
	CarrierLoginPage loginPage;
	CarrierPaidTab carrierPaidTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchStatusText = "";
	String searchAmountText = "";
	String searchPayerText = "";
	String searchLoadIdText = "";

	public CarrierPaidTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new CarrierLoginPage();
		carrierPaidTab = new CarrierPaidTab();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_Login", dataProvider = "getCarrierPaidTabData")
	public void loginAsCarrier(String carrierEmail, String carrierPassword, String statusText, String amountText,
			String payerText, String loadIdText) throws InterruptedException {
		// login as carrier
		loginPage.Carrierlogin(carrierEmail, carrierPassword);
		searchStatusText = statusText;
		searchAmountText = amountText;
		searchPayerText = payerText;
		searchLoadIdText = loadIdText;

		// Assert PayMeNow, Scheduled and Paid tabs exist
		verifyCarrierTabsDisplayed();
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyPaidTab", dependsOnMethods = { "loginAsCarrier" })
	public void verifyPaidTabTest() throws InterruptedException {
		// click Paid Tab
		carrierPaidTab.clickPaidTab();

		// Assert search bar, search button, and table columns are displayed
		verifyPaidTabElementsDisplayed();
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyStatusSort", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifyStatusSortTest() throws InterruptedException {
		// TEST - STATUS SORT
		// click Status Column to change sort from default to ascending
		carrierPaidTab.clickStatusColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Status Column to change sort from ascending to descending
		carrierPaidTab.clickStatusColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare sorted data sets
		// TODO
		// Uncomment Assertion when LP-3241 https://gojira.truckstop.com/browse/LP-3241
		// is resolved
		if (carrierPaidTab.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyAmountSort", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifyAmountSortTest() throws InterruptedException {
		// TEST - AMOUNT SORT
		// click Amount Column to change sort from default to ascending
		carrierPaidTab.clickAmountColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Amount Column to change sort from ascending to descending
		carrierPaidTab.clickAmountColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierPaidTab.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyPayerSort", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifyPayerSortTest() throws InterruptedException {
		// TEST - PAYER SORT
		// click Payer Column to change sort from default to ascending
		carrierPaidTab.clickPayerColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Payer Column to change sort from ascending to descending
		carrierPaidTab.clickPayerColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierPaidTab.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyLoadIdSort", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifyLoadIdSortTest() throws InterruptedException {
		// TEST - LoadID SORT
		// click LoadID Column to change sort from default to ascending
		carrierPaidTab.clickLoadIDColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click LoadID Column to change sort from ascending to descending
		carrierPaidTab.clickLoadIDColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierPaidTab.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	// TODO
	// Enable when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchStatus", enabled = false, dependsOnMethods = {
			"verifyPaidTabTest" })
	public void verifySearchStatusTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchStatusText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by status");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchAmount", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifySearchAmountTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchAmountText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by amount");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchPayer", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifySearchPayerTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchPayerText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by payer");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchLoadId", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifySearchLoadIdTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchLoadIdText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by load ID");
	}

	public void verifyCarrierTabsDisplayed() {

		// Verify that the Tab Text for Carriers is found
		Assert.assertTrue(carrierPaidTab.payMeNowTab.getText().contains("PayMeNowTM"), "PayMeNow Tab not found");
		Assert.assertTrue(carrierPaidTab.schedulePaymentsTab.getText().contains("SCHEDULED PAYMENTS"),
				"Scheduled Payments Tab not found");
		Assert.assertTrue(carrierPaidTab.paidTab.getText().contains("PAID"), "Paid Tab not found");
	}

	public void verifyPaidTabElementsDisplayed() {

		// Verify that the web elements for the Paid tab exist
		Assert.assertTrue(carrierPaidTab.statusColumn.getText().contains("Status"), "Status Column not found");
		Assert.assertTrue(carrierPaidTab.amountColumn.getText().contains("Amount"), "Amount Column not found");
		Assert.assertTrue(carrierPaidTab.payerColumn.getText().contains("Payer"), "Payer Column not found");
		Assert.assertTrue(carrierPaidTab.loadIDColumn.getText().contains("Load ID"), "Load ID not found");
		Assert.assertTrue(carrierPaidTab.searchButton.isDisplayed(), "Search Button not found");
		Assert.assertTrue(carrierPaidTab.searchInputField.isDisplayed(), "Search Input Field not found");
	}

}
