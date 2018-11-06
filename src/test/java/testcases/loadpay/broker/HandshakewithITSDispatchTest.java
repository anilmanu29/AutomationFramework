package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerDisableCopyPasteConfirmBankAccount;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerRegisterCanada;
import pages.loadpay.broker.HandshakewithITSDispatch;
import util.TestUtil;

public class HandshakewithITSDispatchTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	BrokerRegisterCanada brokerregisterobj;
	BrokerDisableCopyPasteConfirmBankAccount brokerdisablecopypasteconfirmbankaccountobj;
	Select typeofentity;
	HandshakewithITSDispatch handshakeitsdispatchobj;

	/*-------Initializing driver---------*/
	public HandshakewithITSDispatchTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws AWTException, IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokerloginobj = new BrokerLoginPage();
		brokerregisterobj = new BrokerRegisterCanada();
		brokerdisablecopypasteconfirmbankaccountobj = new BrokerDisableCopyPasteConfirmBankAccount();
		handshakeitsdispatchobj = new HandshakewithITSDispatch();
	}

	@Test(dataProvider = "getITSDispatchLogindata")
	public void verifyITSDispatchLogin(String accountid, String username, String password)
			throws IOException, InterruptedException {
		// login to ITSDispatch
		handshakeitsdispatchobj.accesITSDispatchAplication();
		handshakeitsdispatchobj.setAccountNameField(accountid);
		handshakeitsdispatchobj.setUsername(username);
		handshakeitsdispatchobj.setPassword(password);
		handshakeitsdispatchobj.clickGoButton();
		handshakeitsdispatchobj.clickCloseButton();
		Assert.assertTrue(handshakeitsdispatchobj.getLoadPayImage().isDisplayed(), "LoadPay Image NOT Found!");
	}

	@Test(dataProvider = "getStagingBrokerLoginData", dependsOnMethods = "verifyITSDispatchLogin")
	public void verifyLoadPayAccount(String lpusername, String lppassword) throws IOException, InterruptedException {
		// verify loadpay account pop up
		handshakeitsdispatchobj.clickLoadPayImage();
		Assert.assertTrue(handshakeitsdispatchobj.getLoadPayLoginButton().isDisplayed(), "Login button NOT found!");
		Assert.assertTrue(handshakeitsdispatchobj.getLoadPayCancelButton().isDisplayed(), "Cancel button NOT found!");
		Assert.assertTrue(handshakeitsdispatchobj.getLoadPayLogo().isDisplayed(), "Load Pay logo NOT found!");
		handshakeitsdispatchobj.clickLoadPayLoginButton();
		handshakeitsdispatchobj.switchtoNewTab(1);
		handshakeitsdispatchobj.setLoadPayUsername(lpusername);
		handshakeitsdispatchobj.setLoadPayPassword(lppassword);
		handshakeitsdispatchobj.clickLogin();
		handshakeitsdispatchobj.switchtoNewTab(0);
		Assert.assertTrue(handshakeitsdispatchobj.getDisconnectButton().isDisplayed(), "Disconnect button NOT found!");
		handshakeitsdispatchobj.clickDisconnectbutton();
	}

	@Test(dependsOnMethods = "verifyLoadPayAccount")
	public void verifyLogOutofITSDispatch() throws InterruptedException {
		// verify logout
		handshakeitsdispatchobj.clickLogOutButton();
	}
}
