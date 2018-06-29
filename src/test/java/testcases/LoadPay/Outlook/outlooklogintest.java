package testcases.LoadPay.Outlook;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Outlook.outlooklogin;

public class outlooklogintest extends TestBase{
	
	outlooklogin loginPage;
		
		public outlooklogintest()
		{
			super();
		}
		
		@BeforeClass
		public void setUp() throws IOException
		{
			
			initialization();
			loginPage = new outlooklogin();	
			
		}

		@Test(dataProvider="getoutlookLoginData", priority=2)
		public void loginTest(String user,String pass) throws InterruptedException, AWTException
		{
			loginPage.outlookLogin(user, pass);
		
			Thread.sleep(5000);
			/*loginPage.BrokerLogout();*/
		}
		

}
