package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.HandshakewithRTFCarrier;

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
		loginPage = new CarrierLoginPage();
		rtfcarrier = new HandshakewithRTFCarrier();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getLoginHandshakewithRTF_CarrierDa")
	public void RTFLogin(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("RTFcarrier"));
		rtfUsername = user;
		rtfPassword = pass;

		rtfcarrier.RTFCarrierlogin(rtfUsername, rtfPassword);
		rtfcarrier.clickAccount();
		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.clickYesToUnlink();
		rtfcarrier.click_LoginButton();
	}

	@Test(dataProvider = "getStagingCarrierLoginData", dependsOnMethods = "RTFLogin")
	public void loginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;
		rtfcarrier.Carrierlogin(carrierUserName, carrierPassword);

		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.clickYesToUnlink();
		rtfcarrier.clickRtflogout();
	}
}
