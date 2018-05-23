package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.BrokerLoginPage;
import pages.CarrierLoginPage;
import pages.CarrierSameDAYACH;

public class CarrierSameDayACHTest extends TestBase {

	CarrierSameDAYACH cs;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierSameDayACHTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		cs = new CarrierSameDAYACH();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData", priority = 42)
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(priority = 43)
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		cs.getAmount();
		cs.clickPaymenow();
		Thread.sleep(3000);
		cs.getsamedayAmount();
		Thread.sleep(3000);
		cs.clickSelectButton();
		Thread.sleep(3000);
		cs.clickConfirmButton();
		Thread.sleep(3000);
		cs.clickPaidTab();
		Thread.sleep(3000);
		cs.gettotalpaiyAmount();
		Thread.sleep(3000);
		cs.verifySamedayach();
		Thread.sleep(3000);
		
	}

}
