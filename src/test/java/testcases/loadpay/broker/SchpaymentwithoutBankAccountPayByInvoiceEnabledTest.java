
package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerRegister;
import pages.loadpay.broker.SchpaymentwithoutBankAccountPayByInvoiceEnabled;
import util.TestUtil;

public class SchpaymentwithoutBankAccountPayByInvoiceEnabledTest extends TestBase {

	// page objects
	SchpaymentwithoutBankAccountPayByInvoiceEnabled schpaymentwithoutBankAccountPayByInvoiceenabled;
	AdminHomePage adminhomepage;
	AdminLogin adminlogin;
	BrokerRegister brokerregister;
	OutlookFunctions brokerOutlookObj;
	OutlookFunctions outlookLoginObj;

	public static String emailid;
	public static String EIN = "99-9999999";
	public static String brokerUsername;
	public static String brokerPassword;
	public static String brokerCompanyName;
	String pwd = "";

	// timestamp variables
	Date currentTime;
	String formattedDate = "";
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	Long longTime;
	DateFormat formatter;

	/*-------Initializing driver---------*/

	public SchpaymentwithoutBankAccountPayByInvoiceEnabledTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		adminhomepage = new AdminHomePage();
		adminlogin = new AdminLogin();
		brokerregister = new BrokerRegister();
		brokerOutlookObj = new OutlookFunctions();
		outlookLoginObj = new OutlookFunctions();
		currentTime = new Date();
		schpaymentwithoutBankAccountPayByInvoiceenabled = new SchpaymentwithoutBankAccountPayByInvoiceEnabled();
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

		pwd = Password;
		brokerregister.signup();
		brokerregister.shipperRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		brokerregister.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		brokerregister.setMotorCarrierField(randomNum);

		brokerregister.companyname(brokerCompanyName);
		brokerregister.doingbussiness(DoingBussinessAS);
		brokerregister.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brokerregister.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		brokerregister.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		emailid = brokerregister.BrokerEmail(brokerUsername);

		brokerregister.confirmEmail(brokerUsername);
		brokerregister.iCertifyClick();
		brokerregister.paymentTerm();
		brokerregister.clickNextBtnOnCompanyForm();
		brokerregister.ZipCode(ZipCode1);
		brokerregister.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		brokerregister.address(Address);
		brokerregister.city(City);
		brokerregister.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		brokerregister.clickNextBtnOnAddressForm();

		brokerregister.ContactFirstName(FirstNames);
		brokerregister.LastName(LastName);
		brokerregister.Phone(PhoneNumber);
		brokerregister.Password(brokerPassword);
		brokerregister.ConfirmPassword(brokerPassword);

		brokerregister.clickNextBtnOnContactForm();

		schpaymentwithoutBankAccountPayByInvoiceenabled.clickaddlater();
		System.out.println("Broker Register Completed...");
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlookLoginObj.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {

		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("loadpaytestEmail"));

		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokerOutlookObj.outlookSearchInbox(emailid, currentHour, currentMinutes);
		schpaymentwithoutBankAccountPayByInvoiceenabled.handleNewInbox(brokerUsername);
		schpaymentwithoutBankAccountPayByInvoiceenabled.verifyConfirmationMessage();

	}

	@Test(description = "Switch to admin URL", dependsOnMethods = "outlookloginTest")
	public void Home() throws IOException, AWTException, InterruptedException {
		adminhomepage.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "outlookloginTest")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {

		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		adminlogin.ClickOnCustomersTab();
		adminlogin.ClickOnSearchBox(brokerUsername);
		adminlogin.ClickOnSearchButton();
		adminlogin.DoubleClickID();
		adminlogin.StatusIDDropDown();
		adminlogin.UpdateButton();
		adminlogin.click_AdminBanking();
		adminlogin.Banking_editbtnPayByInvoice();
		adminlogin.selectPayByInvoieStatus();
		adminlogin.clickupdatebtnPayByInvoice();
	}

	@Test(dependsOnMethods = "adminLogin")
	public void BrokerFirstloginTest() throws InterruptedException {
		driver.get(super.prop.getProperty("loadPayURL"));
		schpaymentwithoutBankAccountPayByInvoiceenabled.brokerfirstLogin();
		schpaymentwithoutBankAccountPayByInvoiceenabled.clickAcceptedTerms();
		schpaymentwithoutBankAccountPayByInvoiceenabled.clicksubmit();
		WebElement confirmationPopup = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[2]/button"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmationPopup));
		confirmationPopup.click();
		schpaymentwithoutBankAccountPayByInvoiceenabled.newPayment();
		schpaymentwithoutBankAccountPayByInvoiceenabled.BrokerLogout();

	}

	@Test(dependsOnMethods = "BrokerFirstloginTest")
	public void adminDisablePayByInvoice() throws IOException, InterruptedException, AWTException {

		adminhomepage.AdminURL();
		adminlogin.ClickOnCustomersTab();
		adminlogin.ClickOnSearchBox(brokerUsername);
		adminlogin.ClickOnSearchButton();
		adminlogin.DoubleClickID();
		adminlogin.click_AdminBanking();
		adminlogin.Banking_editbtnPayByInvoice();
		adminlogin.DisablePayByInvoieStatus();
		adminlogin.clickupdatebtnPayByInvoice();
		/* adminlogin.AdminLogOut(); */
	}

	@Test(dependsOnMethods = "adminDisablePayByInvoice")
	public void BrokerSecondloginTest() throws InterruptedException {
		driver.get(super.prop.getProperty("loadPayURL"));
		schpaymentwithoutBankAccountPayByInvoiceenabled.brokerSecondLogin();
	}

}
