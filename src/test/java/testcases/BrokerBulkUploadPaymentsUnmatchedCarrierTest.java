package testcases;
import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerBulkUploadPaymentsUnmatchedCarrier;
import pages.BrokerBulkUploadPaymentsmatchedCarrier;
import pages.BrokerLoginPage;
import pages.BrokerScheduledPaymentsTab;

public class BrokerBulkUploadPaymentsUnmatchedCarrierTest extends TestBase 
{
	
	BrokerBulkUploadPaymentsUnmatchedCarrier bbmp;
	BrokerLoginPage loginPage;
	BrokerScheduledPaymentsTab brokerschedulepaymentstab;
	BrokerLoginPage bl;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchForText = "";
	 
	
	/*-------Initializing driver---------*/
	public BrokerBulkUploadPaymentsUnmatchedCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage= new BrokerLoginPage();
		bbmp = new BrokerBulkUploadPaymentsUnmatchedCarrier();
		
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=12)
	public void loginBroker(String un, String pwd) throws InterruptedException, IOException, AWTException
	{
		loginPage.Brokerlogin(un, pwd);
		
		
		
	}
	@Test(priority = 13)
	public void verifyBulkUploadPaymentsmatched() throws InterruptedException {
		bbmp = new BrokerBulkUploadPaymentsUnmatchedCarrier();
		//verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed(); 
		
	}
	
	@Test( priority = 14)
	public void verifynewPayment() throws InterruptedException {
		bbmp.newPayment();
		Thread.sleep(1000);
	}
	

	@Test(priority = 15)
	public void verifyUploadFile() throws InterruptedException, IOException, AWTException {
		bbmp.UploadFile();
		Thread.sleep(2000);
	}
	
		
	@Test(priority = 16)
	public void verifyClickimport() throws InterruptedException, IOException {
		bbmp.Clickimport();
		Thread.sleep(2000);
	}
	
	@Test(priority = 17)
	public void verifyClickschpayment() throws InterruptedException, IOException {
		bbmp.Clickschpayment();
		Thread.sleep(1000);
	}
		
	@Test(priority = 18)
	public void verifyClickGridDown() throws InterruptedException, IOException {
		bbmp.ClickGridDown();
		Thread.sleep(1000);
	}
	
	/*@Test( priority = 8)
	public void verifyBrokerScheduledPaymentsTabTest() throws InterruptedException {
		verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed();
	}
	*/
	/*@Test(priority = 9)
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

	@Test( priority = 10 )
	public void verifyclickAnticipatedPullDatesortTest() throws InterruptedException {
		// TEST - Discount SORT
		// click Discount Column to change sort from default to ascending
		bbmp.clickAnticipatedPullDate();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Discount Column to change sort from ascending to descending
		bbmp.clickAnticipatedPullDate();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Discount!");
	}

	@Test( priority = 11)
	public void verifyclickPayToDatesortTest() throws InterruptedException {
		// TEST - Pulled from Bank Date
		// click Pulled from Bank Date to change sort from default to ascending
		bbmp.clickPayToDate();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		bbmp.clickPayToDate();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by Pulled from Bank Date!");
	}*/

	@Test(priority = 19)
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

	/*@Test( priority = 13)
	public void verifyclickCarrierSortTest() throws InterruptedException {
		// TEST - Amount Pulled
		// click Amount Pulled to change sort from default to ascending
		bbmp.clickCarrier();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Amount Pulled to change sort from ascending to descending
		bbmp.clickCarrier();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test( priority = 14)
	public void verifyclickinvoiceSortTest() throws InterruptedException {
		// TEST - Amount Paid To Carrier
		// click Amount Paid ToCarrier sort from default to ascending
		bbmp.clickinvoice();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Amount Paid To Carrier change sort from ascending to descending
		bbmp.clickinvoice();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}*/

	@Test(priority = 20)
	public void verifyclickLoadIDSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		bbmp.clickLoadID();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		// click Carrier change sort from ascending to descending
		//brokerschedulepaymentstab.clickLoadID();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = bbmp.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}
	
	/*@Test( priority = 16)
	public void verifyCompanyNameSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(brokerschedulepaymentstab.CompanyName.getText());
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}*/
	
	@Test(priority = 21)
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(bbmp.Amount.getText());
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
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
	
	/*@Test(  priority = 18)
	public void verifyAnticipatedPullDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(bbmp.AnticipatedPullDate.getAttribute("innerText"));
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(  priority = 19)
	public void verifyinvoiceNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(bbmp.invoiceNum.getText());
		bbmp.clickSearchButton();
		// click first row to expand
		bbmp.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = bbmp.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForText),
				"Matching text [" + searchForText + "] NOT found in [" + firstRowData + "]");

	}*/
	
	@Test( priority = 22)
	public void verifyLoadIDNumSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		bbmp.enterSearchText(bbmp.LoadIDNum.getText());
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
		Assert.assertTrue(bbmp.btn_import.isDisplayed(),
				"import button not found");
		Assert.assertTrue(bbmp.link_schpaymnt.isDisplayed(), "schedule payment tile not found");
		Assert.assertTrue(bbmp.link_griddown.isDisplayed(), "grid Pulled down column not found");
		Assert.assertTrue(bbmp.click_pulldate.isDisplayed(), "pulldate Column not found");
		Assert.assertTrue(bbmp.click_PayToDate.isDisplayed(),
				"PayToDate from Bank data Column not found");
		Assert.assertTrue(bbmp.click_Amount.isDisplayed(), "Amount Column not found");
		Assert.assertTrue(bbmp.click_Carrier.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(bbmp.click_InvoiceID.isDisplayed(), "InvoiceID Pulled column not found");
		Assert.assertTrue(bbmp.Click_LoadID.isDisplayed(),
				"LoadID column not found");
		Assert.assertNotNull(bbmp.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(bbmp.searchButton, "Search Button not found");
		

	}
		
		
		
	

}
	

	
	
	
