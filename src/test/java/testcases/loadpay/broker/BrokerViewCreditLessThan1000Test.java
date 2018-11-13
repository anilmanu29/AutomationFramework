package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerViewCreditLessThan1000;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.outlook.outlooklogin;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerViewCreditLessThan1000Test extends TestBase {
	// BrokerLoginPage loginPage;
	BrokerViewCreditLessThan1000 CreditLessThan1000;
	BrokerOutlook brokeroutlook;
	outlooklogin outlooklog;
	CarrierLoginPage carrierloginobj;
	CarrierNextDAYACH carrierNextDayObj;
	AdminHomePage adminhomeobj;
	AdminLogin adminloginobj;
	String newcreditAmount = "";
	String carrierUsername, carrierPassword, brokerUsername, brokerPassword, adminUsername, adminPassword = "";

	public BrokerViewCreditLessThan1000Test() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		CreditLessThan1000 = new BrokerViewCreditLessThan1000();
		brokeroutlook = new BrokerOutlook();
		outlooklog = new outlooklogin();
		carrierloginobj = new CarrierLoginPage();
		carrierNextDayObj = new CarrierNextDAYACH();
		wait = new WebDriverWait(driver, 30);
		adminhomeobj = new AdminHomePage();
		adminloginobj = new AdminLogin();
	}

	@AfterClass
	public void tearDown() throws IOException, InterruptedException, AWTException {
		updateCreditAmount("999999");
	}

	@Test(dataProvider = "getAdminLoginData")
	public void getAdminCredentials(String username, String password) {
		adminUsername = username;
		adminPassword = password;
	}

	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "getAdminCredentials")
	public void updateCarrierCreditAndPayMeNow(String user, String pass)
			throws InterruptedException, IOException, AWTException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		updateCreditAmount("1000");

		driver.get(super.getProperties().getProperty("loadPayURL"));
		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
		carrierNextDayObj.clickPaymenow();
		carrierNextDayObj.clickSelectButton();
		carrierNextDayObj.clickConfirmButton();
		Thread.sleep(1000);

		if (carrierloginobj.getDonotshowagaincheckbox().isDisplayed()) {
			carrierloginobj.closePaymeNowPopUp();
		}

		carrierloginobj.CarrierLogout();
	}

	@Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "updateCarrierCreditAndPayMeNow")
	public void loginAsBrokerAndViewCredit(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}

		CreditLessThan1000.Brokerlogin(brokerUsername, brokerPassword);

		// make sure pay me now is enabled to view available credit
		CreditLessThan1000.clickAccountLink();
		CreditLessThan1000.clickPayMeNowLink();
		CreditLessThan1000.enrollInPayMeNow();

		CreditLessThan1000.AvailableCreditTab();
		CreditLessThan1000.BrokerLogout();
	}

	@Test(description = "Broker Sees 10% Notification Email in Outlook", dataProvider = "getoutlookLoginData", dependsOnMethods = "loginAsBrokerAndViewCredit")
	public void BrokerOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlooklog.outlookLogin(un, pwd);
			brokeroutlook.clickPopUp();
			brokeroutlook.clickOpenMailBox();
			brokeroutlook.enterEmail(super.prop.getProperty("loadpaytestEmail"));
			SearchInbox("Broker/Shipper Credit Limit 10% Notification");
			// brokeroutlook.quit();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SearchInbox(String SearchText) throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement e : list) {
			Thread.sleep(2000);
			e.click();
			Thread.sleep(1000);
			// System.out.println(e.getText());
			if (e.getText().contains(SearchText)) {
				Thread.sleep(1000);
				Assert.assertTrue(e.getText().contains(SearchText));
				break;
			}

		}
	}

	public void updateCreditAmount(String creditAmount) throws IOException, InterruptedException, AWTException {
		adminhomeobj.AdminURL();
		adminloginobj.adminUserPass(adminUsername, adminPassword);
		adminloginobj.adminLogin();
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.Uncheck_Factor();
		adminloginobj.ClickOnSearchBox(BrokerRegisterTest.brokerUsername);
		adminloginobj.ClickOnSearchButton();
		adminloginobj.DoubleClickID();
		adminloginobj.StatusIDDropDown();
		adminloginobj.UpdateButton();
		adminloginobj.ClickOnCreditTab();
		adminloginobj.EnterExtendedCredit(creditAmount);
		adminloginobj.ClickOnCreditSubmitButton();
		adminloginobj.AdminLogOut();
	}
}
