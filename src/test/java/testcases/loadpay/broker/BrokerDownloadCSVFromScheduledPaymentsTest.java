package testcases.loadpay.broker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerDownloadCSVFromScheduledPayments;
import util.TestUtil;

public class BrokerDownloadCSVFromScheduledPaymentsTest extends TestBase {

	BrokerDownloadCSVFromScheduledPayments brokerdownloadcsvfromscheduledpaymentsbj;
	String expectedFileName = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public BrokerDownloadCSVFromScheduledPaymentsTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		brokerdownloadcsvfromscheduledpaymentsbj = new BrokerDownloadCSVFromScheduledPayments();
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(description = "LP-6627 LoadPay Broker_DownloadCSVfrom_ScheduledPayments", dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerdownloadcsvfromscheduledpaymentsbj.loginAsBroker(brokerUsername, brokerPassword);
		System.out.println("loginAsBrokerTest - Passed");
	}

	/*-------Verify Scheduled Payments Tab---------*/
	@Test(description = "LP-6627 LoadPay Broker_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"loginAsBrokerTest" })
	public void verifyScheduledPaymentsTab() throws InterruptedException {
		Assert.assertTrue(brokerdownloadcsvfromscheduledpaymentsbj.getScheduledPaymenttab().isDisplayed(),
				"Scheduled Payments Tab NOT Found!");
		brokerdownloadcsvfromscheduledpaymentsbj.clickScheduledPaymetsTab();
		System.out.println("verifyScheduledPaymentsTab - Passed");
	}

	/*-------Verify Export Button ---------*/
	@Test(description = "LP-6627 LoadPay Broker_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"verifyScheduledPaymentsTab" })
	public void verifyExportButton() throws InterruptedException {
		Assert.assertTrue(brokerdownloadcsvfromscheduledpaymentsbj.getExportButton().isDisplayed(),
				"Export button in Scheduled Payments tab NOT Found!");
		System.out.println("verifyExportButton - Passed");
	}

	/*-------Verify CSV file Downloads ---------*/
	@Test(description = "LP-6627 LoadPay Broker_DownloadCSVfrom_ScheduledPayments", dependsOnMethods = {
			"verifyScheduledPaymentsTab" })
	public void verifyDownloadCSVFile() throws InterruptedException, IOException {
		// get file count before export
		int initialFileCount = TestUtil.getFileCount();

		// click export button
		brokerdownloadcsvfromscheduledpaymentsbj.clickExportButton();

		// get file count after export
		int updatedFileCount = TestUtil.getFileCount();

		// wait 1 second only if the file counts are still equal, then recheck the
		// updated file count
		while (updatedFileCount == initialFileCount) {
			Thread.sleep(1000);
			updatedFileCount = TestUtil.getFileCount();
		}

		// verify csv files
		Calendar currentDateTime = Calendar.getInstance();
		Date currentDate = new Date(currentDateTime.getTimeInMillis());
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

		expectedFileName = "BrokerPaymentsExport-" + monthFormat.format(currentDate) + "-"
				+ dayFormat.format(currentDate) + "-" + yearFormat.format(currentDate) + ".csv";

		Assert.assertTrue(TestUtil.verifyFileDownload(expectedFileName), "CSV download not found!");

		System.out.println("verifyDownloadCSVFile - Passed");
	}

	/*-------Verify CSV file contents ---------*/
	@Test(description = "LP-6627 LoadPay Broker_VerifyCSVContents", dependsOnMethods = { "verifyDownloadCSVFile" })
	public void verifyCsvContents() throws InterruptedException, InvalidFormatException, IOException {
		List<String[]> dataArray = TestUtil.getCsvContents(expectedFileName);
	}

}
