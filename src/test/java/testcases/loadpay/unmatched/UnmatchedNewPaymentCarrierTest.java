package testcases.loadpay.unmatched;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.unmatched.UnmatchedNewPaymentCarrier;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedNewPaymentCarrierTest extends TestBase {
	UnmatchedNewPaymentCarrier loginPage;

	public UnmatchedNewPaymentCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new UnmatchedNewPaymentCarrier();
	}

	@Test()
	public void loginTest() throws InterruptedException {
		loginPage.carrierfirstLogin();
		loginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		loginPage.clickClose();
		loginPage.clickPaymenow();
		loginPage.clickcollapseDetails();

	}
}
