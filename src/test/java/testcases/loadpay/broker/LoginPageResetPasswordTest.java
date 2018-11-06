package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerPasswordSetupResetPage;
import pages.loadpay.carrier.ResetPassword;
import pages.loadpay.outlook.outlooklogin;
import util.TestUtil;

public class LoginPageResetPasswordTest extends TestBase {
	BrokerPasswordSetupResetPage brokerPasswordSetupResetPage;
	BrokerLoginPage brokerLoginPage;
	ResetPassword resetPassword;
	BrokerOutlook brokerOutlook;
	outlooklogin outlookLogin;
	public static String emailid;
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public LoginPageResetPasswordTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokerLoginPage = new BrokerLoginPage();
		resetPassword = new ResetPassword();
		outlookLogin = new outlooklogin();
		brokerOutlook = new BrokerOutlook();
		brokerPasswordSetupResetPage = new BrokerPasswordSetupResetPage();
		currentTime = new Date();
		wait = new WebDriverWait(driver, 30);

	}

	@Test(description = "LP-5025 ")
	public void openBrokerLoginPage() throws InterruptedException {
		brokerLoginPage.forgotPasswordButton();

	}

	@Test(dataProvider = "getBrokerForgotPassword", dependsOnMethods = "openBrokerLoginPage")
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

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "proceedWithResetPassword")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlookLogin.outlookLogin(un, pwd);
	}

	@Test(dataProvider = "getBrokerForgotPassword", dependsOnMethods = "login")
	public void outlookloginTest(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword)
			throws InterruptedException {
		brokerOutlook.clickPopUp();
		EmailAddress = EmailAddress.trim();
		brokerOutlook.clickOpenMailBox();
		brokerOutlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		brokerOutlook.outlookSearchInbox(EmailAddress, currentHour, currentMinutes);
		brokerOutlook.handleResetPasswordEmailInbox(EmailAddress);
		brokerPasswordSetupResetPage.enterNewPassword(NewPassword);
		brokerPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
		brokerPasswordSetupResetPage.clickSubmitButton();
		brokerLoginPage.brokerVerificationLogin(UserName, NewPassword);
		brokerLoginPage.BrokerLogout();
	}
}
