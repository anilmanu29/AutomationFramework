package testcases;
import java.awt.AWTException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.AdminEditEmailBroker;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.AdminSearchPage;
import pages.BrokerLoginPage;
import pages.BrokerOutlook;
import pages.BrokerRegister;
import pages.outlooklogin;

public class AdminSearchTest extends TestBase
{
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminSearchPage adminSearchPage;
	
	String adminUN = "";
	String adminPW = "";
	
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
	
	@Test(description="LP-5423 Admin_Search_goToSearchPage", dependsOnMethods = {"goToSearchPage"}, dataProvider="getAdminSearchData")
	public void searchTest(
			String paymentId, String invoiceAmountFrom, String invoiceAmountTo, String dateFrom, String dateTo,
			String filterByAll, String filterByUnmatched, String filterByNotScheduled, String filterByScheduled,
			String filterByPaid, String filteredByError, String filterByCanceled)
	{	
		if(!paymentId.isEmpty())
			adminSearchPage.setSearchInputField(paymentId);
		
		if(!invoiceAmountFrom.isEmpty())
			adminSearchPage.setAmountFromInputField(invoiceAmountFrom);
		
		if(!invoiceAmountTo.isEmpty())
			adminSearchPage.setAmountToInputField(invoiceAmountTo);
		
		if(!dateFrom.isEmpty())
			adminSearchPage.setStartDateInputField(dateFrom);
		
		if(!dateTo.isEmpty())
			adminSearchPage.setEndDateInputField(dateTo);
		
		//set filter for ALL based on TestData.xlsx
		if(filterByAll.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isAllFilterChecked())
			{
				adminSearchPage.clickAllCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isAllFilterChecked(), "All filter NOT checked but data indicates it should be!");
		}
		else if(filterByAll.equalsIgnoreCase("f") || filterByAll.isEmpty())
		{
			if(adminSearchPage.isAllFilterChecked())
			{
				adminSearchPage.clickAllCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isAllFilterChecked(), "All filter IS checked but data indicates it should NOT be!");
		}
		
		//set filter for UNMATCHED based on TestData.xlsx
		if(filterByUnmatched.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isUnmatchedChecked())
			{
				adminSearchPage.clickUnmatchedCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isUnmatchedChecked(), "Unmatched filter NOT checked but data indicates it should be!");
		}
		else if(filterByUnmatched.equalsIgnoreCase("f") || filterByUnmatched.isEmpty())
		{
			if(adminSearchPage.isUnmatchedChecked())
			{
				adminSearchPage.clickUnmatchedCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isUnmatchedChecked(), "Unmatched filtered IS checked but data indicates it should NOT be!");
		}
		
		//set filter for NOT SCHEDULED based on TestData.xlsx
		if(filterByNotScheduled.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isNotScheduledChecked())
			{
				adminSearchPage.clickNotScheduledCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isNotScheduledChecked(), "Not Scheduled filter NOT checked but data indicates it should be!");
		}
		else if(filterByNotScheduled.equalsIgnoreCase("f") || filterByNotScheduled.isEmpty())
		{
			if(adminSearchPage.isNotScheduledChecked())
			{
				adminSearchPage.clickNotScheduledCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isNotScheduledChecked(), "Not Scheduled filtered IS checked but data indicates it should NOT be!");
		}
		
		//set filter for SCHEDULED based on TestData.xlsx
		if(filterByScheduled.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isScheduledChecked())
			{
				adminSearchPage.clickScheduledCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isScheduledChecked(), "Scheduled filter NOT checked but data indicates it should be!");
		}
		else if(filterByScheduled.equalsIgnoreCase("f") || filterByScheduled.isEmpty())
		{
			if(adminSearchPage.isScheduledChecked())
			{
				adminSearchPage.clickScheduledCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isScheduledChecked(), "Scheduled filtered IS checked but data indicates it should NOT be!");
		}
		
		//set filter for PAID based on TestData.xlsx
		if(filterByPaid.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isPaidChecked())
			{
				adminSearchPage.clickPaidCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isPaidChecked(), "Paid filter NOT checked but data indicates it should be!");
		}
		else if(filterByPaid.equalsIgnoreCase("f") || filterByPaid.isEmpty())
		{
			if(adminSearchPage.isPaidChecked())
			{
				adminSearchPage.clickPaidCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isPaidChecked(), "Paid filtered IS checked but data indicates it should NOT be!");
		}
		
		//set filter for ERROR based on TestData.xlsx
		if(filteredByError.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isErrorChecked())
			{
				adminSearchPage.clickErrorCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isErrorChecked(), "Error filter NOT checked but data indicates it should be!");
		}
		else if(filteredByError.equalsIgnoreCase("f") || filteredByError.isEmpty())
		{
			if(adminSearchPage.isErrorChecked())
			{
				adminSearchPage.clickErrorCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isErrorChecked(), "Error filtered IS checked but data indicates it should NOT be!");
		}
		
		//set filter for CANCELED based on TestData.xlsx
		if(filterByCanceled.equalsIgnoreCase("t"))
		{
			if(!adminSearchPage.isCanceledChecked())
			{
				adminSearchPage.clickCanceledCheckBox();
			}
			
			Assert.assertTrue(adminSearchPage.isErrorChecked(), "Error filter NOT checked but data indicates it should be!");
		}
		else if(filterByCanceled.equalsIgnoreCase("f") || filterByCanceled.isEmpty())
		{
			if(adminSearchPage.isCanceledChecked())
			{
				adminSearchPage.clickCanceledCheckBox();
			}
			
			Assert.assertFalse(adminSearchPage.isCanceledChecked(), "Error filtered IS checked but data indicates it should NOT be!");
		}
		
		adminSearchPage.clickSearchButton();
		
		//verify data

	}
	
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
	
	
	
}
