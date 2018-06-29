package testcases.LoadPay.Carrier;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Carrier.CarrierLoginPage;

public class CarrierLoginTest extends TestBase{
	CarrierLoginPage loginPage;
	
	public CarrierLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		loginPage = new CarrierLoginPage();	
	}

	@Test(dataProvider="getCarrierLoginData",priority=16)
	public void loginTest(String user,String pass) throws InterruptedException
	{
		loginPage.Carrierlogin(user, pass);
	
		Thread.sleep(5000);
	}
	
	
	
}
