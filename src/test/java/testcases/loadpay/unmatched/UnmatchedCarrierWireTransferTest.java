package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedCarrierWireTransfer;

public class UnmatchedCarrierWireTransferTest extends TestBase {

	UnmatchedCarrierWireTransfer uct;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierWireTransferTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {

		initialization();
		uct = new UnmatchedCarrierWireTransfer();

	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test()
	public void carrierPaymenowWirTransfer() throws InterruptedException {
		uct.carrierLogin();
		uct.getAmount();
		uct.clickPaymenow();
		uct.getwiretransferAmount();
		uct.clickSelectButton();
		uct.clickConfirmButton();
		uct.clickPaidTab();
		uct.gettotalpaiyAmount();
		uct.verifywiretransfer();
	}

}
