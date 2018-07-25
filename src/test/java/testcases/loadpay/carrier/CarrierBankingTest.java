package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierBanking;
import pages.loadpay.carrier.CarrierLoginPage;

public class CarrierBankingTest extends TestBase {

	CarrierBanking cb;
	CarrierLoginPage cl;

	/*-------Initializing driver---------*/

	public CarrierBankingTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		cb = new CarrierBanking();
		cl = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		cl = new CarrierLoginPage();
		cl.Carrierlogin(un, pwd);
	}

	@Test(dataProvider = "getCarrierBankingData", dependsOnMethods = "loginCarrier")
	public void carrierAddNewBusinessBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		cb.clickAccountlink();
		cb.clickBankingLink();
		cb.clickAddNewBankAccountLink();
		cb.enterAccountName(accname);
		cb.enterRoutingNumber(routingnum);
		cb.enterAccountNumber(accnum);
		cb.enterConfirmAccountNumber(confirmaccnum);
		cb.clickSaveButton();
		// Assert.assertEquals(cb.verifyAccountName(), accname);
		// Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "carrierAddNewBusinessBankAccount")
	public void carrierBusinessAccountSetdefault() throws InterruptedException {
		cb.clickSetDefault();
	}

	@Test(dependsOnMethods = "carrierBusinessAccountSetdefault")
	public void carrierBusinessRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();
	}

	@Test(dataProvider = "getCarrierBankingData", dependsOnMethods = "carrierBusinessRemoveBankAccount")
	public void carrierAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {
		cb.clickAddNewBankAccountLink();
		cb.enterAccountName(accname);
		cb.enterRoutingNumber(routingnum);

		cb.enterAccountNumber(accnum);

		cb.enterConfirmAccountNumber(confirmaccnum);

		cb.clickPersonalCheckingRadioButton();

		cb.clickSaveButton();

		// Assert.assertEquals(cb.verifyAccountName(), accname);

		// Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "carrierAddNewPersonalCheckingBankAccount")
	public void carrierPersonalCheckingAccountSetdefault() throws InterruptedException {

		cb.clickSetDefault();

	}

	@Test(dependsOnMethods = "carrierPersonalCheckingAccountSetdefault")
	public void carrierPersonalCheckingRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();

	}

	@Test(dataProvider = "getCarrierBankingData", dependsOnMethods = "carrierPersonalCheckingRemoveBankAccount")
	public void carrierAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum,
			String confirmaccnum) throws InterruptedException {

		cb.clickAddNewBankAccountLink();

		cb.enterAccountName(accname);

		cb.enterRoutingNumber(routingnum);

		cb.enterAccountNumber(accnum);

		cb.enterConfirmAccountNumber(confirmaccnum);

		cb.clickPersonalSavinggRadioButton();

		cb.clickSaveButton();

		// Assert.assertEquals(cb.verifyAccountName(), accname);

		// Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(dependsOnMethods = "carrierAddNewPersonalSavingsBankAccount")
	public void carrierPersonalSavingsAccountSetdefault() throws InterruptedException {

		cb.clickSetDefault();

	}

	@Test(dependsOnMethods = "carrierPersonalSavingsAccountSetdefault")
	public void carrierPersonalSavingsRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();

	}

}
