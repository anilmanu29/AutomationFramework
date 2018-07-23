package testcases.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;

public class ShipperSchedulePaymentTest extends TestBase 
{
	
	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String  payment_status = "Verified";
	 static String invoice;
	
	/*-------Initializing driver---------*/
	public ShipperSchedulePaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bp = new BrokerNewPayment();
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
	
	@Test(dataProvider="getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		
		bp = new BrokerNewPayment();
		bp.newPayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.carrierEmail(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.amount(amt);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		invoice = 	bp.invoiceNumber(invoiceno);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.loadId(loadid);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		//bp.advanceCheckbox();
		//wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickShedulePayment();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickShedulePaymenttab();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.searchCarrier(cemail);
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		bp.clickSearchButton();
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		//bp.logout();
	}

}
