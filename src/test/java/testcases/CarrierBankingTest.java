package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CarrierBanking;
import pages.CarrierLoginPage;

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
	}

	@Test(dataProvider = "getCarrierLoginData", priority = 26)
	public void loginCarrier(String un, String pwd) throws InterruptedException {
		cl = new CarrierLoginPage();
		cl.Carrierlogin(un, pwd);
	}

	@Test(dataProvider = "getCarrierBankingData", priority = 27)
	public void carrierAddNewBusinessBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		cb.clickAccountlink();
		Thread.sleep(1000);
		cb.clickBankingLink();
		Thread.sleep(1000);
		cb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		cb.enterAccountName(accname);
		Thread.sleep(1000);
		cb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		cb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		cb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		cb.clickSaveButton();
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyAccountName(), accname);
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(priority = 28)
	public void carrierBusinessAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		cb.clickSetDefault();
		Thread.sleep(2000);

	}

	@Test(priority = 29)
	public void carrierBusinessRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getCarrierBankingData", priority = 30)
	public void carrierAddNewPersonalCheckingBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		cb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		cb.enterAccountName(accname);
		Thread.sleep(1000);
		cb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		cb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		cb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		cb.clickPersonalCheckingRadioButton();
		Thread.sleep(1000);
		cb.clickSaveButton();
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyAccountName(), accname);
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(priority = 31)
	public void carrierPersonalCheckingAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		cb.clickSetDefault();
		Thread.sleep(2000);

	}

	@Test(priority = 32)
	public void carrierPersonalCheckingRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "getCarrierBankingData", priority = 33)
	public void carrierAddNewPersonalSavingsBankAccount(String accname, String routingnum, String accnum, String confirmaccnum)
			throws InterruptedException {
		Thread.sleep(1000);
		cb.clickAddNewBankAccountLink();
		Thread.sleep(1000);
		cb.enterAccountName(accname);
		Thread.sleep(1000);
		cb.enterRoutingNumber(routingnum);
		Thread.sleep(1000);
		cb.enterAccountNumber(accnum);
		Thread.sleep(1000);
		cb.enterConfirmAccountNumber(confirmaccnum);
		Thread.sleep(1000);
		cb.clickPersonalSavinggRadioButton();
		Thread.sleep(1000);
		cb.clickSaveButton();
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyAccountName(), accname);
		Thread.sleep(1000);
		//Assert.assertEquals(cb.verifyRoutingNumber(), routingnum);

	}

	@Test(priority = 34)
	public void carrierPersonalSavingsAccountSetdefault() throws InterruptedException {
		Thread.sleep(1000);
		cb.clickSetDefault();
		Thread.sleep(2000);

	}

	@Test(priority = 35)
	public void carrierPersonalSavingsRemoveBankAccount() throws InterruptedException {
		cb.clickRemoveButton();
		Thread.sleep(2000);
	}
	
	
	

}
