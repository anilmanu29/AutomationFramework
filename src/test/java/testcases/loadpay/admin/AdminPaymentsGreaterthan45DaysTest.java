package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.outlook.outlooklogin;

public class AdminPaymentsGreaterthan45DaysTest extends TestBase {

	BrokerPaymentSheduledates brokerPaymentSheduledates;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	BrokerLoginPage brokLoginPage;
	CarrierLoginPage loginPage;
	WebElement checkbox;
	String brokerUserName;
	String brokerPassword;
	BrokerOutlook brokerOutlookObj;
	CarrierOutlook carierOutlookObj;
	outlooklogin outlook;
	BrokerLoginPage brokerlogin;
	String payment_status = "Verified";
	String invoice;
	ArrayList<String> arraylist;
	String email;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	CarrierLoginPage carrierloginPage;

	String carrierUserName;
	String carrierPassword;

	CarrierSameDAYACH carriersamedayach;

	/*-------Initializing driver---------*/
	public AdminPaymentsGreaterthan45DaysTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokLoginPage = new BrokerLoginPage();
		currentTime = new Date();
		outlook = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
		carrierloginPage = new CarrierLoginPage();
		carierOutlookObj = new CarrierOutlook();
		brokerlogin = new BrokerLoginPage();
		arraylist = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		carriersamedayach = new CarrierSameDAYACH();
		wait = new WebDriverWait(driver, 30);
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		brokerUserName = user;
		brokerPassword = pass;
	}

	@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminPaymentHistoryStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.select_greaterThan45daysId();
		admLogin.Click_paymentterm45Submit();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();

	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatus")
	public void loginBroker(String un, String pwd) {
		driver.get(super.prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "loginBroker")
	public void brokernewPaymentmorethan365(String cemail, String invoiceno, String loadid, String amt,
			String scheduledate) throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(cemail);
		brokerPaymentSheduledates.amount(amt);
		invoice = brokerPaymentSheduledates.invoiceNumber(invoiceno);
		arraylist.add(invoice);
		brokerPaymentSheduledates.loadId(loadid);
		brokerPaymentSheduledates.clickPaymentDate(scheduledate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();

	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "brokernewPaymentmorethan365")
	public void loginBrokernew(String un, String pwd) {
		driver.get(super.prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	@Test(dataProvider = "getpayementmorethan45daysData", dependsOnMethods = "loginBrokernew")
	public void brokernewPayment45days(String cemail, String invoiceno, String loadid, String amt, String scheduledate)
			throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(cemail);
		brokerPaymentSheduledates.amount(amt);
		invoice = brokerPaymentSheduledates.invoiceNumber(invoiceno);
		arraylist.add(invoice);
		brokerPaymentSheduledates.loadId(loadid);
		brokerPaymentSheduledates.clickPaymentDate(scheduledate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment45days")
	public void verifyAdminPaymentHistoryStatusdisable(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectgreaterThan45daysId();
		admLogin.Click_paymentterm45Submit();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
		admLogin.Link_PayMeNowTm();
		admLogin.clickuncheckpaymennow();
		admLogin.ClickUpdatepaymenow();
		admLogin.ClickCloseButon();
		admLogin.AdminLogOut();

	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatusdisable")
	public void carrierloginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;

		loginPage.Carrierlogin(carrierUserName, carrierPassword);
	}

	@Test(dependsOnMethods = "carrierloginTest")
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		carriersamedayach.clickPaymenow();
		carriersamedayach.clickSelectButton();
		carriersamedayach.clickConfirmButton();
	}

}
