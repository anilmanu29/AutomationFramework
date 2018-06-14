package testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CarrierLoginPage;
import pages.CarrierPaidTab;

public class CarrierPaidTabTest extends TestBase{
	CarrierLoginPage loginPage;
	CarrierPaidTab carrierPaidTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchStatusText = "";
	String searchAmountText = "";
	String searchPayerText = "";
	String searchLoadIdText = "";
	
	public CarrierPaidTabTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierLoginPage();
		carrierPaidTab = new CarrierPaidTab();
	}


	@Test(description="LP-3476 CarrierPaidTabTest_Login", dataProvider="getCarrierPaidTabData", priority=1)
	public void loginAsCarrier(String carrierEmail, String carrierPassword, String statusText, String amountText, String payerText, String loadIdText) throws InterruptedException
	{
		//login as carrier
		loginPage.Carrierlogin(carrierEmail, carrierPassword);
		searchStatusText = statusText;
		searchAmountText = amountText;
		searchPayerText = payerText;
		searchLoadIdText = loadIdText;
		
		//Assert PayMeNow, Scheduled and Paid tabs exist
		verifyCarrierTabsDisplayed();
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifyPaidTab", dependsOnMethods = {"loginAsCarrier"}, priority=2)
	public void verifyPaidTabTest() throws InterruptedException
	{
		//click Paid Tab
		carrierPaidTab.clickPaidTab();
		
		//Assert search bar, search button, and table columns are displayed
		verifyPaidTabElementsDisplayed();
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifyStatusSort", dependsOnMethods = {"verifyPaidTabTest"}, priority=3)
	public void verifyStatusSortTest() throws InterruptedException
	{
		//TEST - STATUS SORT
		//click Status Column to change sort from default to ascending
		carrierPaidTab.clickStatusColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		//click Status Column to change sort from ascending to descending
		carrierPaidTab.clickStatusColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		//compare sorted data sets
		//TODO
		//Uncomment Assertion when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Status!");
	}	
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifyAmountSort", dependsOnMethods = {"verifyPaidTabTest"}, priority=4)
	public void verifyAmountSortTest() throws InterruptedException
	{
		//TEST - AMOUNT SORT
		//click Amount Column to change sort from default to ascending
		carrierPaidTab.clickAmountColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		//click Amount Column to change sort from ascending to descending
		carrierPaidTab.clickAmountColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Amount!");
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifyPayerSort", dependsOnMethods = {"verifyPaidTabTest"}, priority=5)
	public void verifyPayerSortTest() throws InterruptedException
	{
		//TEST - PAYER SORT
		//click Payer Column to change sort from default to ascending
		carrierPaidTab.clickPayerColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		//click Payer Column to change sort from ascending to descending
		carrierPaidTab.clickPayerColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Payer!");
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifyLoadIdSort", dependsOnMethods = {"verifyPaidTabTest"}, priority=6)
	public void verifyLoadIdSortTest() throws InterruptedException
	{
		//TEST - LoadID SORT
		//click LoadID Column to change sort from default to ascending
		carrierPaidTab.clickLoadIDColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		//click LoadID Column to change sort from ascending to descending
		carrierPaidTab.clickLoadIDColumn();
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		//compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by LoadID!");
	}
	
	//TODO
	//Enable when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
	@Test(description="LP-3476 CarrierPaidTabTest_VerifySearchStatus", enabled = false, dependsOnMethods = {"verifyPaidTabTest"}, priority=7)
	public void verifySearchStatusTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchStatusText);
		carrierPaidTab.clickSearchButton();		
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchStatusText), "Matching text [" + searchStatusText + "] NOT found in [" + firstRowData + "]");
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifySearchAmount", dependsOnMethods = {"verifyPaidTabTest"}, priority=8)
	public void verifySearchAmountTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchAmountText);
		carrierPaidTab.clickSearchButton();		
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchAmountText), "Matching text [" + searchAmountText + "] NOT found in [" + firstRowData + "]");
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifySearchPayer", dependsOnMethods = {"verifyPaidTabTest"}, priority=9)
	public void verifySearchPayerTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchPayerText);
		carrierPaidTab.clickSearchButton();		
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchPayerText), "Matching text [" + searchPayerText + "] NOT found in [" + firstRowData + "]");
	}
	
	@Test(description="LP-3476 CarrierPaidTabTest_VerifySearchLoadId", dependsOnMethods = {"verifyPaidTabTest"}, priority=10)
	public void verifySearchLoadIdTest() throws InterruptedException
	{
		//TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchLoadIdText);
		carrierPaidTab.clickSearchButton();		
		//click first row to expand
		carrierPaidTab.clickFirstRow();
		//get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchLoadIdText), "Matching text [" + searchLoadIdText + "] NOT found in [" + firstRowData + "]");
	}
	
	public void verifyCarrierTabsDisplayed(){

		//Verify that the Tab Text for Carriers is found
		Assert.assertTrue(carrierPaidTab.payMeNowTab.getText().contains("PayMeNowTM"), "PayMeNow Tab not found");
		Assert.assertTrue(carrierPaidTab.schedulePaymentsTab.getText().contains("SCHEDULED PAYMENTS"), "Scheduled Payments Tab not found");
		Assert.assertTrue(carrierPaidTab.paidTab.getText().contains("PAID"), "Paid Tab not found");
	}
	
	public void verifyPaidTabElementsDisplayed(){

		//Verify that the web elements for the Paid tab exist
		Assert.assertTrue(carrierPaidTab.statusColumn.getText().contains("Status"), "Status Column not found");
		Assert.assertTrue(carrierPaidTab.amountColumn.getText().contains("Amount"), "Amount Column not found");
		Assert.assertTrue(carrierPaidTab.payerColumn.getText().contains("Payer"), "Payer Column not found");
		Assert.assertTrue(carrierPaidTab.loadIDColumn.getText().contains("Load ID"), "Load ID not found");
		Assert.assertTrue(carrierPaidTab.searchButton.isDisplayed(), "Search Button not found");
		Assert.assertTrue(carrierPaidTab.searchInputField.isDisplayed(), "Search Input Field not found");
	}
	
}
