package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerBanking;
import pages.loadpay.broker.BrokerLoginPage;
import util.TestUtil;

public class BrokerBankingTest extends TestBase {

	BrokerBanking brokerBankingObj;
	BrokerLoginPage brokerLoginObj;
	AdminLogin adminLoginObj;
	public static String acountname;
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/

	public BrokerBankingTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		brokerBankingObj = new BrokerBanking();
		brokerLoginObj = new BrokerLoginPage();
		adminLoginObj = new AdminLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void brokerLogin(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerLoginObj.Brokerlogin(brokerUsername, brokerPassword);
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerLogin")
	public void brokerAddNewBusinessBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {

		brokerBankingObj.clickAccountlink();

		brokerBankingObj.clickBankingLink();

		brokerBankingObj.clickAddNewBankAccountLink();

		acountname = brokerBankingObj.enterAccountName(accname);

		brokerBankingObj.enterRoutingNumber(routingnum);

		brokerBankingObj.enterAccountNumber(accnum);

		brokerBankingObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankingObj.clickSaveButton();

		// Assert.assertEquals(brokerBankingObj.verifyAccountName(), accname);

		// Assert.assertEquals(brokerBankingObj.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void verifyBrokerBusinessBankAccount(String Username, String pass)
			throws InterruptedException, AWTException {

		brokerBankingObj.verifyBankAccount();

		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();

		adminLoginObj.ClickOnCustomersTab();
		adminLoginObj.Uncheck_Factor();

		adminLoginObj.ClickOnSearchBox(brokerUsername);

		adminLoginObj.ClickOnSearchButton();

		adminLoginObj.DoubleClickID();

		brokerBankingObj.clickAdminBankingLink();

		log.info(brokerBankingObj.getPennyVerificationAmount());

		adminLoginObj.AdminLogOut();

		brokerBankingObj.closeTab();

		brokerBankingObj.clickVerifyLink();

		brokerBankingObj.enterAmount();

		brokerBankingObj.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerBusinessBankAccount")
	public void brokerBusinessBankAccountSetdefault() throws InterruptedException {

		brokerBankingObj.clickSetDefault();

	}

	@Test(dependsOnMethods = "brokerBusinessBankAccountSetdefault")
	public void brokerBusinessRemoveBankAccount() throws InterruptedException {

		brokerBankingObj.clickRemoveButton();

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerBusinessRemoveBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerBankingObj.clickAddNewBankAccountLink();

		acountname = brokerBankingObj.enterAccountName(accname);

		brokerBankingObj.enterAccountNumber(accnum);

		brokerBankingObj.enterRoutingNumber(routingnum);

		brokerBankingObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankingObj.clickPersonalCheckingRadioButton();

		brokerBankingObj.clickSaveButton();

		// Assert.assertEquals(brokerBankingObj.verifyAccountName(), accname);

		// Assert.assertEquals(brokerBankingObj.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void verifyBrokerPersonalCheckingBankAccount(String Username, String pass)
			throws InterruptedException, AWTException {

		brokerBankingObj.verifyBankAccount();

		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();

		adminLoginObj.ClickOnCustomersTab();
		adminLoginObj.Uncheck_Factor();

		adminLoginObj.ClickOnSearchBox(brokerUsername);

		adminLoginObj.ClickOnSearchButton();

		adminLoginObj.DoubleClickID();

		brokerBankingObj.clickAdminBankingLink();

		log.info(brokerBankingObj.getPennyVerificationAmount());

		adminLoginObj.AdminLogOut();

		brokerBankingObj.closeTab();

		brokerBankingObj.clickVerifyLink();

		brokerBankingObj.enterAmount();

		brokerBankingObj.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalCheckingBankAccount")
	public void brokerPersonalCheckingBankAccountSetdefault() throws InterruptedException {

		brokerBankingObj.clickSetDefault();

	}

	@Test(dependsOnMethods = "brokerPersonalCheckingBankAccountSetdefault")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {

		brokerBankingObj.clickRemoveButton();

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerBankingObj.clickAddNewBankAccountLink();

		acountname = brokerBankingObj.enterAccountName(accname);

		brokerBankingObj.enterRoutingNumber(routingnum);

		brokerBankingObj.enterAccountNumber(accnum);

		brokerBankingObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankingObj.clickPersonalSavinggRadioButton();

		brokerBankingObj.clickSaveButton();

		// Assert.assertEquals(brokerBankingObj.verifyAccountName(), accname);

		// Assert.assertEquals(brokerBankingObj.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void verifyBrokerPersonalSavingsBankAccount(String Username, String pass)
			throws InterruptedException, AWTException {

		brokerBankingObj.verifyBankAccount();

		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();

		adminLoginObj.ClickOnCustomersTab();
		adminLoginObj.Uncheck_Factor();

		adminLoginObj.ClickOnSearchBox(brokerUsername);

		adminLoginObj.ClickOnSearchButton();

		adminLoginObj.DoubleClickID();

		brokerBankingObj.clickAdminBankingLink();

		log.info(brokerBankingObj.getPennyVerificationAmount());

		adminLoginObj.AdminLogOut();

		brokerBankingObj.closeTab();

		brokerBankingObj.clickVerifyLink();

		brokerBankingObj.enterAmount();

		brokerBankingObj.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalSavingsBankAccount")
	public void brokerPersonalSavingsBankAccountSetdefault() throws InterruptedException {

		brokerBankingObj.clickSetDefault();

	}

	@Test(dependsOnMethods = "brokerPersonalSavingsBankAccountSetdefault")
	public void brokerRemovePersonalSavingsBankAccount() throws InterruptedException {

		brokerBankingObj.clickRemoveButton();

	}

}
