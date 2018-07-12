package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;

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

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dependsOnMethods = "loginTest")
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
