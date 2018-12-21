package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.model.Log;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.admin.AdminEditEmailCarrier;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierRegisterPage;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminEditEmailCarrierTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminEditEmailCarrier adminEmailPage;
	OutlookFunctions outlookLoginObj;
	CarrierLoginPage carrierLoginObj;
	OutlookFunctions carrierOutlookObj;
	CarrierRegisterPage carrierRegisterObj;

	String originalCarrierEmailAddress = "";
	String updatedCarrierEmailAddress = "";
	String originalCarrierPassword = "";
	String updatedCarrierPassword = "";
	String adminUN = "";
	String adminPW = "";

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	Logger log;

	public AdminEditEmailCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		TestUtil.className = this.getClass().getName();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		adminEmailPage = new AdminEditEmailCarrier();
		outlookLoginObj = new OutlookFunctions();
		carrierLoginObj = new CarrierLoginPage();
		carrierOutlookObj = new OutlookFunctions();
		carrierRegisterObj = new CarrierRegisterPage();
		currentTime = new Date();
		log = Logger.getLogger(Log.class.getName());
		log.info("Test Set Up");
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-5432 Admin_EditEmail_adminLogin", dataProvider = "getAdminLoginData")
	public void adminLogin(String Username, String pass) throws AWTException, InterruptedException {
		adminHomePage.AdminURL();

		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);

		adminLoginPage.adminLogin();

	}

	@Test(description = "LP-5432 Admin_EditEmail_Carrier", dependsOnMethods = { "adminLogin" })
	public void carrierEditEmailTest() throws InterruptedException {
		int randomNumber = adminEmailPage.getRandomNumber(1, 999);
		originalCarrierEmailAddress = CarrierRegisterTest.carrierUsername;
		originalCarrierPassword = CarrierRegisterTest.carrierPassword;
		updatedCarrierEmailAddress = originalCarrierEmailAddress.replaceFirst("@", randomNumber + "@");

		log.info(originalCarrierEmailAddress);
		log.info(updatedCarrierEmailAddress);

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.ClickOnSearchBox(originalCarrierEmailAddress);

		adminLoginPage.ClickOnSearchButton();

		adminLoginPage.DoubleClickID();

		adminEmailPage.openEmailLoginUsersPage();

		adminEmailPage.clickEditEmailButton();

		// verify fields and buttons
		Assert.assertTrue(adminEmailPage.getNewEmailField().isEnabled(), "New Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getNewEmailConfirmField().isEnabled(), "Confirm Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getCancelEmailEditButton().isEnabled(), "Cancel Button Disabled!");
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");

		// verify users can cancel and return to the Email/Login/Users page
		adminEmailPage.clickCancelEmailEditButton();

		Assert.assertTrue(adminEmailPage.getEditEmailButton().isDisplayed(), "Edit Email Button not found!");

		// Click Edit email again
		adminEmailPage.clickEditEmailButton();

		// set new email address
		adminEmailPage.setNewEmailAddress(updatedCarrierEmailAddress);
		// verify update button is not yet enabled since the confirm field has not been
		// set
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");
		// set confirmation email address
		adminEmailPage.confirmNewEmailAddress(updatedCarrierEmailAddress);
		// verify update button is now enabled
		Assert.assertTrue(adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");
		// click update button
		adminEmailPage.clickUpdateEmailEditButton();

		// verify close-confirmation button is displayed
		Assert.assertTrue(adminEmailPage.getCloseEmailConfirmationButton().isDisplayed(),
				"Close-confirmation button not found!");

		// verify updated email address is displayed in confirmation popup
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(updatedCarrierEmailAddress),
				"New email address [" + updatedCarrierEmailAddress + "] not found in confirmation!");

		adminEmailPage.clickCloseEmailConfirmationButton();

		adminEmailPage.clickRefreshButton();

		log.info(adminEmailPage.getEmailPagePrimaryAddress().getText());
		log.info(updatedCarrierEmailAddress);

		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(updatedCarrierEmailAddress),
				"Updated Email Address Not Found!");

		// Activate new carrier in admin panel
		adminEmailPage.openCompanyPage();

		Select customerStatus = new Select(driver.findElement(By.id("CustomerStatusId")));
		customerStatus.selectByVisibleText("Active");

		adminEmailPage.clickUpdateCompanyButton();

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

	@Test(description = "LP-5432 Admin_EditEmail_Outlook", dependsOnMethods = {
			"carrierEditEmailTest" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierEmailInOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlookLoginObj.outlookLogin(un, pwd);
			carrierOutlookObj.clickPopUp();
			carrierOutlookObj.clickOpenMailBox();
			carrierOutlookObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
			carrierOutlookObj.outlookSearchInbox(updatedCarrierEmailAddress, currentHour, currentMinutes);
			carrierOutlookObj.handleUpdatedEmailInbox(updatedCarrierEmailAddress);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "LP-5432 Admin_EditEmail_UpdatedCarrierLogin", dependsOnMethods = {
			"verifyCarrierEmailInOutlookTest" })
	public void verifyUpdatedCarrierLogin() throws InterruptedException {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));

		// set new password and confirm pw
		WebElement newPassword = driver.findElement(By.xpath("//*[@id='User_Password']"));
		WebElement confirmPassword = driver.findElement(By.xpath("//*[@id='User_ConfirmPassword']"));
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"formLogIn\"]/div/div[2]/div/div/div[1]/div[3]/input"));

		updatedCarrierPassword = originalCarrierPassword + adminEmailPage.getRandomNumber(1, 999999);
		log.info("\n\n\n==================================================");
		log.info("Updated Username: " + updatedCarrierEmailAddress);
		log.info("Updated Password: " + updatedCarrierPassword);
		log.info("==================================================\n\n\n");

		// reset and confirm password
		newPassword.sendKeys(updatedCarrierPassword);
		confirmPassword.sendKeys(updatedCarrierPassword);
		submitButton.click();

		// log in as carrier
		carrierLoginObj.Carrierlogin(updatedCarrierEmailAddress, updatedCarrierPassword);

		log.info("//span[@title='" + updatedCarrierEmailAddress + "]'");
		WebElement loginLabel = driver.findElement(By.xpath("//span[@title='" + updatedCarrierEmailAddress + "']"));

		Assert.assertTrue(loginLabel.getText().equals(updatedCarrierEmailAddress),
				"Updated email address not seen after login!");
	}

	@Test(description = "LP-5432 Admin_EditEmail_RevertCarrierEmail", dependsOnMethods = {
			"verifyUpdatedCarrierLogin" })
	public void revertToOriginalEmail() throws InterruptedException, AWTException {
		// search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.ClickOnSearchBox(updatedCarrierEmailAddress);

		adminLoginPage.ClickOnSearchButton();

		adminLoginPage.DoubleClickID();

		adminEmailPage.openEmailLoginUsersPage();

		adminEmailPage.clickEditEmailButton();

		adminEmailPage.setNewEmailAddress(originalCarrierEmailAddress);
		adminEmailPage.confirmNewEmailAddress(originalCarrierEmailAddress);
		adminEmailPage.clickUpdateEmailEditButton();

		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(originalCarrierEmailAddress),
				"Original" + originalCarrierEmailAddress + "] not found in confirmation!");
		adminEmailPage.clickCloseEmailConfirmationButton();

		adminEmailPage.clickRefreshButton();

		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(originalCarrierEmailAddress),
				"Original Email Address Not Found!");
	}
}
