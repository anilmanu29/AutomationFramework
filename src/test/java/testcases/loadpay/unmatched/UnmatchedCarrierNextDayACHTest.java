package testcases.loadpay.unmatched;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.unmatched.UnmatchedCarrierNextDayACH;

public class UnmatchedCarrierNextDayACHTest extends TestBase {

	UnmatchedCarrierNextDayACH ns;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierNextDayACHTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		ns = new UnmatchedCarrierNextDayACH();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

/*	@Test(dataProvider = "getCarrierLoginData", priority = 32)
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}*/

	@Test()
	public void carrierPaymenowNextDAYACH() throws InterruptedException {
		ns.carrierLogin();
		Thread.sleep(2000);		
		ns.getAmount();
		Thread.sleep(2000);
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
