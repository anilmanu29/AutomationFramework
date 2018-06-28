package testcases;

  import base.TestBase;
  import org.testng.Assert;
  import org.testng.annotations.BeforeClass;
  import org.testng.annotations.Test;
  import pages.BrokerLoginPage;
  import pages.BrokerOutlook;
  import pages.ResetPassword;
  import pages.outlooklogin;
  import java.awt.*;
  import java.io.IOException;

public class LoginPageResetPasswordTest extends TestBase {
  BrokerLoginPage brokerLoginPage;
  ResetPassword resetPassword;
  BrokerOutlook brokerOutlook;
  outlooklogin outlookLogin;
  public static String emailid;

  public LoginPageResetPasswordTest() {
    super();

  }

  @BeforeClass
  public void setUp() throws IOException, InterruptedException {
    initialization();
    brokerLoginPage = new BrokerLoginPage();
    resetPassword = new ResetPassword();
    outlookLogin = new outlooklogin();
    brokerOutlook = new BrokerOutlook();

  }

  @Test(description = "LP-5025 ", priority = 1)
  public void openBrokerLoginPage() throws InterruptedException {
    Thread.sleep(1000);
    brokerLoginPage.forgotPasswordButton();

  }

  @Test(dataProvider = "getBrokerForgotPassword", dependsOnMethods = "openBrokerLoginPage", priority = 2)
  public void proceedWithResetPassword(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword) throws InterruptedException {
    Thread.sleep(2000);
    resetPassword.enterUserName(UserName);
    Thread.sleep(1000);
    resetPassword.clickResetPassword();
    Assert.assertEquals(resetPassword.verificationPage(), "Thank you. An email has been sent.");

  }

  @Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "proceedWithResetPassword", priority = 3)
  public void login(String un, String pwd) throws InterruptedException, AWTException {
    outlookLogin.outlookLogin(un, pwd);
  }

  @Test(dataProvider = "getBrokerForgotPassword", dependsOnMethods = "login", priority = 4)
  public void outlookloginTest(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword) throws InterruptedException {
    brokerOutlook.clickPopUp();
    EmailAddress = EmailAddress.trim();
    brokerOutlook.clickOpenMailBox();
    brokerOutlook.enterEmail(super.prop.getProperty("email"));
    brokerOutlook.outlookSearchInbox(EmailAddress);
    brokerOutlook.handleResetPasswordEmailInbox(EmailAddress);
  }

}






