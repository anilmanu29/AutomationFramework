package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CarrierFirstLogin;
import pages.CarrierNewPaymentFirstLogin;


public class CarrierNewPaymentFirstLoginTest extends TestBase{
	CarrierNewPaymentFirstLogin loginPage;
	
	public CarrierNewPaymentFirstLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierNewPaymentFirstLogin();	
	}

	@Test(priority=42)
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
