package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerLoginPage;

public class BrokerLoginTest extends TestBase{
	BrokerLoginPage loginPage;
	
	public BrokerLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage = new BrokerLoginPage();	
	}

	@Test(dataProvider="getBrokerLoginData", priority=7)
	public void loginTest(String user,String pass) throws InterruptedException
	{
		loginPage.Brokerlogin(user, pass);
	
		Thread.sleep(5000);
		loginPage.BrokerLogout();
	}
	

}
