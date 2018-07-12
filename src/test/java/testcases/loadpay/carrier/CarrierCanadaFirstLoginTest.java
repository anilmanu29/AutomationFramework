package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierCanadaFirstLogin;

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

	@Test()
	public void loginTest() throws InterruptedException
	{
		loginPage.carrierfirstLogin();
		Thread.sleep(2000);
	
	}


}
