package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
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

public class AdminWireTransferTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminLogin adminLogin;
	AdminWireTransfer adminWireTransfer;
	BrokerNewPayment brokerNewPayment;
	BrokerNewPaymentTest brokerNewPaymentTest;
	BrokerLoginPage brokerloginPage;
	CarrierWireTransfer carrierWireTransfer;
	CarrierLoginPage carrierLoginPage;

	String acountName;
	String paymentStatus = "Verified";
	String invoice;
	String email;
	ArrayList<String> invoiceList;

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
		invoiceList = new ArrayList<String>();
		log = Logger.getLogger(AdminWireTransferTest.class.getName());
		log.info("Test Set Up");
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-6230 Admin Wire Transfer", dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerloginPage.Brokerlogin(un, pwd);
		log.info("Broker Login");

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6230 Admin Wire Transfer", dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokerNewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		log.info("Create new Payment ");
		brokerNewPayment.newPayment();

		brokerNewPayment.carrierEmail(cemail);

		brokerNewPayment.amount(amt);

		invoice = brokerNewPayment.invoiceNumber(invoiceno);
		invoiceList.add(invoice);

		brokerNewPayment.loadId(loadid);

		brokerNewPayment.clickShedulePayment();

		brokerNewPayment.clickShedulePaymenttab();

		brokerNewPayment.searchCarrier(cemail);

		brokerNewPayment.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		brokerNewPayment.verifyInvoiceNumber(invoiceno, amt);

		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(brokerNewPayment.verifyPaymentStatus());

	}

	/*-------Login as Carrier------*/
	@Test(dataProvider = "getCarrierLoginData", dependsOnMethods = "brokerNewPayment")
	public void carrierLogin(String un, String pwd) throws InterruptedException {
		brokerNewPayment.logout();
		log.info("Broker LogOut");

		carrierLoginPage.Carrierlogin(un, pwd);
		log.info("Carrier Login");

	}

	/*------- Perform PayMeNow Wire Transfer------*/
	@Test(dependsOnMethods = "carrierLogin")
	public void performPaymeNow() throws InterruptedException {
		carrierWireTransfer.getAmount();

		carrierWireTransfer.clickPaymenow();

		carrierWireTransfer.getwiretransferAmount();

		carrierWireTransfer.clickSelectButton();

		carrierWireTransfer.clickConfirmButton();
		log.info("Perform Carrier Wire Transfer");

		carrierWireTransfer.getAmount();

		carrierWireTransfer.clickPaymenow();

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

		System.out.println(BrokerLoginPage.bemail);
		adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);

		adminLogin.ClickOnSearchButton();

		adminLogin.DoubleClickID();

		adminWireTransfer.clickPayments();

		adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(0));

		adminWireTransfer.getPaymentID();

		adminWireTransfer.clickSearch();

		adminWireTransfer.searchKeyword();

		adminWireTransfer.clickSearch1();

		adminWireTransfer.clickgridcollapse();

		adminWireTransfer.clickWireTransferButton();

		adminWireTransfer.markOFacCheckbox();

		adminWireTransfer.enterWireTransferConfirmationNumber();

		adminWireTransfer.confirmWireTransfer();
		log.info("Confirm Wire Transfer");

		adminWireTransfer.verifyPaymentIssued();

		/*------- Go To Admin and Fail Payment------*/
		adminLogin.ClickOnCustomersTab();

		System.out.println(BrokerLoginPage.bemail);
		adminLogin.ClickOnSearchBox(BrokerLoginPage.bemail);

		adminLogin.ClickOnSearchButton();

		adminLogin.DoubleClickID();

		adminWireTransfer.clickPayments();

		adminWireTransfer.ClickOnsearchKeywordterm(invoiceList.get(1));

		adminWireTransfer.getPaymentID();
		log.info("Get Payment id for second payment");

		adminWireTransfer.clickSearch();

		adminWireTransfer.searchKeyword();

		adminWireTransfer.clickSearch1();

		adminWireTransfer.clickgridcollapse();

		adminWireTransfer.clickWireTransferButton();

		adminWireTransfer.enterWireTransferConfirmationNumber();

		adminWireTransfer.clickFailedWireTransferButton();
		log.info("Fail payment");

		adminWireTransfer.verifyPaymentFailed();
		log.info("Confirm Wire Transfer Failed");

		adminLogin.AdminLogOut();
	}

}
