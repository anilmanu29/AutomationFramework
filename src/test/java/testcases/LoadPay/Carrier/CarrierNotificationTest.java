package testcases.LoadPay.Carrier;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Carrier.CarrierLoginPage;
import pages.LoadPay.Carrier.CarrierNotification;


public class CarrierNotificationTest extends TestBase {
	
	CarrierNotification cn;
	CarrierLoginPage cl;
	String  payment_status = "Verified";
	String 	 invoice;
	
	/*-------Initializing driver---------*/
	
	public CarrierNotificationTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		cn = new CarrierNotification ();	
		cl= new CarrierLoginPage();
	}
	
	
	@Test(dataProvider="getCarrierLoginData", priority=14)
	public void loginCarrier(String un, String pwd) throws InterruptedException
	{
		cl=new CarrierLoginPage();
		cl.Carrierlogin(un, pwd);
		

	}
	
	@Test(priority=15)
	public void carrierNotification() throws InterruptedException
	{
		cn=new CarrierNotification();
		cn.clickNotifications();
		cn.carrierInvoice();
		cl.CarrierLogout();
		
		

	}


}
