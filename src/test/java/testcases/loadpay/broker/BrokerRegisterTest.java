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
	BrokerRegister r;
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
		r = new BrokerRegister();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		loginPage = new BrokerLoginPage();
		outlookLoginObj = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		brokerUsername = Email;
		brokerPassword = Password;

		r.signup();

		// clicking on carrier Register
		r.shipperRegister();
		r.companyname(CompanyName);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		r.doingbussiness(DoingBussinessAS);
		r.selectType();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		r.countryofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		r.stateofincorporation();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		emailid = r.BrokerEmail(Email);

		r.confirmEmail(ConfirmEmail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.iCertifyClick();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.paymentTerm();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.ZipCode(ZipCode1);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.country();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		r.address(Address);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.city(City);

		r.State();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.submit();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.ContactFirstName(FirstNames);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.LastName(LastName);
		r.Phone(PhoneNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.Password(Password);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		r.ConfirmPassword(ConfirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.Next();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.AccountName(NameonAccount);
		r.BankingAccount(BankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		r.BankingRouting(RoutingNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		r.ConfirmBankingAccount(ConfirmbankAccountNumber);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		r.submit();
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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.adminLogin();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnCustomersTab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchBox(brokerUsername);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.ClickOnSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.DoubleClickID();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.StatusIDDropDown();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		adminLoginPage.UpdateButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// add credit
		WebElement adminCustomerCreditTab = driver.findElement(By.xpath("//a[contains(text(),'Credit')]"));
		adminCustomerCreditTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement extendedCreditField = driver.findElement(By.id("ExtendedCredit"));
		extendedCreditField.clear();
		extendedCreditField.sendKeys("100000");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement updateCreditButton = driver.findElement(By.xpath("//*[@id=\"formCredit\"]/div[3]/input"));
		updateCreditButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement availableCreditLabel = driver.findElement(By.xpath("//*[@id=\"formCredit\"]/div[2]/label"));
		Assert.assertTrue(availableCreditLabel.getText().contains("$100,000.00"), "Available credit amount not found!");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// go to banking tab and capture deposit amount
		WebElement adminCustomerBankingTab = driver.findElement(By.xpath("//a[contains(text(),'Banking')]"));
		adminCustomerBankingTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement adminCustomerDepositAmount = driver.findElement(By.xpath(
				"//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span"));
		depositAmount = adminCustomerDepositAmount.getText();
		depositAmount = depositAmount.substring(depositAmount.length() - 2, depositAmount.length());
		depositAmount = "0" + depositAmount;
		log.info("Captured deposit amount: " + depositAmount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

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
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		//////////////////////////////////////////////////////////////////

		// log in as broker
		driver.get(super.getProperties().getProperty("url"));
		loginPage.Brokerlogin(brokerUsername, brokerPassword);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// enter EIN
		WebElement einInputField = driver.findElement(By.xpath("//*[@id='EIN']"));
		einInputField.clear();
		einInputField.sendKeys(EIN);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// enter deposit amount
		WebElement depositAmtField = driver.findElement(By.xpath("//*[@id='ControlAmount']"));
		depositAmtField.clear();
		depositAmtField.sendKeys(depositAmount);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// click Next
		WebElement nextButton = driver.findElement(By.xpath("//*[@id='formCompany']/input"));
		nextButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		// accept terms and conditions
		WebElement acceptTermsCheckBox = driver.findElement(By.xpath("//*[@id='AcceptedTerms']"));
		acceptTermsCheckBox.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement finishButton = driver.findElement(By.xpath("//*[@id='termsForm']/input"));
		finishButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));

		WebElement confirmationPopup = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[1]/div/p"));
		log.info("Confirmation message: " + confirmationPopup.getText());
		Assert.assertTrue(
				confirmationPopup.getText().contains("Your LoadPay™ registration has been completed successfully."),
				"Registration success message not found");

		// as broker update pay me now option
	}

}
