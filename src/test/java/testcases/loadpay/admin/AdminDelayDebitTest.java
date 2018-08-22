package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminCancelPayByCheck;
import pages.loadpay.admin.AdminCustomersSideMenuSearch;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import pages.loadpay.broker.SchpaymentwithoutBankAccountPayByInvoiceEnabled;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.outlook.outlooklogin;

public class AdminDelayDebitTest extends TestBase {
	SchpaymentwithoutBankAccountPayByInvoiceEnabled schpaymentwithoutBankAccountPayByInvoiceenabled;
	BrokerPaymentSheduledates brokerPaymentSheduledates;
	AdminCustomersSideMenuSearch adminCustomersSideMenuSearch;
	AdminCancelPayByCheck adminPayByCheckObj;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	WebElement checkbox;
	AdminPayByCheck adminExpand;
	String payment_status = "Verified";
	String brokerUsername;
	String brokerPassword;
	BrokerLoginPage brokerlogin;
	String invoice;
	ArrayList<String> invoiceList;
	String email;
	CarrierLoginPage carrierloginPage;
	String carrierUserName;
	String carrierPassword;
	BrokerOutlook brokerOutlookObj;
	outlooklogin outlook;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	String emailid = "";

	/*-------Initializing driver---------*/
	public AdminDelayDebitTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokerlogin = new BrokerLoginPage();
		carrierloginPage = new CarrierLoginPage();
		brokerlogin = new BrokerLoginPage();
		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		adminExpand = new AdminPayByCheck();
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		adminCustomersSideMenuSearch = new AdminCustomersSideMenuSearch();
		schpaymentwithoutBankAccountPayByInvoiceenabled = new SchpaymentwithoutBankAccountPayByInvoiceEnabled();
		adminPayByCheckObj = new AdminCancelPayByCheck();
		outlook = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		brokerUsername = user;
		brokerPassword = pass;
	}

	/*-------Admin Login ---------*/

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyDelayDebit(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUsername);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.Link_delaydebit();
		admLogin.ClickEditDelayDebit();
		admLogin.select_DelayDebitEnabled();
		admLogin.Click_UpdateDelayDebit();
		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyDelayDebit")
	public void loginBroker(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		brokerPaymentSheduledates.lnkMyAccount();
		brokerPaymentSheduledates.clicklnk_PayMeNow();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");
		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(cemail);
		brokerPaymentSheduledates.amount(amt);
		invoice = brokerPaymentSheduledates.invoiceNumber(invoiceno);
		invoiceList.add(invoice);
		brokerPaymentSheduledates.loadId(loadid);
		brokerPaymentSheduledates.clickShedulePayment();
		log.info("Verify New Payment Link Passed");

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		admHomePage.AdminURL();

		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		log.info(BrokerLoginPage.bemail);
		admLogin.ClickOnSearchBox(BrokerLoginPage.bemail);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		adminPayByCheckObj.clickPayments();
		adminPayByCheckObj.ClickOnsearchKeyword(adminPayByCheckObj.getPaymentID1().getText());
		adminPayByCheckObj.getPaymentID();
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollaps();
		Assert.assertTrue(brokerPaymentSheduledates.anticipatedwidrawldate.isDisplayed(),
				"get text of Anticipated Date if NOT Found!");
		brokerPaymentSheduledates.getanticipatedwidrawlDate();

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyAdminPayByCheck")
	public void verifyDelayDebitpaymenow(String Username, String pass) throws InterruptedException, AWTException {
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if  NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUsername);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.Link_delaydebit();
		admLogin.ClickEditDelayDebit();
		admLogin.select_DelayDebitEnabled();
		admLogin.Click_UpdateDelayDebit();
		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");

	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyDelayDebitpaymenow")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("email"));
		getTimestamp();
		brokerOutlookObj.outlookSearchInbox(brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox();

	}

	public void getTimestamp() {
		/////////////////////////////////////////////////////////////////
		TimeZone tz = Calendar.getInstance().getTimeZone();
		String currentTimeZone = tz.getDisplayName();
		System.out.println(currentTimeZone);

		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];

		System.out.println("\n\n\n===============================");
		System.out.println("Current date: " + longTime);
		System.out.println("Formatted date: " + formattedDate);
		System.out.println("Current Hour: " + currentHour);
		System.out.println("Current Minutes: " + currentMinutes);
		System.out.println("===============================");
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dependsOnMethods = "outlookloginTest")
	public void verifypaymenow() {
		driver.get(prop.getProperty("url"));
		brokerPaymentSheduledates.lnkMyAccount();
		brokerPaymentSheduledates.clicklnk_PayMeNow();

	}

}
