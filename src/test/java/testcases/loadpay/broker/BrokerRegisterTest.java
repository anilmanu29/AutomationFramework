package testcases.loadpay.broker;
import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerRegister;
import pages.loadpay.outlook.outlooklogin;


public class BrokerRegisterTest extends TestBase
{
  BrokerRegister r;
  AdminHomePage adminHomePage;
  AdminLogin adminLoginPage;
  BrokerLoginPage loginPage;
  BrokerOutlook brokerOutlookObj;
  outlooklogin outlookLoginObj;
  
  Select type;
  Select stateof;
  Select payment;
  Select state;
  Select country;
  Select Payments;
  WebElement PaymentTerms;
 
  public static String emailid;
  
  String brokerUsername;
  String brokerPassword;
  String outlookUsername;
  String outlookPassword;
  String EIN = "99-9999999";
  String depositAmount = "";
  Date currentTime;
  String formattedDate = "";
  Long longTime;
  DateFormat formatter;
  String currentHour = "";
  String currentMinutes = "";
  String timeArray[] = new String[2];
	
	public BrokerRegisterTest()
	{
		super();
	}
  
  @BeforeClass
	public void setUp() throws IOException
	{
		
		initialization();
		r = new BrokerRegister();	
		adminHomePage = new AdminHomePage();	
		adminLoginPage = new AdminLogin();
		loginPage = new BrokerLoginPage();
		outlookLoginObj = new outlooklogin();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
	}

  @Test(dataProvider = "getBrokerRegisterData")
  public void BrokerRegister( String Dotnumber, String CompanyName, String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
      String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
      String ConfirmbankAccountNumber ) throws IOException, InterruptedException
  {
	  
	   brokerUsername = Email;
	   brokerPassword = Password;
	  
	   r.signup();
	  
	   // clicking on carrier Register
	   r.shipperRegister();  
	   r.companyname( CompanyName );
	   Thread.sleep(1000);
	   r.doingbussiness( DoingBussinessAS );
	   r.selectType();
	   Thread.sleep(1000);
	  
	   Select type = new Select( driver.findElement( By.xpath( ".//*[@id='EntityType']" ) ) );
	   type.selectByVisibleText( "C Corporation" );
	  
	   r.countryofincorporation();
	   Thread.sleep(1000);
		
	   Select countryof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationCountry']" ) ) );
	   countryof.selectByIndex( 0 );
	
	   r.stateofincorporation();
	   Thread.sleep(1000);
	
	   Select stateof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationState']" ) ) );
	   stateof.selectByVisibleText( "California" );
	   
	   emailid = r.BrokerEmail( Email );
	 
	   r.confirmEmail( ConfirmEmail );
	   Thread.sleep(1000);
	   
	   r.iCertifyClick();
	   Thread.sleep(1000);
	   
	   r.paymentTerm();
	   Thread.sleep(1000);
	   
	   r.next();	
	   Thread.sleep(1000);
		
	   r.ZipCode( ZipCode1 );
	   Thread.sleep(1000);
		
	   r.country();
	   Thread.sleep(1000);
		
	   Select country = new Select( driver.findElement( By.xpath( ".//*[@id='OriginCountry']" ) ) );
	   country.selectByVisibleText( "USA" );
	
	   r.address( Address );
	   Thread.sleep(1000);
	
	   r.city( City );
	
	   r.State();
	   Thread.sleep(1000);
	
	   Select state = new Select( driver.findElement( By.xpath( ".//*[@id='State']" ) ) );	
	   state.selectByVisibleText( "CA" );
	
	   Thread.sleep(1000);
	
	   r.submit();
	   Thread.sleep(1000);
	
	   r.ContactFirstName(FirstNames);
	   Thread.sleep(1000);
	
	   r.LastName(LastName);
	   r.Phone(PhoneNumber);
	   Thread.sleep(1000);
	
	   r.Password( Password );
	   Thread.sleep(1000);
	
	   driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
	   r.ConfirmPassword(ConfirmPassword);
	   Thread.sleep(1000);
	  	
	   r.Next();
	   Thread.sleep(1000);
	  	
	   r.AccountName(NameonAccount);
	   r.BankingAccount(BankAccountNumber);
	   Thread.sleep(1000);
	   r.BankingRouting(RoutingNumber);
	   Thread.sleep(2000);
	   r.ConfirmBankingAccount(ConfirmbankAccountNumber);
	   Thread.sleep(2000);
	   
	   r.submit();
	   log.info(" Broker Register Completed...");  
  }
  
  @Test(description = "Capture Outlook Login creds", dataProvider="getoutlookLoginData", dependsOnMethods = "BrokerRegister")
  public void getOutlookLoginCredentials(String user, String pass) throws InterruptedException, AWTException
  {
	outlookUsername = user;
	outlookPassword = pass;
  }
  

  @Test(description = "Complete Broker Registration after first login", dataProvider="getAdminLoginData", dependsOnMethods = "getOutlookLoginCredentials")
	public void loginAndVerifyNewBrokerAccount(String adminUser,String adminPass) throws InterruptedException, AWTException
	{
		adminHomePage.AdminURL();
		adminLoginPage.adminUserPass(adminUser, adminPass);
		Thread.sleep(1000);
		adminLoginPage.adminLogin();
		Thread.sleep(1000);
		adminLoginPage.ClickOnCustomersTab();
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchBox(brokerUsername);
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchButton();
		Thread.sleep(1000);
		adminLoginPage.DoubleClickID();
		Thread.sleep(1000);
		adminLoginPage.StatusIDDropDown();
		Thread.sleep(1000);
		adminLoginPage.UpdateButton();
		Thread.sleep(1000);
		
		//add credit
		WebElement adminCustomerCreditTab = driver.findElement(By.xpath("//a[contains(text(),'Credit')]"));
		adminCustomerCreditTab.click();
		Thread.sleep(1000);
		
		WebElement extendedCreditField = driver.findElement(By.id("ExtendedCredit"));
		extendedCreditField.clear();
		extendedCreditField.sendKeys("100000");
		Thread.sleep(1000);
		
		WebElement updateCreditButton = driver.findElement(By.xpath("//*[@id=\"formCredit\"]/div[3]/input"));
		updateCreditButton.click();
		Thread.sleep(1000);
		
		WebElement availableCreditLabel = driver.findElement(By.xpath("//*[@id=\"formCredit\"]/div[2]/label"));
		Assert.assertTrue(availableCreditLabel.getText().contains("$100,000.00"), "Available credit amount not found!");
		Thread.sleep(1000);
		
		//go to banking tab and capture deposit amount
		WebElement adminCustomerBankingTab = driver.findElement(By.xpath("//a[contains(text(),'Banking')]"));
		adminCustomerBankingTab.click();
		Thread.sleep(1000);
		
		WebElement adminCustomerDepositAmount = driver.findElement(
				By.xpath("//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span"));
		depositAmount = adminCustomerDepositAmount.getText();
		depositAmount = depositAmount.substring(depositAmount.length()-2, depositAmount.length());
		depositAmount = "0" + depositAmount;
		log.info("Captured deposit amount: "  + depositAmount);
		Thread.sleep(5000);
		
		//verify in outlook
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
		
		outlookLoginObj.outlookLogin(outlookUsername, outlookPassword);
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("email"));
		brokerOutlookObj.outlookSearchInbox(brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.handleNewInbox();
		Thread.sleep(5000);
		//////////////////////////////////////////////////////////////////
		
		//log in as broker
		driver.get(super.prop.getProperty("url"));
		loginPage.Brokerlogin(brokerUsername, brokerPassword);
		Thread.sleep(1000);
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		
		//enter EIN
		WebElement einInputField = driver.findElement(By.xpath("//*[@id='EIN']"));
		einInputField.clear();
		einInputField.sendKeys(EIN);
		Thread.sleep(1000);
		
		//enter deposit amount
		WebElement depositAmtField = driver.findElement(By.xpath("//*[@id='ControlAmount']"));
		depositAmtField.clear();
		depositAmtField.sendKeys(depositAmount);
		Thread.sleep(1000);
		
		//click Next
		WebElement nextButton = driver.findElement(By.xpath("//*[@id='formCompany']/input"));
		nextButton.click();
		Thread.sleep(1000);
		
		//accept terms and conditions
		WebElement acceptTermsCheckBox = driver.findElement(By.xpath("//*[@id='AcceptedTerms']"));
		acceptTermsCheckBox.click();
		Thread.sleep(1000);
		
		WebElement finishButton = driver.findElement(By.xpath("//*[@id='termsForm']/input"));
		finishButton.click();
		Thread.sleep(1000);
		
		WebElement confirmationPopup = driver.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[1]/div/p"));
		log.info("Confirmation message: " + confirmationPopup.getText());
		Assert.assertTrue(confirmationPopup.getText().contains("Your LoadPay™ registration has been completed successfully."), "Registration success message not found");

		//as broker update pay me now option
	}
 
}
