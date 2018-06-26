package testcases;
import java.awt.AWTException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.AdminEditEmailBroker;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.BrokerLoginPage;
import pages.BrokerOutlook;
import pages.BrokerRegister;
import pages.outlooklogin;

public class AdminEditEmailBrokerTest extends TestBase
{
	AdminHomePage adminHomePage;
	AdminLogin adminLoginPage;
	AdminEditEmailBroker adminEmailPage;
	outlooklogin outlookLoginObj;
	BrokerLoginPage brokerLoginObj;
	BrokerRegister brokerRegisterObj;
	BrokerOutlook brokerOutlookObj;
	
	String originalBrokerEmailAddress = "";
	String originalBrokerPassword = "";
	String updatedBrokerEmailAddress = "";
	String updatedBrokerPassword = "";
	String adminUN = "";
	String adminPW = "";
	
	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	
	public AdminEditEmailBrokerTest()
	{
		super();
	}
	
	@BeforeClass		
	public void setUp() throws IOException, AWTException 
	{
		initialization();
		adminHomePage = new AdminHomePage();	
		adminLoginPage = new AdminLogin();
		adminEmailPage = new AdminEditEmailBroker();
		outlookLoginObj = new outlooklogin();
		brokerLoginObj = new BrokerLoginPage();
		brokerRegisterObj = new BrokerRegister();
		brokerOutlookObj = new BrokerOutlook();
		currentTime = new Date();
	}
	
	@AfterClass
	public void revertToOriginalEmail() throws InterruptedException, AWTException
	{
		//search-for and reset the updated email address to the original email address
		adminHomePage.AdminURL();
		Thread.sleep(1000);
		adminLoginPage.ClickOnCustomersTab();
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchBox(updatedBrokerEmailAddress);
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchButton();
		Thread.sleep(1000);
		adminLoginPage.DoubleClickID();
		Thread.sleep(1000);
		adminEmailPage.openEmailLoginUsersPage();
		Thread.sleep(1000);
		adminEmailPage.clickEditEmailButton();
		Thread.sleep(1000);
		adminEmailPage.setNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.confirmNewEmailAddress(originalBrokerEmailAddress);
		adminEmailPage.clickUpdateEmailEditButton();
		Thread.sleep(10000);
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(originalBrokerEmailAddress), "Original" + originalBrokerEmailAddress + "] not found in confirmation!");
		adminEmailPage.clickCloseEmailConfirmationButton();
		Thread.sleep(1000);
		adminEmailPage.clickRefreshButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(originalBrokerEmailAddress), "Original Email Address Not Found!");
	}
	
	@Test(description="LP-5432 Admin_EditEmail_registerNewBroker", dataProvider = "getBrokerRegisterData", priority = 90)
	public void registerNewBroker(String DotNumber, String CompanyName, String DoingBusinessAs, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
		      String FirstName, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameOnAccount, String RoutingNumber, String BankAccountNumber,
		      String ConfirmbankAccountNumber ) throws AWTException, InterruptedException
	{
		Select type;
		Select state;
		Select country;
		
		//store these into global variables for reuse
		originalBrokerEmailAddress = Email;
		originalBrokerPassword = Password;
		
		//sign up and register new broker
		brokerRegisterObj.signup();
		brokerRegisterObj.shipperRegister();
		
		brokerRegisterObj.companyname(CompanyName);
		Thread.sleep(1000);
		
		brokerRegisterObj.doingbussiness(DoingBusinessAs);
		Thread.sleep(1000);
		
		brokerRegisterObj.selectType();
		Thread.sleep(1000);
		type = new Select( driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");	  
		Thread.sleep(1000);
		
		brokerRegisterObj.countryofincorporation();
		Thread.sleep(1000);	
		country = new Select(driver.findElement( By.xpath( ".//*[@id='IncorporationCountry']" ) ) );
		country.selectByIndex(0);	    
		Thread.sleep(1000);
		
		brokerRegisterObj.stateofincorporation();
		Thread.sleep(1000);		
	    state = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationState']" ) ) );
	    state.selectByVisibleText("California");
	    Thread.sleep(1000);
	    
	    brokerRegisterObj.BrokerEmail(Email);
		Thread.sleep(1000);
	    
		brokerRegisterObj.confirmEmail(ConfirmEmail);
		Thread.sleep(1000);
		
		brokerRegisterObj.iCertifyClick();
		Thread.sleep(1000);
		
		brokerRegisterObj.paymentTerm();
		Thread.sleep(1000);
		
		brokerRegisterObj.next();
		Thread.sleep(1000);	
		
		brokerRegisterObj.ZipCode(ZipCode1);
		Thread.sleep(1000);
		
		brokerRegisterObj.country();
		Thread.sleep(1000);
	    country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
	    country.selectByVisibleText("USA"); 
	    Thread.sleep(1000);
	    
	    brokerRegisterObj.address(Address);
		Thread.sleep(1000);	
		
		brokerRegisterObj.city(City);   
		Thread.sleep(1000);
		
		brokerRegisterObj.State();
		Thread.sleep(1000);	
	    state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));  
	    state.selectByVisibleText( "CA" );
		Thread.sleep(1000);
		
		brokerRegisterObj.submit();
		Thread.sleep(1000);
		
		brokerRegisterObj.ContactFirstName(FirstName);
		Thread.sleep(1000);
		
		brokerRegisterObj.LastName(LastName);
		Thread.sleep(1000);
		
		brokerRegisterObj.Phone(PhoneNumber);
	    Thread.sleep(1000);
	    
	    brokerRegisterObj.Password( Password );
	    Thread.sleep(1000);
	    
	    brokerRegisterObj.ConfirmPassword(ConfirmPassword);
	  	Thread.sleep(1000);
	  	
	  	brokerRegisterObj.Next();
	  	Thread.sleep(1000);
	  	
	  	brokerRegisterObj.AccountName(NameOnAccount);
	  	Thread.sleep(1000);
	  	
	  	brokerRegisterObj.BankingAccount(BankAccountNumber);
		Thread.sleep(1000);
		
		brokerRegisterObj.BankingRouting(RoutingNumber);
		Thread.sleep(1000);
		
		brokerRegisterObj.ConfirmBankingAccount(ConfirmbankAccountNumber);
		Thread.sleep(1000);
		
		brokerRegisterObj.submit();
		Thread.sleep(1000);
	    
		System.out.println(" Broker Register Completed...");
	}

	@Test(description="LP-5432 Admin_EditEmail_adminLogin",  dependsOnMethods = {"registerNewBroker"}, dataProvider="getAdminLoginData", priority = 91)
	public void adminLogin(String Username,String pass) throws AWTException, InterruptedException
	{		
		adminHomePage.AdminURL();
		Thread.sleep(1000);
		adminUN = Username;
		adminPW = pass;
		adminLoginPage.adminUserPass(adminUN, adminPW);
		Thread.sleep(1000);
		adminLoginPage.adminLogin();
		Thread.sleep(1000);
	}
	
	@Test(description="LP-5432 Admin_EditEmail_Broker", dependsOnMethods = {"adminLogin"}, priority = 92)
	public void brokerEditEmailTest() throws InterruptedException
	{
		int randomNumber = adminEmailPage.getRandomNumber(1,999999);
		updatedBrokerEmailAddress = originalBrokerEmailAddress.replaceFirst("@", randomNumber + "@");
		
		System.out.println(originalBrokerEmailAddress);
		System.out.println(updatedBrokerEmailAddress);
		
		adminLoginPage.ClickOnCustomersTab();
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchBox(originalBrokerEmailAddress);
		Thread.sleep(1000);
		adminLoginPage.ClickOnSearchButton();
		Thread.sleep(1000);
		adminLoginPage.DoubleClickID();
		Thread.sleep(1000);
		adminEmailPage.openEmailLoginUsersPage();
		Thread.sleep(1000);
		adminEmailPage.clickEditEmailButton();
		Thread.sleep(1000);
		
		//verify fields and buttons
		Assert.assertTrue(adminEmailPage.getNewEmailField().isEnabled(), "New Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getNewEmailConfirmField().isEnabled(), "Confirm Email Field Disabled!");
		Assert.assertTrue(adminEmailPage.getCancelEmailEditButton().isEnabled(), "Cancel Button Disabled!");
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(), "Update Button is enabled - should be disabled unless email addresses entered!");
	
		//verify users can cancel and return to the Email/Login/Users page
		adminEmailPage.clickCancelEmailEditButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminEmailPage.getEditEmailButton().isDisplayed(), "Edit Email Button not found!");
		
		//Click Edit email again
		adminEmailPage.clickEditEmailButton();
		Thread.sleep(1000);
		//set new email address
		adminEmailPage.setNewEmailAddress(updatedBrokerEmailAddress);
		//verify update button is not yet enabled since the confirm field has not been set
		Assert.assertTrue(!adminEmailPage.getUpdateEmailEditButton().isEnabled(), "Update Button is enabled - should be disabled unless email addresses entered!");
		//set confirmation email address
		adminEmailPage.confirmNewEmailAddress(updatedBrokerEmailAddress);
		//verify update button is now enabled
		Assert.assertTrue(adminEmailPage.getUpdateEmailEditButton().isEnabled(), "Update Button is enabled - should be disabled unless email addresses entered!");
		//click update button
		adminEmailPage.clickUpdateEmailEditButton();
		Thread.sleep(3000);
		//verify close-confirmation button is displayed
		Assert.assertTrue(adminEmailPage.getCloseEmailConfirmationButton().isDisplayed(), "Close-confirmation button not found!");
		
		//verify updated email address is displayed in confirmation popup
		Assert.assertTrue(adminEmailPage.getNewLoadPayEmailLabel().getText().contains(updatedBrokerEmailAddress), 
				"New email address [" + updatedBrokerEmailAddress + "] not found in confirmation!");

		adminEmailPage.clickCloseEmailConfirmationButton();
		Thread.sleep(1000);
		adminEmailPage.clickRefreshButton();
		Thread.sleep(1000);
		Assert.assertTrue(adminEmailPage.getEmailPagePrimaryAddress().getText().contains(updatedBrokerEmailAddress), "Updated Email Address Not Found!");
		
		//Activate new broker in admin panel
		adminEmailPage.openCompanyPage();
		Thread.sleep(1000);
		Select customerStatus = new Select(driver.findElement(By.id("CustomerStatusId")));
		customerStatus.selectByVisibleText("Active");
		Thread.sleep(1000);
		adminEmailPage.clickUpdateCompanyButton();
		Thread.sleep(1000);
		
		/////////////////////////////////////////////////////////////////
		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		
		if(Integer.parseInt(currentHour) > 12)
			currentHour = Integer.toString((Integer.parseInt(currentHour) - 14)); //minus 12 hour offset and an additional 2 hours for the eastern time zone in outlook
		else
			currentHour = Integer.toString((Integer.parseInt(currentHour) - 2));	//minus 2 hours for EST -> CST offset
		
		currentMinutes = timeArray[1];
		System.out.println("\n\n\n===============================");
		System.out.println("Current date: " + longTime);
		System.out.println("Formatted date: " + formattedDate);
		System.out.println("Hour: " + currentHour);
		System.out.println("Minutes: " + currentMinutes);
		System.out.println("\n\n\n===============================");
		//////////////////////////////////////////////////////////////////
	}	
	
	@SuppressWarnings("static-access")
	@Test(description = "LP-5432 Admin_EditEmail_Outlook", dependsOnMethods = {"brokerEditEmailTest"}, dataProvider = "getoutlookLoginData", priority=93)
	public void verifyBrokerEmailInOutlookTest(String un, String pwd) throws InterruptedException
	{
		
		try {
			outlookLoginObj.outlookLogin(un, pwd);
			brokerOutlookObj.clickPopUp();
			brokerOutlookObj.clickOpenMailBox();
			brokerOutlookObj.enterEmail(super.prop.getProperty("email"));
			brokerOutlookObj.outlookSearchInbox(updatedBrokerEmailAddress, currentHour, currentMinutes);
			brokerOutlookObj.handleUpdatedEmailInbox(updatedBrokerEmailAddress);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Test(description = "LP-5432 Admin_EditEmail_UpdatedBrokerLogin", dependsOnMethods = {"verifyBrokerEmailInOutlookTest"}, priority=94)
	public void verifyUpdatedBrokerLogin() throws InterruptedException
	{
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		Thread.sleep(6000);
		
		//set new password and confirm pw
		WebElement newPassword = driver.findElement(By.xpath("//*[@id='User_Password']"));
		WebElement confirmPassword = driver.findElement(By.xpath("//*[@id='User_ConfirmPassword']"));
		WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"formLogIn\"]/div/div[2]/div/div/div[1]/div[3]/input"));

		updatedBrokerPassword = originalBrokerPassword + adminEmailPage.getRandomNumber(1,999);
		System.out.println("\n\n\n==================================================");
		System.out.println("Updated Username: " + updatedBrokerEmailAddress);
		System.out.println("Updated Password: " + updatedBrokerPassword);
		System.out.println("==================================================\n\n\n");
		
		//reset and confirm password
		newPassword.sendKeys(updatedBrokerPassword);
		confirmPassword.sendKeys(updatedBrokerPassword);
		submitButton.click();
		
		//log in as broker
		brokerLoginObj.Brokerlogin(updatedBrokerEmailAddress, updatedBrokerPassword);

		WebElement loginLabel = driver.findElement(By.xpath("//span[@title='" + updatedBrokerEmailAddress + "']"));		
		Assert.assertTrue(loginLabel.getText().equals(updatedBrokerEmailAddress), "Updated email address not seen after login!");
	}
}
