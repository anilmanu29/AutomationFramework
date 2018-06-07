package testcases;
import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import pages.BrokerLoginPage;
import pages.BrokerAvailableCreditTab;
import pages.BrokerOutlook;


public class BrokerAvailableCreditTabTest extends TestBase {

	BrokerAvailableCreditTab brokeravailablecredittab;
	BrokerLoginPage brokerloginpage; 
	BrokerOutlook brokeroutlook;
	BrokerOutlookTest brokeroutlooktest;
	
	/*-------Initializing driver---------*/
	
	public BrokerAvailableCreditTabTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp() throws IOException
	{
		
		initialization();
		brokeravailablecredittab = new BrokerAvailableCreditTab();
		brokerloginpage = new BrokerLoginPage();
		brokeroutlook = new BrokerOutlook();
		brokeroutlooktest = new BrokerOutlookTest();
		
	}
	
	
	@Test(description = "LP-5398 Broker Request Additional Credit Login", dataProvider="getBrokerLoginData", priority=1)
	public void loginBrokerTest(String un, String pwd) throws InterruptedException
	{
		brokerloginpage.Brokerlogin(un, pwd);
					
	}
	
	@Test(description = "LP-5398 Broker Request Additional Credit Credit Tab", priority=2)
	public void ClickBrokerAvailableCreditTabTest() throws InterruptedException
	{
		brokeravailablecredittab.clickAvailableCreditTab();
	}
	@Test(description = "LP-5398 Broker Request Additional Credit Click Credit Button", priority=3)
	public void RequestAdditionalCreditButtonTest() throws InterruptedException
	{
		brokeravailablecredittab.clickRequestAdditionalCreditButton();
	}
	@Test(description = "LP-5398 Broker Request Additional Credit Pop Up", priority=4)
	public void RequestAdditionalCreditPopUpTest() throws InterruptedException
	{
		brokeravailablecredittab.clickCloseButton();
	}
//	@Test(description = "LP-5398 Broker Request Additional Credit Outlook Login", dataProvider = "getoutlookLoginData", priority=5)
//	public void BrokerOutlookTest(String un, String pwd) throws InterruptedException
//	{
//		try {
//			brokeroutlooktest.login(un, pwd);
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
}

