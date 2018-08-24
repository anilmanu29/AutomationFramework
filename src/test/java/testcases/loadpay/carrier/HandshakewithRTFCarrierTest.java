package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
		rtfcarrier.click_LoginButton();

	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "RTFLogin")
	public void LoadloginTest(String user, String pass) throws InterruptedException {
		/*
		 * driver.get(prop.getProperty("url")); rtfcarrier.click_LoginButton();
		 */
		carrierUserName = user;
		carrierPassword = pass;

		loginPage.Carrierlogin(carrierUserName, carrierPassword);
	}

	@Test(dependsOnMethods = "RTFLogin")
	public void clickUnlinkMyUploadAccount() throws InterruptedException {
		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.click_alert();
	}

	@Test(dependsOnMethods = "clickUnlinkMyUploadAccount")
	public void ClickLoginButton() throws InterruptedException {
		rtfcarrier.click_LoginButton();
	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "ClickLoginButton")
	public void loginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;

		loginPage.Carrierlogin(carrierUserName, carrierPassword);

	}

	@Test(dependsOnMethods = "loginTest")
	public void clickUnlinkMyUploadAccount2() throws InterruptedException {

		rtfcarrier.clickUnlinkMyUploadAccount();
		rtfcarrier.click_alert();

	}

	@Test(dependsOnMethods = "clickUnlinkMyUploadAccount2")
	public void clickRTFlogout() throws InterruptedException {

		rtfcarrier.clickRtflogout();

	}

	public void verifyHandshakewithRTFDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(rtfcarrier.Click_RTFSubmit.isDisplayed(), "RTFSubmit button not found");
		Assert.assertTrue(rtfcarrier.Click_Login.isDisplayed(), "Carrier login button not found");
		Assert.assertTrue(rtfcarrier.Click_Login.isDisplayed(), "RTF Login Column not found");
		Assert.assertTrue(rtfcarrier.Click_UnlinkMyUploadAccount.isDisplayed(),
				"UnlinkMyUploadAccount button not found");
		Assert.assertTrue(rtfcarrier.Click_accept.isDisplayed(), "alert accept button not found");
		Assert.assertTrue(rtfcarrier.Click_rtflogout.isDisplayed(), "RTF Logout button not found");

	}

}
