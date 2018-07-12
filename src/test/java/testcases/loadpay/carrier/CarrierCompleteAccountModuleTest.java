package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierCompleteAccountModule;
import pages.loadpay.carrier.CarrierLoginPage;

public class CarrierCompleteAccountModuleTest extends TestBase {

	CarrierCompleteAccountModule carriercompleteaccountmoduleObj;
	CarrierLoginPage loginPage;
	AdminHomePage adminhomepage;
	AdminLogin adminlogin;
	

	/*-------Initializing driver---------*/

	public CarrierCompleteAccountModuleTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		carriercompleteaccountmoduleObj = new CarrierCompleteAccountModule();
		loginPage = new CarrierLoginPage();	
		adminlogin = new AdminLogin();
		adminhomepage = new AdminHomePage(); 
	}

	@Test(description = "LP-3473 Carrier - Complete Account module",dataProvider = "getCarrierLoginData")
	public void VerifyCarrierLogin(String user, String pass) throws InterruptedException {
		loginPage.Carrierlogin(user, pass);
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_account.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickCompanylink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_Company.isDisplayed());
		
		carriercompleteaccountmoduleObj.enterDotnumber("1234567");
		Thread.sleep(1000);

		carriercompleteaccountmoduleObj.enterEinnumber("99-9999999");
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.clickCompanyUpdate();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.companyupdate.isDisplayed());
	}
	
	@Test(description = "LP-3473 Carrier - Complete Account module", dataProvider = "getAdminLoginData", dependsOnMethods = {"VerifyCarrierLogin"})
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
		System.out.println(CarrierLoginPage.cemail);
		adminlogin.ClickOnSearchBox(CarrierLoginPage.cemail);
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		
		}
	
	@Test(description = "LP-3473 Carrier - Complete Account module", dataProvider = "getShipperCompleteAccModuleData", dependsOnMethods = {"verifyAdminAccountModule"})
	public void verifyContactDetails(String un, String pwd,String ContactFN, String ContactLN,String contactemail, String ContactPN,String Contactextension,String ContactMobileNumber,String ContactFax) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		/*brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		Thread.sleep(1000);*/
		carriercompleteaccountmoduleObj.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_account.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickContactlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_Contact.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickAddNewContact();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.clcik_AddNewContact.isDisplayed());
		
		carriercompleteaccountmoduleObj.enterContactFirstName(ContactFN);
		Thread.sleep(1000);

		carriercompleteaccountmoduleObj.enterContactlastName(ContactLN);
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.enterContactemail(contactemail );
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.Contact_Email.isDisplayed());
		
		carriercompleteaccountmoduleObj.enterContactphonenum(ContactPN );
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.enterContactExtension(Contactextension );
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.enterContactMobileNumber(ContactMobileNumber );
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.enterContactFax(ContactFax );
		Thread.sleep(1000);
		
		carriercompleteaccountmoduleObj.clicksavelink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.click_Save.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickContactUpdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.click_ContactUpdate.isDisplayed());
		
		/*carriercompleteaccountmoduleObj.clickdeletecontactlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.click_DeleteContact.isDisplayed());
		*/
		
	}
	
	@Test(description = "LP-3473 Carrier - Complete Account module", dependsOnMethods = {"verifyContactDetails"})
	public void verifyNotifications() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		carriercompleteaccountmoduleObj.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_account.isDisplayed());

		carriercompleteaccountmoduleObj.clickNotificationlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_Notifications.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickNotifyByNewPaymentlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByNewPayment.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickNotifyByPayMeNowlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByPayMeNow.isDisplayed());
		
		
		carriercompleteaccountmoduleObj.clickNotifyByDepositlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByDeposit.isDisplayed());
		
		carriercompleteaccountmoduleObj.clickCarrierNotificationUpdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_CarrierNotificationUpdate.isDisplayed());
		
		
		carriercompleteaccountmoduleObj.clickNotifyByNewPaymentlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByPayMeNow.isDisplayed());
		
	
		carriercompleteaccountmoduleObj.clickNotifyByPayMeNowlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByPayMeNow.isDisplayed());
		
		
		carriercompleteaccountmoduleObj.clickNotifyByDepositlink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_NotifyByDeposit.isDisplayed());
		
		
		carriercompleteaccountmoduleObj.clickCarrierNotificationUpdatelink();
		Thread.sleep(1000);
		Assert.assertTrue(carriercompleteaccountmoduleObj.uncheck_CarrierNotificationUpdate.isDisplayed());
		
	}
	
}
