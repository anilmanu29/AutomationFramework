package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerEditPaymentAdminPaymentssubmenu;
import pages.loadpay.broker.BrokerNewPayment;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerEditPaymentAdminPaymentssubmenuTest extends TestBase {
	BrokerEditPaymentAdminPaymentssubmenu brokereditpaymentadminpaymentssubmenuobj;
	AdminHomePage adminhomepageobj;
	AdminLogin adminloginobj;
	AdminPayByCheck adminpaybycheckobj;
	BrokerNewPayment newBrokerPaymentObj;

	String brokerUsername, brokerPassword = "";
	String paymentAmount, loadID, invoiceNumber = "";
	LocalDate today;

	/*-------Initializing driver---------*/
	public BrokerEditPaymentAdminPaymentssubmenuTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokereditpaymentadminpaymentssubmenuobj = new BrokerEditPaymentAdminPaymentssubmenu();
		newBrokerPaymentObj = new BrokerNewPayment();
		adminhomepageobj = new AdminHomePage();
		adminloginobj = new AdminLogin();
		adminpaybycheckobj = new AdminPayByCheck();

	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokereditpaymentadminpaymentssubmenuobj.loginAsBroker(brokerUsername, brokerPassword);
		System.out.println("loginAsBrokerTest - Passed");

		newBrokerPaymentObj = new BrokerNewPayment();
		newBrokerPaymentObj.newPayment();

		Integer intPaymentAmount = TestUtil.getRandomNumber(100, 1000);
		paymentAmount = intPaymentAmount.toString();
		invoiceNumber = "NP" + TestUtil.getCurrentDateTime();
		loadID = invoiceNumber;

		newBrokerPaymentObj.carrierEmail(CarrierRegisterTest.carrierUsername);
		newBrokerPaymentObj.amount(paymentAmount);
		newBrokerPaymentObj.loadId(loadID);
		newBrokerPaymentObj.invoiceNumber(invoiceNumber);

		today = LocalDate.now();
		Integer month = today.getMonthValue() + 1;
		String strDate = month.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();
		newBrokerPaymentObj.setField_ScheduleDate(strDate);

		// bp.advanceCheckbox();
		//
		newBrokerPaymentObj.clickShedulePayment();

		newBrokerPaymentObj.clickShedulePaymenttab();

		newBrokerPaymentObj.searchCarrier(CarrierRegisterTest.carrierUsername);

		newBrokerPaymentObj.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		newBrokerPaymentObj.verifyInvoiceNumber(invoiceNumber, paymentAmount);
	}

	/*-------Login to Admin ---------*/
	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Broker", dependsOnMethods = {
			"loginAsBrokerTest" }, dataProvider = "getAdminLoginData")
	public void loginAsAdminTest(String username, String password) throws InterruptedException, AWTException {
		brokereditpaymentadminpaymentssubmenuobj.openandSwitchtoNewTab();
		adminhomepageobj.AdminURL();
		adminloginobj.adminUserPass(username, password);
		adminloginobj.adminLogin();
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(brokerUsername);
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
		adminpaybycheckobj.ClickOnsearchKeyword(invoiceNumber);
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
			"verifyEditableFieldsEnabledTest" })
	public void updatePaymentDetailsTest() throws InterruptedException {
		loadID = "UD" + TestUtil.getCurrentDateTime();
		invoiceNumber = loadID;

		today = LocalDate.now();
		Integer invoiceRecdMonth = today.getMonthValue();
		String invoiceDate = invoiceRecdMonth.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();

		Integer scheduledMonth = today.getMonthValue() + 1;
		String schedDate = scheduledMonth.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();

		brokereditpaymentadminpaymentssubmenuobj.updatePaymentDetails(CarrierRegisterTest.carrierUsername,
				invoiceNumber, "updated carrier", loadID, "987654", schedDate, invoiceDate, "Updated Memo");

		System.out.println("updatePaymentDetailsTest - Passed");
		// brokereditpaymentadminpaymentssubmenuobj.SwitchtoTab(0);

	}

	@Test(description = "LP-5430 LoadPay Admin_Regression_Customers Side Menu_Banking_Payment sub menu_Carrier", dependsOnMethods = {
			"updatePaymentDetailsTest" })
	public void verifySearchforCarrier() throws InterruptedException, AWTException {
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(CarrierRegisterTest.carrierUsername);
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
		adminpaybycheckobj.ClickOnsearchKeyword(invoiceNumber);
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
