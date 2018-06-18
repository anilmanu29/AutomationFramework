package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.BrokerLoginPage;
import pages.CarrierLoginPage;
import pages.CarrierNextDAYACH;
import pages.CarrierSameDAYACH;

public class CarrierNextDAYACHTest extends TestBase {

	CarrierNextDAYACH ns;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierNextDAYACHTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		ns = new CarrierNextDAYACH();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData", priority = 44)
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(priority = 45)
	public void carrierPaymenowNextDAYACH() throws InterruptedException {
		ns.getAmount();
		ns.clickPaymenow();
		Thread.sleep(3000);
		ns.getnextdayAmount();
		Thread.sleep(3000);
		ns.clickSelectButton();
		Thread.sleep(3000);
		ns.clickConfirmButton();
		Thread.sleep(3000);
		ns.clickPaidTab();
		Thread.sleep(3000);
		ns.gettotalpaiyAmount();
		Thread.sleep(3000);
		ns.verifyNextDayach();
		Thread.sleep(3000);
		
	}

}
