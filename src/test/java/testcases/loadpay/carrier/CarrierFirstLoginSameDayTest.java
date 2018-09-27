package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierFirstLoginSameDay;
import pages.loadpay.carrier.CarrierLoginPage;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class CarrierFirstLoginSameDayTest extends TestBase {
	CarrierFirstLoginSameDay loginPage;
	CarrierLoginPage carrierLoginPage;

	public CarrierFirstLoginSameDayTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new CarrierFirstLoginSameDay();
		carrierLoginPage = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test()
	public void loginTest() throws InterruptedException {
		loginPage.carrierfirstLogin();
		loginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		loginPage.clickClose();

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.closePaymeNowPopUp();
		}
	}

}
