package testcases.loadpay.unmatched;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedCarrierPaymeNowFuelCard;

public class UnmatchedCarrierPayMeNowFuelCardTest extends TestBase {

	UnmatchedCarrierPaymeNowFuelCard UnmatchedCarrierFuelCard;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierPayMeNowFuelCardTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		UnmatchedCarrierFuelCard = new UnmatchedCarrierPaymeNowFuelCard();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData")
	public void carrierPaymenowFuelCard(String fleet_accountnbr, String fts_accountnbr) throws InterruptedException {

		UnmatchedCarrierFuelCard.carrierLogin();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickPaymenow();
		Thread.sleep(2000);
		/*
		 * cfot.clickPaymenowicon(); Thread.sleep(2000);
		 */
		UnmatchedCarrierFuelCard.clickSelectButton();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickaddnewcard();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickfleetone();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.input_accountnbr(fleet_accountnbr);
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clicksubmit();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickfuelcardsubmit();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickConfirmButton();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickPaidTab();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickpaymenowtab();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickPaymenow();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickSelectButton();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickaddnewcard();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickFTS();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.input_accountnbr(fts_accountnbr);
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clicksubmit();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickfuelcardsubmit();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickConfirmButton();
		Thread.sleep(2000);
		UnmatchedCarrierFuelCard.clickPaidTab();
		Thread.sleep(2000);
		/*
		 * cw.gettotalpaiyAmount(); Thread.sleep(2000); cw.verifywiretransfer();
		 */
	}

}
