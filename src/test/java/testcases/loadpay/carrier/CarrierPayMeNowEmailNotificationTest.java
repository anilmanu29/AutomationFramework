package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierPayMeNowEmailNotification;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import pages.loadpay.outlook.outlooklogin;

public class CarrierPayMeNowEmailNotificationTest extends TestBase {

	WebDriverWait wait = null;
	Actions act = null;
	CarrierPayMeNowEmailNotification carrierpaymenowemailobj;
	CarrierLoginPage carrierloginobj;
	CarrierNextDAYACH carriernexdayachobj;
	CarrierSameDAYACH carriersamedayachobj;
	CarrierOutlook carrieroutlookobj;
	CarrierWireTransfer carrierwiretransferobj;
	CarrierPaymeNowFuelCard carrierpaymenowfuelcardobj;
	outlooklogin outloginobj;
	BrokerLoginPage brokerloginobj;
	BrokerNewPayment brokernewpayobj;
	AdminHomePage adminhomepageobj;;
	AdminLogin adminloginobj;
	AdminPayByCheck adminpaybycheck;;

	String carrierUN = "";
	String carrierPWD = "";
	String brokerUN = "";
	String invoice = "";

	public CarrierPayMeNowEmailNotificationTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		act = new Actions(driver);
		carrierpaymenowemailobj = new CarrierPayMeNowEmailNotification();
		carrierloginobj = new CarrierLoginPage();
		carriernexdayachobj = new CarrierNextDAYACH();
		outloginobj = new outlooklogin();
		carrieroutlookobj = new CarrierOutlook();
		carriersamedayachobj = new CarrierSameDAYACH();
		carrierwiretransferobj = new CarrierWireTransfer();
		carrierpaymenowfuelcardobj = new CarrierPaymeNowFuelCard();
		brokerloginobj = new BrokerLoginPage();
		brokernewpayobj = new BrokerNewPayment();
		adminhomepageobj = new AdminHomePage();
		adminloginobj = new AdminLogin();
		adminpaybycheck = new AdminPayByCheck();

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dataProvider = "getCarrierLoginData")
	public void verifyCarrierLogin(String user, String pass) throws InterruptedException {
		carrierUN = user;
		carrierPWD = pass;
		carrierloginobj.Carrierlogin(carrierUN, carrierPWD);
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierLogin"}, dataProvider = "getoutlookLoginData")
	public void verifyCarrierNextDayPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		// verify PayMeNow tab
		Thread.sleep(2000);
		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		carriernexdayachobj.clickPaymenow();
		carriernexdayachobj.clickSelectButton();
		Thread.sleep(1000);
		carriernexdayachobj.clickConfirmButton();
		Thread.sleep(1000);
		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		outloginobj.outlookLogin(un, pwd);
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("email"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUN, "NextDayACH");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierNextDayPaymenowEmailNotification"}, dataProvider = "getoutlookLoginData")
	public void verifyCarrierSameDayPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		// verify PayMeNow tab
		Thread.sleep(2000);
		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		carriersamedayachobj.clickPaymenow();
		carriersamedayachobj.clickSelectButton();
		Thread.sleep(1000);
		carriersamedayachobj.clickConfirmButton();
		Thread.sleep(1000);
		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		driver.get(super.getProperties().getProperty("outlookurl"));
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("email"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUN, "SameDayACH");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierSameDayPaymenowEmailNotification" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierWireTransferPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		// verify PayMeNow tab
		Thread.sleep(2000);
		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		carrierwiretransferobj.clickPaymenow();
		carrierwiretransferobj.clickSelectButton();
		Thread.sleep(1000);
		carrierwiretransferobj.clickConfirmButton();
		Thread.sleep(1000);
		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		driver.get(super.getProperties().getProperty("outlookurl"));
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("email"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUN, "WireTransfer");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierWireTransferPaymenowEmailNotification" }, dataProvider = "getCarrierFuelcardaccountNumbersData")
	public void verifyCarrierFuelCardPaymenowEmailNotification(String fleet_accountnbr, String fts_accountnbr)
			throws InterruptedException, AWTException {
		// verify PayMeNow tab
		Thread.sleep(2000);
		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		carrierpaymenowfuelcardobj.clickPaymenow();
		carrierpaymenowfuelcardobj.clickSelectButton();
		carrierpaymenowfuelcardobj.clickaddnewcard();
		carrierpaymenowfuelcardobj.clickfleetone();
		carrierpaymenowfuelcardobj.input_accountnbr(fleet_accountnbr);
		carrierpaymenowfuelcardobj.clicksubmit();

		carrierpaymenowfuelcardobj.clickfuelcardsubmit();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickConfirmButton();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickPaidTab();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickpaymenowtab();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickPaymenow();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickSelectButton();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickaddnewcard();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickFTS();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.input_accountnbr(fts_accountnbr);
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clicksubmit();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickfuelcardsubmit();
		Thread.sleep(1000);
		carrierpaymenowfuelcardobj.clickConfirmButton();
		Thread.sleep(1000);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierFuelCardPaymenowEmailNotification" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		{
			// switch to new window
			carrierpaymenowemailobj.openandSwitchToNewWindow(1);
			driver.get(super.getProperties().getProperty("outlookurl"));
			carrieroutlookobj.clickPopUp();
			carrieroutlookobj.clickOpenMailBox();
			carrieroutlookobj.enterEmail(super.getProperties().getProperty("email"));
			carrierpaymenowemailobj.switchToNewWindow(2);
			carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUN, "FuelCard");
			driver.close();
			carrierpaymenowemailobj.switchToNewWindow(1);
			driver.close();
			carrierpaymenowemailobj.switchToNewWindow(0);
			carrierloginobj.CarrierLogout();
			Thread.sleep(2000);
		}
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierPaymenowEmailNotification" }, dataProvider = "getBrokerLoginData")
	public void verifybrokerLogin(String un, String pwd) {
		brokerUN = un;
		brokerloginobj.Brokerlogin(un, pwd);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifybrokerLogin" }, dataProvider = "getPaymentData")
	public void verifybrokerNewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		brokernewpayobj.newPayment();
		brokernewpayobj.carrierEmail(carrierUN);
		brokernewpayobj.amount(amt);
		invoice = invoiceno;
		brokernewpayobj.invoiceNumber(invoiceno);
		brokernewpayobj.loadId(loadid);
		brokernewpayobj.clickShedulePayment();
		Thread.sleep(2000);
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifybrokerNewPayment" }, dataProvider = "getAdminLoginData")
	public void verifyPayByCheckPaymenow(String un, String pwd) throws InterruptedException, AWTException {
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		adminhomepageobj.AdminURL();
		adminloginobj.adminUserPass(un, pwd);
		adminloginobj.adminLogin();
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(brokerUN);
		adminloginobj.ClickOnSearchButton();
		Thread.sleep(1000);
		adminloginobj.DoubleClickID();
		Thread.sleep(1000);
		adminpaybycheck.clickPayments();
		adminpaybycheck.ClickOnsearchKeyword(invoice);
		adminpaybycheck.getPaymentID();
		adminpaybycheck.clickSearch();
		Thread.sleep(2000);
		adminpaybycheck.searchKeyword();
		Thread.sleep(2000);
		adminpaybycheck.clickSearch1();
		Thread.sleep(2000);
		adminpaybycheck.clickgridcollapse();
		Thread.sleep(2000);
		adminpaybycheck.clickPayByCheck();
		adminpaybycheck.selectTerms();
		Thread.sleep(2000);
	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = {"verifyPayByCheckPaymenow"} )
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String ContactName) throws InterruptedException {
		adminpaybycheck.EnterDOTNnumber(EnterDOTNnumber);
		Thread.sleep(3000);
		adminpaybycheck.ContactName(ContactName);
		Thread.sleep(3000);
		adminpaybycheck.clickPayByChecksubmit();
		Thread.sleep(3000);
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"carrierPaymenowPayByCheck" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierPayByCheckPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		{
			// switch to new window
			carrierpaymenowemailobj.openandSwitchToNewWindow(2);
			driver.get(super.getProperties().getProperty("outlookurl"));
			carrieroutlookobj.clickPopUp();
			carrieroutlookobj.clickOpenMailBox();
			carrieroutlookobj.enterEmail(super.getProperties().getProperty("email"));
			carrierpaymenowemailobj.switchToNewWindow(3);
			carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUN, "Check");
			Thread.sleep(2000);
		}
	}

}
