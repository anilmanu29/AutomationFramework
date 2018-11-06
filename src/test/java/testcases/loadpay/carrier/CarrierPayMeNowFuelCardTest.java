package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import util.TestUtil;

public class CarrierPayMeNowFuelCardTest extends TestBase {

	CarrierPaymeNowFuelCard cfot;
	CarrierLoginPage loginPage;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/
	public CarrierPayMeNowFuelCardTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		cfot = new CarrierPaymeNowFuelCard();
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

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData", dependsOnMethods = { "loginTest" })
	public void carrierPaymenowFuelCard(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {

		cfot.clickPaymenow();
		cfot.clickSelectButton();
		cfot.clickaddnewcard();
		cfot.clickfleetone();
		cfot.input_accountnbr(fleet_accountnbr);
		cfot.clicksubmit();
		cfot.clickfuelcardsubmit();
		cfot.clickConfirmButton();

		if (loginPage.getDonotshowagaincheckbox().isDisplayed()) {
			loginPage.getDonotshowagaincheckbox().click();
			loginPage.getPayMeNowPopupSaveButton().click();
		}

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
	}

}
