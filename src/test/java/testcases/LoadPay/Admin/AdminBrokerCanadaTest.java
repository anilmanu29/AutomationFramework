package testcases.LoadPay.Admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Admin.AdminHomePage;
import pages.LoadPay.Admin.AdminLogin;
import testcases.LoadPay.Broker.BrokerRegisterCanadaTest;

public class AdminBrokerCanadaTest extends TestBase
{
	AdminHomePage h;
	AdminLogin a;
	Select s; 
	
	public AdminBrokerCanadaTest()
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
	
	@Test(priority=4)
	public void Home() throws IOException, AWTException, InterruptedException
	{
	h.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData",priority=5)
	public void adminLogin(String Username,String pass) throws IOException, InterruptedException, AWTException
	{		
		a.adminUserPass(Username, pass);
		Thread.sleep(1000);
		a.adminLogin();
		Thread.sleep(1000);
		a.ClickOnCustomersTab();
		Thread.sleep(1000);
		a.ClickOnSearchBox(BrokerRegisterCanadaTest.emailid);
		Thread.sleep(1000);
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
