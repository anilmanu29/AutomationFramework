package testcases;

  import base.TestBase;
  import org.apache.http.auth.UsernamePasswordCredentials;
  import org.testng.Assert;
  import org.testng.annotations.BeforeClass;
  import org.testng.annotations.Test;
  import pages.*;

  import java.awt.*;
  import java.io.IOException;
  import java.text.DateFormat;
  import java.text.SimpleDateFormat;
  import java.util.Calendar;
  import java.util.Date;
  import java.util.TimeZone;

public class LoginPageResetPasswordTest extends TestBase {
  BrokerPasswordSetupResetPage brokerPasswordSetupResetPage;
  BrokerLoginPage brokerLoginPage;
  ResetPassword resetPassword;
  BrokerOutlook brokerOutlook;
  outlooklogin outlookLogin;
  public static String emailid;
  Date currentTime;
  String formattedDate = "";
  Long longTime;
  DateFormat formatter;
  String currentHour = "";
  String currentMinutes = "";
  String timeArray[] = new String[2];


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
    brokerPasswordSetupResetPage = new BrokerPasswordSetupResetPage();
    currentTime = new Date();

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
    /////////////////////////////////////////////////////////////////
    TimeZone tz = Calendar.getInstance().getTimeZone();
    String currentTimeZone = tz.getDisplayName();
    System.out.println(currentTimeZone);

    formatter = new SimpleDateFormat("HH:mm");
    formatter.setTimeZone(TimeZone.getTimeZone("MST"));
    longTime = currentTime.getTime();
    formattedDate = formatter.format(longTime);
    timeArray = formattedDate.split(":");
    currentHour = timeArray[0];

    currentMinutes = timeArray[1];

    currentHour = Integer.toString(Math.abs(Integer.parseInt(currentHour)));
    currentMinutes =  Integer.toString(Math.abs(Integer.parseInt(currentMinutes)));

    System.out.println("\n\n\n===============================");
    System.out.println("Current date: " + longTime);
    System.out.println("Formatted date: " + formattedDate);
    System.out.println("Current Hour: " + currentHour);
    System.out.println("Current Minutes: " + currentMinutes);
    System.out.println("\n\n\n===============================");
    //////////////////////////////////////////////////////////////////

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
    brokerOutlook.outlookSearchInbox(EmailAddress, currentHour, currentMinutes);
    brokerOutlook.handleResetPasswordEmailInbox(EmailAddress);
    brokerPasswordSetupResetPage.enterNewPassword(NewPassword);
    brokerPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
    brokerPasswordSetupResetPage.clickSubmitButton();
    brokerLoginPage.brokerVerificationLogin(UserName, NewPassword);
    brokerLoginPage.verificationBrokerLogout();
  }
}






