package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		wait = new WebDriverWait(driver, 30);
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

		BrokerPaymentTermsChargeRecipient.clickAccountlink();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.lnk_account.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickPaymentTerms();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_PaymentTerms.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickpaymentenroll();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.check_paymentenroll.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickChargeRecipient();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_ChargeRecipient.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickpaymentTermUpdate();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_paymentTermUpdate.isDisplayed());

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "VerifybrokerLogin" })
	public void verifyAdminPaymentTerm(String Username, String pass) throws InterruptedException, AWTException {

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

		BrokerPaymentTermsChargeRecipient.clickAdminPaymentTerms();

		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_AdminPaymentTerms.isDisplayed());

	}

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "verifyAdminPaymentTerm")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		BrokerNewPayment = new BrokerNewPayment();
		BrokerNewPayment.newPayment();

		email = BrokerNewPayment.carrierEmail(cemail);

		BrokerNewPayment.amount(amt);

		invoice = BrokerNewPayment.invoiceNumber(invoiceno);
		al.add(invoice);

		BrokerNewPayment.loadId(loadid);

		BrokerPaymentTermsChargeRecipient.clickpaymentdatelink();

		BrokerPaymentTermsChargeRecipient.clickpredatelink();

		BrokerPaymentTermsChargeRecipient.clicknextdatelink();

		// bp.advanceCheckbox();
		//
		BrokerNewPayment.clickShedulePayment();

		BrokerNewPayment.clickShedulePaymenttab();

		BrokerNewPayment.searchCarrier(cemail);

		BrokerNewPayment.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		BrokerNewPayment.verifyInvoiceNumber(invoiceno, amt);

		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(BrokerNewPayment.verifyPaymentStatus());
		// bp.logout();
	}

}
