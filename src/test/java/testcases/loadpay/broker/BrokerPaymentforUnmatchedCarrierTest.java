package testcases.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;

public class BrokerPaymentforUnmatchedCarrierTest extends TestBase 
{
	
	BrokerPaymentforUnmatchedCarrier bp;
	BrokerLoginPage bl;
	String  payment_status = "Unmatched";
	static String invoice;
	public static String umemail;
	public static String einno;
	public static ArrayList<String> al;
	public static ArrayList<String> in;
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
		in = new ArrayList<String>();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData")
	public void loginBroker(String un, String pwd)
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	
	@Test(dataProvider="getPaymentDataforUnmatchcarrier", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt, String payto, String ein) throws InterruptedException {
		
		bp.newPayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.carrierEmail(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.amount(amt);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		invoiceNum = bp.invoiceNumber(invoiceno);
		in.add(invoiceNum);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.loadId(loadid);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.companyName(payto);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickShedulePaymenttab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		umemail =	bp.searchCarrier(cemail);
		al.add(umemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		einno =  bp.getEin(ein);
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		
		//bp.logout();
	} 

}
