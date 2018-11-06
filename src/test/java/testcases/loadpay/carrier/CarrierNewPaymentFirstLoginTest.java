package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierNewPaymentFirstLogin;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;
import util.TestUtil;

public class CarrierNewPaymentFirstLoginTest extends TestBase {
	CarrierNewPaymentFirstLogin loginPage;

	public CarrierNewPaymentFirstLoginTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierNewPaymentFirstLogin();
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
	}

}
