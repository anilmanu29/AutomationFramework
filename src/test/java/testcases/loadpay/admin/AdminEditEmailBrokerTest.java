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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	}

	@AfterClass
	public void revertToOriginalEmail() throws InterruptedException, AWTException {
		// search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchBox(updatedBrokerEmailAddress);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.openEmailLoginUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickEditEmailButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.setNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.confirmNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.clickUpdateEmailEditButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(originalBrokerEmailAddress),
				"Original" + originalBrokerEmailAddress + "] not found in confirmation!");
		adminEmailPage.clickCloseEmailConfirmationButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickRefreshButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(originalBrokerEmailAddress),
				"Original Email Address Not Found!");
	}

	@Test(description = "LP-5432 Admin_EditEmail_registerNewBroker", dataProvider = "getBrokerRegisterData")
	public void registerNewBroker(String DotNumber, String CompanyName, String DoingBusinessAs, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstName, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameOnAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws AWTException, InterruptedException {
		Select type;
		Select state;
		Select country;

		// store these into global variables for reuse
		originalBrokerEmailAddress = Email;
		originalBrokerPassword = Password;

		// sign up and register new broker
		brokerRegisterObj.signup();
		brokerRegisterObj.shipperRegister();

		brokerRegisterObj.companyname(CompanyName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.doingbussiness(DoingBusinessAs);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.selectType();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.countryofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		country = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		country.selectByIndex(0);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.stateofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		state = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		state.selectByVisibleText("California");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.BrokerEmail(Email);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.confirmEmail(ConfirmEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.paymentTerm();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.ZipCode(ZipCode1);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.country();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.address(Address);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.city(City);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.State();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.ContactFirstName(FirstName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.LastName(LastName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.Password(Password);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.ConfirmPassword(ConfirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.Next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.AccountName(NameOnAccount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.BankingAccount(BankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.BankingRouting(RoutingNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		brokerRegisterObj.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		log.debug("Broker Registration Completed...");
	}

	@Test(description = "LP-5432 Admin_EditEmail_adminLogin", dependsOnMethods = {
			"registerNewBroker" }, dataProvider = "getAdminLoginData")
	public void adminLogin(String Username, String pass) throws AWTException, InterruptedException {
		adminHomePage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
	}

	@Test(description = "LP-5432 Admin_EditEmail_Broker", dependsOnMethods = { "adminLogin" })
	public void brokerEditEmailTest() throws InterruptedException {
		int randomNumber = adminEmailPage.getRandomNumber(1, 999999);
		updatedBrokerEmailAddress = originalBrokerEmailAddress.replaceFirst("@", randomNumber + "@");

		log.info(originalBrokerEmailAddress);
		log.info(updatedBrokerEmailAddress);

		adminLoginPage.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchBox(originalBrokerEmailAddress);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.openEmailLoginUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickEditEmailButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// verify fields and buttons
		Assert.assertTrue(adminEmailPage.getNewEmailField().isEnabled(), "New Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getNewEmailConfirmField().isEnabled(), "Confirm Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getCancelEmailEditButton().isEnabled(), "Cancel Button Disabled!");
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(),
				"Update Button is enabled - should be disabled unless email addresses entered!");

		// verify users can cancel and return to the Email/Login/Users page
		adminEmailPage.clickCancelEmailEditButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getEditEmailButton().isDisplayed(), "Edit Email Button not found!");

		// Click Edit email again
		adminEmailPage.clickEditEmailButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// verify close-confirmation button is displayed
		Assert.assertTrue(adminEmailPage.getCloseEmailConfirmationButton().isDisplayed(),
				"Close-confirmation button not found!");

		// verify updated email address is displayed in confirmation popup
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(updatedBrokerEmailAddress),
				"New email address [" + updatedBrokerEmailAddress + "] not found in confirmation!");

		adminEmailPage.clickCloseEmailConfirmationButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickRefreshButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(updatedBrokerEmailAddress),
				"Updated Email Address Not Found!");

		// Activate new broker in admin panel
		adminEmailPage.openCompanyPage();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Select customerStatus = new Select(driver.findElement(By.id("CustomerStatusId")));
		customerStatus.selectByVisibleText("Active");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickUpdateCompanyButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

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
}
