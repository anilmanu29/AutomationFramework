package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Carrier.CarrierLoginPage;
import pages.LoadPay.Carrier.CarrierWireTransfer;

public class CarrierWireTransferTest extends TestBase {

	CarrierWireTransfer cw;
	CarrierLoginPage loginPage;

	/*-------Initializing driver---------*/
	public CarrierWireTransferTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new CarrierLoginPage();
		cw = new CarrierWireTransfer();
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData", priority=18)
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(priority=19)
	public void carrierPaymenowWirTransfer() throws InterruptedException {
		cw.getAmount();
		Thread.sleep(2000);
		cw.clickPaymenow();
		Thread.sleep(2000);
		cw.getwiretransferAmount();
		Thread.sleep(2000);
		cw.clickSelectButton();
		Thread.sleep(2000);
		cw.clickConfirmButton();
		Thread.sleep(2000);
		cw.clickPaidTab();
		Thread.sleep(2000);
		cw.gettotalpaiyAmount();
		Thread.sleep(2000);
		cw.verifywiretransfer();
	}

}
