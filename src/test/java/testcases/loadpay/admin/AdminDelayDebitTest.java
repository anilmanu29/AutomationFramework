package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import pages.loadpay.outlook.outlooklogin;

public class AdminDelayDebitTest extends TestBase {

	BrokerPaymentSheduledates brokerPaymentSheduledates;
	AdminCustomersSideMenuSearch adminCustomersSideMenuSearch;
	AdminCancelPayByCheck adminPayByCheckObj;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	CarrierWireTransfer carrierwireframe;
	WebElement checkbox;
	AdminPayByCheck adminExpand;
	String payment_status = "Verified";
	String brokerUserName;
	String brokerPassword;
	BrokerLoginPage brokerlogin;
	ArrayList<String> brokerInvoices;
	String invoice;
	ArrayList<String> invoiceList;
	String email;
	CarrierLoginPage carrierloginPage;
	String carrierUserName;
	String carrierPassword;
	CarrierSameDAYACH carriersamedayach;
	BrokerOutlook brokerOutlookObj;
	outlooklogin outlook;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

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
		carrierwireframe = new CarrierWireTransfer();
		brokerlogin = new BrokerLoginPage();
		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		adminExpand = new AdminPayByCheck();
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		adminCustomersSideMenuSearch = new AdminCustomersSideMenuSearch();
		adminPayByCheckObj = new AdminCancelPayByCheck();
		outlook = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		brokerUserName = user;
		brokerPassword = pass;
	}

	/*-------Admin Login ---------*/

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyDelayDebit(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		// Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if
		// NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
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
		brokerPaymentSheduledates.getanticipatedwidrawlDate();

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyAdminPayByCheck")
	public void verifyDelayDebitpaymenow(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if  NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.Link_delaydebit();
		admLogin.ClickEditDelayDebit();
		admLogin.select_DelayDebitEnabled();
		admLogin.Click_UpdateDelayDebit();
		// admLogin.click_unenrolldelaydebit();
		// admLogin.clicklnkAdminPayMeNow();
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
		brokerOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		// outlookk.clickOpen();
		getTimestamp();
		brokerOutlookObj.outlookSearchInbox(brokerUserName, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox();
		brokerOutlookObj.verifyConfirmationMessage();

	}

	public void getTimestamp() {
		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];

		log.info("\n\n\n===============================");
		log.info("Current date: " + longTime);
		log.info("Formatted date: " + formattedDate);
		log.info("Current Hour: " + currentHour);
		log.info("Current Minutes: " + currentMinutes);
		log.info("===============================");
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData", dependsOnMethods = "outlookloginTest")
	public void verifypaymenow(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);
		brokerPaymentSheduledates.lnkMyAccount();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");
		brokerPaymentSheduledates.clicklnk_PayMeNow();

	}

}
