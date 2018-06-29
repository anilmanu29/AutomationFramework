package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Carrier.CarrierLoginPage;
import pages.LoadPay.Carrier.CarrierPaymeNowFuelCard;
import pages.LoadPay.Carrier.CarrierWireTransfer;

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
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData", priority=43)
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData",priority=44)
	public void carrierPaymenowFuelCard(String fleet_accountnbr,String fts_accountnbr) throws InterruptedException {
		
		cfot.clickPaymenow();
		Thread.sleep(2000);
		/*cfot.clickPaymenowicon();
		Thread.sleep(2000);*/
		cfot.clickSelectButton();
		Thread.sleep(2000);
		cfot.clickaddnewcard();
		Thread.sleep(2000);
		cfot.clickfleetone();
		Thread.sleep(2000);
		cfot.input_accountnbr(fleet_accountnbr);
		Thread.sleep(2000);
		cfot.clicksubmit();
		Thread.sleep(2000);
		cfot.clickfuelcardsubmit();
		Thread.sleep(2000);
		cfot.clickConfirmButton();
		Thread.sleep(2000);
		cfot.clickPaidTab();
		Thread.sleep(2000);
		cfot.clickpaymenowtab();
		Thread.sleep(2000);
		cfot.clickPaymenow();
		Thread.sleep(2000);
		cfot.clickSelectButton();
		Thread.sleep(2000);
		cfot.clickaddnewcard();
		Thread.sleep(2000);
		cfot.clickFTS();
		Thread.sleep(2000);
		cfot.input_accountnbr(fts_accountnbr);
		Thread.sleep(2000);
		cfot.clicksubmit();
		Thread.sleep(2000);
		cfot.clickfuelcardsubmit();
		Thread.sleep(2000);
		cfot.clickConfirmButton();
		Thread.sleep(2000);
		cfot.clickPaidTab();
		Thread.sleep(2000);
		/*cw.gettotalpaiyAmount();
		Thread.sleep(2000);
		cw.verifywiretransfer();*/
	}

}
