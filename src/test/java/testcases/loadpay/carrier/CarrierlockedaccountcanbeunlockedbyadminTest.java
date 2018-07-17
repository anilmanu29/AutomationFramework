package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.Carrierlockedaccountcanbeunlockedbyadmin;

public class CarrierlockedaccountcanbeunlockedbyadminTest extends TestBase
{
	Carrierlockedaccountcanbeunlockedbyadmin Claua;
	AdminHomePage adminHomePage;
	AdminLogin adminLogin;
	CarrierLoginPage loginPage;
	public static String aemail;
	
	public CarrierlockedaccountcanbeunlockedbyadminTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException
	{
		initialization();
		adminHomePage = new AdminHomePage();	
		adminLogin = new AdminLogin();
		Claua= new Carrierlockedaccountcanbeunlockedbyadmin();
		loginPage = new CarrierLoginPage();
	}
	
	@Test(dataProvider = "getCarrierlockedaccountAdminUnlockData")
	public void carrierLoginTest(String user, String pass, String wrongpass) throws InterruptedException
	{
		aemail = user;
		Claua.Carrierloginlock(user, pass, wrongpass);
		Thread.sleep(5000);
	}
	
	@Test(dependsOnMethods = {"carrierLoginTest"})
	public void switchToAdminURL() throws AWTException, InterruptedException
	{
		adminHomePage.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData", dependsOnMethods = {"switchToAdminURL"})
	public void adminCancelLockout(String Username,String pass) throws InterruptedException, AWTException
	{
		adminLogin.adminUserPass(Username, pass);
		Thread.sleep(1000);
		
		adminLogin.adminLogin();
		Thread.sleep(1000);
		
		adminLogin.ClickOnCustomersTab();
		Thread.sleep(1000);

		adminLogin.ClickOnSearchBox(aemail);
		Thread.sleep(1000);
		
		adminLogin.ClickOnSearchButton();
		Thread.sleep(1000);
		
		adminLogin.clickCustomerId();
		Thread.sleep(1000);
		
		adminLogin.clickeditloginuser();
		Thread.sleep(1000);

		adminLogin.clickCancelLockout();
		Thread.sleep(1000);
		
		adminLogin.AdminLogOut();
		Thread.sleep(1000);
	}	
	
	@Test(dataProvider="getCarrierlockedaccountAdminUnlockData", dependsOnMethods = {"adminCancelLockout"})
	public void loginAsCarrierUnlocked(String user, String pass, String wrongpass) throws InterruptedException
	{
		driver.get(super.getProperties().getProperty("url"));
		loginPage.Carrierlogin(user, pass);
		Thread.sleep(3000);
		loginPage.CarrierLogout();
		
	} 
}
