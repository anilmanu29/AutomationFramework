package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerEditPaymentAdminPaymentssubmenu;

public class BrokerEditPaymentAdminPaymentssubmenuTest extends TestBase {
	String brokeremailid = "";
	BrokerEditPaymentAdminPaymentssubmenu brokereditpaymentadminpaymentssubmenuobj;
	AdminHomePage adminhomepageobj;
	AdminLogin adminloginobj;
	AdminPayByCheck adminpaybycheckobj;

	/*-------Initializing driver---------*/
	public BrokerEditPaymentAdminPaymentssubmenuTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		brokereditpaymentadminpaymentssubmenuobj = new BrokerEditPaymentAdminPaymentssubmenu();
		adminhomepageobj = new AdminHomePage();
		adminloginobj = new AdminLogin();
		adminpaybycheckobj = new AdminPayByCheck();

	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) {
		brokeremailid = un;
		brokereditpaymentadminpaymentssubmenuobj.loginAsBroker(un, pwd);
		System.out.println("loginAsBrokerTest - Passed");
	}

	/*-------Scheduling New Payment as a Broker---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"loginAsBrokerTest" }, dataProvider = "getPaymentData")
	public void brokerCreateNewPaymentTest(String cE, String iN, String lId, String pA) throws InterruptedException {
		// invoiceid=iN;
		brokereditpaymentadminpaymentssubmenuobj.brokerCreateNewPayment(cE, iN, lId, pA);
		System.out.println("brokerCreateNewPaymentTest - Passed");
	}

	/*-------Login to Admin ---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"brokerCreateNewPaymentTest" }, dataProvider = "getAdminLoginData")
	public void loginAsAdminTest(String username, String password) throws InterruptedException, AWTException {
		brokereditpaymentadminpaymentssubmenuobj.openandSwitchtoNewTab();
		adminhomepageobj.AdminURL();
		adminloginobj.adminUserPass(username, password);
		adminloginobj.adminLogin();
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(brokeremailid);
		Thread.sleep(1000);
		adminloginobj.ClickOnSearchButton();
		Thread.sleep(1000);
		adminloginobj.DoubleClickID();
	}

	/*-------Verify Admin Payment sub menu---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"loginAsAdminTest" })
	public void verifyAdminPaymentSubMenu() throws InterruptedException, AWTException {
		adminpaybycheckobj.clickPayments();
		adminpaybycheckobj.ClickOnsearchKeyword(BrokerEditPaymentAdminPaymentssubmenu.arraylist.get(0));
		brokereditpaymentadminpaymentssubmenuobj.clickPayment();
		Assert.assertTrue(brokereditpaymentadminpaymentssubmenuobj.getExpandedPayment().isDisplayed(),
				"Paymet has been collapsed");
	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"verifyAdminPaymentSubMenu" })
	public void verifyEditableFieldsEnabledTest() throws InterruptedException {
		brokereditpaymentadminpaymentssubmenuobj.verifyEditableFieldsEnabled();
		System.out.println("verifyEditableFieldsEnabledTest - Passed");
	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"verifyEditableFieldsEnabledTest" }, dataProvider = "getUpdatedBrokerPaymentData")
	public void updatePaymentDetailsTest(String updatedCarrierEmail, String updatedInvoiceNumber, String updatedPayTo,
			String updatedLoadID, String updatedCarrierDOT, String updatedScheduleDate, String updatedInvoiceRecd,
			String updatedMemo) throws InterruptedException {
		brokereditpaymentadminpaymentssubmenuobj.updatePaymentDetails(updatedCarrierEmail, updatedInvoiceNumber,
				updatedPayTo, updatedLoadID, updatedCarrierDOT, updatedScheduleDate, updatedInvoiceRecd, updatedMemo);
		System.out.println("updatePaymentDetailsTest - Passed");
		// brokereditpaymentadminpaymentssubmenuobj.SwitchtoTab(0);

	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Carrier", dependsOnMethods = {
			"updatePaymentDetailsTest" })
	public void verifySearchforCarrier() throws InterruptedException, AWTException {
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(brokereditpaymentadminpaymentssubmenuobj.carrierEmail);
		Thread.sleep(1000);
		adminloginobj.ClickOnSearchButton();
		Thread.sleep(1000);
		adminloginobj.DoubleClickID();
	}

	/*-------Verify Admin Payment sub menu---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Carrier", dependsOnMethods = {
			"verifySearchforCarrier" })
	public void verifyAdminPaymentSubMenuforCarrier() throws InterruptedException, AWTException {
		adminpaybycheckobj.clickPayments();
		adminpaybycheckobj.ClickOnsearchKeyword(BrokerEditPaymentAdminPaymentssubmenu.arraylist.get(1));
		brokereditpaymentadminpaymentssubmenuobj.clickCarrierkPayment();
		Assert.assertTrue(brokereditpaymentadminpaymentssubmenuobj.getExpandedPayment().isDisplayed(),
				"Paymet has been collapsed");
	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Carrier", dependsOnMethods = {
			"verifyAdminPaymentSubMenuforCarrier" })
	public void verifyEditableFieldsEnabledforCarrierTest() throws InterruptedException {
		brokereditpaymentadminpaymentssubmenuobj.verifyEditableFieldsEnabled();
		System.out.println("verifyEditableFieldsEnabledTest - Passed");
	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Carrier", dependsOnMethods = {
			"verifyEditableFieldsEnabledforCarrierTest" }, dataProvider = "getUpdatedCarrierPaymentData")
	public void updateCarrierPaymentDetailsTest(String updatedCarrierEmail, String updatedInvoiceNumber,
			String updatedPayTo, String updatedLoadID, String updatedCarrierDOT, String updatedScheduleDate,
			String updatedInvoiceRecd, String updatedMemo) throws InterruptedException {
		brokereditpaymentadminpaymentssubmenuobj.updatePaymentDetails(updatedCarrierEmail, updatedInvoiceNumber,
				updatedPayTo, updatedLoadID, updatedCarrierDOT, updatedScheduleDate, updatedInvoiceRecd, updatedMemo);
		System.out.println("updatePaymentDetailsTest - Passed");

	}

}
