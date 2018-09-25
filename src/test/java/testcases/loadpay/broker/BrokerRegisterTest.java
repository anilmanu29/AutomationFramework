package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerRegister;
import util.TestUtil;

public class BrokerRegisterTest extends TestBase {
	BrokerRegister brokerRegistrationObj;
	AdminLogin adminLoginPage;
	AdminHomePage adminHomePage;

	Select type;
	Select stateof;
	Select payment;
	Select state;
	Select country;
	Select Payments;
	WebElement PaymentTerms;

	public static String brokerUsername;
	public static String brokerPassword;
	public static String brokerCompanyName;
	String outlookUsername;
	String outlookPassword;
	public static String depositAmount = "";

	public BrokerRegisterTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		brokerRegistrationObj = new BrokerRegister();
		adminLoginPage = new AdminLogin();
		adminHomePage = new AdminHomePage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		if (Email.contains("[uniqueID]")) {
			String uniqueEmail = Email.replace("[uniqueID]", TestUtil.getCurrentDateTime());
			brokerUsername = uniqueEmail;
			brokerPassword = Password;
			brokerCompanyName = CompanyName;
		} else {
			brokerUsername = Email;
			brokerPassword = Password;
		}

		brokerRegistrationObj.signup();

		// clicking on carrier Register
		brokerRegistrationObj.shipperRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		brokerRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		brokerRegistrationObj.setMotorCarrierField(randomNum);

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

		brokerRegistrationObj.BrokerEmail(brokerUsername);
		brokerRegistrationObj.confirmEmail(brokerUsername);
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

		log.info("Broker Register Completed...");
	}

	@Test(description = "Complete Broker Registration after first login", dataProvider = "getAdminLoginData", dependsOnMethods = "BrokerRegister")
	public void loginAndVerifyNewBrokerAccount(String adminUser, String adminPass)
			throws InterruptedException, AWTException {
		adminHomePage.AdminURL();
		adminLoginPage.adminUserPass(adminUser, adminPass);
		adminLoginPage.adminLogin();
		adminLoginPage.ClickOnCustomersTab();
		adminLoginPage.Uncheck_Factor();
		adminLoginPage.ClickOnSearchBox(brokerUsername);
		adminLoginPage.ClickOnSearchButton();
		adminLoginPage.DoubleClickID();
		adminLoginPage.StatusIDDropDown();
		adminLoginPage.UpdateButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminLoginPage.updateButton));
		Thread.sleep(2000);

		// go to banking tab and capture deposit amount
		WebElement adminCustomerBankingTab = driver.findElement(By.xpath("//a[contains(text(),'Banking')]"));
		adminCustomerBankingTab.click();

		wait.until(ExpectedConditions.elementToBeClickable(adminCustomerBankingTab));
		Thread.sleep(2000);

		WebElement adminCustomerDepositAmount = driver.findElement(By.xpath(
				"//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[3]/div/div/div[1]/div/div/div/p[9]/span"));

		// *[@id="angularScope"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[3]/div/div/div[1]/div/div/div/p[9]/span
		depositAmount = adminCustomerDepositAmount.getText();
		depositAmount = depositAmount.substring(depositAmount.length() - 2, depositAmount.length());
		depositAmount = "0" + depositAmount;
		log.info("Captured deposit amount: " + depositAmount);
	}
}
