package testcases.loadpay.broker;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerBankAccountUpdate;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerBankAccountUpdateTest extends TestBase {

	BrokerBankAccountUpdate brokerBankAccountObj;
	BrokerLoginPage brokerLoginPageObj;
	public static String routingnumber;

	/*-------Initializing driver---------*/

	public BrokerBankAccountUpdateTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerBankAccountObj = new BrokerBankAccountUpdate();
		brokerLoginPageObj = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void brokerLogin(String un, String pwd) throws InterruptedException {
		brokerLoginPageObj = new BrokerLoginPage();
		brokerLoginPageObj.Brokerlogin(un, pwd);
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerLogin")
	public void brokerAddNewBusinessBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {

		brokerBankAccountObj.clickAccountlink();

		brokerBankAccountObj.clickBankingLink();

		brokerBankAccountObj.clickAddNewBankAccountLink();

		brokerBankAccountObj.enterAccountName(accname);

		routingnumber = brokerBankAccountObj.enterRoutingNumber(routingnum);

		brokerBankAccountObj.enterAccountNumber(accnum);

		brokerBankAccountObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankAccountObj.clickSaveButton();

	}

	@Test(dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void brokerRemoveBusinessBankAccount() throws InterruptedException {

		brokerBankAccountObj.clickRemoveButton();

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemoveBusinessBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerBankAccountObj.clickAccountlink();

		brokerBankAccountObj.clickBankingLink();

		brokerBankAccountObj.clickAddNewBankAccountLink();

		brokerBankAccountObj.enterAccountName(accname);

		routingnumber = brokerBankAccountObj.enterRoutingNumber(routingnum);

		brokerBankAccountObj.enterAccountNumber(accnum);

		brokerBankAccountObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankAccountObj.clickPersonalCheckingRadioButton();

		brokerBankAccountObj.clickSaveButton();

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {

		brokerBankAccountObj.clickRemoveButton();

	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		brokerBankAccountObj.clickAccountlink();

		brokerBankAccountObj.clickBankingLink();

		brokerBankAccountObj.clickAddNewBankAccountLink();

		brokerBankAccountObj.enterAccountName(accname);

		routingnumber = brokerBankAccountObj.enterRoutingNumber(routingnum);

		brokerBankAccountObj.enterAccountNumber(accnum);

		brokerBankAccountObj.enterConfirmAccountNumber(confirmaccnum);

		brokerBankAccountObj.clickPersonalCheckingRadioButton();

		brokerBankAccountObj.clickPersonalSavinggRadioButton();

		brokerBankAccountObj.clickSaveButton();

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void brokerRemovePersonalSavingsgBankAccount() throws InterruptedException {

		brokerBankAccountObj.clickRemoveButton();

	}

}
