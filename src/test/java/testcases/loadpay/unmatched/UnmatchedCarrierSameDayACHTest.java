package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedCarrierSameDAYACH;
import util.TestUtil;

public class UnmatchedCarrierSameDayACHTest extends TestBase {

	UnmatchedCarrierSameDAYACH usdach;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierSameDayACHTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		usdach = new UnmatchedCarrierSameDAYACH();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test()
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		Thread.sleep(2000);
		usdach.carrierLogin();
		Thread.sleep(2000);
		usdach.getAmount();
		usdach.clickPaymenow();
		usdach.getsamedayAmount();
		usdach.clickSelectButton();
		usdach.clickConfirmButton();
		usdach.clickPaidTab();
		usdach.gettotalpaiyAmount();
		usdach.verifySamedayach();
		//
	}

}
