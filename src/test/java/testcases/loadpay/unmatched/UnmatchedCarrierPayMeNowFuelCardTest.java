package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.unmatched.UnmatchedCarrierPaymeNowFuelCard;
import util.TestUtil;

public class UnmatchedCarrierPayMeNowFuelCardTest extends TestBase {

	UnmatchedCarrierPaymeNowFuelCard UnmatchedCarrierFuelCard;
	CarrierLoginPage carrierLoginPage;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierPayMeNowFuelCardTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		UnmatchedCarrierFuelCard = new UnmatchedCarrierPaymeNowFuelCard();
		wait = new WebDriverWait(driver, 30);
		carrierLoginPage = new CarrierLoginPage();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData")
	public void carrierPaymenowFuelCard(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {

		UnmatchedCarrierFuelCard.carrierLogin();
		carrierLoginPage.closePaymeNowPopUp();

		UnmatchedCarrierFuelCard.clickPaymenow();

		/*
		 * cfot.clickPaymenowicon();
		 */
		UnmatchedCarrierFuelCard.clickSelectButton();

		UnmatchedCarrierFuelCard.clickaddnewcard();

		UnmatchedCarrierFuelCard.clickfleetone();

		UnmatchedCarrierFuelCard.input_accountnbr(fleet_accountnbr);

		UnmatchedCarrierFuelCard.clicksubmit();

		UnmatchedCarrierFuelCard.clickfuelcardsubmit();

		UnmatchedCarrierFuelCard.clickConfirmButton();

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.closePaymeNowPopUp();
		}

		UnmatchedCarrierFuelCard.clickPaidTab();

		UnmatchedCarrierFuelCard.clickpaymenowtab();

		UnmatchedCarrierFuelCard.clickPaymenow();

		UnmatchedCarrierFuelCard.clickSelectButton();

		UnmatchedCarrierFuelCard.clickaddnewcard();

		UnmatchedCarrierFuelCard.clickFTS();

		UnmatchedCarrierFuelCard.input_accountnbr(fts_accountnbr);

		UnmatchedCarrierFuelCard.clicksubmit();

		UnmatchedCarrierFuelCard.clickfuelcardsubmit();

		UnmatchedCarrierFuelCard.clickConfirmButton();
	}

}