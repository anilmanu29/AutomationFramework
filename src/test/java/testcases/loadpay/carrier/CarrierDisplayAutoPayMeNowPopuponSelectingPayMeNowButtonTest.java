package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierRegisterPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import pages.loadpay.outlook.outlooklogin;
import util.TestUtil;

public class CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButtonTest extends TestBase {

	CarrierRegisterPage carrierRegistrationObj;
	CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton carrierdisplayautopaymenowpopupobj;
	CarrierLoginPage carrierloginobj;
	CarrierSameDAYACH carriersamedayachobj;
	CarrierWireTransfer carrierwiretransferobj;
	CarrierNextDAYACH carriernextdayachobj;
	CarrierOutlook carrierOutlookObj;
	outlooklogin outlook;
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/
	public CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButtonTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		carrierdisplayautopaymenowpopupobj = new CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton();
		carrierloginobj = new CarrierLoginPage();
		carriersamedayachobj = new CarrierSameDAYACH();
		carrierwiretransferobj = new CarrierWireTransfer();
		carriernextdayachobj = new CarrierNextDAYACH();
		carrierRegistrationObj = new CarrierRegisterPage();
		outlook = new outlooklogin();
		carrierOutlookObj = new CarrierOutlook();
		currentTime = new Date();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
	}

	@Test(description = "LP-6802  Register New Carrier", dataProvider = "getCarrierRegisterData")
	public void CarrierRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		if (Email.contains("[uniqueID]")) {
			String uniqueEmail = Email.replace("[uniqueID]", TestUtil.getCurrentDateTime());
			carrierUsername = uniqueEmail;
			carrierPassword = Password;
		} else {
			carrierUsername = Email;
			carrierPassword = Password;
		}

		carrierRegistrationObj.signup();

		// clicking on carrier Register
		carrierRegistrationObj.CarrierRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		carrierRegistrationObj.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		carrierRegistrationObj.setMotorCarrierField(randomNum);

		carrierRegistrationObj.companyname(CompanyName);

		carrierRegistrationObj.doingbussiness(DoingBussinessAS);

		carrierRegistrationObj.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));

		type.selectByVisibleText("C Corporation");

		carrierRegistrationObj.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));

		countryof.selectByIndex(0);

		carrierRegistrationObj.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));

		stateof.selectByVisibleText("California");

		carrierRegistrationObj.CarrierEmail(carrierUsername);

		carrierRegistrationObj.confirmEmail(carrierUsername);

		carrierRegistrationObj.iCertifyClick();

		carrierRegistrationObj.clickNextBtnOnCompanyForm();

		carrierRegistrationObj.ZipCode(ZipCode1);

		carrierRegistrationObj.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		carrierRegistrationObj.address(Address);

		carrierRegistrationObj.city(City);

		carrierRegistrationObj.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));

		state.selectByVisibleText("CA");

		carrierRegistrationObj.clickNextBtnOnAddressForm();

		carrierRegistrationObj.ContactFirstName(FirstNames);

		carrierRegistrationObj.LastName(LastName);
		carrierRegistrationObj.Phone(PhoneNumber);

		carrierRegistrationObj.Password(Password);

		driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
		carrierRegistrationObj.ConfirmPassword(ConfirmPassword);

		carrierRegistrationObj.clickNextBtnOnContactForm();

		carrierRegistrationObj.AccountName(NameonAccount);
		carrierRegistrationObj.BankingAccount(BankAccountNumber);

		carrierRegistrationObj.BankingRouting(RoutingNumber);

		carrierRegistrationObj.ConfirmBankingAccount(ConfirmbankAccountNumber);

		carrierRegistrationObj.clickNextBtnOnBankingForm();
		log.info("Carrier Registration Completed...");

	}

	@Test(description = "LP-6802  Login to Outlook", dataProvider = "getoutlookLoginData", dependsOnMethods = "CarrierRegister")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(description = "LP-6802  Verify New Carrier in Outlook", dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		carrierOutlookObj.clickPopUp();
		carrierOutlookObj.clickOpenMailBox();
		carrierOutlookObj.enterEmail(super.getProperties().getProperty("email"));
		// outlookk.clickOpen();
		getTimestamp();
		carrierOutlookObj.outlookSearchInbox(carrierUsername, currentHour, currentMinutes);
		carrierOutlookObj.handleNewInbox();
		carrierOutlookObj.verifyConfirmationMessage();

	}

	@Test(description = "LP-6802 Switch to admin URL", dependsOnMethods = "outlookloginTest")
	public void Home() throws IOException, AWTException, InterruptedException {
		adminHomePage.AdminURL();
	}

	@Test(description = "LP-6802 Activate Carrier", dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
		adminLoginPage.adminUserPass(Username, pass);

		adminLoginPage.adminLogin();

		adminLoginPage.ClickOnCustomersTab();

		adminLoginPage.Uncheck_Factor();

		adminLoginPage.ClickOnSearchBox(carrierUsername);

		adminLoginPage.ClickOnSearchButton();

		adminLoginPage.DoubleClickID();

		adminLoginPage.StatusIDDropDown();

		adminLoginPage.UpdateButton();

		adminLoginPage.AdminLogOut();

	}

	@Test(description = "LP-6802 First Login as Carrier", dependsOnMethods = "adminLogin")
	public void loginTest(String user, String pass) throws InterruptedException {

		String carrierEIN = "99-9999999";

		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);

		wait.until(ExpectedConditions.elementToBeClickable(carrierloginobj.getEinField()));

		// enter EIN and click Next if enabled
		if (carrierloginobj.getEinField().isEnabled()) {
			carrierloginobj.setEinField(carrierEIN);
			carrierloginobj.clickEinNextButton();
		}

		// accept terms and conditions
		if (carrierloginobj.getTermsAndConditionsCheckBox().isEnabled()) {
			carrierloginobj.clickTermsAndConditionsCheckBox();
			carrierloginobj.clickFinishButton();
			Assert.assertTrue(
					carrierloginobj.getConfirmationPopup().getText()
							.contains("Your LoadPay™ registration has been completed successfully."),
					"Registration success message not found");
			carrierloginobj.clickConfirmationPopupCloseButton();
		}
	}

	/*-------Login to Load Pay as Carrier---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dataProvider = "getCarrierLoginData", dependsOnMethods = "loginTest")
	public void carrierLoginTest(String un, String pwd) throws InterruptedException {
		carrierdisplayautopaymenowpopupobj.loginAsCarrier(carrierUsername, carrierPassword);
	}

	/*-------Verify Auto PayMeNow pop up---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dependsOnMethods = {
			"carrierLoginTest" })
	public void verifyAutoPayMeNowPopupTest() throws InterruptedException {
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowSameDayACHLabel().isDisplayed(),
				"PayMeNow Same Day ACH label NOT Found!");
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowNextDayACHLabel().isDisplayed(),
				"PayMeNow Next Day ACH label NOT Found!");
		Assert.assertTrue(carrierloginobj.getDonotshowagaincheckbox().isDisplayed(),
				"Do Not Display this message again check box NOT Found!");
		Assert.assertTrue(carrierloginobj.getPayMeNowPopupSaveButton().isDisplayed(),
				"PayMeNow pop up Save Button NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();

	}

	/*-------Verify Auto PayMeNow pop up for SameDay ACH ---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dependsOnMethods = {
			"verifyAutoPayMeNowPopupTest" })
	public void verifyPayMeNowPopupforSameDayACHTest() throws InterruptedException {
		carriersamedayachobj.clickPaymenow();
		carriersamedayachobj.clickSelectButton();
		carriersamedayachobj.clickConfirmButton();
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	/*-------Verify Auto PayMeNow popup for Wire Transfer---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dependsOnMethods = {
			"verifyPayMeNowPopupforSameDayACHTest" })
	public void verifyPayMeNowPopupforWireTransferTest() throws InterruptedException {
		carrierwiretransferobj.clickPaymenow();
		carrierwiretransferobj.clickSelectButton();
		carrierwiretransferobj.clickConfirmButton();
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	/*-------Verify Auto PayMeNow popup for NextDay ACH---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dependsOnMethods = {
			"verifyPayMeNowPopupforWireTransferTest" })
	public void verifyPayMeNowPopupforNextDayACHTest() throws InterruptedException, AWTException {
		carriernextdayachobj.clickPaymenow();
		carriernextdayachobj.clickSelectButton();
		carriernextdayachobj.clickConfirmButton();
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	public void getTimestamp() {
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
		log.info("===============================");
	}
}