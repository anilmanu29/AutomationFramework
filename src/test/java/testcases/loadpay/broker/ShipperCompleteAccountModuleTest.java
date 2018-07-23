package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
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
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickCompanylink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Company.isDisplayed());

		shippercompleteaccountmodule.enterDotnumber(Dot);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterEinnumber(EIN);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.clickCompanyUpdate();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.companyupdate.isDisplayed());
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "VerifybrokerLogin" })
	public void verifyAdminAccountModule(String Username, String pass) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminhomepage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminlogin.CustomerTab.isDisplayed());

		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		adminlogin.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());

		adminlogin.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertTrue(adminlogin.DoubleClickID.isDisplayed());

	}

	@Test(dataProvider = "getShipperCompleteAccModuleData", dependsOnMethods = { "verifyAdminAccountModule" })
	public void verifyContactDetails(String un, String pwd, String ContactFN, String ContactLN, String contactemail,
			String ContactPN, String Contactextension, String ContactMobileNumber, String ContactFax)
			throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		/*
		 * brokerlogin = new BrokerLoginPage(); brokerlogin.Brokerlogin(un, pwd);
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 */
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickContactlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Contact.isDisplayed());

		shippercompleteaccountmodule.clickAddNewContact();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.clcik_AddNewContact.isDisplayed());

		shippercompleteaccountmodule.enterContactFirstName(ContactFN);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterContactlastName(ContactLN);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterContactemail(contactemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.Contact_Email.isDisplayed());

		shippercompleteaccountmodule.enterContactphonenum(ContactPN);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterContactExtension(Contactextension);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterContactMobileNumber(ContactMobileNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.enterContactFax(ContactFax);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		shippercompleteaccountmodule.clicksavelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_Save.isDisplayed());

		/*
		 * shippercompleteaccountmodule.clickdeletecontactlink();
		 * wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 */

		shippercompleteaccountmodule.clickContactUpdatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_ContactUpdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyContactDetails" })
	public void verifyNotifications() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickNotificationlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Notifications.isDisplayed());

		shippercompleteaccountmodule.checkNotifyByWithdrwallink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.check_NotifyByWithdrawal.isDisplayed());

		shippercompleteaccountmodule.clickUpdatebuttonlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_updatebtn.isDisplayed());

		shippercompleteaccountmodule.uncheckNotifyByWithdrawallink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.uncheck_NotifyByWithdrawal.isDisplayed());

	}

	@Test(dataProvider = "getAdminLoginshipperaccountmoduleData", dependsOnMethods = { "verifyNotifications" })
	public void verifyCredit(String ExtendedCredit) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.get(super.getProperties().getProperty("AdminURL"));
		adminhomepage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		shippercompleteaccountmodule.clickcreditlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Credit.isDisplayed());

		shippercompleteaccountmodule.enterExtendedCredit(ExtendedCredit);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.text_ExtendedCredit.isDisplayed());

		shippercompleteaccountmodule.clickupdatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_Update.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyCredit" })
	public void verifybrokercredit() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickmycreditlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_MyCredit.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifybrokercredit" })
	public void verifybrokerPayMeNow() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());

		shippercompleteaccountmodule.clickpaymenowlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.link_paymeNow.isDisplayed());

		shippercompleteaccountmodule.checkpaymenowenroll();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.check_PMNEnrolled.isDisplayed());

		shippercompleteaccountmodule.clickPaymentTermlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());

		shippercompleteaccountmodule.clickBrokerpaymeNowUpdatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.Click_BrokerpaymeNowUpdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifybrokerPayMeNow" })
	public void verifyAdminPaymeNow() throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		driver.get(super.getProperties().getProperty("AdminURL"));
		adminhomepage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminlogin.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		shippercompleteaccountmodule.clickadminpaymenowlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_adminpaymenow.isDisplayed());

		shippercompleteaccountmodule.uncheckpaymenowenroll();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.ucheck_adminPMNEnrolled.isDisplayed());

		shippercompleteaccountmodule.clickPaymentTermlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());

		shippercompleteaccountmodule.clickadminupdate();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(shippercompleteaccountmodule.click_adminupdate.isDisplayed());

	}

	@Test(dependsOnMethods = { "verifyAdminPaymeNow" })
	public void verifybrokerPayMeNowupdate() throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		shippercompleteaccountmodule.clickpaymenowlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

	}

}
