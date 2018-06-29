package testcases.LoadPay.Admin;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Admin.AdminHomePage;
import pages.LoadPay.Admin.AdminLogin;
import pages.LoadPay.Admin.AdminPaymeNowTab;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Broker.BrokerPayMeNowTab;

public class AdminPayMeNowLockTest extends TestBase {
  AdminHomePage admHomePage;
  AdminLogin admLogin;
  AdminPaymeNowTab admPayMeNowTab;
  BrokerLoginPage brokLoginPage;
  BrokerPayMeNowTab brokerPayMeNowTab;
  WebElement checkbox;
  String brokerUserName;
  String brokerPassword;
  

  public AdminPayMeNowLockTest() {
    super();
   
  }
  @BeforeClass
  public void setUp() throws IOException {

    initialization();
    admLogin = new AdminLogin();
    admHomePage = new AdminHomePage();
    admPayMeNowTab = new AdminPaymeNowTab();
    brokerPayMeNowTab = new BrokerPayMeNowTab(); 
    brokLoginPage = new BrokerLoginPage();
     
  }
  @Test(dataProvider="getBrokerLoginData", priority=56)
  public void getBrokerCredentials(String user,String pass) throws InterruptedException
  {    
         brokerUserName = user;
         brokerPassword = pass;
  }

  @Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods="getBrokerCredentials", priority = 57)
  public void verifyLockPayMeNowStatus(String Username, String pass) throws InterruptedException, AWTException, IOException, InvalidFormatException {
    admHomePage.AdminURL();
    Thread.sleep(1000);
    admLogin.adminUserPass(Username, pass);
    Thread.sleep(2000);
    admLogin.adminLogin();
    Thread.sleep(100);
    admLogin.ClickOnCustomersTab();
    Thread.sleep(1000);
    admLogin.ClickOnSearchBox(brokerUserName);
    Thread.sleep(2000);
    admLogin.ClickOnSearchButton();
    Thread.sleep(1000);
    admLogin.DoubleClickID();
    Thread.sleep(2000);
    admPayMeNowTab.openPayMeNowTab();
    Thread.sleep(2000);
    admPayMeNowTab.clickEnrollInPayMeNow();
    Thread.sleep(1000);
    admPayMeNowTab.clickLockPaymeNowStatusButton();
    Thread.sleep(1000);
    admPayMeNowTab.clickUpdateButton();
    Thread.sleep(2000);
    admLogin.AdminLogOut();
    
  }
  

@Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dataProvider = "getBrokerLoginData",dependsOnMethods="verifyLockPayMeNowStatus", priority=58)
  public void verifyBrokerCannotOptOutPayMeNow(String un, String pwd) throws InterruptedException, AWTException {
	 Thread.sleep(2000);
	 driver.get(super.prop.getProperty("url"));
	 brokLoginPage.Brokerlogin(un, pwd);
     Thread.sleep(5000);
    brokerPayMeNowTab.openAccountTab();
     Thread.sleep(2000);
    brokerPayMeNowTab.openPayMeNowTab();
    Thread.sleep(2000);
    checkbox = driver.findElement(By.id("PMNEnrolled"));
    if (!checkbox.isEnabled()) {
      System.out.println("Enroll in PayMeNow is Disabled");
    } else {
      System.out.println("Enroll in PayMeNow is Enabled");
    }

  }
}
