package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Carrier.CarrierFirstLogin;
import pages.LoadPay.Carrier.CarrierFirstLoginSameDay;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;

public class CarrierFirstLoginSameDayTest extends TestBase{
	CarrierFirstLoginSameDay loginPage;
	
	public CarrierFirstLoginSameDayTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierFirstLoginSameDay();	
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
