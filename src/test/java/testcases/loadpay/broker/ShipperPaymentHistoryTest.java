package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.ShipperPaymentHistory;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import testcases.loadpay.carrier.CarrierRegisterCanadaTest;
import util.TestUtil;

public class ShipperPaymentHistoryTest extends TestBase {

	ShipperPaymentHistory shipperpaymenthistoryobj;
	BrokerLoginPage brokerloginobj;
	CarrierLoginPage carrierLoginPage;
	CarrierPaymeNowFuelCard carrierFuelTestObj;
	ArrayList<String> tabs;
	String brokerUN = "";
	String brokerPWD = "";
	String carrierUsername = "";
	String carrierPassword = "";
	String carrierDOT = "1234567";
	String carrierEIN = "99-9999999";

	public ShipperPaymentHistoryTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		shipperpaymenthistoryobj = new ShipperPaymentHistory();
		brokerloginobj = new BrokerLoginPage();
		carrierLoginPage = new CarrierLoginPage();
		carrierFuelTestObj = new CarrierPaymeNowFuelCard();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void carrierPaymentTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterCanadaTest.carrierUsername;
			carrierPassword = CarrierRegisterCanadaTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		carrierLoginPage.Carrierlogin(carrierUsername, carrierPassword);

		// enter EIN and click Next if enabled
		if (carrierLoginPage.getEinField().isEnabled()) {
			carrierLoginPage.setEinField(carrierEIN);
			carrierLoginPage.clickEinNextButton();
		}

		// accept terms and conditions
		if (carrierLoginPage.getTermsAndConditionsCheckBox().isEnabled()) {
			carrierLoginPage.clickTermsAndConditionsCheckBox();
			carrierLoginPage.clickFinishButton();
			Assert.assertTrue(
					carrierLoginPage.getConfirmationPopup().getText()
							.contains("Your LoadPay™ registration has been completed successfully."),
					"Registration success message not found");
			carrierLoginPage.clickConfirmationPopupCloseButton();
		}

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.getDonotshowagaincheckbox().click();
			carrierLoginPage.getPayMeNowPopupSaveButton().click();
		}

		carrierFuelTestObj.clickPaymenow();
		carrierFuelTestObj.clickSelectButton();
		carrierFuelTestObj.clickaddnewcard();
		carrierFuelTestObj.clickfleetone();
		carrierFuelTestObj.input_accountnbr("6542988");
		carrierFuelTestObj.clicksubmit();
		carrierFuelTestObj.clickfuelcardsubmit();
		carrierFuelTestObj.clickConfirmButton();

		if (carrierLoginPage.getDonotshowagaincheckbox().isDisplayed()) {
			carrierLoginPage.getDonotshowagaincheckbox().click();
			carrierLoginPage.getPayMeNowPopupSaveButton().click();
		}

		carrierFuelTestObj.clickPaymenow();
		carrierFuelTestObj.clickSelectButton();
		carrierFuelTestObj.clickaddnewcard();
		carrierFuelTestObj.clickfleetone();
		carrierFuelTestObj.input_accountnbr("6542988");
		carrierFuelTestObj.clicksubmit();
		carrierFuelTestObj.clickfuelcardsubmit();
		carrierFuelTestObj.clickConfirmButton();

		carrierFuelTestObj.clickPaidTab();
		carrierLoginPage.CarrierLogout();

	}

	@Test(description = "LP-3481 Shipper - Payment History", dataProvider = "getBrokerLoginData", dependsOnMethods = "carrierPaymentTest")
	public void verifyCarrierLogin(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUN = BrokerRegisterCanadaTest.brokerUsername;
			brokerPWD = BrokerRegisterCanadaTest.brokerPassword;
		} else {
			brokerUN = user;
			brokerPWD = pass;
		}

		brokerloginobj.Brokerlogin(brokerUN, brokerPWD);
	}

	@Test(description = "LP-3481 Shipper - Payment History", dependsOnMethods = { "verifyCarrierLogin" })
	public void verifyBrokerPaymentHistoryElements() throws InterruptedException {
		Assert.assertTrue(shipperpaymenthistoryobj.paymenthistorylink.isDisplayed(),
				"Payment History is NOT available");
		// click paymenthistory link
		shipperpaymenthistoryobj.clickPaymentHistorylink();
		// verify Paymenthistory fields
		Assert.assertTrue(shipperpaymenthistoryobj.allbutton.isDisplayed(), "All button NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.searchbutton.isDisplayed(), "Serch button NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.exportbutton.isDisplayed(), "Export button NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.filters.isDisplayed(), "Filters  NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.currentmonth.isDisplayed(), "Current Month NOT found");

		Assert.assertTrue(shipperpaymenthistoryobj.statuscolumn.isDisplayed(), "Status column NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.payselectioncolumn.isDisplayed(), "Pay Selection column NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.amountcolumn.isDisplayed(), "Amount column  NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.carriercolumn.isDisplayed(), "Carrier column NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.loadidcolumn.isDisplayed(), "Load Id column NOT found");
	}

	@Test(description = "LP-3481 Shipper - Payment History", dependsOnMethods = {
			"verifyBrokerPaymentHistoryElements" })
	public void verifyMonthsExpandRolledup() throws InterruptedException {
		shipperpaymenthistoryobj.clickCurrentMonth();
		// verifyng months can be expanded
		Assert.assertTrue(shipperpaymenthistoryobj.currentmonthstatus.getAttribute("aria-expanded").contains("false"),
				"Month should be rolled up");

	}

	@Test(description = "LP-3481 Shipper - Payment History", dependsOnMethods = { "verifyMonthsExpandRolledup" })
	public void verifyPaymentssExpandRolledup() throws InterruptedException {
		// verifyng payments can be expanded/rolled up
		driver.navigate().refresh();
		shipperpaymenthistoryobj.expandcollapsePayments();
	}

	@Test(description = "LP-3481 Shipper - Payment History", dependsOnMethods = { "verifyPaymentssExpandRolledup" })
	public void verifyFiltersFunctionality() throws InterruptedException {
		shipperpaymenthistoryobj.filtercheckboxes();
		// verify PayMEnow filter
		shipperpaymenthistoryobj.clickFilterCheckbox(shipperpaymenthistoryobj.paymenowcheckbox, "PayMeNow");
		// verify Paid filter
		shipperpaymenthistoryobj.clickFilterCheckbox(shipperpaymenthistoryobj.paidcheckbox, "ISSUED");
		// verify Failed filter
		shipperpaymenthistoryobj.clickFilterCheckbox(shipperpaymenthistoryobj.failedcheckbox, "PAYMENT FAILED");
		// verify Cancelled filter
		shipperpaymenthistoryobj.clickFilterCheckbox(shipperpaymenthistoryobj.cancelledcheckbox, "PAYMENT CANCELLED");
		shipperpaymenthistoryobj.filtercheckboxes();
	}

	@Test(description = "LP-3481 Shipper - Payment History", dataProvider = "getBrokerPaymentHistoryData", dependsOnMethods = {
			"verifyFiltersFunctionality" })
	public void verifySearchFunctionality(String amt, String carrier, String loadid, String maxamt, String startdate,
			String enddate) throws InterruptedException {

		// clean up excel formatting if it exists
		amt = TestUtil.removeDecimalZeroFormat(amt);
		loadid = TestUtil.removeDecimalZeroFormat(loadid);
		maxamt = TestUtil.removeDecimalZeroFormat(maxamt);

		// click search button
		shipperpaymenthistoryobj.clickSearchButton();
		// verify elements in the search
		Assert.assertTrue(shipperpaymenthistoryobj.searchfield.isDisplayed(), "Search field NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.searchbuttonn.isDisplayed(), "Search button  NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.advancedsearchlink.isDisplayed(), "Advanced Search link NOT found");
		// verify search for amount
		shipperpaymenthistoryobj.searchAction(amt);
		// verify search for Broker
		shipperpaymenthistoryobj.searchAction(carrier);
		// verify search for LoadID
		shipperpaymenthistoryobj.searchAction(loadid);
		shipperpaymenthistoryobj.searchfield.clear();
		shipperpaymenthistoryobj.searchbuttonn.click();
		// verify Advanced search functionality
		shipperpaymenthistoryobj.AdvancedSearchLinkAction(amt, maxamt, startdate, enddate);
		// shipperpaymenthistoryobj.verifyAdvancedSearchPayments();

	}

	@Test(description = "LP-3472 Carrier - Payment History", dataProvider = "getBrokerPaymentHistoryData", dependsOnMethods = {
			"verifySearchFunctionality" })
	public void verifyExportFunctionality(String amt, String carrier, String loadid, String maxamt, String startdate,
			String enddate) throws InterruptedException {
		// verify Export with Basic (radio button) option
		shipperpaymenthistoryobj.clickExportButton();
		Assert.assertTrue(shipperpaymenthistoryobj.exportstartdate.isDisplayed(), "Export Start Date field NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.exportenddate.isDisplayed(), "Export End Date field NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.basicradiobutton.isDisplayed(), "Basic Radio button NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.detailedradiobutton.isDisplayed(),
				"Detailed Radio button NOT found");
		Assert.assertTrue(shipperpaymenthistoryobj.reportexportbutton.isDisplayed(), "Report Export button NOT found");
		shipperpaymenthistoryobj.clickandEnterExportstartandEnddate(startdate, enddate);
		shipperpaymenthistoryobj.clickRadioButton(shipperpaymenthistoryobj.basicradiobutton);
		shipperpaymenthistoryobj.clickReportExportButton();

		// verify Export with Detailed (radio button) option
		shipperpaymenthistoryobj.clickandEnterExportstartandEnddate(startdate, enddate);
		shipperpaymenthistoryobj.clickRadioButton(shipperpaymenthistoryobj.detailedradiobutton);
		shipperpaymenthistoryobj.clickReportExportButton();

		// verify export by Arrow
		shipperpaymenthistoryobj.filterss.click();
		shipperpaymenthistoryobj.clickArrowExportButton();

		// sleep for 2 minute to allow time to verify csv files
		LocalDate today = LocalDate.now();
		String strDate = today.getMonthValue() + "-" + today.getDayOfMonth() + "-" + today.getYear();
		TestUtil.verifyFileDownload("LoadPay_full_report_" + strDate);
	}

}
