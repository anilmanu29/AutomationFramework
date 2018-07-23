package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		acountname = bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterRoutingNumber(routingnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountNumber(accnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterConfirmAccountNumber(confirmaccnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSaveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void verifyBrokerBusinessBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.verifyBankAccount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.adminUserPass(Username, pass);
		al.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAdminBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(bb.getPennyVerificationAmount());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.AdminLogOut();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.closeTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAmount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerBusinessBankAccount")
	public void brokerBusinessBankAccountSetdefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSetDefault();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

	@Test(dependsOnMethods = "brokerBusinessBankAccountSetdefault")
	public void brokerBusinessRemoveBankAccount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}
	
	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerBusinessRemoveBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		acountname = bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterRoutingNumber(routingnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountNumber(accnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterConfirmAccountNumber(confirmaccnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickPersonalCheckingRadioButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSaveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void verifyBrokerPersonalCheckingBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.verifyBankAccount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.adminUserPass(Username, pass);
		al.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAdminBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(bb.getPennyVerificationAmount());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.AdminLogOut();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.closeTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAmount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalCheckingBankAccount")
	public void brokerPersonalCheckingBankAccountSetdefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSetDefault();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

	@Test(dependsOnMethods = "brokerPersonalCheckingBankAccountSetdefault")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}
	
	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		acountname = bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterRoutingNumber(routingnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountNumber(accnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterConfirmAccountNumber(confirmaccnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickPersonalSavinggRadioButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSaveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void verifyBrokerPersonalSavingsBankAccount(String Username, String pass) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.verifyBankAccount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.adminUserPass(Username, pass);
		al.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAdminBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(bb.getPennyVerificationAmount());
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		al.AdminLogOut();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.closeTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAmount();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickVerifyButton();

	}

	@Test(dependsOnMethods = "verifyBrokerPersonalSavingsBankAccount")
	public void brokerPersonalSavingsBankAccountSetdefault() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSetDefault();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

	@Test(dependsOnMethods = "brokerPersonalSavingsBankAccountSetdefault")
	public void brokerRemovePersonalSavingsBankAccount() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}


}
