package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.HandshakewithRTFBroker;
import util.TestUtil;

public class HandshakewithRTFBrokerTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	Select typeofentity;
	HandshakewithRTFBroker handshakertfbrokeobj;

	/*-------Initializing driver---------*/
	public HandshakewithRTFBrokerTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokerloginobj = new BrokerLoginPage();
		handshakertfbrokeobj = new HandshakewithRTFBroker();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getRTFLogindata")
	public void verifyRTFLogin(String username, String password) throws IOException, InterruptedException {
		handshakertfbrokeobj.accesRTFAplication();
		handshakertfbrokeobj.setUsername(username);
		handshakertfbrokeobj.setPassword(password);
		handshakertfbrokeobj.clickSubmitButton();
	}

	@Test(dependsOnMethods = "verifyRTFLogin")
	public void verifyNavigationtoLoadpaylink() throws InterruptedException {
		Assert.assertTrue(handshakertfbrokeobj.getLoadPaylink().isDisplayed(), "LOADPAY link NOT found!");
		handshakertfbrokeobj.clickLoadPaylink();
	}

	@Test(dependsOnMethods = "verifyNavigationtoLoadpaylink")
	public void verifyUnliknMyLoadPayAccount() throws InterruptedException, IOException {
		handshakertfbrokeobj.clickUnlinkMyLoadPayAccount();
		Assert.assertTrue(handshakertfbrokeobj.getLoginbutton().isDisplayed(), "Login button NOT found!");
		Assert.assertTrue(handshakertfbrokeobj.getSignUpTodaybutton().isDisplayed(), "SignUp Today button NOT found!");
		handshakertfbrokeobj.clickLoginButton();

	}

	@Test(dataProvider = "getStagingBrokerLoginData", dependsOnMethods = "verifyUnliknMyLoadPayAccount")
	public void verifyBrokerLogin(String brokeremail, String password) throws InterruptedException, IOException {
		handshakertfbrokeobj.setBrokerUserName(brokeremail);
		handshakertfbrokeobj.setBrokerPassword(password);
		handshakertfbrokeobj.clickBrokerLoginButton();
		Assert.assertTrue(handshakertfbrokeobj.getUnlinkMyLoadPayAccountbutton().isDisplayed(),
				"Account Succesfully did not hand shake");
	}

}
