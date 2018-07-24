package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.ShipperCompleteAccountModule;

public class ShipperCompleteAccountModuleTest extends TestBase {

	ShipperCompleteAccountModule shippercompleteaccountmodule;
	BrokerLoginPage brokerlogin;
	AdminHomePage adminhomepage;
	AdminLogin adminlogin;
	String Dot = "1234567";
	String EIN = "123456789";

	/*-------Initiadminloginizing driver---------*/

	public ShipperCompleteAccountModuleTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		shippercompleteaccountmodule = new ShipperCompleteAccountModule();
		brokerlogin = new BrokerLoginPage();
		adminlogin = new AdminLogin();
		adminhomepage = new AdminHomePage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void VerifybrokerLogin(String un, String pwd) throws InterruptedException {
		brokerlogin.Brokerlogin(un, pwd);

		shippercompleteaccountmodule.clickAccountlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickCompanylink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_Company.isDisplayed());

		shippercompleteaccountmodule.enterDotnumber(Dot);

		shippercompleteaccountmodule.enterEinnumber(EIN);

		shippercompleteaccountmodule.clickCompanyUpdate();

		Assert.assertTrue(shippercompleteaccountmodule.companyupdate.isDisplayed());
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "VerifybrokerLogin" })
	public void verifyAdminAccountModule(String Username, String pass) throws InterruptedException, AWTException {

		adminhomepage.AdminURL();

		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();

		adminlogin.ClickOnCustomersTab();

		Assert.assertTrue(adminlogin.CustomerTab.isDisplayed());

		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);

		adminlogin.ClickOnSearchButton();

		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());

		adminlogin.DoubleClickID();

		// Assert.assertTrue(adminlogin.DoubleClickID.isDisplayed());

	}

	@Test(dataProvider = "getShipperCompleteAccModuleData", dependsOnMethods = { "verifyAdminAccountModule" })
	public void verifyContactDetails(String un, String pwd, String ContactFN, String ContactLN, String contactemail,
			String ContactPN, String Contactextension, String ContactMobileNumber, String ContactFax)
			throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		/*
		 * brokerlogin = new BrokerLoginPage(); brokerlogin.Brokerlogin(un, pwd);
		 * 
		 */
		shippercompleteaccountmodule.clickAccountlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickContactlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_Contact.isDisplayed());

		shippercompleteaccountmodule.clickAddNewContact();

		Assert.assertTrue(shippercompleteaccountmodule.clickAddNewContact.isDisplayed());

		shippercompleteaccountmodule.enterContactFirstName(ContactFN);

		shippercompleteaccountmodule.enterContactlastName(ContactLN);

		shippercompleteaccountmodule.enterContactemail(contactemail);

		Assert.assertTrue(shippercompleteaccountmodule.Contact_Email.isDisplayed());

		shippercompleteaccountmodule.enterContactphonenum(ContactPN);

		shippercompleteaccountmodule.enterContactExtension(Contactextension);

		shippercompleteaccountmodule.enterContactMobileNumber(ContactMobileNumber);

		shippercompleteaccountmodule.enterContactFax(ContactFax);

		shippercompleteaccountmodule.clicksavelink();

		Assert.assertTrue(shippercompleteaccountmodule.click_Save.isDisplayed());

		/*
		 * shippercompleteaccountmodule.clickdeletecontactlink();
		 * 
		 */

		shippercompleteaccountmodule.clickContactUpdatelink();

		Assert.assertTrue(shippercompleteaccountmodule.click_ContactUpdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyContactDetails" })
	public void verifyNotifications() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickNotificationlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_Notifications.isDisplayed());

		shippercompleteaccountmodule.checkNotifyByWithdrwallink();

		Assert.assertTrue(shippercompleteaccountmodule.check_NotifyByWithdrawal.isDisplayed());

		shippercompleteaccountmodule.clickUpdatebuttonlink();

		Assert.assertTrue(shippercompleteaccountmodule.click_updatebtn.isDisplayed());

		shippercompleteaccountmodule.uncheckNotifyByWithdrawallink();

		Assert.assertTrue(shippercompleteaccountmodule.uncheck_NotifyByWithdrawal.isDisplayed());

	}

	@Test(dataProvider = "getAdminLoginshipperaccountmoduleData", dependsOnMethods = { "verifyNotifications" })
	public void verifyCredit(String ExtendedCredit) throws InterruptedException, AWTException {

		driver.get(super.getProperties().getProperty("AdminURL"));
		adminhomepage.AdminURL();

		adminlogin.ClickOnCustomersTab();

		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);

		adminlogin.ClickOnSearchButton();

		adminlogin.DoubleClickID();

		shippercompleteaccountmodule.clickcreditlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_Credit.isDisplayed());

		shippercompleteaccountmodule.enterExtendedCredit(ExtendedCredit);

		Assert.assertTrue(shippercompleteaccountmodule.text_ExtendedCredit.isDisplayed());

		shippercompleteaccountmodule.clickupdatelink();

		Assert.assertTrue(shippercompleteaccountmodule.click_Update.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyCredit" })
	public void verifybrokercredit() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickmycreditlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_MyCredit.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifybrokercredit" })
	public void verifybrokerPayMeNow() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();

		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickpaymenowlink();

		Assert.assertTrue(shippercompleteaccountmodule.link_paymeNow.isDisplayed());

		shippercompleteaccountmodule.checkpaymenowenroll();

		Assert.assertTrue(shippercompleteaccountmodule.check_PMNEnrolled.isDisplayed());

		shippercompleteaccountmodule.clickPaymentTermlink();

		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());

		shippercompleteaccountmodule.clickBrokerpaymeNowUpdatelink();

		Assert.assertTrue(shippercompleteaccountmodule.Click_BrokerpaymeNowUpdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifybrokerPayMeNow" })
	public void verifyAdminPaymeNow() throws InterruptedException, AWTException {

		driver.get(super.getProperties().getProperty("AdminURL"));
		adminhomepage.AdminURL();

		adminlogin.ClickOnCustomersTab();

		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);

		adminlogin.ClickOnSearchButton();

		adminlogin.DoubleClickID();

		shippercompleteaccountmodule.clickadminpaymenowlink();

		Assert.assertTrue(shippercompleteaccountmodule.click_adminpaymenow.isDisplayed());

		shippercompleteaccountmodule.uncheckpaymenowenroll();

		Assert.assertTrue(shippercompleteaccountmodule.ucheck_adminPMNEnrolled.isDisplayed());

		shippercompleteaccountmodule.clickPaymentTermlink();

		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());

		shippercompleteaccountmodule.clickadminupdate();

		Assert.assertTrue(shippercompleteaccountmodule.click_adminupdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyAdminPaymeNow" })
	public void verifybrokerPayMeNowupdate() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();

		shippercompleteaccountmodule.clickpaymenowlink();

	}

}
