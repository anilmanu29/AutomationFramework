package testcases.loadpay.carrier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;

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
	
	
	@Test(dataProvider="getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException
	{
		lp=new CarrierLoginPage();
		lp.Carrierlogin(un, pwd);
			
	}
	
	@Test(dependsOnMethods = {"loginCarrier"})
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
