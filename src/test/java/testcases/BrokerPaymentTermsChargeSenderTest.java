package testcases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerPaymentTermsChargeSender;

public class BrokerPaymentTermsChargeSenderTest extends TestBase 
{
	BrokerPaymentTermsChargeSender brokerPaymentTermsChargeSenderObj;
	public static String brokeremailid="";

	
	/*-------Initializing driver---------*/
	public BrokerPaymentTermsChargeSenderTest()
	{
		super();
		
	}
	
	@BeforeClass
	public void setUp() throws IOException
	{
		initialization();
		brokerPaymentTermsChargeSenderObj = new BrokerPaymentTermsChargeSender();
	}
	
	/*-------Login to Load Pay as Broker---------*/
	@Test(dataProvider="getBrokerLoginData", priority=1)
	public void loginAsBrokerTest(String un, String pwd)
	{
		brokerPaymentTermsChargeSenderObj.loginAsBroker(un, pwd);
		brokeremailid=un;
	}
	
	@Test(description="LP-5399 Broker Payment Terms Charge Sender", priority=2)
	public void verifyEnablePaymentTerms() throws InterruptedException
	{
		brokerPaymentTermsChargeSenderObj.activatePaymentTerms();
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.paymenttermscheckbox.isDisplayed(), "Enable Payment Term for less than 14 days check box NOT found");;
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.chargerecipientradiobutton.isDisplayed(), "Charge Recipient radio button NOT found");;
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.chargesenderradiobutton.isDisplayed(), "Charge Sender radio button NOT found");;
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.updatebutton.isDisplayed(), "Update button NOT found");
	}
	
	@Test(description="LP-5399 Broker Payment Terms Charge Sender", dataProvider="getAdminLoginData", priority=3)
	public void verifyAdminPaymentTermsEnable(String un, String pwd) throws AWTException, InterruptedException
	{
		brokerPaymentTermsChargeSenderObj.verifyPaymentTermsAdmin(un, pwd);
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminstatusvalue.getAttribute("value").contains("Enabled"),  "Status is Disabled");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminpercentageradiobutton.isDisplayed(),  "Percentage radio button NOT found in Admin");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminflatfeeradiobutton.isDisplayed(),  "Flat fee radio button NOT found in Admin");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminpaymenttermeditbutton.isDisplayed(), "Edit button NOT found");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminchargesenderfield.isDisplayed(), "Charge Sender field NOT found in Admin");
	}
	@Test(priority=4)
	public void verifyEditPercentagePaymentterms() throws InterruptedException
	{
		brokerPaymentTermsChargeSenderObj.editPaymentTermsPercentage();
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.numberAsString.equalsIgnoreCase(brokerPaymentTermsChargeSenderObj.brokerfee.getAttribute("value")));			
	}
	@Test(priority=5)
	public void verifyEditFlatFeePaymentterms() throws InterruptedException
	{
		brokerPaymentTermsChargeSenderObj.editPaymentTermsPercentageFlatFee();
		Thread.sleep(1000);
		brokerPaymentTermsChargeSenderObj.resetStatusFlatFeeValuse();	
	}
	
	@Test(dataProvider="getPaymentData", priority=6)
	public void verifybrokerNewPaymentforLessthanFourthteendays(String cE, String iN, String lId, String pA) throws InterruptedException
	{
		brokerPaymentTermsChargeSenderObj.brokerCreateNewPayment(cE, iN, lId, pA);
		brokerPaymentTermsChargeSenderObj.uncheckEnablePaymentTerms();			
	}

}
