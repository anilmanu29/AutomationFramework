package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerNewPayment;
import pages.CarrierLoginPage;
import pages.CarrierPayMeNowTab;
import pages.CarrierSchedulePayment;

public class CarrierSchedulePaymentTest extends TestBase{

	CarrierPayMeNowTab cp;
	CarrierLoginPage lp;
	CarrierSchedulePayment cs;
	String invoice;
	BrokerNewPayment bp;
	

	/*-------Initializing driver---------*/
	
	public CarrierSchedulePaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		lp= new CarrierLoginPage();
		cp = new CarrierPayMeNowTab();
		cs= new CarrierSchedulePayment();
		bp= new BrokerNewPayment();
		
	}
	
	
	@Test(dataProvider="getCarrierLoginData", priority=19)
	public void loginCarrier(String un, String pwd) throws InterruptedException
	{
		lp=new CarrierLoginPage();
		lp.Carrierlogin(un, pwd);
			
	}
	
	@Test(priority=20)
	public void carrierPaymeNow() throws InterruptedException
	{
		cp.clickPaymenow();
		Thread.sleep(2000);
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(2000);
		cs.clickcarrierPayment(ShipperSchedulePaymentTest.invoice);
		System.out.println(BrokerNewPaymentTest.invoice);
		Thread.sleep(2000);
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(1000);
		cs.clickScheduledPayments(ShipperSchedulePaymentTest.invoice);
		Thread.sleep(5000);
			
}	
}
