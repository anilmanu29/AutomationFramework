package testcases.loadpay.broker;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerBankAccountUpdate;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerBankAccountUpdateTest extends TestBase {

	BrokerBankAccountUpdate bb;
	BrokerLoginPage bl;
	public static String routingnumber;

	/*-------Initializing driver---------*/

	public BrokerBankAccountUpdateTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		bb = new BrokerBankAccountUpdate();
		bl = new BrokerLoginPage();
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
		bb.enterAccountName(accname);
		Thread.sleep(1000);
		routingnumber = bb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		bb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		bb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		bb.clickSaveButton();
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void brokerRemoveBusinessBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		Thread.sleep(1000);
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemoveBusinessBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {
		Thread.sleep(1000);
		bb.clickAccountlink();
		Thread.sleep(1000);
		bb.clickBankingLink();
		Thread.sleep(1000);
		bb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		bb.enterAccountName(accname);
		Thread.sleep(1000);
		routingnumber = bb.enterRoutingNumber(routingnum);
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
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		Thread.sleep(1000);
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {
		Thread.sleep(1000);
		bb.clickAccountlink();
		Thread.sleep(1000);
		bb.clickBankingLink();
		Thread.sleep(1000);
		bb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		bb.enterAccountName(accname);
		Thread.sleep(1000);
		routingnumber = bb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		bb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		bb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		bb.clickPersonalCheckingRadioButton();
		Thread.sleep(1000);
		bb.clickPersonalSavinggRadioButton();
		Thread.sleep(1000);
		bb.clickSaveButton();
		Thread.sleep(1000);
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void brokerRemovePersonalSavingsgBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		Thread.sleep(1000);
	}

}
