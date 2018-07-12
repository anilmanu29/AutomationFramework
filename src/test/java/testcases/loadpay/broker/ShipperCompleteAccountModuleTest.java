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
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		Thread.sleep(1000);
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());
		
		shippercompleteaccountmodule.clickCompanylink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Company.isDisplayed());
		
		shippercompleteaccountmodule.enterDotnumber(Dot);
		Thread.sleep(1000);

		shippercompleteaccountmodule.enterEinnumber(EIN);
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.clickCompanyUpdate();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.companyupdate.isDisplayed());
	}
	
	@Test(dataProvider = "getAdminLoginData",dependsOnMethods = {"VerifybrokerLogin"})
	public void verifyAdminAccountModule(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		adminhomepage.AdminURL(); 
		Thread.sleep(2000);
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		Thread.sleep(1000);
		adminlogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.CustomerTab.isDisplayed());
		
		System.out.println(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());
		
		
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		//Assert.assertTrue(adminlogin.DoubleClickID.isDisplayed());
		
		
		}
	
	@Test(dataProvider = "getShipperCompleteAccModuleData",dependsOnMethods = {"verifyAdminAccountModule"})
	public void verifyContactDetails(String un, String pwd,String ContactFN, String ContactLN,String contactemail, String ContactPN,String Contactextension,String ContactMobileNumber,String ContactFax) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		/*brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		Thread.sleep(1000);*/
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());
		
		shippercompleteaccountmodule.clickContactlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Contact.isDisplayed());
		
		shippercompleteaccountmodule.clickAddNewContact();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.clcik_AddNewContact.isDisplayed());
		
		
		shippercompleteaccountmodule.enterContactFirstName(ContactFN);
		Thread.sleep(1000);

		shippercompleteaccountmodule.enterContactlastName(ContactLN );
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.enterContactemail(contactemail );
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.Contact_Email.isDisplayed());
		
		
		shippercompleteaccountmodule.enterContactphonenum(ContactPN );
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.enterContactExtension(Contactextension );
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.enterContactMobileNumber(ContactMobileNumber );
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.enterContactFax(ContactFax );
		Thread.sleep(1000);
		
		shippercompleteaccountmodule.clicksavelink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_Save.isDisplayed());
		
		
		/*shippercompleteaccountmodule.clickdeletecontactlink();
		Thread.sleep(1000);*/
		
		shippercompleteaccountmodule.clickContactUpdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_ContactUpdate.isDisplayed());
		
		
	}
	
	@Test(dependsOnMethods = {"verifyContactDetails"})
	public void verifyNotifications() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());
		
		shippercompleteaccountmodule.clickNotificationlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Notifications.isDisplayed());
		
		
		shippercompleteaccountmodule.checkNotifyByWithdrwallink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.check_NotifyByWithdrawal.isDisplayed());
		
		
		shippercompleteaccountmodule.clickUpdatebuttonlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_updatebtn.isDisplayed());
		
		
		shippercompleteaccountmodule.uncheckNotifyByWithdrawallink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.uncheck_NotifyByWithdrawal.isDisplayed());
		
		
	}
	
	
	@Test(dataProvider = "getAdminLoginshipperaccountmoduleData",dependsOnMethods = {"verifyNotifications"})
	public void verifyCredit(String ExtendedCredit) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		driver.get(prop.getProperty("AdminURL"));
		adminhomepage.AdminURL(); 
		Thread.sleep(2000);
		adminlogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		shippercompleteaccountmodule.clickcreditlink();
		Thread.sleep(6000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_Credit.isDisplayed());
		
		shippercompleteaccountmodule.enterExtendedCredit(ExtendedCredit);
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.text_ExtendedCredit.isDisplayed());
		
		shippercompleteaccountmodule.clickupdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_Update.isDisplayed());
		
		}
	
	
	@Test(dependsOnMethods = {"verifyCredit"})
	public void verifybrokercredit() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());
		
		shippercompleteaccountmodule.clickmycreditlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_MyCredit.isDisplayed());
	
	}
	
	@Test(dependsOnMethods = {"verifybrokercredit"})
	public void verifybrokerPayMeNow() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.lnk_account.isDisplayed());
		
		shippercompleteaccountmodule.clickpaymenowlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.link_paymeNow.isDisplayed());
		
		shippercompleteaccountmodule.checkpaymenowenroll();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.check_PMNEnrolled.isDisplayed());
		
		shippercompleteaccountmodule.clickPaymentTermlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());
		
		shippercompleteaccountmodule.clickBrokerpaymeNowUpdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.Click_BrokerpaymeNowUpdate.isDisplayed());
			
	}
	
	@Test(dependsOnMethods = {"verifybrokerPayMeNow"})
	public void verifyAdminPaymeNow() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		driver.get(prop.getProperty("AdminURL"));
		adminhomepage.AdminURL(); 
		Thread.sleep(2000);
		adminlogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		shippercompleteaccountmodule.clickadminpaymenowlink();
		Thread.sleep(6000);
		Assert.assertTrue(shippercompleteaccountmodule.click_adminpaymenow.isDisplayed());
		
		shippercompleteaccountmodule.uncheckpaymenowenroll();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.ucheck_adminPMNEnrolled.isDisplayed());
		
		shippercompleteaccountmodule.clickPaymentTermlink();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_PaymentTerm.isDisplayed());
		
		shippercompleteaccountmodule.clickadminupdate();
		Thread.sleep(1000);
		Assert.assertTrue(shippercompleteaccountmodule.click_adminupdate.isDisplayed());
		
		}
	
	@Test(dependsOnMethods = {"verifyAdminPaymeNow"})
	public void verifybrokerPayMeNowupdate() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		shippercompleteaccountmodule.clickAccountlink();
		Thread.sleep(1000);
		shippercompleteaccountmodule.clickpaymenowlink();
		Thread.sleep(3000);
		
	}
	
	
	
}
