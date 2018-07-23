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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.model.Log;

import base.TestBase;
import pages.loadpay.admin.AdminEditEmailCarrier;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierRegisterPage;
import pages.loadpay.outlook.outlooklogin;

public class AdminEditEmailCarrierTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminEditEmailCarrier adminEmailPage;
	outlooklogin outlookLoginObj;
	CarrierLoginPage carrierLoginObj;
	CarrierOutlook carrierOutlookObj;
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
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		adminEmailPage = new AdminEditEmailCarrier();
		outlookLoginObj = new outlooklogin();
		carrierLoginObj = new CarrierLoginPage();
		carrierOutlookObj = new CarrierOutlook();
		carrierRegisterObj = new CarrierRegisterPage();
		currentTime = new Date();
		log = Logger.getLogger(Log.class.getName());
		log.info("Test Set Up");
	}

	@AfterClass
	public void revertToOriginalEmail() throws InterruptedException, AWTException {
		// search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchBox(updatedCarrierEmailAddress);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.openEmailLoginUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickEditEmailButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.setNewEmailAddress(originalCarrierEmailAddress);
		adminEmailPage.confirmNewEmailAddress(originalCarrierEmailAddress);
		adminEmailPage.clickUpdateEmailEditButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(originalCarrierEmailAddress),
				"Original" + originalCarrierEmailAddress + "] not found in confirmation!");
		adminEmailPage.clickCloseEmailConfirmationButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickRefreshButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(originalCarrierEmailAddress),
				"Original Email Address Not Found!");
	}

	@Test(description = "LP-5432 Admin_EditEmail_setAdminURL", dataProvider = "getCarrierRegisterData")
	public void registerNewCarrier(String Dotnumber, String CompanyName, String DoingBusinessAs, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameOnAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		// store these into global variables for reuse
		originalCarrierEmailAddress = Email;
		originalCarrierPassword = Password;

		// sign up and register new carrier
		carrierRegisterObj.signup();
		carrierRegisterObj.CarrierRegister();

		carrierRegisterObj.companyname(CompanyName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		carrierRegisterObj.doingbussiness(DoingBusinessAs);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		carrierRegisterObj.selectType();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.countryofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.stateofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.CarrierEmail(Email);
		carrierRegisterObj.confirmEmail(ConfirmEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.ZipCode(ZipCode1);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.country();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.address(Address);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.city(City);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.State();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.ContactFirstName(FirstNames);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.LastName(LastName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.Password(Password);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.ConfirmPassword(ConfirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.Next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.AccountName(NameOnAccount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.BankingAccount(BankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.BankingRouting(RoutingNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		carrierRegisterObj.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		log.info(" Carrier Register Completed...");

	}

	@Test(description = "LP-5432 Admin_EditEmail_adminLogin", dependsOnMethods = {
			"registerNewCarrier" }, dataProvider = "getAdminLoginData")
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

	@Test(description = "LP-5432 Admin_EditEmail_Carrier", dependsOnMethods = { "adminLogin" })
	public void carrierEditEmailTest() throws InterruptedException {
		int randomNumber = adminEmailPage.getRandomNumber(1, 999999);
		updatedCarrierEmailAddress = originalCarrierEmailAddress.replaceFirst("@", randomNumber + "@");

		log.info(originalCarrierEmailAddress);
		log.info(updatedCarrierEmailAddress);

		adminLoginPage.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchBox(originalCarrierEmailAddress);
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		// verify close-confirmation button is displayed
		Assert.assertTrue(adminEmailPage.getCloseEmailConfirmationButton().isDisplayed(),
				"Close-confirmation button not found!");

		// verify updated email address is displayed in confirmation popup
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(updatedCarrierEmailAddress),
				"New email address [" + updatedCarrierEmailAddress + "] not found in confirmation!");

		adminEmailPage.clickCloseEmailConfirmationButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminEmailPage.clickRefreshButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(updatedCarrierEmailAddress),
				"Updated Email Address Not Found!");

		// Activate new carrier in admin panel
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
			"carrierEditEmailTest" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierEmailInOutlookTest(String un, String pwd) throws InterruptedException {

		try {
			outlookLoginObj.outlookLogin(un, pwd);
			carrierOutlookObj.clickPopUp();
			carrierOutlookObj.clickOpenMailBox();
			carrierOutlookObj.enterEmail(super.getProperties().getProperty("email"));
			carrierOutlookObj.outlookSearchInbox(updatedCarrierEmailAddress, currentHour, currentMinutes);
			carrierOutlookObj.handleUpdatedEmailInbox(updatedCarrierEmailAddress);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "LP-5432 Admin_EditEmail_UpdatedCarrierLogin", dependsOnMethods = {
			"verifyCarrierEmailInOutlookTest" })
	public void verifyUpdatedCarrierLogin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

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
}
