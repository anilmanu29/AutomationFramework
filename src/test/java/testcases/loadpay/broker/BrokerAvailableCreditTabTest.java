package testcases.loadpay.broker;
import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import org.testng.Assert;
import pages.loadpay.broker.BrokerAvailableCreditTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.outlook.outlooklogin;


public class BrokerAvailableCreditTabTest extends TestBase {

	BrokerAvailableCreditTab brokeravailablecredittab;
	BrokerLoginPage brokerloginpage; 
	BrokerOutlook brokeroutlook;
	outlooklogin outlooklog;
	
	
	
	/*-------Initializing driver---------*/
	
	public BrokerAvailableCreditTabTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp() throws IOException
	{
		
		initialization();
		brokeravailablecredittab = new BrokerAvailableCreditTab();
		brokerloginpage = new BrokerLoginPage();
		brokeroutlook = new BrokerOutlook();
		outlooklog = new outlooklogin();
		
		
	}
	
	
	@Test(description = "LP-5398 Broker Request Additional Credit Login", dataProvider="getBrokerLoginData")
	public void loginBrokerTest(String un, String pwd) throws InterruptedException
	{
		brokerloginpage.Brokerlogin(un, pwd);
					
	}
	
	@Test(description = "LP-5398 Broker Request Additional Credit Credit Tab", dependsOnMethods = "loginBrokerTest")
	public void ClickBrokerAvailableCreditTabTest() throws InterruptedException
	{
		brokeravailablecredittab.clickAvailableCreditTab();
	}
	@Test(description = "LP-5398 Broker Request Additional Credit Click Credit Button", dependsOnMethods = "ClickBrokerAvailableCreditTabTest")
	public void RequestAdditionalCreditButtonTest() throws InterruptedException
	{
		brokeravailablecredittab.clickRequestAdditionalCreditButton();
	}
	@Test(description = "LP-5398 Broker Request Additional Credit Pop Up", dependsOnMethods = "RequestAdditionalCreditButtonTest")
	public void RequestAdditionalCreditPopUpTest() throws InterruptedException
	{
		brokeravailablecredittab.clickCloseButton();
		Thread.sleep(2000);
		brokerloginpage.BrokerLogout();
	}
	
	@Test(description = "LP-5398 Broker Request Additional Credit Outlook Login", dataProvider = "getoutlookLoginData", dependsOnMethods = "RequestAdditionalCreditPopUpTest")
	public void BrokerOutlookTest(String un, String pwd) throws InterruptedException
	{
		
		try {
			outlooklog.outlookLogin(un, pwd);
			brokeroutlook.clickPopUp();
			brokeroutlook.clickOpenMailBox();
			brokeroutlook.enterEmail(super.getProperties().getProperty("email"));
			Assert.assertTrue(SearchInbox("Credit increase request"));
			brokeroutlook.quit();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public Boolean SearchInbox(String SearchText) throws InterruptedException
	{
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(6000);
		
		
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for(WebElement e : list)
		{
			Thread.sleep(2000);
			e.click();
			Thread.sleep(1000);
			//log.info(e.getText());
			if(e.getText().contains(SearchText))
			{
				return true;
			}
			
		}
		
		return false;
	}
}



