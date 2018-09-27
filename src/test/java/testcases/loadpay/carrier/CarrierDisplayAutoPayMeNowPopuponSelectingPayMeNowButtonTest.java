package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;

public class CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButtonTest extends TestBase {

	CarrierDisplayAutoPayMeNowPopuponSelectingPayMeNowButton carrierdisplayautopaymenowpopupobj;
	CarrierLoginPage carrierloginobj;
	CarrierSameDAYACH carriersamedayachobj;
	CarrierWireTransfer carrierwiretransferobj;
	CarrierNextDAYACH carriernextdayachobj;

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
	}

	/*-------Login to Load Pay as Carrier---------*/
	@Test(description = "LP-6802  Carrier_Display_Auto_PayMeNow_popup_on_selecting_PayMeNow_Button", dataProvider = "getCarrierLoginData")
	public void carrierLoginTest(String un, String pwd) throws InterruptedException {
		carrierdisplayautopaymenowpopupobj.loginAsCarrier(un, pwd);
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

	//
	// /*-------Verify CSV file Downloads ---------*/
	// @Test(description = "LP-6628 LoadPay
	// Carrier_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
	// "verifyScheduledPaymentsTab" })
	// public void verifyDownloadCSVFile() throws InterruptedException {
	// // get file count before export
	// int initialFileCount = TestUtil.getFileCount();
	//
	// carrierdownloadcsvfromscheduledpaymentsbj.clickExportButton();
	//
	// // get file count after export
	// int updatedFileCount = TestUtil.getFileCount();
	//
	// // wait 1 second only if the file counts are still equal, then recheck the
	// // updated file count
	// while (updatedFileCount == initialFileCount) {
	// Thread.sleep(1000);
	// updatedFileCount = TestUtil.getFileCount();
	// }
	//
	// Calendar currentDateTime = Calendar.getInstance();
	// Date currentDate = new Date(currentDateTime.getTimeInMillis());
	// SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	// SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	// SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	//
	// expectedFileName = "CarrierPaymentsExport-" + monthFormat.format(currentDate)
	// + "-"
	// + dayFormat.format(currentDate) + "-" + yearFormat.format(currentDate) +
	// ".csv";
	//
	// Assert.assertTrue(TestUtil.verifyFileDownload(expectedFileName), "CSV
	// download not found!");
	//
	// }
	//
	// /*-------Verify CSV file contents ---------*/
	// @Test(description = "LP-6628 LoadPay Carrier_VerifyCSVContents",
	// dependsOnMethods = { "verifyDownloadCSVFile" })
	// public void verifyCsvContents() throws InterruptedException,
	// InvalidFormatException, IOException {
	// List<String[]> dataArray = TestUtil.getCsvContents(expectedFileName,
	// expectedFileName.substring(0, expectedFileName.length() - 4));
	// }

}