package testcases.LoadPay.Admin;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Admin.AdminHomePage;
import pages.LoadPay.Admin.AdminLogin;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.LoadPay.Carrier.CarrierRegisterTest;

public class AdminCarrierTest extends TestBase
{
	AdminHomePage h;
	AdminLogin a;
	Select s; 
	
	public AdminCarrierTest()
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
	
	@Test(priority=11)
	public void Home() throws IOException, AWTException, InterruptedException
	{
	System.out.println(BrokerPaymentforUnmatchedCarrierTest.umemail);
	h.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData",priority=12)
	public void adminLogin(String Username,String pass) throws IOException, InterruptedException, AWTException
	{		
		a.adminUserPass(Username, pass);
		Thread.sleep(1000);
		a.adminLogin();
		Thread.sleep(1000);
		a.ClickOnCustomersTab();
		Thread.sleep(1000);
		if(BrokerPaymentforUnmatchedCarrierTest.umemail!=null)
		{
			a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.umemail);
		}
		else
		{
		a.ClickOnSearchBox(CarrierRegisterTest.email);
		Thread.sleep(1000);
		}
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
