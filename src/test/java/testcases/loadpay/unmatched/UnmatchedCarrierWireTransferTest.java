package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedCarrierWireTransfer;
import util.TestUtil;

public class UnmatchedCarrierWireTransferTest extends TestBase {

	UnmatchedCarrierWireTransfer uct;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierWireTransferTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		uct = new UnmatchedCarrierWireTransfer();
		wait = new WebDriverWait(driver, 30);
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
