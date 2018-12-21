package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.outlooklogin;
import pages.loadpay.admin.AdminEditEmailCarrier;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierPasswordSetupResetPage;
import pages.loadpay.carrier.ResetPassword;
import util.TestUtil;

public class CarrierLoginPageResetPasswordTest extends TestBase {
	CarrierPasswordSetupResetPage CarrierPasswordSetupResetPage;
	ResetPassword resetPassword;
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminEditEmailCarrier adminEmailPage;
	outlooklogin outlookLoginObj;
	CarrierLoginPage carrierLoginObj;
	CarrierOutlook carrierOutlookObj;
	// CarrierRegisterPage carrierRegisterObj;
	// public static String emailid;
	// public static Properties prop;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	String originalCarrierEmailAddress = "";
	String updatedCarrierEmailAddress = "";

	String originalCarrierPassword = "";
	String updatedCarrierPassword = "";

	String adminUN = "";
	String adminPW = "";

	public CarrierLoginPageResetPasswordTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		initialization();
		TestUtil.className = this.getClass().getName();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		adminEmailPage = new AdminEditEmailCarrier();
		carrierLoginObj = new CarrierLoginPage();
		carrierOutlookObj = new CarrierOutlook();
		resetPassword = new ResetPassword();
		outlookLoginObj = new outlooklogin();
		CarrierPasswordSetupResetPage = new CarrierPasswordSetupResetPage();
		currentTime = new Date();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-5024 ")
	public void openCarrierLoginPage() throws InterruptedException {
		carrierLoginObj.forgotPasswordButton();

	}

	@Test(dataProvider = "getCarrierForgotPasswordData", dependsOnMethods = { "openCarrierLoginPage" })
	public void proceedWithResetPassword(String UserName, String EmailAddress, String NewPassword,
			String ConfirmPassword) throws InterruptedException {

		resetPassword.enterUserName(UserName);
		resetPassword.clickResetPassword();
		Assert.assertEquals(resetPassword.verificationPage(), "Thank you. An email has been sent.");

		/////////////////////////////////////////////////////////////////
		TimeZone tz = Calendar.getInstance().getTimeZone();
		String currentTimeZone = tz.getDisplayName();
		log.info(currentTimeZone);

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
		log.info("\n\n\n===============================");
		//////////////////////////////////////////////////////////////////

	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = { "proceedWithResetPassword" })
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlookLoginObj.outlookLogin(un, pwd);
	}

	@Test(dataProvider = "getCarrierForgotPasswordData", dependsOnMethods = { "login" })
	public void outlookloginTest(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword)
			throws InterruptedException {
		carrierOutlookObj.clickPopUp();
		EmailAddress = EmailAddress.trim();
		carrierOutlookObj.clickOpenMailBox();
		carrierOutlookObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierOutlookObj.outlookSearchInbox(EmailAddress, currentHour, currentMinutes);
		carrierOutlookObj.handleResetPasswordEmailInbox(EmailAddress);
		CarrierPasswordSetupResetPage.enterNewPassword(NewPassword);
		CarrierPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
		CarrierPasswordSetupResetPage.clickSubmitButton();
		carrierLoginObj.carrierVerificationLogin(UserName, NewPassword);
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = { "outlookloginTest" })
	public void ResetPasswordEmail(String Username, String pass) throws InterruptedException, AWTException {
		// search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();

		adminUN = Username;
		adminPW = pass;

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getUserName()));
		adminLoginPage.adminUserPass(adminUN, adminPW);

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getLoginBtn()));
		adminLoginPage.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getCustomerTab()));
		adminLoginPage.ClickOnCustomersTab();

		Assert.assertTrue(adminLoginPage.CustomerTab.isDisplayed());
		log.info(carrierLoginObj.cemail);

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getSearch()));
		adminLoginPage.ClickOnSearchBox(carrierLoginObj.cemail);

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getClickonSearchButton()));
		adminLoginPage.ClickOnSearchButton();
		Assert.assertTrue(adminLoginPage.ClickonSearchButton.isDisplayed());

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getDoubleClickID()));
		adminLoginPage.DoubleClickID();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getEditloginuser()));
		adminLoginPage.clickEmailLoginUserTab();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getClickAdmin_ResetPassword()));
		adminLoginPage.click_AdminResetPassword();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.getClick_AdminResetpwdConfirm()));
		adminLoginPage.clickAdmin_ResetpwdConfirm();
	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "ResetPasswordEmail")
	public void outlogin(String un, String pwd) throws InterruptedException, AWTException {
		// outlookLogin.outlookLogin(un, pwd);
		driver.get(super.getProperties().getProperty("outlookURL"));
	}

	@Test(dataProvider = "getAdminforcepasswordData", dependsOnMethods = { "outlogin" })
	public void AdminforcepasswordData(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword)
			throws InterruptedException {
		carrierOutlookObj.clickPopUp();
		EmailAddress = EmailAddress.trim();
		carrierOutlookObj.clickOpenMailBox();
		carrierOutlookObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierOutlookObj.outlookSearchInboxforcareer(EmailAddress, currentHour, currentMinutes);
		carrierOutlookObj.handleResetPasswordEmailInbox(EmailAddress);

		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(4));
		Thread.sleep(2000);

		CarrierPasswordSetupResetPage.enterNewPassword(NewPassword);
		CarrierPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
		CarrierPasswordSetupResetPage.clickSubmitButton();
		carrierLoginObj.verificationCarrierLogout();
		carrierLoginObj.carrierVerificationLogin(UserName, NewPassword);
		carrierLoginObj.verificationCarrierLogout();

	}

}
