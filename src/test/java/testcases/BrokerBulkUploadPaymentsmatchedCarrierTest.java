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
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import base.TestBase;

public class BrokerBulkUploadPaymentsmatchedCarrierTest extends TestBase 
{
	
	BrokerBulkUploadPaymentsUnmatchedCarrier bbup;
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
		bbup = new BrokerBulkUploadPaymentsUnmatchedCarrier();
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
	public void verifyBulkUploadPaymentsUnmatched() throws InterruptedException {
		bbup = new BrokerBulkUploadPaymentsUnmatchedCarrier();
		
		
	}
	
	@Test( priority = 3)
	public void verifynewPayment() throws InterruptedException {
		bbup.newPayment();
		Thread.sleep(1000);
	}
	

	@Test( priority = 4)
	public void verifyUploadFile() throws InterruptedException, IOException, AWTException {
		bbup.UploadFile();
		Thread.sleep(2000);
	}
	
		
	@Test(dependsOnMethods = { "verifyBulkUploadPaymentsUnmatched" }, priority = 5)
	public void verifyClickimport() throws InterruptedException, IOException {
		bbup.Clickimport();
		Thread.sleep(2000);
	}
	
	@Test( priority = 6)
	public void verifyClickschpayment() throws InterruptedException, IOException {
		bbup.Clickschpayment();
		Thread.sleep(1000);
	}
		
	@Test(priority = 7)
	public void verifyClickGridDown() throws InterruptedException, IOException {
		bbup.ClickGridDown();
		Thread.sleep(1000);
	}
	
	
	public void verifyBulkUploadPaymentsUnmatchedCarrierElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(bbup.lnk_newpayment.isDisplayed(), "newpayment link  not found");
		Assert.assertTrue(bbup.link_Upload.isDisplayed(), "upload link not found");
		Assert.assertTrue(bbup.btn_import.isDisplayed(),
				"import button not found");
		Assert.assertTrue(bbup.link_schpaymnt.isDisplayed(), "schedule payment tile not found");
		Assert.assertTrue(bbup.link_griddown.isDisplayed(), "grid Pulled down column not found");
		

	}
		
		
		
	

}
	

	
	
	
