package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.HandshakewithRTFCarrier;
import util.TestUtil;

public class HandshakewithRTFCarrierTest extends TestBase {
	CarrierLoginPage loginPage;
	HandshakewithRTFCarrier rtfcarrier;
	String carrierUserName, carrierPassword, rtfUsername, rtfPassword;

	public HandshakewithRTFCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		rtfcarrier = new HandshakewithRTFCarrier();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getStagingCarrierLoginData")
	public void getCarrierData(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;
	}

	@Test(dataProvider = "getLoginHandshakewithRTF_CarrierDa", dependsOnMethods = "getCarrierData")
	public void RTFLogin(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("rtfCarrierURL"));
		rtfUsername = user;
		rtfPassword = pass;

		rtfcarrier.RTFCarrierlogin(rtfUsername, rtfPassword);
		rtfcarrier.clickAccount();

		// if not linked already, then link the account
		if (rtfcarrier.Click_Login.isDisplayed()) {
			rtfcarrier.click_LoginButton();
			rtfcarrier.Carrierlogin(carrierUserName, carrierPassword);
		}

		// unlink account
		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.clickYesToUnlink();
		rtfcarrier.click_LoginButton();

		rtfcarrier.Carrierlogin(carrierUserName, carrierPassword);

		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.clickYesToUnlink();
		rtfcarrier.clickRtflogout();
	}
}
