package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierParentChildRelationships;
import pages.loadpay.carrier.CarrierlockedAccountResetPassword;
import pages.loadpay.outlook.outlooklogin;

public class CarrierlockedAccountResetPasswordTest extends TestBase {
	CarrierlockedAccountResetPassword carrierlockaccounrsetpwdtobj;
	AdminHomePage h;
	AdminLogin a;
	Select s;
	public String aemail = "";
	String newpwd = "";
	CarrierLoginPage loginPage;
	outlooklogin outlookLoginObj;
	CarrierOutlook carrierOutlookObj;
	CarrierParentChildRelationships carrierparentchildobject;
	CarrierLoginPage carrierloginobj;
	ArrayList<String> tabs;
	
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public CarrierlockedAccountResetPasswordTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		h = new AdminHomePage();
		a = new AdminLogin();
		carrierlockaccounrsetpwdtobj = new CarrierlockedAccountResetPassword();
		loginPage = new CarrierLoginPage();
		outlookLoginObj = new outlooklogin();
		carrierOutlookObj = new CarrierOutlook();
		carrierparentchildobject = new CarrierParentChildRelationships();
		carrierloginobj = new CarrierLoginPage();
		currentTime = new Date();
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyCarrierAccountLocked", dataProvider = "getCarrierlockedaccountAdminUnlockData")
	public void verifyCarrierAccountLocked(String user, String pass, String wrgpwd) throws InterruptedException {
		aemail = user;
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.resetpwdlink.isDisplayed(),
				"Forgot Password Link is NOT Available in Login Page");
		carrierlockaccounrsetpwdtobj.lockCarrierAccount(user, pass, wrgpwd);

	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyCarrierLockedAccountPasswordReset", dependsOnMethods = "verifyCarrierAccountLocked")
	public void verifyLockedAccountResetPassword() throws InterruptedException {
		carrierlockaccounrsetpwdtobj.clickResetPasswordlink();
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.usernamefield.isDisplayed(),
				"UserName field for ResetPassword is NOT Displayed");
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.resetpasswordbutton.isDisplayed(),
				"Reset Password button is NOT Displayed");
		carrierlockaccounrsetpwdtobj.enterUserName(aemail);
		carrierlockaccounrsetpwdtobj.clickResetPasswordButton();
		Assert.assertTrue(
				carrierlockaccounrsetpwdtobj.succesfulmessage.getText().contains("Thank you. An email has been sent."),
				"Email sent for Password Rest message is NOT Displayed");

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

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyLockedAccountResetPassword")
	public void verifyCarrierEmailInOutlookTest(String un, String pwd) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		try {
			outlookLoginObj.outlookLogin(un, pwd);
			carrierOutlookObj.clickPopUp();
			carrierOutlookObj.clickOpenMailBox();
			carrierOutlookObj.enterEmail(super.getProperties().getProperty("email"));
			Thread.sleep(4000);
			carrierlockaccounrsetpwdtobj.outlookSearchInbox(aemail, currentHour, currentMinutes);
			carrierlockaccounrsetpwdtobj.handleUpdatedEmailInbox(aemail);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", dataProvider = "getCarrierParentChildPasswordData", dependsOnMethods = "verifyCarrierEmailInOutlookTest")
	public void verifyResetPassword(String nwpwd, String confmpwd, String forcepwd, String confirmforcepwd)
			throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(carrierparentchildobject.newpasswordfield.isDisplayed(),
				"New Password field is NOT Displayed");
		Assert.assertTrue(carrierparentchildobject.confirmpassword.isDisplayed(),
				"Confirm Password field is NOT Displayed");
		Thread.sleep(1000);
		newpwd = nwpwd;
		carrierparentchildobject.newpasswordfield.sendKeys(nwpwd);
		carrierparentchildobject.confirmpassword.sendKeys(confmpwd);
		carrierparentchildobject.submitbutton.click();
		Thread.sleep(2000);
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", dependsOnMethods = "verifyResetPassword")
	public void verifyCarrierLoginWithNewPassword() throws InterruptedException {
		carrierloginobj.Carrierlogin(aemail, newpwd);
		Thread.sleep(2000);
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.btn_logout.isDisplayed(),
				"Carrier should be Logged with NEW Password");
		carrierloginobj.CarrierLogout();

	}
}
