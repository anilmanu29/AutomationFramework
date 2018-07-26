package testcases.loadpay.broker;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerRegister;
import pages.loadpay.outlook.outlooklogin;

public class BrokerRegisterTest extends TestBase {
	BrokerRegister brokerRegistrationObj;
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	BrokerLoginPage loginPage;
	BrokerOutlook brokerOutlookObj;
	outlooklogin outlookLoginObj;

	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;

	public static String emailid;

	String brokerUsername;
	String brokerPassword;
	String outlookUsername;
	String outlookPassword;
	String EIN = "99-9999999";
	String depositAmount = "";
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	public BrokerRegisterTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerRegistrationObj = new BrokerRegister();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		loginPage = new BrokerLoginPage();
		outlookLoginObj = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		brokerUsername = Email;
		brokerPassword = Password;

		brokerRegistrationObj.signup();

		// clicking on carrier Register
		brokerRegistrationObj.shipperRegister();
		brokerRegistrationObj.companyname(CompanyName);
		brokerRegistrationObj.doingbussiness(DoingBussinessAS);
		brokerRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brokerRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		brokerRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		emailid = brokerRegistrationObj.BrokerEmail(Email);
		brokerRegistrationObj.confirmEmail(ConfirmEmail);
		brokerRegistrationObj.iCertifyClick();
		brokerRegistrationObj.paymentTerm();

		brokerRegistrationObj.clickNextBtnOnCompanyForm();

		brokerRegistrationObj.ZipCode(ZipCode1);
		brokerRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		brokerRegistrationObj.address(Address);
		brokerRegistrationObj.city(City);
		brokerRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		brokerRegistrationObj.clickNextBtnOnAddressForm();

		brokerRegistrationObj.ContactFirstName(FirstNames);
		brokerRegistrationObj.LastName(LastName);
		brokerRegistrationObj.Phone(PhoneNumber);
		brokerRegistrationObj.Password(Password);
		brokerRegistrationObj.ConfirmPassword(ConfirmPassword);

		brokerRegistrationObj.clickNextBtnOnContactForm();

		brokerRegistrationObj.AccountName(NameonAccount);
		brokerRegistrationObj.BankingRouting(RoutingNumber);
		brokerRegistrationObj.BankingAccount(BankAccountNumber);
		brokerRegistrationObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		brokerRegistrationObj.clickNextBtnOnBankingForm();

		brokerRegistrationObj.verifyRegistrationConfirmationMessage();

		log.info(" Broker Register Completed...");
	}

	@Test(description = "Capture Outlook Login creds", dataProvider = "getoutlookLoginData", dependsOnMethods = "BrokerRegister")
	public void getOutlookLoginCredentials(String user, String pass) throws InterruptedException, AWTException {
		outlookUsername = user;
		outlookPassword = pass;
	}

	@Test(description = "Complete Broker Registration after first login", dataProvider = "getAdminLoginData", dependsOnMethods = "getOutlookLoginCredentials")
	public void loginAndVerifyNewBrokerAccount(String adminUser, String adminPass)
			throws InterruptedException, AWTException {
		adminHomePage.AdminURL();
		adminLoginPage.adminUserPass(adminUser, adminPass);

		adminLoginPage.adminLogin();

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.ClickOnSearchBox(brokerUsername);

		adminLoginPage.ClickOnSearchButton();

		Thread.sleep(2000);

		WebElement TotalCountElement = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[3]/div[1]/h3/span")));

		Assert.assertTrue(TotalCountElement.getText().contains("1"), "Record count of 1 not found");

		adminLoginPage.DoubleClickID();

		adminLoginPage.StatusIDDropDown();

		adminLoginPage.UpdateButton();

		// add credit
		WebElement adminCustomerCreditTab = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Credit')]")));
		adminCustomerCreditTab.click();

		WebElement extendedCreditField = wait.until(ExpectedConditions.elementToBeClickable(By.id("ExtendedCredit")));
		extendedCreditField.clear();
		extendedCreditField.sendKeys("100000");

		WebElement updateCreditButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formCredit\"]/div[3]/input")));
		updateCreditButton.click();

		// go to banking tab and capture deposit amount
		WebElement adminCustomerBankingTab = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Banking')]")));
		adminCustomerBankingTab.click();

		WebElement adminCustomerDepositAmount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span")));
		depositAmount = adminCustomerDepositAmount.getText();
		depositAmount = depositAmount.substring(depositAmount.length() - 2, depositAmount.length());
		depositAmount = "0" + depositAmount;
		log.info("Captured deposit amount: " + depositAmount);

		// verify in outlook
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

		outlookLoginObj.outlookLogin(outlookUsername, outlookPassword);
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		brokerOutlookObj.outlookSearchInbox(brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox();

		//////////////////////////////////////////////////////////////////

		// log in as broker
		driver.get(super.getProperties().getProperty("url"));
		loginPage.Brokerlogin(brokerUsername, brokerPassword);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		// enter EIN
		WebElement einInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='EIN']")));
		einInputField.clear();
		einInputField.sendKeys(EIN);

		// enter deposit amount
		WebElement depositAmtField = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ControlAmount']")));
		depositAmtField.clear();
		depositAmtField.sendKeys(depositAmount);

		// click Next
		WebElement nextButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='formCompany']/input")));
		nextButton.click();

		// accept terms and conditions
		WebElement acceptTermsCheckBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='AcceptedTerms']")));
		acceptTermsCheckBox.click();

		WebElement finishButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='termsForm']/input")));
		finishButton.click();

		WebElement confirmationPopup = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[1]/div/p")));
		log.info("Confirmation message: " + confirmationPopup.getText());
		Assert.assertTrue(
				confirmationPopup.getText().contains("Your LoadPay™ registration has been completed successfully."),
				"Registration success message not found");

		// as broker update pay me now option
	}

}
