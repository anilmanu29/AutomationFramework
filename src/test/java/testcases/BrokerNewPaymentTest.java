package testcases;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import base.TestBase;

public class BrokerNewPaymentTest extends TestBase 
{
	
	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String  payment_status = "Verified";
	 static String invoice;
	 public static ArrayList<String> al;
	 public static String email;
	
	/*-------Initializing driver---------*/
	public BrokerNewPaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bp = new BrokerNewPayment();
		al = new ArrayList<String>();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=16)
	public void loginBroker(String un, String pwd)
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	
	@Test(dataProvider="getPaymentData", priority=17)
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		
		bp = new BrokerNewPayment();
		bp.newPayment();
		Thread.sleep(1000);
		email= bp.carrierEmail(cemail);
		Thread.sleep(1000);
		bp.amount(amt);
		Thread.sleep(1000);
		invoice = 	bp.invoiceNumber(invoiceno);
		al.add(invoice);
		Thread.sleep(1000);
		bp.loadId(loadid);
		Thread.sleep(1000);
		//bp.advanceCheckbox();
		//Thread.sleep(1000);
		bp.clickShedulePayment();
		Thread.sleep(1000);
		bp.clickShedulePaymenttab();
		Thread.sleep(1000);
		bp.searchCarrier(cemail);
		Thread.sleep(1000);
		bp.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 Thread.sleep(1000);
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(bp.verifyPaymentStatus());
		//bp.logout();
	}

}
