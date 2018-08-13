package testcases.loadpay.broker;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerDownloadCSVFromScheduledPayments;

public class BrokerDownloadCSVFromScheduledPaymentsTest extends TestBase {

	BrokerDownloadCSVFromScheduledPayments brokerdownloadcsvfromscheduledpaymentsbj;

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
		brokerdownloadcsvfromscheduledpaymentsbj.loginAsBroker(un, pwd);
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
	public void verifyDownloadCSVFile() throws InterruptedException {
		brokerdownloadcsvfromscheduledpaymentsbj.clickExportButton();

		// sleep for 1 minute to allow time to verify csv files
		Thread.sleep(60000);
		System.out.println("verifyDownloadCSVFile - Passed");
	}

}
