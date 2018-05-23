package testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AdminHomePage;
import pages.AdminLogin;

public class UnmatchedCarrierAdminNextDayTest  extends TestBase
{
	AdminHomePage h;
	AdminLogin a;
	Select s; 
	
	public UnmatchedCarrierAdminNextDayTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException, AWTException 
		{
		initialization();
		h= new AdminHomePage();	
		a= new AdminLogin();
		}
	
	@Test(priority=29)
	public void Home() throws IOException, AWTException, InterruptedException
	{
	//System.out.println(BrokerPaymentforUnmatchedCarrierTest.umemail);
	h.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData",priority=30)
	public void adminLogin(String Username,String pass) throws IOException, InterruptedException, AWTException
	{		
		a.adminUserPass(Username, pass);
		Thread.sleep(1000);
		a.adminLogin();
		Thread.sleep(1000);
		a.ClickOnCustomersTab();
		Thread.sleep(1000);
		a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.al.get(0));
		
		
//		if(BrokerPaymentforUnmatchedCarrierTest.umemail!=null)
//		{
//			a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.umemail);
//		}
//		else
//		{
//		a.ClickOnSearchBox(CarrierRegisterTest.email);
//		Thread.sleep(1000);
//		}
		a.ClickOnSearchButton();
		Thread.sleep(1000);

		a.DoubleClickID();
		Thread.sleep(1000);

		a.StatusIDDropDown();
		Thread.sleep(1000);

		a.UpdateButton();
		Thread.sleep(1000);

		a.AdminLogOut();
	
	}	 

}
