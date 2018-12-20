package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class AdminEnableDisableLessThan14_DaysPaymentTermsTest extends TestBase {

	BrokerPaymentSheduledates brokerPaymentSheduledates;
	BrokerLoginPage brokerlogin;
	AdminHomePage admHomePage;
	AdminLogin admLogin;

	String brokerUserName;
	String brokerPassword;
	ArrayList<String> invoiceList;
	String email;
	LocalDate today;
	String lessThan14Daysinvoice = "";
	public static String newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum, carrierEmail = "";

	/*-------Initializing driver---------*/
	public AdminEnableDisableLessThan14_DaysPaymentTermsTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokerlogin = new BrokerLoginPage();
		brokerlogin = new BrokerLoginPage();
		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		wait = new WebDriverWait(driver, 30);
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("loadPayURL"));

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUserName = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUserName = user;
			brokerPassword = pass;
		}
	}

	/*-------Admin Login and verify Admin enabled Less than 14 days terms ---------*/

	@Test(description = "LP-7857 LoadPay - Selenium Test - Admin user to be able to enable or disable < 14 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminEnableLessThan14Days(String Username, String pass)
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
		admLogin.clickEditLessThan14Days();
		admLogin.selectLessThan14Days_Enabled();
		admLogin.submitLessThan14Days();

		admLogin.Click_Notes();
		admLogin.systemGeneratedNoteEnabledLessThan14Days();
		admLogin.Clickclosenotesbutton();

		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Admin Login and verify Admin enabled Less than 14 days terms");

	}

	/*-------Broker Login ---------*/
	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminEnableLessThan14Days")
	public void loginBroker(String un, String pwd) {
		driver.get(prop.getProperty("loadPayURL"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(brokerUserName, brokerPassword);

	}

	/*-------Broker able to schedule payment less than 14 days---------*/

	@Test(dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "loginBroker")
	public void brokernewPaymentLessThan14Days(String cemail, String invoiceno, String loadid, String amt,
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
		Integer year = today.getYear();
		Integer day = today.getDayOfMonth();
		String strDate = today.getMonthValue() + "/" + day.toString() + "/" + year.toString();

		System.out.println(strDate);

		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(carrierEmail);
		brokerPaymentSheduledates.amount(newPaymentAmount);
		lessThan14Daysinvoice = brokerPaymentSheduledates.invoiceNumber(newPaymentInvoiceNum);
		invoiceList.add(lessThan14Daysinvoice);
		brokerPaymentSheduledates.loadId(newPaymentLoadId);
		brokerPaymentSheduledates.clickPaymentDate(strDate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.paymentBetween14And45DaysSucessful();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment is scheduled");

	}

	/*-------Admin Login and verify Admin disabled Less than 14 days terms  ---------*/

	@Test(description = "LP-7857 LoadPay - Selenium Test - Admin user to be able to enable or disable < 14 Days", dataProvider = "getAdminLoginData", 
			dependsOnMethods = "brokernewPaymentLessThan14Days")
	public void verifyAdminDisableLessThan14Days(String Username, String pass)
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
		admLogin.clickEditLessThan14Days();
		admLogin.selectLessThan14Days_Disabled();
		admLogin.submitLessThan14Days();

		admLogin.Click_Notes();
		admLogin.systemGeneratedNoteDisabledLessThan14Days();
		admLogin.Clickclosenotesbutton();

		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Admin Login and verify Admin disabled Less than 14 days terms");

	}

	/*-------Broker Login ---------*/
	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminDisableLessThan14Days")
	public void loginBrokerSecondTime(String un, String pwd) {
		driver.get(prop.getProperty("loadPayURL"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(brokerUserName, brokerPassword);

	}

	/*-------Broker unable to schedule payment less than 14 days---------*/

	@Test(dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "loginBrokerSecondTime")
	public void brokerUnableSchedulePaymentLessThan14Days(String cemail, String invoiceno, String loadid, String amt,
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
		Integer year = today.getYear();
		Integer day = today.getDayOfMonth();
		String strDate = today.getMonthValue() + "/" + day.toString() + "/" + year.toString();

		System.out.println(strDate);

		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(carrierEmail);
		brokerPaymentSheduledates.amount(newPaymentAmount);
		lessThan14Daysinvoice = brokerPaymentSheduledates.invoiceNumber(newPaymentInvoiceNum);
		invoiceList.add(lessThan14Daysinvoice);
		brokerPaymentSheduledates.loadId(newPaymentLoadId);
		brokerPaymentSheduledates.clickPaymentDate(strDate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.paymentBetween14And45DaysNotHappening();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment is not scheduled");

	}

}
