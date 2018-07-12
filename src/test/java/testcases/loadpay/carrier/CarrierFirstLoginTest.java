package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierFirstLogin;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;


public class CarrierFirstLoginTest extends TestBase{
	CarrierFirstLogin loginPage;
	
	public CarrierFirstLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierFirstLogin();	
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
