package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;

public class CarrierPayMeNowTabTest extends TestBase {

	CarrierPayMeNowTab cp;
	CarrierLoginPage lp;

	/*-------Initializing driver---------*/

	public CarrierPayMeNowTabTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		lp = new CarrierLoginPage();
		cp = new CarrierPayMeNowTab();

	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		lp = new CarrierLoginPage();
		lp.Carrierlogin(un, pwd);

	}

	@Test(dependsOnMethods = { "loginCarrier" })
	public void carrierPaymeNow() throws InterruptedException {
		cp.clickPaymenow();

		cp.days();

		cp.Amount();

		cp.Payer();

		cp.Invoice();

		cp.LoadID();

		cp.ScheduledDate();

	}

}
