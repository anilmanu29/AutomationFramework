package testcases;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BrokerBulkUploadPaymentsUnmatchedCarrier;
import pages.BrokerBulkUploadPaymentsmatchedCarrier;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import base.TestBase;

public class BrokerBulkUploadPaymentsmatchedCarrierTest extends TestBase 
{
	
	BrokerBulkUploadPaymentsmatchedCarrier bbmp;
	BrokerLoginPage bl;
	String  payment_status = "Verified";
	 static String invoice;
	 public static ArrayList<String> al;
	 public static String email;
	 
	
	/*-------Initializing driver---------*/
	public BrokerBulkUploadPaymentsmatchedCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bbmp = new BrokerBulkUploadPaymentsmatchedCarrier();
		al = new ArrayList<String>();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=1)
	public void loginBroker(String un, String pwd) throws InterruptedException, IOException, AWTException
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	@Test(priority = 2)
	public void verifyBulkUploadPaymentsmatched() throws InterruptedException {
		bbmp = new BrokerBulkUploadPaymentsmatchedCarrier();
		
		
	}
	
	@Test( priority = 3)
	public void verifynewPayment() throws InterruptedException {
		bbmp.newPayment();
		Thread.sleep(1000);
	}
	

	@Test( priority = 4)
	public void verifyUploadFile() throws InterruptedException, IOException, AWTException {
		bbmp.UploadFile();
		Thread.sleep(2000);
	}
	
		
	@Test(dependsOnMethods = { "verifyBulkUploadPaymentsmatched" }, priority = 5)
	public void verifyClickimport() throws InterruptedException, IOException {
		bbmp.Clickimport();
		Thread.sleep(2000);
	}
	
	@Test( priority = 6)
	public void verifyClickschpayment() throws InterruptedException, IOException {
		bbmp.Clickschpayment();
		Thread.sleep(1000);
	}
		
	@Test(priority = 7)
	public void verifyClickGridDown() throws InterruptedException, IOException {
		bbmp.ClickGridDown();
		Thread.sleep(1000);
	}
	
	
	public void verifyBulkUploadPaymentsmatchedCarrierElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(bbmp.lnk_newpayment.isDisplayed(), "newpayment link  not found");
		Assert.assertTrue(bbmp.link_Upload.isDisplayed(), "upload link not found");
		Assert.assertTrue(bbmp.btn_import.isDisplayed(),
				"import button not found");
		Assert.assertTrue(bbmp.link_schpaymnt.isDisplayed(), "schedule payment tile not found");
		Assert.assertTrue(bbmp.link_griddown.isDisplayed(), "grid Pulled down column not found");
		

	}
		
		
		
	

}
	

	
	
	
