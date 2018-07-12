package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminSearchPage;
import util.TestUtil;

public class AdminSearchTest extends TestBase
{
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminSearchPage adminSearchPage;
	String adminUN = "";
	String adminPW = "";
	Integer maxRecordCount = 0;
	
	public AdminSearchTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException, AWTException 
	{
		initialization();
		adminHomePage = new AdminHomePage();	
		adminLoginPage = new AdminLogin();
		adminSearchPage = new AdminSearchPage();
	}
	
	//login as admin
	@Test(description="LP-5423 Admin_Search_adminLogin", dataProvider="getAdminLoginData")
	public void adminLogin(String Username,String pass) throws AWTException, InterruptedException
	{		
		adminHomePage.AdminURL();
		Thread.sleep(1000);
		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);
		Thread.sleep(1000);
		adminLoginPage.adminLogin();
		Thread.sleep(1000);
	}
	
	//click search tab on left pane
	@Test(description="LP-5423 Admin_Search_goToSearchPage", dependsOnMethods = {"adminLogin"})
	public void goToSearchPage()
	{	
		adminSearchPage.clickSearchPageLink();
	}
	
	//  Test search scenarios
	//	SCENARIOS DRIVEN BY DATA in TestData.xlsx
	//	Verify user can search by PaymentID
	//	Verify user can search by Invoice Amount Range
	//	Verify user can search by Date Range
	//	Verify user can search by PaymentID, Invoice Amount Range, and Date Range	
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - All
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Unmatched
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Not Scheduled
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Scheduled
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Paid
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Error
	//	Verify filters - Search for payments with these statuses and verify they come up correctly - Cancelled
	@Test(description="LP-5423 Admin_Search_goToSearchPage", dependsOnMethods = {"goToSearchPage"}, dataProvider="getAdminSearchData")
	public void searchTest(
			String paymentId, String invoiceAmountFrom, String invoiceAmountTo, String dateFrom, String dateTo,
			String filterByAll, String filterByUnmatched, String filterByNotScheduled, String filterByScheduled,
			String filterByPaid, String filterByError, String filterByCanceled) throws InterruptedException
	{
		
		Boolean assertPaymentID = false;
		Boolean assertInvoiceAmountFrom = false;
		Boolean assertInvoiceAmountTo = false;
		Boolean assertDateFrom = false;
		Boolean assertDateTo = false;
		Boolean assertFilterByAll = false;
		Boolean assertFilterByUnmatched = false;
		Boolean assertFilterByNotScheduled = false;
		Boolean assertFilterByScheduled = false;
		Boolean assertFilterByPaid = false;
		Boolean assertFilteredByError = false;
		Boolean assertFilterByCanceled = false;
		
		//clean up excel formatting if it exists
		paymentId = TestUtil.removeDecimalZeroFormat(paymentId);
		invoiceAmountFrom = TestUtil.removeDecimalZeroFormat(invoiceAmountFrom);
		invoiceAmountTo = TestUtil.removeDecimalZeroFormat(invoiceAmountTo);
		
		//reset fields first so that the fields are reset on each iteration as determined by the dataProvider
		adminSearchPage.resetFields();
		
		//check data provider elements for null values...if not null, setup the relevant data in the web page
		if(paymentId != null)
		{
			adminSearchPage.setSearchInputField(paymentId);
			assertPaymentID = true;
		}
		
		if(invoiceAmountFrom != null)
		{
			adminSearchPage.setAmountFromInputField(invoiceAmountFrom);
			assertInvoiceAmountFrom = true;
		}
		
		if(invoiceAmountTo != null)
		{
			adminSearchPage.setAmountToInputField(invoiceAmountTo);
			assertInvoiceAmountTo = true;
		}
		
		if(dateFrom != null)
		{
			adminSearchPage.setStartDateInputField(dateFrom);
			assertDateFrom = true;
		}
		
		if(dateTo != null)
		{
			adminSearchPage.setEndDateInputField(dateTo);
			assertDateTo = true;
		}
		
		//set filter for ALL based on TestData.xlsx
		if(filterByAll != null)
		{
			if(filterByAll.equalsIgnoreCase("t"))
			{
				assertFilterByAll = true;
				
				if(!adminSearchPage.isAllFilterChecked())
				{
					adminSearchPage.clickAllCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isAllFilterChecked(), "All filter NOT checked but data indicates it should be!");
			}
			else if(filterByAll.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isAllFilterChecked())
				{
					adminSearchPage.clickAllCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isAllFilterChecked(), "All filter IS checked but data indicates it should NOT be!");
			}		
		}

		
		//set filter for UNMATCHED based on TestData.xlsx
		if(filterByUnmatched != null)
		{
			if(filterByUnmatched.equalsIgnoreCase("t"))
			{
				assertFilterByUnmatched = true;
				
				if(!adminSearchPage.isUnmatchedChecked())
				{
					adminSearchPage.clickUnmatchedCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isUnmatchedChecked(), "Unmatched filter NOT checked but data indicates it should be!");
			}
			else if(filterByUnmatched.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isUnmatchedChecked())
				{
					adminSearchPage.clickUnmatchedCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isUnmatchedChecked(), "Unmatched filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//set filter for NOT SCHEDULED based on TestData.xlsx
		if(filterByNotScheduled != null)
		{
			if(filterByNotScheduled.equalsIgnoreCase("t"))
			{
				assertFilterByNotScheduled = true;
				
				if(!adminSearchPage.isNotScheduledChecked())
				{
					adminSearchPage.clickNotScheduledCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isNotScheduledChecked(), "Not Scheduled filter NOT checked but data indicates it should be!");
			}
			else if(filterByNotScheduled.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isNotScheduledChecked())
				{
					adminSearchPage.clickNotScheduledCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isNotScheduledChecked(), "Not Scheduled filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//set filter for SCHEDULED based on TestData.xlsx
		if(filterByScheduled != null)
		{
			if(filterByScheduled.equalsIgnoreCase("t"))
			{
				assertFilterByScheduled = true;
				
				if(!adminSearchPage.isScheduledChecked())
				{
					adminSearchPage.clickScheduledCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isScheduledChecked(), "Scheduled filter NOT checked but data indicates it should be!");
			}
			else if(filterByScheduled.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isScheduledChecked())
				{
					adminSearchPage.clickScheduledCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isScheduledChecked(), "Scheduled filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//set filter for PAID based on TestData.xlsx
		if(filterByPaid != null)
		{
			if(filterByPaid.equalsIgnoreCase("t"))
			{
				assertFilterByPaid = true;
				
				if(!adminSearchPage.isPaidChecked())
				{
					adminSearchPage.clickPaidCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isPaidChecked(), "Paid filter NOT checked but data indicates it should be!");
			}
			else if(filterByPaid.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isPaidChecked())
				{
					adminSearchPage.clickPaidCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isPaidChecked(), "Paid filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//set filter for ERROR based on TestData.xlsx
		if(filterByError != null)
		{
			if(filterByError.equalsIgnoreCase("t"))
			{
				assertFilteredByError = true;
				
				if(!adminSearchPage.isErrorChecked())
				{
					adminSearchPage.clickErrorCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isErrorChecked(), "Error filter NOT checked but data indicates it should be!");
			}
			else if(filterByError.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isErrorChecked())
				{
					adminSearchPage.clickErrorCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isErrorChecked(), "Error filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//set filter for CANCELED based on TestData.xlsx
		if(filterByCanceled != null)
		{
			if(filterByCanceled.equalsIgnoreCase("t"))
			{
				assertFilterByCanceled = true;
				
				if(!adminSearchPage.isCanceledChecked())
				{
					adminSearchPage.clickCanceledCheckBox();
				}
				
				Assert.assertTrue(adminSearchPage.isCanceledChecked(), "Canceled filter NOT checked but data indicates it should be!");
			}
			else if(filterByCanceled.equalsIgnoreCase("f"))
			{
				if(adminSearchPage.isCanceledChecked())
				{
					adminSearchPage.clickCanceledCheckBox();
				}
				
				Assert.assertFalse(adminSearchPage.isCanceledChecked(), "Canceled filtered IS checked but data indicates it should NOT be!");
			}
		}
		
		//click search button
		adminSearchPage.clickSearchButton();
		
		//check for loading icon
		WebElement loadingGif = driver.findElement(By.xpath(".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/img"));
		
		while(loadingGif.isDisplayed())
		{
			Thread.sleep(3000);
			System.out.println("Waiting for Loading Gif to disappear!");
		}
		
		
		//verify data with assertions
		if(assertPaymentID)
			Assert.assertTrue(adminSearchPage.verifyFirstRowData(paymentId), "Payment ID not found in the first row of data returned");
		
		if(assertInvoiceAmountFrom || assertInvoiceAmountTo)
			Assert.assertTrue(adminSearchPage.verifyFirstRowDataWithinRange(invoiceAmountFrom, invoiceAmountTo, "Invoice Amount"), "Found Invoice Amount NOT within range");
		
		if(assertDateFrom || assertDateTo)
			Assert.assertTrue(adminSearchPage.verifyFirstRowDataWithinRange(dateFrom, dateTo, "Term Date"), "Found Invoice Date NOT within range");
		
		//verify ALL filter - get max record count here and use as comparison for other filters
		if(assertFilterByAll)
		{
			maxRecordCount = adminSearchPage.getTotalRecordCount();
			System.out.println("\n\n======================\nMax record Count - ALL: [" + maxRecordCount + "]\n=======================\n");
			Assert.assertTrue(maxRecordCount > 0, "Filter by ALL - Record count not greater than zero");
		}
		
		if(assertFilterByUnmatched)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by UNMATCHED not found in the first row of data returned");
		
		if(assertFilterByNotScheduled)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by NOT SCHEDULE not found in the first row of data returned");
		
		if(assertFilterByScheduled)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by SCHEDULED not found in the first row of data returned");
		
		if(assertFilterByPaid)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by PAID not found in the first row of data returned");
		
		if(assertFilteredByError)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by ERROR not found in the first row of data returned");
		
		if(assertFilterByCanceled)
			Assert.assertTrue(adminSearchPage.getTotalRecordCount() <= maxRecordCount, "Filter by CANCELED not found in the first row of data returned");
	}
}
