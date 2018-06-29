package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Carrier.CarrierFirstLoginNextDayACH;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;


public class CarrierFirstLoginNextDayACHTest  extends TestBase{
	CarrierFirstLoginNextDayACH loginPage;
	
	public CarrierFirstLoginNextDayACHTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierFirstLoginNextDayACH();	
	}

	@Test(priority=31)
	public void loginTest() throws InterruptedException
	{
		loginPage.carrierfirstLogin();
		loginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		loginPage.clickClose();	
	} 

}
