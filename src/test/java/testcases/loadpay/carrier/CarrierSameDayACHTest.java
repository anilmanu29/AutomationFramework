package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import util.TestUtil;

public class CarrierSameDayACHTest extends TestBase {

	CarrierSameDAYACH cs;
	CarrierLoginPage loginPage;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/
	public CarrierSameDayACHTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		cs = new CarrierSameDAYACH();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

	}

	@Test(dependsOnMethods = "loginTest")
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		Thread.sleep(2000);
		cs.getAmount();
		cs.clickPaymenow();

		cs.getsamedayAmount();

		cs.clickSelectButton();

		cs.clickConfirmButton();

		cs.clickPaidTab();

		cs.gettotalpaiyAmount();

		cs.verifySamedayach();

	}

}
