package testcases.loadpay.carrier;

  import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminEditEmailCarrier;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierOutlook;
import pages.loadpay.carrier.CarrierPasswordSetupResetPage;
import pages.loadpay.carrier.CarrierRegisterPage;
import pages.loadpay.carrier.ResetPassword;
import pages.loadpay.outlook.outlooklogin;

public class CarrierLoginPageResetPasswordTest extends TestBase {
  CarrierPasswordSetupResetPage CarrierPasswordSetupResetPage;
  CarrierLoginPage carrierLoginPage;
  public static Properties prop;
  ResetPassword resetPassword;
  AdminLogin adminlogin;
  AdminHomePage adminHomePage;
  AdminLogin adminLoginPage;
  AdminEditEmailCarrier adminEmailPage;
  CarrierOutlook carrierOutlook;
  outlooklogin outlookLogin;
  outlooklogin outlookLoginObj;
	CarrierLoginPage carrierLoginObj;
	CarrierOutlook carrierOutlookObj;
	CarrierRegisterPage carrierRegisterObj;
  public static String emailid;
  Date currentTime;
  String formattedDate = "";
  Long longTime;
  DateFormat formatter;
  String currentHour = "";
  String currentMinutes = "";
  String timeArray[] = new String[2];
  String originalCarrierEmailAddress = "";
  String updatedCarrierEmailAddress = "";
	String originalCarrierPassword = "";
	String updatedCarrierPassword = "";
	String adminUN = "";
	String adminPW = "";
	
	


  public CarrierLoginPageResetPasswordTest() {
    super();

  }

  @BeforeClass
  public void setUp() throws IOException, InterruptedException {
    initialization();
	adminHomePage = new AdminHomePage();	
	adminLoginPage = new AdminLogin();
	adminEmailPage = new AdminEditEmailCarrier();
	carrierLoginPage = new CarrierLoginPage();
    resetPassword = new ResetPassword();
    outlookLogin = new outlooklogin();
   carrierOutlook = new CarrierOutlook();
    CarrierPasswordSetupResetPage = new CarrierPasswordSetupResetPage();
    currentTime = new Date();
    adminlogin = new AdminLogin();
    
    

  }

  @Test(description = "LP-5024 ")
  public void openCarrierLoginPage() throws InterruptedException {
    Thread.sleep(1000);
    carrierLoginPage.forgotPasswordButton();

  }

  @Test(dataProvider = "getCarrierForgotPasswordData", dependsOnMethods = {"openCarrierLoginPage"})
  public void proceedWithResetPassword(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword) throws InterruptedException {
    Thread.sleep(2000);
    resetPassword.enterUserName(UserName);
    Thread.sleep(1000);
    resetPassword.clickResetPassword();
    Assert.assertEquals(resetPassword.verificationPage(), "Thank you. An email has been sent.");

	/////////////////////////////////////////////////////////////////
	TimeZone tz = Calendar.getInstance().getTimeZone();
	String currentTimeZone = tz.getDisplayName();
	log.info(currentTimeZone);
	
	formatter = new SimpleDateFormat("HH:mm");
	formatter.setTimeZone(TimeZone.getTimeZone("MST"));
	longTime = currentTime.getTime();
	formattedDate = formatter.format(longTime);
	timeArray = formattedDate.split(":");
	currentHour = timeArray[0];
	currentMinutes = timeArray[1];
	
	log.info("\n\n\n===============================");
	log.info("Current date: " + longTime);
	log.info("Formatted date: " + formattedDate);
	log.info("Current Hour: " + currentHour);
	log.info("Current Minutes: " + currentMinutes);
	log.info("\n\n\n===============================");
	//////////////////////////////////////////////////////////////////

  }

  @Test(dataProvider = "getoutlookLoginData", dependsOnMethods = {"proceedWithResetPassword"})
  public void login(String un, String pwd) throws InterruptedException, AWTException {
    outlookLogin.outlookLogin(un, pwd);
  }

  @Test(dataProvider = "getCarrierForgotPasswordData", dependsOnMethods = {"login"})
  public void outlookloginTest(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword) throws InterruptedException {
	  carrierOutlook.clickPopUp();
    EmailAddress = EmailAddress.trim();
    carrierOutlook.clickOpenMailBox();
    carrierOutlook.enterEmail(super.prop.getProperty("email"));
    carrierOutlook.outlookSearchInbox(EmailAddress, currentHour, currentMinutes);
    carrierOutlook.handleResetPasswordEmailInbox(EmailAddress);
    CarrierPasswordSetupResetPage.enterNewPassword(NewPassword);
    CarrierPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
    CarrierPasswordSetupResetPage.clickSubmitButton();
    carrierLoginPage.carrierVerificationLogin(UserName, NewPassword);
    //CarrierLoginPage.verificationCarrierLogout();
  }
  
  
  @Test(dataProvider="getAdminLoginData",dependsOnMethods = {"outlookloginTest"})
  public void ResetPasswordEmail(String Username,String pass) throws InterruptedException, AWTException
	{
		//search-for and reset the updated email address to the original email address
	  adminHomePage.AdminURL();
		Thread.sleep(1000);
		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);
		Thread.sleep(1000);
		adminLoginPage.adminLogin();
		Thread.sleep(1000);
		adminLoginPage.ClickOnCustomersTab();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.CustomerTab.isDisplayed());
		log.info(carrierLoginPage.cemail);
		adminlogin.ClickOnSearchBox(carrierLoginPage.cemail);
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());
		adminlogin.DoubleClickID();
		Thread.sleep(6000);
		adminlogin.clickeditloginuser();
		Thread.sleep(6000);
		adminlogin.click_AdminResetPassword();
		Thread.sleep(6000);
		adminlogin.clickAdmin_ResetpwdConfirm();
		Thread.sleep(6000);
		}
  
  @Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "ResetPasswordEmail")
  public void outlogin(String un, String pwd) throws InterruptedException, AWTException {
   // outlookLogin.outlookLogin(un, pwd);
	  driver.get(super.prop.getProperty("outlookurl"));
  }
  
  @Test(dataProvider = "getAdminforcepasswordData", dependsOnMethods = {"outlogin"})
  public void AdminforcepasswordData(String UserName, String EmailAddress, String NewPassword, String ConfirmPassword) throws InterruptedException {
	  carrierOutlook.clickPopUp();
    EmailAddress = EmailAddress.trim();
    carrierOutlook.clickOpenMailBox();
    carrierOutlook.enterEmail(super.prop.getProperty("email"));
    carrierOutlook.outlookSearchInboxforcareer(EmailAddress, currentHour, currentMinutes);
    carrierOutlook.handleResetPasswordEmailInbox(EmailAddress);
    Thread.sleep(2000);
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(4));
	Thread.sleep(2000);
    CarrierPasswordSetupResetPage.enterNewPassword(NewPassword);
    CarrierPasswordSetupResetPage.confirmNewPassword(ConfirmPassword);
    CarrierPasswordSetupResetPage.clickSubmitButton();
    Thread.sleep(2000);
    carrierLoginPage.verificationCarrierLogout();
    carrierLoginPage.carrierVerificationLogin(UserName, NewPassword);
    carrierLoginPage.verificationCarrierLogout();
    Thread.sleep(2000);
    
    
  }
  
  
  
}






