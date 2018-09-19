package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminEditEmailBroker;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerRegister;
import pages.loadpay.outlook.outlooklogin;
import testcases.loadpay.broker.BrokerRegisterTest;

public class AdminEditEmailBrokerTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminEditEmailBroker adminEmailPage;
	outlooklogin outlookLoginObj;
	BrokerLoginPage brokerLoginObj;
	BrokerRegister brokerRegisterObj;
	BrokerOutlook brokerOutlookObj;

	String originalBrokerEmailAddress = "";
	String originalBrokerPassword = "";
	String updatedBrokerEmailAddress = "";
	String updatedBrokerPassword = "";
	String adminUN = "";
	String adminPW = "";

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public AdminEditEmailBrokerTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		initialization();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		adminEmailPage = new AdminEditEmailBroker();
		outlookLoginObj = new outlooklogin();
		brokerLoginObj = new BrokerLoginPage();
		brokerRegisterObj = new BrokerRegister();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
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

	@Test(description = "LP-5432 Admin_EditEmail_Broker", dependsOnMethods = { "adminLogin" })
	public void brokerEditEmailTest() throws InterruptedException {
		int randomNumber = adminEmailPage.getRandomNumber(1, 999999);
		originalBrokerEmailAddress = BrokerRegisterTest.brokerUsername;
		originalBrokerPassword = BrokerRegisterTest.brokerPassword;
		updatedBrokerEmailAddress = originalBrokerEmailAddress.replaceFirst("@", randomNumber + "@");

		log.info(originalBrokerEmailAddress);
		log.info(updatedBrokerEmailAddress);

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.ClickOnSearchBox(originalBrokerEmailAddress);

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
		adminEmailPage.setNewEmailAddress(updatedBrokerEmailAddress);
		// verify update button is not yet enabled since the confirm field has not been
		// set
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");
		// set confirmation email address
		adminEmailPage.confirmNewEmailAddress(updatedBrokerEmailAddress);
		// verify update button is now enabled
		Assert.assertTrue(adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");
		// click update button
		adminEmailPage.clickUpdateEmailEditButton();

		// verify close-confirmation button is displayed
		Assert.assertTrue(adminEmailPage.getCloseEmailConfirmationButton().isDisplayed(),
				"Close-confirmation button not found!");

		// verify updated email address is displayed in confirmation popup
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(updatedBrokerEmailAddress),
				"New email address [" + updatedBrokerEmailAddress + "] not found in confirmation!");

		adminEmailPage.clickCloseEmailConfirmationButton();

		adminEmailPage.clickRefreshButton();

		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(updatedBrokerEmailAddress),
				"Updated Email Address Not Found!");

		// Activate new broker in admin panel
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
			"brokerEditEmailTest" }, dataProvider = "getoutlookLoginData")
	public void verifyBrokerEmailInOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlookLoginObj.outlookLogin(un, pwd);
			brokerOutlookObj.clickPopUp();
			brokerOutlookObj.clickOpenMailBox();
			brokerOutlookObj.enterEmail(super.getProperties().getProperty("email"));
			brokerOutlookObj.outlookSearchInbox(updatedBrokerEmailAddress, currentHour, currentMinutes);
			brokerOutlookObj.handleUpdatedEmailInbox(updatedBrokerEmailAddress);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "LP-5432 Admin_EditEmail_UpdatedBrokerLogin", dependsOnMethods = {
			"verifyBrokerEmailInOutlookTest" })
	public void verifyUpdatedBrokerLogin() throws InterruptedException {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));

		// set new password and confirm pw
		WebElement newPassword = driver.findElement(By.xpath("//*[@id='User_Password']"));
		WebElement confirmPassword = driver.findElement(By.xpath("//*[@id='User_ConfirmPassword']"));
		WebElement submitButton = driver
				.findElement(By.xpath("//*[@id=\"formLogIn\"]/div/div[2]/div/div/div[1]/div[3]/input"));

		updatedBrokerPassword = originalBrokerPassword + adminEmailPage.getRandomNumber(1, 999);
		log.info("\n\n\n==================================================");
		log.info("Updated Username: " + updatedBrokerEmailAddress);
		log.info("Updated Password: " + updatedBrokerPassword);
		log.info("==================================================\n\n\n");

		// reset and confirm password
		newPassword.sendKeys(updatedBrokerPassword);
		confirmPassword.sendKeys(updatedBrokerPassword);
		submitButton.click();

		// log in as broker
		brokerLoginObj.Brokerlogin(updatedBrokerEmailAddress, updatedBrokerPassword);

		WebElement loginLabel = driver.findElement(By.xpath("//span[@title='" + updatedBrokerEmailAddress + "']"));
		Assert.assertTrue(loginLabel.getText().equals(updatedBrokerEmailAddress),
				"Updated email address not seen after login!");
	}

	@Test(description = "LP-5432 Admin_EditEmail_RevertBrokerEmail", dependsOnMethods = { "verifyUpdatedBrokerLogin" })
	public void revertToOriginalEmail() throws InterruptedException, AWTException {
		// search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.ClickOnSearchBox(updatedBrokerEmailAddress);

		adminLoginPage.ClickOnSearchButton();

		adminLoginPage.DoubleClickID();

		adminEmailPage.openEmailLoginUsersPage();

		adminEmailPage.clickEditEmailButton();

		adminEmailPage.setNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.confirmNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.clickUpdateEmailEditButton();

		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(originalBrokerEmailAddress),
				"Original" + originalBrokerEmailAddress + "] not found in confirmation!");
		adminEmailPage.clickCloseEmailConfirmationButton();

		adminEmailPage.clickRefreshButton();

		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(originalBrokerEmailAddress),
				"Original Email Address Not Found!");
	}
}
