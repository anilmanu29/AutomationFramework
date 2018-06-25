package testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.outlooklogin;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.CarrierLoginPage;
import pages.CarrierOutlook;
import pages.CarrierParentChildRelationships;
import pages.CarrierlockedAccountResetPassword;

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
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyCarrierAccountLocked", dataProvider = "getCarrierlockedaccountAdminUnlockData", priority = 1)
	public void verifyCarrierAccountLocked(String user, String pass, String wrgpwd) throws InterruptedException {
		aemail = user;
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.resetpwdlink.isDisplayed(),
				"Forgot Password Link is NOT Available in Login Page");
		carrierlockaccounrsetpwdtobj.lockCarrierAccount(user, pass, wrgpwd);

	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyCarrierLockedAccountPasswordReset", priority = 2)
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
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", dataProvider = "getoutlookLoginData", priority = 3)
	public void verifyCarrierEmailInOutlookTest(String un, String pwd) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open();");
		Thread.sleep(1000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		try {
			outlookLoginObj.outlookLogin(un, pwd);
			carrierOutlookObj.clickPopUp();
			carrierOutlookObj.clickOpenMailBox();
			carrierOutlookObj.enterEmail(super.prop.getProperty("email"));
			Thread.sleep(4000);
			carrierlockaccounrsetpwdtobj.outlookSearchInbox(aemail);
			carrierlockaccounrsetpwdtobj.handleUpdatedEmailInbox(aemail);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", dataProvider = "getCarrierParentChildPasswordData", priority = 4)
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

	@Test(description = "LP-5415 Carriercanresetpasswordwhenaccountislocked_verifyPasswordResetEmail", priority = 5)
	public void verifyCarrierLoginWithNewPassword() throws InterruptedException {
		carrierloginobj.Carrierlogin(aemail, newpwd);
		Thread.sleep(2000);
		Assert.assertTrue(carrierlockaccounrsetpwdtobj.btn_logout.isDisplayed(),
				"Carrier should be Logged with NEW Password");
		carrierloginobj.CarrierLogout();

	}
}
