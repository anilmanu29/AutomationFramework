package testcases.loadpay.unmatched;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedCarrierSameDAYACH;

public class UnmatchedCarrierSameDayACHTest extends TestBase {

	UnmatchedCarrierSameDAYACH usdach;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierSameDayACHTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {

		initialization();
		usdach = new UnmatchedCarrierSameDAYACH();

	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test()
	public void carrierPaymenowSameDayACH() throws InterruptedException {

		usdach.carrierLogin();
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
