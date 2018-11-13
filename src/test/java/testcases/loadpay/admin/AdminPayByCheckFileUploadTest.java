package testcases.loadpay.admin;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminCustomersPaymentsPage;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerEditPaymentAdminPaymentssubmenu;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

/*  1.  Login as a Broker
 *  2.  Create a Payments to carrier
 *  3.  Login as an Admin
 *  4   Do to Payments and Get Payments ID and Parse it to CSV file
 *  5.  Go to PayByCheckFile Upload Menu 
 *  6.  Get number of Check File Upload records before
 *  7.  Upload Check File
 *  8.  Verify successfully uploaded message
 *  9.  Get number of Check File Upload records after
 *  10. Click PayByCheck Download template link 
*/

public class AdminPayByCheckFileUploadTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminCustomersPaymentsPage adminCustomersPaymentsPage;
	AdminLogin adminLogin;
	BrokerNewPayment brokerNewPayment;
	BrokerLoginPage brokerLoginPage;
	String brokerUsername = "";
	String brokerPassword = "";
	public String email;
	public ArrayList<String> newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNumber;
	public String strDate;
	String carrierUsername = "";
	String dateTime = "";
	LocalDate today;
	Boolean newDateUsed = false;
	int beforeCount = 0;
	int afterCount = 0;

	public AdminPayByCheckFileUploadTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		wait = new WebDriverWait(driver, 30);
		adminLogin = new AdminLogin();
		adminHomePage = new AdminHomePage();
		brokerLoginPage = new BrokerLoginPage();
		brokerNewPayment = new BrokerNewPayment();
		adminCustomersPaymentsPage = new AdminCustomersPaymentsPage();
		newPaymentAmount = new ArrayList<String>();
		newPaymentLoadId = new ArrayList<String>();
		newPaymentPayer = new ArrayList<String>();
		newPaymentInvoiceNumber = new ArrayList<String>();

	}

	@AfterClass
	public void cleanUp() {
		newDateUsed = false;
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String email, String pwd) {
		dateTime = TestUtil.getCurrentDateTime();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			today = LocalDate.now();
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = email;
			brokerPassword = pwd;
		}

		brokerLoginPage = new BrokerLoginPage();
		brokerLoginPage.Brokerlogin(brokerUsername, brokerPassword);
	}

	/*-------Scheduling New Payment as a Broker---------*/
	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "", dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokerNewPayment(String cemail, String invoiceNumber, String loadid, String amt)
			throws InterruptedException {
		brokerNewPayment = new BrokerNewPayment();
		brokerNewPayment.newPayment();
		Integer intPaymentAmount = TestUtil.getRandomNumber(100, 1000);

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
		} else {
			carrierUsername = cemail;
		}
		invoiceNumber = "NP" + TestUtil.getCurrentDateTime();
		loadid = invoiceNumber;
		newPaymentAmount.add(intPaymentAmount.toString());
		newPaymentLoadId.add(loadid);
		newPaymentInvoiceNumber.add(invoiceNumber);

		// newPaymentAmount.add(intPaymentAmount.toString());
		// String invoiceNumber = "NP" + TestUtil.getCurrentDateTime();
		// newPaymentInvoiceNumber.add(invoiceNumber);
		// newPaymentLoadId = newPaymentInvoiceNumber;

		brokerNewPayment.carrierEmail(carrierUsername);
		brokerNewPayment.amount(intPaymentAmount.toString());
		brokerNewPayment.loadId(invoiceNumber);
		brokerNewPayment.invoiceNumber(invoiceNumber);

		today = LocalDate.now();
		Integer month = today.getMonthValue() + 1;
		String strDate = month.toString() + "/" + today.getDayOfMonth() + "/" + today.getYear();
		brokerNewPayment.setField_ScheduleDate(strDate);

		// bp.advanceCheckbox();
		//
		brokerNewPayment.clickShedulePayment();

		brokerNewPayment.clickShedulePaymenttab();

		brokerNewPayment.searchCarrier(carrierUsername);

		brokerNewPayment.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		brokerNewPayment.verifyInvoiceNumber(invoiceNumber, intPaymentAmount.toString());
	}

	/*-------Login to Admin ---------*/
	@Test(description = "", dataProvider = "getAdminLoginData", dependsOnMethods = "brokerNewPayment")
	public void loginAsAdminTest(String username, String password) throws InterruptedException, AWTException {
		adminCustomersPaymentsPage.openandSwitchtoNewTab();
		adminHomePage.AdminURL();
		adminLogin.adminUserPass(username, password);
		adminLogin.adminLogin();
		adminLogin.ClickOnCustomersTab();
		adminLogin.ClickOnSearchBox(brokerUsername);
		Thread.sleep(1000);
		adminLogin.ClickOnSearchButton();
		Thread.sleep(1000);
		adminLogin.DoubleClickID();
	}

	/*-------Verify Admin Payments--------*/
	@Test(description = "", dependsOnMethods = "loginAsAdminTest")
	public void AdminPaymentSubMenu() throws InterruptedException, AWTException, IOException {
		adminCustomersPaymentsPage.clickPayments();
		List<String> paymentIds = new ArrayList<String>();

		for (int i = 0; i < newPaymentInvoiceNumber.size(); i++) {
			adminCustomersPaymentsPage.ClickOnsearchKeyword(newPaymentInvoiceNumber.get(i));
			// adminCustomersPaymentsPage.clickCarrierkPayment();
			// Assert.assertTrue(adminCustomersPaymentsPage.getExpandedPayment().isDisplayed(),
			// "Payment has been collapsed");
			paymentIds.add(adminCustomersPaymentsPage.getPaymentIdAndStoreItInVariable());

		}
		adminCustomersPaymentsPage.payByCheckFileUploadMenu();
		beforeCount = adminCustomersPaymentsPage.listPayByCheckUpload();
		adminCustomersPaymentsPage.uploadFile();
		//afterCount = adminCustomersPaymentsPage.listPayByCheckUpload();
		//assertEquals(beforeCount, afterCount - 2);

	}

}
