package testcases.loadpay.carrier;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;
import pages.loadpay.carrier.CarrierSchedulePayment;

public class CarrierSchedulePaymentTest extends TestBase {

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

	public CarrierSchedulePaymentTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		carrierschedulepayment = new CarrierSchedulePayment();

	}

	@Test(description = "LP-3475 CarrierSchedulePaymentTabTest_Login", dataProvider = "getCarrierSchedulePaymentTabData")
	public void loginCarrier(String carrierEmail, String carrierPassword, String amountText, String payerText,
			String loadIdText, String InvoiceNumberText) throws InterruptedException {
		loginPage = new CarrierLoginPage();
		loginPage.Carrierlogin(carrierEmail, carrierPassword);
		searchAmountText = amountText;
		searchPayerText = payerText;
		searchLoadIdText = loadIdText;

	}

	@Test(dependsOnMethods = "loginCarrier")
	public void verifyScheduledPaymentsTabTest() throws InterruptedException {
		// click Paid Tab
		carrierschedulepayment.clickScheduledPaymentsTab();

		// Assert search bar, search button, and table columns are displayed
		// verifySchedulePaymentTabElementsDisplayed();
	}

	@Test(dependsOnMethods = "verifyScheduledPaymentsTabTest")
	public void verifyDaysSortTest() throws InterruptedException {
		// TEST - STATUS SORT
		// click Status Column to change sort from default to ascending
		carrierschedulepayment.clickDaysColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		// click Status Column to change sort from ascending to descending
		carrierschedulepayment.clickDaysColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// compare sorted data sets
		// if(carrierschedulepayment.getRowCount() > 1)
		// Assert.assertNotEquals(firstRowData, lastRowData, "First Row Data: \n" +
		// firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(dependsOnMethods = "verifyDaysSortTest")
	public void verifyAmountSortTest() throws InterruptedException {
		// TEST - AMOUNT SORT
		// click Amount Column to change sort from default to ascending
		carrierschedulepayment.clickAmountColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		// click Amount Column to change sort from ascending to descending
		carrierschedulepayment.clickAmountColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierschedulepayment.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(dependsOnMethods = "verifyAmountSortTest")
	public void verifyPayerSortTest() throws InterruptedException {
		// TEST - PAYER SORT
		// click Payer Column to change sort from default to ascending
		carrierschedulepayment.clickPayerColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		// click Payer Column to change sort from ascending to descending
		carrierschedulepayment.clickPayerColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// verify row data exists
		Assert.assertNotNull(firstRowData, "First Row Data: \n" + firstRowData);
		Assert.assertNotNull(lastRowData, "\nLast Row Data: \n" + lastRowData);
	}

	@Test(dependsOnMethods = "verifyPayerSortTest")
	public void verifyLoadIdSortTest() throws InterruptedException {
		// TEST - LoadID SORT
		// click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickLoadIDColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		// click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickLoadIDColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierschedulepayment.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);
	}

	@Test(dependsOnMethods = "verifyLoadIdSortTest")
	public void verifyInvoiceSortTest() throws InterruptedException {
		// TEST - LoadID SORT
		// click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickInvoiceColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		// click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickInvoiceColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		if (carrierschedulepayment.getRowCount() > 1)
			Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by InvoiceNumber!");
	}

	@Test(dependsOnMethods = "verifyInvoiceSortTest")
	public void verifySchedDateSortTest() throws InterruptedException {
		// TEST - LoadID SORT
		// click LoadID Column to change sort from default to ascending
		carrierschedulepayment.clickschdateColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when sorting by date");
		// click LoadID Column to change sort from ascending to descending
		carrierschedulepayment.clickschdateColumn();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierschedulepayment.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertTrue(lastRowData.size() > 0, "No data rows found when sorting by date");
	}

	// TODO
	// Enable when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved

	@Test(dependsOnMethods = "verifySchedDateSortTest")
	public void verifySearchAmountTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchAmountText);
		carrierschedulepayment.clickSearchButton();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by amount");
	}

	@Test(dependsOnMethods = "verifySearchAmountTest")
	public void verifySearchPayerTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchPayerText);
		carrierschedulepayment.clickSearchButton();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by payer");
	}

	@Test(dependsOnMethods = "verifySearchPayerTest")
	public void verifySearchLoadIdTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierschedulepayment.enterSearchText(searchLoadIdText);
		carrierschedulepayment.clickSearchButton();
		// click first row to expand
		carrierschedulepayment.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierschedulepayment.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by load ID");
	}

	public void verifySchedulePaymentTabElementsDisplayed() {

		// Verify that the web elements for the Paid tab exist

		Assert.assertTrue(carrierschedulepayment.amountColumn.getText().contains("Amount"), "Amount Column not found");
		Assert.assertTrue(carrierschedulepayment.payerColumn.getText().contains("Payer"), "Payer Column not found");
		Assert.assertTrue(carrierschedulepayment.loadIDColumn.getText().contains("Load ID"), "Load ID not found");
		Assert.assertTrue(carrierschedulepayment.InvoiceColumn.getText().contains("Invoice #"), "Invoice # not found");
		Assert.assertTrue(carrierschedulepayment.schdateColumn.getText().contains("Schedule Date"),
				"Schedule Date not found");
		Assert.assertTrue(carrierschedulepayment.searchButton.isDisplayed(), "Search Button not found");
		Assert.assertTrue(carrierschedulepayment.searchInputField.isDisplayed(), "Search Input Field not found");
	}
}
