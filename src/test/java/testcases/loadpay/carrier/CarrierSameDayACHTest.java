package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
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
		wait = new WebDriverWait(driver, 30);
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

		cs.getsamedayAmount();

		cs.clickSelectButton();

		cs.clickConfirmButton();

		cs.clickPaidTab();

		cs.gettotalpaiyAmount();

		cs.verifySamedayach();

	}

}
