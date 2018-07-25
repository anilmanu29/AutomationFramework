package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerBulkUploadPaymentsmatchedCarrier;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerScheduledPaymentsTab;

public class BrokerBulkUploadPaymentsmatchedCarrierTest extends TestBase {

	BrokerBulkUploadPaymentsmatchedCarrier bbmp;
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForText = "";

	/*-------Initializing driver---------*/
	public BrokerBulkUploadPaymentsmatchedCarrierTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new BrokerLoginPage();
		bbmp = new BrokerBulkUploadPaymentsmatchedCarrier();

	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) throws InterruptedException, IOException, AWTException {
		loginPage.Brokerlogin(un, pwd);
	}

	@Test(dependsOnMethods = "loginBroker")
	public void verifynewPayment() throws InterruptedException {
		bbmp.newPayment();
	}

	@Test(dependsOnMethods = "verifynewPayment")
	public void verifyUploadFile() throws InterruptedException, IOException, AWTException {
		bbmp.UploadFile();

	}

	@Test(dependsOnMethods = "verifyUploadFile")
	public void verifyClickimport() throws InterruptedException, IOException {
		bbmp.Clickimport();

	}

	@Test(dependsOnMethods = "verifyClickimport")
	public void verifyClickschpayment() throws InterruptedException, IOException {
		bbmp.Clickschpayment();

	}

	@Test(dependsOnMethods = "verifyClickschpayment")
	public void verifyClickGridDown() throws InterruptedException, IOException {
		bbmp.ClickGridDown();

	}

	@Test(dependsOnMethods = "verifyClickGridDown")
	public void verifyclickAmountSortTest() throws InterruptedException {
		// TEST - Paid to Carrier
		// click Paid to Carrier to change sort from default to ascending
		bbmp.clickAmount();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		bbmp.clickAmount();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Paid to Carrier!");
	}

	@Test(dependsOnMethods = "verifyclickAmountSortTest")
	public void verifyclickLoadIDSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		bbmp.clickLoadID();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Carrier change sort from ascending to descending
		// brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}

	@Test(dataProvider = "getBulkUploadPaymentsmatchedData", dependsOnMethods = "verifyclickLoadIDSortTest")
	public void verifyAmountSearchTest(String Amount, String LoadID) throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(Amount);
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}

	@Test(dataProvider = "getBulkUploadPaymentsmatchedData", dependsOnMethods = "verifyAmountSearchTest")
	public void verifyLoadIDNumSearchTest(String Amount, String LoadID) throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(LoadID);
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}

	public void verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(bbmp.lnk_newpayment.isDisplayed(), "newpayment link  not found");
		Assert.assertTrue(bbmp.link_Upload.isDisplayed(), "upload link not found");
		Assert.assertTrue(bbmp.btn_import.isDisplayed(), "import button not found");
		Assert.assertTrue(bbmp.link_schpaymnt.isDisplayed(), "schedule payment tile not found");
		Assert.assertTrue(bbmp.link_griddown.isDisplayed(), "grid Pulled down column not found");
		Assert.assertTrue(bbmp.click_pulldate.isDisplayed(), "pulldate Column not found");
		Assert.assertTrue(bbmp.click_PayToDate.isDisplayed(), "PayToDate from Bank data Column not found");
		Assert.assertTrue(bbmp.click_Amount.isDisplayed(), "Amount Column not found");
		Assert.assertTrue(bbmp.click_Carrier.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(bbmp.click_InvoiceID.isDisplayed(), "InvoiceID Pulled column not found");
		Assert.assertTrue(bbmp.Click_LoadID.isDisplayed(), "LoadID column not found");
		Assert.assertNotNull(bbmp.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(bbmp.searchButton, "Search Button not found");

	}

}
