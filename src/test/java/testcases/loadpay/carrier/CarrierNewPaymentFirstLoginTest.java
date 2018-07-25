package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierNewPaymentFirstLogin;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class CarrierNewPaymentFirstLoginTest extends TestBase {
	CarrierNewPaymentFirstLogin loginPage;

	public CarrierNewPaymentFirstLoginTest() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new CarrierNewPaymentFirstLogin();
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
