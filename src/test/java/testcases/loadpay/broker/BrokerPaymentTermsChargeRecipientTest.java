package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.broker.BrokerPaymentTermsChargeRecipient;

public class BrokerPaymentTermsChargeRecipientTest extends TestBase {

	BrokerPaymentTermsChargeRecipient BrokerPaymentTermsChargeRecipient;
	BrokerLoginPage brokerlogin;
	AdminHomePage adminhomepage;
	BrokerNewPayment BrokerNewPayment;
	AdminLogin adminlogin;
	 public static String email;
	 public static ArrayList<String> al;
	 static String invoice;
	String Dot = "1234567";
	String EIN = "123456789";
	
	

	/*-------Initiadminloginizing driver---------*/

	public BrokerPaymentTermsChargeRecipientTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		BrokerPaymentTermsChargeRecipient = new BrokerPaymentTermsChargeRecipient();
		brokerlogin = new BrokerLoginPage();
		adminlogin = new AdminLogin();
		adminhomepage = new AdminHomePage();
		BrokerNewPayment = new BrokerNewPayment();
		al = new ArrayList<String>();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void VerifybrokerLogin(String un, String pwd) throws InterruptedException {
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		Thread.sleep(1000);
		BrokerPaymentTermsChargeRecipient.clickAccountlink();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.lnk_account.isDisplayed());
		
		BrokerPaymentTermsChargeRecipient.clickPaymentTerms();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_PaymentTerms.isDisplayed());
		
		
		BrokerPaymentTermsChargeRecipient.clickpaymentenroll();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.check_paymentenroll.isDisplayed());
		
		BrokerPaymentTermsChargeRecipient.clickChargeRecipient();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_ChargeRecipient.isDisplayed());
		
		BrokerPaymentTermsChargeRecipient.clickpaymentTermUpdate();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_paymentTermUpdate.isDisplayed());
		
		
	}
	
	@Test(dataProvider = "getAdminLoginData",dependsOnMethods = {"VerifybrokerLogin"})
	public void verifyAdminPaymentTerm(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		adminhomepage.AdminURL(); 
		Thread.sleep(2000);
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		Thread.sleep(1000);
		adminlogin.ClickOnCustomersTab();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.CustomerTab.isDisplayed());
		
		log.info(BrokerLoginPage.bemail);
		adminlogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());
		
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		//Assert.assertTrue(adminlogin.DoubleClickID.isDisplayed());
		
		BrokerPaymentTermsChargeRecipient.clickAdminPaymentTerms();
		Thread.sleep(1000);
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_AdminPaymentTerms.isDisplayed());
		
		}
	
	@Test(dataProvider="getPaymentData", dependsOnMethods = "verifyAdminPaymentTerm")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		BrokerNewPayment = new BrokerNewPayment();
		BrokerNewPayment.newPayment();
		Thread.sleep(1000);
		email= BrokerNewPayment.carrierEmail(cemail);
		Thread.sleep(1000);
		BrokerNewPayment.amount(amt);
		Thread.sleep(1000);
		invoice = 	BrokerNewPayment.invoiceNumber(invoiceno);
		al.add(invoice);
		Thread.sleep(1000);
		BrokerNewPayment.loadId(loadid);
		Thread.sleep(1000);
		BrokerPaymentTermsChargeRecipient.clickpaymentdatelink();
		Thread.sleep(1000);
		BrokerPaymentTermsChargeRecipient.clickpredatelink();
		Thread.sleep(1000);
		BrokerPaymentTermsChargeRecipient.clicknextdatelink();
		Thread.sleep(1000);
		//bp.advanceCheckbox();
		//Thread.sleep(1000);
		BrokerNewPayment.clickShedulePayment();
		Thread.sleep(1000);
		BrokerNewPayment.clickShedulePaymenttab();
		Thread.sleep(1000);
		BrokerNewPayment.searchCarrier(cemail);
		Thread.sleep(1000);
		BrokerNewPayment.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		BrokerNewPayment.verifyInvoiceNumber(invoiceno,amt);
		 Thread.sleep(1000);
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(BrokerNewPayment.verifyPaymentStatus());
		//bp.logout();
	}
		
	}
	
	
	

