package testcases;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import pages.BrokerPaymentforUnmatchedCarrier;

public class BrokerPaymentforUnmatchedCarrierTest extends TestBase 
{
	
	BrokerPaymentforUnmatchedCarrier bp;
	BrokerLoginPage bl;
	String  payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static String invoiceNum;
	
	/*-------Initializing driver---------*/
	public BrokerPaymentforUnmatchedCarrierTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bp = new BrokerPaymentforUnmatchedCarrier();
		al = new ArrayList<String>();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=22)
	public void loginBroker(String un, String pwd)
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	
	@Test(dataProvider="getPaymentDataforUnmatchcarrier", priority=23)
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt, String payto, String ein) throws InterruptedException {
		
		bp.newPayment();
		Thread.sleep(1000);
		bp.carrierEmail(cemail);
		Thread.sleep(1000);
		bp.amount(amt);
		Thread.sleep(1000);
		invoiceNum = bp.invoiceNumber(invoiceno);
		al.add(invoiceNum);
		Thread.sleep(1000);
		bp.loadId(loadid);
		Thread.sleep(1000);
		bp.companyName(payto);
		Thread.sleep(1000);
		bp.clickShedulePayment();
		Thread.sleep(1000);
		bp.clickShedulePaymenttab();
		Thread.sleep(1000);
		umemail =	bp.searchCarrier(cemail);
		al.add(umemail);
		Thread.sleep(1000);
		bp.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 Thread.sleep(1000);
		einno =  bp.getEin(ein);
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(bp.verifyPaymentStatus());
		
		//bp.logout();
	} 

}
