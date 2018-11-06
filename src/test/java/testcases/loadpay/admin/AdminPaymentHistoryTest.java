package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.outlook.outlooklogin;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminPaymentHistoryTest extends TestBase {

	AdminHomePage admHomePage;
	AdminLogin admLogin;
	BrokerLoginPage brokLoginPage;
	CarrierNextDAYACH carrierNextDayObj;
	CarrierLoginPage carrierloginPage;
	WebElement checkbox;

	BrokerOutlook brokerOutlookObj;
	CarrierOutlook carierOutlookObj;
	outlooklogin outlook;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	String brokerUserName;
	String brokerPassword;
	String carrierUserName;
	String carrierPassword;

	/*-------Initializing driver---------*/
	public AdminPaymentHistoryTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokLoginPage = new BrokerLoginPage();
		currentTime = new Date();
		outlook = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
		carrierloginPage = new CarrierLoginPage();
		carierOutlookObj = new CarrierOutlook();
		carrierNextDayObj = new CarrierNextDAYACH();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUserName = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUserName = user;
			brokerPassword = pass;
		}
	}

	@Test(dependsOnMethods = "getBrokerCredentials")
	public void carrierPayMeNowNextDayACH() throws InterruptedException, AWTException {
		carrierloginPage.Carrierlogin(CarrierRegisterTest.carrierUsername, CarrierRegisterTest.carrierPassword);

		carrierNextDayObj.clickPaymenow();
		carrierNextDayObj.clickSelectButton();
		carrierNextDayObj.clickConfirmButton();

		Thread.sleep(2000);

		if (carrierloginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierloginPage.getDonotshowagaincheckbox().click();
			carrierloginPage.getPayMeNowPopupSaveButton().click();
		}

		driver.get(super.getProperties().getProperty("nachaPaymentURL"));

		driver.get(super.getProperties().getProperty("emailBatchPaymentURL"));
	}

	@Test(description = "LP-5428 AdminPaymentHistory_PaymentStatusTest", dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPayMeNowNextDayACH")
	public void verifyAdminPaymentHistoryStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.clickPaymentHistory();
		admLogin.clickpaymnt_Historydownload();
		admLogin.clicforwardfile();
		admLogin.EnterEmailTo(brokerUserName);
		// TODO add cancel test and verify email is not sent
		// admLogin.ClickCancelSendEmailToVerify();
		// verify email not sent to outlook
		admLogin.ClickSendEmailToVerify();
		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatus")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("email"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokerOutlookObj.outlookSearchInbox(brokerUserName, currentHour, currentMinutes);

		// TODO look through inbox for attachment
		// brokerOutlookObj.handleNewInbox();
		driver.close();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "outlookloginTest")
	public void carrierloginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierloginTest")
	public void verifyAdminPaymentHistoryStatuscarrier(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();

		if (admLogin.getLoginBtn().isDisplayed()) {
			admLogin.adminUserPass(Username, pass);
			admLogin.adminLogin();
		}

		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(carrierUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.clickcarrierPaymentHistory();
		admLogin.clickpaymntcarrierHistorydownload();
		admLogin.cliccarrierforwardfile();
		admLogin.EnterEmailTo(carrierUserName);
		admLogin.ClickSendEmailToVerify();
		admLogin.AdminLogOut();
	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatuscarrier")
	public void outlookcarrierloginTest(String un, String pwd) throws InterruptedException, AWTException {
		driver.get(prop.getProperty("outlookurl"));
		carierOutlookObj.clickPopUp();
		carierOutlookObj.clickOpenMailBox();
		carierOutlookObj.enterEmail(super.prop.getProperty("email"));
		carierOutlookObj.outlookSearchInbox(carrierUserName, currentHour, currentMinutes);

		/* carrieroutlookk.handleNewInbox(); */
		/* outlookk.verifyConfirmationMessage(); */

	}

	public void verifyAdminPaymentHistoryTestDisplayed() {
		// Verify that the web elements for the AdminPaymentHistoryTest exist
		Assert.assertTrue(admLogin.search.isDisplayed(), " Broker Email ID should not be found");
		Assert.assertTrue(admLogin.paymnt_Historydownload.isDisplayed(), "Payment History Download not found");
		Assert.assertTrue(admLogin.emailTo.isDisplayed(), "Email To not found");
		Assert.assertTrue(brokerOutlookObj.fieldTextbox.isDisplayed(), "Broker OutLook Email should not be found");
		Assert.assertTrue(carierOutlookObj.fieldTextbox.isDisplayed(), "Carrier OutLook Email should not be found");
	}

}