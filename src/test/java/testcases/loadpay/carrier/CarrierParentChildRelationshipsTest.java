package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierParentChildRelationships;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class CarrierParentChildRelationshipsTest extends TestBase {
	CarrierLoginPage loginPage;
	BrokerLoginPage brokerlogin;
	BrokerNewPayment brokerNewPaymentObj;
	CarrierParentChildRelationships carrierchildrelation;
	CarrierSameDAYACH carriersamedayach;
	CarrierNextDAYACH carriernextdayach;
	CarrierWireTransfer carrierwiretransferach;
	CarrierPaymeNowFuelCard carrierpaymenowfuelcard;
	BrokerOutlook brokeroutlook;
	AdminHomePage adminHomePageObj;
	AdminLogin adminLoginObj;
	AdminPayByCheck adminPayByCheckObj;

	public String invoicenum;
	public static ArrayList<String> arraylist;

	String pwd;
	JavascriptExecutor jse;
	public static String nemail;

	String carrierUsername, carrierPassword = "";
	String brokerUsername, brokerPassword = "";

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public CarrierParentChildRelationshipsTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		carrierchildrelation = new CarrierParentChildRelationships();
		jse = (JavascriptExecutor) driver;
		brokeroutlook = new BrokerOutlook();
		carriersamedayach = new CarrierSameDAYACH();
		carriernextdayach = new CarrierNextDAYACH();
		carrierwiretransferach = new CarrierWireTransfer();
		carrierpaymenowfuelcard = new CarrierPaymeNowFuelCard();
		brokerlogin = new BrokerLoginPage();
		brokerNewPaymentObj = new BrokerNewPayment();
		adminLoginObj = new AdminLogin();
		adminHomePageObj = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		arraylist = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginAsCarrier(String un, String pwd) throws InterruptedException {
		// login as carrier

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = un;
			carrierPassword = pwd;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);
	}

	@Test(dataProvider = "getCarrierParentChildData", dependsOnMethods = { "loginAsCarrier" })
	public void verifyAlertMessageTest(String fn, String ln, String email, String nemailid)
			throws InterruptedException {
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.clickAddUserButton();
		carrierchildrelation.enterFirstName(fn);
		carrierchildrelation.enterLastName(ln);
		carrierchildrelation.enterNewEmailID(email);
		carrierchildrelation.clickSaveButton();
		Thread.sleep(2000);
		Assert.assertTrue(
				carrierchildrelation.getAlertMessage().contains("Email address already linked to a LoadPay account"),
				"Alert message is NOT Displayed");
	}

	@Test(dataProvider = "getCarrierParentChildData", dependsOnMethods = { "verifyAlertMessageTest" })
	public void verifyAddChildAccount(String fn, String ln, String email, String nemailid) throws InterruptedException {
		// check how many users there are...10 max
		carrierchildrelation.clickCancelButton();
		carrierchildrelation.clickAddUserButton();
		carrierchildrelation.enterFirstName(fn);
		carrierchildrelation.enterLastName(ln);
		nemailid = "carrier" + TestUtil.getCurrentDateTime() + "@loadpaytest.truckstop.com";
		nemail = carrierchildrelation.enterNewEmailID(nemailid);
		carrierchildrelation.enablePaymentAccess();
		carrierchildrelation.clickSaveButton();
		Thread.sleep(5000);
		verifyEmailAddress(nemail);
	}

	//
	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = { "verifyAddChildAccount" })
	public void verifyEmailAddress(String un, String pwd) throws InterruptedException {
		carrierchildrelation.outlookLogin(un, pwd);
		carrierchildrelation.getVerificationCodeData();
		carrierchildrelation.enterVerificationCode();
		carrierchildrelation.clickVerifyButton();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.close();
		driver.switchTo().window(tabs.get(1));
		driver.get(super.getProperties().getProperty("outlookURL"));
		brokeroutlook.clickPopUp();
		brokeroutlook.clickOpenMailBox();
		brokeroutlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokeroutlook.outlookSearchInbox(nemail + " AND Reset", currentHour, currentMinutes);
	}

	@Test(dataProvider = "getCarrierParentChildPasswordData", dependsOnMethods = { "verifyEmailAddress" })
	public void verifyResetPassword(String nwpwd, String confmpwd, String forcepwd, String confirmforcepwd)
			throws InterruptedException {
		carrierchildrelation.clickResetPasswordButton(nwpwd, confmpwd);
		pwd = nwpwd;

	}

	@Test(dataProvider = "getCarrierFuelcardaccountNumbersData", dependsOnMethods = { "verifyResetPassword" })
	public void verifyChildAccountLogin(String fleet_accountnbr, String fts_accountnbr)
			throws InterruptedException, AWTException {

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

		Calendar calendar = Calendar.getInstance();

		// Same day ach cuts off at 11am CST
		if (calendar.get(Calendar.HOUR_OF_DAY) < 11) {
			carriersamedayach.getAmount();
			carriersamedayach.clickPaymenow();
			carriersamedayach.getsamedayAmount();
			carriersamedayach.clickSelectButton();
			carriersamedayach.clickConfirmButton();
			carriersamedayach.gettotalpaiyAmount();
		}

		carriernextdayach.getAmount();
		carriernextdayach.clickPaymenow();
		carriernextdayach.clickSelectButton();
		carriernextdayach.clickConfirmButton();
		carriernextdayach.gettotalpaiyAmount();

		if ((calendar.get(Calendar.HOUR_OF_DAY) < 16) && (calendar.get(Calendar.MINUTE) < 30)) {
			carrierwiretransferach.getAmount();
			carrierwiretransferach.clickPaymenow();
			carrierwiretransferach.clickSelectButton();
			carrierwiretransferach.clickConfirmButton();
		}

		carrierpaymenowfuelcard.clickPaymenow();
		carrierpaymenowfuelcard.clickSelectButton();
		carrierpaymenowfuelcard.clickaddnewcard();
		carrierpaymenowfuelcard.clickfleetone();
		carrierpaymenowfuelcard.input_accountnbr(fleet_accountnbr);
		carrierpaymenowfuelcard.clicksubmit();
		carrierpaymenowfuelcard.clickfuelcardsubmit();
		carrierpaymenowfuelcard.clickConfirmButton();

		carrierpaymenowfuelcard.clickPaymenow();
		carrierpaymenowfuelcard.clickSelectButton();
		carrierpaymenowfuelcard.clickaddnewcard();
		carrierpaymenowfuelcard.clickFTS();
		carrierpaymenowfuelcard.input_accountnbr(fts_accountnbr);
		carrierpaymenowfuelcard.clicksubmit();
		carrierpaymenowfuelcard.clickfuelcardsubmit();
		carrierpaymenowfuelcard.clickConfirmButton();
		loginPage.CarrierLogout();
	}

	@Test(dataProvider = "getCarrierParentChildPasswordData", dependsOnMethods = { "verifyChildAccountLogin" })
	public void verifyForcedPasswordReset(String pwd, String confpwd, String forcepwd, String confirmforcepwd)
			throws InterruptedException {
		loginPage.Carrierlogin(carrierUsername, carrierPassword);
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.forcePasswordReset();
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(super.getProperties().getProperty("outlookURL"));
		brokeroutlook.clickPopUp();
		brokeroutlook.clickOpenMailBox();
		brokeroutlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierchildrelation.resetPassword(forcepwd, confirmforcepwd);

	}

	@Test(dataProvider = "getCarrierParentChildData", dependsOnMethods = { "verifyForcedPasswordReset" })
	public void verifyEditAccountTest(String fn, String ln, String email, String nemailid) throws InterruptedException {
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.editAccount();
		carrierchildrelation.enterFirstName(ln);
		carrierchildrelation.enterLastName(fn);
		carrierchildrelation.clickSaveButton();
		carrierchildrelation.carrierLogOut();
	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = { "verifyEditAccountTest" })
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}

		brokerlogin.Brokerlogin(brokerUsername, brokerPassword);

	}

	@Test(dataProvider = "getPaymentData", dependsOnMethods = { "loginTest" })
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		brokerNewPaymentObj = new BrokerNewPayment();
		brokerNewPaymentObj.newPayment();

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			invoiceno = "NP" + TestUtil.getCurrentDateTime();
			loadid = invoiceno;
		}

		BrokerNewPaymentTest.email = brokerNewPaymentObj.carrierEmail(carrierUsername);

		brokerNewPaymentObj.amount(amt);

		invoicenum = brokerNewPaymentObj.invoiceNumber(invoiceno);
		arraylist.add(invoicenum);

		brokerNewPaymentObj.loadId(loadid);
		brokerNewPaymentObj.clickShedulePayment();
		brokerNewPaymentObj.clickShedulePaymenttab();
		brokerNewPaymentObj.searchCarrier(carrierUsername);
		brokerNewPaymentObj.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);

		brokerNewPaymentObj.verifyInvoiceNumber(invoiceno, amt);
		log.info(brokerNewPaymentObj.verifyPaymentStatus());
		// brokerNewPaymentObj.logout();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "brokernewPayment" })
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		adminHomePageObj.AdminURL();

		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomerTab()));
		adminLoginObj.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getSearch()));
		log.info(BrokerLoginPage.bemail);
		adminLoginObj.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getClickonSearchButton()));
		adminLoginObj.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getDoubleClickID()));
		adminLoginObj.DoubleClickID();

		adminPayByCheckObj.clickPayments();
		adminPayByCheckObj.ClickOnsearchKeyword(CarrierParentChildRelationshipsTest.arraylist.get(0));
		adminPayByCheckObj.getPaymentID(CarrierParentChildRelationshipsTest.arraylist.get(0));
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollapse();
		adminPayByCheckObj.clickPayByCheck();
		adminPayByCheckObj.selectTerms();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = { "verifyAdminPayByCheck" })
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String companyName, String streetAddress, String city,
			String state, String zip, String country, String phone, String contactName) throws InterruptedException {
		adminPayByCheckObj.setCarrierDOT(TestUtil.removeDecimalZeroFormat(EnterDOTNnumber));
		adminPayByCheckObj.setCarrierStreet(streetAddress);
		adminPayByCheckObj.setCarrierCity(city);
		adminPayByCheckObj.setCarrierState(state);
		adminPayByCheckObj.setCarrierZIP(TestUtil.removeDecimalZeroFormat(zip));
		adminPayByCheckObj.setCarrierCountry(country);
		adminPayByCheckObj.setCarrierPhone(phone);
		adminPayByCheckObj.setCarrierContactName(contactName);
		adminPayByCheckObj.setCarrierCompanyName(companyName);
		adminPayByCheckObj.clickPayByChecksubmit();
		adminLoginObj.AdminLogOut();

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "carrierPaymenowPayByCheck" })
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {

		adminHomePageObj.AdminURL();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getUserName()));
		adminLoginObj.adminUserPass(Username, pass);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getLoginBtn()));
		adminLoginObj.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getCustomerTab()));
		adminLoginObj.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getSearch()));
		log.info(BrokerLoginPage.bemail);
		adminLoginObj.ClickOnSearchBox(BrokerLoginPage.bemail);
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getClickonSearchButton()));
		adminLoginObj.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(adminLoginObj.getDoubleClickID()));
		adminLoginObj.DoubleClickID();

		adminPayByCheckObj.clickPayments();
		adminPayByCheckObj.ClickOnsearchKeyword(CarrierParentChildRelationshipsTest.arraylist.get(1));
		adminPayByCheckObj.getPaymentID(CarrierParentChildRelationshipsTest.arraylist.get(1));
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollapse();
		adminPayByCheckObj.clickPayByCheck();
		adminPayByCheckObj.selectTerms();
		adminPayByCheckObj.selectTermsTermPayment();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = {
			"verifyAdminPayByCheckTermPayment" })
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String companyName, String streetAddress,
			String city, String state, String zip, String country, String phone, String contactName)
			throws InterruptedException {
		adminPayByCheckObj.setCarrierDOT(TestUtil.removeDecimalZeroFormat(EnterDOTNnumber));
		adminPayByCheckObj.setCarrierStreet(streetAddress);
		adminPayByCheckObj.setCarrierCity(city);
		adminPayByCheckObj.setCarrierState(state);
		adminPayByCheckObj.setCarrierZIP(TestUtil.removeDecimalZeroFormat(zip));
		adminPayByCheckObj.setCarrierCountry(country);
		adminPayByCheckObj.setCarrierPhone(phone);
		adminPayByCheckObj.setCarrierContactName(contactName);
		adminPayByCheckObj.setCarrierCompanyName(companyName);
		adminPayByCheckObj.clickPayByChecksubmit();
		adminLoginObj.AdminLogOut();

	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = { "carrierTermPaymentPayByCheck" })
	public void verifyDeleteChildAccountTest(String un, String password) throws InterruptedException {
		driver.get(super.getProperties().getProperty("loadPayURL"));
		loginPage.Carrierlogin(carrierUsername, carrierPassword);
		carrierchildrelation.clickAccountLink();
		carrierchildrelation.clickEmailLink();
		carrierchildrelation.deleteChildAccount();

	}

	public void verifyEmailAddress(String nemailid) throws InterruptedException {

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='ellipsis ng-binding']"));
		for (WebElement e : list) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			// log.info(e.getText());
			if (e.getText().contains(nemailid)) {
				Assert.assertTrue(e.getText().contains(nemailid));
				break;
			}

		}

	}
}
