package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminPaymentsGreaterthan45DaysTest extends TestBase {

	BrokerPaymentSheduledates brokerPaymentSheduledates;
	BrokerLoginPage brokerlogin;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	CarrierWireTransfer carrierwireframe;
	WebElement checkbox;
	CarrierLoginPage carrierloginPage;
	CarrierSameDAYACH carriersamedayach;

	String brokerUserName;
	String brokerPassword;
	String carrierUserName;
	String carrierPassword;

	String greaterthan365invoice = "";
	String greaterthan45invoice = "";

	ArrayList<String> invoiceList;
	String email;
	LocalDate today;
	public static String newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum, carrierEmail = "";

	/*-------Initializing driver---------*/
	public AdminPaymentsGreaterthan45DaysTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokerlogin = new BrokerLoginPage();
		carrierloginPage = new CarrierLoginPage();
		carrierwireframe = new CarrierWireTransfer();
		brokerlogin = new BrokerLoginPage();
		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		carriersamedayach = new CarrierSameDAYACH();
		wait = new WebDriverWait(driver, 30);
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("url"));

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUserName = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUserName = user;
			brokerPassword = pass;
		}
	}

	/*-------Admin Login ---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminPaymentHistoryStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();

		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Enabled();
		admLogin.Click_paymentterm45Submit();

		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();

		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatus")
	public void loginBroker(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(brokerUserName, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "loginBroker")
	public void brokernewPaymentmorethan365(String cemail, String invoiceno, String loadid, String amt,
			String scheduledate) throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");

		// Store data-provider elements into publicly-accessible strings
		carrierEmail = cemail;
		invoiceno = TestUtil.getCurrentDateTime();
		newPaymentAmount = amt;
		newPaymentInvoiceNum = invoiceno;
		newPaymentLoadId = invoiceno;

		today = LocalDate.now();
		Integer year = today.getYear() + 1;
		Integer day = today.getDayOfMonth() + 1;
		String strDate = today.getMonthValue() + "/" + day.toString() + "/" + year.toString();

		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(carrierEmail);
		brokerPaymentSheduledates.amount(newPaymentAmount);
		greaterthan365invoice = brokerPaymentSheduledates.invoiceNumber(newPaymentInvoiceNum);
		invoiceList.add(greaterthan365invoice);
		brokerPaymentSheduledates.loadId(newPaymentLoadId);
		brokerPaymentSheduledates.clickPaymentDate(strDate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getBrokerLoginData", dependsOnMethods = "brokernewPaymentmorethan365")
	public void loginBrokernew(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(brokerUserName, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - PaymentsGreater than 45 Days", dataProvider = "getpayementmorethan45daysData", dependsOnMethods = "loginBrokernew")
	public void brokernewPayment45days(String cemail, String invoiceno, String loadid, String amt, String scheduledate)
			throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");

		// Store data-provider elements into publicly-accessible strings
		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUserName = CarrierRegisterTest.carrierUsername;
		} else {
			carrierUserName = carrierEmail;
		}

		// carrierEmail = cemail;
		invoiceno = TestUtil.getCurrentDateTime();
		newPaymentAmount = amt;
		newPaymentInvoiceNum = invoiceno;
		newPaymentLoadId = invoiceno;

		today = LocalDate.now();
		Integer month = today.getMonthValue() + 1;
		String strDate = month.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();
		System.out.println(strDate);

		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(carrierUserName);
		brokerPaymentSheduledates.amount(newPaymentAmount);
		greaterthan45invoice = brokerPaymentSheduledates.invoiceNumber(newPaymentInvoiceNum);
		invoiceList.add(greaterthan45invoice);
		brokerPaymentSheduledates.loadId(newPaymentLoadId);
		brokerPaymentSheduledates.clickPaymentDate(strDate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - PaymentsGreater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment45days")
	public void verifyAdminPaymentHistoryStatusdisable(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();

		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Disabled();
		admLogin.Click_paymentterm45Submit();
		driver.navigate().refresh();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();

		admLogin.Link_PayMeNowTm();
		admLogin.clickuncheckpaymennow();
		admLogin.ClickUpdatepaymenow();

		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");
	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - PaymentsGreater than 45 Days", dataProvider = "getCarrierLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatusdisable")
	public void carrierloginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUserName = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUserName = user;
			carrierPassword = pass;
		}

		driver.get(prop.getProperty("url"));
		carrierloginPage = new CarrierLoginPage();
		carrierloginPage.Carrierlogin(carrierUserName, carrierPassword);

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - PaymentsGreater than 45 Days", dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "carrierloginTest")
	public void carrierPaymenow(String cemail, String invoiceno, String loadid, String amt, String scheduledate)
			throws InterruptedException {
		Assert.assertTrue(carriersamedayach.btn_paymenow.isDisplayed(), "Payme now Link if NOT Found!");
		carrierwireframe.clickPaymenow();
		// System.out.println(greaterthan365invoice);
		carrierwireframe.clickSelectButton();
		carrierwireframe.clickConfirmButton();
		Thread.sleep(2000);
		// carrierwireframe.clickPayMeNowPayment(greaterthan45invoice);
		// System.out.println(greaterthan45invoice);
		// carrierwireframe.clickSelectButton();
		// carrierwireframe.clickConfirmButton();
		log.info("Verify Payme Now Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - PaymentsGreater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenow")
	public void verifySystemGeNnotes(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();

		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Disabled();
		admLogin.Click_paymentterm45Submit();

		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
	}

}
