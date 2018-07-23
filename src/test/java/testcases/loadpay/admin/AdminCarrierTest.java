package testcases.loadpay.admin;
import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import testcases.loadpay.carrier.CarrierRegisterTest;

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
	
	@Test(description = "Switch to admin URL")
	public void Home() throws IOException, AWTException, InterruptedException
	{
	log.info(BrokerPaymentforUnmatchedCarrierTest.umemail);
	h.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData",dependsOnMethods = "Home")
	public void adminLogin(String Username,String pass) throws IOException, InterruptedException, AWTException
	{		
		a.adminUserPass(Username, pass);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		a.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		a.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		if(BrokerPaymentforUnmatchedCarrierTest.umemail!=null)
		{
			a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.umemail);
		}
		else
		{
		a.ClickOnSearchBox(CarrierRegisterTest.email);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		}
		a.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		a.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		a.StatusIDDropDown();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		a.UpdateButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		a.AdminLogOut();
	
	}	
	
}
