package testcases.loadpay.admin;

import java.awt.AWTException;
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
import pages.loadpay.admin.AdminDailyNACHAPaymentsFile;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import util.TestUtil;

public class AdminDailyNACHAPaymentsFileTest extends TestBase {

	AdminDailyNACHAPaymentsFile admindailynachapaymentsfileobj;
	AdminHomePage adminhomepage;
	AdminLogin adminloginobj;
	String expectedFileName = "";

	/*-------Initializing driver---------*/
	public AdminDailyNACHAPaymentsFileTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		admindailynachapaymentsfileobj = new AdminDailyNACHAPaymentsFile();
		adminhomepage = new AdminHomePage();
		adminloginobj = new AdminLogin();
	}

	/*-------Admin Login ---------*/
	@Test(description = "LP-3494 LoadPay Admin_Daily_NACHA_Payments_File", dataProvider = "getAdminLoginData")
	public void adminLoginTest(String username, String password) throws InterruptedException, AWTException {
		adminhomepage.AdminURL();
		adminloginobj.adminUserPass(username, password);
		adminloginobj.adminLogin();
		log.info("adminLoginTest - Passed");
	}

	/*-------verify daily nacha payment files ---------*/
	@Test(description = "LP-3494 LoadPay Admin_Daily_NACHA_Payments_File", dependsOnMethods = { "adminLoginTest" })
	public void verifyDailyNACHAPaymentFilesTest() throws InterruptedException, AWTException {
		Assert.assertTrue(admindailynachapaymentsfileobj.getDailyNACHAPaymentFiles().isDisplayed(),
				"Daily NACHA Payment Files NOT Found!");
		admindailynachapaymentsfileobj.clickDailyNACHAPaymentFile();
		Assert.assertTrue(admindailynachapaymentsfileobj.getFilesLabel().getText().contains("Files"),
				"Files Label NOT Found!");
		Assert.assertTrue(admindailynachapaymentsfileobj.getNACHAFilesLabel().getText()
				.contains("Select a file from the left to see its content"), "Select Files Label NOT Found!");
		admindailynachapaymentsfileobj.clickNACHAFileButon();
		log.info("verifyDailyNACHAPaymentFilesTest - Passed");
	}

	/*-------Verify CSV file Downloads ---------*/
	@Test(description = "LP-3494 LoadPay Admin_Daily_NACHA_Payments_File", dependsOnMethods = {
			"verifyDailyNACHAPaymentFilesTest" })
	public void verifyDownloadCSVFileTest() throws InterruptedException, IOException {

		{
			// get file count before export
			int initialFileCount = TestUtil.getFileCount();

			Assert.assertTrue(admindailynachapaymentsfileobj.getDownloadCSVLink().isDisplayed(),
					"Download as CSV link NOT Found!");

			// click download link
			admindailynachapaymentsfileobj.clickDownloadCSVLink();
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

			expectedFileName = monthFormat.format(currentDate) + "-" + dayFormat.format(currentDate) + "-"
					+ yearFormat.format(currentDate) + "_NACHAPaymentFile_"
					+ admindailynachapaymentsfileobj.getNACHAID() + ".csv";
			Assert.assertTrue(TestUtil.verifyFileDownload(expectedFileName), "CSV download not found!");
			log.info("verifyDownloadCSVFile - Passed");
		}

	}

	/*-------Verify CSV file contents ---------*/
	@Test(description = "LP-3494 LoadPay Admin_Daily_NACHA_Payments_File", dependsOnMethods = {
			"verifyDownloadCSVFileTest" })
	public void verifyCsvContents() throws InterruptedException, InvalidFormatException, IOException {
		List<String[]> dataArray = TestUtil.getCsvContents(expectedFileName);
		log.info("verifyCsvContents - Passed");
	}
}
