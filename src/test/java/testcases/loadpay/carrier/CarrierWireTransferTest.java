package testcases.loadpay.carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierWireTransfer;

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

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);

	}

	@Test(dependsOnMethods = "loginTest")
	public void carrierPaymenowWirTransfer() throws InterruptedException {
		cw.getAmount();

		cw.clickPaymenow();

		cw.getwiretransferAmount();

		cw.clickSelectButton();

		cw.clickConfirmButton();

		cw.clickPaidTab();

		cw.gettotalpaiyAmount();

		cw.verifywiretransfer();
	}

}
