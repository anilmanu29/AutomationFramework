package testcases.loadpay.broker;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		routingnumber = bb.enterRoutingNumber(routingnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountNumber(accnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterConfirmAccountNumber(confirmaccnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSaveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewBusinessBankAccount")
	public void brokerRemoveBusinessBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemoveBusinessBankAccount")
	public void brokerAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		routingnumber = bb.enterRoutingNumber(routingnum);
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
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalCheckingBankAccount")
	public void brokerRemovePersonalCheckingBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	@Test(dataProvider = "getBrokerBankingData", dependsOnMethods = "brokerRemovePersonalCheckingBankAccount")
	public void brokerAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickBankingLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickAddNewBankAccountLink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountName(accname);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		routingnumber = bb.enterRoutingNumber(routingnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterAccountNumber(accnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.enterConfirmAccountNumber(confirmaccnum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickPersonalCheckingRadioButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickPersonalSavinggRadioButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bb.clickSaveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bb.verifyAccountName(), accname);
		// Assert.assertEquals(bb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "brokerAddNewPersonalSavingsBankAccount")
	public void brokerRemovePersonalSavingsgBankAccount() throws InterruptedException {

		bb.clickRemoveButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

}
