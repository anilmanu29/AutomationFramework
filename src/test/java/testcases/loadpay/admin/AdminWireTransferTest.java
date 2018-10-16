package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminWireTransfer;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminWireTransferTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLogin;
	AdminWireTransfer adminWireTransfer;
	BrokerNewPayment brokerNewPayment;
	BrokerNewPaymentTest brokerNewPaymentTest;
	BrokerLoginPage brokerloginPage;
	CarrierWireTransfer carrierWireTransfer;
	CarrierLoginPage carrierLoginPage;

	String paymentStatus = "Verified";
	String[] invoiceNumber = new String[2];
	String[] loadId = new String[2];
	Integer paymentAmount;

	String carrierUsername, carrierPassword, brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public AdminWireTransferTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		adminLogin = new AdminLogin();
		adminHomePage = new AdminHomePage();
		brokerloginPage = new BrokerLoginPage();
		brokerNewPayment = new BrokerNewPayment();
		brokerNewPaymentTest = new BrokerNewPaymentTest();
		adminWireTransfer = new AdminWireTransfer();
		carrierLoginPage = new CarrierLoginPage();
		carrierWireTransfer = new CarrierWireTransfer();
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-6230 Admin Wire Transfer", dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerloginPage.Brokerlogin(brokerUsername, brokerPassword);
		log.info("Broker Login");

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6230 Admin Wire Transfer", dependsOnMethods = "loginBroker")
	public void brokerNewPayment() throws InterruptedException {

		for (int i = 0; i < 2; i++) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			invoiceNumber[i] = "NPWT" + TestUtil.getCurrentDateTime();
			loadId[i] = invoiceNumber[i];
			paymentAmount = TestUtil.getRandomNumber(100, 1000);
			log.info("Create new Payment ");
			brokerNewPayment.newPayment();

			brokerNewPayment.carrierEmail(carrierUsername);

			brokerNewPayment.amount(paymentAmount.toString());

			brokerNewPayment.invoiceNumber(invoiceNumber[i]);

			brokerNewPayment.loadId(loadId[i]);

			brokerNewPayment.clickShedulePayment();

			brokerNewPayment.clickShedulePaymenttab();

			brokerNewPayment.searchCarrier(carrierUsername);

			brokerNewPayment.clickSearchButton();

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");

			brokerNewPayment.verifyInvoiceNumber(invoiceNumber[i], paymentAmount.toString());

			// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
			System.out.println(brokerNewPayment.verifyPaymentStatus());
		}
	}

	/*-------Login as Carrier------*/
	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokerNewPayment")
	public void carrierLogin(String un, String pwd) throws InterruptedException {
		brokerNewPayment.logout();
		log.info("Broker LogOut");

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = un;
			carrierPassword = pwd;
		}

		carrierLoginPage.Carrierlogin(carrierUsername, carrierPassword);
		log.info("Carrier Login");

	}

	/*------- Perform PayMeNow Wire Transfer------*/
	@Test(dependsOnMethods = "carrierLogin")
	public void performPaymeNow() throws InterruptedException {

		// carrierWireTransfer.clickPaymenow();
		adminWireTransfer.clickPayMeNowPayment(invoiceNumber[0]);

		carrierWireTransfer.getwiretransferAmount();

		carrierWireTransfer.clickSelectButton();

		carrierWireTransfer.clickConfirmButton();
		log.info("Perform Carrier Wire Transfer");

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.closePaymeNowPopUp();
		}

		// carrierWireTransfer.clickPaymenow();
		adminWireTransfer.clickPayMeNowPayment(invoiceNumber[1]);

		carrierWireTransfer.getwiretransferAmount();

		carrierWireTransfer.clickSelectButton();

		carrierWireTransfer.clickConfirmButton();
		log.info("Perform Carrier Wire Transfer");

		Thread.sleep(1000);

		carrierLoginPage.CarrierLogout();
		log.info("Carrier LogOut");
	}

	/*------Login as Admin And Verify Wire Transfer------------*/
	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "performPaymeNow")
	public void goToAdminPage(String Username, String pass) throws InterruptedException, AWTException {

		adminHomePage.AdminURL();

		adminLogin.adminUserPass(Username, pass);
		adminLogin.adminLogin();
		log.info("Admin Login");

		adminLogin.ClickOnCustomersTab();

		adminLogin.ClickOnSearchBox(brokerUsername);

		adminLogin.ClickOnSearchButton();

		adminLogin.DoubleClickID();

		adminWireTransfer.clickPayments();

		adminWireTransfer.ClickOnsearchKeywordterm(invoiceNumber[0]);

		adminWireTransfer.getPaymentID();

		adminWireTransfer.clickSearch();

		adminWireTransfer.searchKeyword();

		adminWireTransfer.clickSearch1();

		// adminWireTransfer.clickgridcollapse();

		adminWireTransfer.expandPayment(invoiceNumber[0]);

		adminWireTransfer.clickWireTransferButton();

		adminWireTransfer.markOFacCheckbox();

		adminWireTransfer.enterWireTransferConfirmationNumber();

		adminWireTransfer.confirmWireTransfer();
		log.info("Confirm Wire Transfer");

		adminWireTransfer.verifyPaymentIssued();

		/*------- Go To Admin and Fail Payment------*/
		adminLogin.ClickOnCustomersTab();

		adminLogin.ClickOnSearchBox(brokerUsername);

		adminLogin.ClickOnSearchButton();

		adminLogin.DoubleClickID();

		adminWireTransfer.clickPayments();

		adminWireTransfer.ClickOnsearchKeywordterm(invoiceNumber[1]);

		adminWireTransfer.getPaymentID();
		log.info("Get Payment id for second payment");

		adminWireTransfer.clickSearch();

		adminWireTransfer.searchKeyword();

		adminWireTransfer.clickSearch1();

		// adminWireTransfer.clickgridcollapse();
		adminWireTransfer.expandPayment(invoiceNumber[1]);
		adminWireTransfer.clickWireTransferButton();

		adminWireTransfer.enterWireTransferConfirmationNumber();

		adminWireTransfer.clickFailedWireTransferButton();
		log.info("Fail payment");

		adminWireTransfer.verifyPaymentFailed();
		log.info("Confirm Wire Transfer Failed");

		adminLogin.AdminLogOut();
	}

}