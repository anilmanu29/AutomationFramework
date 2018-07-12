package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierFirstLoginSameDay;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

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

	@Test()
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
