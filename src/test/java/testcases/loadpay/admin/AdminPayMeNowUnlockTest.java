package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayMeNowUnlockTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPayMeNowTab;

public class AdminPayMeNowUnlockTest extends TestBase {
  AdminHomePage admHomePage;
  AdminLogin admLogin;
  AdminPayMeNowUnlockTab admPayMeNowUnlockTab;
  BrokerLoginPage brokLoginPage;
  BrokerPayMeNowTab brokerPayMeNowTab;
  WebElement checkbox;
  String brokerUserName;
  String brokerPassword;


  public AdminPayMeNowUnlockTest() {
    super();

  }

  @BeforeClass
  public void setUp() throws IOException {

    initialization();
    admLogin = new AdminLogin();
    admHomePage = new AdminHomePage();
    brokerPayMeNowTab = new BrokerPayMeNowTab();
    brokLoginPage = new BrokerLoginPage();
    admPayMeNowUnlockTab = new AdminPayMeNowUnlockTab();

  }

  @Test(dataProvider = "getBrokerLoginData")
  public void getBrokerCredentials(String user, String pass) throws InterruptedException {
    brokerUserName = user;
    brokerPassword = pass;
  }

  @Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
  public void verifyLockPayMeNowStatus(String Username, String pass) throws InterruptedException, AWTException {
    admHomePage.AdminURL();
    Thread.sleep(1000);
    admLogin.adminUserPass(Username, pass);
    Thread.sleep(2000);
    admLogin.adminLogin();
    Thread.sleep(100);
    admLogin.ClickOnCustomersTab();
    Thread.sleep(1000);
    admLogin.ClickOnSearchBox(brokerUserName);
    Thread.sleep(1000);
    admLogin.ClickOnSearchButton();
    Thread.sleep(1000);
    admLogin.DoubleClickID();
    Thread.sleep(1000);
    admPayMeNowUnlockTab.openPayMeNowTab();
    Thread.sleep(1000);
    admPayMeNowUnlockTab.clickEnrollInPayMeNow();
    Thread.sleep(1000);
    admPayMeNowUnlockTab.clickLockPaymeNowStatusButton();
    Thread.sleep(1000);
    admPayMeNowUnlockTab.clickUpdateButton();
    Thread.sleep(2000);
    admLogin.AdminLogOut();

  }

  @Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyLockPayMeNowStatus")
  public void verifyBrokerCannotOptOutPayMeNow(String un, String pwd) throws InterruptedException, AWTException {
    Thread.sleep(2000);
    driver.get(super.prop.getProperty("url"));
    brokLoginPage.Brokerlogin(un, pwd);
    Thread.sleep(1000);
    brokerPayMeNowTab.openAccountTab();
    Thread.sleep(1000);
    brokerPayMeNowTab.openPayMeNowTab();
    Thread.sleep(1000);
    checkbox = driver.findElement(By.id("PMNEnrolled"));
    if (!checkbox.isEnabled()) {
      log.info("Enroll in PayMeNow is Disabled");
    } else {
      log.info("Enroll in PayMeNow is Enabled");
    }
  Thread.sleep(2000);
    brokLoginPage.BrokerLogout();
  }
    @Test(description = "LP-6167 AdminUnlockPayMeNowTest", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyBrokerCannotOptOutPayMeNow")
    public void verifyAdminUnlockPayMeNowStatus (String Username, String pass) throws InterruptedException, AWTException {
      admHomePage.AdminURL();
      Thread.sleep(1000);
      admLogin.adminUserPass(Username, pass);
      Thread.sleep(1000);
      admLogin.adminLogin();
      Thread.sleep(100);
      admLogin.ClickOnCustomersTab();
      Thread.sleep(1000);
      admLogin.ClickOnSearchBox(brokerUserName);
      Thread.sleep(1000);
      admLogin.ClickOnSearchButton();
      Thread.sleep(1000);
      admLogin.DoubleClickID();
      Thread.sleep(1000);
      admPayMeNowUnlockTab.openPayMeNowTab();
      Thread.sleep(1000);
      admPayMeNowUnlockTab.clickUnLockPaymeNowStatusButton();
      Thread.sleep(1000);
      admPayMeNowUnlockTab.clickUpdateButton();
      Thread.sleep(1000);
      admLogin.AdminLogOut();
    }
  @Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminUnlockPayMeNowStatus")
  public void verifyBrokerCanEnrollInPayMeNow(String un, String pwd) throws InterruptedException, AWTException {
    Thread.sleep(2000);
    driver.get(super.prop.getProperty("url"));
    brokLoginPage.Brokerlogin(un, pwd);
    Thread.sleep(2000);
    brokerPayMeNowTab.openAccountTab();
    Thread.sleep(1000);
    brokerPayMeNowTab.openPayMeNowTab();
    Thread.sleep(1000);
    checkbox = driver.findElement(By.id("PMNEnrolled"));

    brokerPayMeNowTab.enrollPayMeNow();
    Thread.sleep(2000);
    log.info("User enrolled in PayMeNow");
    brokerPayMeNowTab.updateButton();

   /* if (!checkbox.isEnabled()) {
      brokerPayMeNowTab.enrollPayMeNow();
      log.info("Enroll in PayMeNow is Disabled");
    } else {
      log.info("Enroll in PayMeNow is Enabled");
    }*/
  }

}
