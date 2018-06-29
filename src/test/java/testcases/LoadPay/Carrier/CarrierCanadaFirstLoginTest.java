package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Carrier.CarrierCanadaFirstLogin;

public class CarrierCanadaFirstLoginTest extends TestBase{
	CarrierCanadaFirstLogin loginPage;
	
	public CarrierCanadaFirstLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierCanadaFirstLogin();	
	}

	@Test(priority=11)
	public void loginTest() throws InterruptedException
	{
		loginPage.carrierfirstLogin();
		Thread.sleep(2000);
	
	}


}
