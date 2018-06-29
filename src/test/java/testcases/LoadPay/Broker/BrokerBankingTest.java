package testcases.LoadPay.Broker;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Admin.AdminLogin;
import pages.LoadPay.Broker.BrokerBanking;
import pages.LoadPay.Broker.BrokerLoginPage;

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

	@Test(dataProvider = "getBrokerLoginData", priority = 36)
	public void brokerLogin(String un, String pwd) throws InterruptedException {
		bl = new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
	}

	@Test(dataProvider = "getBrokerBankingData", priority = 37)
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

	@Test(dataProvider = "getAdminLoginData", priority = 38)
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

	@Test(priority = 39)
	public void brokerBusinessBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(priority = 40)
	public void brokerBusinessRemoveBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getBrokerBankingData", priority = 41)
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

	@Test(dataProvider = "getAdminLoginData", priority = 42)
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

	@Test(priority = 43)
	public void brokerPersonalCheckingBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(priority = 44)
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getBrokerBankingData", priority = 45)
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

	@Test(dataProvider = "getAdminLoginData", priority = 46)
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

	@Test(priority = 47)
	public void brokerPersonalSavingsBankAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickSetDefault();
		Thread.sleep(1000);

	}

	@Test(priority = 48)
	public void brokerRemovePersonalSavingsBankAccount() throws InterruptedException {
		Thread.sleep(1000);
		bb.clickRemoveButton();
		Thread.sleep(2000);
	}


}
