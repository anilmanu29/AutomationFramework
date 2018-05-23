package testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.UnmatchedCarrierOutlookNexdDay;
import pages.outlooklogin;

public class UnmatchedCarrierOutlookNextDayTest  extends TestBase {
	
	UnmatchedCarrierOutlookNexdDay outlookk;
	outlooklogin outlook;
	public static String pwd;

	public UnmatchedCarrierOutlookNextDayTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		outlook = new outlooklogin();
		outlookk = new UnmatchedCarrierOutlookNexdDay();
	}

	@Test(dataProvider = "getoutlookLoginData", priority=24)
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(priority=25)
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.prop.getProperty("email"));
		//outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.switchtoCarrieregistration();
		outlookk.unmatchedCarrierRegistration();
	
	} 
	 @Test(dataProvider ="getCarrierRegisterData",priority=26)
	  public void CarrierRegister( String Dotnumber, String CompanyName, String DoingBussinessAS, String Email, String ConfirmEmail, String ZipCode1, String Address, String City,
	      String FirstNames, String LastName, String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber, String BankAccountNumber,
	      String ConfirmbankAccountNumber ) throws IOException, InterruptedException
	  {
	   
		 
		 outlookk.companyname( CompanyName );

		Thread.sleep(1000);
		
		outlookk.doingbussiness( DoingBussinessAS );
	    
	    
		outlookk.selectType();

		Thread.sleep(1000);
		
	    Select type = new Select( driver.findElement( By.xpath( ".//*[@id='EntityType']" ) ) );
	    
	    type.selectByVisibleText( "C Corporation" );
	  
	    outlookk.countryofincorporation();

		Thread.sleep(1000);
		
	    Select countryof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationCountry']" ) ) );
	    
	    countryof.selectByIndex( 0 );
	    
	    outlookk.stateofincorporation();

		Thread.sleep(1000);
		
	    Select stateof = new Select( driver.findElement( By.xpath( ".//*[@id='IncorporationState']" ) ) );
	    
	    stateof.selectByVisibleText( "California" );
	   
//	     outlookk.CarrierEmail(Email);
//	    
//	 
//	     outlookk.confirmEmail( ConfirmEmail );

		Thread.sleep(1000);
		outlookk.iCertifyClick();
		Thread.sleep(1000);

		Thread.sleep(1000);
		outlookk.next();

		Thread.sleep(1000);
		
		outlookk.ZipCode( ZipCode1 );

		Thread.sleep(1000);
		
		outlookk.country();

		Thread.sleep(1000);
		
	    Select country = new Select( driver.findElement( By.xpath( ".//*[@id='OriginCountry']" ) ) );
	    country.selectByVisibleText( "USA" );
	    
	    outlookk.address( Address );

		Thread.sleep(1000);
		
		outlookk.city( City );
	    
		outlookk.State();

		Thread.sleep(1000);
		
	    Select state = new Select( driver.findElement( By.xpath( ".//*[@id='State']" ) ) );
	    
	    state.selectByVisibleText( "CA" );

		Thread.sleep(1000);
		outlookk.submit();
		Thread.sleep(1000);
		outlookk.ContactFirstName(FirstNames);
		Thread.sleep(1000);
		outlookk.LastName(LastName);
		outlookk.Phone(PhoneNumber);
	    Thread.sleep(1000);
	   pwd=  outlookk.Password(Password);
	    Thread.sleep(1000);
	    driver.findElement(By.xpath(".//*[@id='Registration_User_Password']"));
	    outlookk.ConfirmPassword(ConfirmPassword);
	  	Thread.sleep(1000);
	  	outlookk.Next();
	  	Thread.sleep(1000);
	  	outlookk.AccountName(NameonAccount);
	  	outlookk.BankingAccount(BankAccountNumber);
		Thread.sleep(1000);
		outlookk.BankingRouting(RoutingNumber);
		Thread.sleep(1000);
		Thread.sleep(1000);
		outlookk.ConfirmBankingAccount(ConfirmbankAccountNumber);
		Thread.sleep(1000);
		Thread.sleep(1000);
		outlookk.submit();
		Thread.sleep(1000);

	  
	  } 

}
