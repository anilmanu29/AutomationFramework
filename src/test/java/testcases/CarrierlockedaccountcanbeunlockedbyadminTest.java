package testcases;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.Carrierlockedaccountcanbeunlockedbyadmin;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.CarrierLoginPage;

public class CarrierlockedaccountcanbeunlockedbyadminTest extends TestBase
{
	Carrierlockedaccountcanbeunlockedbyadmin Claua;
	AdminHomePage h;
	AdminLogin a;
	Select s; 
	public static String aemail;
	CarrierLoginPage loginPage;
	
	public CarrierlockedaccountcanbeunlockedbyadminTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException, AWTException 
		{
		initialization();
		h= new AdminHomePage();	
		a= new AdminLogin();
		Claua= new Carrierlockedaccountcanbeunlockedbyadmin();
		loginPage = new CarrierLoginPage();
		}
	
	@Test(dataProvider="getCarrierlockedaccountAdminUnlockData",priority=86)
	public void loginTest(String user,String pass, String wrongpass) throws InterruptedException
	{
		aemail = user;
		Claua.Carrierloginlock(user, pass, wrongpass);
	
		Thread.sleep(5000);
	}
	
	@Test(priority=87)
	public void Home() throws IOException, AWTException, InterruptedException
	{
	System.out.println(BrokerPaymentforUnmatchedCarrierTest.umemail);
	h.AdminURL();
	}
	
	@Test(dataProvider="getAdminLoginData",priority=88)
	public void adminLogin(String Username,String pass) throws IOException, InterruptedException, AWTException
	{		
		a.adminUserPass(Username, pass);
		Thread.sleep(1000);
		a.adminLogin();
		Thread.sleep(1000);
		a.ClickOnCustomersTab();
		Thread.sleep(1000);
		/*if(BrokerPaymentforUnmatchedCarrierTest.umemail!=null)
		{
			a.ClickOnSearchBox(BrokerPaymentforUnmatchedCarrierTest.umemail);
		}
		else*/
		{
		a.ClickOnSearchBox(aemail);
		Thread.sleep(1000);
		}
		a.ClickOnSearchButton();
		Thread.sleep(1000);
		
		a.clickCustomerId();
		Thread.sleep(1000);
		
		a.clickeditloginuser();
		Thread.sleep(1000);

		a.clickCancelLockout();
		Thread.sleep(1000);
		
		a.AdminLogOut();
	
	}	
	
	@Test(dataProvider="getCarrierLoginData",priority=89)
	public void loginTest(String user,String pass) throws InterruptedException
	{
		
		driver.get(prop.getProperty("url"));
		loginPage.Carrierlogin(user, pass);
	
		Thread.sleep(5000);
	}
	
	
}
