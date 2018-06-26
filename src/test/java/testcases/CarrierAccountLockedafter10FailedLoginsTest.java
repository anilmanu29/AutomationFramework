package testcases;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.Carrierlockedaccountcanbeunlockedbyadmin;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.CarrierAccountLockedafter10FailedLogins;
import pages.CarrierLoginPage;

public class CarrierAccountLockedafter10FailedLoginsTest extends TestBase
{
	CarrierAccountLockedafter10FailedLogins Calck;
	AdminHomePage h;
	AdminLogin a;
	Select s; 
	public static String aemail;
	CarrierLoginPage loginPage;
	
	public CarrierAccountLockedafter10FailedLoginsTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException, AWTException 
		{
		initialization();
		h= new AdminHomePage();	
		a= new AdminLogin();
		Calck= new CarrierAccountLockedafter10FailedLogins();
		loginPage = new CarrierLoginPage();
		}
	
	@Test(dataProvider="getCarrierlockedaccountAdminUnlockData",priority=1)
	public void loginTest(String user,String pass, String wrongpass) throws InterruptedException
	{
		aemail = user;
		Calck.Carrierloginlock(user, pass, wrongpass);
		//verifyCarrierAccLockTabElementsDisplayed();
	
		Thread.sleep(5000);
		
	}
	
	public void verifyCarrierAccLockTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(Calck.UserName.isDisplayed(), "username Column not found");
		Assert.assertTrue(Calck.Password.isDisplayed(), "Password Column not found");
		Assert.assertTrue(Calck.loginBtn.isDisplayed(),
				"loginBtn Column not found");
		Assert.assertTrue(Calck.btn_logout.isDisplayed(),
				"btn_logout Column not found");
		
		
		
	}


	
	
}
