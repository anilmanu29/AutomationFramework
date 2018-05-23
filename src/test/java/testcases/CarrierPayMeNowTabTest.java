package testcases;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CarrierLoginPage;
import pages.CarrierNotification;
import pages.CarrierPayMeNowTab;

public class CarrierPayMeNowTabTest extends TestBase {

	CarrierPayMeNowTab cp;
	CarrierLoginPage lp;

	
	/*-------Initializing driver---------*/
	
	public CarrierPayMeNowTabTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		lp= new CarrierLoginPage();
		cp = new CarrierPayMeNowTab();	
		
	}
	
	
	@Test(dataProvider="getCarrierLoginData", priority=20)
	public void loginCarrier(String un, String pwd) throws InterruptedException
	{
		lp=new CarrierLoginPage();
		lp.Carrierlogin(un, pwd);
			
	}
	
	@Test(priority=21)
	public void carrierPaymeNow() throws InterruptedException
	{
		cp.clickPaymenow();
		Thread.sleep(1000);
		cp.days();
		Thread.sleep(1000);
		cp.Amount();
		Thread.sleep(1000);
		cp.Payer();
		Thread.sleep(1000);
		cp.Invoice();
		Thread.sleep(1000);
		cp.LoadID();
		Thread.sleep(1000);
		cp.ScheduledDate();
		Thread.sleep(2000);
	}

}
