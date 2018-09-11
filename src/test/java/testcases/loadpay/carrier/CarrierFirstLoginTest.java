package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierFirstLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class CarrierFirstLoginTest extends TestBase {
	CarrierFirstLogin carrierFirstLoginPage;
	CarrierLoginPage carrierLoginPage;

	public CarrierFirstLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		carrierFirstLoginPage = new CarrierFirstLogin();
		carrierLoginPage = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test()
	public void loginTest() throws InterruptedException {
		carrierFirstLoginPage.carrierfirstLogin();
		carrierFirstLoginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		carrierFirstLoginPage.clickAcceptCheckbox();
		carrierFirstLoginPage.clickEmailcheckbox();
		carrierFirstLoginPage.clickFinish();
		carrierFirstLoginPage.clickClose();

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.closePaymeNowPopUp();
		}
	}

}
