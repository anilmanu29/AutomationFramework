package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerBanking;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerBankingTest extends TestBase {

	BrokerBanking bb;
	BrokerLoginPage bl;
	AdminLogin al;
	public static String acountname;

	/*-------Initializing driver---------*/

	public BrokerBankingTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		bb = new BrokerBanking();
		bl = new BrokerLoginPage();
		al = new AdminLogin();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void brokerLogin(String un, String pwd) throws InterruptedException {
		bl = new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerLogin")
	public void brokerAddNewBusinessBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		bb.clickAccountlink();
		Thread.sleep(1000);
		bb.clickBankingLink();
		Thread.sleep(1000);
		bb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		acountname = bb.enterAccountName(accname);
		Thread.sleep(1000);
		bb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		bb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		bb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		bb.clickSaveButton();
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void verifyBrokerBusinessBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		bb.verifyBankAccount();
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		bb.clickAdminBankingLink();
		Thread.sleep(1000);
		System.out.println(bb.getPennyVerificationAmount());
		Thread.sleep(1000);
		al.AdminLogOut();
		Thread.sleep(1000);
		bb.closeTab();
		Thread.sleep(1000);
		bb.clickVerifyLink();
		Thread.sleep(1000);
		bb.enterAmount();
		Thread.sleep(1000);
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerBusinessBankAccount")
	public void brokerBusinessBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(dependsOnMethods = "brokerBusinessBankAccountSetdefault")
	public void brokerBusinessRemoveBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerBusinessRemoveBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		bb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		acountname = bb.enterAccountName(accname);
		Thread.sleep(1000);
		bb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		bb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		bb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		bb.clickPersonalCheckingRadioButton();
		Thread.sleep(1000);
		bb.clickSaveButton();
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void verifyBrokerPersonalCheckingBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		bb.verifyBankAccount();
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		bb.clickAdminBankingLink();
		Thread.sleep(1000);
		System.out.println(bb.getPennyVerificationAmount());
		Thread.sleep(1000);
		al.AdminLogOut();
		Thread.sleep(1000);
		bb.closeTab();
		Thread.sleep(1000);
		bb.clickVerifyLink();
		Thread.sleep(1000);
		bb.enterAmount();
		Thread.sleep(1000);
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalCheckingBankAccount")
	public void brokerPersonalCheckingBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(dependsOnMethods = "brokerPersonalCheckingBankAccountSetdefault")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		bb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		acountname = bb.enterAccountName(accname);
		Thread.sleep(1000);
		bb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		bb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		bb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		bb.clickPersonalSavinggRadioButton();
		Thread.sleep(1000);
		bb.clickSaveButton();
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void verifyBrokerPersonalSavingsBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		bb.verifyBankAccount();
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		bb.clickAdminBankingLink();
		Thread.sleep(1000);
		System.out.println(bb.getPennyVerificationAmount());
		Thread.sleep(1000);
		al.AdminLogOut();
		Thread.sleep(1000);
		bb.closeTab();
		Thread.sleep(1000);
		bb.clickVerifyLink();
		Thread.sleep(1000);
		bb.enterAmount();
		Thread.sleep(1000);
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalSavingsBankAccount")
	public void brokerPersonalSavingsBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(dependsOnMethods = "brokerPersonalSavingsBankAccountSetdefault")
	public void brokerRemovePersonalSavingsBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}


}
