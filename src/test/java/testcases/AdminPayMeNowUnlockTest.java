package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.io.IOException;

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

  @Test(dataProvider = "getBrokerLoginData", priority = 100)
  public void getBrokerCredentials(String user, String pass) throws InterruptedException {
    brokerUserName = user;
    brokerPassword = pass;
  }

  @Test(description = "LP-4683 AdminPayMeNowLockTest_verifyLockPayMeNowStatus", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials", priority = 101)
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

  @Test(description = "LP-4683 AdminPayMeNowLockTest_verifyBrokerCannotOptOutPayMeNow", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyLockPayMeNowStatus", priority = 102)
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
      System.out.println("Enroll in PayMeNow is Disabled");
    } else {
      System.out.println("Enroll in PayMeNow is Enabled");
    }
  Thread.sleep(2000);
    brokLoginPage.BrokerLogout();
  }
    @Test(description = "LP-6167 AdminUnlockPayMeNowTest", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyLockPayMeNowStatus", priority = 103)
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
      AdminPayMeNowUnlockTab.openPayMeNowTab();
      Thread.sleep(1000);
      AdminPayMeNowUnlockTab.clickUnLockPaymeNowStatusButton();
      Thread.sleep(1000);
      AdminPayMeNowUnlockTab.clickUpdateButton();
      Thread.sleep(1000);
      admLogin.AdminLogOut();
    }
  @Test(dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminUnlockPayMeNowStatus", priority = 104)
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
    System.out.println("User enrolled in PayMeNow");
    brokerPayMeNowTab.updateButton();

   /* if (!checkbox.isEnabled()) {
      brokerPayMeNowTab.enrollPayMeNow();
      System.out.println("Enroll in PayMeNow is Disabled");
    } else {
      System.out.println("Enroll in PayMeNow is Enabled");
    }*/
  }

}
