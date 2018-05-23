package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CarrierFirstLogin;
import pages.CarrierFirstLoginSameDay;

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
