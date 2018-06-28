package testcases;

import base.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BrokerEmailLoginUsersPage;
import pages.BrokerLoginPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class AccountChangePasswordTest extends TestBase {
  WebDriverWait wait;
  BrokerLoginPage brokerLoginPage;
  BrokerEmailLoginUsersPage brokEmailLogUsePage;


  public AccountChangePasswordTest() {
    super();
  }

  @BeforeClass
  public void setUp() throws IOException {

    initialization();
    brokerLoginPage = new BrokerLoginPage();
    brokEmailLogUsePage = new BrokerEmailLoginUsersPage();

  }

  @Test(dataProvider = "getBrokerLoginData", priority = 1)
  public void loginTest(String user, String pass) throws InterruptedException {
    wait = new WebDriverWait(driver, 30);
    brokerLoginPage.Brokerlogin(user, pass);

  }

  @Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "loginTest", priority = 2)
  public void changePasswordVerification(String Username, String CurrentPassword, String NewPassword, String ConfirmNewPassword) throws
    InterruptedException {
    System.out.println(Username);
    System.out.println(CurrentPassword);
    System.out.println(NewPassword);
    brokEmailLogUsePage.openAccountTab();
    Thread.sleep(1000);
    brokEmailLogUsePage.goToEmailLoginUsers();
    brokEmailLogUsePage.openPasswordAccountSecurityLink();
    Thread.sleep(2000);
    brokEmailLogUsePage.clickChangePasswordButton();
    Thread.sleep(2000);
    brokEmailLogUsePage.clicCurrentPasswordField();
    brokEmailLogUsePage.enterCurrentPassword(CurrentPassword);
    brokEmailLogUsePage.clickNewPasswordField();
    brokEmailLogUsePage.enterNewPassword(NewPassword);
    brokEmailLogUsePage.clickConfirmNewPasswordField();
    brokEmailLogUsePage.enterConfirmNewPasswordField(ConfirmNewPassword);
    brokEmailLogUsePage.clickUpdateButton();
    assertEquals(brokEmailLogUsePage.verificationMessage(), "Saved", "Password isn't saved");
    //assertEquals(brokEmailLogUsePage.lastFourPasswords(), "Your new Password cannot be any of your last four Passwords.", "Your new Password cannot be any of your last four Passwords");
      /* if(brokEmailLogUsePage.lastFourPasswords()==true) {
         brokerLoginPage.BrokerLogout();
       } else {

       }*/

    Thread.sleep(3000);
    brokerLoginPage.BrokerLogout();
    Thread.sleep(2000);
  }

  @Test(dataProvider = "getBrokerChangePasswordData", dependsOnMethods = "changePasswordVerification", priority = 3)
  public void loginVerificationTest(String UserName, String CurrentPassword, String NewPassword, String ConfirmNewPassword) throws InterruptedException {
    wait = new WebDriverWait(driver, 30);
    brokerLoginPage.brokerVerificationLogin(UserName, NewPassword);
    Thread.sleep(2000);
    assertEquals(brokEmailLogUsePage.logOffButton(), true, "User is unable to login with New Password");
  }
}
