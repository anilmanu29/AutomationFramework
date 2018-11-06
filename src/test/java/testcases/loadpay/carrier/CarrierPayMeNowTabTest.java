package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;
import util.TestUtil;

public class CarrierPayMeNowTabTest extends TestBase {

	CarrierPayMeNowTab cp;
	CarrierLoginPage lp;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/

	public CarrierPayMeNowTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		lp = new CarrierLoginPage();
		cp = new CarrierPayMeNowTab();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = un;
			carrierPassword = pwd;
		}

		lp = new CarrierLoginPage();
		lp.Carrierlogin(carrierUsername, carrierPassword);

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
