package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.outlooklogin;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import pages.loadpay.carrier.CarrierRegisterPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButtonTest extends TestBase {

	CarrierRegisterPage carrierRegistrationObj;
	CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton carrierdisplayautopaymenowpopupobj;
	CarrierLoginPage carrierloginobj;
	CarrierSameDAYACH carrierSameDayObj;
	CarrierWireTransfer carrierWireTransferObj;
	CarrierNextDAYACH carrierNextDayObj;
	CarrierPaymeNowFuelCard carrierFuelCardObj;
	CarrierOutlook carrierOutlookObj;
	BrokerLoginPage brokerLoginObj;
	outlooklogin outlook;
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	LocalDate today;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	String carrierUsername, carrierPassword = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButtonTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		carrierdisplayautopaymenowpopupobj = new CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton();
		carrierloginobj = new CarrierLoginPage();
		carrierSameDayObj = new CarrierSameDAYACH();
		carrierWireTransferObj = new CarrierWireTransfer();
		carrierNextDayObj = new CarrierNextDAYACH();
		carrierFuelCardObj = new CarrierPaymeNowFuelCard();
		carrierRegistrationObj = new CarrierRegisterPage();
		outlook = new outlooklogin();
		carrierOutlookObj = new CarrierOutlook();
		currentTime = new Date();
		adminHomePage = new AdminHomePage();
		adminLoginPage = new AdminLogin();
		brokerLoginObj = new BrokerLoginPage();
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
		carrierOutlookObj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		// outlookk.clickOpen();
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		carrierOutlookObj.outlookSearchInbox(carrierUsername, currentHour, currentMinutes);
		carrierOutlookObj.handleNewInbox(carrierUsername);
		carrierOutlookObj.verifyConfirmationMessage();

	}

	@Test(description = "LP-6802 Switch to admin URL", dependsOnMethods = "outlookloginTest")
	public void switchToAdmin() throws IOException, AWTException, InterruptedException {
		adminHomePage.AdminURL();
	}

	@Test(description = "LP-6802 Activate Carrier", dataProvider = "getAdminLoginData", dependsOnMethods = "switchToAdmin")
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
	}

	@Test(description = "LP-6802 First Login as Carrier", dependsOnMethods = "adminLogin")
	public void carrierFirstLogin() throws InterruptedException {

		driver.get(super.getProperties().getProperty("loadPayURL"));

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
			Assert.assertTrue(carrierloginobj.getConfirmationPopup().getText().contains(
					"registration has been completed successfully."), "Registration success message not found");
			carrierloginobj.clickConfirmationPopupCloseButton();
		}

		// log off
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(carrierloginobj.closePayMeNowNotification));

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");

		carrierloginobj.closePayMeNowNotification.click();
		carrierloginobj.CarrierLogout();
	}

	@Test(description = "LP-6802  Login as Broker", dependsOnMethods = "carrierFirstLogin", dataProvider = "getBrokerLoginData")
	public void loginBroker(String email, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			today = LocalDate.now();
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = email;
			brokerPassword = pwd;
		}

		brokerLoginObj.Brokerlogin(brokerUsername, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6802  Make New Payment", dependsOnMethods = "loginBroker")
	public void brokerNewPayment() throws InterruptedException {

		String invoiceNum, loadID, paymentAmount = "";
		BrokerNewPayment brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();

		Integer month = today.getMonthValue() + 1;
		String strDate = month.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();
		Integer intPaymentAmount = 0;

		for (int i = 0; i < 4; i++) {
			invoiceNum = "NP" + TestUtil.getCurrentDateTime();
			loadID = invoiceNum;
			intPaymentAmount = TestUtil.getRandomNumber(100, 1000);
			paymentAmount = intPaymentAmount.toString();

			brokerPaymentObj.carrierEmail(carrierUsername);
			brokerPaymentObj.amount(paymentAmount);
			brokerPaymentObj.invoiceNumber(invoiceNum);
			brokerPaymentObj.loadId(loadID);
			brokerPaymentObj.setField_ScheduleDate(strDate);
			brokerPaymentObj.clickShedulePayment();
			brokerPaymentObj.clickShedulePaymenttab();
			brokerPaymentObj.searchCarrier(carrierUsername);
			brokerPaymentObj.clickSearchButton();
			brokerPaymentObj.verifyInvoiceNumber(invoiceNum, paymentAmount);
			brokerPaymentObj.newPayment();
		}

		brokerLoginObj.BrokerLogout();
		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
	}

	/*-------Verify Auto PayMeNow popup for NextDay ACH---------*/
	@Test(description = "LP-6802  Carrier_PayMeNow_NextDayACH", dependsOnMethods = { "brokerNewPayment" })
	public void verifyPayMeNowPopupforNextDayACHTest() throws InterruptedException, AWTException {
		carrierNextDayObj.clickPaymenow();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowNextDayACHLabel().isDisplayed(),
				"PayMeNow Next Day ACH label NOT Found!");

		carrierNextDayObj.clickSelectButton();
		carrierNextDayObj.clickConfirmButton();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	/*-------Verify Auto PayMeNow pop up for SameDay ACH ---------*/
	@Test(description = "LP-6802  Carrier_PayMeNow_SameDayACH", dependsOnMethods = {
			"verifyPayMeNowPopupforNextDayACHTest" })
	public void verifyPayMeNowPopupforSameDayACHTest() throws InterruptedException {
		carrierSameDayObj.clickPaymenow();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowSameDayACHLabel().isDisplayed(),
				"PayMeNow Same Day ACH label NOT Found!");

		carrierSameDayObj.clickSelectButton();
		carrierSameDayObj.clickConfirmButton();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	/*-------Verify Auto PayMeNow popup for Wire Transfer---------*/
	@Test(description = "LP-6802  Carrier_PayMeNow_WireTransfer", dependsOnMethods = {
			"verifyPayMeNowPopupforSameDayACHTest" })
	public void verifyPayMeNowPopupforWireTransferTest() throws InterruptedException {
		carrierWireTransferObj.clickPaymenow();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowWireLabel().isDisplayed(),
				"PayMeNow Wire Transfer label NOT Found!");

		carrierWireTransferObj.clickSelectButton();
		carrierWireTransferObj.clickConfirmButton();
		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();
	}

	/*-------Verify Auto PayMeNow popup for Fuel Cards---------*/
	@Test(description = "LP-6802  Carrier_PayMeNow_FuelCards", dataProvider = "getCarrierFuelcardaccountNumbersData", dependsOnMethods = {
			"verifyPayMeNowPopupforWireTransferTest" })
	public void verifyPayMeNowPopupforFuelCardsTest(String fleet_accountnbr, String fts_accountnbr)
			throws InterruptedException {

		carrierFuelCardObj.clickPaymenow();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getPayMeNowFuelLabel().isDisplayed(),
				"PayMeNow Fuel Card label NOT Found!");

		carrierFuelCardObj.clickSelectButton();
		carrierFuelCardObj.clickaddnewcard();
		carrierFuelCardObj.clickfleetone();
		carrierFuelCardObj.input_accountnbr(fleet_accountnbr);
		carrierFuelCardObj.clicksubmit();
		carrierFuelCardObj.clickfuelcardsubmit();
		carrierFuelCardObj.clickConfirmButton();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();

		carrierFuelCardObj.clickPaidTab();
		carrierFuelCardObj.clickpaymenowtab();

		carrierFuelCardObj.clickPaymenow();
		carrierFuelCardObj.clickSelectButton();
		carrierFuelCardObj.clickaddnewcard();
		carrierFuelCardObj.clickFTS();
		carrierFuelCardObj.input_accountnbr(fts_accountnbr);
		carrierFuelCardObj.clicksubmit();
		carrierFuelCardObj.clickfuelcardsubmit();
		carrierFuelCardObj.clickConfirmButton();

		Assert.assertTrue(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup NOT Found!");
		Assert.assertTrue(carrierloginobj.getDonotshowagaincheckbox().isDisplayed(),
				"Do Not Display this message again check box NOT Found!");
		Assert.assertTrue(carrierloginobj.getPayMeNowPopupSaveButton().isDisplayed(),
				"PayMeNow pop up Save Button NOT Found!");
		carrierdisplayautopaymenowpopupobj.clickPopupCloseButton();

		carrierloginobj.CarrierLogout();
	}

	@Test(description = "LP-6802  Carrier_PayMeNow_FuelCards", dependsOnMethods = "verifyPayMeNowPopupforFuelCardsTest")
	public void verifyPayMeNowPopupIsDisabled() throws InterruptedException {
		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);

		carrierNextDayObj.clickPaymenow();
		Assert.assertFalse(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup Found!");

		carrierSameDayObj.clickPaymenow();
		Assert.assertFalse(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup Found!");

		carrierWireTransferObj.clickPaymenow();
		Assert.assertFalse(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup Found!");

		carrierFuelCardObj.clickPaymenow();
		Assert.assertFalse(carrierdisplayautopaymenowpopupobj.getAutoPayMeNowPopup().isDisplayed(),
				"Auto PayMeNowPopup Found!");

		carrierloginobj.CarrierLogout();
	}

}