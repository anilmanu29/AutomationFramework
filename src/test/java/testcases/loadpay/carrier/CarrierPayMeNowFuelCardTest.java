package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;

public class CarrierPayMeNowFuelCardTest extends TestBase {

	CarrierPaymeNowFuelCard cfot;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierPayMeNowFuelCardTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		cfot = new CarrierPaymeNowFuelCard();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData", dependsOnMethods = { "loginTest" })
	public void carrierPaymenowFuelCard(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {

		cfot.clickPaymenow();

		/*
		 * cfot.clickPaymenowicon();
		 * 
		 */
		cfot.clickSelectButton();

		cfot.clickaddnewcard();

		cfot.clickfleetone();

		cfot.input_accountnbr(fleet_accountnbr);

		cfot.clicksubmit();

		cfot.clickfuelcardsubmit();

		cfot.clickConfirmButton();

		cfot.clickPaidTab();

		cfot.clickpaymenowtab();

		cfot.clickPaymenow();

		cfot.clickSelectButton();

		cfot.clickaddnewcard();

		cfot.clickFTS();

		cfot.input_accountnbr(fts_accountnbr);

		cfot.clicksubmit();

		cfot.clickfuelcardsubmit();

		cfot.clickConfirmButton();

		cfot.clickPaidTab();

		/*
		 * cw.gettotalpaiyAmount();
		 * 
		 * cw.verifywiretransfer();
		 */
	}

}
