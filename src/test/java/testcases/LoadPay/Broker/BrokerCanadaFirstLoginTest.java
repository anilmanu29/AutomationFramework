package testcases.LoadPay.Broker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerCanadaFirstLogin;


public class BrokerCanadaFirstLoginTest extends TestBase{
	BrokerCanadaFirstLogin loginPage;
	
	public BrokerCanadaFirstLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new BrokerCanadaFirstLogin();	
	}

	@Test(priority=6)
	public void loginTest() throws InterruptedException
	{
		loginPage.brokerfirstLogin();
		Thread.sleep(2000);
	
	}

}
