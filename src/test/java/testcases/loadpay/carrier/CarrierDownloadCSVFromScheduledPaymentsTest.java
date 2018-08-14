package testcases.loadpay.carrier;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierDownloadCSVFromScheduledPayments;
import util.TestUtil;

public class CarrierDownloadCSVFromScheduledPaymentsTest extends TestBase {

	CarrierDownloadCSVFromScheduledPayments carrierdownloadcsvfromscheduledpaymentsbj;

	/*-------Initializing driver---------*/
	public CarrierDownloadCSVFromScheduledPaymentsTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		carrierdownloadcsvfromscheduledpaymentsbj = new CarrierDownloadCSVFromScheduledPayments();
	}

	/*-------Login to Load Pay as Carrier---------*/
	@Test(description = "LP-6628 LoadPay Carrier_DownloadCSVfrom_ScheduledPayments", dataProvider = "getCarrierLoginData")
	public void loginAsCarrierTest(String un, String pwd) {
		carrierdownloadcsvfromscheduledpaymentsbj.loginAsCarrier(un, pwd);
	}

	/*-------Verify Scheduled Payments Tab---------*/
	@Test(description = "LP-6628 LoadPay Carrier_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"loginAsCarrierTest" })
	public void verifyScheduledPaymentsTab() throws InterruptedException {
		Assert.assertTrue(carrierdownloadcsvfromscheduledpaymentsbj.getScheduledPaymenttab().isDisplayed(),
				"Scheduled Payments Tab NOT Found!");
		carrierdownloadcsvfromscheduledpaymentsbj.clickScheduledPaymetsTab();
	}

	/*-------Verify Export Button ---------*/
	@Test(description = "LP-6628 LoadPay Carrier_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"verifyScheduledPaymentsTab" })
	public void verifyExportButton() throws InterruptedException {
		Assert.assertTrue(carrierdownloadcsvfromscheduledpaymentsbj.getExportButton().isDisplayed(),
				"Export button in Scheduled Payments tab NOT Found!");
	}

	/*-------Verify CSV file Downloads ---------*/
	@Test(description = "LP-6628 LoadPay Carrier_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"verifyScheduledPaymentsTab" })
	public void verifyDownloadCSVFile() throws InterruptedException {
		carrierdownloadcsvfromscheduledpaymentsbj.clickExportButton();

		Calendar currentDateTime = Calendar.getInstance();
		Date currentDate = new Date(currentDateTime.getTimeInMillis());
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

		Assert.assertTrue(
				TestUtil.verifyFileDownload("CarrierPaymentsExport-" + monthFormat.format(currentDate) + "-"
						+ dayFormat.format(currentDate) + "-" + yearFormat.format(currentDate) + ".csv"),
				"CSV download not found!");

	}

}
