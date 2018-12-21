package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminCustomersPaymentsPage;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
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
 *  6.  Upload Check File
 *  7.  Verify successfully uploaded message
 *  8.  Verify check number present on payment id field
*/

public class AdminPayByCheckFileUploadTest extends TestBase {
	AdminHomePage adminHomePage;
	AdminCustomersPaymentsPage adminCustomersPaymentsPage;
	AdminLogin adminLogin;
	BrokerNewPayment brokerNewPayment;
	BrokerLoginPage brokerLoginPage;

	String carrierUsername = "";
	String dateTime = "";
	String brokerUsername = "";
	String brokerPassword = "";

	String csvFilePath = super.getProperties().getProperty("PayByCheckCsvUploadPath");

	public String email;
	public ArrayList<String> newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNumber;

	public String strDate;
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

		// write paymentIDs to a CSV file
		String[][] csvData = new String[paymentIds.size() + 1][14];
		csvData[0][0] = "LOADPAY PAYMENT ID#*";
		csvData[0][1] = "TERMS*";
		csvData[0][2] = "CARRIER PAYMENT DATE*";
		csvData[0][3] = "CHECK FEE*";
		csvData[0][4] = "COMPANY NAME (MAKE CHECK PAYABLE TO)*";
		csvData[0][5] = "STREET ADDRESS*";
		csvData[0][6] = "CITY*";
		csvData[0][7] = "ST*";
		csvData[0][8] = "ZIP*";
		csvData[0][9] = "PHONE*";
		csvData[0][10] = "MC";
		csvData[0][11] = "DOT";
		csvData[0][12] = "CONTACT NAME*";
		csvData[0][13] = "CHECK NUMBER*";

		LocalDateTime now = LocalDateTime.now();
		String todaysDate = now.getMonthValue() + "/" + now.getDayOfMonth() + "/" + now.getYear();

		for (int i = 0; i < paymentIds.size(); i++) {
			// myList.add(new String[] { "Name", "Class", "Marks" });
			csvData[i + 1][0] = paymentIds.get(i);
			csvData[i + 1][1] = "PayMeNow";
			csvData[i + 1][2] = todaysDate;
			csvData[i + 1][3] = "Yes";
			csvData[i + 1][4] = "Ryan Carrier Company";
			csvData[i + 1][5] = "645 E. Missouri Suite 119";
			csvData[i + 1][6] = "Phoenix";
			csvData[i + 1][7] = "AZ";
			csvData[i + 1][8] = "85012";
			csvData[i + 1][9] = "480-773-9907";
			csvData[i + 1][10] = "MC123456";
			csvData[i + 1][11] = "54283";
			csvData[i + 1][12] = "Ryan Hill";
			csvData[i + 1][13] = "543312";
		}

		TestUtil.writeDataForCustomSeperatorCSV(csvFilePath, csvData, ",");

		// click paybyCheckFileUplaod
		adminCustomersPaymentsPage.payByCheckFileUploadMenu();
		// Click Upload selected file
		adminCustomersPaymentsPage.uploadFile();
		// Verify successful upload messsage
		adminCustomersPaymentsPage.sucessfulCheckUpload();

		// store payment Id
		List<String> paymentIdsSecond = new ArrayList<String>();

		for (int i = 0; i < newPaymentInvoiceNumber.size(); i++) {
			// open Customres Tab
			adminLogin.ClickOnCustomersTab();
			// enter customer name
			adminLogin.ClickOnSearchBox(brokerUsername);
			// click search
			adminLogin.ClickOnSearchButton();
			// open customer id
			adminLogin.DoubleClickID();
			// click payments
			adminCustomersPaymentsPage.clickPayments();
			// Search for invoice number
			adminCustomersPaymentsPage.ClickOnsearchKeyword(newPaymentInvoiceNumber.get(i));
			// click Payment id
			adminCustomersPaymentsPage.clickCarrierkPayment();
			// switch to new Tab
			adminCustomersPaymentsPage.SwitchtoTab(2);
			// Expand Payment id
			adminCustomersPaymentsPage.getExpandedPayment();
			// GetCheckNumber field
			adminCustomersPaymentsPage.getCheckNumberField();
			adminCustomersPaymentsPage.SwitchtoTab(1);

		}

	}

}
