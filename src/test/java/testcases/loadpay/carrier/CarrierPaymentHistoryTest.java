package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierPayMeNowTab;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import pages.loadpay.carrier.CarrierPaymentHistory;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import util.TestUtil;

public class CarrierPaymentHistoryTest extends TestBase {

	CarrierPaymentHistory carrierpaymenthistory;
	CarrierLoginPage loginPage;
	OutlookFunctions outlookLoginObj;
	CarrierLoginPage carrierloginobj;
	CarrierPayMeNowTab carrierPayMeNowTab;
	CarrierPaymeNowFuelCard carrierPmnFuelCardObj;

	ArrayList<String> tabs;
	String carrierUsername, carrierPassword = "";

	public CarrierPaymentHistoryTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		TestUtil.className = this.getClass().getName();
		carrierpaymenthistory = new CarrierPaymentHistory();
		carrierloginobj = new CarrierLoginPage();
		carrierPayMeNowTab = new CarrierPayMeNowTab();
		carrierPmnFuelCardObj = new CarrierPaymeNowFuelCard();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-3472 Carrier - Payment History", dataProvider = "getCarrierLoginData")
	public void verifyCarrierLogin(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
	}

	@Test(description = "LP-3472 Carrier - Payment History", dependsOnMethods = { "verifyCarrierLogin" })
	public void verifyCarrierPaymentHistoryElements() throws InterruptedException {
		Assert.assertTrue(carrierpaymenthistory.paymenthistorylink.isDisplayed(), "Payment History is NOT available");
		carrierpaymenthistory.clickPaymentHistorylink();
		Assert.assertTrue(carrierpaymenthistory.allbutton.isDisplayed(), "All button NOT found");
		Assert.assertTrue(carrierpaymenthistory.searchbutton.isDisplayed(), "Serch button NOT found");
		Assert.assertTrue(carrierpaymenthistory.exportbutton.isDisplayed(), "Export button NOT found");
		Assert.assertTrue(carrierpaymenthistory.filters.isDisplayed(), "Filters  NOT found");

		carrierPayMeNowTab.clickPaymenow();
		carrierPmnFuelCardObj.clickPaymenow();
		carrierPmnFuelCardObj.clickSelectButton();
		carrierPmnFuelCardObj.clickaddnewcard();
		carrierPmnFuelCardObj.clickfleetone();
		carrierPmnFuelCardObj.input_accountnbr("6542988");
		carrierPmnFuelCardObj.clicksubmit();
		carrierPmnFuelCardObj.clickfuelcardsubmit();
		carrierPmnFuelCardObj.clickConfirmButton();
		carrierpaymenthistory.clickPaymentHistorylink();
		Assert.assertTrue(carrierpaymenthistory.currentmonth.isDisplayed(), "Current Month NOT found");
	}

	@Test(description = "LP-3472 Carrier - Payment History", dependsOnMethods = {
			"verifyCarrierPaymentHistoryElements" })
	public void verifyPaymentssExpandCollapse() throws InterruptedException {
		carrierpaymenthistory.clickCurrentMonth();

	}

	@Test(description = "LP-3472 Carrier - Payment History", dependsOnMethods = { "verifyPaymentssExpandCollapse" })
	public void verifyCurrentMonthsExpandCollapse() throws InterruptedException {
		// verifyng payments can be expanded/rolled up
		carrierpaymenthistory.expandcollapseMonths();

	}

	@Test(description = "LP-3472 Carrier - Payment History", dependsOnMethods = { "verifyCurrentMonthsExpandCollapse" })
	public void verifyFiltersFunctionality() throws InterruptedException {
		carrierpaymenthistory.filtercheckboxes();
		// verify PayMEnow filter
		carrierpaymenthistory.clickFilterCheckbox(carrierpaymenthistory.paymenowcheckbox, "PayMeNow");
		// verify Paid filter
		carrierpaymenthistory.clickFilterCheckbox(carrierpaymenthistory.paidcheckbox, "ISSUED");
		// verify Failed filter
		carrierpaymenthistory.clickFilterCheckbox(carrierpaymenthistory.failedcheckbox, "PAYMENT FAILED");
		// verify Cancelled filter
		carrierpaymenthistory.clickFilterCheckbox(carrierpaymenthistory.cancelledcheckbox, "PAYMENT CANCELLED");
		carrierpaymenthistory.filtercheckboxes();
	}

	@Test(description = "LP-3472 Carrier - Payment History", dataProvider = "getCarrierPaymentHistoryData", dependsOnMethods = {
			"verifyFiltersFunctionality" })
	public void verifySearchFunctionality(String amount, String payer, String loadid, String maxamt, String datefrom,
			String dateto) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			amount = BrokerNewPaymentTest.newPaymentAmount.get(0);
			payer = BrokerNewPaymentTest.newPaymentPayer.get(0);
			loadid = BrokerNewPaymentTest.newPaymentLoadId.get(0);
			amount = TestUtil.removeDecimalZeroFormat(amount);
			Integer tempAmt = Integer.parseInt(amount) + 100;
			maxamt = tempAmt.toString();
			datefrom = BrokerNewPaymentTest.strDate;
			dateto = BrokerNewPaymentTest.strDate;
		} else {
			// clean up excel formatting if it exists
			amount = TestUtil.removeDecimalZeroFormat(amount);
			loadid = TestUtil.removeDecimalZeroFormat(loadid);
			maxamt = TestUtil.removeDecimalZeroFormat(maxamt);
		}

		// click search button
		carrierpaymenthistory.clickSearchButton();

		// verify elements in the search

		Assert.assertTrue(carrierpaymenthistory.searchfield.isDisplayed(), "Search field NOT found");
		Assert.assertTrue(carrierpaymenthistory.searchbuttonn.isDisplayed(), "Search button  NOT found");
		Assert.assertTrue(carrierpaymenthistory.advancedsearchlink.isDisplayed(), "Advanced Search link NOT found");
		// verify search for amount
		carrierpaymenthistory.searchAction(amount);
		// verify search for Broker
		carrierpaymenthistory.searchAction(payer);
		// verify search for LoadID
		carrierpaymenthistory.searchAction(loadid);
		carrierpaymenthistory.searchfield.clear();
		carrierpaymenthistory.searchbuttonn.click();
		// verify Advanced search functionality
		carrierpaymenthistory.AdvancedSearchLinkAction(amount, maxamt, datefrom, dateto);
	}

	@Test(description = "LP-3472 Carrier - Payment History", dataProvider = "getCarrierPaymentHistoryData", dependsOnMethods = {
			"verifySearchFunctionality" })
	public void verifyExportFunctionality(String amount, String payer, String loadid, String maxamt, String datefrom,
			String dateto) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			amount = BrokerNewPaymentTest.newPaymentAmount.get(0);
			payer = BrokerNewPaymentTest.newPaymentPayer.get(0);
			loadid = BrokerNewPaymentTest.newPaymentLoadId.get(0);

			amount = TestUtil.removeDecimalZeroFormat(amount);
			Integer tempAmt = Integer.parseInt(amount) + 100;

			maxamt = tempAmt.toString();
			datefrom = BrokerNewPaymentTest.strDate;
			dateto = BrokerNewPaymentTest.strDate;
		} else {
			// clean up excel formatting if it exists
			amount = TestUtil.removeDecimalZeroFormat(amount);
			loadid = TestUtil.removeDecimalZeroFormat(loadid);
			maxamt = TestUtil.removeDecimalZeroFormat(maxamt);
		}

		// verify Export with Basic (radio button) option
		carrierpaymenthistory.clickExportButton();
		Assert.assertTrue(carrierpaymenthistory.exportstartdate.isDisplayed(), "Export Start Date field NOT found");
		Assert.assertTrue(carrierpaymenthistory.exportenddate.isDisplayed(), "Export End Date field NOT found");
		Assert.assertTrue(carrierpaymenthistory.basicradiobutton.isDisplayed(), "Basic Radio button NOT found");
		Assert.assertTrue(carrierpaymenthistory.detailedradiobutton.isDisplayed(), "Detailed Radio button NOT found");
		Assert.assertTrue(carrierpaymenthistory.reportexportbutton.isDisplayed(), "Report Export button NOT found");
		carrierpaymenthistory.clickandEnterExportstartandEnddate(datefrom, dateto);
		carrierpaymenthistory.clickRadioButton(carrierpaymenthistory.basicradiobutton);
		carrierpaymenthistory.clickReportExportButton();

		// verify Export with Detailed (radio button) option
		carrierpaymenthistory.clickandEnterExportstartandEnddate(datefrom, dateto);
		carrierpaymenthistory.clickRadioButton(carrierpaymenthistory.detailedradiobutton);
		carrierpaymenthistory.clickReportExportButton();

		// verify export by Arrow
		// carrierpaymenthistory.currentmonth.click();
		carrierpaymenthistory.clickArrowExportButton();

		// sleep for 2 minute to allow time to verify csv files
		// TODO need an automated approach (download csv test?)
		// Thread.sleep(120000);
	}

}
