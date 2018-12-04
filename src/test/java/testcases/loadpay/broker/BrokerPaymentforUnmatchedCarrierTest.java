package testcases.loadpay.broker;

import java.time.LocalDate;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;
import util.TestUtil;

public class BrokerPaymentforUnmatchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier bp;
	BrokerLoginPage bl;
	String payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static ArrayList<String> in;

	public static String unMatchedCarrierUsername;
	public static String unMatchedCarrierPassword;
	public static String loadID, invoiceNum = "";
	String brokerUsername, brokerPassword = "";
	String dateTime;
	LocalDate today;
	Boolean newDateUsed = false;

	/*-------Initializing driver---------*/
	public BrokerPaymentforUnmatchedCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		bl = new BrokerLoginPage();
		bp = new BrokerPaymentforUnmatchedCarrier();
		al = new ArrayList<String>();
		in = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
	}

	@AfterClass
	public void cleanUp() {
		newDateUsed = false;
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String email, String pwd) {
		bl = new BrokerLoginPage();
		dateTime = TestUtil.getCurrentDateTime();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			today = LocalDate.now();
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = email;
			brokerPassword = pwd;
		}

		bl.Brokerlogin(brokerUsername, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String carrierEmail, String invoiceno, String loadid, String amt, String payto,
			String ein) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicUnmatchedData").contains("true")) {
			String[] emailArray = carrierEmail.split("@");
			emailArray[0] = emailArray[0] + dateTime;

			unMatchedCarrierUsername = emailArray[0] + "@" + emailArray[1];
			unMatchedCarrierPassword = "Password@123";
			invoiceNum = "UM" + TestUtil.getCurrentDateTime();
			loadID = invoiceNum;

		} else {
			unMatchedCarrierUsername = carrierEmail;
			unMatchedCarrierPassword = "Password@123";
		}

		bp.newPayment();

		bp.carrierEmail(unMatchedCarrierUsername);

		bp.amount(amt);

		invoiceNum = bp.invoiceNumber(invoiceNum);
		in.add(invoiceNum);

		bp.loadId(loadID);

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true") && !newDateUsed) {
//			Integer month = today.getMonthValue() + 1;
//			Integer day = today.getDayOfMonth();
//
//			if (day > 28)
//				day = 28;
//
//			String strDate = month.toString() + "/" + day.toString() + "/" + today.getYear();
//			bp.setField_ScheduleDate(strDate);
//			newDateUsed = true;
			
			
			Integer month = today.getMonthValue() < 12 ? (today.getMonthValue() + 1) : (today.getMonthValue() - 11);
			Integer year = today.getMonthValue() == 12 ? (today.getYear() + 1) : (today.getYear());
			
			Integer day = today.getDayOfMonth();
			log.info(month);
			log.info(year);
			log.info(day);

			// account for 28/30 day months
			if (day > 28)
				day = 28;

			String strDate = month.toString() + "/" + day.toString() + "/" + year.toString();
			System.out.println(strDate);
			bp.setField_ScheduleDate(strDate);
			newDateUsed = true;
			
			
		}

		bp.companyName(payto);

		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		umemail = bp.searchCarrier(unMatchedCarrierUsername);
		al.add(umemail);

		bp.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		bp.verifyInvoiceNumber(invoiceno, amt);

		einno = bp.getEin(ein);
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());

		// bp.logout();
	}

}
