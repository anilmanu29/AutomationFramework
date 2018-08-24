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
import pages.loadpay.broker.BrokerNewPayment;
import util.TestUtil;

public class BrokerAdvancePaymenttoUnmatchedCarrierTest extends TestBase {

	BrokerLoginPage brokerloginobj;
	BrokerNewPayment brokerpaymentobj;
	BrokerAdvancePaymenttoUnmatchedCarrier brokeradvancepaymentobj;
	String invoicenumber = "";
	int invoiceNum = 0;

	/*-------Initializing driver---------*/
	public BrokerAdvancePaymenttoUnmatchedCarrierTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		brokerloginobj = new BrokerLoginPage();
		brokerpaymentobj = new BrokerNewPayment();
		brokeradvancepaymentobj = new BrokerAdvancePaymenttoUnmatchedCarrier();
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerloginobj = new BrokerLoginPage();
		brokerloginobj.Brokerlogin(un, pwd);
	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void verifyBrokerPayment(String cemail, String invoiceno, String loadid, String amt, String payto,
			String ein) throws InterruptedException, InvalidFormatException, IOException {
		// create a new payment

		int randomNumber = TestUtil.getRandomNumber(1, 999999);
		invoiceNum = randomNumber;
		invoicenumber = Integer.toString(invoiceNum);
		invoiceno = invoicenumber;
		loadid = invoicenumber;

		brokerpaymentobj.newPayment();
		brokerpaymentobj.setField_CarrierEmail(cemail);
		brokerpaymentobj.setField_PayTo(payto);
		brokerpaymentobj.setField_InvoiceNum(invoiceno);
		brokerpaymentobj.setField_LoadID(loadid);
		brokerpaymentobj.setField_PaymentAmount(amt);
		brokeradvancepaymentobj.clickAdvancePaymentCheckbox();
		brokeradvancepaymentobj.clickScheduleButton();
	}

	@Test(dependsOnMethods = "verifyBrokerPayment")
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