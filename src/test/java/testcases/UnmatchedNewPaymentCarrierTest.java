package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.UnmatchedNewPaymentCarrier;

public class UnmatchedNewPaymentCarrierTest extends TestBase{
	UnmatchedNewPaymentCarrier loginPage;
	
	public UnmatchedNewPaymentCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new UnmatchedNewPaymentCarrier();	
	}

	@Test(priority=41)
	public void loginTest() throws InterruptedException
	{
		loginPage.carrierfirstLogin();
		loginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		Thread.sleep(2000);
		loginPage.clickClose();
		Thread.sleep(6000);
		loginPage.clickPaymenow();
		Thread.sleep(2000);
		/*loginPage.clickcarrierPayment();
		Thread.sleep(2000);
		loginPage.clickScheduledPayments();
		Thread.sleep(5000);*/
		loginPage.clickcollapseDetails();
		Thread.sleep(5000);
	
		
	}
	}


