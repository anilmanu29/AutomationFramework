package testcases.loadpay.broker;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerAdvancePaymenttoUnmatchedCarrier;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;
import util.TestUtil;

public class BrokerAdvancePaymenttoUnmatchedCarrierTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	BrokerPaymentforUnmatchedCarrier brokerpaymentobj;
	BrokerAdvancePaymenttoUnmatchedCarrier brokeradvancepaymentobj;
	String invoicenumber = "";
	// int invoiceNum = 0;
	String dateTime;

	public static String unMatchedCarrierUsername;
	public static String unMatchedCarrierPassword;
	public static String loadID, invoiceNum = "";
	String brokerUsername, brokerPassword = "";

	/*-------Initializing driver---------*/
	public BrokerAdvancePaymenttoUnmatchedCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		brokerloginobj = new BrokerLoginPage();
		brokerpaymentobj = new BrokerPaymentforUnmatchedCarrier();
		brokeradvancepaymentobj = new BrokerAdvancePaymenttoUnmatchedCarrier();
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerloginobj = new BrokerLoginPage();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerloginobj.Brokerlogin(brokerUsername, brokerPassword);
		dateTime = TestUtil.getCurrentDateTime();
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

		brokerpaymentobj.newPayment();

		brokerpaymentobj.carrierEmail(unMatchedCarrierUsername);

		brokerpaymentobj.amount(amt);

		invoiceNum = brokerpaymentobj.invoiceNumber(invoiceNum);
		// in.add(invoiceNum);

		brokerpaymentobj.loadId(loadID);

		brokerpaymentobj.companyName(payto);
		brokeradvancepaymentobj.clickAdvancePaymentCheckbox();

		brokerpaymentobj.clickShedulePayment();

		// @Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods =
		// "loginBroker")
		// public void verifyBrokerPayment(String cemail, String invoiceno, String
		// loadid, String amt, String payto,
		// String ein) throws InterruptedException, InvalidFormatException, IOException
		// {
		// create a new payment

		// int randomNumber = TestUtil.getRandomNumber(1, 999999);
		// invoiceNum = randomNumber;
		// invoicenumber = Integer.toString(invoiceNum);
		// invoiceno = invoicenumber;
		// loadid = invoicenumber;
		//
		// brokerpaymentobj.newPayment();
		// brokerpaymentobj.setField_CarrierEmail(cemail);
		// brokerpaymentobj.setField_PayTo(payto);
		// brokerpaymentobj.setField_InvoiceNum(invoiceno);
		// brokerpaymentobj.setField_LoadID(loadid);
		// brokerpaymentobj.setField_PaymentAmount(amt);
		// brokeradvancepaymentobj.clickAdvancePaymentCheckbox();
		// brokeradvancepaymentobj.clickScheduleButton();
	}

	@Test(dependsOnMethods = "brokernewPayment")
	public void verifyAlertMessage() throws InterruptedException, InvalidFormatException, IOException {
		// verify alert message
		log.info(brokeradvancepaymentobj.alertMessage());
		Assert.assertEquals(brokeradvancepaymentobj.alertMessage(),
				"The advance payment you are trying to schedule to this Carrier cannot be completed at this time. "
						+ "The Carrier has not yet verified a bank account with LoadPay. "
						+ "Please let your Carrier know that without bank verification there is a risk of delay in payment.",
				"Error message is NOT displayed");
	}

}