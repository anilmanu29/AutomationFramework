package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerPaymentTermsChargeRecipient.clickAccountlink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.lnk_account.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickPaymentTerms();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_PaymentTerms.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickpaymentenroll();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.check_paymentenroll.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickChargeRecipient();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_ChargeRecipient.isDisplayed());

		BrokerPaymentTermsChargeRecipient.clickpaymentTermUpdate();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.click_paymentTermUpdate.isDisplayed());

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "VerifybrokerLogin" })
	public void verifyAdminPaymentTerm(String Username, String pass) throws InterruptedException, AWTException {
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

		BrokerPaymentTermsChargeRecipient.clickAdminPaymentTerms();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(BrokerPaymentTermsChargeRecipient.Click_AdminPaymentTerms.isDisplayed());

	}

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "verifyAdminPaymentTerm")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		driver.get(super.getProperties().getProperty("url"));
		BrokerNewPayment = new BrokerNewPayment();
		BrokerNewPayment.newPayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		email = BrokerNewPayment.carrierEmail(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.amount(amt);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		invoice = BrokerNewPayment.invoiceNumber(invoiceno);
		al.add(invoice);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.loadId(loadid);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerPaymentTermsChargeRecipient.clickpaymentdatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerPaymentTermsChargeRecipient.clickpredatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerPaymentTermsChargeRecipient.clicknextdatelink();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// bp.advanceCheckbox();
		// wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.clickShedulePaymenttab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.searchCarrier(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.clickSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		BrokerNewPayment.verifyInvoiceNumber(invoiceno, amt);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(BrokerNewPayment.verifyPaymentStatus());
		// bp.logout();
	}

}
